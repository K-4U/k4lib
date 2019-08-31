package k4unl.minecraft.k4lib.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import k4unl.minecraft.k4lib.commands.impl.CommandCoords;
import k4unl.minecraft.k4lib.commands.impl.CommandK4Lib;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;


public class CommandsRegistry {

	public CommandsRegistry(boolean isDedicatedServer, CommandDispatcher<CommandSource> dispatcher) {
		register(dispatcher, new CommandCoords());
		register(dispatcher, new CommandK4Lib());
	}

	public static void register(CommandDispatcher<CommandSource> dispatcher, Command command) {
		LiteralArgumentBuilder<CommandSource> literalArgumentBuilder = Commands.literal(command.getName()).requires(command::canUse);
		command.register(literalArgumentBuilder);
		dispatcher.register(literalArgumentBuilder);
	}
}
