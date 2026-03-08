package net.dermif1.maren.enchantment;

import com.mojang.serialization.MapCodec;
import net.dermif1.maren.Maren;
import net.dermif1.maren.enchantment.custom.AbracadabraEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Maren.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> ABRACADABRA =
            ENTITY_ENCHANTMENT_EFFECTS.register("abracadabra", () -> AbracadabraEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
