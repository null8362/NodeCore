
package com.null8.nodecore.block;

import com.null8.nodecore.init.NodeCoreBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DirtLoose extends FallingBlock {

    public DirtLoose() {
        super(Properties.of(Material.DIRT, MaterialColor.DIRT).sound(SoundType.GRAVEL).strength(1f));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(NodeCoreBlocks.DIRT_LOOSE.get(), renderType -> renderType == RenderType.translucent());
    }
}
