package com.sprout.beemod.item;

import com.sprout.beemod.BeeMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            BuiltInRegistries.CREATIVE_MODE_TAB, BeeMod.MOD_ID
    );



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(
                "bees", () -> CreativeModeTab.builder()
                        .icon(() -> new ItemStack(Items.BEE_SPAWN_EGG))
                        .title(Component.translatable("creativetab.beemod.bees"))
                        .displayItems((_, output) -> {
                            output.accept(ModItems.SOULBLOSSOM.get());
                            output.accept(ModItems.SOUL_NECTAR_BOTTLE.get());
                            output.accept(ModItems.SOUL_BEEHIVE.get());
                            output.accept(ModItems.SOULCOMB.get());
                        })
                        .build()
        );

        CREATIVE_MODE_TABS.register(eventBus);
    }

}
