package net.blazecode.vanillify.api.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.world.World;

public class ServerEntity extends ArmorStandEntity
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
    
    public ServerEntity( EntityType<? extends ArmorStandEntity> type, World world, EntityType representation )
    {
        super( type, world );
        this.representation = representation;
    }
    
    public EntityType getRepresentation()
    {
        return representation;
    }
    
    private final EntityType representation;
}
