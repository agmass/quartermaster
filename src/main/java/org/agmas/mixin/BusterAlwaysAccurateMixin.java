package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.init.ModAttachments;
import org.agmas.item.EstocItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ProjectileUtil.class)
public abstract class BusterAlwaysAccurateMixin {

    @WrapMethod(method = "computeMargin")
    private static float changeMargin(Entity entity, Operation<Float> original) {
        return entity instanceof GreataxeProjectileEntity ? 1f : original.call(entity);
    }


}
