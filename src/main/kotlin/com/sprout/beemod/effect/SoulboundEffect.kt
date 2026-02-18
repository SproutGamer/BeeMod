package com.sprout.beemod.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory

class SoulboundEffect : MobEffect(MobEffectCategory.BENEFICIAL, 0x7B2CBF /* purple-ish */) {
    override fun shouldApplyEffectTickThisTick(duration: Int, amplifier: Int): Boolean {
        return false
    }
}