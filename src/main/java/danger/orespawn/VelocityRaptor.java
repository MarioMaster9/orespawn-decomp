package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
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


















public class VelocityRaptor extends EntityCannonFodder
{
	private float moveSpeed = 0.55F;

	public VelocityRaptor(World par1World)
	{
		super(par1World);
		
		this.setSize(0.5F, 0.6F);
		this.fireResistance = 10;
		this.getNavigator().setAvoidsWater(true);
		this.setSitting(false);
		this.experienceValue = 5;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new MyEntityAIFollowOwner(this, 1.5F, 10.0F, 2.0F));
		this.tasks.addTask(3, new MyEntityAIAvoidEntity(this, EntityMob.class, 8.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(4, new EntityAITempt(this, 1.25D, Items.apple, false));
		this.tasks.addTask(5, new EntityAIPanic(this, (double)1.6F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new MyEntityAIWander(this, 0.9F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(9, new EntityAIMoveIndoors(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)2.0F);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.setSitting(false);
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (!this.worldObj.isDaytime()) return false;
		return true;
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



	private int closest = 99999;
	private int tx = 0, ty = 0, tz = 0;

	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz){
		int found = 0;
		int i, j, d;
		Block bid;

		//Fixed x, scan two sides of 3d rectangle
		for(i=-dy;i<=dy;i++){
			for(j=-dz;j<=dz;j++){
				bid = worldObj.getBlock(x+dx, y+i, z+j);
				if(bid == Blocks.tallgrass || bid == Blocks.yellow_flower || bid == Blocks.red_flower || bid == Blocks.deadbush || bid == Blocks.double_plant){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = worldObj.getBlock(x-dx, y+i, z+j);
				if(bid == Blocks.tallgrass || bid == Blocks.yellow_flower || bid == Blocks.red_flower || bid == Blocks.deadbush || bid == Blocks.double_plant){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x-dx; ty = y+i; tz = z+j;
						found++;
					}
				} 			
			}
		}
		//Fixed y, scan two sides of 3d rectangle
		for(i=-dx;i<=dx;i++){
			for(j=-dz;j<=dz;j++){
				bid = worldObj.getBlock(x+i, y+dy, z+j);
				if(bid == Blocks.tallgrass || bid == Blocks.yellow_flower || bid == Blocks.red_flower || bid == Blocks.deadbush || bid == Blocks.double_plant){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = worldObj.getBlock(x+i, y-dy, z+j);
				if(bid == Blocks.tallgrass || bid == Blocks.yellow_flower || bid == Blocks.red_flower || bid == Blocks.deadbush || bid == Blocks.double_plant){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y-dy; tz = z+j;
						found++;
					}
				} 			
			}
		}    	
		//Fixed z, scan two sides of 3d rectangle
		for(i=-dx;i<=dx;i++){
			for(j=-dy;j<=dy;j++){
				bid = worldObj.getBlock(x+i, y+j, z+dz);
				if(bid == Blocks.tallgrass || bid == Blocks.yellow_flower || bid == Blocks.red_flower || bid == Blocks.deadbush || bid == Blocks.double_plant){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = worldObj.getBlock((int)x+i, (int)y+j, (int)z-dz);
				if(bid == Blocks.tallgrass || bid == Blocks.yellow_flower || bid == Blocks.red_flower || bid == Blocks.deadbush || bid == Blocks.double_plant){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z-dz;
						found++;
					}
				} 			
			}
		}    	

		if(found != 0)return true;
		return false;
	}

	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		int i, j;
		super.updateAITick();
		if (this.isDead) return;
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		if (!this.isSitting()) {
			if (this.worldObj.rand.nextInt(20) == 0 && this.getVHealth() < this.mygetMaxHealth() || this.worldObj.rand.nextInt(250) == 0)
			{
				
				
				if (OreSpawnMain.PlayNicely == 0) {
					this.closest = 99999;
					this.tx = this.ty = this.tz = 0;
					for (i = 1; i < 10; i++) {
						j = i;
						if (j > 2) j = 2;
						if (this.scan_it((int)this.posX, (int)this.posY + 1, (int)this.posZ, i, j, i) == true) break;
						if (i >= 5) i++;
					}

					if (this.closest < 99999) {
						
						this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
						if (this.closest < 12) {
							
							if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
							this.heal(2.0F);
							this.playSound("random.burp", 0.5F, this.worldObj.rand.nextFloat() * 0.2F + 1.5F);
						}
					}
				}
			}
		}
		
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
		return this.isTamed() ? 20 : 10;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}



	public int getVHealth()
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
					
					par1EntityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.6F);
				}

				if ((float)this.mygetMaxHealth() > this.getHealth()) {
					this.heal((float)this.mygetMaxHealth() - this.getHealth());
				}
			}

			
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				var2.stackSize--;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		}
		if (this.isTamed() && var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.deadbush) && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer))
		{
			
			if (!this.worldObj.isRemote) {
				
				this.setTamed(false);
				this.setHealth((float)this.mygetMaxHealth());
				this.func_152115_b("");
				this.playTameEffect(false);
				this.worldObj.setEntityState(this, (byte)6);
			} else {
				
				par1EntityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.3F);
			}
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				var2.stackSize--;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		}
		if (this.isTamed() && var2 != null && var2.getItem() == Items.name_tag && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer)) {
			
			this.setCustomNameTag(var2.getDisplayName());
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				var2.stackSize--;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		} else if (this.isTamed() && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer)) {
			
			if (!this.isSitting()) {
				this.setSitting(true);
			} else {
				this.setSitting(false);
			}

			if (this.worldObj.isRemote)
			{
				
				par1EntityPlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.3F);
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
		return "orespawn:cryo_hurt";
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
		return null;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = 0;
		
		if (this.isTamed())
		{
			var3 = this.rand.nextInt(5);
			var3 += 2;
			for (int var4 = 0; var4 < var3; var4++)
			{
				this.dropItem(Item.getItemFromBlock(Blocks.red_flower), 1);
			}
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
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		float p2 = par2;
		if (p2 > 10.0F) p2 = 10.0F;
		ret = super.attackEntityFrom(par1DamageSource, p2);
		return ret;
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
		if (this.isTamed()) return false;
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return this.spawnBabyAnimal(entityageable);
	}


	public VelocityRaptor spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return new VelocityRaptor(this.worldObj);
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
