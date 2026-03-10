package com.sprout.beemod.item;

import com.sprout.beemod.BeeMod;
import com.sprout.beemod.block.ModBlocks;
import com.sprout.beemod.item.food.ModConsumables;
import com.sprout.beemod.item.food.ModFoods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BeeMod.MOD_ID);

    public static final DeferredItem<Item> SOULCOMB = ITEMS.registerSimpleItem("soulcomb");
    public static final DeferredItem<Item> SOUL_NECTAR_BOTTLE = ITEMS.registerSimpleItem("soul_nectar_bottle",
            () -> new Item.Properties()
                    .stacksTo(16)
                    .craftRemainder(SOULCOMB.get())
                    .food(ModFoods.SOUL_NECTAR_BOTTLE, ModConsumables.SOUL_NECTAR_BOTTLE));

    public static final DeferredItem<BlockItem> SOUL_BEEHIVE = ITEMS.registerSimpleBlockItem(ModBlocks.SOUL_BEEHIVE);
    public static final DeferredItem<BlockItem> SOULBLOSSOM = ITEMS.registerSimpleBlockItem(ModBlocks.SOULBLOSSOM);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
