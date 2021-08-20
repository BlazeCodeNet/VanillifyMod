package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ServerThrownEntity extends ThrownItemEntity implements EntityTypeProxy
{
    public ServerThrownEntity(EntityType<? extends ServerThrownEntity> entityType, World world)
    {
        super(entityType, world);
        setItem(new ItemStack(getShownItem()));
    }

    public ServerThrownEntity(EntityType<? extends ServerThrownEntity> entityType, LivingEntity livingEntity, World world)
    {
        super(entityType, livingEntity, world);
        setItem(new ItemStack(getShownItem()));
    }

    public Item getShownItem()
    {
        return getDefaultItem();
    }

    @Override
    public EntityType getRepresentation()
    {
        return EntityType.SNOWBALL;
    }

    @Override
    protected Item getDefaultItem()
    {
        return Items.SNOWBALL;
    }
}
