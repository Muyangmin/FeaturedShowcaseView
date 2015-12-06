package org.mym.featuredshowcase.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 * Draw oval.
 */
public class OvalShape extends AlphaShapeDrawer{

    private float width;
    private float height;

    public OvalShape() {
        this(0, 0);
    }

    public OvalShape(float width, float height) {
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
        RectF ovalRectF = new RectF(x - paddingInPixel.paddingLeft,
                y - paddingInPixel.paddingTop,
                x + width + paddingInPixel.paddingRight,
                y + height + paddingInPixel.paddingBottom);
        canvas.drawOval(ovalRectF, paint);
    }
}
