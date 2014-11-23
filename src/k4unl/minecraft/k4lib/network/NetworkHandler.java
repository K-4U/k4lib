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

import k4unl.minecraft.k4lib.network.messages.LocationDoublePacket;
import k4unl.minecraft.k4lib.network.messages.LocationIntPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * 
 * @author MineMaarten.
 * Modified by Koen Beckers (K4Unl) for K4Lib
 */

public class NetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(getModId());
    protected static int discriminant;

    /**
     * OVERRIDE THIS METHOD
     * @return
     */
    public static String getModId() {

        return "";
    }

    /*
     * The integer is the ID of the message, the Side is the side this message will be handled (received) on!
     */
    public static void init() {

    }

    /*
     * public static void INSTANCE.registerMessage(Class<? extends AbstractPacket<? extends IMessage>> clazz){ INSTANCE.registerMessage(clazz, clazz,
     * discriminant++, Side.SERVER, discriminant++, Side.SERVER); }
     */

    public static void sendToAll(IMessage message) {

        INSTANCE.sendToAll(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {

        INSTANCE.sendTo(message, player);
    }

    @SuppressWarnings("rawtypes")
    public static void sendToAllAround(LocationIntPacket message, World world, double distance) {

        sendToAllAround(message, message.getTargetPoint(world, distance));
    }

    @SuppressWarnings("rawtypes")
    public static void sendToAllAround(LocationIntPacket message, World world) {

        sendToAllAround(message, message.getTargetPoint(world));
    }

    @SuppressWarnings("rawtypes")
    public static void sendToAllAround(LocationDoublePacket message, World world) {

        sendToAllAround(message, message.getTargetPoint(world));
    }

    public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {

        INSTANCE.sendToAllAround(message, point);
    }

    public static void sendToDimension(IMessage message, int dimensionId) {

        INSTANCE.sendToDimension(message, dimensionId);
    }

    public static void sendToServer(IMessage message) {

        INSTANCE.sendToServer(message);
    }
}
