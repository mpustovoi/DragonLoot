package net.dragonloot.network.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record AnvilPacket(int entityId, String blockString) implements CustomPayload {

    public static final CustomPayload.Id<AnvilPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of("dragonloot", "anvil_packet"));

    public static final PacketCodec<RegistryByteBuf, AnvilPacket> PACKET_CODEC = PacketCodec.of((value, buf) -> {
        buf.writeInt(value.entityId);
        buf.writeString(value.blockString);
    }, buf -> new AnvilPacket(buf.readInt(), buf.readString()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

}
