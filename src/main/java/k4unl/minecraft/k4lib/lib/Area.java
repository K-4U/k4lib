package k4unl.minecraft.k4lib.lib;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

import java.util.Optional;

public class Area {

    private String name;
    private Location loc1;
    private Location loc2;
    private DimensionType dimensionType;

    public Area(String name_, Location loc1_, Location loc2_, DimensionType dimensionType) {
        name = name_;
        loc1 = loc1_;
        loc2 = loc2_;
        this.dimensionType = dimensionType;

    }

    public Area(CompoundNBT compoundNBT) {
        this.name = compoundNBT.getString("name");
        this.loc1 = new Location(compoundNBT.getCompound("loc1"));
        this.loc2 = new Location(compoundNBT.getCompound("loc2"));

        Optional<DimensionType> dimension = Functions.getDimensionTypeForString(compoundNBT.getString("dimension"));
        dimension.ifPresent((e) -> this.dimensionType = e);
    }

    public CompoundNBT save() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("name", this.name);
        nbt.put("loc1", this.loc1.getNBT());
        nbt.put("loc2", this.loc2.getNBT());
        nbt.putString("dimension", this.dimensionType.getRegistryName().toString());
        return nbt;
    }

    public Location getLoc1() {
        return loc1;
    }

    public void setLoc1(Location newLoc) {
        loc1 = newLoc;
    }

    public Location getLoc2() {
        return loc2;
    }

    public void setLoc2(Location newLoc) {
        loc2 = newLoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public DimensionType getDimension() {
        return dimensionType;
    }

    public void setDimension(DimensionType dimension) {
        this.dimensionType = dimension;
    }

    public boolean contains(Location locToCheck) {
        return contains(locToCheck.getX(), locToCheck.getY(), locToCheck.getZ());
    }

    public boolean contains(int x, int y, int z) {
        int x1 = Math.min(loc1.getX(), loc2.getX());
        int y1 = Math.min(loc1.getY(), loc2.getY());
        int z1 = Math.min(loc1.getZ(), loc2.getZ());

        int x2 = Math.max(loc1.getX(), loc2.getX());
        int y2 = Math.max(loc1.getY(), loc2.getY());
        int z2 = Math.max(loc1.getZ(), loc2.getZ());

        if (x >= x1 && x <= x2) {
            if (y >= y1 && y <= y2) {
                if (z >= z1 && z <= z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(BlockPos pos) {
        return contains(pos.getX(), pos.getY(), pos.getZ());
    }

    public int getMinX() {
        return Math.min(loc1.getX(), loc2.getX());
    }

    public int getMinY() {
        return Math.min(loc1.getY(), loc2.getY());
    }

    public int getMinZ() {
        return Math.min(loc1.getZ(), loc2.getZ());
    }

    public int getMaxX() {
        return Math.max(loc1.getX(), loc2.getX());
    }

    public int getMaxY() {
        return Math.max(loc1.getY(), loc2.getY());
    }

    public int getMaxZ() {
        return Math.max(loc1.getZ(), loc2.getZ());
    }

    public AxisAlignedBB getAABB() {
        return new AxisAlignedBB(getMinX(), getMinY(), getMinZ(), getMaxX(), getMaxY(), getMaxZ());
    }
}
