package org.mym.featuredshowcase.advanced;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Default background drawer: just draw a full-sized image.
 * Created by Muyangmin on 15-12-2.
 */
public class FullSizeBackgroundDrawer implements BackgroundDrawer {

    private Bitmap bitmap;
    private int ResId;

    public FullSizeBackgroundDrawer(int resId) {
        ResId = resId;
    }

    @Override
    public void drawBackground(Context context, Canvas canvas, RectF targetRect) {
        if (bitmap == null || bitmap.isRecycled()) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), ResId);
        }
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    @Override
    public void recycle() {
        if (bitmap != null && (!bitmap.isRecycled())) {
            bitmap.recycle();
            bitmap = null;
        }
    }
}
