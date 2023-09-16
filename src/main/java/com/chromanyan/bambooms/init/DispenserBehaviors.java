package com.chromanyan.bambooms.init;

import com.chromanyan.bambooms.entities.PrimedBamboom;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class DispenserBehaviors {
    public static void registerBehaviors() {
        DispenserBlock.registerBehavior(ModItems.BAMBOOM_BLOCK_ITEM.get(), new DefaultDispenseItemBehavior() {
            protected @NotNull ItemStack execute(@NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                Level level = blockSource.getLevel();
                BlockPos blockpos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
                PrimedBamboom primedBamboom = new PrimedBamboom(level, (double)blockpos.getX() + 0.5D, blockpos.getY(), (double)blockpos.getZ() + 0.5D, null);
                level.addFreshEntity(primedBamboom);
                level.playSound(null, primedBamboom.getX(), primedBamboom.getY(), primedBamboom.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.ENTITY_PLACE, blockpos);
                itemStack.shrink(1);
                return itemStack;
            }
        });
    }
}
