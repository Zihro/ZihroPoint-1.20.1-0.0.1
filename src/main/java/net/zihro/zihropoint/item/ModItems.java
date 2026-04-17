package net.zihro.zihropoint.item;


import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.block.registration.ModBlocks;
import net.zihro.zihropoint.item.custom.FuelItem;
import net.zihro.zihropoint.item.custom.OreDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZihroPoint.MOD_ID);


    //raw
    public static final RegistryObject<Item> RAW_NICKEL =ITEMS.register("raw_nickel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFUR_DUST =ITEMS.register("sulfur_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LEAD =ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SILVER =ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TIN =ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PLATINUM =ITEMS.register("raw_platinum",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TITANIUM =ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_VIBRANIUM =ITEMS.register("raw_vibranium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ORICHALCUM =ITEMS.register("raw_orichalcum",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ADAMANTIUM =ITEMS.register("raw_adamantium",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RESIN =ITEMS.register("raw_resin",
            () -> new FuelItem(new Item.Properties(), 100));

    //TOOLS
    public static final RegistryObject<Item> ORICHALCUM_SWORD =ITEMS.register("orichalcum_sword",
            () -> new SwordItem(ModToolTiers.ORICHALCUM,9,2,new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_PICKAXE =ITEMS.register("orichalcum_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ORICHALCUM,6,2,new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_AXE =ITEMS.register("orichalcum_axe",
            () -> new AxeItem(ModToolTiers.ORICHALCUM,11,4,new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_SHOVEL =ITEMS.register("orichalcum_shovel",
            () -> new ShovelItem(ModToolTiers.ORICHALCUM,5,2,new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_HOE =ITEMS.register("orichalcum_hoe",
            () -> new HoeItem(ModToolTiers.ORICHALCUM,4,2,new Item.Properties()));


    //ingots
    public static final RegistryObject<Item> LEAD_INGOT =ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NICKEL_INGOT =ITEMS.register("nickel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT =ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT =ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TITANIUM_INGOT =ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_INGOT =ITEMS.register("platinum_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VIBRANIUM_INGOT =ITEMS.register("vibranium_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORICHALCUM_INGOT =ITEMS.register("orichalcum_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ADAMANTIUM_INGOT =ITEMS.register("adamantium_ingot",
            () -> new Item(new Item.Properties()));


    //Custom Items
    public static final RegistryObject<Item> ORE_DETECTOR =ITEMS.register("ore_detector",
            () -> new OreDetectorItem(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> STRAWBERRY =ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> COTTON_SEEDS =ITEMS.register("cotton_seeds",
            () -> new ItemNameBlockItem(ModBlocks.COTTON_CROP.get(), new Item.Properties()));

    //Machines
    public static final RegistryObject<Item> SOLID_FUEL_GENERATOR = ITEMS.register("solid_fuel_generator",
            () -> new BlockItem(ModBlocks.SOLID_FUEL_GENERATOR.get(), new Item.Properties()));

    //MISC
    public static final RegistryObject<Item> COTTON =ITEMS.register("cotton",
            () -> new Item(new Item.Properties()));






    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
