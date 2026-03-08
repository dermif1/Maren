package net.dermif1.maren.mixin.blocks;

import net.dermif1.maren.mixin.entity.EnchantingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {
    @Inject(method = "newBlockEntity", at = @At("HEAD"), cancellable = true)
    private void newBlockEntity(
            BlockPos pos,
            BlockState state,
            CallbackInfoReturnable<BlockEntity> cir
    ) {
        cir.setReturnValue(new EnchantingTableBlockEntity(pos, state));
    }

    @Inject(method = "useWithoutItem", at = @At("HEAD"), cancellable = true)
    private void skipGUI(BlockState state,
                         Level level,
                         BlockPos pos,
                         Player player,
                         BlockHitResult hit,
                         CallbackInfoReturnable<InteractionResult> cir) {
        cir.setReturnValue(InteractionResult.SUCCESS);
    }
}
