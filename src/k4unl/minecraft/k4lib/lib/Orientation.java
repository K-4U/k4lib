package k4unl.minecraft.k4lib.lib;


import net.minecraft.util.EnumFacing;

/**
 * @author K-4U
 */
public enum Orientation {
    FRONT(0), LEFT(1), RIGHT(2), BACK(3), TOP(4), BOTTOM(5);

    private int orientation;

    private Orientation(int or){
        this.orientation = or;
    }

    public static Orientation calculateOrientation(EnumFacing side, int meta){
        return calculateOrientation(side.ordinal(), meta);
    }

    public static Orientation calculateOrientation(int side, int meta){
        if(meta > 0){
            if(side == meta){
                return FRONT;
            }
            if(meta == 3){
                if(side == 2)
                    return BACK;
                if(side == 4)
                    return LEFT;
                if(side == 5)
                    return RIGHT;
            }
            if(meta == 5){
                if(side == 2)
                    return RIGHT;
                if(side == 3)
                    return LEFT;
                if(side == 4){
                    return BACK;
                }

            }
            if(meta == 4){
                if(side == 2)
                    return LEFT;
                if(side == 3)
                    return RIGHT;
                if(side == 5)
                    return BACK;
            }
            if(meta == 2){
                if(side == 3)
                    return BACK;
                if(side == 4)
                    return RIGHT;
                if(side == 5)
                    return LEFT;
            }

            if(side == 0)
                return BOTTOM;
            if(side == 1)
                return TOP;

        }else{
            switch (side){
                case 0:
                    return BOTTOM;
                case 1:
                    return TOP;
                case 2:
                    return RIGHT;
                case 3:
                    return FRONT;
                case 4:
                    return LEFT;
                case 5:
                    return BACK;
            }
        }
        return FRONT;
    }

}
