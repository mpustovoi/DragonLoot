package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Mixin(ItemRenderer.class)
public interface ItemRendererInterface {
    @Invoker("getModels")
    ItemModels getModelsInvoker();

    @Invoker("RenderBakedItemModel")
    void renderBakedItemModelInvoker(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices,
            VertexConsumer vertexConsumer4);
}