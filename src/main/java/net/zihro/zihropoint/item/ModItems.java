package net.zihro.zihropoint.item;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zihro.zihropoint.ZihroPoint;
import net.zihro.zihropoint.item.custom.OreDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ZihroPoint.MOD_ID);


    //raw
    public static final RegistryObject<Item> RAW_NICKEL =ITEMS.register("raw_nickel",
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


    //Custom Items
    public static final RegistryObject<Item> ORE_DETECTOR =ITEMS.register("ore_detector",
            () -> new OreDetectorItem(new Item.Properties().durability(128)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }


}
