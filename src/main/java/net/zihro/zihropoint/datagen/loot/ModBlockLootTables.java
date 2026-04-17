package net.zihro.zihropoint.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.block.ModBlocks;
import net.zihro.zihropoint.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.LEAD_BLOCK.get());
        this.dropSelf(ModBlocks.NICKEL_BLOCK.get());


        //ORES
        this.add(ModBlocks.LEAD_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
        this.add(ModBlocks.NICKEL_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.NICKEL_ORE.get(), ModItems.RAW_NICKEL.get()));
        this.add(ModBlocks.TIN_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));

        this.add(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                block -> createIronLikeOreDrops(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),ModItems.RAW_PLATINUM.get()));
        this.add(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),
                block -> createIronLikeOreDrops(ModBlocks.DEEPSLATE_TITANIUM_ORE.get(),ModItems.RAW_TITANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                block -> createIronLikeOreDrops(ModBlocks.DEEPSLATE_SILVER_ORE.get(),ModItems.RAW_SILVER.get()));
    }
    protected LootTable.Builder createIronLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }


        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
}
