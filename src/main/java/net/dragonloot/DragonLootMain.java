package net.dragonloot;

import net.dragonloot.init.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class DragonLootMain implements ModInitializer {

    public static Identifier ID(String path) {
        return Identifier.of(MOD_ID, path);
    }

    private static final String MOD_ID = "dragonloot";

    @Override
    public void onInitialize() {
        ConfigInit.init();
        BlockInit.init();
        EntityInit.init();
        ItemInit.init();
        TagInit.init();
        NetworkInit.serverInit();
    }

}
