package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

























public class BandP extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.32F;
	private int whatset = 0;
	private int whatami = 0;
	public ItemStack[] MymainInventory = new ItemStack[100];
	int got_stuff = 0;

	public BandP(World par1World)
	{
		super(par1World);
		this.setSize(0.75F, 1.75F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 1000;
		this.fireResistance = 2;
		this.TargetSorter = new GenericTargetSorter(this);
		
		this.tasks.addTask(0, new EntityAIMoveThroughVillage(this, 0.5D, false));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 16, 0.5D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIMoveIndoors(this));
		
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.BandP_stats.attack);
	}

	
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		if (this.got_stuff != 0) return false;
		return true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (!this.worldObj.isRemote) {
			if (this.whatset == 0) {
				this.whatset = 1;
				this.whatami = this.worldObj.rand.nextInt(2);
				this.setWhat(this.whatami);
			}
		}
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.BandP_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.BandP_stats.defense;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "mob.villager.idle";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "mob.villager.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "mob.villager.death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 1.5F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	
	
	
	protected Item getDropItem()
	{
		return Items.emerald;
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		if (index == null) return null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	
	
	protected void dropFewItems(boolean par1, int par2) {
		int var4, i;
		ItemStack is;
		var4 = 10 + this.worldObj.rand.nextInt(5);
		for (i = 0; i < var4; i++) {
			this.dropItemRand(Items.emerald, 1);
		}

		if (this.getWhat() == 0) {
			var4 = 2 + this.worldObj.rand.nextInt(3);
			for (i = 0; i < var4; i++) {
				this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
				this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
			}
		}
		for (i = 0; i < this.MymainInventory.length; i++) {
			if (this.MymainInventory[i] != null) {
				if (this.MymainInventory[i].stackSize != 0) {
					is = this.dropItemRand(this.MymainInventory[i].getItem(), this.MymainInventory[i].stackSize);
					if (this.MymainInventory[i].stackSize == 1) is.setItemDamage(this.MymainInventory[i].getItemDamage());
				}
			}
		}
	}

	
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}

	
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return super.attackEntityAsMob(par1Entity);
	}

	
	
	protected void updateAITasks() {
		if (this.isDead) return;
		super.updateAITasks();
		if (this.worldObj.rand.nextInt(12) == 1) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				this.faceEntity(e, 10.0F, 10.0F);
				if (this.getDistanceSqToEntity(e) < 9.0D)
				{
					this.attackEntityAsMob(e);
					if (e instanceof EntityPlayer) {
						EntityPlayer p = (EntityPlayer)e;
						int i, k;
						
						k = -1;
						int kp = -1;
						for (i = 0; i < this.MymainInventory.length; i++) {
							if (this.MymainInventory[i] == null) {
								k = i;
								break;
							}
						}
						if (k >= 0) {
							for (i = p.inventory.armorInventory.length - 1; i >= 0; i--) {
								if (p.inventory.armorInventory[i] != null) {
									kp = i;
									break;
								}
							}
							if (kp >= 0) {
								this.MymainInventory[k] = p.inventory.armorInventory[kp];
								p.inventory.armorInventory[kp] = null;
								this.got_stuff++;
							}
							if (kp < 0) {
								for (i = p.inventory.mainInventory.length - 1; i >= 0; i--) {
									if (p.inventory.mainInventory[i] != null) {
										kp = i;
										break;
									}
								}
								if (kp >= 0) {
									this.MymainInventory[k] = p.inventory.mainInventory[kp];
									p.inventory.mainInventory[kp] = null;
									this.got_stuff++;
								}
							}
						}
					}
				} else {
					this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
				}
			}
		}
		
		
		
	}
	
	
	
	
	
	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
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
		if (par1EntityLiving instanceof EntityVillager)
		{
			return true;
		}
		if (par1EntityLiving instanceof Girlfriend)
		{
			return true;
		}
		if (par1EntityLiving instanceof Boyfriend)
		{
			return true;
		}
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, 6.0D, 20.0D));
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

	public int getWhat()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public void setWhat(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
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
							if (s.equals("Criminal")) return true;
						}
					}
				}
			}
		}
		if (!this.worldObj.isDaytime()) return false;
		if (this.posY < 50.0D) return false;
		if (this.posY < 100.0D) return false;
		BandP target = null;
		target = (BandP)this.worldObj.findNearestEntityWithinAABB(BandP.class, this.boundingBox.expand(32.0D, 12.0D, 32.0D), this);
		if (target != null)
		{
			return false;
		}
		EntityVillager target2 = null;
		target2 = (EntityVillager)this.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.boundingBox.expand(36.0D, 12.0D, 36.0D), this);
		if (target2 == null)
		{
			return false;
		}
		return true;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		if (this.got_stuff != 0) {
			par1NBTTagCompound.setTag("Inventory", this.writeToNBT(new NBTTagList()));
		}
		par1NBTTagCompound.setInteger("GotStuff", this.got_stuff);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.got_stuff = par1NBTTagCompound.getInteger("GotStuff");
		if (this.got_stuff != 0) {
			NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Inventory", 10);
			this.readFromNBT(nbttaglist);
		}
		
	}
	
	
	
	
	
	
	
	public NBTTagList writeToNBT(NBTTagList par1NBTTagList)
	{
		int i;
		for (i = 0; i < this.MymainInventory.length; i++)
		{
			if (this.MymainInventory[i] != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte)i);
				this.MymainInventory[i].writeToNBT(nbttagcompound);
				par1NBTTagList.appendTag(nbttagcompound);
			}
		}

		return par1NBTTagList;
	}

	
	
	
	public void readFromNBT(NBTTagList par1NBTTagList)
	{
		this.MymainInventory = new ItemStack[100];

		for (int i = 0; i < par1NBTTagList.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound = par1NBTTagList.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
			if (itemstack != null)
			{
				if (j >= 0 && j < this.MymainInventory.length)
				{
					this.MymainInventory[j] = itemstack;
				}
			}
		}
	}
}
