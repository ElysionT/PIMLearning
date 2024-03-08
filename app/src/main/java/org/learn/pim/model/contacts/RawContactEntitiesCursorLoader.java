package org.learn.pim.model.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;

import org.learn.pim.util.Constants;

public class RawContactEntitiesCursorLoader extends CursorLoader {

    public static final String[] PROJECTION = {
            RawContacts._ID,
            RawContacts.CONTACT_ID,
            RawContacts.Entity.DATA_ID,
            RawContacts.DELETED,
            RawContacts.STARRED,
            RawContacts.RAW_CONTACT_IS_USER_PROFILE,
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
    };

    public static final int _ID = 0;
    public static final int CONTACT_ID = 1;
    public static final int DATA_ID = 2;
    public static final int DELETED = 3;
    public static final int STARRED = 4;
    public static final int RAW_CONTACT_IS_USER_PROFILE = 5;
    public static final int ACCOUNT_NAME = 6;
    public static final int ACCOUNT_TYPE = 7;
    public static final int DATA_SET = 8;
    public static final int ACCOUNT_TYPE_AND_DATA_SET = 9;
    public static final int DIRTY = 10;
    public static final int SOURCE_ID = 11;
    public static final int BACKUP_ID = 12;
    public static final int VERSION = 13;
    public static final int SYNC1 = 14;
    public static final int SYNC2 = 15;
    public static final int SYNC3 = 16;
    public static final int SYNC4 = 17;
    public static final int DATA1 = 18;
    public static final int DATA2 = 19;
    public static final int DATA3 = 20;
    public static final int DATA4 = 21;
    public static final int DATA5 = 22;
    public static final int DATA6 = 23;
    public static final int DATA7 = 24;
    public static final int DATA8 = 25;
    public static final int DATA9 = 26;
    public static final int DATA10 = 27;
    public static final int DATA11 = 28;
    public static final int DATA12 = 29;
    public static final int DATA13 = 30;
    public static final int DATA14 = 31;
    public static final int DATA15 = 32;
    public static final int CARRIER_PRESENCE = 33;
    public static final int PREFERRED_PHONE_ACCOUNT_COMPONENT_NAME = 34;
    public static final int PREFERRED_PHONE_ACCOUNT_ID = 35;
    public static final int DATA_VERSION = 36;
    public static final int IS_PRIMARY = 37;
    public static final int IS_SUPER_PRIMARY = 38;
    public static final int MIMETYPE = 39;
    public static final int RES_PACKAGE = 40;
    public static final int DATA_SYNC1 = 41;
    public static final int DATA_SYNC2 = 42;
    public static final int DATA_SYNC3 = 43;
    public static final int DATA_SYNC4 = 44;
    public static final int GROUP_SOURCE_ID = 45;

    public RawContactEntitiesCursorLoader(@NonNull Context context, String queryString, int loaderId, @Nullable String selection, @Nullable String[] selectionArgs) {
        this(context, buildUri(queryString, loaderId), selection, selectionArgs);
    }

    public RawContactEntitiesCursorLoader(@NonNull Context context, Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        super(context, uri, PROJECTION, selection, selectionArgs, null);
    }

    private static Uri buildUri(String queryString, int loaderId) {
        Uri.Builder baseUri = ContactsConstants.getUri(loaderId, -1, null, queryString).buildUpon();
        Log.i(Constants.LOG_IMPORTANT, "buildUri - baseUri:" + baseUri);
        return baseUri.build();
    }
}
