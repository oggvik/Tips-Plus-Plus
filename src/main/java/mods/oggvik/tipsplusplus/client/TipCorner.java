package mods.oggvik.tipsplusplus.client;

/**
 * A screen corner that can anchor rendered tips.
 */
public enum TipCorner {

    TOP_LEFT(false, false),
    TOP_RIGHT(false, true),
    BOTTOM_LEFT(true, false),
    BOTTOM_RIGHT(true, true);

    private final boolean bottom;
    private final boolean right;

    TipCorner(boolean bottom, boolean right) {

        this.bottom = bottom;
        this.right = right;
    }

    /**
     * Gets the horizontal position of content anchored to this corner.
     *
     * @param screenWidth The width of the screen.
     * @param contentWidth The width of the content.
     * @param margin The distance between the content and screen edge.
     * @return The horizontal content position.
     */
    public int getX (int screenWidth, int contentWidth, int margin) {

        return this.right ? screenWidth - margin - contentWidth : margin;
    }

    /**
     * Gets the vertical position of content anchored to this corner.
     *
     * @param screenHeight The height of the screen.
     * @param contentHeight The height of the content.
     * @param margin The distance between the content and screen edge.
     * @return The vertical content position.
     */
    public int getY (int screenHeight, int contentHeight, int margin) {

        return this.bottom ? screenHeight - margin - contentHeight : margin;
    }
}
