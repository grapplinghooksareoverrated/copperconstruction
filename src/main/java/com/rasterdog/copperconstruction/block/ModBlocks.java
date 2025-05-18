package com.rasterdog.copperconstruction.block;

import com.rasterdog.copperconstruction.CopperConstruction;
import com.rasterdog.copperconstruction.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    // Append this mod's blocks to the greater list of blocks in Minecraft
    public static final DeferredRegister.Blocks BLOCKS =
                DeferredRegister.createBlocks(CopperConstruction.MOD_ID);

    // <Block Definitions>
    public static final DeferredBlock<Block> DRAFTWOOL_BLOCK = registerBlock("draftwool_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0f).explosionResistance(0f).sound(SoundType.WOOL)));
    // </Block Definitions>

    // uhhh it's important
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Automatically creates a new item that the player can hold in his inventory/access from the creative menu
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
