package com.sprout.beemod.datagen;

import com.sprout.beemod.BeeMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = BeeMod.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    private static void gatherClientData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModEntityTagProvider::new);
        event.createProvider(ModPoiTypeTagProvider::new);
    }

}
