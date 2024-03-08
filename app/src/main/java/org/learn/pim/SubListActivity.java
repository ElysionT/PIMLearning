package org.learn.pim;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;

import org.learn.pim.model.ListCursorAdapter;
import org.learn.pim.model.contacts.ContactsConstants;
import org.learn.pim.util.Constants;

import java.util.Map;

public class SubListActivity extends AppCompatActivity implements
        LoaderCallbacks<Cursor>,
        ActivityCompat.OnRequestPermissionsResultCallback,
        AdapterView.OnItemClickListener {

    private static final String TAG = "SubListActivity";

    private ListView mListView;
    private Spinner mSubSubActionListView;

    private ListCursorAdapter mListCursorAdapter;

    private int mAction;
    private Map<String, Integer> mSubActions;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAction = getIntent().getIntExtra(Constants.EXTRA_MODE, -1);
        mSubActions = ContactsConstants.Actions.getSubActionsById(mAction);
        mUri = getIntent().getData();

        setContentView(R.layout.sub_list_activity);

        mSubSubActionListView = findViewById(R.id.sub_sub_action_list);
        mSubSubActionListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                mSubActions.keySet().toArray(new String[0])));

        mListView = (ListView) findViewById(android.R.id.list);
        mListView.setOnItemClickListener(this);
        mListCursorAdapter = ListCursorAdapter.getInstance(getApplicationContext(), mAction);
        mListView.setAdapter(mListCursorAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final LoaderManager loaderManager = LoaderManager.getInstance(this);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.EXTRA_URI, mUri);
        if (null == loaderManager.getLoader(mAction)) {
            Log.i(TAG, "initLoader - action:" + mAction);
            loaderManager.initLoader(mAction, bundle, this);
        } else {
            Log.i(TAG, "restartLoader - action:" + mAction);
            loaderManager.restartLoader(mAction, bundle, this);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i(TAG, "onCreateLoader - id:" + id + " args:" + args);
        return mListCursorAdapter.getCursorLoader(getApplicationContext(), args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Log.i(TAG, "onLoadFinished - id:" + loader.getId());

        if (null == data || mListCursorAdapter.getCursor() == data)
            return;
        data.moveToPosition(-1);
        long index = 0;
        while (data.moveToNext()) {
//            System.out.print(++index + ":");
            for (int i = 0, count = data.getColumnCount(); i < count; i++) {
                final String columnName = data.getColumnName(i);
                final int columnIndex = data.getColumnIndex(columnName);
                final int dataType = data.getType(columnIndex);
                Object value;
                switch (dataType) {
                    case Cursor.FIELD_TYPE_INTEGER:
                        value = data.getInt(columnIndex);
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        value = data.getFloat(columnIndex);
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        value = data.getString(columnIndex);
                        break;
                    case Cursor.FIELD_TYPE_BLOB:
                        value = data.getBlob(columnIndex);
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                    default:
                        value = null;
                        break;
                }
//                System.out.print(columnName + ":" + value + "\t");
            }
            System.out.println();
        }
        mListCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.i(TAG, "onLoaderReset - id:" + loader.getId());
        mListCursorAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick - tag:" + view.getTag() + " position:" + position + " id:" + id);
        final String subActionName = (String) mSubSubActionListView.getSelectedItem();
        if (null == subActionName)
            return;
        final int subAction = mSubActions.get(subActionName);
        Uri uri = ContactsConstants.getUri(subAction, id, view.getTag());
        Log.i(Constants.LOG_IMPORTANT, "onItemClick - subActionName:" + subActionName + " subAction:" + subAction + " uri:" + uri);
        startActivity(DetailActivity.composeQuickContactIntent(getApplicationContext(), uri, subAction));
    }
}