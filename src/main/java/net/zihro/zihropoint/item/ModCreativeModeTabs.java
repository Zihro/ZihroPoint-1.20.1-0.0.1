package net.zihro.zihropoint.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ZihroPoint.MOD_ID);

        public static final RegistryObject<CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("zihropoint_items",
                ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.RAW_NICKEL.get()))
                        .title(Component.translatable("creativetab.zihropoint_items"))
                        .displayItems((pParameters, pOutput) -> {
                            pOutput.accept(ModItems.NICKEL_INGOT.get());
                            pOutput.accept(ModItems.RAW_NICKEL.get());
                            pOutput.accept(ModItems.RAW_LEAD.get());
                            pOutput.accept(ModItems.LEAD_INGOT.get());
                            pOutput.accept(ModBlocks.NICKEL_BLOCK.get());
                            pOutput.accept(ModBlocks.LEAD_BLOCK.get());


                        })
                                .build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
