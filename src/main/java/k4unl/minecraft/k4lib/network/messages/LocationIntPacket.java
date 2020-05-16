/*
 * This file is part of Blue Power. Blue Power is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. Blue Power is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along
 * with Blue Power. If not, see <http://www.gnu.org/licenses/>
 */
package k4unl.minecraft.k4lib.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * @author MineMaarten
 */

public abstract class LocationIntPacket extends AbstractPacket {

    protected int x, y, z;
    protected BlockPos pos;

    public LocationIntPacket() {

    }

    public LocationIntPacket(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.pos = pos;
    }

    public LocationIntPacket(ByteBuf buf) {

        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        this.pos = new BlockPos(x, y, z);
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public PacketDistributor.TargetPoint getTargetPoint(World world) {

		return getTargetPoint(world, 64);
	}

	public PacketDistributor.TargetPoint getTargetPoint(World world, double updateDistance) {

		return new PacketDistributor.TargetPoint(x, y, z, updateDistance, world.getDimension().getType());
	}

	protected Block getBlock(World world) {

		return world.getBlockState(pos).getBlock();
	}
}
