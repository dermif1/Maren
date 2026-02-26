package net.dermif1.maren.item;

import net.dermif1.maren.Maren;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Maren.MOD_ID);

    public static final DeferredItem<Item> SOUL_FRAGMENT = ITEMS.registerItem("soul_fragment",
            Item::new);

    // public static final DeferredItem<Item> CAVE_TURTLE = ITEMS.registerItem("cave_turtle",
    //         Item::new);
    // public static final DeferredItem<Item> SHELL_CAVE_TURTLE = ITEMS.registerItem("shell_cave_turtle",
    //         Item::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
