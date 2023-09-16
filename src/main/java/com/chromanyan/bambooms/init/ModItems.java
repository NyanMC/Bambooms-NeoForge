package com.chromanyan.bambooms.init;

import com.chromanyan.bambooms.Bambooms;
import com.chromanyan.bambooms.items.BamboomMinecartItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Bambooms.MODID);
    public static final RegistryObject<Item> BAMBOOM_BLOCK_ITEM = ITEMS.register("bamboom", () -> new BlockItem(ModBlocks.BAMBOOM_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAMBOOM_MINECART_ITEM = ITEMS.register("bamboom_minecart", BamboomMinecartItem::new);
}
