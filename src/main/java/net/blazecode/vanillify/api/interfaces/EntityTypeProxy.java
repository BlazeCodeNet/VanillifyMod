package net.blazecode.vanillify.api.interfaces;

import net.minecraft.entity.EntityType;

public interface EntityTypeProxy
{
    /**
     * Outputs the representational entity from vanilla to show the client
     *
     * @return Vanilla Entity Type
     */
    public EntityType getRepresentation();
}
