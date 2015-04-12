package k4unl.minecraft.k4lib.lib;


public class Area {

    private String name;
    private Location loc1;
    private Location loc2;

    public Area(String name_, Location loc1_, Location loc2_){
        name = name_;
        loc1 = loc1_;
        loc2 = loc2_;
    }

    public Area(String name_){
        this(name_, null, null);
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

    public Location getLoc1(){
        return loc1;
    }

    public Location getLoc2(){
        return loc2;
    }

    public String getName(){
        return name;
    }

    public boolean contains(Location locToCheck) {

        int x1 = Math.min(loc1.getX(), loc2.getX());
        int y1 = Math.min(loc1.getY(), loc2.getY());
        int z1 = Math.min(loc1.getZ(), loc2.getZ());

        int x2 = Math.max(loc1.getX(), loc2.getX());
        int y2 = Math.max(loc1.getY(), loc2.getY());
        int z2 = Math.max(loc1.getZ(), loc2.getZ());

        if (locToCheck.getX() >= x1 && locToCheck.getX() <= x2) {
            if (locToCheck.getY() >= y1 && locToCheck.getY() <= y2) {
                if (locToCheck.getZ() >= z1 && locToCheck.getZ() <= z2) {
                    return true;
                }
            }
        }
        return false;
    }
}
