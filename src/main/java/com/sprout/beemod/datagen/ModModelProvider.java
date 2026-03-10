package com.sprout.beemod.datagen;

import com.sprout.beemod.BeeMod;
import com.sprout.beemod.block.ModBlocks;
import com.sprout.beemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {


    public ModModelProvider(PackOutput output) {
        super(output, BeeMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.SOULCOMB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SOUL_NECTAR_BOTTLE.get(), ModelTemplates.FLAT_ITEM);

        blockModels.createBeeNest(ModBlocks.SOUL_BEEHIVE.get(), TextureMapping::orientableCubeSameEnds);
        this.createFlower(blockModels, ModBlocks.SOULBLOSSOM.get(), ModBlocks.POTTED_SOULBLOSSOM.get());
    }

    private void createFlower(BlockModelGenerators blockModels, Block flower, Block pottedFlower) {
        blockModels.createTrivialBlock(flower, TexturedModel.createDefault(
                TextureMapping::cross,
                ModelTemplates.CROSS.extend().renderType("cutout").build()
        ));
        blockModels.createTrivialBlock(pottedFlower, TexturedModel.createDefault(
                TextureMapping::plant,
                ModelTemplates.FLOWER_POT_CROSS.extend().renderType("cutout").build()
        ));
    }

    @Override
    protected @NonNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected @NonNull Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }
}
