package net.dermif1.maren.mixin.blocks;

import net.dermif1.maren.mixin.entity.EnchantingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void onUseItemOn(ItemStack stack, BlockState state, Level level,
                             BlockPos pos, Player player, InteractionHand hand,
                             BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {

        if (state.getBlock() instanceof EnchantingTableBlock) {
            if (!level.isClientSide()) {
                if (level.getBlockEntity(pos) instanceof EnchantingTableBlockEntity be) {

                    // Рука порожня — забираємо предмет
                    if (stack.isEmpty()) {
                        if (!be.inventory.getStackInSlot(0).isEmpty()) {
                            ItemStack stackOnPedestal = be.inventory.extractItem(0, 1, false);
                            player.setItemInHand(InteractionHand.MAIN_HAND, stackOnPedestal);
                            be.clearContents();
                            level.playSound(null, pos, SoundEvents.ITEM_PICKUP,
                                    SoundSource.BLOCKS, 1f, 1f);
                        }
                        // Рука не порожня — кладемо предмет
                    } else if (be.inventory.getStackInSlot(0).isEmpty()) {
                        be.inventory.insertItem(0, stack.copy(), false);
                        stack.shrink(1);
                        level.playSound(null, pos, SoundEvents.ITEM_PICKUP,
                                SoundSource.BLOCKS, 1f, 2f);
                    }
                }
            }
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}