package org.learn.pim.model;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VCardLoader extends DetailLoader<VCardData> {
    public VCardLoader(Context context, Uri lookupUri) {
        super(context, lookupUri);
    }

    @Nullable
    @Override
    public VCardData loadInBackground() {
        VCardData result = new VCardData(DetailData.Status.NOT_FOUND);
        if (mLookupUri != null) {
            try {
//                final AssetFileDescriptor fd = getContext().getContentResolver().openAssetFileDescriptor(mLookupUri, "r");
                final InputStream inputStream = getContext().getContentResolver().openInputStream(mLookupUri);

                byte[] buffer = new byte[16 * 1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    int size;
                    while ((size = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, size);
                    }
                    result.mStatus = DetailData.Status.LOADED;
                    result.setMimeData(baos.toByteArray());
                } finally {
                    baos.close();
                    inputStream.close();
//                    if (fd != null) {
//                        fd.close();
//                    }
                }
            } catch (IOException ioe) {
                // Just fall back to the case below.
            }
        }
        return result;
    }
}
