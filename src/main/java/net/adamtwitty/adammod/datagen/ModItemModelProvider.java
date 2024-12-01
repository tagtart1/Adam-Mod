package net.adamtwitty.adammod.datagen;

import net.adamtwitty.adammod.AdamMod;
import net.adamtwitty.adammod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Item;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AdamMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.CARMELO_COIN);
        simpleItem(ModItems.LARA_COIN);
        simpleItem(ModItems.METAL_DETECTOR);
        dynamicItem(ModItems.ENCHANTED_BOOK);
        textureOnly("elite");
        textureOnly("simple");
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AdamMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder textureOnly(String path) {
        return withExistingParent((path),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(AdamMod.MOD_ID, "item/" + path));

    }


    private ItemModelBuilder.OverrideBuilder dynamicItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(AdamMod.MOD_ID, "item/" + "simple"))
                .override()
                    .predicate(new ResourceLocation(AdamMod.MOD_ID, "book_rarity"),1)
                    .model(new ModelFile.UncheckedModelFile(new ResourceLocation(AdamMod.MOD_ID, "item/simple")))
                .end()
                .override()
                    .predicate(new ResourceLocation(AdamMod.MOD_ID, "book_rarity"),2)
                    .model(new ModelFile.UncheckedModelFile(new ResourceLocation(AdamMod.MOD_ID, "item/elite")));



    }
}
