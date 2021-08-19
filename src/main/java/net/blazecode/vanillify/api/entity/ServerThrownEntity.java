package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ServerThrownEntity extends ThrownItemEntity implements EntityTypeProxy
{
    public ServerThrownEntity(EntityType<Entity> entityType, World world)
    {
        super((EntityType) entityType, world);
    }

    @Override
    public EntityType getRepresentation(LivingEntity original)
    {
        return EntityType.SNOWBALL;
    }

    @Override
    public Identifier getIdentifier()
    {
        return null;
    }

    @Override
    protected Item getDefaultItem()
    {
        return Items.SLIME_BALL;
    }
}
