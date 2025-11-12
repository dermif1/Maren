package net.dermif1.maren.datagen;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, Maren.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ANARCHY.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.LUGTER_PLANKS.get());

        tag(BlockTags.FENCES).add(ModBlocks.LUGTER_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.LUGTER_FENCE_GATE.get());
    }
}
