package com.chromanyan.bambooms;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Bambooms.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue BLAST_POWER = BUILDER
            .comment("The amount of power a Bamboom explosion has. This is used in determining the blast radius and other factors.")
            .defineInRange("blastPower", 4D, 0, Float.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static float blastPower;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        blastPower = BLAST_POWER.get().floatValue();
    }
}
