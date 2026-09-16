package danger.orespawn;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MyEntityAIFollowOwner extends EntityAIBase {
	private EntityTameable thePet;
	private EntityLivingBase theOwner;
	World theWorld;
	private float field_75336_f;
	private PathNavigate petPathfinder;
	private int field_75343_h;
	float maxDist;
	float minDist;
	private boolean field_75344_i;

	public MyEntityAIFollowOwner(EntityTameable par1EntityTameable, float par2, float par3, float par4) {
		this.thePet = par1EntityTameable;
		this.theWorld = par1EntityTameable.worldObj;
		this.field_75336_f = par2;
		this.petPathfinder = par1EntityTameable.getNavigator();
		this.minDist = par4;
		this.maxDist = par3;
		this.setMutexBits(3);
	}

	public boolean shouldExecute() {
		EntityLivingBase var1 = this.thePet.getOwner();
		if (var1 == null) {
			return false;
		} else {
			this.theOwner = var1;
			if (this.thePet.isSitting()) {
				return false;
			} else if (this.thePet instanceof Girlfriend && OreSpawnMain.valentines_day != 0) {
				return false;
			} else if (this.thePet != null && (this.thePet.posY < (double)60.0F || !this.thePet.worldObj.isDaytime()) && this.thePet.getDistanceSqToEntity(var1) > (double)(this.maxDist / 2.0F * (this.maxDist / 2.0F))) {
				return true;
			} else {
				return !(this.thePet.getDistanceSqToEntity(var1) < (double)(this.maxDist * this.maxDist));
			}
		}
	}

	public boolean continueExecuting() {
		if (this.thePet.isSitting()) {
			return false;
		} else if (this.petPathfinder.noPath()) {
			return false;
		} else {
			if (this.thePet != null && this.thePet instanceof EntityTameable) {
				EntityTameable gf = this.thePet;
				EntityLivingBase var1 = gf.getOwner();
				if (var1 != null && (int)gf.posZ == (int)var1.posZ && (int)gf.posX == (int)var1.posX && (int)gf.posY < (int)var1.posY + 2 && (int)gf.posY > (int)var1.posY - 2) {
					return false;
				}
			}

			return this.thePet.getDistanceSqToEntity(this.theOwner) > (double)(this.minDist * this.minDist);
		}
	}

	public void startExecuting() {
		this.field_75343_h = 0;
		this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
		this.thePet.getNavigator().setAvoidsWater(false);
	}

	public void resetTask() {
		this.theOwner = null;
		this.petPathfinder.clearPathEntity();
		this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
	}

	public void updateTask() {
		this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float)this.thePet.getVerticalFaceSpeed());
		if (!this.thePet.isSitting() && --this.field_75343_h <= 0) {
			this.field_75343_h = 10;
			if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, (double)this.field_75336_f) && this.thePet.getDistanceSqToEntity(this.theOwner) >= (double)144.0F) {
				int var1 = MathHelper.floor_double(this.theOwner.posX) - 2;
				int var2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
				int var3 = MathHelper.floor_double(this.theOwner.boundingBox.minY);

				for (int var4 = 0; var4 <= 4; ++var4) {
					for (int var5 = 0; var5 <= 4; ++var5) {
						if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && World.doesBlockHaveSolidTopSurface(this.theWorld, var1 + var4, var3 - 1, var2 + var5) && !this.theWorld.getBlock(var1 + var4, var3, var2 + var5).isNormalCube() && !this.theWorld.getBlock(var1 + var4, var3 + 1, var2 + var5).isNormalCube()) {
							this.thePet.setLocationAndAngles((double)((float)(var1 + var4) + 0.5F), (double)var3, (double)((float)(var2 + var5) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
							this.petPathfinder.clearPathEntity();
							return;
						}
					}
				}
			}
		}

	}
}
