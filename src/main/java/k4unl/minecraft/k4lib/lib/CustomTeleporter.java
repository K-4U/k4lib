package k4unl.minecraft.k4lib.lib;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.server.ServerWorld;

/**
 * @author Koen Beckers (K-4U)
 */
public class CustomTeleporter extends Teleporter {

	public CustomTeleporter(ServerWorld par1WorldServer) {

        super(par1WorldServer);
    }

    @Override
    public boolean makePortal(Entity entity) {

        return true;
    }

	//placeInPortal
	@Override
	public boolean placeInPortal(Entity entity, float rotationYaw) {
		int newX = MathHelper.floor(entity.posX);
		int newY = MathHelper.floor(entity.posY);
		int newZ = MathHelper.floor(entity.posZ);
		entity.setLocationAndAngles(newX, newY, newZ, rotationYaw, 0.0F);

		return true;
	}


}
