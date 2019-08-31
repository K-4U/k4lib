package k4unl.minecraft.k4lib.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.command.CommandSource;

/**
 * @author Koen Beckers (K-4U)
 */
public interface Command {

	void register(LiteralArgumentBuilder<CommandSource> argumentBuilder);

	String getName();

	boolean canUse(CommandSource commandSource);
}
