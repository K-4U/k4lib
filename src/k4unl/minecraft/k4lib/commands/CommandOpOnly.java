package k4unl.minecraft.k4lib.commands;

import k4unl.minecraft.k4lib.lib.Functions;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;


public abstract class CommandOpOnly extends CommandK4Base {
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if(sender instanceof EntityPlayerMP){
            return Functions.isPlayerOpped(((EntityPlayerMP) sender).getGameProfile());
        } else {
            return true;
        }
    }
}
