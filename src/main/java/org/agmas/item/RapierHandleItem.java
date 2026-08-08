package org.agmas.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.agmas.init.ModItems;

public class RapierHandleItem extends Item {
    public RapierHandleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());

        if (player != null) {
            if (state.is(Blocks.GRINDSTONE)) {
                if (stack.is(ModItems.RUINED_HANDLE)) {
                    if (player.isShiftKeyDown()) {
                        stack.shrink(1);
                        player.addItem(new ItemStack(ModItems.RELIC_HANDLE));
                    }
                }
            }
        }
        return super.useOn(context);
    }
}
