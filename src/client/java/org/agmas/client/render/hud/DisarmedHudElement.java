package org.agmas.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
//? >=26.1 {
/*import net.minecraft.client.gui.GuiGraphics;
*///? } else {
import net.minecraft.client.gui.GuiGraphics;
//? }
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import org.agmas.Quartermaster;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModAttributes;
import org.agmas.init.ModComponents;
import org.agmas.init.ModEffects;

import java.util.Random;

public class DisarmedHudElement implements HudElement {

    @Override
    //? >=26.1 {
    /*public void extractRenderState(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    *///? } else {
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    //?}
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        int y = guiGraphics.guiHeight() / 2 + 16;
        int x = guiGraphics.guiWidth() / 2;

        if (player.hasAttached(ModAttachments.CUTLASS_COMBO) && player.getMainHandItem().getComponents().has(ModComponents.COMBO_TO_DISABLE)) {
            guiGraphics.pose().pushMatrix();
            guiGraphics.pose().scale(1.25f);
            Component s = Component.literal(player.getAttached(ModAttachments.CUTLASS_COMBO).intValue() + "/" + player.getMainHandItem().getComponents().get(ModComponents.COMBO_TO_DISABLE).intValue()).withStyle(ChatFormatting.GRAY);
            guiGraphics.textRenderer().accept((int) (((x-Minecraft.getInstance().font.width(s)/2))/1.25), (int) (y/1.25),s);
            guiGraphics.pose().popMatrix();
        }
    }

}
