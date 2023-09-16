package com.chromanyan.bambooms.init;

import com.chromanyan.bambooms.Bambooms;
import com.chromanyan.bambooms.entities.PrimedBamboom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Bambooms.MODID);

    public static final RegistryObject<EntityType<PrimedBamboom>> PRIMED_BAMBOOM = ENTITY_TYPES.register("bamboom", () -> EntityType.Builder.of((EntityType<PrimedBamboom> type, Level level) -> new PrimedBamboom(type, level), MobCategory.MISC).fireImmune().sized(0.98F, 0.98F).clientTrackingRange(10).updateInterval(10).build("bamboom"));
}
