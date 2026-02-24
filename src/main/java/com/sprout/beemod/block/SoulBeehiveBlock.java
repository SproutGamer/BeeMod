package com.sprout.beemod.block;

import com.sprout.beemod.block.entity.SoulBeehiveBlockEntity;
import com.sprout.beemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class SoulBeehiveBlock extends BeehiveBlock {
    public SoulBeehiveBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull InteractionResult useItemOn(
            @NonNull ItemStack stack,
            @NonNull BlockState blockState,
            @NonNull Level world,
            @NonNull BlockPos blockPos,
            @NonNull Player player,
            @NonNull InteractionHand hand,
            @NonNull BlockHitResult hitResult
    ) {
        int honeyLevel = blockState.getValue(HONEY_LEVEL);
        boolean actionPerformed = false;
        if (honeyLevel >= MAX_HONEY_LEVELS) {
            Item item = stack.getItem();
            if (world instanceof ServerLevel && stack.canPerformAction(ItemAbilities.SHEARS_HARVEST)) {
                dropSoulcomb(
                        (ServerLevel) world,
                        blockPos
                );
                world.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.BEEHIVE_SHEAR,
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f
                );
                stack.hurtAndBreak(1, player, hand.asEquipmentSlot());
                actionPerformed = true;
                world.gameEvent(player, GameEvent.SHEAR, blockPos);
            } else if (stack.is(Items.GLASS_BOTTLE)){
                stack.shrink(1);
                world.playSound(
                        player,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.BOTTLE_FILL,
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f
                );
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, new ItemStack(ModItems.SOUL_NECTAR_BOTTLE.get()));
                } else if (!player.getInventory().add(new ItemStack(ModItems.SOUL_NECTAR_BOTTLE.get()))) {
                    player.drop(new ItemStack(ModItems.SOUL_NECTAR_BOTTLE.get()), false);
                }

                actionPerformed = true;
                world.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
            }

            if (!world.isClientSide() && actionPerformed) {
                player.awardStat(Stats.ITEM_USED.get(item));
            }
        }

        if (actionPerformed) {
            if (!CampfireBlock.isSmokeyPos(world, blockPos)) {
                this.releaseBeesAndResetHoneyLevel(
                        world,
                        blockState,
                        blockPos,
                        player,
                        BeehiveBlockEntity.BeeReleaseStatus.EMERGENCY
                );
            } else {
                this.resetHoneyLevel(world, blockState, blockPos);
            }

            return InteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, blockState, world, blockPos, player, hand, hitResult);
    }

    private void dropSoulcomb(
            ServerLevel world, BlockPos pos
    ) {
        ItemStack soulcomb = new ItemStack(ModItems.SOULCOMB.get());
        popResource(world, pos, soulcomb);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos blockPos, @NonNull BlockState blockState) {
        return new SoulBeehiveBlockEntity(blockPos, blockState);
    }
}
