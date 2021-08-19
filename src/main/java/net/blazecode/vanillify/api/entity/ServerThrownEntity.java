package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ServerThrownEntity extends SnowballEntity implements EntityTypeProxy
{
    public ServerThrownEntity(EntityType<? extends ServerThrownEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Override
    public EntityType getRepresentation()
    {
        return EntityType.SNOWBALL;
    }

    @Override
    protected Item getDefaultItem()
    {
        return Items.BEDROCK;
    }
}
