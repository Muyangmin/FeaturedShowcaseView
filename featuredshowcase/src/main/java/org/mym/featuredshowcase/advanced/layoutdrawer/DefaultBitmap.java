package org.mym.featuredshowcase.advanced.layoutdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DefaultBitmap implements BitmapCreator {

    private int resId;
    private Bitmap bitmap;

    public DefaultBitmap(int resId) {
        this.resId = resId;
    }

    @Override
    public Bitmap getBitmapOrCreate(Context context) {
        if (bitmap == null || bitmap.isRecycled()) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        }
        return bitmap;
    }

    @Override
    public void recycle() {
        if (bitmap != null && (!bitmap.isRecycled())) {
            bitmap.recycle();
        }
    }
}
