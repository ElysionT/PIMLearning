package org.learn.pim.model.contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import org.learn.pim.R;
import org.learn.pim.model.ListCursorAdapter;
import org.learn.pim.util.Constants;

public class RawContactsListAdapter extends ListCursorAdapter {
    public RawContactsListAdapter(Context context) {
        super(context);
    }

    @Override
    public CursorLoader getCursorLoader(Context context, Bundle args) {
        String selection = null;
        String[] selectionArgs = null;
        if (null != args && args.containsKey(Constants.EXTRA_FLAG)) {
            selection = RawContacts.DELETED + "=?";
            selectionArgs = new String[]{args.getBoolean(Constants.EXTRA_FLAG) ? "1" : "0"};
        }

        Uri uri = null != args ? args.getParcelable(Constants.EXTRA_URI) : null;
        if (null != uri) {
            return new RawContactsCursorLoader(context, uri, selection, selectionArgs);
        }
        String queryString = null != args ? args.getString(Constants.EXTRA_QUERY, null) : null;
        return new RawContactsCursorLoader(context, queryString, getCurrentAction(), selection, selectionArgs);
    }

    @Override
    protected int getIdIndex() {
        return RawContactsCursorLoader._ID;
    }

    @Override
    protected int getDisplayNameIndex() {
        return RawContactsCursorLoader.DISPLAY_NAME_PRIMARY;
    }

    @Override
    protected int getLookupIndex() {
        throw new UnsupportedOperationException("RawContactsListAdapter unsupport getLookupIndex");
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        final String contactId = String.valueOf(cursor.getLong(RawContactsCursorLoader.CONTACT_ID));
        ((TextView) view.findViewById(R.id.lookup_key)).setText(contactId);
        view.setTag(contactId);
    }
}
