package org.learn.pim.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import org.learn.pim.R;
import org.learn.pim.model.contacts.ContactsConstants;
import org.learn.pim.model.contacts.ContactsListAdapter;
import org.learn.pim.model.contacts.DataListAdapter;
import org.learn.pim.model.contacts.GroupsListAdapter;
import org.learn.pim.model.contacts.RawContactEntitiesListAdapter;
import org.learn.pim.model.contacts.RawContactsListAdapter;

public abstract class ListCursorAdapter extends CursorAdapter {
    private static final String TAG = "ListCursorAdapter";

    private int mCurrentAction;

    public ListCursorAdapter(Context context) {
        super(context, null, false);
    }

    public abstract CursorLoader getCursorLoader(Context context, Bundle args);

    protected abstract int getIdIndex();

    protected abstract int getDisplayNameIndex();

    protected abstract int getLookupIndex();

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return itemView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.display_name)).setText(cursor.getString(getDisplayNameIndex()));
        ((TextView) view.findViewById(R.id._id)).setText(cursor.getLong(getIdIndex()) + "");
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getLong(getIdIndex());
    }

    @Override
    public Cursor getItem(int position) {
        final Cursor temp = getCursor();
        temp.moveToPosition(position);
        return temp;
    }

    public String getItemLookup(int position) {
        return getItem(position).getString(getLookupIndex());
    }

    public void setCurrentAction(int action) {
        mCurrentAction = action;
    }

    public int getCurrentAction() {
        return mCurrentAction;
    }

    public static ListCursorAdapter getInstance(Context context, int action) {
        final ListCursorAdapter adapter;
        switch (action) {
            case ContactsConstants.CONTACTS:
            case ContactsConstants.CONTACTS_FILTER:
            case ContactsConstants.CONTACTS_STREQUENT:
            case ContactsConstants.CONTACTS_STREQUENT_FILTER:
            case ContactsConstants.CONTACTS_GROUP:
            case ContactsConstants.CONTACTS_FREQUENT:
            case ContactsConstants.CONTACTS_FILTER_ENTERPRISE:
            case ContactsConstants.AGGREGATION_SUGGESTIONS: /* Sub Action */
                adapter = new ContactsListAdapter(context);
                break;
            case ContactsConstants.RAW_CONTACTS:
//            case ContactsConstants.RAW_CONTACTS_ID_STREAM_ITEMS: /* Sub Action */
                adapter = new RawContactsListAdapter(context);
                break;
            case ContactsConstants.RAW_CONTACT_ENTITIES:
            case ContactsConstants.RAW_CONTACT_ENTITIES_CORP:
                adapter = new RawContactEntitiesListAdapter(context);
                break;
            case ContactsConstants.DATA:
            case ContactsConstants.PHONES:
                adapter = new DataListAdapter(context);
                break;
            case ContactsConstants.GROUPS:
            case ContactsConstants.GROUPS_SUMMARY:
                adapter = new GroupsListAdapter(context);
                break;
            default:
                Log.w(TAG, "Unsupport action:" + action);
                throw new IllegalArgumentException("Unsupport action:" + action);
        }
        adapter.setCurrentAction(action);
        return adapter;
    }
}
