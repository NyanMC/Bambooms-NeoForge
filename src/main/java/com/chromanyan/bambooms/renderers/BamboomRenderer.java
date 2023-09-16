package com.chromanyan.bambooms.renderers;

import com.chromanyan.bambooms.entities.PrimedBamboom;
import com.chromanyan.bambooms.init.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.NotNull;

public class BamboomRenderer extends EntityRenderer<PrimedBamboom> {
    private final BlockRenderDispatcher blockRenderer;

    public BamboomRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
        this.shadowRadius = 0.5F;
        this.blockRenderer = p_174008_.getBlockRenderDispatcher();
    }

    public void render(PrimedBamboom p_116177_, float p_116178_, float p_116179_, PoseStack p_116180_, @NotNull MultiBufferSource p_116181_, int p_116182_) {
        p_116180_.pushPose();
        p_116180_.translate(0.0F, 0.5F, 0.0F);
        int i = p_116177_.getFuse();
        if ((float)i - p_116179_ + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - p_116179_ + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            p_116180_.scale(f1, f1, f1);
        }

        p_116180_.mulPose(Axis.YP.rotationDegrees(-90.0F));
        p_116180_.translate(-0.5F, -0.5F, 0.5F);
        p_116180_.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, ModBlocks.BAMBOOM_BLOCK.get().defaultBlockState(), p_116180_, p_116181_, p_116182_, i / 5 % 2 == 0);
        p_116180_.popPose();
        super.render(p_116177_, p_116178_, p_116179_, p_116180_, p_116181_, p_116182_);
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull PrimedBamboom p_116175_) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
