package net.blazecode.vanillify.mixins.ents;

import com.mojang.authlib.GameProfile;
import net.blazecode.vanillify.api.listeners.BlockActionListener;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( ServerPlayerEntity.class )
public abstract class ServerPlayerMixin extends PlayerEntity
{
    @Shadow public abstract ServerWorld getServerWorld( );

    @Inject( method = "tick", at = @At("RETURN") )
    void onTick( CallbackInfo ci )
    {
        if(!getBlockPos().equals( lastBlockPos) )
        {
            lastBlockPos = getBlockPos();

            Block blk = getServerWorld().getBlockState( lastBlockPos ).getBlock();
            if( blk instanceof BlockActionListener )
            {
                BlockActionListener listener = (BlockActionListener) blk;
                listener.onWalkOver( (ServerPlayerEntity)(Object)this, lastBlockPos );
            }
        }
    }

    @Override
    public void setSneaking( boolean sneaking )
    {
        super.setSneaking( sneaking );
        if(sneaking)
        {
            BlockPos p = this.getBlockPos().down();
            Block blk = this.world.getBlockState( p ).getBlock();

            if(blk instanceof BlockActionListener )
            {
                BlockActionListener bActioner = (BlockActionListener ) blk;
                bActioner.onCrouch( (ServerPlayerEntity)(Object)this, p );
            }
        }
    }

    @Override
    public void jump( )
    {
        BlockPos p = this.getBlockPos().down();
        Block blk = this.world.getBlockState( p ).getBlock();

        if(blk instanceof BlockActionListener )
        {
            BlockActionListener bActioner = (BlockActionListener ) blk;
            if(bActioner.onJump( (ServerPlayerEntity)(Object)this, p ) )
            {
                return;
            }
        }

        super.jump( );
    }

    private BlockPos lastBlockPos = BlockPos.ORIGIN;

    public ServerPlayerMixin( World world , BlockPos pos , float yaw , GameProfile profile )
    {
        super( world , pos , yaw , profile );
    }
}
