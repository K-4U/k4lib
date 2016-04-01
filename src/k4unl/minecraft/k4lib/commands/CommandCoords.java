package k4unl.minecraft.k4lib.commands;

import k4unl.minecraft.k4lib.lib.Functions;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandCoords extends CommandK4Base {

    @Override
    public String getCommandName() {

        return "coords";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {

        return "/coords. Print coordinates you're at.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        String senderCoords = sender.getName() + " is at [" + sender.getPosition().getX() + ", " + sender.getPosition().getY() + ", " + sender.getPosition().getZ() + "]";
        Functions.sendChatMessageServerWide(new TextComponentString(senderCoords));
    }
}
