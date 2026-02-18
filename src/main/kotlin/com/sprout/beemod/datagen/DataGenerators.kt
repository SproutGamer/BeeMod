package com.sprout.beemod.datagen

import com.sprout.beemod.BeeMod
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

@EventBusSubscriber(modid = BeeMod.MODID)
object DataGenerators {

    @SubscribeEvent
    @JvmStatic
    fun gatherClientData(event: GatherDataEvent.Client) {
        val generator = event.generator
        val packOutput = generator.packOutput

        generator.addProvider(true, ModModelProvider(packOutput))
    }

}