package com.sprout.beemod.tag;

import com.sprout.beemod.BeeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public class ModPoiTypeTags {
    public static final TagKey<PoiType> SOUL_BEE_HOME = createTag("soul_bee_home");

    private static TagKey<PoiType> createTag(String name) {
        return TagKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(BeeMod.MOD_ID, name));
    }

}
