package net.zihro.zihropoint.block.registration;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.custom.ResinExtractorBlock;
import net.zihro.zihropoint.block.custom.SolidFuelGeneratorBlock;
import net.zihro.zihropoint.block.entity.ResinExtractorBlockEntity;
import net.zihro.zihropoint.block.entity.SolidFuelGeneratorBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZihroPoint.MOD_ID);

    public static final RegistryObject<BlockEntityType<SolidFuelGeneratorBlockEntity>> SOLID_FUEL_GENERATOR =
            BLOCK_ENTITIES.register("solid_fuel_generator",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new SolidFuelGeneratorBlockEntity(
                                    (SolidFuelGeneratorBlock) ModBlocks.SOLID_FUEL_GENERATOR.get(), // Cast to specific block class
                                    pos,
                                    state
                            ),
                            ModBlocks.SOLID_FUEL_GENERATOR.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<ResinExtractorBlockEntity>> RESIN_EXTRACTOR =
            BLOCK_ENTITIES.register("resin_extractor",
                    () -> BlockEntityType.Builder.of(
                            (pos, state) -> new ResinExtractorBlockEntity(
                                    (ResinExtractorBlock) ModBlocks.RESIN_EXTRACTOR.get(), // Cast to specific block class
                                    pos,
                                    state
                            ),
                            ModBlocks.RESIN_EXTRACTOR.get()
                    ).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}