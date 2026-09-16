package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMosquito extends EntityAmbientCreature {
	private ChunkCoordinates currentFlightTarget = null;

	public EntityMosquito(World par1World) {
		super(par1World);
		this.setSize(0.2F, 0.2F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 5;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	protected void entityInit() {
		super.entityInit();
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected float getSoundPitch() {
		return 1.5F;
	}

	protected String getLivingSound() {
		return "orespawn:mosquito";
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	public boolean canBePushed() {
		return false;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	protected void collideWithNearbyEntities() {
	}

	public int mygetMaxHealth() {
		return 2;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		super.onUpdate();
		this.motionY *= (double)0.6F;
	}

	protected void updateAITasks() {
		int keep_trying = 50;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
			}

			if (this.rand.nextInt(20) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 3.0F) {
				EntityPlayer target = null;
				if (OreSpawnMain.OreSpawnRand.nextInt(4) == 0) {
					target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(10.0D, 6.0D, 10.0D), this);
					if (target != null) {
						this.currentFlightTarget.set((int)target.posX, (int)target.posY + 2, (int)target.posZ);
					} else {
						for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
							this.currentFlightTarget.set((int)this.posX + this.rand.nextInt(6) - this.rand.nextInt(6), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(6) - this.rand.nextInt(6));
							bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
						}
					}
				} else {
					for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
						this.currentFlightTarget.set((int)this.posX + this.rand.nextInt(6) - this.rand.nextInt(6), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(6) - this.rand.nextInt(6));
						bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
					}
				}
			}

			double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
			double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
			double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.1D;
			this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * 0.1D;
			this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.1D;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 0.3F;
			this.rotationYaw += var8;
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public boolean getCanSpawnHere() {
		return true;
	}

	public void initCreature() {
	}
}
