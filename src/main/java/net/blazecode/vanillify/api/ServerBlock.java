package net.blazecode.vanillify.api;

import net.blazecode.vanillify.api.interfaces.BlockStateProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ServerBlock extends Block implements BlockStateProxy
{
    public ServerBlock( Block representation, Identifier realIdent, Settings settings, Text customName )
    {
        super( settings );
        rep = representation;
        ident = realIdent;
        displayName = customName;
    }
    
    @Override
    public BlockState getClientBlockState( BlockState original )
    {
        BlockState ret = rep.getDefaultState();
        return ret;
    }
    
    @Override
    public Identifier getId( )
    {
        return ident;
    }
    
    public Text getDisplayName ( )
    {
        if(displayName==null)
        {
            return super.getName();
        }
        else
        {
            return displayName;
        }
    }
    
    private Block rep;
    private Identifier ident;
    private Text displayName;
}
