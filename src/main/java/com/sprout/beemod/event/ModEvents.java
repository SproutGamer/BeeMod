package com.sprout.beemod.event;

import com.sprout.beemod.BeeMod;
import com.sprout.beemod.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.*;

@EventBusSubscriber(modid = BeeMod.MOD_ID)
public class ModEvents {

    private static final Map<UUID, List<ItemStack>> soulboundCache = new HashMap<>();

    @SubscribeEvent
    private static void onLivingDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player && player.hasEffect(ModEffects.SOULBOUND)) {
            event.getDrops().clear();
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    private static void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player && player.hasEffect(ModEffects.SOULBOUND)) {
            Inventory inv = player.getInventory();
            List<ItemStack> copy = new ArrayList<>(inv.getContainerSize());
            for (int i = 0; i < inv.getContainerSize(); i++) {
                copy.add(inv.getItem(i).copy());
            }
            soulboundCache.put(player.getUUID(), copy);
        }
    }

    @SubscribeEvent
    private static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        List<ItemStack> cached = soulboundCache.remove(player.getUUID());
        if (cached == null) return;

        Inventory inv = player.getInventory();
        inv.clearContent();

        int max = Math.min(inv.getContainerSize(), cached.size());
        for (int i = 0; i < max; i++) {
            inv.setItem(i, cached.get(i));
        }

    }
}
