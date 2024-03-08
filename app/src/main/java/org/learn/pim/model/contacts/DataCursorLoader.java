package org.learn.pim.model.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import org.learn.pim.util.Constants;

public class DataCursorLoader extends CursorLoader {

    public static final String[] PROJECTION = {
            ContactsContract.Data._ID,
            ContactsContract.Data.RAW_CONTACT_ID,
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.NAME_RAW_CONTACT_ID,
            RawContacts.RAW_CONTACT_IS_USER_PROFILE,
            ContactsContract.Data.DATA1,
            ContactsContract.Data.DATA2,
            ContactsContract.Data.DATA3,
            ContactsContract.Data.DATA4,
            ContactsContract.Data.DATA5,
            ContactsContract.Data.DATA6,
            ContactsContract.Data.DATA7,
            ContactsContract.Data.DATA8,
            ContactsContract.Data.DATA9,
            ContactsContract.Data.DATA10,
            ContactsContract.Data.DATA11,
            ContactsContract.Data.DATA12,
            ContactsContract.Data.DATA13,
            ContactsContract.Data.DATA14,
            ContactsContract.Data.DATA15,
            ContactsContract.Data.CARRIER_PRESENCE,
            ContactsContract.Data.PREFERRED_PHONE_ACCOUNT_COMPONENT_NAME,
            ContactsContract.Data.PREFERRED_PHONE_ACCOUNT_ID,
            ContactsContract.Data.DATA_VERSION,
            ContactsContract.Data.IS_PRIMARY,
            ContactsContract.Data.IS_SUPER_PRIMARY,
            ContactsContract.Data.MIMETYPE,
            ContactsContract.Data.RES_PACKAGE,
            ContactsContract.Data.SYNC1,
            ContactsContract.Data.SYNC2,
            ContactsContract.Data.SYNC3,
            ContactsContract.Data.SYNC4,
            ContactsContract.CommonDataKinds.GroupMembership.GROUP_SOURCE_ID,
            RawContacts.ACCOUNT_NAME,
            RawContacts.ACCOUNT_TYPE,
            RawContacts.DATA_SET,
            RawContacts.ACCOUNT_TYPE_AND_DATA_SET,
            RawContacts.DIRTY,
            RawContacts.SOURCE_ID,
            RawContacts.BACKUP_ID,
            RawContacts.VERSION,
            ContactsContract.Contacts.CUSTOM_RINGTONE,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE,
            ContactsContract.Contacts.DISPLAY_NAME_SOURCE,
            ContactsContract.Contacts.IN_DEFAULT_DIRECTORY,
            ContactsContract.Contacts.IN_VISIBLE_GROUP,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.PHONETIC_NAME,
            ContactsContract.Contacts.PHONETIC_NAME_STYLE,
            ContactsContract.Contacts.PHOTO_ID,
            ContactsContract.Contacts.PHOTO_FILE_ID,
            ContactsContract.Contacts.PHOTO_URI,
            ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
            ContactsContract.Contacts.SEND_TO_VOICEMAIL,
            ContactsContract.Contacts.SORT_KEY_ALTERNATIVE,
            ContactsContract.Contacts.SORT_KEY_PRIMARY,
            /*ContactsColumns.PHONEBOOK_LABEL_PRIMARY*/"phonebook_label",
            /*ContactsColumns.PHONEBOOK_LABEL_ALTERNATIVE*/"phonebook_label_alt",
            /*ContactsColumns.PHONEBOOK_BUCKET_PRIMARY*/"phonebook_bucket",
            /*ContactsColumns.PHONEBOOK_BUCKET_ALTERNATIVE*/"phonebook_bucket_alt",
            ContactsContract.Contacts.STARRED,
            ContactsContract.Contacts.PINNED,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP,
            /*ContactsColumns.CUSTOM_VIDEO_RINGTONE*/ // "custom_video_ringtone",
    };

    public static final int _ID = 0;
    public static final int RAW_CONTACT_ID = 1;
    public static final int CONTACT_ID = 2;
    public static final int NAME_RAW_CONTACT_ID = 3;
    public static final int RAW_CONTACT_IS_USER_PROFILE = 4;
    public static final int DATA1 = 5;
    public static final int DATA2 = 6;
    public static final int DATA3 = 7;
    public static final int DATA4 = 8;
    public static final int DATA5 = 9;
    public static final int DATA6 = 10;
    public static final int DATA7 = 11;
    public static final int DATA8 = 12;
    public static final int DATA9 = 13;
    public static final int DATA10 = 14;
    public static final int DATA11 = 15;
    public static final int DATA12 = 16;
    public static final int DATA13 = 17;
    public static final int DATA14 = 18;
    public static final int DATA15 = 19;
    public static final int CARRIER_PRESENCE = 20;
    public static final int PREFERRED_PHONE_ACCOUNT_COMPONENT_NAME = 21;
    public static final int PREFERRED_PHONE_ACCOUNT_ID = 22;
    public static final int DATA_VERSION = 23;
    public static final int IS_PRIMARY = 24;
    public static final int IS_SUPER_PRIMARY = 25;
    public static final int MIMETYPE = 26;
    public static final int RES_PACKAGE = 27;
    public static final int SYNC1 = 28;
    public static final int SYNC2 = 29;
    public static final int SYNC3 = 30;
    public static final int SYNC4 = 31;
    public static final int GROUP_SOURCE_ID = 32;
    public static final int ACCOUNT_NAME = 33;
    public static final int ACCOUNT_TYPE = 34;
    public static final int DATA_SET = 35;
    public static final int ACCOUNT_TYPE_AND_DATA_SET = 36;
    public static final int DIRTY = 37;
    public static final int SOURCE_ID = 38;
    public static final int BACKUP_ID = 39;
    public static final int VERSION = 40;
    public static final int CUSTOM_RINGTONE = 41;
    public static final int DISPLAY_NAME = 42;
    public static final int DISPLAY_NAME_ALTERNATIVE = 43;
    public static final int DISPLAY_NAME_SOURCE = 44;
    public static final int IN_DEFAULT_DIRECTORY = 45;
    public static final int IN_VISIBLE_GROUP = 46;
    public static final int LOOKUP_KEY = 47;
    public static final int PHONETIC_NAME = 48;
    public static final int PHONETIC_NAME_STYLE = 49;
    public static final int PHOTO_ID = 50;
    public static final int PHOTO_FILE_ID = 51;
    public static final int PHOTO_URI = 52;
    public static final int PHOTO_THUMBNAIL_URI = 53;
    public static final int SEND_TO_VOICEMAIL = 54;
    public static final int SORT_KEY_ALTERNATIVE = 55;
    public static final int SORT_KEY_PRIMARY = 56;
    public static final int PHONEBOOK_LABEL = 57;
    public static final int PHONEBOOK_LABEL_ALT = 58;
    public static final int PHONEBOOK_BUCKET = 59;
    public static final int PHONEBOOK_BUCKET_ALT = 60;
    public static final int STARRED = 61;
    public static final int PINNED = 62;
    public static final int HAS_PHONE_NUMBER = 63;
    public static final int CONTACT_LAST_UPDATED_TIMESTAMP = 64;
//    public static final int CUSTOM_VIDEO_RINGTONE= 65;

    public DataCursorLoader(@NonNull Context context, String queryString, int loaderId) {
        this(context, buildUri(queryString, loaderId));
    }

    public DataCursorLoader(@NonNull Context context, Uri uri) {
        super(context, uri, PROJECTION, null, null, null);
    }

    private static Uri buildUri(String queryString, int loaderId) {
        Uri.Builder baseUri = ContactsConstants.getUri(loaderId, -1, null, queryString).buildUpon();
        Log.i(Constants.LOG_IMPORTANT, "buildUri - baseUri:" + baseUri);
        return baseUri.build();
    }
}
