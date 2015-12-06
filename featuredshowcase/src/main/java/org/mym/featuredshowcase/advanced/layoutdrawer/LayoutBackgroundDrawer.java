package org.mym.featuredshowcase.advanced.layoutdrawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import org.mym.featuredshowcase.advanced.BackgroundDrawer;

import java.util.Collection;

/**
 * Draw complicated backgrounds using a easy way similar as layouts.
 */
public class LayoutBackgroundDrawer implements BackgroundDrawer {

    //Use collection type so that clients can use ordered and unordered data structures.
    private Collection<BitmapItem> bitmapItems;

    public LayoutBackgroundDrawer(Collection<BitmapItem> bitmapItems) {
        this.bitmapItems = bitmapItems;
    }

    @Override
    public void drawBackground(Context context, Canvas canvas, RectF targetRect) {
        if (bitmapItems==null || bitmapItems.isEmpty()){
            return ;
        }
        for (BitmapItem item:bitmapItems){
            Bitmap bitmap = item.bitmapCreator.getBitmapOrCreate(context);
            if (bitmap != null && (!bitmap.isRecycled())) {
                drawBitmapItem(context, canvas, targetRect, item);
            }
        }
    }

    @Override
    public void recycle() {
        if (bitmapItems==null || bitmapItems.isEmpty()){
            return ;
        }
        for (BitmapItem item : bitmapItems){
            item.bitmapCreator.recycle();
//            item.layoutParam = null;
        }
//        bitmapItems.clear();
//        bitmapItems = null;
    }

    private void drawBitmapItem(Context context, Canvas canvas, RectF targetRect, BitmapItem item){
        LayoutParam param = item.layoutParam;
        if (param==null){
            return ;
        }
        Bitmap bitmap = item.bitmapCreator.getBitmapOrCreate(context);
        switch (param.type){
            case LayoutParam.TYPE_ABSOLUTE:
                drawAbsoluteBitmap(canvas, bitmap, param.pointX, param.pointY);
                break;
            case LayoutParam.TYPE_RELATIVE_EDGE:
                drawRelativeEdgeBitmap(canvas, bitmap, param.edge, param.marginEdge, targetRect);
                break;
            case LayoutParam.TYPE_RELATIVE_POINT:
                drawRelativePointBitmap(canvas, bitmap, param.pointX, param.pointY, param
                        .xOffset, param.yOffset);
                break;
            default:
                throw new IllegalArgumentException("Unsupported type: " + param.type);
        }
    }

    private void drawAbsoluteBitmap(Canvas canvas, Bitmap bitmap, float x, float y) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    private void drawRelativeEdgeBitmap(Canvas canvas, Bitmap bitmap, int edge, float margin,
                                        RectF targetRect){
        float startX, startY;
        switch (edge) {
            case LayoutParam.EDGE_LEFT:
                startX = targetRect.left - bitmap.getWidth() - margin;
                startY = targetRect.centerY() - bitmap.getHeight() / 2;
                break;
            case LayoutParam.EDGE_TOP:
                startX = targetRect.centerX() - bitmap.getWidth() / 2;
                startY = targetRect.top - bitmap.getHeight() - margin;
                break;
            case LayoutParam.EDGE_RIGHT:
                startX = targetRect.right + margin;
                startY = targetRect.centerY() - bitmap.getHeight() / 2;
                break;
            case LayoutParam.EDGE_BOTTOM:
                startX = targetRect.centerX() - bitmap.getWidth() / 2;
                startY = targetRect.bottom + margin;
                break;
            default:
                throw new IllegalArgumentException("Unsupported edge:" + edge);
        }
        canvas.drawBitmap(bitmap, startX, startY, null);
    }

    private void drawRelativePointBitmap(Canvas canvas, Bitmap bitmap, float pointX,
                                         float pointY, float xOffset, float yOffset){
        canvas.drawBitmap(bitmap, pointX+xOffset, pointY+yOffset, null);
    }


}
