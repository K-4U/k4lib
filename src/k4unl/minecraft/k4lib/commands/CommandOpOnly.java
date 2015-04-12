package k4unl.minecraft.k4lib.commands;

import k4unl.minecraft.k4lib.lib.Functions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;


public abstract class CommandOpOnly extends CommandBase {


    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        if(sender instanceof EntityPlayerMP){
            return Functions.isPlayerOpped(((EntityPlayerMP) sender).getGameProfile());
        } else {
            return true;
        }
    }
}
