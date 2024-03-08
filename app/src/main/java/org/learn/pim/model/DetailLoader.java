package org.learn.pim.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.learn.pim.model.contacts.ContactLoader;
import org.learn.pim.model.contacts.ContactsConstants;

public class DetailLoader<T extends DetailData> extends AsyncTaskLoader<T> {
    private static final String TAG = "DetailLoader";

    private static enum Type {
        CONTACTS,
        DATA,
        IMAGE,
        VCARD,
        ERROR;
    }

    protected Uri mLookupUri;

    public DetailLoader(Context context, Uri lookupUri) {
        super(context);
        mLookupUri = lookupUri;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        cancelLoad();
    }

    @Nullable
    @Override
    public T loadInBackground() {
        T result = (T) new DetailData(DetailData.Status.NOT_FOUND);

        final ContentResolver resolver = getContext().getContentResolver();
        Cursor cursor = resolver.query(mLookupUri, null, null, null, null);
        if (null == cursor) {
            return result;
        }
        try {
            if (!cursor.moveToFirst()) {
                cursor.close();
                return result;
            }
            result.mStatus = DetailData.Status.LOADED;
            result.iterateDate(cursor);

            return result;
        } finally {
            cursor.close();
        }
    }

    public Uri getLookupUri() {
        return mLookupUri;
    }

    public void setNewLookup(Uri lookupUri) {
        mLookupUri = lookupUri;
    }

    public static DetailLoader getLoaderInstance(Context context, Uri uri) {
        switch (ensureUriType(context.getContentResolver(), uri)) {
            case CONTACTS:
                return new ContactLoader(context, uri);
            case DATA:
                return new DetailLoader(context, uri);
            case IMAGE:
                return new ImageLoader(context, uri);
            case VCARD:
                return new VCardLoader(context, uri);
            case ERROR:
            default:
                throw new IllegalArgumentException("Unsupport uri:" + uri);
        }
    }

    private static Type ensureUriType(final ContentResolver resolver, final Uri uri)
            throws IllegalArgumentException {
        if (uri == null) return Type.ERROR;

        final String authority = uri.getAuthority();

        // Current Style Uri?
        if (ContactsContract.AUTHORITY.equals(authority)) {
            final String type = resolver.getType(uri);
            // Contact-Uri? Good, return it
            if (ContactsContract.Contacts.CONTENT_ITEM_TYPE.equals(type))
                return Type.CONTACTS;


            if (ContactsContract.Contacts.CONTENT_VCARD_TYPE.equals(type))
                return Type.VCARD;

            // RawContact-Uri? Transform it to ContactUri
            if (ContactsContract.RawContacts.CONTENT_ITEM_TYPE.equals(type))
                return Type.DATA; // Type.CONTACTS;

            if ("image/jpeg".equals(type))
                return Type.IMAGE;

            if (uri.getPath().startsWith(ContactsConstants.CORP_CONTENT_URI.getPath()))
                return Type.IMAGE;

            // Anything else? We don't know what this is
            return Type.DATA;
        }

        // Legacy Style? Convert to RawContact
        final String OBSOLETE_AUTHORITY = android.provider.Contacts.AUTHORITY;
        if (OBSOLETE_AUTHORITY.equals(authority)) {
            // Legacy Format. Convert to RawContact-Uri and then lookup the contact
            return Type.CONTACTS;
        }
        return Type.DATA;
    }
}
