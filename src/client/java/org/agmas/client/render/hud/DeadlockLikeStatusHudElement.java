package org.agmas.client.render.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import org.agmas.init.ModEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeadlockLikeStatusHudElement implements HudElement {

    private static List<RenderState> effectStates = new ArrayList<>();

    public void addEffect(Holder<MobEffect> effectHolder) {
        effectStates.add(new RenderState(effectHolder));
    }
    @Override
    //? >=26.1 {
    /*public void extractRenderState(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    *///? } else {
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
    //?}
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        int y = guiGraphics.guiHeight() / 2 + 16;
        int x;

        for (RenderState effectState : effectStates) {
            x = guiGraphics.guiWidth() / 2;
            if (player.hasEffect(effectState.effectHolder)) {
                if (effectState.maxTime  == -1) {
                    effectState.maxTime = player.getEffect(effectState.effectHolder).getDuration();
                }
                if (effectState.lastTime < player.getEffect(effectState.effectHolder).getDuration()) {
                    effectState.maxTime = player.getEffect(effectState.effectHolder).getDuration();
                }
                String disarmedText = Language.getInstance().getOrDefault(effectState.effectHolder.value().getDescriptionId()).toUpperCase();
                int w = Minecraft.getInstance().font.width(disarmedText)*2 + (disarmedText.length()*3);
                x -= (w)/2;
                float tickDelta = (player.tickCount%2) + deltaTracker.getGameTimeDeltaPartialTick(false);
                Random tickRandom = new Random(player.tickCount/2);
                Random nextRandom = new Random((player.tickCount/2)+1);
                tickDelta /= 3.0f;

                float progress = (float) ((float) player.getEffect(effectState.effectHolder).getDuration() / effectState.maxTime);
                int hS = guiGraphics.guiWidth()/2;
                for (char c : disarmedText.toCharArray()) {
                    guiGraphics.pose().pushMatrix();

                    float xChange = Mth.lerp(tickDelta,tickRandom.nextInt(-4,4),nextRandom.nextInt(-4,4));
                    float yChange = Mth.lerp(tickDelta,tickRandom.nextInt(-4,4),nextRandom.nextInt(-4,4));

                    guiGraphics.pose().scale(2);
                    float angle = Mth.lerp(tickDelta,tickRandom.nextFloat(-8,8),nextRandom.nextFloat(-8,8));

                    angle = (float) Math.toRadians(angle);
                    guiGraphics.pose().pushMatrix();
                    guiGraphics.pose().rotateAbout(angle, (float) Math.round(x + xChange) /2, (float) Math.round(y + yChange) /2);

                    guiGraphics.textRenderer().accept(Math.round(x+xChange)/2,Math.round(y+yChange)/2, Component.literal(String.valueOf(c)));
                    guiGraphics.pose().popMatrix();

                    guiGraphics.enableScissor(0, 0, ((int) Mth.lerp(progress,hS-(w/2f),hS+(w/2f)))/2,guiGraphics.guiHeight());

                    guiGraphics.pose().pushMatrix();
                    guiGraphics.pose().rotateAbout(angle, (float) Math.round(x + xChange) /2, (float) Math.round(y + yChange) /2);
                    guiGraphics.textRenderer().accept(Math.round(x+xChange)/2,Math.round(y+yChange)/2, Component.literal(String.valueOf(c)).withColor(effectState.effectHolder.value().getColor()));
                    guiGraphics.pose().popMatrix();

                    guiGraphics.disableScissor();


                    guiGraphics.pose().popMatrix();

                    x += (Minecraft.getInstance().font.width(String.valueOf(c))*2)+3;
                }
                effectState.lastTime = player.getEffect(effectState.effectHolder).getDuration();
                y += 24;
            }
        }
    }

    class RenderState {
        public Holder<MobEffect> effectHolder = ModEffects.DISARMED;
        public int maxTime = 0;
        public int lastTime = 0;
        
        public RenderState(Holder<MobEffect> effectHolder) {
            this.effectHolder = effectHolder;
        }
    }
}
