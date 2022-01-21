package net.blazecode.vanillify.testing;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.blazecode.vanillify.VanillifyMod;
import net.blazecode.vanillify.api.VanillaUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class TestingCommand
{
    public static void register( CommandDispatcher<ServerCommandSource> dispatcher)
    {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("vest")
                .requires( src -> src.hasPermissionLevel(2))
                .then(literal("gui")
                        .executes( TestingCommand::executeOpenUI ) );
        
        dispatcher.register(builder);
    }
    
    public static int executeOpenUI( CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException
    {
        ServerPlayerEntity srvPlr = ctx.getSource().getPlayer();
    
        SimpleNamedScreenHandlerFactory fac = new SimpleNamedScreenHandlerFactory( (syncId, inventory, player) ->
                new TestingGUI( syncId, inventory ), VanillaUtils.getText( "Testing GUI", Formatting.BOLD, Formatting.BLUE ));
        
        srvPlr.openHandledScreen( fac );
        
        srvPlr.sendMessage( VanillaUtils.getText( "Opened testing GUI", Formatting. RED ), false );
        
        return Command.SINGLE_SUCCESS;
    }
}
