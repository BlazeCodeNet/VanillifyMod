package net.blazecode.vanillify.api.listeners;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public interface BlockActionListener
{
    boolean onJump( ServerPlayerEntity player, BlockPos pos );
    void onCrouch( ServerPlayerEntity player, BlockPos pos );

    void onWalkOver( ServerPlayerEntity player, BlockPos pos );

    void onPunch( ServerPlayerEntity player, BlockPos pos );
}
