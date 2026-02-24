package com.sprout.beemod.block.entity;

import com.sprout.beemod.BeeMod;
import com.sprout.beemod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, BeeMod.MOD_ID
    );

    public static final Supplier<BlockEntityType<SoulBeehiveBlockEntity>> SOUL_BEEHIVE = BLOCK_ENTITIES.register(
            "soul_beehive",
            () -> new BlockEntityType<>(
                    SoulBeehiveBlockEntity::new, ModBlocks.SOUL_BEEHIVE.get()
            )
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
