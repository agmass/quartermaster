package org.agmas.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
//? if <26.1 {
import net.minecraft.client.gui.GuiGraphics;
//? } else {
/*import net.minecraft.client.gui.GuiGraphicsExtractor;
*///? }
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.duck.PlayerAcessor;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModComponents;
import org.agmas.init.ModEffects;
import org.agmas.item.EstocItem;

import java.awt.*;
import java.util.Random;

/**
 * @author Chemthunder
 * Ported to Mojmaps
 */
public class WoundHudElement implements HudElement {

    @Override
    //? >=26.1 {
    /*public void extractRenderState(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker) {
    *///? } else {
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    //?}
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        int y = guiGraphics.guiHeight() / 2 + 28;
        int x = guiGraphics.guiWidth() / 2;

        int i = ((PlayerAcessor)player).quartermaster$getEstocWoundChanceTicks();

        if (i > 0 && player.getMainHandItem().getItem() instanceof EstocItem) {
            guiGraphics.pose().pushMatrix();
            guiGraphics.pose().translate(x,y);
            guiGraphics.pose().scale(Mth.lerp(EasingType.IN_QUAD.apply(i/20f), 1f, 2f));
            guiGraphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    QMIdentifier.of("hud/wound_indicator").id,
                    -8,
                    -8,
                    16,
                    16,
                    new Color(255,255,255,(int)Mth.lerp(EasingType.IN_QUAD.apply(i/20f), 0, 255)).getRGB()
            );
            guiGraphics.pose().popMatrix();
        }
    }

}
