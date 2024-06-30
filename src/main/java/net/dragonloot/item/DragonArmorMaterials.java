package net.dragonloot.item;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import net.dragonloot.init.ConfigInit;
import net.dragonloot.init.ItemInit;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class DragonArmorMaterials {

    public static final RegistryEntry<ArmorMaterial> DRAGON = register("dragonloot:dragon", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
        map.put(ArmorItem.Type.BOOTS, ConfigInit.CONFIG.dragon_armor_protection_boots);
        map.put(ArmorItem.Type.LEGGINGS, ConfigInit.CONFIG.dragon_armor_protection_leggings);
        map.put(ArmorItem.Type.CHESTPLATE, ConfigInit.CONFIG.dragon_armor_protection_chest);
        map.put(ArmorItem.Type.HELMET, ConfigInit.CONFIG.dragon_armor_protection_helmet);
        map.put(ArmorItem.Type.BODY, ConfigInit.CONFIG.dragon_armor_protection_chest);
    }), ConfigInit.CONFIG.dragon_armor_enchantability, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, ConfigInit.CONFIG.dragon_armor_toughness, ConfigInit.CONFIG.dragon_armor_knockback_resistance, () -> Ingredient.ofItems(ItemInit.DRAGON_SCALE));

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Identifier.of(id)));

        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            enumMap.put(type, defense.get(type));
        }
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, list, toughness, knockbackResistance));
    }

}
