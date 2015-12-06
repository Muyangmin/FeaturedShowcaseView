package org.mym.featuredshowcase.shape;

import android.content.Context;
import android.graphics.Canvas;

import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 * Draw target.
 * <p>
 * By convention, Shape dimension should be computed in {@link #adjustToTarget(Target)} or
 * constructor, and just draw it in {@link #drawTarget(Context, Canvas, float, float, Padding)}.
 * </p>
 */
public interface TargetDrawer {
    /**
     * Update shape bounds if necessary.
     */
    void adjustToTarget(Target target);

    /**
     * Do the real alpha drawing based on start point (x, y).
     */
    void drawTarget(Context context, Canvas canvas, float x, float y, Padding paddingInPixel);

    /**
     * Do the recycle work when ShowcaseView is no longer visible, e.g. recycle bitmaps.
     */
    void recycle();
}
