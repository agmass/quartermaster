package org.agmas.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
//? >=26.1 {
/*import net.minecraft.client.gui.GuiGraphicsExtractor;
*///? } else {
import net.minecraft.client.gui.GuiGraphics;
//? }
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.entity.player.Player;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModItems;

/**
 * @author Chemthunder
 * Ported to Mojmaps
 */
public class ParryHudElement implements HudElement {

    @Override
    //? >=26.1 {
    /*public void extractRenderState(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
    *///? } else {
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    //?}
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (player.getUseItem().is(ModItems.RAPIER)) {
            guiGraphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    QMIdentifier.of("hud/rapier_parry_indicator").id,
                    guiGraphics.guiWidth() / 2 - 9,
                    guiGraphics.guiHeight() / 2 - 20,
                    16,
                    16
            );
        }
    }
}
