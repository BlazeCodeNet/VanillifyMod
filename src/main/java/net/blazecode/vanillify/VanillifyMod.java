package net.blazecode.vanillify;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import java.util.logging.Logger;

public class VanillifyMod implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ServerLifecycleEvents.SERVER_STARTED.register( server ->
        {
            if (server.isDedicated())
            {
                
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
