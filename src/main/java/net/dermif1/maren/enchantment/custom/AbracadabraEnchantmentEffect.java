package net.dermif1.maren.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AbracadabraEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AbracadabraEnchantmentEffect> CODEC = MapCodec.unit(AbracadabraEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        Entity newEntity;
        int rand = serverLevel.random.nextInt(3);
        if (rand==0) {
            newEntity = EntityType.SHEEP.spawn(serverLevel, entity.getOnPos().above(), EntitySpawnReason.TRIGGERED);
        } else if (rand==1) {
            newEntity = EntityType.RABBIT.spawn(serverLevel, entity.getOnPos().above(), EntitySpawnReason.TRIGGERED);
        } else {
            newEntity = EntityType.GOAT.spawn(serverLevel, entity.getOnPos().above(), EntitySpawnReason.TRIGGERED);
        }
        entity.discard();
        assert newEntity != null;
        if (entity.getCustomName() != null) {
            newEntity.setCustomName(Component.literal(entity.getCustomName().getString() + " " + newEntity.getName().getString()));
        } else {
            newEntity.setCustomName(Component.literal(entity.getName().getString() + " " + newEntity.getName().getString()));
        }
        newEntity.setCustomNameVisible(true);
        System.out.println(newEntity.getCustomName().getString());
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
