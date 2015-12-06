package org.mym.featuredshowcase.advanced.layoutdrawer;

public final class LayoutParam {
    protected static final int TYPE_ABSOLUTE = 0x1001;
    protected static final int TYPE_RELATIVE_EDGE = 0x2001;
    protected static final int TYPE_RELATIVE_POINT = 0x2002;

    public static final int EDGE_LEFT = 0;
    public static final int EDGE_TOP = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_BOTTOM = 3;
    private static final int EDGE_INVALID = -1;

    protected int type;
    protected float pointX;
    protected float pointY;
    protected int edge;
    protected float marginEdge;
    protected float xOffset;
    protected float yOffset;

    /**
     * Absolutely start at (x, y).
     */
    public static LayoutParam newAbsoluteLayoutParam(float x, float y) {
        return new LayoutParam(TYPE_ABSOLUTE, x, y, EDGE_INVALID, 0, 0, 0);
    }

    /**
     * Center aligned with an edge, and an margin is allowed.
     * @param edge must be one of {@link #EDGE_LEFT}, {@link #EDGE_TOP}, {@link #EDGE_RIGHT},
     * {@link #EDGE_BOTTOM}.
     * @param margin a margin from the edge.
     */
    public static LayoutParam newRelativeToEdgeParam(int edge, float margin) {
        return new LayoutParam(TYPE_RELATIVE_EDGE, 0, 0, edge, margin, 0, 0);
    }

    /**
     * Offset from specified point(pointX, pointY) with offset(xOffset, yOffset).
     */
    public static LayoutParam newRelativeToPointParam(float pointX, float pointY, float xOffset,
                                                      float yOffset) {
        return new LayoutParam(TYPE_RELATIVE_POINT, pointX, pointY, EDGE_INVALID, 0, xOffset,
                yOffset);
    }

    private LayoutParam(int type, float pointX, float pointY, int edge, float marginEdge,
                        float xOffset, float yOffset) {
        this.type = type;
        this.pointX = pointX;
        this.pointY = pointY;
        this.edge = edge;
        this.marginEdge = marginEdge;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}