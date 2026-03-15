package com.sprout.beemod.datagen;

import com.sprout.beemod.BeeMod;
import com.sprout.beemod.poitype.ModPoiTypes;
import com.sprout.beemod.tag.ModPoiTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ModPoiTypeTagProvider extends PoiTypeTagsProvider {
    public ModPoiTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BeeMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.tag(ModPoiTypeTags.SOUL_BEE_HOME)
                .add(Objects.requireNonNull(
                        ModPoiTypes.SOUL_BEEHIVE.getKey(),
                        "Soul Beehive Poi Type Key Not Found!"
                ));
    }
}
