package org.learn.pim.model.contacts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import org.learn.pim.R;
import org.learn.pim.model.ListCursorAdapter;
import org.learn.pim.util.Constants;

public class GroupsListAdapter extends ListCursorAdapter {

    public GroupsListAdapter(Context context) {
        super(context);
    }

    @Override
    public CursorLoader getCursorLoader(Context context, Bundle args) {
        String queryString = null != args ? args.getString(Constants.EXTRA_QUERY, null) : null;
        return new GroupsCursorLoader(context, queryString, getCurrentAction());
    }

    @Override
    protected int getIdIndex() {
        return GroupsCursorLoader._ID;
    }

    @Override
    protected int getDisplayNameIndex() {
        return GroupsCursorLoader.TITLE;
    }

    @Override
    protected int getLookupIndex() {
        throw new UnsupportedOperationException("Group Unsupport");
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        if (ContactsConstants.GROUPS_SUMMARY == getCurrentAction()) {
            ((TextView) view.findViewById(R.id.lookup_key)).setText(cursor.getLong(GroupsCursorLoader.SUMMARY_COUNT) + "");
        }
    }
}
