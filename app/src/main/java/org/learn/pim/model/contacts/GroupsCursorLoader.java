package org.learn.pim.model.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract.Groups;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import org.learn.pim.util.Constants;

import java.util.Arrays;

public class GroupsCursorLoader extends CursorLoader {
    private static final String TAG = "GroupsCursorLoader";
    public static final String[] PROJECTION = {
            Groups._ID,
            Groups.ACCOUNT_NAME,
            Groups.ACCOUNT_TYPE,
            Groups.DATA_SET,
            /* Groups.ACCOUNT_TYPE_AND_DATA_SET */ "account_type_and_data_set",
            Groups.SOURCE_ID,
            Groups.DIRTY,
            Groups.VERSION,
            Groups.RES_PACKAGE,
            Groups.TITLE,
            Groups.TITLE_RES,
            Groups.GROUP_VISIBLE,
            Groups.SYSTEM_ID,
            Groups.DELETED,
            Groups.NOTES,
            Groups.SHOULD_SYNC,
            Groups.FAVORITES,
            Groups.AUTO_ADD,
            Groups.GROUP_IS_READ_ONLY,
            Groups.SYNC1,
            Groups.SYNC2,
            Groups.SYNC3,
            Groups.SYNC4,
    };

    public static final String[] PROJECTION_SUMMARY;

    public static final int _ID = 0;
    public static final int ACCOUNT_NAME = 1;
    public static final int ACCOUNT_TYPE = 2;
    public static final int DATA_SET = 3;
    public static final int ACCOUNT_TYPE_AND_DATA_SET = 4;
    public static final int SOURCE_ID = 5;
    public static final int DIRTY = 6;
    public static final int VERSION = 7;
    public static final int RES_PACKAGE = 8;
    public static final int TITLE = 9;
    public static final int TITLE_RES = 10;
    public static final int GROUP_VISIBLE = 11;
    public static final int SYSTEM_ID = 12;
    public static final int DELETED = 13;
    public static final int NOTES = 14;
    public static final int SHOULD_SYNC = 15;
    public static final int FAVORITES = 16;
    public static final int AUTO_ADD = 17;
    public static final int GROUP_IS_READ_ONLY = 18;
    public static final int SYNC1 = 19;
    public static final int SYNC2 = 20;
    public static final int SYNC3 = 21;
    public static final int SYNC4 = 22;
    public static final int SUMMARY_COUNT = 23;
    public static final int SUMMARY_WITH_PHONES = 24;

    static {
        PROJECTION_SUMMARY = Arrays.copyOf(PROJECTION, PROJECTION.length + 2);
        PROJECTION_SUMMARY[SUMMARY_COUNT] = Groups.SUMMARY_COUNT;
        PROJECTION_SUMMARY[SUMMARY_WITH_PHONES] = Groups.SUMMARY_WITH_PHONES;
    }

    public GroupsCursorLoader(@NonNull Context context, String queryString, int loaderId) {
        super(context, buildUri(queryString, loaderId), getProjection(loaderId), null, null, null);
    }

    private static Uri buildUri(String queryString, int loaderId) {
        Uri.Builder baseUri = ContactsConstants.getUri(loaderId, -1, null, queryString).buildUpon();
        Log.i(Constants.LOG_IMPORTANT, "buildUri - baseUri:" + baseUri);
        return baseUri.build();
    }

    private static String[] getProjection(int loaderId) {
        if (ContactsConstants.Actions.GROUPS_SUMMARY.id == loaderId) {
            return PROJECTION_SUMMARY;
        }
        return PROJECTION;

    }
}
