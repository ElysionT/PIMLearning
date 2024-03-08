package org.learn.pim.model;

import org.learn.pim.model.DetailData;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class VCardData extends DetailData {

    private static final String TAG = "VCardData";

    private byte[] mMimeData;

    public VCardData(Status status) {
        super(status);
    }

    public void setMimeData(byte[] mimeData) {
        mMimeData = mimeData;
    }

    @Override
    public String getDataDetail() {
        return decode();
    }

    public String decode() {
        String msg = "Data is EMPTY or ERROR";
        if (null == mMimeData || 0 == mMimeData.length) {
            return msg;
        }

        try {
            final byte[] bytes = Arrays.copyOf(mMimeData, mMimeData.length);

            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                if (b != 95) {
                    bytes[i] = b;
                } else {
                    bytes[i] = 32;
                }
            }
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            for (int i = 0; i < bytes.length; i++) {
                int b = bytes[i];
                if (b == '=') {
                    int u = Character.digit((char) bytes[++i], 16);
                    int l = Character.digit((char) bytes[++i], 16);
                    if (u == -1 || l == -1) {
                        continue;
                    }
                    buffer.write((char) ((u << 4) + l));
                } else {
                    buffer.write(b);
                }
            }
            return new String(buffer.toByteArray(), "UTF-8");
        } catch (Exception e) {
            return msg;
        }
    }
}
