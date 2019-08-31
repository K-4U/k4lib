package k4unl.minecraft.k4lib.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.server.ServerWorld;

/**
 * @author Koen Beckers (K-4U)
 */
public class TeleportHelper {


	public static void teleportEntity(Entity ent, Location target) {

		if (ent.dimension.getId() != target.getDimension()) {
			travelToDimension(ent, target);
		} else {
			if (ent instanceof PlayerEntity) {
				ent.setPositionAndUpdate(target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5);
			} else {
				ent.setLocationAndAngles(target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5, ent.rotationYaw, ent.rotationPitch);
			}
		}
	}

	public static void travelToDimension(Entity ent, Location target) {
		ServerWorld worldServerForDimensionId = Functions.getWorldServerForDimensionId(target.getDimension());

		ent.changeDimension(worldServerForDimensionId.getDimension().getType()); // was there the giant copypasted code for some reason?
	}
}
