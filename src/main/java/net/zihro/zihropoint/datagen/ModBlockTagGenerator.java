package net.zihro.zihropoint.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.ModBlocks;
import org.jetbrains.annotations.Nullable;
import net.zihro.zihropoint.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {


    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ZihroPoint.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LEAD_ORE.get(),
                        ModBlocks.TIN_ORE.get(),
                        ModBlocks.NICKEL_ORE.get(),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                        ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                        ModBlocks.LEAD_BLOCK.get(),
                        ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_VIBRANIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get(),
                        ModBlocks.DEEPSLATE_ADAMANTIUM_ORE.get(),
                        ModBlocks.SULFUR_ORE.get()

                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LEAD_ORE.get(),
                        ModBlocks.TIN_ORE.get(),
                        ModBlocks.NICKEL_ORE.get(),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                        ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                        ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                        ModBlocks.LEAD_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DEEPSLATE_ORICHALCUM_ORE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SULFUR_ORE.get());

        this.tag(ModTags.Blocks.NEEDS_ORICHALCUM_TOOLS)
                .add(ModBlocks.DEEPSLATE_VIBRANIUM_ORE.get());

        this.tag(ModTags.Blocks.NEEDS_VIBRANIUM_TOOLS)
                .add(ModBlocks.DEEPSLATE_ADAMANTIUM_ORE.get());
    }
}
