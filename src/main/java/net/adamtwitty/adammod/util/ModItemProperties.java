package net.adamtwitty.adammod.util;

import net.adamtwitty.adammod.AdamMod;
import net.adamtwitty.adammod.config.AdamModCommonConfigs;
import net.adamtwitty.adammod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;
import java.util.Map;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.ENCHANTED_BOOK.get(),
                new ResourceLocation(AdamMod.MOD_ID, "book_rarity" ),
                (itemStack, clientLevel, livingEntity, i) -> {
                    CompoundTag nbt = itemStack.getTag();
                    float rarity = 0f;
                    assert nbt != null;
                    CompoundTag enchantmentTag = nbt.getCompound("Enchantment");
                    String enchantment = enchantmentTag.getString("id");

                    // TODO: MAKE THE OTHER CONFIG FOR OTHER RARITES AND CODE THAT SHIT IN

                    if (AdamModCommonConfigs.SIMPLE_ENCHANTMENTS.get().contains(enchantment)) {
                        rarity = 1f;
                    } else if (AdamModCommonConfigs.UNIQUE_ENCHANTMENTS.get().contains(enchantment)) {
                        rarity = 2f;
                    } else if (AdamModCommonConfigs.ELITE_ENCHANTMENTS.get().contains(enchantment)) {
                        rarity = 3f;
                    } else if (AdamModCommonConfigs.ULTIMATE_ENCHANTMENTS.get().contains(enchantment)) {
                        rarity = 4f;
                    } else if (AdamModCommonConfigs.LEGENDARY_ENCHANTMENTS.get().contains(enchantment)) {
                        rarity = 5f;
                    }
                    return rarity;
                });
    }
}
