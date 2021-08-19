package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ServerEntity extends ArmorStandEntity implements EntityTypeProxy
{
    @Override
    public boolean isFireImmune( )
    {
        return true;
    }
    
    @Override
    public boolean isMarker( )
    {
        return true;
    }
    
    public ServerEntity(EntityType<? extends ServerEntity> type, World world )
    {
        super(type, world );
    }

    @Override
    public EntityType getRepresentation()
    {
        return EntityType.ARMOR_STAND;
    }
}
