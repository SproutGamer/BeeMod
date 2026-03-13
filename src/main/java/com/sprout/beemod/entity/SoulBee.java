package com.sprout.beemod.entity;

import com.sprout.beemod.entity.ai.StopAttackingInDayGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class SoulBee extends Bee {

    public SoulBee(EntityType<? extends Bee> beeEntityType, Level world) {
        super(beeEntityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                this,
                Player.class,
                true,
                (player, world) -> world.isDarkOutside() && this.hasLineOfSight(player)
        ));
        this.targetSelector.addGoal(0, new StopAttackingInDayGoal(this));
    }

    @Override
    public @NonNull EntityType<SoulBee> getType() {
        return ModEntities.SOUL_BEE.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide()) return;

        if (this.isOnFire()) {
            this.setStayOutOfHiveCountdown(0); // Let it re-enter the hive immediately.
        }
    }
}