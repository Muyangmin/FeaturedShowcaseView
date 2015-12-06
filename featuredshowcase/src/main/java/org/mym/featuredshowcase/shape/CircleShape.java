package org.mym.featuredshowcase.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 * Draw circle.
 */
public class CircleShape extends AlphaShapeDrawer {

    private float radius;
    private int shortAxis;           // Use this to decide center, 1=X, 2=Y.
    private float shortAxisLength;   // Use this to calc center.

    public CircleShape() {
        this(0);
    }

    public CircleShape(int radius) {
        this.radius = radius;
    }

    @Override
    public void adjustToTarget(Target target) {
        float width = target.getRectF().width();
        float height = target.getRectF().height();
        if (width <= height) {
            shortAxis = 1;
            radius = height / 2;
            shortAxisLength = width / 2;
        } else {
            shortAxis = 2;
            radius = width / 2;
            shortAxisLength = height / 2;

        }
    }

    @Override
    protected void drawAlpha(Canvas canvas, Paint paint, float x, float y, Padding paddingInPixel) {
        float centerX, centerY;
        float radiusPlusPadding;
        float halfPadding;
        //If the X is shorter, then use x for center.
        if (shortAxis == 1) {
            halfPadding = (paddingInPixel.paddingTop + paddingInPixel.paddingBottom) / 2;
            centerX = x + shortAxisLength;
            centerY = y + radius + halfPadding - paddingInPixel.paddingTop;
            radiusPlusPadding = radius + halfPadding;
        } else {
            halfPadding = (paddingInPixel.paddingLeft + paddingInPixel.paddingRight) / 2;
            centerX = x + radius + halfPadding - paddingInPixel.paddingLeft;
            centerY = y + shortAxisLength;
            radiusPlusPadding = radius + halfPadding;
        }

        canvas.drawCircle(centerX, centerY, radiusPlusPadding, paint);
    }
}
