package com.sprout.beemod.item

import com.sprout.beemod.BeeMod
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {
    val ITEMS = DeferredRegister.createItems(BeeMod.MODID)

    val SOULCOMB = ITEMS.registerSimpleItem("soulcomb")

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}