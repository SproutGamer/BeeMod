package com.sprout.beemod.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.sprout.beemod.block.ModBlocks;
import com.sprout.beemod.block.entity.ModBlockEntities;
import com.sprout.beemod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(BeehiveBlockEntity.class)
public abstract class SoulBeehiveBlockEntityMixin {

    @ModifyArg(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/BlockEntity;<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"
            ),
            index = 0
    )
    private static BlockEntityType<?> redirectBlockEntityType(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        if (state.is(ModBlocks.SOUL_BEEHIVE)) {
            return ModBlockEntities.SOUL_BEEHIVE.get();
        }

        return type;
    }

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

        return level.isBrightOutside();
    }

    @Redirect(method = "releaseOccupant",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;Ljava/util/function/Predicate;)Z")
    )
    private static boolean redirectIsBlockStateBeehive(BlockState instance, TagKey<Block> tagKey, Predicate<BlockBehaviour.BlockStateBase> predicate) {
        if (instance.is(ModBlocks.SOUL_BEEHIVE)) {
            return true;
        }

        return instance.is(tagKey, predicate);
    }

    @Unique
    private static boolean beeMod$isSoulBee(BeehiveBlockEntity.Occupant occupant) {
        return occupant.entityData().type().equals(ModEntities.SOUL_BEE.get());
    }
}
