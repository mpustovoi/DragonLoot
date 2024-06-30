package net.dragonloot.compat.recipes;

import net.dragonloot.DragonLootMain;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CompatRecipes {

    private static final Identifier NETHERITE_UPGRADE_TEMPLATE = Identifier.of("minecraft:netherite_upgrade_smithing_template");

    public static void loadRecipes() {

        if (FabricLoader.getInstance().isModLoaded("netherite_plus")) {
            RecipeGenerator.SMITHING_RECIPES.put("dragon_horse_armor", new RecipeMaterial(Identifier.of("netherite_plus", "netherite_horse_armor"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_horse_armor"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_trident", new RecipeMaterial(Identifier.of("netherite_plus", "netherite_trident"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_trident"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_bow", new RecipeMaterial(Identifier.of("netherite_plus", "netherite_bow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_bow"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_crossbow", new RecipeMaterial(Identifier.of("netherite_plus", "netherite_crossbow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_crossbow"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("upgraded_dragon_chestplate", new RecipeMaterial(Identifier.of("dragonloot", "dragon_chestplate"), Identifier.of("netherite_plus", "netherite_elytra"), "item", "item", DragonLootMain.ID("upgraded_dragon_chestplate"), NETHERITE_UPGRADE_TEMPLATE));
        } else {
            RecipeGenerator.SMITHING_RECIPES.put("dragon_horse_armor", new RecipeMaterial(Identifier.of("minecraft", "diamond_horse_armor"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_horse_armor"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_trident", new RecipeMaterial(Identifier.of("minecraft", "trident"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_trident"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_bow", new RecipeMaterial(Identifier.of("minecraft", "bow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_bow"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_crossbow", new RecipeMaterial(Identifier.of("minecraft", "crossbow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_crossbow"), NETHERITE_UPGRADE_TEMPLATE));
            RecipeGenerator.SMITHING_RECIPES.put("upgraded_dragon_chestplate", new RecipeMaterial(Identifier.of("dragonloot", "dragon_chestplate"), Identifier.of("minecraft", "elytra"), "item", "item", DragonLootMain.ID("upgraded_dragon_chestplate"), NETHERITE_UPGRADE_TEMPLATE));

        }

    }
}

// {"type":"minecraft:smithing","base":{"item":"dragonloot:dragon_chestplate"},"addition":{"item":"minecraft:elytra"},"result":{"item":"dragonloot:upgraded_dragon_chestplate"}}
