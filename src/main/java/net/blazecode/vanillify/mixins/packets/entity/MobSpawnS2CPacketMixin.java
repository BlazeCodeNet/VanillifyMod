package net.blazecode.vanillify.mixins.packets.entity;

import net.blazecode.vanillify.api.entity.ServerEntity;
import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.MobSpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobSpawnS2CPacket.class)
public abstract class MobSpawnS2CPacketMixin implements Packet<ClientPlayPacketListener>
{
    @Redirect( method = "<init>(Lnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getType()Lnet/minecraft/entity/EntityType;") )
    EntityType getEntityTypeProxy( LivingEntity livingEntity )
    {
        if( livingEntity instanceof EntityTypeProxy )
        {
            EntityTypeProxy entityTypeProxy = (EntityTypeProxy) livingEntity;
            
            return entityTypeProxy.getRepresentation();
        }
        
        return livingEntity.getType();
    }
}
