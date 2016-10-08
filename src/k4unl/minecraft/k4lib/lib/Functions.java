package k4unl.minecraft.k4lib.lib;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

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
        Vec3 entityVec = Vec3.createVectorHelper(entity.posX, entity.posY + entity.getEyeHeight() - entity.yOffset - (entity.isSneaking() ? 0.08 : 0), entity.posZ);
        Vec3 entityLookVec = entity.getLook(1.0F);
        Vec3 maxDistVec = entityVec.addVector(entityLookVec.xCoord * maxDistance, entityLookVec.yCoord * maxDistance, entityLookVec.zCoord * maxDistance);
        return entity.worldObj.rayTraceBlocks(entityVec, maxDistVec);
    }

    public static ChunkPosition getEntityLookedBlock(EntityLivingBase entity, float maxDistance){
        MovingObjectPosition hit = getEntityLookedObject(entity, maxDistance);
        if(hit == null || hit.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            return null;
        }
        return new ChunkPosition(hit.blockX, hit.blockY, hit.blockZ);
    }

    public static void sendChatMessageServerWide(World world, ChatComponentText message){
        for (EntityPlayer player : (List<EntityPlayer>) world.playerEntities) {
            player.addChatMessage(message);
        }
    }


    public static boolean isPlayerOpped(GameProfile player){
        if(MinecraftServer.getServer().getConfigurationManager().func_152606_n().length > 0) {
            for (String name : MinecraftServer.getServer().getConfigurationManager().func_152606_n()) {
                if (name.toLowerCase().equals(player.getName().toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isInDev() {

        return Boolean.parseBoolean(System.getProperty("k4lib.dev", "false"));
    }
    
    public static WorldServer getWorldServerForDimensionId(int dimensionId) {
        
        for (WorldServer server : MinecraftServer.getServer().worldServers) {
            if (server.provider.dimensionId == dimensionId) {
                return server;
            }
        }
        return null;
    }
}
