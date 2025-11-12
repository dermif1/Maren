package net.dermif1.maren.item;

import net.dermif1.maren.Maren;
import net.dermif1.maren.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Maren.MOD_ID);

    public static final Supplier<CreativeModeTab> MAREN_TAB = CREATIVE_MODE_TAB.register("maren_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LUGTER_PLANKS.get()))
                    .title(Component.translatable("creativetab.maren.maren_tab"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModItems.SOUL_FRAGMENT);
                                output.accept(ModBlocks.ANARCHY);
                                /*output.accept(ModBlocks.LUGTER_LOG);
                                output.accept(ModBlocks.STRIPPED_LUGTER_LOG);*/
                                output.accept(ModBlocks.LUGTER_PLANKS);
                                output.accept(ModBlocks.LUGTER_BUTTON);
                                output.accept(ModBlocks.LUGTER_DOOR);
                                output.accept(ModBlocks.LUGTER_FENCE);
                                output.accept(ModBlocks.LUGTER_FENCE_GATE);
                                output.accept(ModBlocks.LUGTER_PRESSURE_PLATE);
                                output.accept(ModBlocks.LUGTER_SLAB);
                                output.accept(ModBlocks.LUGTER_STAIRS);
                                output.accept(ModBlocks.LUGTER_TRAPDOOR);
                            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
