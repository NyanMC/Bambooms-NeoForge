package com.chromanyan.bambooms;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class NoGriefingExplosionDamageCalculator extends ExplosionDamageCalculator {
    @Override
    public boolean shouldBlockExplode(@NotNull Explosion explosion, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull BlockState state, float p_46098_) {
        return state.getBlock() instanceof TntBlock;
    }
}
