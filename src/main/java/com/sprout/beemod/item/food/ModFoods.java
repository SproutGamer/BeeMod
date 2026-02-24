package com.sprout.beemod.item.food;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties SOUL_NECTAR_BOTTLE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .alwaysEdible()
            .build();
}
