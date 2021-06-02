package net.blazecode.vanillify.api.block;

import net.blazecode.vanillify.api.interfaces.BlockStateProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ServerBlock extends Block implements BlockStateProxy
{
    public ServerBlock( Block representation, Identifier identifier, Settings settings, Text displayName )
    {
        super( settings );
        this.representation = representation;
        this.identifier = identifier;
        this.displayName = displayName;
    }
    
    @Override
    public BlockState getClientBlockState( BlockState original )
    {
        return representation.getDefaultState();
    }
    
    @Override
    public Identifier getId( )
    {
        return identifier;
    }
    
    public Text getDisplayName ( )
    {
        if( displayName==null )
        {
            return super.getName();
        }
        else
        {
            return displayName;
        }
    }
    
    private Block representation;
    private Identifier identifier;
    private Text displayName;
}
