package k4unl.minecraft.k4lib.lib;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

import java.util.List;

public class Functions {
    public static List mergeList(List l1, List l2){
        for (Object object : l1) {
            if(!l2.contains(object)){
                l2.add(object);
            }
        }
        return l2;
    }

    public static boolean isInString(String oreName, String[] list){
        boolean ret = false;
        for(int i = 0; i < list.length; i++){
            ret = ret || (oreName.substring(0, list[i].length()).equals(list[i]));
        }
        return ret;
    }

    public static MovingObjectPosition getEntityLookedObject(EntityLivingBase entity, float maxDistance){
        /*Vec3 entityVec = Vec3.createVectorHelper(entity.posX, entity.posY + entity.getEyeHeight() - entity.yOffset - (entity.isSneaking() ? 0.08 : 0), entity.posZ);
        Vec3 entityLookVec = entity.getLook(1.0F);
        Vec3 maxDistVec = entityVec.addVector(entityLookVec.xCoord * maxDistance, entityLookVec.yCoord * maxDistance, entityLookVec.zCoord * maxDistance);
        return entity.worldObj.rayTraceBlocks(entityVec, maxDistVec);*/
        //TODO: FIX ME
        return null;
    }

    public static Location getEntityLookedBlock(EntityLivingBase entity, float maxDistance){
        MovingObjectPosition hit = getEntityLookedObject(entity, maxDistance);
        if(hit == null || hit.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return null;
        }
        return new Location(hit.func_178782_a().getX(), hit.func_178782_a().getY(), hit.func_178782_a().getZ());
    }

}
