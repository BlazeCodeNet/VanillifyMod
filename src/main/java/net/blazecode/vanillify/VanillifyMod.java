package net.blazecode.vanillify;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import java.util.logging.Logger;

public class VanillifyMod implements DedicatedServerModInitializer
{
    @Override
    public void onInitializeServer( )
    {

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
