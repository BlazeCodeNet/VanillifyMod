package net.blazecode.vanillify.testing;

import net.blazecode.vanillify.VanillifyMod;
import net.blazecode.vanillify.api.entity.ServerThrownEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class TestEntity extends ServerThrownEntity
{
    public TestEntity(World world)
    {
        super( TestRegistery.TEST_ENTITY, world);
    }

    public TestEntity(EntityType<TestEntity> entityType, World world)
    {
        super(entityType, world);
    }
}
