package com.sprout.beemod.effect

import com.sprout.beemod.BeeMod
import net.minecraft.core.registries.BuiltInRegistries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModEffects {

    val EFFECTS = DeferredRegister.create(
        BuiltInRegistries.MOB_EFFECT, BeeMod.MODID
    )

    val SOULBOUND = EFFECTS.register("soulbound") { _ -> SoulboundEffect() }

    fun register(eventBus: IEventBus) {
        EFFECTS.register(eventBus)
    }

}