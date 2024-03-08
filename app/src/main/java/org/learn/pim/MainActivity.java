package org.learn.pim;

import android.Manifest;
import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;

import org.learn.pim.model.ListCursorAdapter;
import org.learn.pim.model.contacts.ContactsConstants;
import org.learn.pim.model.contacts.ContactsConstants.Actions;
import org.learn.pim.util.Constants;
import org.learn.pim.util.PermissionsUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderCallbacks<Cursor>,
        View.OnClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener, AbsListView.MultiChoiceModeListener {

    private static final String TAG = "MainActivity";

    private ListView mListView;
    private Spinner mSubActionListView;
    private Spinner mActionListView;
    private EditText mQueryContentView;
    private EditText mSubQueryContentView;
    private CheckBox mSubActionOptionView;

    private ListCursorAdapter mListCursorAdapter;

    private ContactsConstants.Actions mOldAction;

//    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubActionOptionView = findViewById(R.id.sub_action_option);
        mQueryContentView = findViewById(R.id.action_query_content);
        mSubQueryContentView = findViewById(R.id.sub_action_query_content);

        ((Button) findViewById(R.id.action_done)).setOnClickListener(this);

        mListView = (ListView) findViewById(android.R.id.list);
        mListView.setOnItemClickListener(this);
        mListView.setMultiChoiceModeListener(this);

        mActionListView = findViewById(R.id.action_list);
        mActionListView.setAdapter(new ArrayAdapter<Actions>(getApplicationContext(), android.R.layout.simple_spinner_item, ContactsConstants.Actions.values()));
        mActionListView.setOnItemSelectedListener(this);

        mSubActionListView = findViewById(R.id.sub_action_list);
        mSubActionListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String subActionName = (String) parent.getSelectedItem();
                if (null == subActionName)
                    return;
                final int subAction = ((Actions) mActionListView.getSelectedItem()).subAction.get(subActionName);
                if (ContactsConstants.isSupportMultiple(subAction)) {
                    mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                } else {
                    mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
            }
        });

        final ContentResolver resolver = getApplicationContext().getContentResolver();

        Button action = findViewById(R.id.action);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Insert
                Account account = new Account("Tablet", "Local Phone Account");
                ContentValues values = new ContentValues();
                if (account != null) {
                    values.put(ContactsContract.RawContacts.ACCOUNT_NAME, account.name);
                    values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, account.type);
                }
                Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(uri);
                Log.w(TAG, "Insert uri:" + uri + " rawContactId:" + rawContactId);

                // 2. Delete
                uri = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, rawContactId)
                        .buildUpon()
                        .appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "false")
                        .build();
                resolver.delete(uri, null, null);
                Log.w(TAG, "Delete uri:" + uri + " rawContactId:" + rawContactId);
            }
        });
        testSetDefaultAccount();
    }


    public void testSetDefaultAccount() {
        Intent intent = new Intent(ContactsContract.Settings.ACTION_SET_DEFAULT_ACCOUNT);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL));
        Log.w(TAG, "==============================================");
        for (ResolveInfo info : resolveInfoList) {
            Log.i(TAG, "ResolveInfo:" + info);
        }
        Log.e(TAG, "==============================================");

//        assertNotNull("Missing ResolveInfo", resolveInfoList);
//        int handlerCount = 0;
//        for (ResolveInfo resolveInfo : resolveInfoList) {
//            String packageName = resolveInfo.activityInfo.packageName;
//            if (packageManager.checkPermission(
//                    android.Manifest.permission.SET_DEFAULT_ACCOUNT_FOR_CONTACTS, packageName)
//                    == PackageManager.PERMISSION_GRANTED) {
//                handlerCount++;
//            }
//        }
//        assertEquals(1, handlerCount);
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
    public void onClick(View v) {
        final int viewId = v.getId();
        final LoaderManager loaderManager = LoaderManager.getInstance(this);
        switch (viewId) {
            case R.id.action_done:
                if (PermissionsUtil.hasPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS)) {
                    ContactsConstants.Actions action = (ContactsConstants.Actions) mActionListView.getSelectedItem();
                    if (action != mOldAction) {
                        mListCursorAdapter = ListCursorAdapter.getInstance(getApplicationContext(), action.id);
                        mListView.setAdapter(mListCursorAdapter);
                        if (null != mOldAction) {
                            loaderManager.destroyLoader(mOldAction.id);
                        }
                    }
                    mOldAction = action;
                    int id = action.id;
                    final Bundle bundle = new Bundle();
                    if (ContactsConstants.isSupportFilter(id)) {
                        final String queryString = mQueryContentView.getEditableText().toString();
                        if (TextUtils.isEmpty(queryString)) {
                            Toast.makeText(getApplicationContext(), "Query content is empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        bundle.putString(Constants.EXTRA_QUERY, queryString);
                    }
                    bundle.putBoolean(Constants.EXTRA_FLAG, mSubActionOptionView.isChecked());
                    if (null == loaderManager.getLoader(id)) {
                        Log.i(TAG, "initLoader - action:" + action);
                        loaderManager.initLoader(action.id, bundle, this);
                    } else {
                        Log.i(TAG, "restartLoader - action:" + action);
                        loaderManager.restartLoader(id, bundle, this);
                    }
                    hideSoftKeyboard(mQueryContentView);
                } else {
                    PermissionsUtil.requestPermissions(this, Manifest.permission.READ_CONTACTS);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "onRequestPermissionsResult - requestCode:" + requestCode + " permissions:" + permissions + " grantResults:" + grantResults);
        if (null != permissions && permissions.length > 0) {
            for (int i = 0, lenght = permissions.length; i < lenght; i++) {
                Toast.makeText(getApplicationContext(), permissions[i] + ":" + grantResults[i], Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick - tag:" + view.getTag() + " position:" + position + " id:" + id);
        final String subActionName = (String) mSubActionListView.getSelectedItem();
        if (null == subActionName)
            return;
        final int subAction = mOldAction.subAction.get(subActionName);
        Uri uri = ContactsConstants.getUri(subAction, id, view.getTag(), mSubQueryContentView.getText().toString(), mSubActionOptionView.isChecked());
        Log.i(Constants.LOG_IMPORTANT, "onItemClick - subActionName:" + subActionName + " subAction:" + subAction + " uri:" + uri);
        startActivity(DetailActivity.composeQuickContactIntent(getApplicationContext(), uri, subAction));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ContactsConstants.Actions action = (ContactsConstants.Actions) parent.getSelectedItem();
        if (action != mOldAction) {
            mSubActionListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item,
                    action.subAction.keySet().toArray(new String[0])));
        }
        boolean isFilterAction = ContactsConstants.isSupportFilter(action.id);
        mQueryContentView.setEnabled(isFilterAction);
        if (isFilterAction) {
            showSoftKeyboard(mQueryContentView);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSubActionListView.setAdapter(null);
    }

    /**
     * Hide software keyboard for the given {@link View}.
     */
    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Show the soft-keyboard
     */
    public void showSoftKeyboard(View view) {
        view.post(() -> {
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        });
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getMenuInflater().inflate(R.menu.action_mode_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        final String subActionName = (String) mSubActionListView.getSelectedItem();
        if (null == subActionName)
            return false;

        int id = item.getItemId();
        switch (id) {
            case R.id.action_option_done:
                final SparseBooleanArray postions = mListView.getCheckedItemPositions();
                final StringBuffer lookups = new StringBuffer();
                for (int i = 0, size = postions.size(); i < size; i++) {
                    lookups.append(mListCursorAdapter.getItemLookup(postions.keyAt(i)));
                    if (i != size - 1) {
                        lookups.append(":");
                    }
                }
                System.out.println("lookups:" + lookups);
                final int subAction = mOldAction.subAction.get(subActionName);
                Uri uri = ContactsConstants.getUri(subAction, id, lookups.toString(), mSubQueryContentView.getText().toString(), mSubActionOptionView.isChecked());
                Log.i(Constants.LOG_IMPORTANT, "onActionItemClicked - subActionName:" + subActionName + " subAction:" + subAction + " uri:" + uri);
                startActivity(DetailActivity.composeQuickContactIntent(getApplicationContext(), uri, subAction));

                return true;
            default:
                return false;
        }

    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        mode.setTitle("Selected: " + mListView.getCheckedItemCount());
    }
}