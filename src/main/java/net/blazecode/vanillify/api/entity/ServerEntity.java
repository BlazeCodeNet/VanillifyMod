package net.blazecode.vanillify.api.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
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
    
    public ServerEntity( EntityType<? extends ServerEntity> type, World world, Identifier identifier, EntityType representation )
    {
        super( type, world );
        this.representation = representation;
        this.identifier = identifier;
    }
    
    private final EntityType representation;
    private final Identifier identifier;

    @Override
    public EntityType getRepresentation(LivingEntity original)
    {
        return representation;
    }

    @Override
    public Identifier getIdentifier()
    {
        return identifier;
    }
}
