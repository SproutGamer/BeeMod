package com.sprout.beemod

import com.mojang.logging.LogUtils
import com.sprout.beemod.effect.ModEffects
import com.sprout.beemod.item.ModItems
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import org.slf4j.Logger

@Mod(BeeMod.MODID)
class BeeMod(modEventBus: IEventBus) {
    init {
        // Register the commonSetup method for modloading
        modEventBus.addListener { _: FMLCommonSetupEvent -> this.commonSetup() }

        ModItems.register(modEventBus)
        ModEffects.register(modEventBus)
    }

    private fun commonSetup() {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP")
    }

    companion object {
        // Define mod id in a common place for everything to reference
        const val MODID: String = "beemod"

        // Directly reference a slf4j logger
        val LOGGER: Logger = LogUtils.getLogger()
    }
}
