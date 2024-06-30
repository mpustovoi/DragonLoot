package net.dragonloot.network;

import net.dragonloot.network.packet.AnvilPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class DragonLootServerPacket {

    public static void init() {
        PayloadTypeRegistry.playS2C().register(AnvilPacket.PACKET_ID, AnvilPacket.PACKET_CODEC);
    }
}
