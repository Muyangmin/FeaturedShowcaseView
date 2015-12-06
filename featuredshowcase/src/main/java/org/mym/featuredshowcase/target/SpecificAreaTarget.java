package org.mym.featuredshowcase.target;

import android.graphics.RectF;

/**
 * target for specific area.
 */
public class SpecificAreaTarget implements Target{

    private RectF rectF;

    public SpecificAreaTarget(float x, float y, int width, int height) {
        this(new RectF(x, y, width, height));
    }

    public SpecificAreaTarget(RectF rectF) {
        this.rectF = rectF;
    }

    @Override
    public RectF getRectF() {
        return rectF;
    }
}
