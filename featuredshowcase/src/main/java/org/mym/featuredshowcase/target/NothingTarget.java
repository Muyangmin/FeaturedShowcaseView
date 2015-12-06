package org.mym.featuredshowcase.target;

import android.graphics.RectF;

/**
 * No target.
 */
public class NothingTarget implements Target{

    private static final RectF RECT = new RectF(0,0,0,0);

    @Override
    public RectF getRectF() {
        return RECT;
    }
}
