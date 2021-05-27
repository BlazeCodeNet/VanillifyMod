package net.blazecode.vanillify.api;

import net.blazecode.vanillify.api.gui.ServerScreenHandler;
import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ServerBlockItem extends BlockItem implements ItemStackProxy
{
    public ServerBlockItem( Block block, Item representation, Identifier id, Settings settings, Text customName )
    {
        super( block, settings );
        rep = representation;
        ident = id;
        displayName = customName;
    }
    
    @Override
    public ItemStack getClientItemStack( ItemStack original )
    {
        ItemStack changed = new ItemStack(rep);
        changed.setCount(original.getCount());
        changed.setCustomName( getServerSideName() );
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
    
    public Item getRepresentationItem()
    {
        return rep;
    }
    
    public Text getServerSideName()
    {
        return displayName;
    }
    
    private Item rep;
    private Identifier ident;
    private Text displayName;
    
}
