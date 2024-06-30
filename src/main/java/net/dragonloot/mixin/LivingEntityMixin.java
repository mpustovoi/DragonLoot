package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.dragonloot.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    protected int fallFlyingTicks;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tickFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V", ordinal = 0), cancellable = true)
    private void tickFallFlyingMixin(CallbackInfo info) {
        boolean bl = this.getFlag(7);
        if (bl && !this.isOnGround() && !this.hasVehicle() && ((LivingEntity) (Object) this).getEquippedStack(EquipmentSlot.CHEST).getItem() == ItemInit.UPGRADED_DRAGON_CHESTPLATE) {
            this.setFlag(7, true);
            info.cancel();
        }
    }

    @Inject(method = "tickFallFlying", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/LivingEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    private void tickFallFlyingSecondMixin(CallbackInfo info) {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
        if (itemStack.isOf(ItemInit.UPGRADED_DRAGON_CHESTPLATE)) {
            if (itemStack.getDamage() < itemStack.getMaxDamage() - 1) {
                int i = this.fallFlyingTicks + 1;
                if (!this.getWorld().isClient() && i % 10 == 0) {
                    int j = i / 10;
                    if (j % 2 == 0) {
                        itemStack.damage(1, (LivingEntity) (Object) this, EquipmentSlot.CHEST);
                    }
                    this.emitGameEvent(GameEvent.ELYTRA_GLIDE);
                }

            } else if ((Object) this instanceof LivingEntity livingEntity) {
                if (!this.getWorld().isClient()) {
                    itemStack = new ItemStack(Items.ELYTRA);
                    itemStack.setDamage(Items.ELYTRA.getDefaultStack().getMaxDamage());
                }
                if (!livingEntity.isSilent()) {
                    livingEntity.getWorld().playSound(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.ENTITY_ITEM_BREAK, livingEntity.getSoundCategory(), 0.8F, 0.8F + livingEntity.getWorld().getRandom().nextFloat() * 0.4F, false);
                }
            }
        }
    }

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot equipmentSlot);

}