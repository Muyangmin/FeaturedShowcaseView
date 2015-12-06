package org.mym.featuredshowcase.target;

/**
 * wrapper class of padding; it is immutable.
 */
public class Padding {
    public final int paddingLeft;
    public final int paddingTop;
    public final int paddingRight;
    public final int paddingBottom;

    public Padding(int padding) {
        this(padding, padding, padding, padding);
    }

    public Padding(int paddingHorizontal, int paddingVertical) {
        this(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
    }

    public Padding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.paddingBottom = paddingBottom;
        this.paddingRight = paddingRight;
        this.paddingTop = paddingTop;
        this.paddingLeft = paddingLeft;
    }
}
