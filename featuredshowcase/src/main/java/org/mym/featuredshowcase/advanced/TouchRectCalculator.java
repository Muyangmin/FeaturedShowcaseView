package org.mym.featuredshowcase.advanced;

import android.graphics.RectF;

import org.mym.featuredshowcase.target.Padding;

/**
 * This provides user to customize the touch area, ofter used with customize background drawer.
 */
public interface TouchRectCalculator {
    /**
     * Returns the touch zone for dismiss action.
     */
    RectF getTouchRect();

    /**
     * Called when target changed. subclass should re-calc touch rect here.
     * @param target target Rect.
     * @param shapePadding shape padding.
     */
    void updateTouchRect(RectF target, Padding shapePadding);
}
