package net.darkhax.tips.client;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.darkhax.tips.data.tip.ITip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.MathHelper;

/**
 * Handles the layout and rendering of tips on the screen.
 */
public final class TipRenderer {

    private static final float MAXIMUM_WIDTH_PERCENTAGE = 0.35f;
    private static final int SCREEN_MARGIN = 10;
    private static final int TITLE_PADDING = 3;
    private static final int TEXT_COLOR = 0xffffff;

    private TipRenderer() {

    }

    /**
     * Renders a tip in the configured screen corner.
     *
     * @param matrixStack The active render matrix.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param tip The tip to render.
     * @param corner The corner in which to render the tip.
     */
    public static void render (MatrixStack matrixStack, int screenWidth, int screenHeight, ITip tip, TipCorner corner) {

        final FontRenderer font = Minecraft.getInstance().fontRenderer;
        final int textWidth = MathHelper.floor(screenWidth * MAXIMUM_WIDTH_PERCENTAGE);
        final List<IReorderingProcessor> titleLines = font.trimStringToWidth(tip.getTitle(), textWidth);
        final List<IReorderingProcessor> textLines = font.trimStringToWidth(tip.getText(), textWidth);
        final int lineHeight = font.FONT_HEIGHT + 1;
        final int contentHeight = (titleLines.size() + textLines.size()) * lineHeight + TITLE_PADDING;
        int y = corner.getY(screenHeight, contentHeight, SCREEN_MARGIN);

        y = renderLines(matrixStack, font, titleLines, screenWidth, y, lineHeight, corner);
        renderLines(matrixStack, font, textLines, screenWidth, y + TITLE_PADDING, lineHeight, corner);
    }

    private static int renderLines (MatrixStack matrixStack, FontRenderer font, List<IReorderingProcessor> lines, int screenWidth, int y, int lineHeight, TipCorner corner) {

        for (final IReorderingProcessor line : lines) {

            final int lineWidth = font.func_243245_a(line);
            final int x = corner.getX(screenWidth, lineWidth, SCREEN_MARGIN);
            font.func_238422_b_(matrixStack, line, x, y, TEXT_COLOR);
            y += lineHeight;
        }

        return y;
    }
}
