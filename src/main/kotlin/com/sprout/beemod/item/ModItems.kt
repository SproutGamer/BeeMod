package com.sprout.beemod.item

import com.sprout.beemod.BeeMod
import com.sprout.beemod.item.food.ModConsumables
import com.sprout.beemod.item.food.ModFoods
import net.minecraft.world.item.Items
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {
    val ITEMS = DeferredRegister.createItems(BeeMod.MODID)

    val SOULCOMB = ITEMS.registerSimpleItem("soulcomb")
    val SOUL_NECTAR_BOTTLE = ITEMS.registerSimpleItem("soul_nectar_bottle") { properties ->
        properties
            .stacksTo(16)
            .craftRemainder(Items.GLASS_BOTTLE)
            .food(
                ModFoods.SOUL_NECTAR_BOTTLE,
                ModConsumables.SOUL_NECTAR_BOTTLE
            )
    }

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}