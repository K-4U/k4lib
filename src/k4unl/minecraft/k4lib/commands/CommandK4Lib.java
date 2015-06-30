package k4unl.minecraft.k4lib.commands;

import k4unl.minecraft.k4lib.lib.config.ModInfo;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandK4Lib extends CommandK4OpOnly {

    @Override
    public String getCommandName() {

        return "k4lib";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {

        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if(args.length > 0){
            if(args[0].toLowerCase().equals("version")){
                sender.addChatMessage(new ChatComponentText("K4Lib version " + ModInfo.VERSION));
            }
        }
    }
}
