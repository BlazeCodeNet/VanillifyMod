package net.blazecode.vanillify.mixins.packets;

import net.blazecode.vanillify.api.ServerBlock;
import net.blazecode.vanillify.api.interfaces.BlockStateProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockUpdateS2CPacket.class)
public abstract class BlockUpdatePacketMixin implements Packet<ClientPlayPacketListener>
{
    @Mutable
    @Final
    @Shadow
    private BlockState state;
    
    private void doInitProxy()
    {
        Block blk = this.state.getBlock();
        if(blk instanceof BlockStateProxy )
        {
            this.state = (( BlockStateProxy )blk).getClientBlockState( this.state );
        }
    }
    
    @Inject( method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V", at = @At("RETURN") )
    void onInitPacketByteBuff( PacketByteBuf buf, CallbackInfo ci )
    {
        doInitProxy();
    }
    
    @Inject( method = "<init>(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)V", at = @At("RETURN"))
    void onInitBlockView( BlockView world, BlockPos pos, CallbackInfo ci )
    {
        doInitProxy();
    }
    
    @Inject( method = "<init>(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", at = @At("RETURN"))
    void onInitBlockPos( BlockPos pos, BlockState state, CallbackInfo ci )
    {
        doInitProxy();
    }
}
