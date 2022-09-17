package com.null8.nodecore.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

import static com.null8.nodecore.api.Util.getMoveFree;
import static com.null8.nodecore.api.Util.isFree;

public interface Fallable {
    default void onLand(Level p_153220_, BlockPos p_153221_, BlockState p_153222_, BlockState p_153223_, NodeCoreFallingBlockEntity p_153224_) {
    }

    default void onBrokenAfterFall(Level level, BlockPos pos, NodeCoreFallingBlockEntity fallingBlock) {
        BlockState state = fallingBlock.getBlockState();
        BlockState replaceBlock = level.getBlockState(pos);

        if (!isFree(replaceBlock)) {
            BlockPos moveTo = getMoveFree(pos, level);

            level.setBlock(moveTo, state, 3);
            fallingBlock.discard();
            level.blockUpdated(moveTo, level.getBlockState(moveTo).getBlock());
        }
    }

    default DamageSource getFallDamageSource() {
        return DamageSource.FALLING_BLOCK;
    }

    default Predicate<Entity> getHurtsEntitySelector() {
        return EntitySelector.NO_SPECTATORS;
    }
}
