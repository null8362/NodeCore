package com.null8.nodecore.api;

import java.util.Random;

import com.null8.nodecore.init.NodeCoreBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;

public class UnstableBlock extends Block implements Fallable {
    public UnstableBlock(BlockBehaviour.Properties p_53205_) {
        super(p_53205_);
    }

    public void onPlace(BlockState p_53233_, Level p_53234_, BlockPos p_53235_, BlockState p_53236_, boolean p_53237_) {
        p_53234_.scheduleTick(p_53235_, this, this.getDelayAfterPlace());
    }

    public BlockState updateShape(BlockState p_53226_, Direction p_53227_, BlockState p_53228_, LevelAccessor p_53229_, BlockPos p_53230_, BlockPos p_53231_) {
        p_53229_.scheduleTick(p_53230_, this, this.getDelayAfterPlace());
        return super.updateShape(p_53226_, p_53227_, p_53228_, p_53229_, p_53230_, p_53231_);
    }

    public void tick(BlockState p_53216_, ServerLevel p_53217_, BlockPos p_53218_, Random p_53219_) {
        if (isFree(p_53217_.getBlockState(p_53218_.below())) && p_53218_.getY() >= p_53217_.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(p_53217_, p_53218_, p_53216_);
            this.falling(fallingblockentity);
        }
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        BlockPos FallToPos = getMoveFree(pos, level);
        if (FallToPos != pos) {
            level.setBlock(FallToPos, state, 3);
            level.destroyBlock(pos, false);
            level.blockUpdated(FallToPos, level.getBlockState(FallToPos).getBlock());
        }
    }

    protected void falling(FallingBlockEntity p_53206_) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }

    public static boolean isFree(BlockState p_53242_) {
        Material material = p_53242_.getMaterial();
        return p_53242_.isAir() || p_53242_.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }

    public static BlockPos getMoveFree(BlockPos pos, Level level) {
        Random random = new Random();
        BlockPos result = pos;
        int direction = random.nextInt(4);
        BlockPos offset = new BlockPos(0, 0, 0);

        if (level.getBlockState(pos.below()).is(NodeCoreBlockTags.LOOSE)) {
            if (direction == 0) {
                offset = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
            } else if (direction == 1) {
                offset = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
            } else if (direction == 2) {
                offset = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
            } else if (direction == 3) {
                offset = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
            }

            if (isFree(level.getBlockState(offset)) && isFree(level.getBlockState(offset.below()))) {
                result = offset;
            }
        }

        return result;
    }

    public void animateTick(BlockState p_53221_, Level p_53222_, BlockPos p_53223_, Random p_53224_) {
        if (p_53224_.nextInt(16) == 0) {
            BlockPos blockpos = p_53223_.below();
            if (isFree(p_53222_.getBlockState(blockpos))) {
                double d0 = (double)p_53223_.getX() + p_53224_.nextDouble();
                double d1 = (double)p_53223_.getY() - 0.05D;
                double d2 = (double)p_53223_.getZ() + p_53224_.nextDouble();
                p_53222_.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, p_53221_), d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public int getDustColor(BlockState p_53238_, BlockGetter p_53239_, BlockPos p_53240_) {
        return -16777216;
    }
}
