package net.zihro.zihropoint.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.ModBlocks;
import net.zihro.zihropoint.item.ModItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.RAW_LEAD.get(),
            ModBlocks.LEAD_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        //ORES
        registerMetalSet(pWriter, ModItems.RAW_SILVER.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.SILVER_INGOT.get(),null ,"silver");
        registerMetalSet(pWriter, ModItems.RAW_TITANIUM.get(), ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModItems.TITANIUM_INGOT.get(),null, "titanium");
        registerMetalSet(pWriter, ModItems.RAW_PLATINUM.get(), ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), ModItems.PLATINUM_INGOT.get(),null, "platinum");
        registerMetalSet(pWriter, ModItems.RAW_TIN.get(), ModBlocks.TIN_ORE.get(), ModItems.TIN_INGOT.get(), null,"tin");
        registerMetalSet(pWriter, ModItems.RAW_LEAD.get(), ModBlocks.LEAD_ORE.get(), ModItems.LEAD_INGOT.get(), ModBlocks.LEAD_BLOCK.get(),"lead");
        registerMetalSet(pWriter, ModItems.RAW_NICKEL.get(), ModBlocks.NICKEL_ORE.get(), ModItems.NICKEL_INGOT.get(),null ,"nickel");

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  ZihroPoint.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }


    protected static void registerMetalSet(Consumer<FinishedRecipe> pWriter, ItemLike raw, ItemLike ore, ItemLike ingot, @Nullable ItemLike block, String name) {
        // 1. Setup Smelting/Blasting (Always happens)
        List<ItemLike> meltables = List.of(raw, ore);
        oreSmelting(pWriter, meltables, RecipeCategory.MISC, ingot, 0.25f, 200, name);
        oreBlasting(pWriter, meltables, RecipeCategory.MISC, ingot, 0.25f, 100, name);

        // 2. Only generate Block recipes IF the block exists
        if (block != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                    .pattern("SSS").pattern("SSS").pattern("SSS")
                    .define('S', ingot)
                    .unlockedBy(getHasName(ingot), has(ingot))
                    // REMOVE the custom name here:
                    .save(pWriter);

// 4. Shapeless: 1 Block -> 9 Ingots
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                    .requires(block)
                    .unlockedBy(getHasName(block), has(block))
                    // You only need a custom name if the ID would conflict with another recipe
                    .save(pWriter, ZihroPoint.MOD_ID + ":" + getItemName(ingot) + "_from_" + name + "_block");
        }
    }
}