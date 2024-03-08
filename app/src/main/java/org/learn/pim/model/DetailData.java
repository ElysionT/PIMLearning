package org.learn.pim.model;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DetailData {

    protected enum Status {
        /**
         * Contact is successfully loaded
         */
        LOADED,
        /**
         * There was an error loading the contact
         */
        ERROR,
        /**
         * Contact is not found
         */
        NOT_FOUND,
    }

    protected Status mStatus;

    public DetailData() {
    }

    public DetailData(Status status) {
        mStatus = status;
    }

    private List<Item> mItems = new ArrayList<>();

    public void iterateDate(Cursor data) {
        mItems.clear();
        if (null == data)
            return;
        data.moveToPosition(-1);
        while (data.moveToNext()) {
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
                mItems.add(new Item(columnName, dataType, value));
                System.out.println(columnName + ":" + value + "");
            }
            System.out.println("---------------------------------");
        }
    }

    public String getDataDetail() {
        return "mStatus:" + mStatus + "\n" + mItems.toString();
    }

    /**
     * @return true when an exception happened during loading, in which case
     * {@link #getException} returns the actual exception object.
     * Note {@link #isNotFound()} and {@link #isError()} are mutually exclusive; If
     * {@link #isError()} is {@code true}, {@link #isNotFound()} is always {@code false},
     * and vice versa.
     */
    public boolean isError() {
        return mStatus == Status.ERROR;
    }

    /**
     * @return true when the specified contact is not found.
     * Note {@link #isNotFound()} and {@link #isError()} are mutually exclusive; If
     * {@link #isError()} is {@code true}, {@link #isNotFound()} is always {@code false},
     * and vice versa.
     */
    public boolean isNotFound() {
        return mStatus == Status.NOT_FOUND;
    }

    /**
     * @return true if the specified contact is successfully loaded.
     * i.e. neither {@link #isError()} nor {@link #isNotFound()}.
     */
    public boolean isLoaded() {
        return mStatus == Status.LOADED;
    }

    class Item {
        String name;
        int type;
        Object value;

        Item(String name, int type, Object value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", value=" + value +
                    '}';
        }
    }
}
