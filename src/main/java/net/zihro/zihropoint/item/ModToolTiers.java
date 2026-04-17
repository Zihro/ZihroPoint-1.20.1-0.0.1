package net.zihro.zihropoint.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier ORICHALCUM = TierSortingRegistry.registerTier(
            new ForgeTier(5,1500,5f,4f,25,
                    ModTags.Blocks.NEEDS_ORICHALCUM_TOOLS, () -> Ingredient.of(ModItems.ORICHALCUM_INGOT.get())),
            new ResourceLocation(ZihroPoint.MOD_ID,"orichalcum"), List.of(Tiers.DIAMOND),List.of());

    public static final Tier VIBRANIUM = TierSortingRegistry.registerTier(
            new ForgeTier(6,1800,6f,6f,25,
                    ModTags.Blocks.NEEDS_VIBRANIUM_TOOLS, () -> Ingredient.of(ModItems.VIBRANIUM_INGOT.get())),
            new ResourceLocation(ZihroPoint.MOD_ID,"vibranium"), List.of(Tiers.NETHERITE),List.of());

}
