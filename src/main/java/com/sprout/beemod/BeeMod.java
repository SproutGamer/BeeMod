package com.sprout.beemod;

import com.sprout.beemod.block.ModBlocks;
import com.sprout.beemod.block.entity.ModBlockEntities;
import com.sprout.beemod.effect.ModEffects;
import com.sprout.beemod.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(BeeMod.MOD_ID)
public class BeeMod {
    public static final String MOD_ID = "beemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public BeeMod(IEventBus modEventBus) {
        LOGGER.info("Hello NeoForge world!");

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModBlockEntities.register(modEventBus);
    }

}
