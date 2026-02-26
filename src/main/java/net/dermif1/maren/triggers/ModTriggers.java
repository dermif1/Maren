package net.dermif1.maren.triggers;

import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class ModTriggers {
    public record ModTriggerInstance(Optional<ContextAwarePredicate> player, ItemPredicate predicate)
            implements SimpleCriterionTrigger.SimpleInstance {
        public boolean matches(ItemStack stack) {
            return this.predicate.test(stack);
        }
    }
}
