package net.zihro.zihropoint.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.item.ModItems;
import net.zihro.zihropoint.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider{
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, ZihroPoint.MOD_ID);
    }

    @Override
    protected void start() {

        add("sulfur_from_creep", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build() }, ModItems.SULFUR_DUST.get()));

        add("ore_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build() }, ModItems.ORE_DETECTOR.get()));


    }

}
