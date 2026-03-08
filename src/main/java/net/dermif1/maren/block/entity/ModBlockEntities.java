package net.dermif1.maren.block.entity;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.dermif1.maren.mixin.entity.EnchantingTableBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
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

    public static final Supplier<BlockEntityType<EnchantingTableBlockEntity>> ENCHANTING_TABLE_BE =
            BLOCK_ENTITY.register("enchanting_table_be", () -> new BlockEntityType<>(
                    EnchantingTableBlockEntity::new, Blocks.ENCHANTING_TABLE));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY.register(eventBus);
    }
}