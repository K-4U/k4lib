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
 * @author MineMaarten.
 *         Modified by Koen Beckers (K4Unl) for K4Lib
 *         Hey, I fixed the static .. abstract...! @CallMeFoxie :D
 */

public abstract class NetworkHandler {

   public final NetworkHandler       INSTANCE;
   protected    int                  discriminant;
   private      SimpleNetworkWrapper channel;

   public NetworkHandler() {
      channel = NetworkRegistry.INSTANCE.newSimpleChannel(getModId());
      INSTANCE = this;
   }

   public abstract String getModId();

   /*
    * The integer is the ID of the message, the Side is the side this message will be handled (received) on!
    * Empty init would make no sense as there has to be at least one message added.
    */
   public abstract void init();

    /*
     * public static void registerMessage(){ INSTANCE.registerMessage(clazz, clazz,
     * discriminant++, Side.SERVER, discriminant++, Side.SERVER); }
     */

   public SimpleNetworkWrapper getChannel() {
      return channel;
   }

   public void sendToAll(IMessage message) {
      channel.sendToAll(message);
   }

   public void sendTo(IMessage message, EntityPlayerMP player) {
      channel.sendTo(message, player);
   }

   public void sendToAllAround(LocationIntPacket message, World world, double distance) {
      sendToAllAround(message, message.getTargetPoint(world, distance));
   }

   public void sendToAllAround(LocationIntPacket message, World world) {
      sendToAllAround(message, message.getTargetPoint(world));
   }

   public void sendToAllAround(LocationDoublePacket message, World world) {
      sendToAllAround(message, message.getTargetPoint(world));
   }

   public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
      channel.sendToAllAround(message, point);
   }

   public void sendToDimension(IMessage message, int dimensionId) {
      channel.sendToDimension(message, dimensionId);
   }

   public void sendToServer(IMessage message) {
      channel.sendToServer(message);
   }
}
