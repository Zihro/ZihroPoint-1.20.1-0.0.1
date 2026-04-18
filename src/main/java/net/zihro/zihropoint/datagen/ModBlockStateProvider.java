package net.zihro.zihropoint.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.registration.ModBlocks;
import net.zihro.zihropoint.block.custom.CottonCropBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ZihroPoint.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Ores
        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.NICKEL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_PLATINUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SILVER_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TITANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_VIBRANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ADAMANTIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ORICHALCUM_ORE);
        blockWithItem(ModBlocks.SULFUR_ORE);


        //WOOD
        logBlock(((RotatedPillarBlock) ModBlocks.RUBBER_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.RUBBER_WOOD.get()), blockTexture(ModBlocks.RUBBER_LOG.get()),blockTexture(ModBlocks.RUBBER_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_RUBBER_LOG.get()), blockTexture(ModBlocks.STRIPPED_RUBBER_LOG.get()),
                new ResourceLocation(ZihroPoint.MOD_ID, "block/stripped_rubber_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_RUBBER_WOOD.get()), blockTexture(ModBlocks.STRIPPED_RUBBER_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_RUBBER_LOG.get()));

        blockItem(ModBlocks.RUBBER_LOG);
        blockItem(ModBlocks.RUBBER_WOOD);
        blockItem(ModBlocks.STRIPPED_RUBBER_LOG);
        blockItem(ModBlocks.STRIPPED_RUBBER_WOOD);

        blockWithItem(ModBlocks.RUBBER_PLANKS);

        leavesBlock(ModBlocks.RUBBER_LEAVES);



        
        

        //Mat Block
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.NICKEL_BLOCK);

        //Crops
        makeCottonCrop((CropBlock) ModBlocks.COTTON_CROP.get(), "cotton_stage", "cotton_stage");
        saplingBlock(ModBlocks.RUBBER_SAPLING);
    }



    private void saplingBlock(RegistryObject<Block> blockRegistryObject){
        simpleBlock(blockRegistryObject.get());
            models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),blockTexture(blockRegistryObject.get())).renderType("cutout");
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(ZihroPoint.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }


    public void makeCottonCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cottonStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cottonStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CottonCropBlock) block).getAgeProperty()),
                new ResourceLocation(ZihroPoint.MOD_ID, "block/" + textureName + state.getValue(((CottonCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
