/*
 * This file is part of Blue Power.
 *
 *     Blue Power is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Blue Power is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Blue Power.  If not, see <http://www.gnu.org/licenses/>
 */

package k4unl.minecraft.k4lib.network;

import k4unl.minecraft.k4lib.lib.Log;
import k4unl.minecraft.k4lib.network.messages.AbstractPacket;
import k4unl.minecraft.k4lib.network.messages.LocationDoublePacket;
import k4unl.minecraft.k4lib.network.messages.LocationIntPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author MineMaarten.
 * Modified by Koen Beckers (K4Unl) for K4Lib
 * Hey, I fixed the static .. abstract...! @CallMeFoxie :D
 */

public abstract class NetworkHandler {

    private static NetworkHandler instance;
    private SimpleChannel channel;

    public NetworkHandler() {
        channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(getModId(), "main"),
                this::getProtocolVersion,
                this.getProtocolVersion()::equals,
                this.getProtocolVersion()::equals
        );
        instance = this;
    }


    public static NetworkHandler getInstance() {
        return instance;
    }

    public SimpleChannel getChannel() {
        return channel;
    }

    public <T extends AbstractPacket> void sendToAll(T message) {
        Log.info("Sending a package of type " + message.getClass().getCanonicalName() + " to all on the server");
        getChannel().send(PacketDistributor.ALL.noArg(), message);
    }

    public <T extends AbstractPacket> void sendTo(T message, ServerPlayerEntity player) {
        Log.info("Sending a package of type " + message.getClass().getCanonicalName() + " to player " + player.getName());
        getChannel().send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    /*
     * public static void registerMessage(){ INSTANCE.registerMessage(clazz, clazz,
     * discriminant++, Side.SERVER, discriminant++, Side.SERVER); }
     */

    public void sendToAllAround(LocationIntPacket message, World world, double distance) {
        sendToAllAround(message, message.getTargetPoint(world, distance));
    }

    public void sendToAllAround(LocationIntPacket message, World world) {
        sendToAllAround(message, message.getTargetPoint(world));
    }

    public void sendToAllAround(LocationDoublePacket message, World world) {
        sendToAllAround(message, message.getTargetPoint(world));
    }

    public <T extends AbstractPacket> void sendToAllAround(T message, PacketDistributor.TargetPoint point) {
        Log.info("Sending a package of type " + message.getClass().getCanonicalName() + " to ");
        getChannel().send(PacketDistributor.NEAR.with(() -> point), message);
    }

    public <T extends AbstractPacket> void sendToDimension(T message, DimensionType dimensionType) {
        getChannel().send(PacketDistributor.DIMENSION.with(() -> dimensionType), message);
    }

    public <T extends AbstractPacket> void sendToServer(T message) {
        getChannel().sendToServer(message);
    }

    /*
     * The integer is the ID of the message, the Side is the side this message will be handled (received) on!
     * Empty init would make no sense as there has to be at least one message added.
     */
    public abstract void init();

    public abstract String getModId();

    public abstract String getProtocolVersion();

}
