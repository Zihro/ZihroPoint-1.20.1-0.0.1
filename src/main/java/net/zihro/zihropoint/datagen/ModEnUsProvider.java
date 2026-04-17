package net.zihro.zihropoint.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.registration.ModBlocks;
import net.zihro.zihropoint.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public class ModEnUsProvider extends LanguageProvider {
    public ModEnUsProvider(PackOutput output) {
        super(output, ZihroPoint.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        //Tabs
        add("creativetab.zihropoint_items", "Zihro Point Items & Blocks");

        //Tooltips
        add("tooltip.zihropoint.ore_detector.tooltip", "Detects precious metals through solid rock.");



        //MACHINES
        add("block.zihropoint.solid_fuel_generator", "Solid Fuel Generator");

        //MACHINE UIS
        add("gui.zihropoint.energy_stored", "Energy Stored: %s FE");


        //BLOCKS
        add("block.zihropoint.rubber_log", "Rubber Log");
        add("block.zihropoint.rubber_wood", "Rubber Wood");
        add("block.zihropoint.stripped_rubber_log", "Stripped Rubber Log");
        add("block.zihropoint.stripped_rubber_wood", "Stripped Rubber Wood");
        add("block.zihropoint.rubber_planks", "Rubber Planks");
        add("block.zihropoint.rubber_leaves", "Rubber Leaves");



        // --- Metals with Blocks ---
        addMetalSet(ModBlocks.LEAD_ORE, ModItems.RAW_LEAD, ModItems.LEAD_INGOT, ModBlocks.LEAD_BLOCK, "Lead");


        // --- Metals without Blocks (Pass null for the block) ---
        addMetalSet(ModBlocks.DEEPSLATE_SILVER_ORE, ModItems.RAW_SILVER, ModItems.SILVER_INGOT, null, "Silver");
        addMetalSet(ModBlocks.DEEPSLATE_TITANIUM_ORE, ModItems.RAW_TITANIUM, ModItems.TITANIUM_INGOT, null, "Titanium");
        addMetalSet(ModBlocks.DEEPSLATE_PLATINUM_ORE, ModItems.RAW_PLATINUM, ModItems.PLATINUM_INGOT, null, "Platinum");
        addMetalSet(ModBlocks.TIN_ORE, ModItems.RAW_TIN, ModItems.TIN_INGOT, null, "Tin");
        addMetalSet(ModBlocks.NICKEL_ORE, ModItems.RAW_NICKEL, ModItems.NICKEL_INGOT, null, "Nickel");
        addMetalSet(ModBlocks.DEEPSLATE_ADAMANTIUM_ORE, ModItems.RAW_ADAMANTIUM, ModItems.ADAMANTIUM_INGOT, ModBlocks.ADAMANTIUM_BLOCK, "Adamantium");
        addMetalSet(ModBlocks.DEEPSLATE_VIBRANIUM_ORE, ModItems.RAW_VIBRANIUM, ModItems.VIBRANIUM_INGOT, ModBlocks.VIBRANIUM_BLOCK, "Vibranium");
        addMetalSet(ModBlocks.DEEPSLATE_ORICHALCUM_ORE, ModItems.RAW_ORICHALCUM, ModItems.ORICHALCUM_INGOT, ModBlocks.ORICHALCUM_BLOCK, "Orichalcum");




        // --- Specialty Items ---
        add(ModItems.ORE_DETECTOR.get(), "Ore Detector");
        add(ModItems.COTTON.get(), "Cotton");
        add(ModItems.COTTON_SEEDS.get(), "Cotton Seeds");



        add(ModItems.ORICHALCUM_PICKAXE.get(), "Orichalcum Pickaxe");
        add(ModItems.ORICHALCUM_SWORD.get(), "Orichalcum Sword");
        add(ModItems.ORICHALCUM_AXE.get(), "Orichalcum Axe");
        add(ModItems.ORICHALCUM_SHOVEL.get(), "Orichalcum Shovel");
        add(ModItems.ORICHALCUM_HOE.get(), "Orichalcum Hoe");




        //Special Cases
        addDustSet(ModBlocks.SULFUR_ORE,ModItems.SULFUR_DUST,"Sulfur");

        //foods and fuels
        add(ModItems.STRAWBERRY.get(), "Strawberry");
        add(ModItems.RAW_RESIN.get(), "Raw Resin");


    }

    /**
     * The Speed-Runner Helper:
     * Automatically names the Ore, Raw Item, Ingot, and Block.
     */
    private void addMetalSet(Supplier<? extends Block> ore, Supplier<? extends Item> raw,
                             Supplier<? extends Item> ingot, Supplier<? extends Block> block, String name) {

        add(ore.get(), name + " Ore");
        add(raw.get(), "Raw " + name);
        add(ingot.get(), name + " Ingot");

        // Only add the block translation if it's not null
        if (block != null) {
            add(block.get(), name + " Block");
        }
    }
    private void addTooltip(String key, String text) {
        add("tooltip." + ZihroPoint.MOD_ID + "." + key, text);
    }
    private void addDustSet(Supplier<? extends Block> ore, Supplier<? extends Item> dust, String name) {
        add(ore.get(), name + " Ore");
        add(dust.get(), name + " Dust");
    }


}