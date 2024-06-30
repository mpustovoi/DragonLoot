package net.dragonloot.init;

import net.dragonloot.entity.DragonTridentEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityInit {

    public static final EntityType<DragonTridentEntity> DRAGON_TRIDENT_ENTITY = EntityType.Builder.<DragonTridentEntity>create(DragonTridentEntity::new, SpawnGroup.MISC).dimensions(0.5F, 0.5F).eyeHeight(0.13f).maxTrackingRange(4).trackingTickInterval(20).build();

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, Identifier.of("dragonloot", "dragon_trident"), DRAGON_TRIDENT_ENTITY);
    }

}
