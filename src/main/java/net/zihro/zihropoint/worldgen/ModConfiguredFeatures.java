package net.zihro.zihropoint.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.registration.ModBlocks;

import java.util.List;
import java.util.TimerTask;

public class ModConfiguredFeatures {
    // 1. Define the ResourceKey for your ore
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_NICKEL_ORE_KEY = registerKey("nickel_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_ORE_KEY = registerKey("lead_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("silver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TITANIUM_ORE_KEY = registerKey("titanium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PLATINUM_ORE_KEY = registerKey("platinum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SULFUR_ORE_KEY = registerKey("sulfur_ore");

    /*public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ADAMANTIUM_ORE_KEY = registerKey("adamantium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ORICHALCUM_ORE_KEY = registerKey("orichalcum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_VIBRANIUM_ORE_KEY = registerKey("vibranium_ore");*/

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        // 2. Define what blocks can be replaced (Stone, Deepslate, etc.)
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);


        //STONE ORES
        List<OreConfiguration.TargetBlockState> tinTarget = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_TIN_ORE_KEY, Feature.ORE, new OreConfiguration(tinTarget, 8));

        List<OreConfiguration.TargetBlockState> leadTarget = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.LEAD_BLOCK.get().defaultBlockState()));
        register(context, OVERWORLD_LEAD_ORE_KEY, Feature.ORE, new OreConfiguration(leadTarget, 7));

        List<OreConfiguration.TargetBlockState> nickelTarget = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.NICKEL_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_NICKEL_ORE_KEY, Feature.ORE, new OreConfiguration(nickelTarget, 5));


        //DEEPSLATE ORES
        List<OreConfiguration.TargetBlockState> silverTarget = List.of(
                OreConfiguration.target(deepslateReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(silverTarget, 4));

        List<OreConfiguration.TargetBlockState> platinumTarget = List.of(
                OreConfiguration.target(deepslateReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_PLATINUM_ORE_KEY, Feature.ORE, new OreConfiguration(platinumTarget, 4));

        List<OreConfiguration.TargetBlockState> titaniumTarget = List.of(
                OreConfiguration.target(deepslateReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_TITANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(titaniumTarget, 3));

        //NETHER ORES
        List<OreConfiguration.TargetBlockState> netherSulfurOre = List.of(
                OreConfiguration.target(netherReplaceables, ModBlocks.SULFUR_ORE.get().defaultBlockState()));
        register(context, NETHER_SULFUR_ORE_KEY, Feature.ORE, new OreConfiguration(netherSulfurOre, 11));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ZihroPoint.MOD_ID, name));
    }

    private static <FC extends net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                                                                                    ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}