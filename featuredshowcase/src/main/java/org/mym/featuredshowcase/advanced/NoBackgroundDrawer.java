package org.mym.featuredshowcase.advanced;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Default background drawer: no background :)
 */
public class NoBackgroundDrawer implements BackgroundDrawer {
    @Override
    public void drawBackground(Context context, Canvas canvas, RectF targetRect) {
        //do nothing
    }

    @Override
    public void recycle() {
        //do nothing
    }
}
