package org.learn.pim.model.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import org.learn.pim.util.Constants;

public class ContactsCursorLoader extends CursorLoader {
    private static final String TAG = "ContactsCursorLoader";
    public static final String[] PROJECTION = {
            Contacts.NAME_RAW_CONTACT_ID,
            Contacts.DISPLAY_NAME_SOURCE,
            Contacts.LOOKUP_KEY,
            Contacts.DISPLAY_NAME,
            Contacts.DISPLAY_NAME_ALTERNATIVE,
            Contacts.PHONETIC_NAME,
            Contacts.PHONETIC_NAME_STYLE,
            Contacts.PHOTO_ID,
            Contacts.PHOTO_URI,
            Contacts.PHOTO_THUMBNAIL_URI,
            Contacts.PHOTO_FILE_ID,
            Contacts.STARRED,
            Contacts.CONTACT_PRESENCE,
            Contacts.CONTACT_STATUS,
            Contacts.CONTACT_STATUS_TIMESTAMP,
            Contacts.CONTACT_STATUS_RES_PACKAGE,
            Contacts.CONTACT_STATUS_LABEL,
            Contacts.CONTACT_STATUS_ICON,
            Contacts._ID,
            Contacts.SEND_TO_VOICEMAIL,
            Contacts.CUSTOM_RINGTONE,
            Contacts.IS_USER_PROFILE,
            Contacts.IN_VISIBLE_GROUP,
            Contacts.HAS_PHONE_NUMBER,
            Contacts.IN_DEFAULT_DIRECTORY,
            Contacts.SORT_KEY_PRIMARY,
            Contacts.SORT_KEY_ALTERNATIVE,
            Contacts.PINNED,
            Contacts.CONTACT_CHAT_CAPABILITY,
            Contacts.CONTACT_LAST_UPDATED_TIMESTAMP,
            Contacts.LAST_TIME_CONTACTED,
            Contacts.TIMES_CONTACTED,

            /*PHONEBOOK_LABEL_PRIMARY*/"phonebook_label",
            /*PHONEBOOK_LABEL_ALTERNATIVE*/"phonebook_label_alt",
            /*PHONEBOOK_BUCKET_PRIMARY*/"phonebook_bucket",
            /*PHONEBOOK_BUCKET_ALTERNATIVE*/"phonebook_bucket_alt",

            RawContacts.ACCOUNT_NAME,
            RawContacts.ACCOUNT_TYPE,
    };

    public static final int NAME_RAW_CONTACT_ID = 0;
    public static final int DISPLAY_NAME_SOURCE = 1;
    public static final int LOOKUP_KEY = 2;
    public static final int DISPLAY_NAME = 3;
    public static final int DISPLAY_NAME_ALT = 4;
    public static final int PHONETIC_NAME = 5;
    public static final int PHONETIC_NAME_STYLE = 6;
    public static final int PHOTO_ID = 7;
    public static final int PHOTO_URI = 8;
    public static final int PHOTO_THUMBNAIL_URI = 9;
    public static final int PHOTO_FILE_ID = 10;
    public static final int STARRED = 11;
    public static final int CONTACT_PRESENCE = 12;
    public static final int CONTACT_STATUS = 13;
    public static final int CONTACT_STATUS_TIMESTAMP = 14;
    public static final int CONTACT_STATUS_RES_PACKAGE = 15;
    public static final int CONTACT_STATUS_LABEL = 16;
    public static final int CONTACT_STATUS_ICON = 17;
    public static final int _ID = 18;
    public static final int SEND_TO_VOICEMAIL = 19;
    public static final int CUSTOM_RINGTONE = 20;
    public static final int IS_USER_PROFILE = 21;
    public static final int IN_VISIBLE_GROUP = 22;
    public static final int HAS_PHONE_NUMBER = 23;
    public static final int IN_DEFAULT_DIRECTORY = 24;
    public static final int SORT_KEY = 25;
    public static final int SORT_KEY_ALT = 26;
    public static final int PINNED = 27;
    public static final int CONTACT_CHAT_CAPABILITY = 28;
    public static final int CONTACT_LAST_UPDATED_TIMESTAMP = 29;
    public static final int LAST_TIME_CONTACTED = 30;
    public static final int TIMES_CONTACTED = 31;
    public static final int PHONEBOOK_LABEL = 32;
    public static final int PHONEBOOK_LABEL_ALT = 33;
    public static final int PHONEBOOK_BUCKET = 34;
    public static final int PHONEBOOK_BUCKET_ALT = 35;
    public static final int ACCOUNT_NAME = 36;
    public static final int ACCOUNT_TYPE = 37;

    public ContactsCursorLoader(@NonNull Context context, String queryString, int loaderId) {
        this(context, buildUri(queryString, loaderId));
    }

    public ContactsCursorLoader(@NonNull Context context, Uri uri) {
        super(context, uri, PROJECTION, null, null, null);
    }

    private static Uri buildUri(String queryString, int loaderId) {
        Uri.Builder baseUri = ContactsConstants.getUri(loaderId, -1, null, queryString).buildUpon();
        Log.i(Constants.LOG_IMPORTANT, "buildUri - baseUri:" + baseUri);
        return baseUri.build();
    }
}
