package com.sprout.beemod.item.food

import com.sprout.beemod.effect.ModEffects
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.component.Consumables
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect

object ModConsumables {

    val SOUL_NECTAR_BOTTLE = Consumables.defaultDrink()
        .onConsume(ApplyStatusEffectsConsumeEffect(MobEffectInstance(ModEffects.SOULBOUND, 20 * 60 * 5))) // Soulbound effect for 5 minutes
        .build()

}