package com.chromanyan.bambooms.items;

import com.chromanyan.bambooms.entities.MinecartBamboom;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class BamboomMinecartItem extends MinecartItem {
    public BamboomMinecartItem() {
        super(AbstractMinecart.Type.TNT, new Item.Properties().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if(!state.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        }
        ItemStack stack = context.getItemInHand();
        double railHeight = 0;
        if(!level.isClientSide) {
            RailShape rail = state.getBlock() instanceof BaseRailBlock ? ((BaseRailBlock)state.getBlock()).getRailDirection(state, level, pos, null) : RailShape.NORTH_SOUTH;
            if (rail.isAscending()) {
                railHeight = 0.5D;
            }
        }
        MinecartBamboom minecart = new MinecartBamboom(level, pos.getX() + 0.5f, pos.getY() + 0.0625f + railHeight, pos.getZ() + 0.5f);
        level.addFreshEntity(minecart);
        if (stack.hasCustomHoverName()) {
            minecart.setCustomName(stack.getHoverName());
        }
        level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, pos);
        stack.shrink(1);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
