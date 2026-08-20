package org.agmas.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.agmas.QMIdentifier;

public class ModGameRule {
    public static final GameRule<Boolean> WOUND_WHEN_ESTOC_UNCHARGED_BOOLEAN_GAMERULE = GameRuleBuilder
            .forBoolean(false)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(QMIdentifier.of("wound_when_estoc_uncharges").id);

    public static final GameRule<Boolean> ALLOW_ITEM_INTERACTIONS_WHEN_DISARMED = GameRuleBuilder
            .forBoolean(true)
            .category(GameRuleCategory.PLAYER)
            .buildAndRegister(QMIdentifier.of("allow_item_interactions_when_disarmed").id);

    public static void init() {}
}
