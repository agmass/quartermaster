package org.agmas.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.agmas.QMIdentifier;

public class ModGameRule {
    public static final GameRule<Boolean> WOUND_WHEN_ESTOC_UNCHARGED_BOOLEAN_GAMERULE = GameRuleBuilder
            .forBoolean(false)
            .category(GameRuleCategory.MISC)
            .buildAndRegister(QMIdentifier.of("wound_when_estoc_uncharges").id);

    public static void init() {}
}
