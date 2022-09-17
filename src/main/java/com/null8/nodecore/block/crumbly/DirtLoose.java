package com.null8.nodecore.block.crumbly;

import com.null8.nodecore.api.UnstableBlock;
import com.null8.nodecore.init.NodeCoreBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.null8.nodecore.api.Util.getFallFree;

public class DirtLoose extends UnstableBlock {

    public DirtLoose() {
        super(Properties.of(Material.DIRT, MaterialColor.DIRT).randomTicks().sound(SoundType.GRAVEL).strength(0.25f));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(NodeCoreBlocks.DIRT_LOOSE.get(), renderType -> renderType == RenderType.translucent());
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random) {
        BlockPos fallToPos = getFallFree(pos, level);

        if (fallToPos != pos) {
            level.setBlock(fallToPos, state, 3);
            level.destroyBlock(pos, false);
            level.blockUpdated(fallToPos, level.getBlockState(fallToPos).getBlock());
        }

    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootContext.@NotNull Builder builder) {

        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
