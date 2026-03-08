package net.dermif1.maren.enchantment;

import net.dermif1.maren.Maren;
import net.dermif1.maren.enchantment.custom.AbracadabraEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

import java.util.List;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> ABRACADABRA = ResourceKey.create(Registries.ENCHANTMENT,
            Identifier.fromNamespaceAndPath(Maren.MOD_ID, "abracadabra"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, ABRACADABRA, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.SWORDS),
                20,
                1,
                Enchantment.constantCost(25),
                Enchantment.constantCost(100),
                75,
                EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new AbracadabraEnchantmentEffect())
                .withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                Identifier.fromNamespaceAndPath(Maren.MOD_ID, "damage_disable"),
                                Attributes.ATTACK_DAMAGE,
                                LevelBasedValue.constant(-0.99f),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                ));
    }


    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key,
                                 Enchantment.Builder builder) {
        registry.register(key, builder.build(key.identifier()));
    }
}
