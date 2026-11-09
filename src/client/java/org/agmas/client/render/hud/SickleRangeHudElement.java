package org.agmas.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.agmas.item.EstocItem;
import org.agmas.item.SicklesItem;
import org.agmas.porting.QMIdentifier;

import java.awt.*;

public class SickleRangeHudElement implements HudElement {

    @Override
    //? >=26.1 {
    /*public void extractRenderState(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
    *///? } else {
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    //?}
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        int y = guiGraphics.guiHeight() / 2 - 7 + 16;
        int x = (guiGraphics.guiWidth() / 2) - 8;


        if (player.getMainHandItem().getItem() instanceof SicklesItem && Minecraft.getInstance().crosshairPickEntity instanceof LivingEntity victim) {
            if (player.getMainHandItem().is(player.getOffhandItem().getItem())) {
                float f = SicklesItem.calculateFalloff(player,victim);
                guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("hud/crosshair_attack_indicator_progress"), 16, 4, 0, 0, x, y, (int)(f*17), 4, Color.RED.getRGB());
            }
        }
    }

}
