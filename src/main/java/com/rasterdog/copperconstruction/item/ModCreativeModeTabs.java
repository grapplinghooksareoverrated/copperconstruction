package com.rasterdog.copperconstruction.item;

import com.rasterdog.copperconstruction.CopperConstruction;
import com.rasterdog.copperconstruction.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CopperConstruction.MOD_ID);

    public static final Supplier<CreativeModeTab> COPPERCONSTRUCTION_ITEMS_TAB = CREATIVE_MODE_TAB.register(
      "copperconstruction_items_tab",
      () -> CreativeModeTab.builder()
              .icon( () -> new ItemStack(ModItems.COPPERWAND.get()) )
              .title( Component.translatable("creativetab.copperconstruction.copperconstruction") )
              .displayItems((itemDisplayParameters, output) -> {
                  output.accept(ModItems.COPPERWAND);
                  output.accept(ModItems.PROJECTIONKIT);
                  output.accept(ModBlocks.DRAFTWOOL_BLOCK);
              }).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
