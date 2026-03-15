package com.sprout.beemod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sprout.beemod.entity.ModEntities;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeehiveBlockEntity.class)
public abstract class SoulBeehiveBlockEntityMixin {

    @ModifyExpressionValue(method = "releaseOccupant", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;getValue(Lnet/minecraft/world/attribute/EnvironmentAttribute;Lnet/minecraft/core/BlockPos;)Ljava/lang/Object;"
    ))
    private static Object invertReleaseConditionForSoulBee(
            Object original,
            @Local(argsOnly = true) BeehiveBlockEntity.Occupant occupant,
            @Local(argsOnly = true) Level level
    ) {
        if (!beeMod$isSoulBee(occupant)) return original;

        return level.getDayTime() < 12000 || level.getDayTime() > 23000 || level.isRaining() || level.isThundering();
    }

    @Unique
    private static boolean beeMod$isSoulBee(BeehiveBlockEntity.Occupant occupant) {
        return occupant.entityData().type().equals(ModEntities.SOUL_BEE.get());
    }
}
