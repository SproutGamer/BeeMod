package com.sprout.beemod.mixin;

import com.sprout.beemod.block.ModBlocks;
import com.sprout.beemod.entity.ModEntities;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Bee.BeeGoToHiveGoal.class)
public class SoulBeeGoToHiveGoalMixin {

    @Shadow
    @Final
    Bee this$0;

    @Redirect(
            method = "canBeeUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean redirectIsBlockStateValid(BlockState blockState, TagKey<Block> tag) {
        if ((this$0.getType() == ModEntities.SOUL_BEE.get())) {
            return blockState.is(ModBlocks.SOUL_BEEHIVE);
        }
        return blockState.is(tag);
    }

}
