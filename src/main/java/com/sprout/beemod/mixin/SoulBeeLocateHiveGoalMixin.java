package com.sprout.beemod.mixin;

import com.sprout.beemod.entity.ModEntities;
import com.sprout.beemod.tag.ModPoiTypeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.animal.bee.Bee;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(targets = "net.minecraft.world.entity.animal.bee.Bee$BeeLocateHiveGoal")
public class SoulBeeLocateHiveGoalMixin {

    @Shadow
    @Final
    Bee this$0;

    @Redirect(
            method = "findNearbyHivesWithSpace",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/village/poi/PoiManager;getInRange(Ljava/util/function/Predicate;Lnet/minecraft/core/BlockPos;ILnet/minecraft/world/entity/ai/village/poi/PoiManager$Occupancy;)Ljava/util/stream/Stream;")
    )
    private Stream<PoiRecord> redirectPoiType(PoiManager instance, Predicate<Holder<PoiType>> typePredicate, BlockPos pos, int distance, PoiManager.Occupancy status) {
        if (this$0.getType().equals(ModEntities.SOUL_BEE.get())) {
            return instance.getInRange(poiType -> poiType.is(ModPoiTypeTags.SOUL_BEE_HOME), pos, distance, status);
        }

        return instance.getInRange(typePredicate, pos, distance, status);
    }

}
