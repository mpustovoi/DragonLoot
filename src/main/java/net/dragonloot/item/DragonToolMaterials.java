package net.dragonloot.item;

import net.dragonloot.init.ConfigInit;
import net.dragonloot.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class DragonToolMaterials implements ToolMaterial {

    public static final ToolMaterial DRAGON = new DragonToolMaterials(BlockTags.INCORRECT_FOR_WOODEN_TOOL, ConfigInit.CONFIG.dragon_item_durability_multiplier, ConfigInit.CONFIG.dragon_item_mining_speed_multiplier, ConfigInit.CONFIG.dragon_item_base_damage, ConfigInit.CONFIG.dragon_tool_enchantability, Ingredient.ofItems(ItemInit.DRAGON_SCALE), "dragon");

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Ingredient repairIngredient;
    private final String name;

    private DragonToolMaterials(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Ingredient repairIngredient, String name) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
        this.name = name;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
