package com.chromanyan.bambooms.entities;

import com.chromanyan.bambooms.NoGriefingExplosionDamageCalculator;
import com.chromanyan.bambooms.init.ModBlocks;
import com.chromanyan.bambooms.init.ModEntities;
import com.chromanyan.bambooms.init.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class MinecartBamboom extends MinecartTNT {
    public MinecartBamboom(EntityType<? extends MinecartBamboom> p_38649_, Level p_38650_) {
        super(p_38649_, p_38650_);
    }

    public MinecartBamboom(Level level, double x, double y, double z) {
        this(ModEntities.MINECART_BAMBOOM.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public @NotNull BlockState getDefaultDisplayBlockState() {
        return ModBlocks.BAMBOOM_BLOCK.get().defaultBlockState();
    }

    @Override
    protected @NotNull Item getDropItem() {
        return ModItems.BAMBOOM_MINECART_ITEM.get();
    }

    @Override
    protected void explode(@Nullable DamageSource p_259539_, double p_260287_) {
        if (!this.level().isClientSide) {
            double d0 = Math.sqrt(p_260287_);
            if (d0 > 5.0D) {
                d0 = 5.0D;
            }

            this.level().explode(this, p_259539_, new NoGriefingExplosionDamageCalculator(), this.getX(), this.getY(), this.getZ(), (float)(4.0D + this.random.nextDouble() * 1.5D * d0), false, Level.ExplosionInteraction.TNT);
            this.discard();
        }

    }
}
