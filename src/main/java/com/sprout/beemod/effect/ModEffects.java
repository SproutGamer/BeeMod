package com.sprout.beemod.effect;

import com.sprout.beemod.BeeMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(
            BuiltInRegistries.MOB_EFFECT, BeeMod.MOD_ID
    );

    public static final DeferredHolder<MobEffect, SoulboundEffect> SOULBOUND = EFFECTS.register("soulbound", SoulboundEffect::new);

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

}
