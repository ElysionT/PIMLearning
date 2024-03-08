package org.learn.pim.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.util.Log;

public class ImageData extends DetailData {

    private static final String TAG = "ImageData";

    private byte[] mPhotoBinaryData;

    public ImageData(Status status) {
        super(status);
    }

    public void setPhotoBinaryData(byte[] photoBinaryData) {
        mPhotoBinaryData = photoBinaryData;
    }

    public byte[] getPhotoBinaryData() {
        return mPhotoBinaryData;
    }

    public Bitmap decodedPhotoBitmap() {
        if (null == mPhotoBinaryData)
            throw new RuntimeException("Load photo first");

        return BitmapFactory.decodeByteArray(mPhotoBinaryData, 0, mPhotoBinaryData.length);
    }

    public BitmapDrawable decodedPhotoDrawable(Resources resources) {
        Bitmap bitmap = decodedPhotoBitmap();
        Log.i(TAG, "decodedPhotoDrawable - bitmap:" + bitmap.getWidth() + " - " + bitmap.getHeight());
        if (bitmap.getHeight() != bitmap.getWidth()) {
            // Crop the bitmap into a square.
            final int size = Math.max(bitmap.getWidth(), bitmap.getHeight());
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, size, size);
        }
        return new BitmapDrawable(resources, bitmap);
    }
}
