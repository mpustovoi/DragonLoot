package net.dragonloot.network;

import net.dragonloot.access.DragonAnvilInterface;
import net.dragonloot.network.packet.AnvilPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;

public class DragonLootClientPacket {

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(AnvilPacket.PACKET_ID, (payload, context) -> {
            int entityId = payload.entityId();
            String blockString = payload.blockString();
            context.client().execute(() -> {
                if (context.player().getWorld().getEntityById(entityId) instanceof PlayerEntity playerEntity) {
                    if (playerEntity.currentScreenHandler instanceof AnvilScreenHandler) {
                        ((DragonAnvilInterface) playerEntity.currentScreenHandler).setDragonAnvil(blockString);
                    }

                }
            });
        });
    }

}
