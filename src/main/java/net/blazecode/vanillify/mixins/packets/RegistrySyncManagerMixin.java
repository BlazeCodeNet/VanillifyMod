package net.blazecode.vanillify.mixins.packets;


import net.blazecode.vanillify.api.interfaces.BlockStateProxy;
import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.blazecode.vanillify.api.interfaces.RecipeProviderProxy;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin( RegistrySyncManager.class )
public class RegistrySyncManagerMixin
{
    @Shadow @Final private static Logger LOGGER;
    
    @Redirect( method = "createAndPopulateRegistryMap", at = @At( value = "INVOKE", target = "Lnet/minecraft/util/registry/Registry;getId(Ljava/lang/Object;)Lnet/minecraft/util/Identifier;" ), require = 0 )
    private static Identifier onRegistryToTag( Registry<Object> registry, Object obj, boolean isClientSync )
    {
        {
            if ( isClientSync &&
                (
                    obj instanceof ItemStackProxy ||
                    obj instanceof EntityTypeProxy ||
                    obj instanceof BlockStateProxy ||
                    obj instanceof RecipeProviderProxy
                )
            )
            {
                LOGGER.warn( "Not syncing:" + registry.getId( obj ) );
                return null;
            }
            return registry.getId( obj );
        }
    }
}
