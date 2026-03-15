package com.sprout.beemod;

import com.sprout.beemod.block.ModBlocks;
import com.sprout.beemod.block.entity.ModBlockEntities;
import com.sprout.beemod.effect.ModEffects;
import com.sprout.beemod.entity.ModEntities;
import com.sprout.beemod.item.ModItems;
import com.sprout.beemod.poitype.ModPoiTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
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
        ModEntities.register(modEventBus);
        ModPoiTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(this::addFlowers);
    }

    private void addFlowers() {
        FlowerPotBlock potBlock = ((FlowerPotBlock) Blocks.FLOWER_POT);
        potBlock.addPlant(ModBlocks.SOULBLOSSOM.getId(), ModBlocks.POTTED_SOULBLOSSOM);
    }

}
