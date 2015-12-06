package org.mym.featuredshowcase;

import android.graphics.Color;

import org.mym.featuredshowcase.advanced.BackgroundDrawer;
import org.mym.featuredshowcase.advanced.DefaultTouchRect;
import org.mym.featuredshowcase.advanced.NoBackgroundDrawer;
import org.mym.featuredshowcase.advanced.TouchRectCalculator;
import org.mym.featuredshowcase.extension.ShowcaseListener;
import org.mym.featuredshowcase.shape.CircleShape;
import org.mym.featuredshowcase.shape.RectangleShape;
import org.mym.featuredshowcase.shape.TargetDrawer;
import org.mym.featuredshowcase.target.NothingTarget;
import org.mym.featuredshowcase.target.Padding;
import org.mym.featuredshowcase.target.Target;

/**
 * provides a Builder utility as described in Effective Java.
 */
public class ShowConfig {

    protected boolean adjustToTarget;
    protected boolean hideOnTouchOutside;
    protected int maskColor;
    protected Padding shapePadding;
    protected Target target;
    protected TargetDrawer targetDrawer;
    protected BackgroundDrawer backgroundDrawer;
    protected TouchRectCalculator touchRectCalc;
    protected ShowcaseListener showcaseListener;

    private ShowConfig(Builder builder) {
        adjustToTarget = builder.adjustToTarget;
        hideOnTouchOutside = builder.hideOnTouchOutside;
        maskColor = builder.maskColor;
        shapePadding = builder.shapePadding;
        target = builder.target;
        targetDrawer = builder.targetDrawer;
        backgroundDrawer = builder.backgroundDrawer;
        touchRectCalc = builder.touchRectCalc;
        showcaseListener = builder.showcaseListener;
    }


    private static TargetDrawer getDefaultTargetDrawer(){
        return new CircleShape();
    }

    private static int getDefaultMaskColor(){
        return Color.parseColor("#99000000");
    }

    private static Padding getDefaultShapePaddingPixel(){
        return new Padding(0);
    }

    private static Target getDefaultTarget(){
        return new NothingTarget();
    }

    private static BackgroundDrawer getDefaultBkgDrawer(){
        return new NoBackgroundDrawer();
    }

    private static TouchRectCalculator getDefaultTouchCalc(){
        return new DefaultTouchRect();
    }


    /**
     * {@code ShowConfig} builder static inner class.
     */
    public static final class Builder {
        private boolean adjustToTarget;
        private boolean hideOnTouchOutside;
        private int maskColor;
        private Padding shapePadding;
        private Target target;
        private TargetDrawer targetDrawer;
        private BackgroundDrawer backgroundDrawer;
        private TouchRectCalculator touchRectCalc;
        private ShowcaseListener showcaseListener;

        public Builder() {
            adjustToTarget = true;
            hideOnTouchOutside = true;
            maskColor = getDefaultMaskColor();
            shapePadding = getDefaultShapePaddingPixel();
            target = getDefaultTarget();
            targetDrawer = getDefaultTargetDrawer();
            backgroundDrawer = getDefaultBkgDrawer();
            touchRectCalc = getDefaultTouchCalc();
            showcaseListener = null;

        }

        /**
         * Sets the {@code adjustToTarget} and returns a reference to this Builder so that the
         * methods can be chained together.
         *
         * @param val the {@code adjustToTarget} to set
         * @return a reference to this Builder
         */
        public Builder adjustToTarget(boolean val) {
            adjustToTarget = val;
            return this;
        }

        /**
         * Sets the {@code hideOnTouchOutside} and returns a reference to this Builder so that
         * the methods can be chained together.
         *
         * @param val the {@code hideOnTouchOutside} to set
         * @return a reference to this Builder
         */
        public Builder hideOnTouchOutside(boolean val) {
            hideOnTouchOutside = val;
            return this;
        }

        /**
         * Sets the {@code maskColor} and returns a reference to this Builder so that the methods
         * can be chained together.
         *
         * @param val the {@code maskColor} to set
         * @return a reference to this Builder
         */
        public Builder maskColor(int val) {
            maskColor = val;
            return this;
        }

        /**
         * Sets the {@code shapePadding} and returns a reference to this Builder so that the
         * methods can be chained together.
         *
         * @param val the {@code shapePadding} to set
         * @return a reference to this Builder
         */
        public Builder shapePadding(Padding val) {
            shapePadding = val;
            return this;
        }

        /**
         * Sets the {@code target} and returns a reference to this Builder so that the methods
         * can be chained together.
         *
         * @param val the {@code target} to set
         * @return a reference to this Builder
         */
        public Builder target(Target val) {
            target = val;
            return this;
        }

        /**
         * Sets the {@code targetDrawer} and returns a reference to this Builder so that the
         * methods can be chained together.
         *
         * @param val the {@code targetDrawer} to set
         * @return a reference to this Builder
         */
        public Builder targetDrawer(TargetDrawer val) {
            targetDrawer = val;
            return this;
        }

        /**
         * Sets the {@code backgroundDrawer} and returns a reference to this Builder so that the
         * methods can be chained together.
         *
         * @param val the {@code backgroundDrawer} to set
         * @return a reference to this Builder
         */
        public Builder backgroundDrawer(BackgroundDrawer val) {
            backgroundDrawer = val;
            return this;
        }

        /**
         * Sets the {@code touchRectCalc} and returns a reference to this Builder so that the
         * methods can be chained together.
         *
         * @param val the {@code touchRectCalc} to set
         * @return a reference to this Builder
         */
        public Builder touchRectCalc(TouchRectCalculator val) {
            touchRectCalc = val;
            return this;
        }

        /**
         * Sets the {@code showcaseListener} and returns a reference to this Builder so that the
         * methods can be chained together.
         *
         * @param val the {@code showcaseListener} to set
         * @return a reference to this Builder
         */
        public Builder showcaseListener(ShowcaseListener val) {
            showcaseListener = val;
            return this;
        }

        /**
         * Returns a {@code ShowConfig} built from the parameters previously set.
         *
         * @return a {@code ShowConfig} built with parameters of this {@code ShowConfig.Builder}
         */
        public ShowConfig build() {
            return new ShowConfig(this);
        }
    }
}
