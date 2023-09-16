package com.chromanyan.bambooms.entities;

import com.chromanyan.bambooms.NoGriefingExplosionDamageCalculator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrimedBamboom extends PrimedTnt {
    public PrimedBamboom(Level level, double x, double y, double z, @Nullable LivingEntity igniter) {
        super(level, x, y, z, igniter);
    }

    @Override
    protected void explode() {
        float f = 4.0F;
        this.level().explode(this, null, new NoGriefingExplosionDamageCalculator(), this.getX(), this.getY(0.0625D), this.getZ(), f, false, Level.ExplosionInteraction.TNT);
    }

    @Override
    public boolean shouldBlockExplode(@NotNull Explosion p_19987_, @NotNull BlockGetter p_19988_, @NotNull BlockPos p_19989_, @NotNull BlockState p_19990_, float p_19991_) {
        return false;
    }
}
