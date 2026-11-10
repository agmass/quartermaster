package org.agmas.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
//? if <26.1 {
/*import net.minecraft.client.gui.GuiGraphics;
*///? } else {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//? }
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.agmas.porting.QMIdentifier;
import org.agmas.item.EstocItem;

import java.awt.*;

/**
 * @author Chemthunder
 * Ported to Mojmaps
 */
public class WoundHudElement implements HudElement {

    @Override
    //? >=26.1 {
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
    //? } else {
    /*public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    *///?}
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        int y = guiGraphics.guiHeight() / 2 + 28;
        int x = guiGraphics.guiWidth() / 2;


        if (player.getMainHandItem().getItem() instanceof EstocItem && Minecraft.getInstance().crosshairPickEntity != null) {
            if (Minecraft.getInstance().crosshairPickEntity.distanceTo(player) > 2 && player.getAttackStrengthScale(0f) > 0.5f) {
                guiGraphics.pose().pushMatrix();
                guiGraphics.pose().translate(x, y);
                guiGraphics.blitSprite(
                        RenderPipelines.GUI_TEXTURED,
                        QMIdentifier.of("hud/wound_indicator").id,
                        -8,
                        -8,
                        16,
                        16
                );
                guiGraphics.pose().popMatrix();
            }
        }
    }

}
