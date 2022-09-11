package com.null8.nodecore.init;

import com.null8.nodecore.NodeCore;
import com.null8.nodecore.block.*;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

public class NodeCoreBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, NodeCore.MODID);

	// WIP
	public static final RegistryObject<Block> ITEM_BLOCK = REGISTRY.register("item_block", ItemBlock::new);

	// cracky
	public static final RegistryObject<Block> STONE = REGISTRY.register("stone", Stone::new);

	// crumbly
	public static final RegistryObject<Block> DIRT = REGISTRY.register("dirt", Dirt::new);
	public static final RegistryObject<Block> DIRT_LOOSE = REGISTRY.register("dirt_loose", DirtLoose::new);
	public static final RegistryObject<Block> COBBLE = REGISTRY.register("cobble", Cobble::new);
	public static final RegistryObject<Block> COBBLE_LOOSE = REGISTRY.register("cobble_loose", CobbleLoose::new);

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			// Rendertype change (mainly for loose blocks with overlay)
			ItemBlock.registerRenderLayer();
			DirtLoose.registerRenderLayer();
			CobbleLoose.registerRenderLayer();

		}
	}
}
