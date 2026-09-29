package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;






public class EntityButterfly extends EntityAmbientCreature
{
	private static final ResourceLocation texture1 = new ResourceLocation("orespawn", "butterfly.png");
	private static final ResourceLocation texture2 = new ResourceLocation("orespawn", "butterfly2.png");
	private static final ResourceLocation texture3 = new ResourceLocation("orespawn", "butterfly3.png");
	private static final ResourceLocation texture4 = new ResourceLocation("orespawn", "butterfly4.png");
	private static final ResourceLocation texture5 = new ResourceLocation("orespawn", "eyemoth.png");
	private static final ResourceLocation texture6 = new ResourceLocation("orespawn", "lunamoth.png");
	private static final ResourceLocation texture7 = new ResourceLocation("orespawn", "darkmoth.png");
	private static final ResourceLocation texture8 = new ResourceLocation("orespawn", "firemoth.png");
	private static final ResourceLocation texture9 = new ResourceLocation("orespawn", "vbutterfly1.png");
	
	public int butterfly_type = 0;
	private int attack_delay = 0;
	private GenericTargetSorter TargetSorter = null;
	private int force_sync = 25;
	
	private ChunkCoordinates currentFlightTarget = null;

	public EntityButterfly(World par1World) {
		super(par1World);
		
		this.butterfly_type = OreSpawnMain.OreSpawnRand.nextInt(4);
		this.setSize(0.4F, 0.4F);
		this.getNavigator().setAvoidsWater(true);
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.1F);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	public ResourceLocation getTexture(EntityButterfly a) {
		if (a instanceof Mothra) return texture5;
		if (a instanceof EntityLunaMoth) {
			if (((EntityLunaMoth)a).moth_type == 1) return texture5;
			if (((EntityLunaMoth)a).moth_type == 2) return texture7;
			if (((EntityLunaMoth)a).moth_type == 3) return texture8;
			return texture6;
		}
		if (this.butterfly_type == 1) {
			if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID4) {
				return texture9;
			}
			return texture2;
		}
		if (this.butterfly_type == 2) return texture3;
		if (this.butterfly_type == 3) return texture4;
		return texture1;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, this.butterfly_type);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.0F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch()
	{
		return 1.0F;
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
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {}

	protected void collideWithNearbyEntities() {}

	public int mygetMaxHealth()
	{
		return 2;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	
	
	protected void updateAITasks() {
		Block bid;
		int keep_trying = 25;
		if (this.isDead) return;
		super.updateAITasks();
		
		
		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		if (this.rand.nextInt(100) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
		{
			bid = Blocks.stone;
			while (bid != Blocks.air && keep_trying != 0) {
				this.currentFlightTarget.set((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
				bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
				keep_trying--;
			}
		} else if (this.rand.nextInt(10) == 0 && this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID4 && this.butterfly_type == 1 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
		{
			
			EntityLivingBase e = null;
			e = this.findSomethingToAttack();
			if (e != null)
			{
				
				this.currentFlightTarget.set((int)e.posX, (int)(e.posY + 1.0D), (int)e.posZ);
				if (this.getDistanceSqToEntity(e) < 6.0D) {
					this.attackEntityAsMob(e);
				}
			}
		}
		
		
		double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * (double)0.1F;
		this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * (double)0.1F;
		this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * (double)0.1F;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 0.5F;
		this.rotationYaw += var8;
	}


	
	
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (OreSpawnMain.OreSpawnRand.nextInt(2) != 0) return false;
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) return false;
		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
		return var4;
	}

	
	
	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) return false;
		
		if (par1EntityLiving == null)
		{
			return false;
		}
		if (par1EntityLiving == this)
		{
			return false;
		}
		if (!par1EntityLiving.isEntityAlive())
		{
			
			return false;
		}
		if (!this.getEntitySenses().canSee(par1EntityLiving))
		{
			
			return false;
		}
		
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode == true) {
				return false;
			}
			return true;
		}
		if (par1EntityLiving instanceof EntityHorse)
		{
			return true;
		}
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)8.0F, 5.0D, 8.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			
			if (this.isSuitableTarget(var4, false))
			{
				return var4;
			}
		}
		return null;
	}

	public void onUpdate()
	{
		super.onUpdate();
		this.motionY *= (double)0.6F;
		
		--this.force_sync;
		if (this.force_sync < 0) {
			this.force_sync = 25;
			if (this.worldObj.isRemote) {
				this.butterfly_type = this.dataWatcher.getWatchableObjectInt(20);
			} else {
				this.dataWatcher.updateObject(20, this.butterfly_type);
			}
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1) {}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double par1, boolean par3) {}
	
	/**
	 * Return whether this entity should NOT trigger a pressure plate or a tripwire.
	 */
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}

	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer == null) return false;
		
		
		
		
		
		if (!(par1EntityPlayer instanceof EntityPlayerMP)) return false;
		
		
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null) {
			if (var2.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				var2 = null;
			}
		}
		if (var2 != null) {
			return false;
		}
		
		
		if (par1EntityPlayer.dimension != OreSpawnMain.DimensionID6) {
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, OreSpawnMain.DimensionID6, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(OreSpawnMain.DimensionID6), OreSpawnMain.DimensionID6, this.worldObj));
		} else {
			
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, 0, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(0), 0, this.worldObj));
		}

		
		return true;
	}

	
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		Block bid;
		int i, j, k;
		
		for (k = -3; k < 3; k++)
		{
			for (j = -3; j < 3; j++)
			{
				for (i = 0; i < 5; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null) {
							if (s.equals("Butterfly")) {
								this.butterfly_type = 1;
								return true;
							}
						}
					}
				}
			}
		}

		bid = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);
		if (bid != Blocks.air) return false;
		if (!this.worldObj.isDaytime()) return false;
		if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID4) return true;
		if (this.posY < 50.0D) return false;
		return true;
	}

	/**
	 * Initialize this creature.
	 */
	public void initCreature() {}
	
	

	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setInteger("ButterflyType", this.butterfly_type);
	}

	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		
		this.butterfly_type = par1NBTTagCompound.getInteger("ButterflyType");
	}
}
