package net.dragonloot.init;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModelProviderInit {

    public static void init() {
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_BOW, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItem() != stack ? 0.0F : (float) (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_BOW, Identifier.ofVanilla("pulling"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW, Identifier.ofVanilla("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0;
            } else
                return CrossbowItem.isCharged(itemStack) ? 0.0F : (float) (itemStack.getMaxUseTime(livingEntity) - livingEntity.getItemUseTimeLeft()) / (float) CrossbowItem.getPullTime(itemStack, livingEntity);
        });
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW, Identifier.ofVanilla("pulling"), (itemStack, clientWorld1, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW, Identifier.ofVanilla("charged"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_CROSSBOW, Identifier.ofVanilla("firework"), (stack, world, entity, seed) -> {
            ChargedProjectilesComponent chargedProjectilesComponent = stack.get(DataComponentTypes.CHARGED_PROJECTILES);
            return chargedProjectilesComponent != null && chargedProjectilesComponent.contains(Items.FIREWORK_ROCKET) ? 1.0f : 0.0f;
        });

        ModelPredicateProviderRegistry.register(ItemInit.DRAGON_TRIDENT, Identifier.ofVanilla("throwing"), (itemStack, clientWorld, livingEntity, seed) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F);
    }
}