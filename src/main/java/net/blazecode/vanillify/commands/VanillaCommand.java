package net.blazecode.vanillify.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.blazecode.vanillify.api.VanillaUtils;
import net.blazecode.vanillify.testing.TestEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;

import java.util.logging.Logger;

import static net.minecraft.server.command.CommandManager.*;

public class VanillaCommand
{
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("vanilla")
                .requires( src -> src.hasPermissionLevel(2))
                    .then(literal("setmodeldata")
                        .then(argument("id", IntegerArgumentType.integer(2, 9999999))
                            .executes( ctx -> executeSetModelData( ctx, IntegerArgumentType.getInteger(ctx, "id")))));

        dispatcher.register(builder);
    }

    public static int executeSetModelData(CommandContext<ServerCommandSource> ctx, int id) throws CommandSyntaxException
    {
        ServerPlayerEntity srvPlr = ctx.getSource().getPlayer();

        ItemStack handStack = srvPlr.getInventory().getMainHandStack();
        if(handStack != ItemStack.EMPTY)
        {
            VanillaUtils.setStackModelData(handStack, id);
            ctx.getSource().sendFeedback(VanillaUtils.getText("Set CustomModelData to " + id, Formatting.GREEN), false);
        }

        return Command.SINGLE_SUCCESS;
    }

}
