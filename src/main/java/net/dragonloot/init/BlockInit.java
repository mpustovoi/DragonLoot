package net.dragonloot.init;

import net.dragonloot.block.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockInit {

    public static final DragonAnvilBlock DRAGON_ANVIL_BLOCK = new DragonAnvilBlock(AbstractBlock.Settings.copy(Blocks.ANVIL));

    public static void init() {
        Registry.register(Registries.ITEM, Identifier.of("dragonloot", "dragon_anvil"), new BlockItem(DRAGON_ANVIL_BLOCK, new Item.Settings()));
        Registry.register(Registries.BLOCK, Identifier.of("dragonloot", "dragon_anvil"), DRAGON_ANVIL_BLOCK);
    }

}
