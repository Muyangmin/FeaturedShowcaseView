package org.mym.featuredshowcase;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import org.mym.featuredshowcase.target.Target;

/**
 * This class provides a customized guide view.
 */
public class FeaturedShowcaseView extends View implements View.OnTouchListener{

    private ShowConfig showConfig;

    //To fit screen measure size change, save the "old" dimensions on every onDraw calls
    private int oldWidth;
    private int oldHeight;

    //pre allocate for improving onDraw performance
    private Bitmap internalBitmap;
    private Canvas internalCanvas;

    //fit global layout changes
    private ViewTreeObserver.OnGlobalLayoutListener updateLayoutListener = new ViewTreeObserver
            .OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            if (showConfig != null && showConfig.target != null){
                notifyTargetChanged(showConfig.target);
            }
        }
    };

    /**
     * Initialize ShowcaseView and show it.
     * @return Returns the shown instance.
     */
    public static FeaturedShowcaseView show(Activity activity, ShowConfig config){
        FeaturedShowcaseView newInstance = new FeaturedShowcaseView(activity, config);
        newInstance.show(activity);
        return newInstance;
    }

    //Should NOT instantiable by outside
    public FeaturedShowcaseView(Context context, ShowConfig config) {
        super(context);
        init(config);
    }

    private void init(ShowConfig config){
        this.showConfig = config;
        // consume touch events
        setOnTouchListener(this);
        getViewTreeObserver().addOnGlobalLayoutListener(updateLayoutListener);
    }

//   Due to we using static factory method, these constructors are NOT supported !
    public FeaturedShowcaseView(Context context) {
        super(context);
    }

//    public ShowcaseView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public ShowcaseView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @SuppressWarnings("unused")
//    public ShowcaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init(attrs, defStyleRes);
//    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN){
            return false;
        }
        // Touch the target
        if (getTouchRectF().contains(event.getX(), event.getY())){
            hide();
        }
        // touch out side
        else if (showConfig.hideOnTouchOutside){
            hide();
        }
        return true;
    }

    /**
     * Default back action: dismiss only if {@link ShowConfig#hideOnTouchOutside} is allowed, or
     * do nothing otherwise.
     */
    public void onBackPressed() {
        // dismiss only when cancellable
        if (isShown() && !showConfig.hideOnTouchOutside) {
            hide();
        }
    }

    public void show(Activity activity) {
        ((ViewGroup)activity.getWindow().getDecorView()).addView(this);
        if (showConfig.showcaseListener != null){
            showConfig.showcaseListener.onVisible(this);
        }
    }

    public void hide() {
        removeFromWindowAndRecycle();
        if (showConfig.showcaseListener != null) {
            showConfig.showcaseListener.onDismiss(this);
        }
    }

    private void removeFromWindowAndRecycle() {
        if (getParent() != null && getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }

        if (internalBitmap != null) {
            internalBitmap.recycle();
            internalBitmap = null;
        }

        //recycle backgrounds.
        showConfig.targetDrawer.recycle();
        showConfig.backgroundDrawer.recycle();

        internalCanvas = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().removeOnGlobalLayoutListener(updateLayoutListener);
        } else {
            //noinspection deprecation
            getViewTreeObserver().removeGlobalOnLayoutListener(updateLayoutListener);
        }
        updateLayoutListener = null;
    }

    public void setTarget(Target target){
        notifyTargetChanged(target);
    }

    private synchronized void notifyTargetChanged(Target target) {
        if (target!=null){
            //only adjust shape when adjust allowed
            if (showConfig.adjustToTarget){
                showConfig.targetDrawer.adjustToTarget(target);
            }
            //re-calc touch rect!
            showConfig.touchRectCalc.updateTouchRect(target.getRectF(),
                    showConfig.shapePadding);
            //update reference
            showConfig.target = target;
        }
    }

    //get current touch RectF
    private RectF getTouchRectF(){
        return showConfig.touchRectCalc.getTouchRect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()){
            //Do nothing on IDE preview.
            throw new IllegalStateException("This view should not use in XML, pls using show()");
        }
        if (showConfig.target == null){
            throw new NullPointerException("No target specified!");
        }

        // get current dimensions
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //check for init
        if (internalBitmap == null || internalCanvas == null || oldWidth != width || oldHeight != height) {
            if (internalBitmap!=null && (!internalBitmap.isRecycled())){
                internalBitmap.recycle();
            }

            //create internal bitmap and canvas object
            internalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            internalCanvas = new Canvas(internalBitmap);

            //save dimensions
            oldWidth = width;
            oldHeight = height;
        }

        // clear canvas
        internalCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        //draw mask layer
        internalCanvas.drawColor(showConfig.maskColor);

        //draw background img
        showConfig.backgroundDrawer.drawBackground(getContext(), internalCanvas,
                showConfig.target.getRectF());

        //draw alpha shape
        showConfig.targetDrawer.drawTarget(getContext(), internalCanvas, showConfig.target
                .getRectF().left, showConfig.target.getRectF().top, showConfig.shapePadding);

        //draw all on the final canvas
        canvas.drawBitmap(internalBitmap, 0, 0, null);
    }
}
