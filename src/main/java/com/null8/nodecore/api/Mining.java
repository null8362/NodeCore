package com.null8.nodecore.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Mining {
    public static void replaceBlock(Block block, Block original, LevelAccessor world, BlockPos pos) {
        world.levelEvent(2001, pos, Block.getId(original.defaultBlockState()));
        world.setBlock(pos, block.defaultBlockState(), 3);
    }

    public static void breakBlock(LevelAccessor world, BlockPos pos) {
        world.destroyBlock(pos, false);
    }

    public static void cancelBreak(Block original, LevelAccessor world, BlockPos pos) {
        //world.levelEvent(2001, pos, Block.getId(original.defaultBlockState()));
        //world.setBlock(pos, original.defaultBlockState(), 3);
    }


    public static boolean onDestroyedByPlayer(Block block, Block original, BlockState state, float threshold, Level level, BlockPos pos, Player player, boolean willHarvest) {
        float digSpeed = player.getDigSpeed(state, pos);
        if (willHarvest) {
            if (digSpeed <= threshold) {
                replaceBlock(block, original, level, pos);
                return false;
            } else {
                breakBlock(level, pos);
                return true;
            }
        } else {
            cancelBreak(original, level, pos);
            return false;
        }
    }

    public static boolean replaceblock(Block block, Block original, BlockState state, float threshold, Level level, BlockPos pos, Player player, boolean willHarvest) {
        return onDestroyedByPlayer(block, original, state, threshold, level, pos, player, willHarvest);
    }
}
