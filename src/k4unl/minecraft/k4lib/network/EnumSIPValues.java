package k4unl.minecraft.k4lib.network;

/**
 * @author Koen Beckers (K-4U)
 */
public enum EnumSIPValues {
    INVALID
    , MISFORMED
    , TIME
    , PLAYERS
    , DAYNIGHT
    , DIMENSIONS
    , UPTIME
    , DEATHS
    , WEATHER
    , TPS
    , ENTITIES
    , VERSIONS
    ;

    public static EnumSIPValues fromString(String str) {
        for(EnumSIPValues v : values()){
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
