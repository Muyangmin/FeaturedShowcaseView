package org.mym.featuredshowcase.advanced.layoutdrawer;

public final class BitmapItem {

    protected BitmapCreator bitmapCreator;
    protected LayoutParam layoutParam;

    public BitmapItem(BitmapCreator bitmapCreator, LayoutParam layoutParam) {
        this.bitmapCreator = bitmapCreator;
        this.layoutParam = layoutParam;
    }
}
