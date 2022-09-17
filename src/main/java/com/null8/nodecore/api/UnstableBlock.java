package com.null8.nodecore.api;

import com.null8.nodecore.init.NodeCoreBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

import static com.null8.nodecore.api.Util.*;

public class UnstableBlock extends Block implements Fallable {
    public UnstableBlock(BlockBehaviour.Properties p_53205_) {
        super(p_53205_);
    }

    public static BlockPos intToPos(int direction, BlockPos pos) {
        BlockPos offset = pos;

        if (direction == 0) {
            offset = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
        } else if (direction == 1) {
            offset = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
        } else if (direction == 2) {
            offset = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
        } else if (direction == 3) {
            offset = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
        }

        return offset;
    }

    public void onPlace(@NotNull BlockState p_53233_, Level p_53234_, @NotNull BlockPos p_53235_, @NotNull BlockState p_53236_, boolean p_53237_) {
        p_53234_.scheduleTick(p_53235_, this, this.getDelayAfterPlace());
    }

    public @NotNull BlockState updateShape(@NotNull BlockState p_53226_, @NotNull Direction p_53227_, @NotNull BlockState p_53228_, LevelAccessor p_53229_, BlockPos p_53230_, BlockPos p_53231_) {
        p_53229_.scheduleTick(p_53230_, this, this.getDelayAfterPlace());
        return super.updateShape(p_53226_, p_53227_, p_53228_, p_53229_, p_53230_, p_53231_);
    }

    public void tick(@NotNull BlockState p_53216_, ServerLevel p_53217_, BlockPos p_53218_, @NotNull Random p_53219_) {
        if (isFree(p_53217_.getBlockState(p_53218_.below())) && p_53218_.getY() >= p_53217_.getMinBuildHeight()) {
            NodeCoreFallingBlockEntity fallingblockentity = NodeCoreFallingBlockEntity.fall(p_53217_, p_53218_, p_53216_);
            this.falling(fallingblockentity);
        }
    }

    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull Random random) {
        BlockPos fallToPos = getFallFree(pos, level);
        if (fallToPos != pos) {
            level.setBlock(fallToPos, state, 3);
            level.destroyBlock(pos, false);
            level.blockUpdated(fallToPos, level.getBlockState(fallToPos).getBlock());

            if (state.is(NodeCoreBlockTags.LOOSE) && random.nextBoolean()) {
                String regName = Objects.requireNonNull(state.getBlock().getRegistryName()).toString();
                level.setBlock(pos, Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(regName.substring(0, regName.indexOf("_loose"))))).defaultBlockState(), 3);
            }
        }
    }

    protected void falling(NodeCoreFallingBlockEntity p_53206_) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public void onBrokenAfterFall(Level level, @NotNull BlockPos pos, NodeCoreFallingBlockEntity fallingBlock) {
        BlockState state = fallingBlock.getBlockState();
        BlockState replaceBlock = level.getBlockState(pos);

        if (!isFree(replaceBlock)) {
            BlockPos moveTo = getMoveFree(pos, level);

            level.setBlock(moveTo, state, 3);
            fallingBlock.discard();
            level.blockUpdated(moveTo, level.getBlockState(moveTo).getBlock());
        }
    }

    public void animateTick(@NotNull BlockState p_53221_, @NotNull Level p_53222_, @NotNull BlockPos p_53223_, Random p_53224_) {
        if (p_53224_.nextInt(16) == 0) {
            BlockPos blockpos = p_53223_.below();
            if (isFree(p_53222_.getBlockState(blockpos))) {
                double d0 = (double) p_53223_.getX() + p_53224_.nextDouble();
                double d1 = (double) p_53223_.getY() - 0.05D;
                double d2 = (double) p_53223_.getZ() + p_53224_.nextDouble();
                p_53222_.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, p_53221_), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public int getDustColor(BlockState p_53238_, BlockGetter p_53239_, BlockPos p_53240_) {
        return -16777216;
    }
}
