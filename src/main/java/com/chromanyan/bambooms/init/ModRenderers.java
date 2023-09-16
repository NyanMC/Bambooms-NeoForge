package com.chromanyan.bambooms.init;

import com.chromanyan.bambooms.renderers.BamboomRenderer;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderers {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PRIMED_BAMBOOM.get(), BamboomRenderer::new);
        event.registerEntityRenderer(ModEntities.MINECART_BAMBOOM.get(), TntMinecartRenderer::new);
    }

}
