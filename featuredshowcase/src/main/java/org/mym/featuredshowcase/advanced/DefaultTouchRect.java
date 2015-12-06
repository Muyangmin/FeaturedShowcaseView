package org.mym.featuredshowcase.advanced;

import android.graphics.RectF;

import org.mym.featuredshowcase.target.Padding;

/**
 * Default touch area: just the alpha area itself.
 */
public class DefaultTouchRect implements TouchRectCalculator {

    private RectF rectF = new RectF();

    @Override
    public RectF getTouchRect() {
        return rectF;
    }

    @Override
    public void updateTouchRect(RectF target, Padding shapePadding) {
        //plus
        rectF.set(target.left - shapePadding.paddingLeft,
                target.top - shapePadding.paddingTop,
                target.right + shapePadding.paddingRight,
                target.bottom + shapePadding.paddingBottom);
    }
}
