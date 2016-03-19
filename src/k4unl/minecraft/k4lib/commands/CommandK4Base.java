package k4unl.minecraft.k4lib.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K-4U on 30-6-2015.
 */
public abstract class CommandK4Base extends CommandBase {
    protected List<String> aliases = new ArrayList<String>();

    public List getCommandAliases()
    {
        return aliases;
    }

    @Override
    public abstract String getCommandName();

    @Override
    public abstract String getCommandUsage(ICommandSender p_71518_1_);
}
