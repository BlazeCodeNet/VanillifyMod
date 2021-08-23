package net.blazecode.vanillify.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ServerBlockWithEntity extends ServerBlock implements BlockEntityProvider
{
    public ServerBlockWithEntity( Block representation, Identifier identifier, Settings settings, Text displayName )
    {
        super( representation, identifier, settings, displayName );
    }
    
    public BlockRenderType getRenderType( BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
    
    public boolean onSyncedBlockEvent( BlockState state, World world, BlockPos pos, int type, int data) {
        super.onSyncedBlockEvent(state, world, pos, type, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity == null ? false : blockEntity.onSyncedBlockEvent(type, data);
    }
    
    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory( BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }
    
    /**
     * Returns the ticker if the given type and expected type are the same, or null if they are different.
     */
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType( BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }
}
