package org.mym.featuredshowcase.shape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 *
 */
public class BitmapTargetDrawer implements TargetDrawer{

    private float width;
    private float height;

    private int resId;
    private Bitmap bitmap;
    private Paint alphaPaint;

    public BitmapTargetDrawer(int resId) {
        this.resId = resId;
        alphaPaint = new Paint();
        alphaPaint.setAlpha(0); //totally transparent
        alphaPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        alphaPaint.setAntiAlias(true);
        alphaPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @Override
    public void adjustToTarget(Target target) {
        this.width = target.getRectF().width();
        this.height = target.getRectF().height();
    }

    @Override
    public void drawTarget(Context context, Canvas canvas, float x, float y, Padding paddingInPixel) {

        float width = this.width + paddingInPixel.paddingLeft + paddingInPixel.paddingRight;
        float height = this.height + paddingInPixel.paddingTop + paddingInPixel.paddingBottom;
        if (bitmap==null || bitmap.isRecycled()){
            bitmap = createBitmapByMatrix(context, width, height);
        }
        if (bitmap!=null){
            //clear canvas mask color on target area.
            float startX = x - paddingInPixel.paddingLeft;
            float startY = y - paddingInPixel.paddingTop;
            float endX = startX + bitmap.getWidth();
            float endY = startY + bitmap.getHeight();

            canvas.drawRect(startX, startY, endX, endY, alphaPaint);

            canvas.drawBitmap(bitmap, startX, startY, null);
        }
    }

    private Bitmap createBitmapByMatrix(Context context, float width, float height) {
        Bitmap tempBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        int bmpWidth = tempBitmap.getWidth();
        int bmpHeight = tempBitmap.getHeight();
        Bitmap resultBitmap = tempBitmap;
        if (bmpWidth != width || bmpHeight != height) {
            float scaleW = width/bmpWidth;
            float scaleH = height/bmpHeight;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleW, scaleH);
            resultBitmap = Bitmap.createBitmap(tempBitmap, 0, 0, bmpWidth,
                    bmpHeight, matrix, true);
            tempBitmap.recycle();
        }
        return resultBitmap;
    }

    @Override
    public void recycle() {
        if (bitmap != null && (!bitmap.isRecycled())) {
            bitmap.recycle();
        }
    }
}
