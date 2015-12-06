package org.mym.featuredshowcase.shape;

import android.graphics.Canvas;
import android.graphics.Paint;

import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 * draw rectangle.
 */
public class RectangleShape extends AlphaShapeDrawer{

    private float width;
    private float height;

    public RectangleShape() {
        this(0, 0);
    }

    public RectangleShape(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void adjustToTarget(Target target) {
        width = target.getRectF().width();
        height = target.getRectF().height();
    }

    @Override
    protected void drawAlpha(Canvas canvas, Paint paint, float x, float y, Padding paddingInPixel) {
        canvas.drawRect(x - paddingInPixel.paddingLeft, y - paddingInPixel.paddingTop,
                x + width + paddingInPixel.paddingRight, y + height + paddingInPixel.paddingBottom,
                paint);
    }
}
