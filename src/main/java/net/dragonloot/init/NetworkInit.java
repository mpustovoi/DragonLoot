package net.dragonloot.init;

import net.dragonloot.network.DragonLootClientPacket;
import net.dragonloot.network.DragonLootServerPacket;

public class NetworkInit {

    public static void serverInit() {
        DragonLootServerPacket.init();
    }

    public static void clientInit() {
        DragonLootClientPacket.init();
    }

}
