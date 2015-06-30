package k4unl.minecraft.k4lib.lib;

import net.minecraft.util.AxisAlignedBB;

public class Area {

    private String name;
    private Location loc1;
    private Location loc2;
    private int dimensionId;

    public Area(String name_, Location loc1_, Location loc2_, int dimensionId_){
        name = name_;
        loc1 = loc1_;
        loc2 = loc2_;
        dimensionId = dimensionId_;

    }

    public Area(String name_){
        this(name_, null, null, 0);
    }

    public void setName(String newName){
        name = newName;
    }

    public void setLoc1(Location newLoc){
        loc1 = newLoc;
    }

    public void setLoc2(Location newLoc){
        loc2 = newLoc;
    }

    public void setDimensionId(int newId){
        dimensionId = newId;
    }

    public Location getLoc1(){
        return loc1;
    }

    public Location getLoc2(){
        return loc2;
    }

    public String getName(){
        return name;
    }

    public int getDimensionId(){
        return dimensionId;
    }

    public boolean contains(Location locToCheck) {
        return contains(locToCheck.getX(), locToCheck.getY(), locToCheck.getZ());
    }

    public boolean contains(int x, int y, int z){
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

    public int getMinX(){
        return Math.min(loc1.getX(), loc2.getX());
    }

    public int getMinY(){
        return Math.min(loc1.getY(), loc2.getY());
    }

    public int getMinZ(){
        return Math.min(loc1.getZ(), loc2.getZ());
    }

    public int getMaxX(){
        return Math.max(loc1.getX(), loc2.getX());
    }

    public int getMaxY(){
        return Math.max(loc1.getY(), loc2.getY());
    }

    public int getMaxZ(){
        return Math.max(loc1.getZ(), loc2.getZ());
    }

    public AxisAlignedBB getAABB(){
        return AxisAlignedBB.fromBounds(getMinX(), getMinY(), getMinZ(), getMaxX(), getMaxY(), getMaxZ());
    }
}
