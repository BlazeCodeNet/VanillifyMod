package net.blazecode.vanillify;

import net.blazecode.vanillify.api.listeners.BlockActionListener;
import net.blazecode.vanillify.commands.VanillaCommand;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

import java.util.logging.Logger;

public class VanillifyMod implements DedicatedServerModInitializer
{
    @Override
    public void onInitializeServer( )
    {
        CommandRegistrationCallback.EVENT.register( (dispatcher, dedicated) ->
        {
            if(dedicated)
            {
                VanillaCommand.register(dispatcher);
            }
        });

        AttackBlockCallback.EVENT.register( (player, world, hand, pos, direction) ->
        {
            if(!world.isClient)
            {
                ServerPlayerEntity srvPlr = (ServerPlayerEntity )player;
                Block blk = world.getBlockState( pos ).getBlock();
                if( blk instanceof BlockActionListener )
                {
                    BlockActionListener l = (BlockActionListener ) blk;

                    l.onPunch(srvPlr, pos);
                }
            }
            return ActionResult.PASS;
        });
    }
    
    public static final String MODID = "vanillify";
    public static final Logger LOGGER = Logger.getLogger( MODID );
    
    private static boolean screenDebugEnabled = false;
    public static boolean isScreenDebugEnabled()
    {
        return screenDebugEnabled;
    }
    
    public static void enableScreenDebug()
    {
        screenDebugEnabled = true;
    }
    
}
