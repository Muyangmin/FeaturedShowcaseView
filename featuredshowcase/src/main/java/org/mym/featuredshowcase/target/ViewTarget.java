package org.mym.featuredshowcase.target;

import android.graphics.RectF;
import android.view.View;

public class ViewTarget implements Target {
    private View view;

    public ViewTarget(View view) {
        this.view = view;
    }

    @Override
    public RectF getRectF() {

        //This is relative position, we need absolute position for component in nested layouts!
//        return new RectF(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());

        int[] locationInWindow = new int[2];
        view.getLocationInWindow(locationInWindow);
        return new RectF(locationInWindow[0], locationInWindow[1],
                locationInWindow[0] + view.getWidth(),
                locationInWindow[1] + view.getHeight());
    }
}
