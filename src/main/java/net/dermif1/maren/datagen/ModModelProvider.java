package net.dermif1.maren.datagen;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.dermif1.maren.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, Maren.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.SOUL_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        /* BLOCKS */
        /*blockModels.woodProvider(ModBlocks.LUGTER_LOG.get()).log(ModBlocks.LUGTER_LOG.get()).log(ModBlocks.LUGTER_LOG.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_LUGTER_LOG.get()).log(ModBlocks.STRIPPED_LUGTER_LOG.get()).log(ModBlocks.STRIPPED_LUGTER_LOG.get());*/
        blockModels.family(ModBlocks.LUGTER_PLANKS.get())
                .fence(ModBlocks.LUGTER_FENCE.get())
                .fenceGate(ModBlocks.LUGTER_FENCE_GATE.get())
                .stairs(ModBlocks.LUGTER_STAIRS.get())
                .slab(ModBlocks.LUGTER_SLAB.get())
                .button(ModBlocks.LUGTER_BUTTON.get())
                .pressurePlate(ModBlocks.LUGTER_PRESSURE_PLATE.get())
                .door(ModBlocks.LUGTER_DOOR.get())
                .trapdoor(ModBlocks.LUGTER_TRAPDOOR.get());
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(x -> !x.is(ModBlocks.ANARCHY));
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream().filter(x -> x.get() != ModBlocks.ANARCHY.asItem());
    }
}
