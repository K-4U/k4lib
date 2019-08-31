package k4unl.minecraft.k4lib.lib;

import java.util.List;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;

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

		return LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
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

	public static void sendChatMessageServerWide(ITextComponent message) {

		for (PlayerEntity player : getServer().getPlayerList().getPlayers()) {
			player.sendMessage(message);
		}
	}

	public static RayTraceResult getEntityLookedObject(LivingEntity entity, float maxDistance) {

		Vec3d entityVec = new Vec3d(entity.posX, entity.posY + entity.getEyeHeight() - entity.getYOffset() - (entity.isSneaking() ? 0.08 : 0), entity.posZ);
		Vec3d entityLookVec = entity.getLook(1.0F);
		Vec3d maxDistVec = entityVec.add(entityLookVec.x * maxDistance, entityLookVec.y * maxDistance, entityLookVec.z * maxDistance);
		RayTraceContext rtc = new RayTraceContext(entityVec, maxDistVec, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity);
		return entity.getEntityWorld().rayTraceBlocks(rtc);
	}

	public static Location getEntityLookedBlock(LivingEntity entity, float maxDistance) {

		RayTraceResult hit = getEntityLookedObject(entity, maxDistance);
		if (hit == null || hit.getType() != RayTraceResult.Type.BLOCK) {
			return null;
		}
		return new Location((BlockRayTraceResult) hit);
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
	 *
	 * @return
	 */
	public static int getPlayerXP(PlayerEntity player) {

		return (int) (getExperienceForLevel(player.experienceLevel) + (player.experience * player.xpBarCap()));
	}

	public static void addPlayerXP(PlayerEntity player, int amount) {

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
		//TODO: Fix me
		//return Minecraft.getInstance().getTextureMapBlocks().getTextureExtry(fluid.getFlowing().toString());
		return null;
	}


	public static void displayTitleMessage(STitlePacket.Type titleType, ServerPlayerEntity entityPlayerMP, ITextComponent toShow) {

		STitlePacket spackettitle = new STitlePacket(titleType, toShow);
		entityPlayerMP.connection.sendPacket(spackettitle);
	}

	public static ServerWorld getWorldServerForDimensionId(int dimensionId) {
		for (ServerWorld world : getServer().getWorlds()) {
			if (world.getDimension().getType().getId() == dimensionId) {
				return world;
			}
		}
		return null;
	}
}
