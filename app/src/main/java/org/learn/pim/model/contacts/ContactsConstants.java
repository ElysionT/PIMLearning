package org.learn.pim.model.contacts;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ContactsConstants {
//    public static final String ACTION_FILTER_SUFFIX = "_FILTER";

    // ContactsContract.Contacts.StreamItems.CONTENT_DIRECTORY
    // ContactsContract.RawContacts.StreamItems.CONTENT_DIRECTORY
    public static final String STREAM_ITEMS_CONTENT_DIRECTORY = "stream_items";

    public static final Uri CORP_CONTENT_URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "contacts_corp");

    public static enum Actions {
        // Contacts
        CONTACTS(ContactsConstants.CONTACTS, new LinkedHashMap<String, Integer>() {{
            put("CONTACTS_ID", CONTACTS_ID);
            put("CONTACTS_ID_DATA", CONTACTS_ID_DATA);
            put("CONTACTS_ID_ENTITIES", CONTACTS_ID_ENTITIES);
            put("AGGREGATION_SUGGESTIONS", AGGREGATION_SUGGESTIONS);
            put("CONTACTS_ID_PHOTO", CONTACTS_ID_PHOTO);
            put("CONTACTS_ID_DISPLAY_PHOTO", CONTACTS_ID_DISPLAY_PHOTO);

            // Special URIs that refer to contact pictures in the corp CP2.
            put("CONTACTS_ID_PHOTO_CORP", CONTACTS_ID_PHOTO_CORP);
            put("CONTACTS_ID_DISPLAY_PHOTO_CORP", CONTACTS_ID_DISPLAY_PHOTO_CORP);

            // deprecated
            put("CONTACTS_ID_STREAM_ITEMS", CONTACTS_ID_STREAM_ITEMS);

            put("CONTACTS_LOOKUP", CONTACTS_LOOKUP);
            put("CONTACTS_LOOKUP_DATA", CONTACTS_LOOKUP_DATA);
            put("CONTACTS_LOOKUP_PHOTO", CONTACTS_LOOKUP_PHOTO);
            put("CONTACTS_LOOKUP_ID", CONTACTS_LOOKUP_ID);
            put("CONTACTS_LOOKUP_ID_DATA", CONTACTS_LOOKUP_ID_DATA);
            put("CONTACTS_LOOKUP_ID_PHOTO", CONTACTS_LOOKUP_ID_PHOTO);
            put("CONTACTS_LOOKUP_DISPLAY_PHOTO", CONTACTS_LOOKUP_DISPLAY_PHOTO);
            put("CONTACTS_LOOKUP_ID_DISPLAY_PHOTO", CONTACTS_LOOKUP_ID_DISPLAY_PHOTO);
            put("CONTACTS_LOOKUP_ENTITIES", CONTACTS_LOOKUP_ENTITIES);
            put("CONTACTS_LOOKUP_ID_ENTITIES", CONTACTS_LOOKUP_ID_ENTITIES);
            put("CONTACTS_LOOKUP_STREAM_ITEMS", CONTACTS_LOOKUP_STREAM_ITEMS);
            put("CONTACTS_LOOKUP_ID_STREAM_ITEMS", CONTACTS_LOOKUP_ID_STREAM_ITEMS);
            put("CONTACTS_AS_VCARD", CONTACTS_AS_VCARD);
            put("CONTACTS_AS_MULTI_VCARD", CONTACTS_AS_MULTI_VCARD);


        }}),
        CONTACTS_FILTER(ContactsConstants.CONTACTS_FILTER, CONTACTS.subAction),
        CONTACTS_FREQUENT(ContactsConstants.CONTACTS_FREQUENT, CONTACTS.subAction),
        // Favorite
        CONTACTS_STREQUENT(ContactsConstants.CONTACTS_STREQUENT, CONTACTS.subAction),
        CONTACTS_STREQUENT_FILTER(ContactsConstants.CONTACTS_STREQUENT_FILTER, CONTACTS.subAction),
        CONTACTS_GROUP(ContactsConstants.CONTACTS_GROUP, CONTACTS.subAction),
        CONTACTS_FILTER_ENTERPRISE(ContactsConstants.CONTACTS_FILTER_ENTERPRISE, CONTACTS.subAction),
        // RawContacts
        RAW_CONTACTS(ContactsConstants.RAW_CONTACTS, new LinkedHashMap<String, Integer>() {{
            put("RAW_CONTACTS_ID", RAW_CONTACTS_ID);
            put("RAW_CONTACTS_ID_DATA", RAW_CONTACTS_ID_DATA);
            put("RAW_CONTACTS_ID_DISPLAY_PHOTO", RAW_CONTACTS_ID_DISPLAY_PHOTO);
            put("RAW_CONTACT_ID_ENTITY", RAW_CONTACT_ID_ENTITY);
//            put("RAW_CONTACTS_ID_STREAM_ITEMS", RAW_CONTACTS_ID_STREAM_ITEMS);
        }}),
        RAW_CONTACT_ENTITIES(ContactsConstants.RAW_CONTACT_ENTITIES, RAW_CONTACTS.subAction),
        RAW_CONTACT_ENTITIES_CORP(ContactsConstants.RAW_CONTACT_ENTITIES_CORP, RAW_CONTACTS.subAction),
        // Data
        DATA(ContactsConstants.DATA, new LinkedHashMap<String, Integer>() {{
            put("DATA_ID", DATA_ID);
        }}),
        PHONES(ContactsConstants.PHONES, new LinkedHashMap<String, Integer>() {{
            put("PHONES_ID", PHONES_ID);
        }}),
        // Groups
        GROUPS(ContactsConstants.GROUPS, new HashMap<String, Integer>() {{
            put("GROUPS_ID", GROUPS_ID);
        }}),
        GROUPS_SUMMARY(ContactsConstants.GROUPS_SUMMARY, GROUPS.subAction),
        ;

        public static Map<String, Integer> getSubActionsById(int actionId) {
            switch (actionId) {
                case AGGREGATION_SUGGESTIONS:
                    return CONTACTS.subAction;
                case RAW_CONTACTS_ID_STREAM_ITEMS:
                    return new LinkedHashMap<String, Integer>() {{
                        put("RAW_CONTACTS_ID_STREAM_ITEMS_ID", RAW_CONTACTS_ID_STREAM_ITEMS_ID);
                    }};

                default:
                    throw new IllegalArgumentException("Not found sub-action:" + actionId);
            }
        }

        private Actions(int id, Map<String, Integer> subAction) {
            this.id = id;
            this.subAction = subAction;
        }

        public int id;
        public Map<String, Integer> subAction;
    }

    public static final int CONTACTS = 1000;
    public static final int CONTACTS_ID = 1001;
    public static final int CONTACTS_LOOKUP = 1002;
    public static final int CONTACTS_LOOKUP_ID = 1003;
    public static final int CONTACTS_ID_DATA = 1004;
    public static final int CONTACTS_FILTER = 1005;
    public static final int CONTACTS_STREQUENT = 1006;
    public static final int CONTACTS_STREQUENT_FILTER = 1007;
    public static final int CONTACTS_GROUP = 1008;
    public static final int CONTACTS_ID_PHOTO = 1009;
    public static final int CONTACTS_LOOKUP_PHOTO = 1010;
    public static final int CONTACTS_LOOKUP_ID_PHOTO = 1011;
    public static final int CONTACTS_ID_DISPLAY_PHOTO = 1012;
    public static final int CONTACTS_LOOKUP_DISPLAY_PHOTO = 1013;
    public static final int CONTACTS_LOOKUP_ID_DISPLAY_PHOTO = 1014;
    public static final int CONTACTS_AS_VCARD = 1015;
    public static final int CONTACTS_AS_MULTI_VCARD = 1016;
    public static final int CONTACTS_LOOKUP_DATA = 1017;
    public static final int CONTACTS_LOOKUP_ID_DATA = 1018;
    public static final int CONTACTS_ID_ENTITIES = 1019;
    public static final int CONTACTS_LOOKUP_ENTITIES = 1020;
    public static final int CONTACTS_LOOKUP_ID_ENTITIES = 1021;
    public static final int CONTACTS_ID_STREAM_ITEMS = 1022;
    public static final int CONTACTS_LOOKUP_STREAM_ITEMS = 1023;
    public static final int CONTACTS_LOOKUP_ID_STREAM_ITEMS = 1024;
    public static final int CONTACTS_FREQUENT = 1025;
    public static final int CONTACTS_DELETE_USAGE = 1026; // Delete operate
    public static final int CONTACTS_ID_PHOTO_CORP = 1027;
    public static final int CONTACTS_ID_DISPLAY_PHOTO_CORP = 1028;
    public static final int CONTACTS_FILTER_ENTERPRISE = 1029;

    public static final int RAW_CONTACTS = 2002;
    public static final int RAW_CONTACTS_ID = 2003;
    public static final int RAW_CONTACTS_ID_DATA = 2004;
    public static final int RAW_CONTACT_ID_ENTITY = 2005;
    public static final int RAW_CONTACTS_ID_DISPLAY_PHOTO = 2006;
    public static final int RAW_CONTACTS_ID_STREAM_ITEMS = 2007;
    public static final int RAW_CONTACTS_ID_STREAM_ITEMS_ID = 2008;

    public static final int DATA = 3000;
    public static final int DATA_ID = 3001;
    public static final int PHONES = 3002;
    public static final int PHONES_ID = 3003;
    public static final int PHONES_FILTER = 3004;
    public static final int EMAILS = 3005;
    public static final int EMAILS_ID = 3006;
    public static final int EMAILS_LOOKUP = 3007;
    public static final int EMAILS_FILTER = 3008;
    public static final int POSTALS = 3009;
    public static final int POSTALS_ID = 3010;
    public static final int CALLABLES = 3011;
    public static final int CALLABLES_ID = 3012;
    public static final int CALLABLES_FILTER = 3013;
    public static final int CONTACTABLES = 3014;
    public static final int CONTACTABLES_FILTER = 3015;
    public static final int PHONES_ENTERPRISE = 3016;
    public static final int EMAILS_LOOKUP_ENTERPRISE = 3017;
    public static final int PHONES_FILTER_ENTERPRISE = 3018;
    public static final int CALLABLES_FILTER_ENTERPRISE = 3019;
    public static final int EMAILS_FILTER_ENTERPRISE = 3020;

    public static final int PHONE_LOOKUP = 4000;
    public static final int PHONE_LOOKUP_ENTERPRISE = 4001;

    public static final int AGGREGATION_EXCEPTIONS = 6000;
    public static final int AGGREGATION_EXCEPTION_ID = 6001;

    public static final int STATUS_UPDATES = 7000;
    public static final int STATUS_UPDATES_ID = 7001;

    public static final int AGGREGATION_SUGGESTIONS = 8000;

    public static final int SETTINGS = 9000;

    public static final int GROUPS = 10000;
    public static final int GROUPS_ID = 10001;
    public static final int GROUPS_SUMMARY = 10003;

    public static final int SYNCSTATE = 11000;
    public static final int SYNCSTATE_ID = 11001;
    public static final int PROFILE_SYNCSTATE = 11002;
    public static final int PROFILE_SYNCSTATE_ID = 11003;

    public static final int SEARCH_SUGGESTIONS = 12001;
    public static final int SEARCH_SHORTCUT = 12002;

    public static final int RAW_CONTACT_ENTITIES = 15001;
    public static final int RAW_CONTACT_ENTITIES_CORP = 15002;

    public static final int PROVIDER_STATUS = 16001;

    public static final int DIRECTORIES = 17001;
    public static final int DIRECTORIES_ID = 17002;
    public static final int DIRECTORIES_ENTERPRISE = 17003;
    public static final int DIRECTORIES_ID_ENTERPRISE = 17004;

    public static final int COMPLETE_NAME = 18000;

    public static final int PROFILE = 19000;
    public static final int PROFILE_ENTITIES = 19001;
    public static final int PROFILE_DATA = 19002;
    public static final int PROFILE_DATA_ID = 19003;
    public static final int PROFILE_AS_VCARD = 19004;
    public static final int PROFILE_RAW_CONTACTS = 19005;
    public static final int PROFILE_RAW_CONTACTS_ID = 19006;
    public static final int PROFILE_RAW_CONTACTS_ID_DATA = 19007;
    public static final int PROFILE_RAW_CONTACTS_ID_ENTITIES = 19008;
    public static final int PROFILE_STATUS_UPDATES = 19009;
    public static final int PROFILE_RAW_CONTACT_ENTITIES = 19010;
    public static final int PROFILE_PHOTO = 19011;
    public static final int PROFILE_DISPLAY_PHOTO = 19012;

    public static final int DATA_USAGE_FEEDBACK_ID = 20001;

    public static final int STREAM_ITEMS = 21000;
    public static final int STREAM_ITEMS_PHOTOS = 21001;
    public static final int STREAM_ITEMS_ID = 21002;
    public static final int STREAM_ITEMS_ID_PHOTOS = 21003;
    public static final int STREAM_ITEMS_ID_PHOTOS_ID = 21004;
    public static final int STREAM_ITEMS_LIMIT = 21005;

    public static final int DISPLAY_PHOTO_ID = 22000;
    public static final int PHOTO_DIMENSIONS = 22001;

    public static final int DELETED_CONTACTS = 23000;
    public static final int DELETED_CONTACTS_ID = 23001;

    public static final int DIRECTORY_FILE_ENTERPRISE = 24000;


    public static final String LOOKUP_URI_ENCODED = "encoded";

    public static boolean isSupportMultiple(int action) {
        switch (action) {
            case CONTACTS_AS_MULTI_VCARD:
                return true;
            default:
                return false;
        }
    }

    public static boolean isDirAction(int action) {
        switch (action) {
            case RAW_CONTACTS_ID_STREAM_ITEMS:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSupportFilter(int action) {
        switch (action) {
            case CONTACTS_FILTER:
            case CONTACTS_STREQUENT_FILTER:
            case CONTACTS_GROUP:
            case CONTACTS_FILTER_ENTERPRISE:
                return true;
            default:
                return false;
        }
    }

    /*
        @patchSegments [0](Long)   # contact id | raw contact id
                       [1](String) * lookup_key
                       [2](String) * filter
                       [3](Boolean)  option

     */
    public static Uri getUri(int action, Object... patchSegments) {
        switch (action) {
            case CONTACTS:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type
                 * FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                 * WHERE ((1)) with args null
                 */
                // content://com.android.contacts/contacts
                return ContactsContract.Contacts.CONTENT_URI;
            case CONTACTS_FILTER:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type
                 * FROM view_contacts
                 * JOIN (SELECT contact_id AS snippet_contact_id FROM search_index WHERE search_index MATCH 'content:t* OR name:CE* OR tokens:t*' AND snippet_contact_id IN default_directory) ON (_id=snippet_contact_id)
                 * LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) with args null
                 */
                // content://com.android.contacts/contacts/filter/t
                return ContactsContract.Contacts.CONTENT_FILTER_URI.buildUpon().appendEncodedPath(Uri.encode((String) patchSegments[2])).build();
            case CONTACTS_STREQUENT:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type, 9223372036854775807 AS times_used, 9223372036854775807 AS last_time_used
                 * FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                 * WHERE ((starred=1))
                 * GROUP BY _id ORDER BY display_name COLLATE LOCALIZED ASC
                 */
                // content://com.android.contacts/contacts/strequent
                return ContactsContract.Contacts.CONTENT_STREQUENT_URI;
            case CONTACTS_STREQUENT_FILTER:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type, 9223372036854775807 AS times_used, 9223372036854775807 AS last_time_used
                 * FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) WHERE (((_id IN
                 * (SELECT DISTINCT contact_id FROM raw_contacts JOIN name_lookup ON(raw_contacts._id=raw_contact_id)WHERE normalized_name GLOB 'CE*' AND name_type IN(2,4,3))
                 * )) AND (starred=1))
                 * GROUP BY _id ORDER BY display_name COLLATE LOCALIZED ASC
                 */
                // content://com.android.contacts/contacts/strequent/filter/t
                return ContactsContract.Contacts.CONTENT_STREQUENT_FILTER_URI.buildUpon().appendEncodedPath(Uri.encode((String) patchSegments[2])).build();
            case CONTACTS_GROUP:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type
                 * FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                 * WHERE (_id IN (SELECT contact_id FROM raw_contacts WHERE raw_contacts._id IN
                 * (SELECT data.raw_contact_id FROM data JOIN mimetypes ON (data.mimetype_id = mimetypes._id) WHERE mimetype_id=? AND data1=(SELECT groups._id FROM groups WHERE title=?))))
                 * with args [11, Group1]
                 */
                // content://com.android.contacts/contacts/group/Group1
                return ContactsContract.Contacts.CONTENT_GROUP_URI.buildUpon().appendEncodedPath(Uri.encode((String) patchSegments[2])).build();
            case CONTACTS_FREQUENT:
                /* @deprecated Frequent contacts are no longer supported as of
                 * Android version {@link android.os.Build.VERSION_CODES#Q}.
                 * <b>This URI always returns an empty cursor.</b>
                 */
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type
                 * FROM data_usage_stat INNER JOIN view_contacts ON (((0)) AND (contact_id=view_contacts._id)) LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                 * WHERE (((0))) GROUP BY _id with args null
                 */
                // content://com.android.contacts/contacts/frequent
                return ContactsContract.Contacts.CONTENT_FREQUENT_URI;
            case CONTACTS_FILTER_ENTERPRISE:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type
                 * FROM view_contacts
                 * JOIN (SELECT NULL AS snippet WHERE 0)
                 * LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) with args null
                 */
                // content://com.android.contacts/contacts/filter_enterprise?directory=0
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, phonetic_name_style, photo_id, photo_uri, photo_thumb_uri, photo_file_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contacts_status_updates.status_icon AS contact_status_icon, _id, send_to_voicemail, custom_ringtone, is_user_profile, in_visible_group, has_phone_number, in_default_directory, sort_key, sort_key_alt, pinned, agg_presence.chat_capability AS contact_chat_capability, contact_last_updated_timestamp, 0 AS last_time_contacted, 0 AS times_contacted, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, account_name, account_type
                 * FROM view_contacts
                 * JOIN (SELECT contact_id AS snippet_contact_id FROM search_index
                 * WHERE search_index MATCH 'content:t* OR name:CE* OR tokens:t*' AND snippet_contact_id IN default_directory) ON (_id=snippet_contact_id) LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) with args null
                 */
                // content://com.android.contacts/contacts/filter_enterprise/t?directory=0
                return ContactsContract.Contacts.ENTERPRISE_CONTENT_FILTER_URI.buildUpon().appendEncodedPath(Uri.encode((String) patchSegments[2])).appendQueryParameter(ContactsContract.DIRECTORY_PARAM_KEY, String.valueOf(ContactsContract.Directory.DEFAULT)).build();

            // Sub action for above ACTIONS -- BEGIN --
            // adb shell am start -a "pim.intent.action.DETAIL" -t "vnd.android.cursor.item/contact" -d
            case CONTACTS_ID:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, photo_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contact_id, raw_contact_id, account_name, account_type, data_set, dirty, version, sourceid, sync1, sync2, sync3, sync4, deleted, data_id, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, data11, data12, data13, data14, data15, data_sync1, data_sync2, data_sync3, data_sync4, data_version, is_primary, is_super_primary, mimetype, group_sourceid, presence.mode AS mode, presence.chat_capability AS chat_capability, status_updates.status AS status, status_updates.status_res_package AS status_res_package, status_updates.status_icon AS status_icon, status_updates.status_label AS status_label, status_updates.status_ts AS status_ts, photo_uri, send_to_voicemail, custom_ringtone, is_user_profile, carrier_presence
                 * FROM view_entities data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data_id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data_id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data_id)
                 * WHERE (1 AND contact_id=?)
                 * ORDER BY raw_contact_id with args [1]
                 */
                // "content://com.android.contacts/contacts/1"
                return ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]);
            case CONTACTS_ID_DATA:
                /**
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND contact_id=?) with args [1]
                 */
                /*
                 *  phonetic_name:null
                 *  status_res_package:null
                 *  custom_ringtone:null
                 *  contact_status_ts:null
                 *  account_type:Local Phone Account
                 *  data_version:0
                 *  photo_file_id:null
                 *  contact_status_res_package:null
                 *  group_sourceid:null
                 *  display_name_alt:6194645块
                 *  sort_key_alt:6194645块
                 *  mode:null
                 *  last_time_used:0
                 *  starred:0
                 *  contact_status_label:null
                 *  has_phone_number:0
                 *  chat_capability:null
                 *  raw_contact_id:1
                 *  carrier_presence:0
                 *  contact_last_updated_timestamp:-2021948957
                 *  res_package:null
                 *  photo_uri:null
                 *  data_sync4:null
                 *  phonebook_bucket:213
                 *  times_used:0
                 *  display_name:6194645块
                 *  sort_key:6194645块
                 *  data_sync1:null
                 *  version:2
                 *  data_sync2:null
                 *  data_sync3:null
                 *  photo_thumb_uri:null
                 *  status_label:null
                 *  contact_presence:null
                 *  in_default_directory:1
                 *  times_contacted:0
                 *  _id:1
                 *  account_type_and_data_set:Local Phone Account
                 *  name_raw_contact_id:1
                 *  status:null
                 *  phonebook_bucket_alt:213
                 *  last_time_contacted:0
                 *  pinned:0
                 *  is_primary:0
                 *  photo_id:null
                 *  contact_id:1
                 *  contact_chat_capability:null
                 *  contact_status_icon:null
                 *  in_visible_group:0
                 *  phonebook_label:#
                 *  account_name:Phone
                 *  display_name_source:40
                 *  data9:null
                 *  dirty:1
                 *  sourceid:null
                 *  phonetic_name_style:0
                 *  send_to_voicemail:0
                 *  data8:null
                 *  lookup:3176r1-1F15251B1F1B1D708C
                 *  data7:null
                 *  data6:null
                 *  phonebook_label_alt:#
                 *  data5:null
                 *  is_super_primary:0
                 *  data4:null
                 *  data3:null
                 *  data2:6194645块
                 *  data1:6194645块
                 *  data_set:null
                 *  contact_status:null
                 *  backup_id:null
                 *  preferred_phone_account_component_name:null
                 *  raw_contact_is_user_profile:0
                 *  status_ts:null
                 *  data10:3
                 *  preferred_phone_account_id:null
                 *  data12:null
                 *  mimetype:vnd.android.cursor.item/name
                 *  status_icon:null
                 *  data11:0
                 *  data14:null
                 *  data13:null
                 *  hash_id:graYqAX9e6zReBQwHp22TzcEv+c=
                 *  data15:null
                 */
                // "content://com.android.contacts/contacts/1/data"
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.Data.CONTENT_DIRECTORY);
            case CONTACTS_ID_ENTITIES:
                /**
                 * SELECT phonetic_name, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, status_updates.status_res_package AS status_res_package, account_type, photo_file_id, data_version, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, is_user_profile, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, contact_last_updated_timestamp, carrier_presence, res_package, photo_uri, phonebook_bucket, data_sync4, 0 AS times_used, display_name, sort_key, version, data_sync1, data_sync2, photo_thumb_uri, data_sync3, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, phonebook_bucket_alt, status_updates.status AS status, 0 AS last_time_contacted, pinned, photo_id, is_primary, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, data_id, phonebook_label, account_name, display_name_source, phonetic_name_style, send_to_voicemail, dirty, sourceid, data9, lookup, data8, data7, phonebook_label_alt, data6, data5, is_super_primary, data4, data3, data2, data1, contacts_status_updates.status AS contact_status, data_set, backup_id, deleted, sync4, preferred_phone_account_component_name, sync3, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, sync2, data12, mimetype, status_updates.status_icon AS status_icon, sync1, data11, data14, data13, data15
                 * FROM view_entities data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data_id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data_id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data_id)
                 * WHERE (1 AND contact_id=?) with args [1]
                 */
                /**
                 * phonetic_name:null
                 * custom_ringtone:null
                 * contact_status_ts:null
                 * status_res_package:null
                 * account_type:Local Phone Account
                 * photo_file_id:null
                 * data_version:0
                 * contact_status_res_package:null
                 * group_sourceid:null
                 * display_name_alt:6194645块
                 * sort_key_alt:6194645块
                 * mode:null
                 * last_time_used:0
                 * starred:0
                 * contact_status_label:null
                 * is_user_profile:0
                 * has_phone_number:0
                 * chat_capability:null
                 * raw_contact_id:1
                 * contact_last_updated_timestamp:-2021948957
                 * carrier_presence:0
                 * res_package:null
                 * photo_uri:null
                 * phonebook_bucket:213
                 * data_sync4:null
                 * times_used:0
                 * display_name:6194645块
                 * sort_key:6194645块
                 * version:2
                 * data_sync1:null
                 * data_sync2:null
                 * photo_thumb_uri:null
                 * data_sync3:null
                 * status_label:null
                 * contact_presence:null
                 * in_default_directory:1
                 * times_contacted:0
                 * _id:1
                 * account_type_and_data_set:Local Phone Account
                 * name_raw_contact_id:1
                 * phonebook_bucket_alt:213
                 * status:null
                 * last_time_contacted:0
                 * pinned:0
                 * photo_id:null
                 * is_primary:0
                 * contact_id:1
                 * contact_chat_capability:null
                 * contact_status_icon:null
                 * in_visible_group:0
                 * data_id:1
                 * phonebook_label:#
                 * account_name:Phone
                 * display_name_source:40
                 * phonetic_name_style:0
                 * send_to_voicemail:0
                 * dirty:1
                 * sourceid:null
                 * data9:null
                 * lookup:3176r1-1F15251B1F1B1D708C
                 * data8:null
                 * data7:null
                 * phonebook_label_alt:#
                 * data6:null
                 * data5:null
                 * is_super_primary:0
                 * data4:null
                 * data3:null
                 * data2:6194645块
                 * data1:6194645块
                 * contact_status:null
                 * data_set:null
                 * backup_id:null
                 * deleted:0
                 * sync4:null
                 * preferred_phone_account_component_name:null
                 * sync3:null
                 * status_ts:null
                 * data10:3
                 * preferred_phone_account_id:null
                 * sync2:null
                 * data12:null
                 * mimetype:vnd.android.cursor.item/name
                 * status_icon:null
                 * sync1:null
                 * data11:0
                 * data14:null
                 * data13:null
                 * data15:null
                 */
                // content://com.android.contacts/contacts/1/entities
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.Entity.CONTENT_DIRECTORY);
            case AGGREGATION_SUGGESTIONS:
                /**
                 * "SELECT _id, account_id FROM raw_contacts WHERE contact_id=2"
                 *
                 * "SELECT raw_contacts._id, contact_id, account_id
                 * FROM data dataA JOIN data dataB ON (dataA.data2=dataB.data2 AND dataA.data1=dataB.data1) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)
                 * WHERE dataA.raw_contact_id='2' AND dataA.mimetype_id='9' AND dataA.data2 NOT NULL AND dataA.data1 NOT NULL AND dataB.mimetype_id='9' AND contact_id IN default_directory
                 * GROUP BY contact_id"
                 *
                 * "SELECT _id, contact_id, account_id, nameA.normalized_name, nameA.name_type, nameB.name_type
                 * FROM name_lookup nameA JOIN name_lookup nameB ON (nameA.normalized_name=nameB.normalized_name) JOIN raw_contacts ON (nameB.raw_contact_id = raw_contacts._id)
                 * WHERE nameA.raw_contact_id='2' AND contact_id IN default_directory
                 * LIMIT 15"
                 *
                 * "SELECT raw_contacts._id, contact_id, account_id
                 * FROM data dataA JOIN data dataB ON dataA.data1= dataB.data1 JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)
                 * WHERE dataA.raw_contact_id='2' AND dataA.mimetype_id='1' AND dataA.data1 NOT NULL AND dataB.mimetype_id='1' AND contact_id IN default_directory
                 * LIMIT 20"
                 *
                 * "SELECT raw_contacts._id, contact_id, account_id
                 * FROM phone_lookup phoneA JOIN data dataA ON (dataA._id=phoneA.data_id) JOIN phone_lookup phoneB ON (phoneA.min_match=phoneB.min_match) JOIN data dataB ON (dataB._id=phoneB.data_id) JOIN raw_contacts ON (dataB.raw_contact_id = raw_contacts._id)
                 * WHERE dataA.raw_contact_id='2' AND PHONE_NUMBERS_EQUAL(dataA.data1, dataB.data1,'1') AND contact_id IN default_directory
                 * LIMIT 20"
                 *
                 * "SELECT normalized_name, name_type
                 * FROM name_lookup
                 * WHERE raw_contact_id='2'"
                 *
                 * "SELECT _id, contact_id, account_id, normalized_name, name_type
                 * FROM name_lookup INNER JOIN view_raw_contacts ON (name_lookup.raw_contact_id = view_raw_contacts._id)
                 * WHERE (normalized_name GLOB 'A8*') AND (name_type IN(2,4,3)) AND contact_id IN default_directory
                 * LIMIT 100"
                 *
                 * "SELECT _id, contact_id, account_id, normalized_name, name_type
                 * FROM name_lookup INNER JOIN view_raw_contacts ON (name_lookup.raw_contact_id = view_raw_contacts._id)
                 * WHERE (normalized_name GLOB 'D4*') AND (name_type IN(2,4,3)) AND contact_id IN default_directory
                 * LIMIT 100"
                 *
                 * "SELECT _id
                 * FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                 * WHERE _id IN (6)"
                 *
                 *  SELECT 0 AS last_time_contacted, phonetic_name, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, pinned, account_type, photo_id, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, display_name_alt, sort_key_alt, in_visible_group, starred, contacts_status_updates.status_label AS contact_status_label, phonebook_label, is_user_profile, account_name, has_phone_number, display_name_source, phonetic_name_style, send_to_voicemail, lookup, phonebook_label_alt, contact_last_updated_timestamp, photo_uri, phonebook_bucket, contacts_status_updates.status AS contact_status, display_name, sort_key, photo_thumb_uri, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, name_raw_contact_id, phonebook_bucket_alt
                 *  FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                 *  WHERE (_id IN (6)) ORDER BY _id with args null
                 */
                // content://com.android.contacts/contacts/1/suggestions
                Uri uri = Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.AggregationSuggestions.CONTENT_DIRECTORY);
                String filter = (null != patchSegments && patchSegments.length > 2 && !TextUtils.isEmpty((String) patchSegments[2])) ? (String) patchSegments[2] : null;
                if (null != filter) {
                    /**
                     * "SELECT _id
                     * FROM view_contacts LEFT OUTER JOIN agg_presence ON (_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id)
                     * WHERE _id IN (6)
                     * AND _id IN (SELECT DISTINCT contact_id FROM raw_contacts JOIN name_lookup ON(raw_contacts._id=raw_contact_id) WHERE normalized_name GLOB 'CE*' AND name_type IN(2,4,3))"
                     */
                    // content://com.android.contacts/contacts/2/suggestions/t
                    uri = Uri.withAppendedPath(uri, filter);
                }
                return uri;
            case CONTACTS_ID_PHOTO:
                /**
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND contact_id=? AND _id=photo_id) with args [1]
                 */
                // openAssetFileDescriptor
                /**
                 * SELECT data15 FROM view_data WHERE _id=photo_id AND contact_id='1'
                 */
                // content://com.android.contacts/contacts/1/photo
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            case CONTACTS_ID_DISPLAY_PHOTO:
                /**
                 * SELECT photo_file_id FROM contacts WHERE _id='1'
                 */
                // content://com.android.contacts/contacts/1/display_photo
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
            case CONTACTS_ID_PHOTO_CORP:
                /**
                 *
                 */
                // content://com.android.contacts/contacts_corp/1/photo
                return Uri.withAppendedPath(ContentUris.withAppendedId(CORP_CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

            case CONTACTS_ID_DISPLAY_PHOTO_CORP:
                /**
                 *
                 */
                // content://com.android.contacts/contacts_corp/1/display_photo
                return Uri.withAppendedPath(ContentUris.withAppendedId(CORP_CONTENT_URI, (Long) patchSegments[0]), ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
            case CONTACTS_ID_STREAM_ITEMS:
                /**
                 * SELECT raw_contact_id, stream_item_sync4, account_type, comments, res_package, stream_item_sync1, stream_item_sync3, stream_item_sync2, icon, data_set, label, contact_id, contact_lookup, raw_contact_source_id, account_name, _id, text, timestamp
                 * FROM view_stream_items
                 * WHERE (contact_id=?) with args [1]
                 */
                // content://com.android.contacts/contacts/1/stream_items
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, (Long) patchSegments[0]), STREAM_ITEMS_CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP:
                /**
                 * Same SQL as CONTACTS_ID
                 */
                // "content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C"
                return Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, (String) patchSegments[1]);
            case CONTACTS_LOOKUP_DATA:
                /**
                 * SELECT contact_id, account_type_and_data_set, account_name, _id FROM view_raw_contacts WHERE _id IN (1) AND contact_id NOT NULL
                 *
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND contact_id=?) with args [1]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/data
                return Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, (String) patchSegments[1]), ContactsContract.Contacts.Data.CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_PHOTO:
                /**
                 * SELECT photo_id, photo_file_id FROM view_contacts WHERE (_id='1')
                 * SELECT data15 FROM view_data WHERE _id='1179'
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/photo
                return Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, (String) patchSegments[1]), ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_ID:
                /**
                 * SELECT name_raw_contact_id, display_name_source, lookup, display_name, display_name_alt, phonetic_name, photo_id, starred, agg_presence.mode AS contact_presence, contacts_status_updates.status AS contact_status, contacts_status_updates.status_ts AS contact_status_ts, contacts_status_updates.status_res_package AS contact_status_res_package, contacts_status_updates.status_label AS contact_status_label, contact_id, raw_contact_id, account_name, account_type, data_set, dirty, version, sourceid, sync1, sync2, sync3, sync4, deleted, data_id, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, data11, data12, data13, data14, data15, data_sync1, data_sync2, data_sync3, data_sync4, data_version, is_primary, is_super_primary, mimetype, group_sourceid, presence.mode AS mode, presence.chat_capability AS chat_capability, status_updates.status AS status, status_updates.status_res_package AS status_res_package, status_updates.status_icon AS status_icon, status_updates.status_label AS status_label, status_updates.status_ts AS status_ts, photo_uri, send_to_voicemail, custom_ringtone, is_user_profile, carrier_presence
                 * FROM view_entities data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data_id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data_id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data_id)
                 * WHERE (1 AND contact_id=? AND lookup=?)
                 * ORDER BY raw_contact_id with args [1, 3176r1-1F15251B1F1B1D708C]
                 */
                // "content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/1"
                return ContactsContract.Contacts.getLookupUri((Long) patchSegments[0], (String) patchSegments[1]);
            case CONTACTS_LOOKUP_ID_DATA:
                /**
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND contact_id=? AND lookup=?) with args [1, 3176r1-1F15251B1F1B1D708C]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/1/data
                return Uri.withAppendedPath(ContactsContract.Contacts.getLookupUri((Long) patchSegments[0], (String) patchSegments[1]), ContactsContract.Contacts.Data.CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_ID_PHOTO:
                /**
                 * SELECT photo_id, photo_file_id FROM view_contacts WHERE (_id=? AND lookup=?) with args [1, 3176r1-1F15251B1F1B1D708C]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/1/photo
                return Uri.withAppendedPath(ContactsContract.Contacts.getLookupUri((Long) patchSegments[0], (String) patchSegments[1]), ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_DISPLAY_PHOTO:
                /**
                 *  SELECT photo_id, photo_file_id FROM view_contacts WHERE (_id=?) with args [1]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/display_photo
                return Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, (String) patchSegments[1]), ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
            case CONTACTS_LOOKUP_ID_DISPLAY_PHOTO:
                /**
                 * SELECT photo_id, photo_file_id FROM view_contacts WHERE (_id=? AND lookup=?) with args [1, 3176r1-1F15251B1F1B1D708C]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/1/display_photo
                return Uri.withAppendedPath(ContactsContract.Contacts.getLookupUri((Long) patchSegments[0], (String) patchSegments[1]), ContactsContract.Contacts.Photo.DISPLAY_PHOTO);
            case CONTACTS_LOOKUP_ENTITIES:
                /**
                 * SELECT phonetic_name, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, status_updates.status_res_package AS status_res_package, account_type, photo_file_id, data_version, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, is_user_profile, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, contact_last_updated_timestamp, carrier_presence, res_package, photo_uri, phonebook_bucket, data_sync4, 0 AS times_used, display_name, sort_key, version, data_sync1, data_sync2, photo_thumb_uri, data_sync3, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, phonebook_bucket_alt, status_updates.status AS status, 0 AS last_time_contacted, pinned, photo_id, is_primary, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, data_id, phonebook_label, account_name, display_name_source, phonetic_name_style, send_to_voicemail, dirty, sourceid, data9, lookup, data8, data7, phonebook_label_alt, data6, data5, is_super_primary, data4, data3, data2, data1, contacts_status_updates.status AS contact_status, data_set, backup_id, deleted, sync4, preferred_phone_account_component_name, sync3, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, sync2, data12, mimetype, status_updates.status_icon AS status_icon, sync1, data11, data14, data13, data15
                 * FROM view_entities data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data_id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data_id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data_id)
                 * WHERE (1 AND contact_id=?) with args [1]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/entities
                return Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, (String) patchSegments[1]), ContactsContract.Contacts.Entity.CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_ID_ENTITIES:
                /**
                 * SELECT phonetic_name, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, status_updates.status_res_package AS status_res_package, account_type, photo_file_id, data_version, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, is_user_profile, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, contact_last_updated_timestamp, carrier_presence, res_package, photo_uri, phonebook_bucket, data_sync4, 0 AS times_used, display_name, sort_key, version, data_sync1, data_sync2, photo_thumb_uri, data_sync3, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, phonebook_bucket_alt, status_updates.status AS status, 0 AS last_time_contacted, pinned, photo_id, is_primary, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, data_id, phonebook_label, account_name, display_name_source, phonetic_name_style, send_to_voicemail, dirty, sourceid, data9, lookup, data8, data7, phonebook_label_alt, data6, data5, is_super_primary, data4, data3, data2, data1, contacts_status_updates.status AS contact_status, data_set, backup_id, deleted, sync4, preferred_phone_account_component_name, sync3, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, sync2, data12, mimetype, status_updates.status_icon AS status_icon, sync1, data11, data14, data13, data15
                 * FROM view_entities data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data_id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data_id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data_id)
                 * WHERE (1 AND contact_id=? AND lookup=?) with args [1, 3176r1-1F15251B1F1B1D708C]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/1/entities
                return Uri.withAppendedPath(ContactsContract.Contacts.getLookupUri((Long) patchSegments[0], (String) patchSegments[1]), ContactsContract.Contacts.Entity.CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_STREAM_ITEMS:
                /**
                 * SELECT raw_contact_id, stream_item_sync4, account_type, comments, res_package, stream_item_sync1, stream_item_sync3, stream_item_sync2, icon, data_set, label, contact_id, contact_lookup, raw_contact_source_id, account_name, _id, text, timestamp
                 * FROM view_stream_items
                 * WHERE (contact_id=?) with args [1]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/stream_items
                return Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, (String) patchSegments[1]), STREAM_ITEMS_CONTENT_DIRECTORY);
            case CONTACTS_LOOKUP_ID_STREAM_ITEMS:
                /**
                 * SELECT raw_contact_id, stream_item_sync4, account_type, comments, res_package, stream_item_sync1, stream_item_sync3, stream_item_sync2, icon, data_set, label, contact_id, contact_lookup, raw_contact_source_id, account_name, _id, text, timestamp
                 * FROM view_stream_items
                 *
                 * WHERE (contact_id=? AND contact_lookup=?) with args [1, 3176r1-1F15251B1F1B1D708C]
                 *
                 * SELECT raw_contact_id, stream_item_sync4, account_type, comments, res_package, stream_item_sync1, stream_item_sync3, stream_item_sync2, icon, data_set, label, contact_id, contact_lookup, raw_contact_source_id, account_name, _id, text, timestamp
                 * FROM view_stream_items
                 * WHERE (contact_id=?) with args [1]
                 */
                // content://com.android.contacts/contacts/lookup/3176r1-1F15251B1F1B1D708C/1/stream_items
                return Uri.withAppendedPath(ContactsContract.Contacts.getLookupUri((Long) patchSegments[0], (String) patchSegments[1]), STREAM_ITEMS_CONTENT_DIRECTORY);
            case CONTACTS_AS_VCARD: // lookup key
                /**
                 * SELECT _id FROM view_contacts WHERE (_id=?) with args [1]
                 * SELECT account_type, is_primary, data_version, group_sourceid, contact_id, starred, data_id, account_name, dirty, sourceid, data9, data8, data7, data6, carrier_presence, data5, is_super_primary, res_package, data4, data3, data2, data1, data_sync4, data_set, version, data_sync1, data_sync2, data_sync3, backup_id, deleted, sync4, preferred_phone_account_component_name, sync3, raw_contact_is_user_profile, data10, preferred_phone_account_id, sync2, data12, mimetype, _id, sync1, data11, data14, account_type_and_data_set, data13, data15
                 * FROM view_raw_entities
                 * WHERE (1) AND ((contact_id IN (1)))
                 * ORDER BY contact_id with args null
                 */
                // content://com.android.contacts/contacts/as_vcard/3176r1-1F15251B1F1B1D708C
                Uri.Builder builder = ContactsContract.Contacts.CONTENT_VCARD_URI.buildUpon().appendPath((String) patchSegments[1]);
                if (!(Boolean) patchSegments[3]) {
                    // content://com.android.contacts/contacts/as_vcard/3176r1-1F15251B1F1B1D708C?no_photo=true
                    builder.appendQueryParameter(ContactsContract.Contacts.QUERY_PARAMETER_VCARD_NO_PHOTO, "true");
                }
                return builder.build();
            case CONTACTS_AS_MULTI_VCARD: // lookup key : lookup key : ...
                /**
                 *
                 */
                // content://com.android.contacts/contacts/as_multi_vcard/3176r1-1F15251B1F1B1D708C%3A3176r2-A8D0AECAB0D8D4A8C2B4%3A3176r3-ACC4BEB0CECEB0B0BEAEB0CA%3A3176r4-ACCAD8CCCEA8BEBEB8C2?no_photo=true
                builder = ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI.buildUpon().appendPath((String) patchSegments[1]);
                if (!(Boolean) patchSegments[3]) {
                    // Unused for CONTACTS_AS_MULTI_VCARD
                    builder.appendQueryParameter(ContactsContract.Contacts.QUERY_PARAMETER_VCARD_NO_PHOTO, "true");
                }
                return builder.build();
            // Sub action for above ACTIONS -- END --

            case RAW_CONTACTS:
                /**
                 * SELECT _id, contact_id, deleted, display_name, display_name_alt, display_name_source, phonetic_name, phonetic_name_style, sort_key, sort_key_alt, custom_ringtone, send_to_voicemail, starred, pinned, aggregation_mode, raw_contact_is_user_profile, metadata_dirty, account_name, account_type, data_set, account_type_and_data_set, dirty, sourceid, backup_id, version, sync1, sync2, sync3, sync4, phonebook_label, phonebook_bucket, phonebook_label_alt, phonebook_bucket_alt
                 * FROM view_raw_contacts
                 * WHERE (1) with args null
                 */
                // content://com.android.contacts/raw_contacts
                return ContactsContract.RawContacts.CONTENT_URI;
            case RAW_CONTACT_ENTITIES:
                /**
                 * SELECT _id, contact_id, data_id, deleted, starred, raw_contact_is_user_profile, account_name, account_type, data_set, account_type_and_data_set, dirty, sourceid, backup_id, version, sync1, sync2, sync3, sync4, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, data11, data12, data13, data14, data15, carrier_presence, preferred_phone_account_component_name, preferred_phone_account_id, data_version, is_primary, is_super_primary, mimetype, res_package, data_sync1, data_sync2, data_sync3, data_sync4, group_sourceid
                 * FROM view_raw_entities
                 * WHERE (1) AND ((deleted=?))
                 * with args [0]
                 */
                // content://com.android.contacts/raw_contact_entities
                return ContactsContract.RawContactsEntity.CONTENT_URI;
            case RAW_CONTACT_ENTITIES_CORP:
                /**
                 *
                 */
                // content://com.android.contacts/raw_contact_entities_corp
                // ContactsContract.RawContactsEntity.CORP_CONTENT_URI;
                // java.lang.SecurityException: The caller must have the android.permission.INTERACT_ACROSS_USERS permission.
                return Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "raw_contact_entities_corp");
            // Sub action for above ACTIONS -- BEGIN --
            case RAW_CONTACTS_ID:
                /**
                 * SELECT phonetic_name, last_time_contacted, custom_ringtone, pinned, account_type, aggregation_mode, contact_id, display_name_alt, sort_key_alt, starred, phonebook_label, account_name, display_name_source, phonetic_name_style, send_to_voicemail, dirty, sourceid, phonebook_label_alt, phonebook_bucket, data_set, display_name, sort_key, version, backup_id, deleted, sync4, sync3, raw_contact_is_user_profile, times_contacted, sync2, _id, metadata_dirty, sync1, account_type_and_data_set, phonebook_bucket_alt
                 * FROM view_raw_contacts
                 * WHERE (1 AND _id=?) with args [1]
                 */
                // content://com.android.contacts/raw_contacts/1
                return ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, (Long) patchSegments[0]);
            case RAW_CONTACTS_ID_DATA:
                /**
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND raw_contact_id=?) with args [1]
                 */
                // content://com.android.contacts/raw_contacts/1/data
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.RawContacts.Data.CONTENT_DIRECTORY);
            case RAW_CONTACTS_ID_DISPLAY_PHOTO:
                /**
                 * SELECT _id, data14
                 * FROM view_data data LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1) AND (raw_contact_id=? AND mimetype_id=?)
                 * ORDER BY is_primary DESC with args [1, 10]
                 */
                // content://com.android.contacts/raw_contacts/1/display_photo
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.RawContacts.DisplayPhoto.CONTENT_DIRECTORY);
            case RAW_CONTACT_ID_ENTITY:
                /**
                 * SELECT account_type, is_primary, data_version, group_sourceid, contact_id, starred, data_id, account_name, dirty, sourceid, data9, data8, data7, data6, carrier_presence, data5, is_super_primary, res_package, data4, data3, data2, data1, data_sync4, data_set, version, data_sync1, data_sync2, data_sync3, backup_id, deleted, sync4, preferred_phone_account_component_name, sync3, raw_contact_is_user_profile, data10, preferred_phone_account_id, sync2, data12, mimetype, _id, sync1, data11, data14, account_type_and_data_set, data13, data15
                 * FROM view_raw_entities
                 * WHERE (1 AND _id=?) with args [1]
                 */
                // content://com.android.contacts/raw_contacts/1/entity
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, (Long) patchSegments[0]), ContactsContract.RawContacts.Entity.CONTENT_DIRECTORY);
            case RAW_CONTACTS_ID_STREAM_ITEMS:
                /**
                 *
                 */
                //
                return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, (Long) patchSegments[0]), STREAM_ITEMS_CONTENT_DIRECTORY);
            // Sub action for above ACTIONS -- END --

            case DATA:
                /**
                 * SELECT _id, raw_contact_id, contact_id, name_raw_contact_id, raw_contact_is_user_profile, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, data11, data12, data13, data14, data15, carrier_presence, preferred_phone_account_component_name, preferred_phone_account_id, data_version, is_primary, is_super_primary, mimetype, res_package, data_sync1, data_sync2, data_sync3, data_sync4, group_sourceid, account_name, account_type, data_set, account_type_and_data_set, dirty, sourceid, backup_id, version, custom_ringtone, display_name, display_name_alt, display_name_source, in_default_directory, in_visible_group, lookup, phonetic_name, phonetic_name_style, photo_id, photo_file_id, photo_uri, photo_thumb_uri, send_to_voicemail, sort_key_alt, sort_key, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, starred, pinned, has_phone_number, contact_last_updated_timestamp
                 * FROM view_data data
                 * LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1) with args null
                 */
                // content://com.android.contacts/data
                return ContactsContract.Data.CONTENT_URI;
            // Sub action for above ACTIONS -- BEGIN --
            case DATA_ID:
                /**
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data
                 * LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND _id=?) with args [1]
                 */
                // content://com.android.contacts/data/1
                return ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, (Long) patchSegments[0]);
            // Sub action for above ACTIONS -- END --

            case PHONES:
                /**
                 * SELECT _id, raw_contact_id, contact_id, name_raw_contact_id, raw_contact_is_user_profile, data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, data11, data12, data13, data14, data15, carrier_presence, preferred_phone_account_component_name, preferred_phone_account_id, data_version, is_primary, is_super_primary, mimetype, res_package, data_sync1, data_sync2, data_sync3, data_sync4, group_sourceid, account_name, account_type, data_set, account_type_and_data_set, dirty, sourceid, backup_id, version, custom_ringtone, display_name, display_name_alt, display_name_source, in_default_directory, in_visible_group, lookup, phonetic_name, phonetic_name_style, photo_id, photo_file_id, photo_uri, photo_thumb_uri, send_to_voicemail, sort_key_alt, sort_key, phonebook_label, phonebook_label_alt, phonebook_bucket, phonebook_bucket_alt, starred, pinned, has_phone_number, contact_last_updated_timestamp
                 * FROM view_data data LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND mimetype_id=5) with args null
                 */
                // content://com.android.contacts/data/phones
                return ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            case PHONES_ID:
                /**
                 * SELECT phonetic_name, status_updates.status_res_package AS status_res_package, custom_ringtone, contacts_status_updates.status_ts AS contact_status_ts, account_type, data_version, photo_file_id, contacts_status_updates.status_res_package AS contact_status_res_package, group_sourceid, display_name_alt, sort_key_alt, presence.mode AS mode, 0 AS last_time_used, starred, contacts_status_updates.status_label AS contact_status_label, has_phone_number, presence.chat_capability AS chat_capability, raw_contact_id, carrier_presence, contact_last_updated_timestamp, res_package, photo_uri, data_sync4, phonebook_bucket, 0 AS times_used, display_name, sort_key, data_sync1, version, data_sync2, data_sync3, photo_thumb_uri, status_updates.status_label AS status_label, agg_presence.mode AS contact_presence, in_default_directory, 0 AS times_contacted, _id, account_type_and_data_set, name_raw_contact_id, status_updates.status AS status, phonebook_bucket_alt, 0 AS last_time_contacted, pinned, is_primary, photo_id, contact_id, agg_presence.chat_capability AS contact_chat_capability, contacts_status_updates.status_icon AS contact_status_icon, in_visible_group, phonebook_label, account_name, display_name_source, data9, dirty, sourceid, phonetic_name_style, send_to_voicemail, data8, lookup, data7, data6, phonebook_label_alt, data5, is_super_primary, data4, data3, data2, data1, data_set, contacts_status_updates.status AS contact_status, backup_id, preferred_phone_account_component_name, raw_contact_is_user_profile, status_updates.status_ts AS status_ts, data10, preferred_phone_account_id, data12, mimetype, status_updates.status_icon AS status_icon, data11, data14, data13, hash_id, data15
                 * FROM view_data data LEFT OUTER JOIN agg_presence ON (contact_id = agg_presence.presence_contact_id) LEFT OUTER JOIN status_updates contacts_status_updates ON (status_update_id=contacts_status_updates.status_update_data_id) LEFT OUTER JOIN presence ON (presence_data_id=data._id) LEFT OUTER JOIN status_updates ON (status_updates.status_update_data_id=data._id) LEFT OUTER JOIN (SELECT 0 as STAT_DATA_ID,0 as x_times_used, 0 as x_last_time_used,0 as times_used, 0 as last_time_used where 0) as data_usage_stat ON (STAT_DATA_ID=data._id)
                 * WHERE (1 AND mimetype_id=5 AND _id=?) with args [498]
                 */
                // content://com.android.contacts/data/phones/498
                return ContentUris.withAppendedId(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (Long) patchSegments[0]);
            case GROUPS:
                /**
                 * SELECT _id, account_name, account_type, data_set, account_type_and_data_set, sourceid, dirty, version, res_package, title, title_res, group_visible, system_id, deleted, notes, should_sync, favorites, auto_add, group_is_read_only, sync1, sync2, sync3, sync4
                 * FROM view_groups WHERE (1) with args null
                 */
                // content://com.android.contacts/groups
                return ContactsContract.Groups.CONTENT_URI;
            case GROUPS_SUMMARY:
                /**
                 * SELECT _id, account_name, account_type, data_set, account_type_and_data_set, sourceid, dirty, version, res_package, title, title_res, group_visible, system_id, deleted, notes, should_sync, favorites, auto_add, group_is_read_only, sync1, sync2, sync3, sync4, ifnull(group_member_count, 0) AS summ_count, (SELECT COUNT(contacts._id) FROM contacts INNER JOIN raw_contacts ON (raw_contacts.contact_id=contacts._id) INNER JOIN data ON (data.data1=groups._id AND data.raw_contact_id=raw_contacts._id AND data.mimetype_id=(SELECT _id FROM mimetypes WHERE mimetypes.mimetype='vnd.android.cursor.item/group_membership')) WHERE has_phone_number) AS summ_phones
                 * FROM view_groups AS groups LEFT OUTER JOIN
                 * (SELECT data.data1 AS member_count_group_id, COUNT(data.raw_contact_id) AS group_member_count FROM data WHERE data.mimetype_id = (SELECT _id FROM mimetypes WHERE mimetypes.mimetype = 'vnd.android.cursor.item/group_membership')GROUP BY member_count_group_id) AS member_count_table
                 * ON (groups._id = member_count_table.member_count_group_id)
                 * WHERE (1)
                 * GROUP BY groups._id with args null
                 */
                // content://com.android.contacts/groups_summary
                return ContactsContract.Groups.CONTENT_SUMMARY_URI;

            // Sub action for above ACTIONS -- BEGIN --
            case GROUPS_ID:
                /**
                 * SELECT sourceid, dirty, favorites, title_res, account_type, notes, res_package, system_id, data_set, title, version, group_visible, deleted, sync4, sync3, account_name, auto_add, should_sync, sync2, _id, sync1, account_type_and_data_set, group_is_read_only
                 * FROM view_groups
                 * WHERE (_id=?) with args [1]
                 */
                // content://com.android.contacts/groups/1
                return ContentUris.withAppendedId(ContactsContract.Groups.CONTENT_URI, (Long) patchSegments[0]);

            // Sub action for above ACTIONS -- END --
            default:
                throw new RuntimeException("mode is null !");
        }
    }
}
