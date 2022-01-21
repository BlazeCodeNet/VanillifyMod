package net.blazecode.vanillify.mixins.generic;

import io.netty.handler.logging.LogLevel;
import net.blazecode.vanillify.VanillifyMod;
import net.blazecode.vanillify.api.gui.SLOT_CLICK_RESULT;
import net.blazecode.vanillify.api.gui.ServerScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Level;
import java.util.logging.Logger;

@Mixin( ServerPlayNetworkHandler.class )
public abstract class SlotClickMixin
{
    @Shadow
    public ServerPlayerEntity player;
    
    @Redirect( method = "onClickSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;onSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V") )
    void onClickSlotInjection( ScreenHandler instance, int slotIndex, int button, SlotActionType actionType, PlayerEntity player )
    {
        if(this.player.currentScreenHandler !=null && this.player.currentScreenHandler instanceof ServerScreenHandler && instance instanceof ServerScreenHandler )
        {
            ServerScreenHandler serverScreenHandler = (ServerScreenHandler ) this.player.currentScreenHandler;
            ClickType clickType = button == 0 ? ClickType.LEFT : ClickType.RIGHT;
            
            SLOT_CLICK_RESULT clickResult = serverScreenHandler.checkSlotClick( slotIndex, button, actionType, this.player );
            
            if(clickResult == SLOT_CLICK_RESULT.DENY_CLICK)
            {
                // DO NOTHING; DONT PASS THE CLICK THROUGH
            }
            else if(clickResult == SLOT_CLICK_RESULT.PASSTHROUGH_CLICK)
            {
                // PASS THROUGH THE CLICK TO THE SCREEN HANDLER
                this.player.currentScreenHandler.onSlotClick(slotIndex, button, actionType, player);
            }
            else
            {
                VanillifyMod.LOGGER.log( Level.SEVERE, "[SlotClickMixin] ClickResult isnt handled properly?!?" );
            }
        }
        
    }
}
