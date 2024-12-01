package net.adamtwitty.adammod.item.custom;

import net.adamtwitty.adammod.config.AdamModCommonConfigs;
import net.adamtwitty.adammod.util.UtilFunctions;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class EnchantedBookItem extends Item {
    Random random = new Random();

    public EnchantedBookItem(Properties pProperties) {

        super(pProperties);
    }

    @Override
    public Component getName(ItemStack pStack) {
        CompoundTag rootTag = pStack.getTag();
        CompoundTag enchantmentTag = rootTag.getCompound("Enchantment");
        String enchantmentRaw = enchantmentTag.getString("id");
        String[] enchantmentInfo = enchantmentRaw.split(":");
        String enchantmentSource = enchantmentInfo[0];
        String enchantmentName = enchantmentInfo[1];
        Pair<String, ChatFormatting> enchantRarityInfo = getRarityInfo(enchantmentRaw);

        int enchantmentLvl = enchantmentTag.getInt("lvl");
        String romanLevel = Component.translatable("enchantment.level." + enchantmentLvl).getString();

        String enchantFormattedName = Component.translatable("enchantment." + enchantmentSource + "." + enchantmentName).getString();
        String rarityIcon = Component.translatable("enchantment.rarity." + enchantRarityInfo.getA()).getString();

        return Component.literal(rarityIcon + " ")
                .append(Component.literal(enchantFormattedName + " " + romanLevel)
                        .withStyle(enchantRarityInfo.getB()));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        assert pStack.getTag() != null;
        int maxWidthTooltip = 165;
        int successRate = pStack.getTag().getInt("SuccessRate");


        pTooltipComponents.add(Component.literal(" "));

        Component translatable = Component.translatable("enchantment.minecraft.fortune.description");
        String resolvedText = translatable.getString();
        List<String> splitText = UtilFunctions.wrapText(resolvedText, maxWidthTooltip);
          for (String line : splitText) {
              pTooltipComponents.add(Component.literal(line.trim()));
          }

        pTooltipComponents.add(Component.literal(" "));

        pTooltipComponents.add(Component.literal("Success Rate: ")
                .setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GREEN))  // Green for "Success Rate: "
                .append(Component.literal(successRate + "%")
                        .setStyle(Style.EMPTY.withColor(ChatFormatting.WHITE))));
          pTooltipComponents.add(Component.literal(" "));
          pTooltipComponents.add(Component.literal("→ ᴅʀᴀɢ ɴ ᴅʀᴏᴘ ᴏɴᴛᴏ ʏᴏᴜʀ").withStyle(ChatFormatting.GRAY));
          pTooltipComponents.add(Component.literal("ɪᴛᴇᴍ ᴛᴏ ᴀᴘᴘʟʏ ᴛʜɪꜱ ʙᴏᴏᴋ").withStyle(ChatFormatting.GRAY));
          pTooltipComponents.add(Component.literal(" "));


          pTooltipComponents.add(Component.translatable("enchantment.icon.pickaxe"));
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack pStack, Slot pSlot, ClickAction pAction, Player pPlayer) {

        ItemStack otherStack = pSlot.getItem();


        if (pAction == ClickAction.PRIMARY && otherStack.isEnchantable()) {
            Map<Enchantment, Integer> enchants = pStack.getAllEnchantments();

            pStack.shrink(1);
            pPlayer.playSound(SoundEvents.PLAYER_LEVELUP);
            return true;
        } else {
            return false;
        }
    }

    private Pair<String, ChatFormatting> getRarityInfo(String enchantmentRaw) {
        if (AdamModCommonConfigs.SIMPLE_ENCHANTMENTS.get().contains(enchantmentRaw)) {
            return new Pair<>("simple", ChatFormatting.WHITE);
        } else if (AdamModCommonConfigs.ELITE_ENCHANTMENTS.get().contains(enchantmentRaw)) {
            return new Pair<>("elite", ChatFormatting.AQUA);
        } else if (AdamModCommonConfigs.UNIQUE_ENCHANTMENTS.get().contains(enchantmentRaw)) {
            return new Pair<>("unique", ChatFormatting.GREEN);
        } else if (AdamModCommonConfigs.ULTIMATE_ENCHANTMENTS.get().contains(enchantmentRaw)) {
            return new Pair<>("ultimate", ChatFormatting.YELLOW);
        } else if (AdamModCommonConfigs.LEGENDARY_ENCHANTMENTS.get().contains(enchantmentRaw)) {
            return new Pair<>("legendary", ChatFormatting.GOLD);
        }
        return new Pair<>("simple", ChatFormatting.WHITE);
    }
}
