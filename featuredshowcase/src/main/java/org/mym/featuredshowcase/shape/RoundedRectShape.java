package org.mym.featuredshowcase.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 * Draw rounded Rect.
 */
public class RoundedRectShape extends AlphaShapeDrawer{

    private float width;
    private float height;
    private float radiusX;
    private float radiusY;

    public RoundedRectShape(float radius) {
        this(radius, radius);
    }

    public RoundedRectShape(float radiusX, float radiusY) {
        this(0, 0, radiusX, radiusY);
    }

    public RoundedRectShape(float width, float height, float radius) {
        this(width, height, radius, radius);
    }

    public RoundedRectShape(float width, float height, float radiusX, float radiusY) {
        this.width = width;
        this.height = height;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void adjustToTarget(Target target) {
        width = target.getRectF().width();
        height = target.getRectF().height();
    }

    @Override
    protected void drawAlpha(Canvas canvas, Paint paint, float x, float y, Padding paddingInPixel) {
        RectF rectF = new RectF(x - paddingInPixel.paddingLeft, y - paddingInPixel.paddingTop,
                x + width + paddingInPixel.paddingRight, y + height + paddingInPixel.paddingBottom);
        canvas.drawRoundRect(rectF, radiusX, radiusY, paint);
    }
}
