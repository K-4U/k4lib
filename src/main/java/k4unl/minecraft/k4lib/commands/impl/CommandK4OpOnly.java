package k4unl.minecraft.k4lib.commands.impl;

import k4unl.minecraft.k4lib.commands.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;


public abstract class CommandK4OpOnly implements Command {

	@Override
	public boolean canUse(CommandSource commandSource) {
		if (commandSource.getEntity() instanceof ServerPlayerEntity) {
			if (commandSource.getName().equalsIgnoreCase("k4unl")) {
				return true;
			}
			return commandSource.hasPermissionLevel(3); //I guess? Need to check
		}
		return true;
	}
}
