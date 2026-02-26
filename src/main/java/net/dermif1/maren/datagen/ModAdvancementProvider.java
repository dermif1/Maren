package net.dermif1.maren.datagen;

import net.dermif1.maren.block.ModBlocks;
import net.dermif1.maren.item.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> saver) {

    }

    public void build(String name, String parent, Item display, int rarity,
                       boolean show_adv, boolean announce_adv, boolean should_be_hide, String trigger_name,
                       Item item_need, int experience, Consumer<AdvancementHolder> saver) {

        AdvancementType advancementType = AdvancementType.TASK;

        if(rarity==1) {
            advancementType = AdvancementType.GOAL;
        } else if(rarity==2) {
            advancementType = AdvancementType.CHALLENGE;
        }

        Advancement.Builder builder = Advancement.Builder.advancement();
        builder.parent(AdvancementSubProvider.createPlaceholder(parent));
        builder.display(
                new ItemStack(display.asItem()),
                Component.translatable("advancements.maren."+name.trim()+".title"),
                Component.translatable("advancements.maren."+name.trim()+".description"),
                null,
                advancementType,
                show_adv,
                announce_adv,
                should_be_hide
        );

        builder.addCriterion(trigger_name, InventoryChangeTrigger.TriggerInstance.hasItems(item_need));
        builder.requirements(AdvancementRequirements.allOf(List.of(trigger_name)));
        builder.rewards(AdvancementRewards.Builder.experience(experience));

        builder.save(saver, Identifier.fromNamespaceAndPath("maren", name));
    }
}
