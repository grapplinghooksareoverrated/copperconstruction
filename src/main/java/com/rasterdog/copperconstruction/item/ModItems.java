package com.rasterdog.copperconstruction.item;

import com.rasterdog.copperconstruction.CopperConstruction;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    // Tells Minecraft to include this set of items under this mod ID
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CopperConstruction.MOD_ID);

    // Add Copper Wand
    public static final DeferredItem<Item> COPPERWAND = ITEMS.register(
        "copperwand"
        , () -> new Item( new Item.Properties() )
    );

    // Add Projection Kit
    public static final DeferredItem<Item> PROJECTIONKIT = ITEMS.register(
            "projectionkit"
            , () -> new Item( new Item.Properties() )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
