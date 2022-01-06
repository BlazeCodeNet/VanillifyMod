package net.blazecode.vanillify.mixins.packets;

import net.blazecode.vanillify.api.interfaces.ItemStackProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin( PacketByteBuf.class)
public class PacketByteBuffMixin
{
    @ModifyVariable(method = "writeItemStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/network/PacketByteBuf;", at = @At("HEAD"), ordinal = 0, argsOnly = true )
    private ItemStack proxyItemStack( ItemStack stack)
    {
        if(stack.getItem() instanceof ItemStackProxy )
        {
            return ((ItemStackProxy)stack.getItem()).getClientItemStack( stack );
        }
        return stack;
    }
}

