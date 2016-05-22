package k4unl.minecraft.k4lib.lib;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class Functions {

    public static List mergeList(List l1, List l2) {

        for (Object object : l1) {
            if (!l2.contains(object)) {
                l2.add(object);
            }
        }
        return l2;
    }

    public static boolean isInString(String oreName, String[] list) {

        boolean ret = false;
        for (int i = 0; i < list.length; i++) {
            ret = ret || (oreName.substring(0, list[i].length()).equals(list[i]));
        }
        return ret;
    }

    public static MinecraftServer getServer() {

        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }

    public static boolean isPlayerOpped(GameProfile player) {

        if (getServer().getPlayerList().getOppedPlayerNames().length > 0) {
            for (String name : getServer().getPlayerList().getOppedPlayerNames()) {
                if (name.toLowerCase().equals(player.getName().toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static void sendChatMessageServerWide(TextComponentString message) {

        for (EntityPlayer player : getServer().getPlayerList().getPlayerList()) {
            player.addChatMessage(message);
        }
    }

    public static RayTraceResult getEntityLookedObject(EntityLivingBase entity, float maxDistance) {

        Vec3d entityVec = new Vec3d(entity.posX, entity.posY + entity.getEyeHeight() - entity.getYOffset() - (entity.isSneaking() ? 0.08 : 0), entity.posZ);
        Vec3d entityLookVec = entity.getLook(1.0F);
        Vec3d maxDistVec = entityVec.addVector(entityLookVec.xCoord * maxDistance, entityLookVec.yCoord * maxDistance, entityLookVec.zCoord * maxDistance);
        return entity.worldObj.rayTraceBlocks(entityVec, maxDistVec);
    }

    public static Location getEntityLookedBlock(EntityLivingBase entity, float maxDistance) {

        RayTraceResult hit = getEntityLookedObject(entity, maxDistance);
        if (hit == null || hit.typeOfHit != RayTraceResult.Type.BLOCK) {
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

        return (int) (getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
    }

    public static void addPlayerXP(EntityPlayer player, int amount) {

        int experience = getPlayerXP(player) + amount;
        player.experienceTotal = experience;
        player.experienceLevel = getLevelForExperience(experience);
        int expForLevel = getExperienceForLevel(player.experienceLevel);
        player.experience = (float) (experience - expForLevel) / (float) player.xpBarCap();
    }

    public static int getExperienceForLevel(int level) {

        if (level == 0) {
            return 0;
        }
        if (level > 0 && level < 16) {
            return level * 17;
        } else if (level > 15 && level < 31) {
            return (int) (1.5 * Math.pow(level, 2) - 29.5 * level + 360);
        } else {
            return (int) (3.5 * Math.pow(level, 2) - 151.5 * level + 2220);
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


    public static void displayTitleMessage(SPacketTitle.Type titleType, EntityPlayerMP entityPlayerMP, ITextComponent toShow) {

        SPacketTitle spackettitle = new SPacketTitle(titleType, toShow);
        entityPlayerMP.playerNetServerHandler.sendPacket(spackettitle);
    }

}
