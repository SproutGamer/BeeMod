package com.sprout.beemod.entity.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;

public class StopAttackingInDayGoal extends TargetGoal {
    private final Mob mob;
    public StopAttackingInDayGoal(Mob mob) {
        super(mob, false);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return mob.level().isBrightOutside() && mob.getTarget() != null;
    }

    @Override
    public void tick() {
        mob.setTarget(null);
    }
}
