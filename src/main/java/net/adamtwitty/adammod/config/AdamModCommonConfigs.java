package net.adamtwitty.adammod.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdamModCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> SIMPLE_ENCHANTMENTS;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ELITE_ENCHANTMENTS;

    static {
        // Simple rarity builder default config
        BUILDER.push("Configs for Adam Mod");
        BUILDER.comment("Configs for Simple Rarity Enchantments").push("simple");
        SIMPLE_ENCHANTMENTS = BUILDER.comment("Enchants that can appear at this rarity")
                        .defineList("enchantments", Arrays.asList("minecraft:smite", "minecraft:aqua_affinity"),
                                s -> s instanceof String);
        BUILDER.pop();

        // Elite rarity builder default config
        BUILDER.comment("Configs for Elite Rarity Enchantments").push("elite");
        ELITE_ENCHANTMENTS = BUILDER.comment("Enchants that can appear at this rarity")
                .defineList("enchantments", Arrays.asList("minecraft:fortune", "minecraft:looting"),
                        s -> s instanceof String);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
