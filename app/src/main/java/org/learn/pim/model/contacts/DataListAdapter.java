package org.learn.pim.model.contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import org.learn.pim.R;
import org.learn.pim.model.ListCursorAdapter;
import org.learn.pim.util.Constants;

public class DataListAdapter extends ListCursorAdapter {

    public DataListAdapter(Context context) {
        super(context);
    }

    @Override
    public CursorLoader getCursorLoader(Context context, Bundle args) {
        Uri uri = null != args ? args.getParcelable(Constants.EXTRA_URI) : null;
        if (null != uri) {
            return new DataCursorLoader(context, uri);
        }
        String queryString = null != args ? args.getString(Constants.EXTRA_QUERY, null) : null;
        return new DataCursorLoader(context, queryString, getCurrentAction());
    }

    @Override
    protected int getIdIndex() {
        return DataCursorLoader._ID;
    }

    @Override
    protected int getDisplayNameIndex() {
        return DataCursorLoader.DATA1;
    }

    @Override
    protected int getLookupIndex() {
        return DataCursorLoader.LOOKUP_KEY;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        final String contactId = String.valueOf(cursor.getLong(DataCursorLoader.CONTACT_ID));
        ((TextView) view.findViewById(R.id.lookup_key)).setText(contactId);
        view.setTag(contactId);
    }
}
