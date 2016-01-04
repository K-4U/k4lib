package k4unl.minecraft.k4lib.lib;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

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

    public static boolean isPlayerOpped(GameProfile player){
        if(MinecraftServer.getServer().getConfigurationManager().getOppedPlayers().getKeys().length > 0) {
            for (String name : MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames()) {
                if (name.toLowerCase().equals(player.getName().toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isPlayerOpped(String player){
        if(MinecraftServer.getServer().getConfigurationManager().getOppedPlayers().getKeys().length > 0) {
            for (String name : MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames()) {
                if (name.toLowerCase().equals(player.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static void sendChatMessageServerWide(World world, ChatComponentText message){
        for (EntityPlayer player : (List<EntityPlayer>) world.playerEntities) {
            player.addChatMessage(message);
        }
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
        return new Location(hit.getBlockPos().getX(), hit.getBlockPos().getY(), hit.getBlockPos().getZ());
    }

    public static boolean isInDev() {

        return Boolean.parseBoolean(System.getProperty("k4lib.dev", "false"));
    }

    /**
     * I copied this code from OpenModsLib!
     * Be warned, minecraft doesn't update experienceTotal properly, so we have
     * to do this.
     *
     * @param player
     * @return
     */
    public static int getPlayerXP(EntityPlayer player) {
        return (int)(getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
    }

    public static void addPlayerXP(EntityPlayer player, int amount) {
        int experience = getPlayerXP(player) + amount;
        player.experienceTotal = experience;
        player.experienceLevel = getLevelForExperience(experience);
        int expForLevel = getExperienceForLevel(player.experienceLevel);
        player.experience = (float)(experience - expForLevel) / (float)player.xpBarCap();
    }

    public static int getExperienceForLevel(int level) {
        if (level == 0) { return 0; }
        if (level > 0 && level < 16) {
            return level * 17;
        } else if (level > 15 && level < 31) {
            return (int)(1.5 * Math.pow(level, 2) - 29.5 * level + 360);
        } else {
            return (int)(3.5 * Math.pow(level, 2) - 151.5 * level + 2220);
        }
    }

    public static int getXpToNextLevel(int level) {
        int levelXP = getLevelForExperience(level);
        int nextXP = getExperienceForLevel(level + 1);
        return nextXP - levelXP;
    }

    public static int getLevelForExperience(int experience) {
        int i = 0;
        while (getExperienceForLevel(i) <= experience) {
            i++;
        }
        return i - 1;
    }

    public static TextureAtlasSprite getFluidIcon(Fluid fluid) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(fluid.getFlowing().toString());
    }

}
