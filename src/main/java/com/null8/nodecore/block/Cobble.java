
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

import static com.null8.nodecore.api.Mining.replaceblock;

public class Cobble extends FallingBlock {

    public Cobble() {
        super(Properties.of(Material.STONE, MaterialColor.STONE).sound(SoundType.STONE).strength(2f, 6));
    }

    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        return replaceblock(NodeCoreBlocks.COBBLE_LOOSE.get(), this.asBlock(), state, 8f, level, pos, player, willHarvest);
    }
}
