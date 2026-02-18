package com.sprout.beemod.item.food

import net.minecraft.world.food.FoodProperties

object ModFoods {

    val SOUL_NECTAR_BOTTLE = FoodProperties.Builder()
        .nutrition(6)
        .saturationModifier(0.6f)
        .alwaysEdible()
        .build()

}