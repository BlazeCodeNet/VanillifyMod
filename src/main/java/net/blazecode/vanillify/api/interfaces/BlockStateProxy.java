package net.blazecode.vanillify.api.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public interface BlockStateProxy
{
    /**
     * Should get a vanilla item stack that will be sent to the client instead of
     * the given modded item stack
     *
     * @param original
     * @return Vanillifed item stack
     */
    public BlockState getClientBlockState( BlockState original);
    
    /**
     * Get the identifier that represents this item
     *
     * @return
     */
    public Identifier getId();
}
