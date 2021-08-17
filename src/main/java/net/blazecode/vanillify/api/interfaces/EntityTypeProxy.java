package net.blazecode.vanillify.api.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public interface EntityTypeProxy
{
    /**
     * Takes a "ServerSide Entity" type as input and ouputs
     * the representational entity from vanilla to show the client
     *
     * @param original
     * @return Vanilla Entity Type
     */
    public EntityType getRepresentation(LivingEntity original);

    /**
     * Get the identifier that represents this item
     *
     * @return
     */
    public Identifier getIdentifier();
}
