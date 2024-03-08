package org.learn.pim.model.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

import org.learn.pim.util.Constants;

public class RawContactsCursorLoader extends CursorLoader {

    public static final String[] PROJECTION = {
            RawContacts._ID,
            RawContacts.CONTACT_ID,
            RawContacts.DELETED,
            RawContacts.DISPLAY_NAME_PRIMARY,
            RawContacts.DISPLAY_NAME_ALTERNATIVE,
            RawContacts.DISPLAY_NAME_SOURCE,
            RawContacts.PHONETIC_NAME,
            RawContacts.PHONETIC_NAME_STYLE,
            RawContacts.SORT_KEY_PRIMARY,
            RawContacts.SORT_KEY_ALTERNATIVE,
            RawContacts.CUSTOM_RINGTONE,
            RawContacts.SEND_TO_VOICEMAIL,
            RawContacts.STARRED,
            RawContacts.PINNED,
            RawContacts.AGGREGATION_MODE,
            RawContacts.RAW_CONTACT_IS_USER_PROFILE,
            RawContacts.METADATA_DIRTY,
            RawContacts.ACCOUNT_NAME,
            RawContacts.ACCOUNT_TYPE,
            RawContacts.DATA_SET,
            RawContacts.ACCOUNT_TYPE_AND_DATA_SET,
            RawContacts.DIRTY,
            RawContacts.SOURCE_ID,
            RawContacts.BACKUP_ID,
            RawContacts.VERSION,
            RawContacts.SYNC1,
            RawContacts.SYNC2,
            RawContacts.SYNC3,
            RawContacts.SYNC4,
            /*RawContactsColumns.PHONEBOOK_LABEL_PRIMARY*/ "phonebook_label",
            /*RawContactsColumns.PHONEBOOK_BUCKET_PRIMARY*/ "phonebook_bucket",
            /*RawContactsColumns.PHONEBOOK_LABEL_ALTERNATIVE*/ "phonebook_label_alt",
            /*RawContactsColumns.PHONEBOOK_BUCKET_ALTERNATIVE*/ "phonebook_bucket_alt",
//            RawContacts.LR_TIMES_CONTACTED,
//            RawContacts.LR_LAST_TIME_CONTACTED,
    };

    public static final int _ID = 0;
    public static final int CONTACT_ID = 1;
    public static final int DELETED = 2;
    public static final int DISPLAY_NAME_PRIMARY = 3;
    public static final int DISPLAY_NAME_ALTERNATIVE = 4;
    public static final int DISPLAY_NAME_SOURCE = 5;
    public static final int PHONETIC_NAME = 6;
    public static final int PHONETIC_NAME_STYLE = 7;
    public static final int SORT_KEY_PRIMARY = 8;
    public static final int SORT_KEY_ALTERNATIVE = 9;
    public static final int CUSTOM_RINGTONE = 10;
    public static final int SEND_TO_VOICEMAIL = 11;
    public static final int STARRED = 12;
    public static final int PINNED = 13;
    public static final int AGGREGATION_MODE = 14;
    public static final int RAW_CONTACT_IS_USER_PROFILE = 15;
    public static final int METADATA_DIRTY = 16;
    public static final int ACCOUNT_NAME = 17;
    public static final int ACCOUNT_TYPE = 18;
    public static final int DATA_SET = 19;
    public static final int ACCOUNT_TYPE_AND_DATA_SET = 20;
    public static final int DIRTY = 21;
    public static final int SOURCE_ID = 22;
    public static final int BACKUP_ID = 23;
    public static final int VERSION = 24;
    public static final int SYNC1 = 25;
    public static final int SYNC2 = 26;
    public static final int SYNC3 = 27;
    public static final int SYNC4 = 28;
    public static final int PHONEBOOK_LABEL_PRIMARY = 29;
    public static final int PHONEBOOK_BUCKET_PRIMARY = 30;
    public static final int PHONEBOOK_LABEL_ALTERNATIVE = 31;
    public static final int PHONEBOOK_BUCKET_ALTERNATIVE = 32;
//  public static final int LR_TIMES_CONTACTED = 33;
//  public static final int LR_LAST_TIME_CONTACTED = 34;

    public RawContactsCursorLoader(@NonNull Context context, String queryString, int loaderId, @Nullable String selection, @Nullable String[] selectionArgs) {
        this(context, buildUri(queryString, loaderId), selection, selectionArgs);
    }

    public RawContactsCursorLoader(@NonNull Context context, Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        super(context, uri, PROJECTION, selection, selectionArgs, null);
    }

    private static Uri buildUri(String queryString, int loaderId) {
        Uri.Builder baseUri = ContactsConstants.getUri(loaderId, -1, null, queryString).buildUpon();
        Log.i(Constants.LOG_IMPORTANT, "buildUri - baseUri:" + baseUri);
        return baseUri.build();
    }
}
