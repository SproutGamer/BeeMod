package com.sprout.beemod.entity;

import com.sprout.beemod.BeeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(BeeMod.MOD_ID);

    public static final Supplier<EntityType<SoulBee>> SOUL_BEE = ENTITIES.register(
            "soul_bee",
            () -> EntityType.Builder.of(
                    SoulBee::new,
                    MobCategory.MONSTER
            ).sized(0.7f, 0.6f)
                    .eyeHeight(0.3f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(
                            Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(BeeMod.MOD_ID, "soul_bee")
                    ))

    );

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
