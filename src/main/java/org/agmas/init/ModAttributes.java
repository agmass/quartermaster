package org.agmas.init;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.agmas.QMIdentifier;

public class ModAttributes {

    public static final Holder<Attribute> STUN_TIME = register(
            "stun_time", new RangedAttribute("attribute.name.stun_time", 1.0, 0.0, 1024.0).setSyncable(true).setSentiment(Attribute.Sentiment.NEGATIVE)
    );
    public static final Holder<Attribute> FROST_TIME = register(
            "frost_time", new RangedAttribute("attribute.name.frost_time", 1.0, 0.0, 1024.0).setSyncable(true).setSentiment(Attribute.Sentiment.NEGATIVE)
    );

    private static Holder<Attribute> register(String string, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, QMIdentifier.of(string).id, attribute);
    }

    public static void init() {}
}
