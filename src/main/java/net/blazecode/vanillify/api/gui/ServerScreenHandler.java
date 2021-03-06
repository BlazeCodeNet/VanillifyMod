package net.blazecode.vanillify.api.gui;

import net.blazecode.vanillify.VanillifyMod;
import net.blazecode.vanillify.api.VanillaUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerScreenHandler extends ScreenHandler
{
    protected ServerScreenHandler( int syncId, PlayerInventory playerInventory, int rows )
    {
        super( fromRows( rows ), syncId );
        
        int i = (rows - 4) * 18;
        this.inventory = new SimpleInventory( rows * 9 );
        this.onInitInventoryFill( (ServerPlayerEntity)playerInventory.player, this.inventory );
        
        int n, m;
        
        for (n = 0; n < rows; ++n) {
            for (m = 0; m < 9; ++m) {
                this.addSlot(new Slot(this.inventory, m + n * 9, 8 + m * 18, 18 + n * 18));
            }
        }
        
        for (n = 0; n < 3; ++n) {
            for (m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 8 + m * 18, 103 + n * 18 + i));
            }
        }
        
        for (n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 8 + n * 18, 161 + i));
        }
        
        playerInventory.player.currentScreenHandler.sendContentUpdates();
        
    }
    
    private static ScreenHandlerType<GenericContainerScreenHandler> fromRows( int rows)
    {
        switch (rows) {
            case 2:
                return ScreenHandlerType.GENERIC_9X2;
            case 3:
                return ScreenHandlerType.GENERIC_9X3;
            case 4:
                return ScreenHandlerType.GENERIC_9X4;
            case 5:
                return ScreenHandlerType.GENERIC_9X5;
            case 6:
                return ScreenHandlerType.GENERIC_9X6;
            default:
                return ScreenHandlerType.GENERIC_9X1;
        }
    }
    protected abstract void onInitInventoryFill( ServerPlayerEntity player, Inventory inv );
    
    @Override
    public boolean canUse(PlayerEntity player)
    {
        return true;
    }
    
    public SLOT_CLICK_RESULT checkSlotClick( int slotIndex, int button, SlotActionType actionType, ServerPlayerEntity player)
    {
        return SLOT_CLICK_RESULT.DENY_CLICK;
    }
    
    @Override
    public void addListener(ScreenHandlerListener listener)
    {
        if (!this.listeners.contains(listener))
        {
            this.listeners.add(listener);
            this.sendContentUpdates();
        }
    }
    
    public static ItemStack getFillerItem()
    {
        ItemStack filler = new ItemStack( Items.GRAY_STAINED_GLASS_PANE );
        filler.setCustomName( VanillaUtils.getText( "" ) );
        return filler;
    }
    public int getInventorySize()
    {
        return inventory.size( );
    }
    protected Inventory getInventory()
    {
        return inventory;
    }
    
    private final Inventory inventory;
    private final List<ScreenHandlerListener> listeners = new ArrayList<>();
}
