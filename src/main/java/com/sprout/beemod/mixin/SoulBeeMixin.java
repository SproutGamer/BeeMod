package com.sprout.beemod.mixin;

import com.sprout.beemod.entity.ModEntities;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bee.class)
public class SoulBeeMixin {

    @Inject(method = "setHasStung", at = @At("HEAD"), cancellable = true)
    private void preventSoulBeeStingerLoss(boolean hasStung, CallbackInfo ci) {
        if (((Bee)(Object)this).getType() == ModEntities.SOUL_BEE.get()) {
            ci.cancel();
        }
    }

    @Redirect(method = "wantsToEnterHive", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;getValue(Lnet/minecraft/world/attribute/EnvironmentAttribute;Lnet/minecraft/world/phys/Vec3;)Ljava/lang/Object;"
    ))
    private <T> Object redirectEnvironmentAttribute(EnvironmentAttributeSystem instance, EnvironmentAttribute<T> environmentAttribute, Vec3 vec3) {
        if (((Bee)(Object)this).getType() == ModEntities.SOUL_BEE.get()) {
            return !(boolean) instance.getValue(environmentAttribute, vec3);
        }
        return instance.getValue(environmentAttribute, vec3);
    }

}
