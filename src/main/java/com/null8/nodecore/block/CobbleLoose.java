
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

public class CobbleLoose extends FallingBlock {

    public CobbleLoose() {
        super(Properties.of(Material.DIRT, MaterialColor.STONE).sound(SoundType.GRAVEL).strength(0.5f));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(NodeCoreBlocks.COBBLE_LOOSE.get(), renderType -> renderType == RenderType.translucent());
    }
}
