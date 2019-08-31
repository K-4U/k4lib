package k4unl.minecraft.k4lib.commands.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import k4unl.minecraft.k4lib.commands.Command;
import k4unl.minecraft.k4lib.lib.Functions;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class CommandCoords implements Command {

	@Override
	public void register(LiteralArgumentBuilder<CommandSource> argumentBuilder) {
		argumentBuilder.executes(this::execute);
	}

	private int execute(CommandContext<CommandSource> commandSourceCommandContext) {
		CommandSource source = commandSourceCommandContext.getSource();


		if (null != source.getEntity()) {
			Entity entity = source.getEntity();

			ITextComponent iTextComponent = source.getDisplayName().deepCopy();
			iTextComponent.appendSibling(new StringTextComponent(" is at [" + entity.getPosition().getX() + ", " + entity.getPosition().getY() + ", " + entity.getPosition().getZ() + "]"));

			Functions.sendChatMessageServerWide(iTextComponent);
		}
		return 0;
	}

	@Override
	public String getName() {

		return "coords";
	}

	@Override
	public boolean canUse(CommandSource sender) {
		return true;
	}
}
