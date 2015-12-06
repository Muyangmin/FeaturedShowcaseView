package org.mym.featuredshowcase.advanced;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * This interface allow user to customize background drawing.
 */
public interface BackgroundDrawer {
    /**
     * Called on each onDraw(), before drawing alpha area and after drawing mask color.
     * Do not do too much work in this method, be careful for 60 FPS!
     * @param context the context object associated of view, maybe helpful for retrieving resources.
     * @param canvas the canvas to draw.
     * @param targetRect this parameter is provided for layout bitmaps.
     */
    void drawBackground(Context context, Canvas canvas, RectF targetRect);

    /**
     * Called when ShowcaseView is dismissed, etc.
     */
    void recycle();
}
