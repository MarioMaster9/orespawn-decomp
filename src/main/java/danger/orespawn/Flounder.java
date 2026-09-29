package danger.orespawn;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;























public class Flounder extends EntityAnimal
{
	private float moveSpeed = 0.25F;

	public Flounder(World par1World)
	{
		super(par1World);
		
		this.setSize(0.55F, 0.25F);
		this.moveSpeed = 0.25F;
		this.fireResistance = 15;
		this.experienceValue = 5;
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(4, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
		this.tasks.addTask(6, new MyEntityAIWander(this, 1.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
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
		return true;
	}

	public int mygetMaxHealth()
	{
		return 5;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "splash";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "little_splat";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:ratdead";
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
		return Items.fish;
	}




	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = 0;
		var3 = this.rand.nextInt(2);
		var3++;
		for (int var4 = 0; var4 < var3; var4++)
		{
			this.dropItem(Items.fish, 1);
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
				bid = this.worldObj.getBlock(x+dx, y+i, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x-dx, y+i, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
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
				bid = this.worldObj.getBlock(x+i, y+dy, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y-dy, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
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
				bid = this.worldObj.getBlock(x+i, y+j, z+dz);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y+j, z-dz);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
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






	protected void updateAITick()
	{
		super.updateAITick();
		if (this.isDead) return;
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		
		
		if (!this.isInWater() && this.worldObj.rand.nextInt(20) == 0)
		{
			int i, j;
			
			this.closest = 99999;
			this.tx = this.ty = this.tz = 0;
			for (i = 1; i < 11; i++) {
				j = i;
				if (j > 4) j = 4;
				if (this.scan_it((int)this.posX, (int)this.posY - 1, (int)this.posZ, i, j, i) == true) break;
				if (i >= 5) i++;
			}

			if (this.closest < 99999) {
				this.getNavigator().tryMoveToXYZ((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.0D);
			} else {
				if (this.worldObj.rand.nextInt(25) == 1)
					this.heal(-1.0F);
				if (this.getHealth() <= 0.0F) {
					this.setDead();
					return;
				}
			}
		}
		if (this.isInWater() && this.worldObj.rand.nextInt(50) == 0) {
			this.playSound("splash", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
			this.heal(1.0F);
		}
		
	}

	private int findBuddies()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(Flounder.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
		return var5.size();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (!this.worldObj.isDaytime()) return false;
		if (this.worldObj.rand.nextInt(20) != 1) return false;
		if (this.findBuddies() > 10) return false;
		return true;
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
		return true;
	}


	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}



	public Flounder spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Flounder(this.worldObj);
	}

	
	
	
	public boolean isWheat(ItemStack par1ItemStack)
	{
		return par1ItemStack != null && par1ItemStack.getItem() == Items.fish;
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
