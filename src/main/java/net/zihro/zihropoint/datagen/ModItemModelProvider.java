package net.zihro.zihropoint.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ZihroPoint.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        //FOODS
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.RAW_RESIN);



        //items and tools
        simpleItem(ModItems.ORE_DETECTOR);

        handheldItem(ModItems.ORICHALCUM_SHOVEL);
        handheldItem(ModItems.ORICHALCUM_PICKAXE);
        handheldItem(ModItems.ORICHALCUM_AXE);
        handheldItem(ModItems.ORICHALCUM_SWORD);
        handheldItem(ModItems.ORICHALCUM_HOE);



        //raw ores
        simpleItem(ModItems.RAW_LEAD);
        simpleItem(ModItems.RAW_NICKEL);
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.RAW_SILVER);
        simpleItem(ModItems.RAW_TITANIUM);
        simpleItem(ModItems.RAW_PLATINUM);
        simpleItem(ModItems.SULFUR_DUST);
        simpleItem(ModItems.RAW_ADAMANTIUM);
        simpleItem(ModItems.RAW_ORICHALCUM);
        simpleItem(ModItems.RAW_VIBRANIUM);


        //ingots
        simpleItem(ModItems.LEAD_INGOT);
        simpleItem(ModItems.NICKEL_INGOT);
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.SILVER_INGOT);
        simpleItem(ModItems.TITANIUM_INGOT);
        simpleItem(ModItems.PLATINUM_INGOT);
        simpleItem(ModItems.VIBRANIUM_INGOT);
        simpleItem(ModItems.ORICHALCUM_INGOT);
        simpleItem(ModItems.ADAMANTIUM_INGOT);

        //MISC
        simpleItem(ModItems.COTTON);
        simpleItem(ModItems.COTTON_SEEDS);

    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ZihroPoint.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ZihroPoint.MOD_ID,"item/" + item.getId().getPath()));
    }
}
