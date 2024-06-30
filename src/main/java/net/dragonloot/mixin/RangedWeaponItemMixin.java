package net.dragonloot.mixin;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.dragonloot.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;

@Mixin(RangedWeaponItem.class)
public class RangedWeaponItemMixin {

    @Inject(method = "shootAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/RangedWeaponItem;shoot(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/projectile/ProjectileEntity;IFFFLnet/minecraft/entity/LivingEntity;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    protected void shootAllMixin(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack, List<ItemStack> projectiles, float speed, float divergence, boolean critical, @Nullable LivingEntity target, CallbackInfo info, float f, float g, float h, float i, int j, ItemStack itemStack, float k, ProjectileEntity projectileEntity) {
        if (projectileEntity instanceof PersistentProjectileEntity persistentProjectileEntity) {
            Item item = shooter.getStackInHand(hand).getItem();
            if (item == ItemInit.DRAGON_CROSSBOW) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * 1.25f + 1f);
            } else if (item == ItemInit.DRAGON_BOW) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() * 1.25f + 1f);
            }
        }
    }
}
