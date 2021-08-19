package net.blazecode.vanillify.testing;

import net.blazecode.vanillify.VanillifyMod;
import net.blazecode.vanillify.api.VanillaUtils;
import net.blazecode.vanillify.api.item.ServerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class TestItem extends ServerItem
{
    public TestItem()
    {
        super(Items.STICK, new Identifier(VanillifyMod.MODID, "test_item"), new FabricItemSettings().maxCount(1), VanillaUtils.getText("Noob Stick"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        TestEntity testEnt = new TestEntity(user.world);
        testEnt.setProperties(user, user.getPitch(), user.getYaw(), 0.0f, 0.2f, 1.0f);
        testEnt.setNoGravity(true);
        ServerWorld srvWorld = (ServerWorld)user.world;
        srvWorld.spawnEntity(testEnt);

        user.sendMessage(VanillaUtils.getText("Spawned Test Ent..."), false);

        return super.use(world, user, hand);
    }
}
