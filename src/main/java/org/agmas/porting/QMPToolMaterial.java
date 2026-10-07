package org.agmas.porting;
//? if <=1.21.1
//import net.minecraft.world.item.Tier;
//? if <=1.21.1
//import net.minecraft.world.item.Tiers;
//? if >1.21.1
import net.minecraft.world.item.ToolMaterial;

public class QMPToolMaterial {
    //? if <=1.21.1 {
    /*public Tier toolMaterial;

    public static QMPToolMaterial WOOD = new QMPToolMaterial(Tiers.WOOD);
    public static QMPToolMaterial STONE = new QMPToolMaterial(Tiers.STONE);
    public static QMPToolMaterial IRON = new QMPToolMaterial(Tiers.IRON);
    public static QMPToolMaterial DIAMOND = new QMPToolMaterial(Tiers.DIAMOND);
    public static QMPToolMaterial GOLD = new QMPToolMaterial(Tiers.GOLD);
    public static QMPToolMaterial NETHERITE = new QMPToolMaterial(Tiers.NETHERITE);

    public QMPToolMaterial(Tier toolMaterial) {
        this.toolMaterial = toolMaterial;
    }
    *///? } else {
    

    public static QMPToolMaterial WOOD = new QMPToolMaterial(ToolMaterial.WOOD);
    public static QMPToolMaterial STONE = new QMPToolMaterial(ToolMaterial.STONE);
    public static QMPToolMaterial IRON = new QMPToolMaterial(ToolMaterial.IRON);
    public static QMPToolMaterial COPPER = new QMPToolMaterial(ToolMaterial.COPPER);
    public static QMPToolMaterial DIAMOND = new QMPToolMaterial(ToolMaterial.DIAMOND);
    public static QMPToolMaterial GOLD = new QMPToolMaterial(ToolMaterial.GOLD);
    public static QMPToolMaterial NETHERITE = new QMPToolMaterial(ToolMaterial.NETHERITE);

    public ToolMaterial toolMaterial;
    public QMPToolMaterial(ToolMaterial toolMaterial) {
        this.toolMaterial = toolMaterial;
    }
    //? }

    public float attackDamageBonus() {
        //? if >1.21.1
        return toolMaterial.attackDamageBonus();
        //? if <=1.21.1
        //return toolMaterial.getAttackDamageBonus();
    }


    public int enchantability() {
        //? if >1.21.1
        return toolMaterial.enchantmentValue();
        //? if <=1.21.1
        //return toolMaterial.getEnchantmentValue();
    }

    public int durability() {
        //? if >1.21.1
        return toolMaterial.durability();
        //? if <=1.21.1
        //return toolMaterial.getUses();
    }


}
