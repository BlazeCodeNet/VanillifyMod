package net.blazecode.vanillify.mixins.packets;

import net.blazecode.vanillify.api.interfaces.RecipeProviderProxy;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

@Mixin(SynchronizeRecipesS2CPacket.class)
public abstract class SyncRecipesPacketMixin implements Packet<ClientPlayPacketListener>
{
    @Final
    @Shadow
    private List<Recipe<?>> recipes;
    
    private void doInitProxy()
    {
        for(int i =0; i < recipes.size(); i++)
        {
            Recipe<?> recipe = recipes.get(i);
        
            if( recipe instanceof RecipeProviderProxy )
            {
                recipes.set( i, ((RecipeProviderProxy)recipe).getRecipeProxy());
            }
        
        }
    }
    
    @Inject( method = "<init>(Ljava/util/Collection;)V", at = @At("RETURN") )
    void onInitCollection( Collection<Recipe<?>> recipesCollection, CallbackInfo ci )
    {
        doInitProxy();
    }
    
    @Inject( method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V", at = @At("RETURN"))
    void onInitPacketByteBuf( PacketByteBuf buf, CallbackInfo ci )
    {
        doInitProxy();
    }
    
}
