
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.null8.nodecore.init;

import com.null8.nodecore.NodeCore;
import com.null8.nodecore.block.entity.ItemBlock;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

public class NodeCoreBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, NodeCore.MODID);
	public static final RegistryObject<BlockEntityType<?>> ITEM_BLOCK = register("item_block", NodeCoreBlocks.ITEM_BLOCK,
			ItemBlock::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
