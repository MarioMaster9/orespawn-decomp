package danger.orespawn;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class BetterFireball extends EntityFireball
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inTile = 0;
	private boolean inGround = false;
	public EntityLivingBase shootingEntity;
	private int ticksAlive;
	private int ticksInAir = 0;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;
	public int field_92012_e = 1;
	private int notme = 0;
	private boolean small = false;

	public BetterFireball(World par1World)
	{
		super(par1World);
		this.setSize(1.0F, 1.0F);
	}

	protected void entityInit() {}

	public BetterFireball(World par1World, EntityLivingBase par2EntityLiving, double par3, double par5, double par7)
	{
		super(par1World);
		
		this.shootingEntity = par2EntityLiving;
		this.setSize(1.0F, 1.0F);
		this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = this.motionY = this.motionZ = 0.0D;
		//par3 += this.rand.nextGaussian() * 0.4D;
		//par5 += this.rand.nextGaussian() * 0.4D;
		//par7 += this.rand.nextGaussian() * 0.4D;
		double var9 = (double)MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
		this.accelerationX = par3 / var9 * 0.1;
		this.accelerationY = par5 / var9 * 0.1;
		this.accelerationZ = par7 / var9 * 0.1;
	}

	
	public void setNotMe()
	{
		this.notme = 1;
	}

	public void setBig() {
		this.field_92012_e = 2;
	}

	public void setReallyBig() {
		this.field_92012_e = 4;
	}

	public void setSmall()
	{
		this.small = true;
		this.setSize(0.3125F, 0.3125F);
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		Block var1;
		Vec3 var15 = null;
		Vec3 var2 = null;
		MovingObjectPosition var3 = null;
		Entity var4 = null;
		List var5 = null;
		double var6 = 0.0D;
		Entity var9 = null;
		int var8;
		float var10 = 0.3F;
		double var13 = 0.0D;
		float var16 = 0.0F;
		float var17 = 0.0F;
		float var18 = 0.0F;
		int var19;
		
		if (this.ticksAlive >= 600 || this.ticksInAir >= 600)
		{
			this.setDead();
			return;
		}
		
		if (!this.worldObj.isRemote && ((this.shootingEntity != null && this.shootingEntity.isDead) || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
		{
			this.setDead();
		}
		else
		{
			super.onUpdate();
			this.setFire(1);
			
			if (this.inGround)
			{
				var1 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
				
				if (var1 != Blocks.air)
				{
					++this.ticksAlive;
				}

				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				//this.ticksAlive = 0;
				//this.ticksInAir = 0;
			}
			else
			{
				++this.ticksInAir;
			}

			var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			var3 = this.worldObj.rayTraceBlocks(var15, var2, false);
			var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			
			if (var3 != null)
			{
				var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
			}

			var4 = null;
			var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			var6 = 0.0D;

			for (var8 = 0; var8 < var5.size(); ++var8)
			{
				var9 = (Entity)var5.get(var8);
				if (this.shootingEntity == var9) {
					var3 = null;
					break;
				}
				if (var9 instanceof BetterFireball) {
					var3 = null;
					break;
				}
				if (var9 instanceof GodzillaHead) {
					var3 = null;
					break;
				}
				if (MyUtils.isRoyalty(var9)) {
					var3 = null;
					break;
				}

				if (this.notme != 0) {
					if (var9 instanceof EntityPlayer || var9 instanceof Dragon || var9 instanceof Mothra) {
						var3 = null;
						break;
					}
				}

				if (var9.canBeCollidedWith() && (!var9.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25))
				{
					//float f = 0.3F;
					AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
					MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);
					
					if (var12 != null)
					{
						var13 = var15.distanceTo(var12.hitVec);
						
						if (var13 < var6 || var6 == 0.0D)
						{
							var4 = var9;
							var6 = var13;
						}
					}
				}
			}

			if (var4 != null)
			{
				var3 = new MovingObjectPosition(var4);
			}

			if (var3 != null)
			{
				this.onImpact(var3);
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

			for (this.rotationPitch = (float)(Math.atan2((double)var16, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			var17 = this.getMotionFactor();
			
			if (this.isInWater())
			{
				for (var19 = 0; var19 < 4; ++var19)
				{
					var18 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var18, this.posY - this.motionY * (double)var18, this.posZ - this.motionZ * (double)var18, this.motionX, this.motionY, this.motionZ);
				}

				var17 = 0.8F;
			}

			this.motionX += this.accelerationX;
			this.motionY += this.accelerationY;
			this.motionZ += this.accelerationZ;
			this.motionX *= (double)var17;
			this.motionY *= (double)var17;
			this.motionZ *= (double)var17;
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
			this.setPosition(this.posX, this.posY, this.posZ);
		}
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		if (!this.worldObj.isRemote)
		{
			if (par1MovingObjectPosition.entityHit != null) {
				if (par1MovingObjectPosition.entityHit instanceof BetterFireball) return;
				if (par1MovingObjectPosition.entityHit instanceof Mothra) return;
				if (this.notme != 0) {
					if (par1MovingObjectPosition.entityHit instanceof Dragon || par1MovingObjectPosition.entityHit instanceof EntityPlayer) {
						this.setDead();
						return;
					}
				}
				Entity e = par1MovingObjectPosition.entityHit;
				if (e instanceof EntityLiving) {
					EntityLiving el = (EntityLiving)e;
					if (el.width * el.height > 30.0F) {
						if (!MyUtils.isRoyalty(el)
						&& !(el instanceof Godzilla)
						&& !(el instanceof GodzillaHead)
						&& !(el instanceof PitchBlack)
						&& !(el instanceof Kraken))
						{
							el.setHealth(el.getHealth() / 2.0F);
						}
					}
				}

				if (!this.small) {
					par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 10.0F);
					par1MovingObjectPosition.entityHit.setFire(5);
				} else {
					par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0F);
					par1MovingObjectPosition.entityHit.setFire(5);
				}
			}
			else
			{
				int i = par1MovingObjectPosition.blockX;
				int j = par1MovingObjectPosition.blockY;
				int k = par1MovingObjectPosition.blockZ;
				
				switch (par1MovingObjectPosition.sideHit)
				{
					case 0:
						--j;
						break;
					case 1:
						++j;
						break;
					case 2:
						--k;
						break;
					case 3:
						++k;
						break;
					case 4:
						--i;
						break;
					case 5:
						++i;
						break;
				}
				if (this.worldObj.isAirBlock(i, j, k))
				{
					this.worldObj.setBlock(i, j, k, Blocks.fire);
				}
			}

			if (!this.small) {
				this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.field_92012_e, true, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			}

			this.setDead();
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("ExplosionPower", this.field_92012_e);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		
		if (par1NBTTagCompound.hasKey("ExplosionPower"))
		{
			this.field_92012_e = par1NBTTagCompound.getInteger("ExplosionPower");
		}
	}
}
