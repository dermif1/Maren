package net.dermif1.maren.mixin.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MinecraftBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "minecraft");

//    public static final Supplier<BlockEntityType<net.dermif1.maren.block.entity.EnchantingTableBlockEntity>> ENCHANTING_TABLE_BE =
//            BLOCK_ENTITY.register("enchanting_table_be", () -> new BlockEntityType<>(
//                    EnchantingTableBlockEntity::new, MinecraftBlocks.ENCHANTING_TABLE.get()));
}
