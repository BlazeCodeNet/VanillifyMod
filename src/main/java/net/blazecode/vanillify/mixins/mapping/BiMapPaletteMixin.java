package net.blazecode.vanillify.mixins.mapping;

import net.blazecode.vanillify.api.interfaces.BlockStateProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.chunk.BiMapPalette;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BiMapPalette.class)
public class BiMapPaletteMixin
{
    @ModifyArg( method = "toPacket", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;writeVarInt(I)Lnet/minecraft/network/PacketByteBuf;", ordinal = 1), index = 0 )
    private int proxyBlockIDInjection(int original)
    {
        BlockState state = Block.getStateFromRawId( original );
        Block blk = state.getBlock();
        
        if( blk instanceof BlockStateProxy )
        {
            return Block.getRawIdFromState( ((BlockStateProxy)blk).getClientBlockState( state ) );
        }
        
        return original;
    }
}
