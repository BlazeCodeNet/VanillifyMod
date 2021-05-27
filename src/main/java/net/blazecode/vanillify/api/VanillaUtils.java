package net.blazecode.vanillify.api;

import net.blazecode.vanillify.VanillifyMod;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.util.Tristate;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class VanillaUtils
{
    public static Text getText( String text, Formatting... formattings)
    {
        return new LiteralText(text).setStyle( Style.EMPTY.withItalic(false).withFormatting(formattings));
    }
    public static void playSoundToPlayer( ServerPlayerEntity player, SoundEvent event, float vol, float pitch)
    {
        player.networkHandler.sendPacket( new PlaySoundS2CPacket(event, SoundCategory.PLAYERS, player.getPos().x, player.getPos().y, player.getPos().z, vol, pitch));
    }
    
    public static boolean checkPermission( ServerPlayerEntity player, String permissionNode )
    {
        if( Objects.requireNonNull( player.getServer( ) ).isDedicated() )
        {
            User permUser = VanillifyMod.luckperms.getUserManager( ).getUser( player.getUuid() );
            if(permUser!=null)
            {
                CachedPermissionData permData = permUser.getCachedData( ).getPermissionData();
                
                Tristate checkResult = permData.checkPermission(permissionNode);
                
                return checkResult.asBoolean();
            }
        }
        
        return false;
    }
    
    public static Text getGenericResponse(GenericResponseTypes type)
    {
        Text retText;
        switch(type)
        {
            case INSUFFICIENT_PERMISSION:
                retText = getText( "Insufficient Permissions!", Formatting.RED, Formatting.ITALIC );
                break;
            case UNKNOWN_COMMAND:
                retText = getText( "Uknown Command.", Formatting.RED );
                break;
            case BAD_COMMAND_SYNTAX:
                retText = getText( "Invalid Command Syntax.", Formatting.RED );
                break;
            case UNKNOWN_MOD_ERROR:
                retText = getText( "Uknown Mod-Related Error.", Formatting.RED );
                break;
            case ONLY_PLAYERS_CAN_EXECUTE:
                retText = getText( "Only players can run this command.", Formatting.RED );
                break;
            default:
                retText = getText( "Vanillify: Invalid GenericResponseType...?", Formatting.DARK_RED );
                break;
        }
        
        return retText;
    }
    
    public enum GenericResponseTypes
    {
        INSUFFICIENT_PERMISSION,
        UNKNOWN_COMMAND,
        BAD_COMMAND_SYNTAX,
        UNKNOWN_MOD_ERROR,
        ONLY_PLAYERS_CAN_EXECUTE,
    }
}
