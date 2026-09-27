package danger.orespawn;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

















public class Chipmunk extends EntityCannonFodder
{
	private float moveSpeed = 0.38F;

	public Chipmunk(World par1World)
	{
		super(par1World);
		
		this.setSize(0.35F, 0.35F);
		this.moveSpeed = 0.38F;
		this.fireResistance = 100;
		this.getNavigator().setAvoidsWater(true);
		this.setSitting(false);
		this.experienceValue = 5;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new MyEntityAIFollowOwner(this, 2.0F, 10.0F, 2.0F));
		this.tasks.addTask(3, new MyEntityAIAvoidEntity(this, EntityMob.class, 8.0F, 1.0D, (double)1.6F));
		this.tasks.addTask(4, new EntityAITempt(this, (double)1.2F, Items.apple, false));
		this.tasks.addTask(5, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(6, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityLiving.class, 5.0F));
		this.tasks.addTask(9, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(10, new EntityAILookIdle(this));
		this.tasks.addTask(11, new EntityAIMoveIndoors(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.setSitting(false);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1) {
		float i = (float)MathHelper.ceiling_float_int(par1 - 3.0F);
		
		if (i > 0.0F)
		{
			if (i > 3.0F)
			{
				this.playSound("damage.fallbig", 1.0F, 1.0F);
			}
			else
			{
				this.playSound("damage.fallsmall", 1.0F, 1.0F);
			}
			if (i > 2.0F)
			{
				i = 2.0F;
			}
			this.attackEntityFrom(DamageSource.fall, i);
		}
		
	}
	
	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		int unused1, unused2;
		Block bid;
		
		if (this.isDead) return;
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		if (this.worldObj.rand.nextInt(250) == 0)
		{
			this.heal(1.0F);
		}
		if (!this.worldObj.isRemote && this.worldObj.rand.nextInt(600) == 1) {
			bid = this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ);
			if (bid == Blocks.dirt || bid == Blocks.farmland) {
				if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ, Blocks.air, 0, 2);
			}
		}
		super.updateAITick();
		
	}
	
	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	
	public boolean canBreatheUnderwater()
	{
		return false;
	}

	public int mygetMaxHealth()
	{
		return 5;
	}

	
	
	public int getChipmunkHealth()
	{
		return (int)this.getHealth();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		
		
		if (var2 != null)
		{
			if (var2.stackSize <= 0)
			{
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				var2 = null;
			}
		}

		if (super.interact(par1EntityPlayer)) {
			return true;
		}
		
		if (var2 != null && var2.getItem() == Items.apple && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D)
		{
			if (!this.isTamed())
			{
				if (!this.worldObj.isRemote)
				{
					if (this.rand.nextInt(2) == 0)
					{
						this.setTamed(true);
						this.func_152115_b(par1EntityPlayer.getUniqueID().toString());
						this.playTameEffect(true);
						this.worldObj.setEntityState(this, (byte)7);
						this.heal((float)this.mygetMaxHealth() - this.getHealth());
						
					}
					else
					{
						this.playTameEffect(false);
						this.worldObj.setEntityState(this, (byte)6);
					}
					
				}
			}
			else if (this.func_152114_e(par1EntityPlayer))
			{
				if (this.worldObj.isRemote) {
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
				}

				if ((float)this.mygetMaxHealth() > this.getHealth()) {
					this.heal((float)this.mygetMaxHealth() - this.getHealth());
				}
			}

			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				--var2.stackSize;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		} else if (this.isTamed() && var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.deadbush) && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer))
		{
			
			if (!this.worldObj.isRemote)
			{
				this.setTamed(false);
				this.func_152115_b("");
				this.playTameEffect(false);
				this.worldObj.setEntityState(this, (byte)6);
			}
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				--var2.stackSize;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		}
		
		if (this.isTamed() && var2 != null && var2.getItem() == Items.name_tag && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer))
		{
			this.setCustomNameTag(var2.getDisplayName());
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				--var2.stackSize;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		} else if (this.isTamed() && this.func_152114_e(par1EntityPlayer) && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D)
		{
			if (!this.isSitting()) {
				this.setSitting(true);
			} else {
				this.setSitting(false);
			}
			return true;
		}
		
		return false;
	}

	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (this.isSitting())
		{
			return null;
		}
		return null;
	}
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:scorpion_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:cryo_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	
	
	
	protected Item getDropItem()
	{
		return Items.wheat;
	}

	
	
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = 0;
		
		if (this.isTamed())
		{
			var3 = this.rand.nextInt(5);
			var3 += 2;
			for (int var4 = 0; var4 < var3; ++var4)
			{
				this.dropItem(Item.getItemFromBlock(Blocks.red_flower), 1);
			}
		} else {
			super.dropFewItems(par1, par2);
		}

	}

	
	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch()
	{
		return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.0F;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere() {
		if (this.posY < 50.0D) return false;
		if (this.findBuddies() > 2) return false;
		return true;
	}

	private int findBuddies() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Chipmunk.class, this.boundingBox.expand(20.0D, 10.0D, 20.0D));
		return var5.size();
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		if (this.isChild()) {
			this.func_110163_bv();
			return false;
		}
		if (this.isNoDespawnRequired()) return false;
		if (this.isTamed()) return false;
		return true;
	}

	
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	
	
	public Chipmunk spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Chipmunk(this.worldObj);
	}

	
	
	
	public boolean isWheat(ItemStack par1ItemStack)
	{
		return par1ItemStack != null && par1ItemStack.getItem() == Items.apple;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
