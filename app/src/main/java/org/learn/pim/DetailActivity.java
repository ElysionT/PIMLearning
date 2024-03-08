package org.learn.pim;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;

import org.learn.pim.model.DetailData;
import org.learn.pim.model.DetailLoader;
import org.learn.pim.model.ImageData;
import org.learn.pim.model.contacts.ContactsConstants;
import org.learn.pim.util.Constants;

public class DetailActivity extends AppCompatActivity implements LoaderCallbacks<DetailData> {
    private static final String TAG = "DetailActivity";

    private static final String KEY_LOOKUP = "lookup";

    private TextView mDetailContext;
    private ImageView mPhotoView;

    private Uri mLookup;
    private DetailData mDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        mDetailContext = findViewById(R.id.detial_content);
        mPhotoView = findViewById(R.id.photo);
        Log.i(TAG, "onCreate - processIntent:" + getIntent());
        processIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent - processIntent:" + getIntent());
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (null == intent) {
            finish();
            return;
        }

        Uri lookupUri = intent.getData();
        // adb shell am start -a "com.android.contacts.action.QUICK_CONTACT" -d "//contacts/10" -t "vnd.android.cursor.item/contact"
//        if (lookupUri != null && android.provider.Contacts.AUTHORITY.equals(lookupUri.getAuthority())) {
//            final long rawContactId = ContentUris.parseId(lookupUri);
//            lookupUri = RawContacts.getContactLookupUri(getContentResolver(),
//                    ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId));
//        }

        if (null == lookupUri) {
            finish();
            return;
        }

        mLookup = lookupUri;

        int id = intent.getIntExtra(Constants.EXTRA_MODE, -1);

        if (-1 == id) {
            finish();
            return;
        }

        final LoaderManager loaderManager = LoaderManager.getInstance(this);
        // (Loader<?>) Inconvertible types; cannot cast 'androidx.loader.content.Loader<java.lang.Object>' to 'org.learn.pim.model.contacts.ContactLoader'
        DetailLoader detailLoader = (DetailLoader) (Loader<?>) loaderManager.getLoader(id);
//        final Bundle bundle = new Bundle();
//        bundle.putParcelable(KEY_LOOKUP, lookupUri);
        if (null == detailLoader) {
            Log.i(TAG, "initLoader - LOADER_CONTACTS_LOOKUP:" + id);
            detailLoader = (DetailLoader) (Loader<?>) loaderManager.initLoader(id, null, this);
        } else {
            Log.i(TAG, "forceLoad - LOADER_CONTACTS_LOOKUP:" + id);
            detailLoader.setNewLookup(lookupUri);
            detailLoader.forceLoad();
        }
    }

    @NonNull
    @Override
    public Loader<DetailData> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i(TAG, "onCreateLoader - id:" + id + " args:" + args);
        return DetailLoader.getLoaderInstance(getApplicationContext(), mLookup);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<DetailData> loader, DetailData data) {
        Log.i(TAG, "onLoadFinished - contact:" + data);
        if (isFinishing())
            return;
        if (data.isError()) {
            Toast.makeText(getApplicationContext(), "Failed to load contact: " + ((DetailLoader) loader).getLookupUri(), Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Failed to load contact: " + ((DetailLoader) loader).getLookupUri());
            finish();
            return;
        }
        if (data.isNotFound()) {
            Toast.makeText(getApplicationContext(), "No contact found: " + ((DetailLoader) loader).getLookupUri(), Toast.LENGTH_SHORT).show();
            Log.i(TAG, "No contact found: " + ((DetailLoader) loader).getLookupUri());
            finish();
            return;

        }
//        mContactData = data;
        if (data instanceof ImageData) {
            mPhotoView.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams layoutParams = mPhotoView.getLayoutParams();
            final Bitmap photo = ((ImageData) data).decodedPhotoBitmap();
            Log.i(TAG, "onLoadFinished - photo:" + photo.getWidth() + " - " + photo.getHeight());
            layoutParams.width = photo.getWidth();
            layoutParams.height = photo.getHeight();
            mPhotoView.setLayoutParams(layoutParams);
            mPhotoView.setImageBitmap(photo);
//            mPhotoView.setImageDrawable(((ImageData) data).decodedPhotoDrawable(getResources()));
        } else {
            mDetailContext.setVisibility(View.VISIBLE);
            mDetailContext.setText(data.getDataDetail());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<DetailData> loader) {
        Log.i(TAG, "onLoaderReset - id:" + loader.getId());
//        mContactData = null;

    }

    /**
     * Returns an implicit intent for opening DetailActivity.
     */
    public static Intent composeQuickContactIntent(Context context, Uri uri, int subAction) {
        final Intent intent;
        if (ensureIsDirUri(context.getContentResolver(), uri) || ContactsConstants.isDirAction(subAction)) {
            intent = new Intent(context, SubListActivity.class);
            intent.setAction(Constants.ACTION_SUB_LIST);
        } else {
            intent = new Intent(context, DetailActivity.class);
            intent.setAction(Constants.ACTION_DETAIL);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        intent.setData(uri);
        intent.putExtra(Constants.EXTRA_MODE, subAction);
        // Make sure not to show DetailActivity on top of another DetailActivity.
        return intent;
    }

    private static boolean ensureIsDirUri(final ContentResolver resolver, final Uri uri) {
        if (uri == null) throw new IllegalArgumentException("uri must not be null");
        final String type = resolver.getType(uri);
        Log.i(TAG, "ensureIsDirUri - type:" + type);
        return ContactsContract.Contacts.CONTENT_TYPE.equals(type);


    }


}
