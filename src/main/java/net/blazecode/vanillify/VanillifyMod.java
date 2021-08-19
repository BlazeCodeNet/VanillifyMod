package net.blazecode.vanillify;

import net.blazecode.vanillify.commands.VanillaCommand;
import net.blazecode.vanillify.testing.TestEntity;
import net.blazecode.vanillify.testing.TestRegistery;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.EntityType;

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

        TestRegistery.init();
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
