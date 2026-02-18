package com.sprout.beemod.event

import com.sprout.beemod.BeeMod
import com.sprout.beemod.effect.ModEffects
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import java.util.*


@EventBusSubscriber(modid = BeeMod.MODID)
object ModEvents {

    private val soulboundCache = mutableMapOf<UUID, List<ItemStack>>()

    @SubscribeEvent
    @JvmStatic
    fun onLivingDrops(event: LivingDropsEvent) {
        val player = event.entity
        if (player is Player && player.hasEffect(ModEffects.SOULBOUND)) {
            event.drops.clear()
            event.isCanceled = true
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onPlayerDeath(event: LivingDeathEvent) {
        val player = event.entity
        if (player is Player && player.hasEffect(ModEffects.SOULBOUND)) {
            val inv = player.inventory
            val copy = (0..<inv.containerSize).map { slot ->
                inv.getItem(slot).copy()
            }
            soulboundCache[player.uuid] = copy
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onPlayerRespawn(event: PlayerEvent.PlayerRespawnEvent) {
        val player = event.entity
        val cached = soulboundCache.remove(player.uuid) ?: return

        val inv = player.inventory
        inv.clearContent()

        val max = minOf(inv.containerSize, cached.size)
        for (slot in 0..<max) {
            inv.setItem(slot, cached[slot].copy())
        }
    }
}