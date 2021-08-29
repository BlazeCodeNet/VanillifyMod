package net.blazecode.vanillify.api.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface ItemStackProxy
{
    /**
     * Should get a vanilla item stack that will be sent to the client instead of
     * the given modded item stack
     *
     * @param original
     * @return Vanillifed item stack
     */
    public ItemStack getClientItemStack( ItemStack original);

    /**
     * Get the identifier that represents this item
     *
     * @return Modded Identifier
     */
    public Identifier getIdentifier();
}
