package com.null8.nodecore.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.null8.nodecore.api.Util.isFree;

public class DirectionalFallingBlock extends Block implements Fallable {
    public DirectionalFallingBlock(BlockBehaviour.Properties p_53205_) {
        super(p_53205_);
    }

    public void onPlace(@NotNull BlockState p_53233_, Level p_53234_, @NotNull BlockPos p_53235_, @NotNull BlockState p_53236_, boolean p_53237_) {
        p_53234_.scheduleTick(p_53235_, this, this.getDelayAfterPlace());
    }

    public @NotNull BlockState updateShape(@NotNull BlockState p_53226_, @NotNull Direction p_53227_, @NotNull BlockState p_53228_, LevelAccessor p_53229_, @NotNull BlockPos p_53230_, @NotNull BlockPos p_53231_) {
        p_53229_.scheduleTick(p_53230_, this, this.getDelayAfterPlace());
        return super.updateShape(p_53226_, p_53227_, p_53228_, p_53229_, p_53230_, p_53231_);
    }

    public void tick(@NotNull BlockState p_53216_, ServerLevel p_53217_, BlockPos p_53218_, @NotNull Random p_53219_) {
        if (isFree(p_53217_.getBlockState(p_53218_.below())) && p_53218_.getY() >= p_53217_.getMinBuildHeight() && (p_53217_.getBlockState(p_53218_)) == (new Object() {
            public BlockState with(BlockState _bs, Direction newValue) {
                Property<?> _prop = _bs.getBlock().getStateDefinition().getProperty("facing");
                if (_prop instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(newValue))
                    return _bs.setValue(_dp, newValue);
                _prop = _bs.getBlock().getStateDefinition().getProperty("axis");
                return _prop instanceof EnumProperty _ep && _ep.getPossibleValues().contains(newValue.getAxis())
                        ? _bs.setValue(_ep, newValue.getAxis())
                        : _bs;
            }
        }.with(p_53216_.getBlock().defaultBlockState(), Direction.DOWN))) {
            NodeCoreFallingBlockEntity fallingblockentity = NodeCoreFallingBlockEntity.fall(p_53217_, p_53218_, p_53216_);
            this.falling(fallingblockentity);
        }
    }

    protected void falling(NodeCoreFallingBlockEntity p_53206_) {
    }

    protected int getDelayAfterPlace() {
        return 2;
    }
}
