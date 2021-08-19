package net.blazecode.vanillify.testing;

import net.blazecode.vanillify.VanillifyMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TestRegistery
{
    public static void init()
    {
        Registry.register(Registry.ITEM, new Identifier(VanillifyMod.MODID, "test_item"), TEST_ITEM);
        TEST_ENTITY = register("test_entity", FabricEntityTypeBuilder.<TestEntity>create(SpawnGroup.MISC, TestEntity::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(64).trackedUpdateRate(3).build());
    }

    public static EntityType<TestEntity> TEST_ENTITY;
    public static TestItem TEST_ITEM = new TestItem();

    public static <T extends Entity> EntityType<T> register(String registryName, EntityType<T> entityType)
    {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(VanillifyMod.MODID, registryName), entityType);
    }
}
