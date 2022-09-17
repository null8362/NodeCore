package com.null8.nodecore.block.snappy;

import com.null8.nodecore.api.UnstableBlock;
import com.null8.nodecore.api.Util;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class LeavesLoose extends UnstableBlock implements net.minecraftforge.common.IForgeShearable {

    public LeavesLoose() {
        super(Properties.of(Material.LEAVES).randomTicks().sound(SoundType.GRASS).strength(0.25f).noOcclusion().isValidSpawn(Util::ocelotOrParrot).isSuffocating(Util::never).isViewBlocking(Util::never));
    }

    @OnlyIn(Dist.CLIENT)
    public static void blockColorLoad(ColorHandlerEvent.Block event, Block block) {
        event.getBlockColors().register((bs, world, pos, index) -> {
            return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D);
        }, block);
    }

    @OnlyIn(Dist.CLIENT)
    public static void itemColorLoad(ColorHandlerEvent.Item event, Block block) {
        event.getItemColors().register((stack, index) -> {
            return GrassColor.get(0.5D, 1.0D);
        }, block);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, renderType -> renderType == RenderType.translucent());
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootContext.@NotNull Builder builder) {

        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
