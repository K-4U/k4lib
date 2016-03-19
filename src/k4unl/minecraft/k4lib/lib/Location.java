package k4unl.minecraft.k4lib.lib;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

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

    public Location(EntityPlayer player) {
        this.x = (int) player.posX;
        this.y = (int) player.posY;
        this.z = (int) player.posZ;
    }

    public Location(Location clone) {
        this.x = clone.x;
        this.y = clone.y;
        this.z = clone.z;
    }

    public Location(int xCoord, int yCoord, int zCoord, EnumFacing dir) {
        this.x = xCoord + dir.getFrontOffsetX();
        this.y = yCoord + dir.getFrontOffsetY();
        this.z = zCoord + dir.getFrontOffsetY();
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


    public Location(int _x, int _y, int _z, EnumFacing d, int offset) {
        x = _x + (d.getFrontOffsetX() * offset);
        y = _y + (d.getFrontOffsetY() * offset);
        z = _z + (d.getFrontOffsetZ() * offset);
    }

    public Location(int _x, int _y, int _z, int _dimension, EnumFacing d, int offset) {
        x = _x + (d.getFrontOffsetX() * offset);
        y = _y + (d.getFrontOffsetY() * offset);
        z = _z + (d.getFrontOffsetZ() * offset);
        dimension = _dimension;
    }

    public Location(Location baseLoc, EnumFacing d, int offset) {
        x = baseLoc.getX() + (d.getFrontOffsetX() * offset);
        y = baseLoc.getY() + (d.getFrontOffsetY() * offset);
        z = baseLoc.getZ() + (d.getFrontOffsetZ() * offset);
        dimension = baseLoc.dimension;
    }

    public Location(int _x, int _y, int _z, int _dimension, EnumFacing d) {
        x = _x + d.getFrontOffsetX();
        y = _y + d.getFrontOffsetY();
        z = _z + d.getFrontOffsetZ();
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

    public Location(BlockPos pos, EnumFacing offset){
        this(pos, offset, 1);
    }

    public Location(RayTraceResult blockLookedAt) {
        if (blockLookedAt != null) {
            this.x = blockLookedAt.getBlockPos().getX();
            this.y = blockLookedAt.getBlockPos().getY();
            this.z = blockLookedAt.getBlockPos().getZ();
        }
    }

    public Location(BlockPos pos, EnumFacing baseDir, int offset) {
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

    public void setLocation(int[] coords) {
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
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

    public Location getNewOffset(EnumFacing dir, int offset) {
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

    public void offset(EnumFacing dir, int offsetInt) {
        x += dir.getFrontOffsetX() * offsetInt;
        y += dir.getFrontOffsetY() * offsetInt;
        z += dir.getFrontOffsetZ() * offsetInt;
    }

    public int[] getIntArray() {
        return new int[]{x, y, z, dimension};
    }

    public Block getBlock(IBlockAccess iba) {
        return iba.getBlockState(new BlockPos(x, y, z)).getBlock();
    }


    public Block getBlock(IBlockAccess iba, EnumFacing dir) {
        return iba.getBlockState(new BlockPos(x + dir.getFrontOffsetX(), y + dir.getFrontOffsetY(), z + dir.getFrontOffsetZ())).getBlock();
    }

    public TileEntity getTE(IBlockAccess iba) {
        return iba.getTileEntity(new BlockPos(x, y, z));
    }


    public TileEntity getTE(IBlockAccess iba, EnumFacing dir) {
        return iba.getTileEntity(new BlockPos(x + dir.getFrontOffsetX(), y + dir.getFrontOffsetY(), z + dir.getFrontOffsetZ()));
    }

    public String print() {
        return String.format("D: " + dimension + " X: " + x + " Y: " + y + " Z: " + z);
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

    public IBlockState getBlockState(World worldObj) {
        return worldObj.getBlockState(this.toBlockPos());
    }
}
