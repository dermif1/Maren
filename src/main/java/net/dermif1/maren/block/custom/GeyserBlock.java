package net.dermif1.maren.block.custom;

import com.mojang.serialization.MapCodec;
import net.dermif1.maren.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class GeyserBlock extends Block {
    public static final MapCodec<GeyserBlock> CODEC = simpleCodec(GeyserBlock::new);
    public static final VoxelShape SHAPE = Block.box(0,0,0, 16, 14, 16);
    private boolean soundWillStart=true;


    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public GeyserBlock(Properties p_49795_) {
        super(p_49795_.randomTicks());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // ONLY FOR TESTS!!!
    //@Override
    //protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    //    if(!level.isClientSide()) {
    //        System.out.println("Geyser are using now on pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
    //        level.scheduleTick(pos, this, 20);
    //        System.out.println("Geyser was end on: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
    //    }
//
    //    return InteractionResult.SUCCESS;
    //}

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        System.out.println("Geyser are using now on pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
        level.scheduleTick(pos, this, 20);
        System.out.println("Geyser was end on: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isWaterAt(pos.below())) {
            if (soundWillStart) {
                level.playSound(null, pos, ModSounds.GEYSER_ERUPTION.get(), SoundSource.BLOCKS);
                soundWillStart=false;
            }
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + 0.5;

            double heightPower = 0.75;
            double spread = 0.45;

            for (int i = 0; i < 15; i++) {
                double dy = heightPower + (level.random.nextDouble() * 0.5);

                double dx = (level.random.nextDouble() - 0.5) * spread;
                double dz = (level.random.nextDouble() - 0.5) * spread;

                level.sendParticles(ParticleTypes.CLOUD, x, y, z, 0, dx * 0.5, dy * 0.75, dz * 0.5, 0.75);
            }

            AABB geyzerArea = new AABB(pos.above()).expandTowards(0, 4, 0);
            List<Entity> entities = level.getEntitiesOfClass(Entity.class, geyzerArea);

            for (Entity entity : entities) {
                entity.setDeltaMovement(entity.getDeltaMovement().x, 0.8, entity.getDeltaMovement().z);
                entity.hurtMarked = true;
            }

            if (random.nextInt(60) > 0) {
                level.scheduleTick(pos, this, 1);
            } else {
                soundWillStart=true;
                Minecraft.getInstance().getSoundManager().stop(ModSounds.GEYSER_ERUPTION.get().location(), SoundSource.BLOCKS);
            }

        }
    }
}
