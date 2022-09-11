
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.null8.nodecore.init;

import com.null8.nodecore.NodeCore;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class NodeCoreItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, NodeCore.MODID);

	public static final RegistryObject<Item> ITEM_BLOCK = block(NodeCoreBlocks.ITEM_BLOCK, null);
	public static final RegistryObject<Item> DIRT = block(NodeCoreBlocks.DIRT, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> DIRT_LOOSE = block(NodeCoreBlocks.DIRT_LOOSE, CreativeModeTab.TAB_BUILDING_BLOCKS);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
