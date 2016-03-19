package k4unl.minecraft.k4lib.commands;

import k4unl.minecraft.k4lib.lib.Functions;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

/**
 * Created by koen_000 on 12-4-2015.
 */
public abstract class CommandK4OpOnly extends CommandK4Base {

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender){
        if(sender instanceof EntityPlayerMP){
            if(sender.getName().toLowerCase().equals("k4unl")){
                return true;
            }
            return Functions.isPlayerOpped(((EntityPlayerMP) sender).getGameProfile());
        } else {
            return true;
        }
    }
}
