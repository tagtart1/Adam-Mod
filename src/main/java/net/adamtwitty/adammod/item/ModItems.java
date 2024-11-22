package net.adamtwitty.adammod.item;

import net.adamtwitty.adammod.AdamMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AdamMod.MOD_ID);

    public static final RegistryObject<Item> CARMELO_COIN = ITEMS.register("carmelocoin", () -> new Item(new Item.Properties()));


    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
