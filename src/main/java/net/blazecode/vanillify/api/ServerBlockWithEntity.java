package net.blazecode.vanillify.api;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ServerBlockWithEntity extends ServerBlock implements BlockEntityProvider
{
    protected ServerBlockWithEntity( Block representation, Identifier identifier, AbstractBlock.Settings settings, Text displayName)
    {
        super(representation, identifier, settings, displayName);
    }
    
    public BlockRenderType getRenderType( BlockState state)
    {
        return BlockRenderType.INVISIBLE;
    }
    
    public boolean onSyncedBlockEvent( BlockState state, World world, BlockPos pos, int type, int data)
    {
        super.onSyncedBlockEvent(state, world, pos, type, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity == null ? false : blockEntity.onSyncedBlockEvent(type, data);
    }
    
    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory( BlockState state, World world, BlockPos pos)
    {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }
}
