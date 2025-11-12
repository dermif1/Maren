package net.dermif1.maren.block.entity;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Maren.MOD_ID);

    public static final Supplier<BlockEntityType<AnarchyBlockEntity>> ANARCHY_BE =
            BLOCK_ENTITY.register("anarchy_be", () -> new BlockEntityType<>(
                    AnarchyBlockEntity::new, ModBlocks.ANARCHY.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY.register(eventBus);
    }
}
