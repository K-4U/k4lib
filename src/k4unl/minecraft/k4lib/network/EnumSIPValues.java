package k4unl.minecraft.k4lib.network;

/**
 * @author Koen Beckers (K-4U)
 */
public enum EnumQueryValues {
    INVALID, MISFORMED, TIME, PLAYERS, DAYNIGHT, DIMENSIONS, UPTIME, DEATHS, WEATHER, BLOCKINFO, RF, FLUID, INVENTORY;

    public static EnumQueryValues fromString(String str) {
        for(EnumQueryValues v : values()){
            if(v.toString().toLowerCase().equals(str.toLowerCase())){
                return v;
            }
        }
        return INVALID;
    }


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
