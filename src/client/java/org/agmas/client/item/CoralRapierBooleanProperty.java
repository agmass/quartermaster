package org.agmas.client.item;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModComponents;
import org.jspecify.annotations.Nullable;

public record CoralRapierBooleanProperty() implements ConditionalItemModelProperty {
    public static final Identifier ID = QMIdentifier.of("coral_rapier").id;
    public static final MapCodec<CoralRapierBooleanProperty> CODEC = MapCodec.unit(CoralRapierBooleanProperty::new);

    @Override
    public MapCodec<? extends ConditionalItemModelProperty> type() {
        return CODEC;
    }

    @Override
    public boolean get(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i, ItemDisplayContext itemDisplayContext) {
        return itemStack.has(ModComponents.IS_CORAL) && itemStack.get(ModComponents.IS_CORAL).booleanValue();
    }
}
