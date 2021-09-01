package net.blazecode.vanillify;

import net.blazecode.vanillify.commands.VanillaCommand;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.S2CPlayChannelEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

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
