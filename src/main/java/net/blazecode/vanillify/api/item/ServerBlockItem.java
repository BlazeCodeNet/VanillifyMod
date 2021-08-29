package net.blazecode.vanillify.api.item;

import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ServerBlockItem extends BlockItem implements ItemStackProxy
{
    public ServerBlockItem( Block block, Item representation, Identifier identifier, Settings settings, Text displayName )
    {
        super( block, settings );
        this.representation = representation;
        this.identifier = identifier;
        this.displayName = displayName;
    }
    
    @Override
    public ItemStack getClientItemStack( ItemStack original )
    {
        ItemStack changed = new ItemStack(representation);
        changed.setCount(original.getCount());
        changed.setCustomName( getServerSideName() );
        return changed;
    }
    
    
    @Override
    public Identifier getIdentifier ( )
    {
        return identifier;
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
        return representation;
    }
    
    public Text getServerSideName()
    {
        return displayName;
    }
    
    private Item representation;
    private Identifier identifier;
    private Text displayName;
    
}
