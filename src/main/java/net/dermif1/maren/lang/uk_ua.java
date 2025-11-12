package net.dermif1.maren.lang;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.dermif1.maren.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class uk_ua extends LanguageProvider {
    public uk_ua(PackOutput output) {
        super(output, Maren.MOD_ID, "uk_ua");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.maren.maren_tab", "Вкладка Maren");

        add(ModBlocks.ANARCHY.get(), "Анарх");
        /*add(ModBlocks.LUGTER_LOG.get(), "Люктерове дерево");
        add(ModBlocks.STRIPPED_LUGTER_LOG.get(), "Обтесене люктерове дерево");*/
        add(ModBlocks.LUGTER_PLANKS.get(), "Люктерові дошки");
        add(ModBlocks.LUGTER_BUTTON.get(), "Люктерова кнопка");
        add(ModBlocks.LUGTER_DOOR.get(), "Люктерові двері");
        add(ModBlocks.LUGTER_FENCE.get(), "Люктерова огорожа");
        add(ModBlocks.LUGTER_FENCE_GATE.get(), "Люктерові ворота");
        add(ModBlocks.LUGTER_PRESSURE_PLATE.get(), "Люктерова натисна плита");
        add(ModBlocks.LUGTER_SLAB.get(), "Люктерова плита");
        add(ModBlocks.LUGTER_STAIRS.get(), "Люктерові сходи");
        add(ModBlocks.LUGTER_TRAPDOOR.get(), "Люктеровий люк");

        add(ModItems.SOUL_FRAGMENT.get(), "Фрагмент души");
    }
}
