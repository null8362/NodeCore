
package com.null8.nodecore.block;

import com.null8.nodecore.init.NodeCoreBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import static com.null8.nodecore.api.MiningDecay.replaceblock;

public class Dirt extends FallingBlock {

    public Dirt() {
        super(Properties.of(Material.STONE, MaterialColor.DIRT).sound(SoundType.GRAVEL).strength(1f));
    }

    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        return replaceblock(NodeCoreBlocks.DIRT_LOOSE.get(), this.asBlock(), state, 10f, level, pos, player);
    }
}
