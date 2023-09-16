package com.chromanyan.bambooms.entities;

import com.chromanyan.bambooms.Config;
import com.chromanyan.bambooms.NoGriefingExplosionDamageCalculator;
import com.chromanyan.bambooms.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrimedBamboom extends PrimedTnt {

    @Nullable
    private LivingEntity owner;
    public PrimedBamboom(Level level, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntities.PRIMED_BAMBOOM.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = igniter;
    }

    public PrimedBamboom(EntityType<? extends PrimedBamboom> p_32076_, Level p_32077_) {
        super(p_32076_, p_32077_);
        this.blocksBuilding = true;
    }

    @Override
    protected void explode() {
        float f = Config.blastPower;
        this.level().explode(this, null, new NoGriefingExplosionDamageCalculator(), this.getX(), this.getY(0.0625D), this.getZ(), f, false, Level.ExplosionInteraction.TNT);
    }

    @Override
    public boolean shouldBlockExplode(@NotNull Explosion p_19987_, @NotNull BlockGetter p_19988_, @NotNull BlockPos p_19989_, @NotNull BlockState p_19990_, float p_19991_) {
        return false;
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return owner;
    }
}
