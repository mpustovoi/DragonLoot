package net.dragonloot.item;

import net.dragonloot.entity.DragonTridentEntity;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DragonTridentItem extends TridentItem {

    public DragonTridentItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return;
        }
        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        if (i < 10) {
            return;
        }
        float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, playerEntity);
        if (f > 0.0f && !playerEntity.isTouchingWaterOrRain()) {
            return;
        }
        if (isAboutToBreak(stack)) {
            return;
        }
        RegistryEntry<SoundEvent> registryEntry = EnchantmentHelper.getEffect(stack, EnchantmentEffectComponentTypes.TRIDENT_SOUND).orElse(SoundEvents.ITEM_TRIDENT_THROW);
        if (!world.isClient()) {
            stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));
            if (f == 0.0f) {
                DragonTridentEntity tridentEntity = new DragonTridentEntity(world, playerEntity, stack);
                tridentEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, 2.5f, 1.0f);
                if (playerEntity.isInCreativeMode()) {
                    tridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                }
                world.spawnEntity(tridentEntity);
                world.playSoundFromEntity(null, tridentEntity, registryEntry.value(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                if (!playerEntity.isInCreativeMode()) {
                    playerEntity.getInventory().removeOne(stack);
                }
            }
        }
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        if (f > 0.0f) {
            float g = playerEntity.getYaw();
            float h = playerEntity.getPitch();
            float j = -MathHelper.sin(g * ((float) Math.PI / 180)) * MathHelper.cos(h * ((float) Math.PI / 180));
            float k = -MathHelper.sin(h * ((float) Math.PI / 180));
            float l = MathHelper.cos(g * ((float) Math.PI / 180)) * MathHelper.cos(h * ((float) Math.PI / 180));
            float m = MathHelper.sqrt(j * j + k * k + l * l);
            playerEntity.addVelocity(j *= f / m, k *= f / m, l *= f / m);
            playerEntity.useRiptide(20, 8.0f, stack);
            if (playerEntity.isOnGround()) {
                playerEntity.move(MovementType.SELF, new Vec3d(0.0, 1.1999999284744263, 0.0));
            }
            world.playSoundFromEntity(null, playerEntity, registryEntry.value(), SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
    }

    private static boolean isAboutToBreak(ItemStack stack) {
        return stack.getDamage() >= stack.getMaxDamage() - 1;
    }
}
