package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ServerThrownEntity extends ThrownItemEntity implements EntityTypeProxy
{
    public ServerThrownEntity(EntityType<? extends ServerThrownEntity> entityType, World world, Identifier identifier, Item shownItem)
    {
        super(entityType, world);
        this.shownItem = shownItem;
        this.identifier = identifier;
    }

    private final Item shownItem;
    private final Identifier identifier;

    @Override
    public EntityType getRepresentation(LivingEntity original)
    {
        return EntityType.SNOWBALL;
    }

    @Override
    public Identifier getIdentifier()
    {
        return identifier;
    }

    @Override
    protected Item getDefaultItem()
    {
        return shownItem;
    }
}
