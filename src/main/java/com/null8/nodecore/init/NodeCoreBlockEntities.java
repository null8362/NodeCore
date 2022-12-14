
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.null8.nodecore.init;

import com.null8.nodecore.NodeCore;
import com.null8.nodecore.block.entity.ItemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NodeCoreBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, NodeCore.MODID);

    private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
                                                               BlockEntityType.BlockEntitySupplier<?> supplier) {
        return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }    public static final RegistryObject<BlockEntityType<?>> ITEM_BLOCK = register("item_block", NodeCoreBlocks.ITEM_BLOCK,
            ItemBlock::new);


}
