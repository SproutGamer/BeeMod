package com.sprout.beemod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class SoulBeehiveBlockEntity extends BeehiveBlockEntity {
    public SoulBeehiveBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState);
    }

    @Override
    public boolean isValidBlockState(@NonNull BlockState state) {
        return ModBlockEntities.SOUL_BEEHIVE.get().isValid(state);
    }
}
