package com.chromanyan.bambooms.init;

import com.chromanyan.bambooms.entities.MinecartBamboom;
import com.chromanyan.bambooms.entities.PrimedBamboom;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class DispenserBehaviors {
    public static void registerBehaviors() {
        DispenserBlock.registerBehavior(ModItems.BAMBOOM_MINECART_ITEM.get(), new DefaultDispenseItemBehavior() {
            protected @NotNull ItemStack execute(@NotNull BlockSource blockSource, @NotNull ItemStack itemStack) {
                Direction direction = blockSource.getBlockState().getValue(DispenserBlock.FACING);
                Level level = blockSource.getLevel();
                double d0 = blockSource.x() + (double)direction.getStepX() * 1.125D;
                double d1 = Math.floor(blockSource.y()) + (double)direction.getStepY();
                double d2 = blockSource.z() + (double)direction.getStepZ() * 1.125D;
                BlockPos blockpos = blockSource.getPos().relative(direction);
                BlockState blockstate = level.getBlockState(blockpos);
                RailShape railshape = blockstate.getBlock() instanceof BaseRailBlock ? ((BaseRailBlock)blockstate.getBlock()).getRailDirection(blockstate, level, blockpos, null) : RailShape.NORTH_SOUTH;
                double d3;
                if (blockstate.is(BlockTags.RAILS)) {
                    if (railshape.isAscending()) {
                        d3 = 0.6D;
                    } else {
                        d3 = 0.1D;
                    }
                } else {
                    if (!blockstate.isAir() || !level.getBlockState(blockpos.below()).is(BlockTags.RAILS)) {
                        return new DefaultDispenseItemBehavior().dispense(blockSource, itemStack);
                    }

                    BlockState blockstate1 = level.getBlockState(blockpos.below());
                    @SuppressWarnings("deprecation")
                    RailShape railshape1 = blockstate1.getBlock() instanceof BaseRailBlock ? blockstate1.getValue(((BaseRailBlock)blockstate1.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
                    if (direction != Direction.DOWN && railshape1.isAscending()) {
                        d3 = -0.4D;
                    } else {
                        d3 = -0.9D;
                    }
                }
                MinecartBamboom minecart = new MinecartBamboom(level, d0, d1 + d3, d2);
                if (itemStack.hasCustomHoverName()) {
                    minecart.setCustomName(itemStack.getHoverName());
                }

                level.addFreshEntity(minecart);
                itemStack.shrink(1);
                return itemStack;
            }
        });

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
