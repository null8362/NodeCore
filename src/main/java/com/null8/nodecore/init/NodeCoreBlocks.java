package com.null8.nodecore.init;

import com.null8.nodecore.NodeCore;
import com.null8.nodecore.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NodeCoreBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, NodeCore.MODID);

	// WIP
	public static final RegistryObject<Block> ITEM_BLOCK = REGISTRY.register("item_block", ItemBlock::new);

	// cracky
	public static final RegistryObject<Block> STONE = REGISTRY.register("stone", Stone::new);
	public static final RegistryObject<Block> STONE_HARD = REGISTRY.register("stone_hard", StoneHard::new);
	public static final RegistryObject<Block> GRASS_BLOCK = REGISTRY.register("grass_block", GrassBlock::new);

	// crumbly
	public static final RegistryObject<Block> DIRT = REGISTRY.register("dirt", Dirt::new);
	public static final RegistryObject<Block> DIRT_LOOSE = REGISTRY.register("dirt_loose", DirtLoose::new);
	public static final RegistryObject<Block> COBBLE = REGISTRY.register("cobble", Cobble::new);
	public static final RegistryObject<Block> COBBLE_LOOSE = REGISTRY.register("cobble_loose", CobbleLoose::new);
	public static final RegistryObject<Block> GRAVEL = REGISTRY.register("gravel", Gravel::new);
	public static final RegistryObject<Block> GRAVEL_LOOSE = REGISTRY.register("gravel_loose", GravelLoose::new);
	public static final RegistryObject<Block> SAND = REGISTRY.register("sand", Sand::new);
	public static final RegistryObject<Block> SAND_LOOSE = REGISTRY.register("sand_loose", SandLoose::new);

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			// Rendertype change (mainly for loose blocks with overlay)
			ItemBlock.registerRenderLayer();
			DirtLoose.registerRenderLayer();
			CobbleLoose.registerRenderLayer();
			GravelLoose.registerRenderLayer();
			SandLoose.registerRenderLayer();

			GrassBlock.registerRenderLayer();
		}

		@SubscribeEvent
		public static void blockColorLoad(ColorHandlerEvent.Block event) {
			GrassBlock.blockColorLoad(event);
		}

		@SubscribeEvent
		public static void itemColorLoad(ColorHandlerEvent.Item event) {
			GrassBlock.itemColorLoad(event);
		}
	}
}
