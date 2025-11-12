package net.dermif1.maren.lang;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.dermif1.maren.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class en_us extends LanguageProvider {
    public en_us(PackOutput output) {
        super(output, Maren.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.maren.maren_tab", "Maren Tab");

        add(ModBlocks.ANARCHY.get(), "Anarchy");
        /*add(ModBlocks.LUGTER_LOG.get(), "Lugter log");
        add(ModBlocks.STRIPPED_LUGTER_LOG.get(), "Stripped lugter log");*/
        add(ModBlocks.LUGTER_PLANKS.get(), "Lugter planks");
        add(ModBlocks.LUGTER_BUTTON.get(), "Lugter button");
        add(ModBlocks.LUGTER_DOOR.get(), "Lugter door");
        add(ModBlocks.LUGTER_FENCE.get(), "Lugter fence");
        add(ModBlocks.LUGTER_FENCE_GATE.get(), "Lugter fence gate");
        add(ModBlocks.LUGTER_PRESSURE_PLATE.get(), "Lugter pressure plate");
        add(ModBlocks.LUGTER_SLAB.get(), "Lugter slab");
        add(ModBlocks.LUGTER_STAIRS.get(), "Lugter stairs");
        add(ModBlocks.LUGTER_TRAPDOOR.get(), "Lugter trapdoor");

        add(ModItems.SOUL_FRAGMENT.get(), "Soul fragment");
    }
}
