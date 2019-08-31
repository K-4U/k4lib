package k4unl.minecraft.k4lib.network;

import java.util.function.Supplier;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * @author Koen Beckers (K-4U)
 */
public interface Message<R extends Message> {
	void toBytes(ByteBuf buffer);

	void fromBytes(ByteBuf buffer);

	void handle(R message, Supplier<NetworkEvent.Context> ctx);
}
