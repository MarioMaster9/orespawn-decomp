package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class KingHead extends EntityLiving {
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	private double velocityX;
	private double velocityY;
	private double velocityZ;

	public KingHead(World par1World) {
		super(par1World);
		this.setSize(19.9F, 10.0F);
		this.noClip = true;
		this.fireResistance = 10000;
		this.isImmuneToFire = true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)OreSpawnMain.TheKing_stats.health);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)1.33F);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	protected boolean canDespawn() {
		return false;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void entityInit() {
		super.entityInit();
	}

	public boolean canBePushed() {
		return true;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		if (par1DamageSource.getDamageType().equals("inWall")) {
			return false;
		} else {
			Entity e = par1DamageSource.getEntity();
			if (e == null || !(e instanceof TheKing) && !(e instanceof KingHead)) {
				e = par1DamageSource.getSourceOfDamage();
				if (e == null || !(e instanceof TheKing) && !(e instanceof KingHead)) {
					List var5 = this.worldObj.getEntitiesWithinAABB(TheKing.class, this.boundingBox.expand((double)48.0F, 32.0D, (double)48.0F));
					Iterator var2 = var5.iterator();
					Entity var3 = null;
					TheKing var4 = null;
					if (var2.hasNext()) {
						var3 = (Entity)var2.next();
						var4 = (TheKing)var3;
						ret = var4.attackEntityFrom(par1DamageSource, par2);
					}

					return ret;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public boolean canBeCollidedWith() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		if (this.riddenByEntity != null) {
			this.boatPosRotationIncrements = par9 + 8;
		} else {
			this.boatPosRotationIncrements = 6;
		}

		this.boatX = par1;
		this.boatY = par3;
		this.boatZ = par5;
		this.boatYaw = (double)par7;
		this.boatPitch = (double)par8;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5) {
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	public void onUpdate() {
		if (!this.isDead) {
			this.isAirBorne = true;
			this.setFire(0);
			if (this.worldObj.isRemote) {
				if (this.boatPosRotationIncrements > 0) {
					double d4 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
					double d5 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
					double d11 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;
					this.setPosition(d4, d5, d11);
					this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
					double d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
					if (this.riddenByEntity != null) {
						d10 = MathHelper.wrapAngleTo180_double((double)this.riddenByEntity.rotationYaw - (double)this.rotationYaw);
					}

					this.rotationYaw = (float)((double)this.rotationYaw + d10 / (double)this.boatPosRotationIncrements);
					this.setRotation(this.rotationYaw, this.rotationPitch);
					--this.boatPosRotationIncrements;
				}
			} else {
				List var5 = this.worldObj.getEntitiesWithinAABB(TheKing.class, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
				Iterator var2 = var5.iterator();
				Entity var3 = null;
				TheKing var4 = null;
				if (var2.hasNext()) {
					var3 = (Entity)var2.next();
					var4 = (TheKing)var3;
					this.posY = var4.posY + 12.0D;
					this.posX = var4.posX - (double)30.0F * Math.sin(Math.toRadians((double)var4.rotationYawHead));
					this.posZ = var4.posZ + (double)30.0F * Math.cos(Math.toRadians((double)var4.rotationYawHead));
					this.rotationYaw = var4.rotationYaw;
					this.rotationYawHead = var4.rotationYawHead;
					this.motionX = var4.motionX;
					this.motionY = var4.motionY;
					this.motionZ = var4.motionZ;
					this.setHealth(var4.getHealth());
				} else {
					this.setDead();
				}
			}

		}
	}
}
