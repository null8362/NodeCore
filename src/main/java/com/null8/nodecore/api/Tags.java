package com.null8.nodecore.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class Tags {
    public static final TagKey<Block> FLESHY   = create("material/fleshy");   // living things (zombie, player)
    public static final TagKey<Block> CRUMBLY  = create("material/crumbly");  // crumbles (dirt, sand)
    public static final TagKey<Block> CRACKY   = create("material/cracky");   // tough but crackable (stone, ores)
    public static final TagKey<Block> METALLIC = create("material/metallic"); // made from metals (iron, lode)
    public static final TagKey<Block> SNAPPY   = create("material/snappy");   // cut using fine tools (leaves, small plants)
    public static final TagKey<Block> CHOPPY   = create("material/choppy");   // can be cut using force (logs, planks)
    public static final TagKey<Block> EXPLODY  = create("material/explody");  // especially prone to explosions (plants, )
    public static final TagKey<Block> WEAK     = create("material/weak");     // mineable by hand (leaves, rocks)

    private static TagKey<Block> create(String location) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(location));
    }

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, name);
    }
}
