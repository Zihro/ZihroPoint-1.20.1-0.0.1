package net.zihro.zihropoint.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.registration.ModBlocks;
import net.zihro.zihropoint.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, ZihroPoint.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ModTags.Items.RUBBER_LOG)
                .add(ModBlocks.RUBBER_LOG.get().asItem(),
                        ModBlocks.STRIPPED_RUBBER_LOG.get().asItem(),
                        ModBlocks.RUBBER_WOOD.get().asItem(),
                        ModBlocks.STRIPPED_RUBBER_WOOD.get().asItem());
        this.tag(ItemTags.PLANKS).add(ModBlocks.RUBBER_PLANKS.get().asItem());

        this.tag(ItemTags.LOGS_THAT_BURN).addTag(ModTags.Items.RUBBER_LOG);


    }
}
