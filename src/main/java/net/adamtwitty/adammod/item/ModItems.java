package net.adamtwitty.adammod.item;

import net.adamtwitty.adammod.AdamMod;
import net.adamtwitty.adammod.item.custom.EnchantedBookItem;
import net.adamtwitty.adammod.item.custom.MetalDetectorItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AdamMod.MOD_ID);

    public static final RegistryObject<Item> CARMELO_COIN = ITEMS.register("carmelocoin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector", () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> ENCHANTED_BOOK = ITEMS.register("enchanted_book", () -> new EnchantedBookItem(new Item.Properties()));

    public static final RegistryObject<Item> LARA_COIN = ITEMS.register("lara_coin", () -> new Item(new Item.Properties()));

    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}