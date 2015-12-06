package org.mym.featuredshowcase.shape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import org.mym.featuredshowcase.target.Padding;

/**
 * Draw an alpha target, i.e. absolutely transparent.
 */
public abstract class AlphaShapeDrawer implements TargetDrawer{

    private Paint alphaPaint;

    public AlphaShapeDrawer() {
        alphaPaint = new Paint();
        alphaPaint.setAlpha(0); //totally transparent
        alphaPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        alphaPaint.setAntiAlias(true);
        alphaPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    public void drawTarget(Context context, Canvas canvas, float x, float y, Padding paddingInPixel) {
        drawAlpha(canvas, alphaPaint, x, y, paddingInPixel);
    }

    @Override
    public void recycle() {
        //Do nothing
    }

    /**
     * Do the real alpha drawing based on start point (x, y).
     */
    protected abstract void drawAlpha(Canvas canvas, Paint paint, float x, float y, Padding
            paddingInPixel);

}
