package com.null8.nodecore.init;

import com.null8.nodecore.NodeCore;
import com.null8.nodecore.block.choppy.LogOak;
import com.null8.nodecore.block.cracky.Cobble;
import com.null8.nodecore.block.cracky.Stone;
import com.null8.nodecore.block.cracky.StoneHard;
import com.null8.nodecore.block.crumbly.*;
import com.null8.nodecore.block.other.ItemBlock;
import com.null8.nodecore.block.snappy.Leaves;
import com.null8.nodecore.block.snappy.LeavesLoose;
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

	// other
	public static final RegistryObject<Block> ITEM_BLOCK = REGISTRY.register("item_block", ItemBlock::new);

	// cracky
	public static final RegistryObject<Block> STONE = REGISTRY.register("stone", Stone::new);
	public static final RegistryObject<Block> STONE_HARD = REGISTRY.register("stone_hard", StoneHard::new);
	public static final RegistryObject<Block> GRASS_BLOCK = REGISTRY.register("grass_block", GrassBlock::new);
	public static final RegistryObject<Block> COBBLE = REGISTRY.register("cobble", Cobble::new);

	// crumbly
	public static final RegistryObject<Block> DIRT = REGISTRY.register("dirt", Dirt::new);
	public static final RegistryObject<Block> DIRT_LOOSE = REGISTRY.register("dirt_loose", DirtLoose::new);
	public static final RegistryObject<Block> COBBLE_LOOSE = REGISTRY.register("cobble_loose", CobbleLoose::new);
	public static final RegistryObject<Block> GRAVEL = REGISTRY.register("gravel", Gravel::new);
	public static final RegistryObject<Block> GRAVEL_LOOSE = REGISTRY.register("gravel_loose", GravelLoose::new);
	public static final RegistryObject<Block> SAND = REGISTRY.register("sand", Sand::new);
	public static final RegistryObject<Block> SAND_LOOSE = REGISTRY.register("sand_loose", SandLoose::new);

	// choppy
	public static final RegistryObject<Block> LOG_OAK = REGISTRY.register("log_oak", LogOak::new);

	// snappy
	public static final RegistryObject<Block> LEAVES_OAK = REGISTRY.register("leaves_oak", Leaves::new);
	public static final RegistryObject<Block> LEAVES_OAK_LOOSE = REGISTRY.register("leaves_oak_loose", LeavesLoose::new);

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

			Leaves.registerRenderLayer(LEAVES_OAK.get());
			LeavesLoose.registerRenderLayer(LEAVES_OAK_LOOSE.get());
		}

		@SubscribeEvent
		public static void blockColorLoad(ColorHandlerEvent.Block event) {
			// Block Color Tint
			GrassBlock.blockColorLoad(event);
			Leaves.blockColorLoad(event, LEAVES_OAK.get());
			LeavesLoose.blockColorLoad(event, LEAVES_OAK_LOOSE.get());
		}

		@SubscribeEvent
		public static void itemColorLoad(ColorHandlerEvent.Item event) {
			// Item Color Tint
			GrassBlock.itemColorLoad(event);
			Leaves.itemColorLoad(event, LEAVES_OAK.get());
			LeavesLoose.itemColorLoad(event, LEAVES_OAK_LOOSE.get());
		}
	}

}
