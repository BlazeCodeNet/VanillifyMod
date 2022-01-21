package net.blazecode.vanillify.testing;

import net.blazecode.vanillify.api.VanillaUtils;
import net.blazecode.vanillify.api.gui.SLOT_CLICK_RESULT;
import net.blazecode.vanillify.api.gui.ServerScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mutable;

import java.util.ArrayList;
import java.util.List;

public class TestingGUI extends ServerScreenHandler
{
    protected TestingGUI( int syncId, PlayerInventory playerInventory )
    {
        super( syncId, playerInventory, 1 );
    }
    
    @Override
    protected void onInitInventoryFill( ServerPlayerEntity player, Inventory inv )
    {
        for(int i = 0; i < inv.size(); i++)
        {
            boolean isUnfilled = false;
            ItemStack buffer = ItemStack.EMPTY;
            MutableText bufferTitle = VanillaUtils.getText( "_NULL_" );
            List<MutableText> buffLore = new ArrayList();
            
            switch(i)
            {
                case 0:
                    buffer = new ItemStack( Items.BARRIER );
                    bufferTitle =  VanillaUtils.getText( "Close", Formatting.RED );
                    break;
                case 1:
                    buffer = ItemStack.EMPTY;
                    break;
                default:
                    isUnfilled = true;
                    break;
            }
            
            if(isUnfilled)
            {
                buffer = getFillerItem();
            }
            else if(buffer != ItemStack.EMPTY)
            {
                buffer.setCustomName( bufferTitle );
                VanillaUtils.setStackLore( buffer, buffLore );
            }
            
            inv.setStack( i, buffer );
        }
    }
    
    @Override
    public SLOT_CLICK_RESULT checkSlotClick( int slotIndex, int button, SlotActionType actionType, ServerPlayerEntity player )
    {
        if(slotIndex == 0 && button == 0)
        {
            player.closeHandledScreen();
        }
        else if(slotIndex == 1 || slotIndex >= getInventorySize())
        {
            return SLOT_CLICK_RESULT.PASSTHROUGH_CLICK;
        }
        return SLOT_CLICK_RESULT.DENY_CLICK;
    }
}
