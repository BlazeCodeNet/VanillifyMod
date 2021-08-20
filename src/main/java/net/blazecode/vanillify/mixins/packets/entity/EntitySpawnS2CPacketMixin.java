package net.blazecode.vanillify.mixins.packets.entity;

import net.blazecode.vanillify.api.interfaces.EntityTypeProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySpawnS2CPacket.class)
public abstract class EntitySpawnS2CPacketMixin implements Packet<ClientPlayPacketListener>
{
    @Mutable
    @Shadow
    @Final
    private EntityType<?> entityTypeId;

    @Shadow public abstract EntityType<?> getEntityTypeId();

    void onInitProxy(Entity base)
    {
        if(base instanceof EntityTypeProxy)
        {
            EntityTypeProxy proxy = (EntityTypeProxy) base;
            this.entityTypeId = proxy.getRepresentation();
        }
    }

    @Inject( method = "<init>(Lnet/minecraft/entity/Entity;I)V", at = @At("RETURN"))
    void onInitMainStupidlyLongParams(Entity entity, int entityData, CallbackInfo ci)
    {
        onInitProxy(entity);
    }
}
