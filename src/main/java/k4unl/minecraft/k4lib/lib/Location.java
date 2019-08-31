package k4unl.minecraft.k4lib.lib;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Location {
	private int x;
	private int y;
	private int z;
	private int dimension;

	public Location(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Location(String x, String y, String z) {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
		this.z = Integer.parseInt(z);
	}

	public Location(PlayerEntity player) {
		this.x = (int) player.posX;
		this.y = (int) player.posY;
		this.z = (int) player.posZ;
	}

	public Location(Location clone) {
		this.x = clone.x;
		this.y = clone.y;
		this.z = clone.z;
	}

	public Location(int xCoord, int yCoord, int zCoord, Direction dir) {
		this.x = xCoord + dir.getXOffset();
		this.y = yCoord + dir.getYOffset();
		this.z = zCoord + dir.getZOffset();
	}

	public Location(int _x, int _y, int _z, int _dimension) {
		x = _x;
		y = _y;
		z = _z;
		dimension = _dimension;
	}

	public Location(int[] loc) {
		if (loc.length > 2) {
			x = loc[0];
			y = loc[1];
			z = loc[2];
			if (loc.length > 3) {
				dimension = loc[3];
			}
		} else {
			//Log.error("Trying to load a location with a wrong int array!");
		}
	}


	public Location(int _x, int _y, int _z, Direction d, int offset) {
		x = _x + (d.getXOffset() * offset);
		y = _y + (d.getYOffset() * offset);
		z = _z + (d.getZOffset() * offset);
	}

	public Location(int _x, int _y, int _z, int _dimension, Direction d, int offset) {
		x = _x + (d.getXOffset() * offset);
		y = _y + (d.getYOffset() * offset);
		z = _z + (d.getZOffset() * offset);
		dimension = _dimension;
	}

	public Location(Location baseLoc, Direction d, int offset) {
		x = baseLoc.getX() + (d.getXOffset() * offset);
		y = baseLoc.getY() + (d.getYOffset() * offset);
		z = baseLoc.getZ() + (d.getZOffset() * offset);
		dimension = baseLoc.dimension;
	}

	public Location(int _x, int _y, int _z, int _dimension, Direction d) {
		x = _x + d.getXOffset();
		y = _y + d.getYOffset();
		z = _z + d.getZOffset();
		dimension = _dimension;
	}


	public Location(BlockPos pos) {
		if (pos != null) {
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public Location(BlockPos pos, int dimension) {
		if (pos != null) {
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
			this.dimension = dimension;
		}
	}

	public Location(BlockPos pos, Direction offset) {
		this(pos, offset, 1);
	}

	public Location(BlockRayTraceResult blockLookedAt) {
		if (blockLookedAt != null) {
			this.x = (int) blockLookedAt.getPos().getX();
			this.y = (int) blockLookedAt.getPos().getY();
			this.z = (int) blockLookedAt.getPos().getZ();
		}
	}

	public Location(BlockPos pos, Direction baseDir, int offset) {
		this(pos.getX(), pos.getY(), pos.getZ(), baseDir, offset);
	}

	public boolean equals(Location toTest) {
		if (this.x == toTest.x && this.y == toTest.y && this.z == toTest.z) {
			return true;
		}
		return false;
	}

	public void setLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getZ() {
		return this.z;
	}

	public void setZ(int newZ) {
		this.z = newZ;
	}

	public int[] getLocation() {
		int[] ret = new int[3];
		ret[0] = this.x;
		ret[1] = this.y;
		ret[2] = this.z;
		return ret;
	}

	public void setLocation(int[] coords) {
		this.x = coords[0];
		this.y = coords[1];
		this.z = coords[2];
	}

	public Location getNewOffset(Direction dir, int offset) {
		return new Location(this, dir, offset);
	}

	public int getDifference(Location otherLoc) {
		return (int) Math.sqrt(Math.pow(this.x - otherLoc.x, 2) + Math.pow(this.y - otherLoc.y, 2) + Math.pow(this.z - otherLoc.z, 2));
	}

	public int getDifference(BlockPos otherLoc) {
		return (int) Math.sqrt(Math.pow(this.x - otherLoc.getX(), 2) + Math.pow(this.y - otherLoc.getY(), 2) + Math.pow(this.z - otherLoc.getZ(), 2));
	}

	public String printLocation() {
		return "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
	}

	public String printCoords() {
		return this.x + ", " + this.y + ", " + this.z;
	}

	public boolean compare(int x, int y, int z) {
		return (this.x == x && this.y == y && this.z == z);
	}

	public void addX(int toAdd) {
		x += toAdd;
	}

	public void addY(int toAdd) {
		y += toAdd;
	}

	public void addZ(int toAdd) {
		z += toAdd;
	}

	public void offset(Direction dir, int offsetInt) {
		x += dir.getXOffset() * offsetInt;
		y += dir.getYOffset() * offsetInt;
		z += dir.getZOffset() * offsetInt;
	}

	public int[] getIntArray() {
		return new int[]{x, y, z, dimension};
	}

	public Block getBlock(IBlockReader ibr) {
		return ibr.getBlockState(new BlockPos(x, y, z)).getBlock();
	}


	public Block getBlock(IBlockReader ibr, Direction dir) {
		return ibr.getBlockState(new BlockPos(x + dir.getXOffset(), y + dir.getYOffset(), z + dir.getZOffset())).getBlock();
	}

	public TileEntity getTE(IBlockReader ibr) {
		return ibr.getTileEntity(new BlockPos(x, y, z));
	}


	public TileEntity getTE(IBlockReader ibr, Direction dir) {
		return ibr.getTileEntity(new BlockPos(x + dir.getXOffset(), y + dir.getYOffset(), z + dir.getZOffset()));
	}

	public String print() {
		return "D: " + dimension + " X: " + x + " Y: " + y + " Z: " + z;
	}

	public int getDimension() {
		return dimension;
	}

	public boolean isInList(List<Location> locationList) {
		for (Location loc : locationList) {
			if (loc.equals(this)) {
				return true;
			}
		}
		return false;
	}

	public BlockPos toBlockPos() {
		return new BlockPos(this.x, this.y, this.z);
	}

	public boolean compare(BlockPos pos) {
		return compare(pos.getX(), pos.getY(), pos.getZ());
	}

	public BlockState getBlockState(World worldObj) {
		return worldObj.getBlockState(this.toBlockPos());
	}
}
