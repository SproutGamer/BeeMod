package com.sprout.beemod.mixin;

import com.sprout.beemod.entity.ModEntities;
import net.minecraft.world.entity.animal.bee.Bee;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bee.class)
public class SoulBeeStingerMixin {

    @Inject(method = "setHasStung", at = @At("HEAD"), cancellable = true)
    private void preventSoulBeeStingerLoss(boolean hasStung, CallbackInfo ci) {
        if (((Bee)(Object)this).getType() == ModEntities.SOUL_BEE.get()) {
            ci.cancel();
        }
    }

}
