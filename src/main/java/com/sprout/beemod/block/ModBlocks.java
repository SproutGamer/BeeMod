package com.sprout.beemod.block;

import com.sprout.beemod.BeeMod;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BeeMod.MOD_ID);

    public static final DeferredBlock<SoulBeehiveBlock> SOUL_BEEHIVE = BLOCKS.registerBlock(
            "soul_beehive",
            SoulBeehiveBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE)
    );

    public static final DeferredBlock<FlowerBlock> SOULBLOSSOM = BLOCKS.registerBlock(
            "soulblossom",
            properties -> new FlowerBlock(
                    MobEffects.NAUSEA, 20 * 41, properties
            ),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
    );

    public static final DeferredBlock<FlowerPotBlock> POTTED_SOULBLOSSOM = BLOCKS.registerBlock(
            "potted_soulblossom",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SOULBLOSSOM,
                    properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_DANDELION)
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
