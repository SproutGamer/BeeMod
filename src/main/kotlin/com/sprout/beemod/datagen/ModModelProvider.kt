package com.sprout.beemod.datagen

import com.sprout.beemod.BeeMod
import com.sprout.beemod.item.ModItems
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.ModelProvider
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.core.Holder
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import java.util.stream.Stream

class ModModelProvider(output: PackOutput) : ModelProvider(output, BeeMod.MODID) {

    override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {
        itemModels.generateFlatItem(ModItems.SOULCOMB.get(), ModelTemplates.FLAT_ITEM)
    }

    override fun getKnownItems(): Stream<out Holder<Item>> {
        return ModItems.ITEMS.entries.stream()
    }

}