package net.dermif1.maren.datagen;

import net.dermif1.maren.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ANARCHY.get());
        dropSelf(ModBlocks.LUGTER_PLANKS.get());
        dropSelf(ModBlocks.LUGTER_TRAPDOOR.get());
        dropSelf(ModBlocks.LUGTER_FENCE.get());
        dropSelf(ModBlocks.LUGTER_FENCE_GATE.get());
        dropSelf(ModBlocks.LUGTER_BUTTON.get());
        dropSelf(ModBlocks.LUGTER_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LUGTER_STAIRS.get());

        add(ModBlocks.LUGTER_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.LUGTER_SLAB.get()));
        add(ModBlocks.LUGTER_DOOR.get(),
                block -> createDoorTable(ModBlocks.LUGTER_DOOR.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
