package k4unl.minecraft.k4lib.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koen Beckers (K-4U)
 */
public abstract class CommandK4Base extends CommandBase {
    private List<String> aliases = new ArrayList<String>();

    public List getCommandAliases()
    {
        return aliases;
    }

    @Override
    public abstract String getName();

    @Override
    public abstract String getUsage(ICommandSender p_71518_1_);
}
