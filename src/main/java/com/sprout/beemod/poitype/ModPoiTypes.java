package com.sprout.beemod.poitype;

import com.google.common.collect.ImmutableSet;
import com.sprout.beemod.BeeMod;
import com.sprout.beemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPoiTypes {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(
            BuiltInRegistries.POINT_OF_INTEREST_TYPE, BeeMod.MOD_ID
    );

    public static final Holder<PoiType> SOUL_BEEHIVE = POI_TYPES.register(
            "soul_beehive",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SOUL_BEEHIVE.get().getStateDefinition().getPossibleStates()), 0, 1)
    );

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }

}
