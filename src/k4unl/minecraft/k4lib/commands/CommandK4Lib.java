package k4unl.minecraft.k4lib.commands;

import k4unl.minecraft.k4lib.lib.config.ModInfo;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandK4Lib extends CommandK4OpOnly {

    @Override
    public String getName() {
        return "k4lib";
    }

    @Override
    public String getUsage(ICommandSender p_71518_1_) {
        return "/k4lib version";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length > 0){
            if(args[0].toLowerCase().equals("version")){
                sender.sendMessage(new TextComponentString("K4Lib version " + ModInfo.VERSION));
            }
        }
    }
}
