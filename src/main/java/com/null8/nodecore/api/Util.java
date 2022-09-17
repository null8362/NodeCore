package com.null8.nodecore.api;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

import static com.null8.nodecore.api.UnstableBlock.intToPos;

public class Util {
    public static BlockPos getMoveFree(BlockPos pos, Level level) {
        Random random = new Random();
        BlockPos result = pos;
        int randomInt = random.nextInt(4);
        int count = 4;
        boolean found = false;

        while (count > 0) {
            if (isFree(level.getBlockState(intToPos(randomInt, pos)))) {
                result = intToPos(randomInt, pos);
                found = true;
            }
            randomInt = stepInt(randomInt, 0, 3);
            count--;
        }
        if (!found) {
            result = pos.above();
        }

        return result;
    }


    public static int stepInt(int Int, int min, int max) {
        Int = Int + 1;
        if (Int > max) {
            Int = min;
        }
        return Int;
    }

    public static BlockPos getFallFree(BlockPos pos, Level level) {
        Random random = new Random();
        BlockPos result = pos;
        int randomInt = random.nextInt(4);
        int count = 4;

        while (count > 0) {
            if (isFree(level.getBlockState(intToPos(randomInt, pos))) && isFree(level.getBlockState(intToPos(randomInt, pos).below()))) {
                result = intToPos(randomInt, pos);
            }
            randomInt = stepInt(randomInt, 0, 3);
            count--;
        }

        return result;
    }

    public static boolean isFree(BlockState p_53242_) {
        Material material = p_53242_.getMaterial();
        return p_53242_.isAir() || p_53242_.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }

    public static Block LooseVersion(Block original) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(original.getRegistryName() + "_loose"));
    }

    public static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }

    public static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
        return false;
    }

}
