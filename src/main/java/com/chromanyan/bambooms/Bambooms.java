package com.chromanyan.bambooms;

import com.chromanyan.bambooms.init.DispenserBehaviors;
import com.chromanyan.bambooms.init.ModBlocks;
import com.chromanyan.bambooms.init.ModEntities;
import com.chromanyan.bambooms.init.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Bambooms.MODID)
public class Bambooms
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "bambooms";
    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();

    public Bambooms()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DispenserBehaviors.registerBehaviors();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS)
            event.accept(ModItems.BAMBOOM_BLOCK_ITEM);
        if (event.getTabKey() == CreativeModeTabs.COMBAT)
            event.accept(ModItems.BAMBOOM_BLOCK_ITEM);
    }
}
