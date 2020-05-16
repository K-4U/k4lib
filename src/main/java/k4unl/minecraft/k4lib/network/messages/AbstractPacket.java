/*
 * This file is part of Blue Power. Blue Power is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. Blue Power is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along
 * with Blue Power. If not, see <http://www.gnu.org/licenses/>
 */
package k4unl.minecraft.k4lib.network.messages;

import io.netty.buffer.ByteBuf;
import k4unl.minecraft.k4lib.K4Lib;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author K4Unl
 * Make sure to create a constructor accepting a bytebuff
 */
public abstract class AbstractPacket {

	public abstract void toBytes(ByteBuf buffer);

	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			if (ctx.get().getDirection().getReceptionSide().isServer()) {
				handleServerSide(ctx.get().getSender());
			} else {
				handleClientSide(K4Lib.proxy.getPlayer());
			}
			ctx.get().setPacketHandled(true);
		});
	}

	/**
	 * Handle a packet on the client side.
	 *
	 * @param message TODO
	 * @param player  the player reference
	 */
	public abstract void handleClientSide(PlayerEntity player);

	/**
	 * Handle a packet on the server side.
	 *
	 * @param message TODO
	 * @param player  the player reference
	 */
	public abstract void handleServerSide(PlayerEntity player);

	protected void writeString(String string, ByteBuf buf) {
		buf.writeByte(string.length());
		for (int i = 0; i < string.length(); i++)
			buf.writeChar(string.charAt(i));
	}

	protected String readString(ByteBuf buf) {

		StringBuilder ret = new StringBuilder();
		int size = buf.readByte();
		for (int i = 0; i < size; i++)
			ret.append(buf.readChar());
		return ret.toString();
	}
}
