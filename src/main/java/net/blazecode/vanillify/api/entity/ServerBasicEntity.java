package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.world.World;

public class ServerBasicEntity extends ArmorStandEntity implements EntityTypeProxy
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
    
    public ServerBasicEntity( EntityType<? extends ServerBasicEntity> type, World world )
    {
        super(type, world );
    }

    @Override
    public EntityType getRepresentation()
    {
        return EntityType.ARMOR_STAND;
    }
}
