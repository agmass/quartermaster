package org.agmas.init.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;

public class ModTags {
    public static final TagKey<Item> CUTLASSES = TagKey.create(Registries.ITEM, QMIdentifier.of("cutlasses").id);
    public static final TagKey<Item> MORNINGSTARS = TagKey.create(Registries.ITEM, QMIdentifier.of("morningstars").id);
    public static final TagKey<Item> ESTOCS = TagKey.create(Registries.ITEM, QMIdentifier.of("estocs").id);
    public static final TagKey<Item> GREATAXES = TagKey.create(Registries.ITEM, QMIdentifier.of("greataxes").id);
    public static final TagKey<Item> WARHAMMERS = TagKey.create(Registries.ITEM, QMIdentifier.of("warhammers").id);

    public static final TagKey<Enchantment> RAPIER_ENCHANTABLE = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("cutlasses").id);

    public static final TagKey<Item> WOODEN_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("wooden_light_weapons").id);
    public static final TagKey<Item> WOODEN_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("wooden_heavy_weapons").id);

    public static final TagKey<Item> STONE_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("stone_light_weapons").id);
    public static final TagKey<Item> STONE_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("stone_heavy_weapons").id);

    public static final TagKey<Item> COPPER_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("copper_light_weapons").id);
    public static final TagKey<Item> COPPER_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("copper_heavy_weapons").id);

    public static final TagKey<Item> IRON_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("iron_light_weapons").id);
    public static final TagKey<Item> IRON_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("iron_heavy_weapons").id);

    public static final TagKey<Item> GOLDEN_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("golden_light_weapons").id);
    public static final TagKey<Item> GOLDEN_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("golden_heavy_weapons").id);

    public static final TagKey<Item> DIAMOND_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("diamond_light_weapons").id);
    public static final TagKey<Item> DIAMOND_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("diamond_heavy_weapons").id);

    public static final TagKey<Item> NETHERITE_LIGHT_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("netherite_light_weapons").id);
    public static final TagKey<Item> NETHERITE_HEAVY_WEAPONS = TagKey.create(Registries.ITEM, QMIdentifier.of("netherite_heavy_weapons").id);


    public static final TagKey<Enchantment> EXCLUSIVE_SET_SPEARS = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/spears").id);
    public static final TagKey<Enchantment> EXCLUSIVE_SET_SHIELD_DISABLE = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/shield_disable").id);
    public static final TagKey<Enchantment> EXCLUSIVE_SET_GREATAXE = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/greataxe").id);
    public static final TagKey<Enchantment> EXCLUSIVE_SET_WARHAMMER = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/warhammer").id);
    public static final TagKey<Enchantment> EXCLUSIVE_SET_KNOCKBACK = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/knockback").id);
    public static final TagKey<Enchantment> EXCLUSIVE_SET_FALL_PROTECTION = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/fall_prot").id);
    public static final TagKey<Enchantment> EXCLUSIVE_SET_SHIELD = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("exclusive_set/shield").id);
    public static final TagKey<Enchantment> YOU_TWO_SHOULD_JUST_KISS_ALREADY = TagKey.create(Registries.ENCHANTMENT, QMIdentifier.of("you_two_should_just_kiss_already").id);

    public static final TagKey<DamageType> IS_MAGIC = TagKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("is_magic").id);
    public static final TagKey<DamageType> IS_IMPACT = TagKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("is_impact").id);

    public static final TagKey<MobEffect> IS_STUN = TagKey.create(Registries.MOB_EFFECT, QMIdentifier.of("is_impact").id);

    public static void init() {}
}
