package com.null8.nodecore.init;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class NodeCoreBlockTags {
    public static final TagKey<Block> LOOSE = create("nodecore:loose");

    private NodeCoreBlockTags() {
    }

    private static TagKey<Block> create(String p_203847_) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(p_203847_));
    }

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, name);
    }
}
