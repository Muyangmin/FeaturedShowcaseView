package org.mym.featuredshowcase.advanced.layoutdrawer;

import android.content.Context;
import android.graphics.Bitmap;

public interface BitmapCreator {
    /**
     * Get the bitmap for display, create it if needed.
     *
     * @param context the context object for creating bitmap object.
     * @return Returns a bitmap for draw ; if null or already recycled, nothing was drawn.
     */
    Bitmap getBitmapOrCreate(Context context);

    /**
     * Do the real bitmap recycle.
     */
    void recycle();
}