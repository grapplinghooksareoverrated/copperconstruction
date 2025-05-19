package com.rasterdog.copperconstruction.item;

import com.rasterdog.copperconstruction.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class CopperWand extends Item {
    public CopperWand(Properties properties) {
        super(properties);
    }

    private boolean tryPlaceBlock(Level level, BlockState state, BlockPos pos) {
        if (state.isAir()) {
            level.setBlock(pos, ModBlocks.DRAFTWOOL_BLOCK.get().defaultBlockState(), 3);
            return true;
        }
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Get the block the player is looking at
            HitResult hitResult = player.pick(5.0D, 0.0F, false);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                BlockPos hitPos = blockHit.getBlockPos();

                // try place above current target
                BlockPos pendPos = hitPos.above();
                BlockState pendBlockState = level.getBlockState(pendPos);
                if (tryPlaceBlock(level, pendBlockState, pendPos)) {


                    Direction playerDirection = player.isCrouching() ? Direction.UP : Direction.fromYRot(player.getYRot());

                    for (int i = 0; i < 2; i++) {
                        pendPos = pendPos.relative(playerDirection);
                        pendBlockState = level.getBlockState(pendPos);
                        if (!tryPlaceBlock(level, pendBlockState, pendPos)) {
                            break;
                        }
                    }

                    player.swing(hand);
                    return InteractionResultHolder.success(stack);
                }

            }
        }

        return InteractionResultHolder.fail(stack);

    }

}
