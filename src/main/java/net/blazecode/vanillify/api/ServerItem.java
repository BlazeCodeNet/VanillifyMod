package net.blazecode.vanillify.api;

import net.blazecode.vanillify.api.gui.ServerScreenHandler;
import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ServerItem extends Item implements ItemStackProxy
{
    
    public ServerItem( Item representation, Identifier identifier, Item.Settings settings, Text clientSideName)
    {
        super( settings );
        rep = representation;
        ident = identifier;
        displayName = clientSideName;
    }
    
    @Override
    public ItemStack getClientItemStack ( ItemStack original )
    {
        ItemStack changed = new ItemStack(rep);
        changed.setCount(original.getCount());
        changed.setCustomName( getServerSideName() );
        changed.addEnchantment( Enchantments.UNBREAKING, 1 );
        return changed;
    }
    
    @Override
    public Identifier getId ( )
    {
        return ident;
    }
    
    @Override
    public boolean hasGlint ( ItemStack stack )
    {
        return true;
    }
    
    @Override
    public Text getName ( )
    {
        if( displayName == null )
        {
            return super.getName();
        }
        else
        {
            return getServerSideName();
        }
    }
    
    public Text getServerSideName()
    {
        return displayName;
    }
    
    public Item getRepresentationItem()
    {
        return rep;
    }
    
    private Item rep;
    private Identifier ident;
    private Text displayName;
}
