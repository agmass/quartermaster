package org.agmas.init;

import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.Item;
import org.agmas.init.tag.ModItemLists;

import net.minecraft.world.entity.npc.villager.VillagerProfession;
//? if <=1.21.11 {
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
//? } else {
/*import net.minecraft.world.item.trading.VillagerTrades;
*///? }

public class ModTrades {
    public static void init() {
        //? if <=1.21.11 {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 0, (factories)->{
            for (Item item : ModItemLists.golden_heavy) {
                factories.add(new VillagerTrades.EnchantedItemForEmeralds(item, 2, 3, 1));
            }
            for (Item item : ModItemLists.golden_light) {
                factories.add(new VillagerTrades.EnchantedItemForEmeralds(item, 2, 3, 1));
            }
            for (Item item : ModItemLists.iron_heavy) {
                factories.add(new VillagerTrades.EnchantedItemForEmeralds(item, 2, 3, 1));
            }
            for (Item item : ModItemLists.iron_light) {
                factories.add(new VillagerTrades.EnchantedItemForEmeralds(item, 2, 3, 1));
            }
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5, (factories)->{
            for (Item item : ModItemLists.diamond_light) {
                factories.add(new VillagerTrades.EnchantedItemForEmeralds(item, 2, 3, 1));
            }
        });
        //? }
    }
}
