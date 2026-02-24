package com.sprout.beemod.block;

import com.sprout.beemod.BeeMod;
import net.minecraft.world.level.block.Blocks;
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

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
