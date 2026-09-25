package danger.orespawn;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
































public class RockBase extends EntityLiving
{
	public int rock_type = 0;
	private double dx, dz;

	public RockBase(World par1World)
	{
		super(par1World);
		this.setSize(0.25F, 0.15F);
		this.fireResistance = 100000;
		this.isImmuneToFire = true;
		this.dx = this.dz = 0.0D;
		
	}
	
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, 0);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		Entity e = par1DamageSource.getEntity();
		if (par1DamageSource.getDamageType().equals("inWall")) {
			return false;
		}
		if (e != null && e instanceof EntityLivingBase) {
			this.playSound("random.pop", 0.75F, 2.25F);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	
	
	
	
	
	
	public int getRockType()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	public void setRockType(int par1)
	{
		if (this.worldObj == null) return;
		if (this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(20, par1);
	}

	public void placeRock(int par1)
	{
		this.rock_type = par1;
		this.setRockType(par1);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(1 + this.rock_type / 4));
		this.setHealth((float)(1 + this.rock_type / 4));
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return 0;
	}

	
	
	protected void fall(float par1) {}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		if (this.dx == 0.0D && this.dz == 0.0D) {
			this.dx = this.posX;
			this.dz = this.posZ;
		}
		super.onUpdate();
		this.rotationPitch = 0.0F;
		this.rotationYaw = this.rotationYawHead = 0.0F;
		
		
		
		if (this.worldObj.isRemote) {
			this.rock_type = this.getRockType();
		}
		if (!this.worldObj.isRemote && this.rock_type == 0) {
			if (this.worldObj.provider.dimensionId != OreSpawnMain.DimensionID5) {
				this.rock_type = 1;
				if (this.worldObj.rand.nextInt(10) == 0) this.rock_type = 2;
				if (this.worldObj.rand.nextInt(20) == 0) this.rock_type = 3;
				if (this.worldObj.rand.nextInt(30) == 0) this.rock_type = 4;
				if (this.worldObj.rand.nextInt(40) == 0) this.rock_type = 5;
				if (this.worldObj.rand.nextInt(50) == 0) this.rock_type = 6;
				if (this.worldObj.rand.nextInt(100) == 0) this.rock_type = 7;
				if (this.worldObj.rand.nextInt(200) == 0) this.rock_type = 8;
				if (this.worldObj.rand.nextInt(500) == 0) this.rock_type = 9;
				if (this.worldObj.rand.nextInt(500) == 0) this.rock_type = 10;
				if (this.worldObj.rand.nextInt(500) == 0) this.rock_type = 11;
				if (this.worldObj.rand.nextInt(1000) == 0) this.rock_type = 12;
			} else {
				this.rock_type = 9;
				if (this.worldObj.rand.nextInt(3) == 0) this.rock_type = 10;
				if (this.worldObj.rand.nextInt(5) == 0) this.rock_type = 11;
				if (this.worldObj.rand.nextInt(10) == 0) this.rock_type = 12;
			}

			
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(1 + this.rock_type / 4));
			this.setHealth((float)(1 + this.rock_type / 4));
		}
		if (!this.worldObj.isRemote) this.setRockType(this.rock_type);

		if (this.worldObj.isRemote) {
			if (this.rock_type == 9 && this.worldObj.rand.nextInt(20) == 0) {
				this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ,
					(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F),
					(double)(this.worldObj.rand.nextFloat() / 10.0F),
					(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F));
			}
			
			if (this.rock_type == 10 && this.worldObj.rand.nextInt(20) == 0) {
				this.worldObj.spawnParticle("happyVillager", this.posX, this.posY + 0.25D, this.posZ,
					(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F),
					(double)(this.worldObj.rand.nextFloat() / 2.0F),
					(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F));
			}

			if (this.rock_type == 11 && this.worldObj.rand.nextInt(20) == 0) {
				this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ,
						(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F),
						(double)(this.worldObj.rand.nextFloat() / 10.0F),
						(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F));
			}

			if (this.rock_type == 12 && this.worldObj.rand.nextInt(20) == 0) {
				this.worldObj.spawnParticle("fireworksSpark", this.posX, this.posY + 0.25D, this.posZ,
						(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F),
						(double)(this.worldObj.rand.nextFloat() / 5.0F),
						(double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 60.0F));
			}
			
		}
	}
	
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return null;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return null;
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.65F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	
	
	
	
	protected Item getDropItem()
	{
		return null;
	}

	public boolean canDespawn()
	{
		return false;
	}

	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		return true;
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean canBeCollidedWith()
	{
		return true;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return true;
	}

	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	public void performHurtAnimation()
	{
		this.hurtTime = this.maxHurtTime = 0;
		this.attackedAtYaw = 0.0F;
	}

	protected void onDeathUpdate()
	{
		this.setDead();
	}

	public void onDeath(DamageSource par1DamageSource)
	{
		this.setDead();
		if (this.rock_type == 1) this.dropItemRand(OreSpawnMain.MySmallRock, 1);
		if (this.rock_type == 2) this.dropItemRand(OreSpawnMain.MyRock, 1);
		if (this.rock_type == 3) this.dropItemRand(OreSpawnMain.MyRedRock, 1);
		if (this.rock_type == 4) this.dropItemRand(OreSpawnMain.MyGreenRock, 1);
		if (this.rock_type == 5) this.dropItemRand(OreSpawnMain.MyBlueRock, 1);
		if (this.rock_type == 6) this.dropItemRand(OreSpawnMain.MyPurpleRock, 1);
		if (this.rock_type == 7) this.dropItemRand(OreSpawnMain.MySpikeyRock, 1);
		if (this.rock_type == 8) this.dropItemRand(OreSpawnMain.MyTNTRock, 1);
		if (this.rock_type == 9) this.dropItemRand(OreSpawnMain.MyCrystalRedRock, 1);
		if (this.rock_type == 10) this.dropItemRand(OreSpawnMain.MyCrystalGreenRock, 1);
		if (this.rock_type == 11) this.dropItemRand(OreSpawnMain.MyCrystalBlueRock, 1);
		if (this.rock_type == 12) this.dropItemRand(OreSpawnMain.MyCrystalTNTRock, 1);
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)((OreSpawnMain.OreSpawnRand.nextFloat() - OreSpawnMain.OreSpawnRand.nextFloat()) / 3.0F), this.posY + 0.25D, this.posZ + (double)((OreSpawnMain.OreSpawnRand.nextFloat() - OreSpawnMain.OreSpawnRand.nextFloat()) / 3.0F), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setInteger("ButterflyType", this.rock_type);
	}

	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		
		this.rock_type = par1NBTTagCompound.getInteger("ButterflyType");
	}
}
