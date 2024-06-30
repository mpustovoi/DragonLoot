package net.dragonloot.init;

import net.dragonloot.DragonLootMain;
import net.dragonloot.entity.model.DragonElytraEntityModel;
import net.dragonloot.entity.render.DragonTridentEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class RenderInit {

    public static final EntityModelLayer DRAGON_ELYTRA_LAYER = new EntityModelLayer(DragonLootMain.ID("dragon_elytra_render_layer"), "dragon_elytra_render_layer");

    public static void init() {
        EntityRendererRegistry.register(EntityInit.DRAGON_TRIDENT_ENTITY, DragonTridentEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DRAGON_ELYTRA_LAYER, DragonElytraEntityModel::getTexturedModelData);
    }
}