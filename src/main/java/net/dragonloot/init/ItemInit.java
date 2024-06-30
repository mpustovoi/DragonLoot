package net.dragonloot.init;

import net.dragonloot.DragonLootMain;
import net.dragonloot.compat.recipes.CompatRecipes;
import net.dragonloot.compat.recipes.RecipeGenerator;
import net.dragonloot.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemInit {

    public static final RegistryKey<ItemGroup> DRAGON_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, DragonLootMain.ID("dragonloot"));

    // Items
    public static final Item DRAGON_SCALE = register("dragon_scale", new Item(new Item.Settings().fireproof()));
    public static final Item DRAGON_HORSE_ARMOR = register("dragon_horse_armor", new AnimalArmorItem(DragonArmorMaterials.DRAGON, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1).fireproof()));

    public static final Item UPGRADED_DRAGON_CHESTPLATE = register("upgraded_dragon_chestplate", new ArmorItem(DragonArmorMaterials.DRAGON, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(ConfigInit.CONFIG.dragon_armor_durability_multiplier)).fireproof()));

    public static final Item DRAGON_HELMET = register("dragon_helmet", new ArmorItem(DragonArmorMaterials.DRAGON, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ConfigInit.CONFIG.dragon_armor_durability_multiplier)).fireproof()));
    public static final Item DRAGON_CHESTPLATE = register("dragon_chestplate", new ArmorItem(DragonArmorMaterials.DRAGON, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(ConfigInit.CONFIG.dragon_armor_durability_multiplier)).fireproof()));
    public static final Item DRAGON_LEGGINGS = register("dragon_leggings", new ArmorItem(DragonArmorMaterials.DRAGON, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(ConfigInit.CONFIG.dragon_armor_durability_multiplier)).fireproof()));
    public static final Item DRAGON_BOOTS = register("dragon_boots", new ArmorItem(DragonArmorMaterials.DRAGON, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(ConfigInit.CONFIG.dragon_armor_durability_multiplier)).fireproof()));

    public static final Item DRAGON_SWORD = register("dragon_sword", new SwordItem(DragonToolMaterials.DRAGON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(DragonToolMaterials.DRAGON, 3, -2.4f)).fireproof()));
    public static final Item DRAGON_SHOVEL = register("dragon_shovel", new ShovelItem(DragonToolMaterials.DRAGON, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(DragonToolMaterials.DRAGON, 1.5f, -3.0f)).fireproof()));
    public static final Item DRAGON_PICKAXE = register("dragon_pickaxe", new PickaxeItem(DragonToolMaterials.DRAGON, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(DragonToolMaterials.DRAGON, 1.0f, -2.8f)).fireproof()));
    public static final Item DRAGON_AXE = register("dragon_axe", new AxeItem(DragonToolMaterials.DRAGON, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(DragonToolMaterials.DRAGON, 5.0f, -3.0f)).fireproof()));
    public static final Item DRAGON_HOE = register("dragon_hoe", new HoeItem(DragonToolMaterials.DRAGON, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(DragonToolMaterials.DRAGON, -4f, -2.0f)).fireproof()));

    public static final Item DRAGON_BOW = register("dragon_bow", new BowItem(new Item.Settings().fireproof().maxDamage(DragonToolMaterials.DRAGON.getDurability())));
    public static final Item DRAGON_CROSSBOW = register("dragon_crossbow", new CrossbowItem(new Item.Settings().fireproof().maxDamage(DragonToolMaterials.DRAGON.getDurability())));

    public static final Item DRAGON_TRIDENT = register("dragon_trident", new DragonTridentItem(new Item.Settings().rarity(Rarity.EPIC).maxDamage(DragonToolMaterials.DRAGON.getDurability()).attributeModifiers(TridentItem.createAttributeModifiers()).component(DataComponentTypes.TOOL, TridentItem.createToolComponent()).fireproof()));

    private static Item register(String id, Item item) {
        return register(DragonLootMain.ID(id), item);
    }

    private static Item register(Identifier id, Item item) {
        ItemGroupEvents.modifyEntriesEvent(DRAGON_ITEM_GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, DRAGON_ITEM_GROUP, FabricItemGroup.builder().entries((context, entries) -> entries.add(BlockInit.DRAGON_ANVIL_BLOCK)).icon(() -> new ItemStack(ItemInit.DRAGON_SCALE)).displayName(Text.translatable("itemGroup.dragonloot.dragonloot")).build());

        CompatRecipes.loadRecipes();
        RecipeGenerator.addRecipes();
    }
}
