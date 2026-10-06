package danger.orespawn;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;























public class Whale extends EntityAnimal
{
	private float moveSpeed = 0.35F;
	private int spray = 0;
	private int spray_timer = 0;

	public Whale(World par1World)
	{
		super(par1World);
		
		this.setSize(1.5F, 2.5F);
		this.moveSpeed = 0.35F;
		this.fireResistance = 100;
		this.experienceValue = 40;
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new EntityAITempt(this, (double)1.2F, Items.fish, false));
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
		if (this.spray == 0) {
			if (this.spray_timer > 0) this.spray_timer--;
			if (this.spray_timer == 0) {
				this.spray_timer = 250 + this.worldObj.rand.nextInt(250);
				this.spray = 25 + this.worldObj.rand.nextInt(25);
			}
		}
		if (this.worldObj.isRemote && this.spray > 0) {
			double d, dir;
			double dx, dz;
			int i;
			for (i = 0; i < 20; i++) {
				d = this.worldObj.rand.nextDouble() * 0.75D;
				d *= d;
				dir = this.worldObj.rand.nextDouble() * 2.0D * Math.PI;
				dir -= Math.PI;
				dx = Math.cos(dir) * d / 2.0D;
				dz = Math.sin(dir) * d / 2.0D;
				dir += Math.PI / 2D;
				if (i < 10) {
					this.worldObj.spawnParticle("bubble", this.posX + dx, this.posY + 1.0D + d, this.posZ + dz,
							Math.cos(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D,
							(double)(this.worldObj.rand.nextFloat() * 2.0F),
							Math.sin(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D);
				} else {
					this.worldObj.spawnParticle("splash", this.posX + dx, this.posY + 1.0D + d, this.posZ + dz,
							Math.cos(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D,
							(double)(this.worldObj.rand.nextFloat() * 2.0F),
							Math.sin(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D);
				}
			}
			this.spray--;
		}

		if (this.worldObj.rand.nextInt(200) == 1) this.heal(1.0F);
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
		return 100;
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
		return "orespawn:little_splat";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:big_splat";
	}


	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.9F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 0.5F;
	}




	protected Item getDropItem() {
		return Items.fish;
	}

	private void dropItemRand(Item index, int par1)
	{
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		
		this.worldObj.spawnEntityInWorld(var3);
	}




	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = 0;
		var3 = this.rand.nextInt(25);
		var3 += 20;
		for (int var4 = 0; var4 < var3; var4++)
		{
			this.dropItemRand(Items.fish, 1);
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
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = worldObj.getBlock(x-dx, y+i, z+j);
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
				bid = worldObj.getBlock(x+i, y+dy, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = worldObj.getBlock(x+i, y-dy, z+j);
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
				bid = worldObj.getBlock(x+i, y+j, z+dz);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = worldObj.getBlock((int)x+i, (int)y+j, (int)z-dz);
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

	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		int i, j;
		
		super.updateAITick();
		if (this.isDead) return;
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		
		
		if (!this.isInWater() && this.worldObj.rand.nextInt(20) == 0)
		{
			
			
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
					this.heal(-4.0F);
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
		List var5 = this.worldObj.getEntitiesWithinAABB(Whale.class, this.boundingBox.expand(32.0D, 8.0D, 32.0D));
		return var5.size();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (!this.worldObj.isDaytime()) return false;
		if (this.worldObj.rand.nextInt(50) != 1) return false;
		if (this.findBuddies() > 0) return false;
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

	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return this.spawnBabyAnimal(entityageable);
	}


	public Whale spawnBabyAnimal(EntityAgeable par1EntityAgeable)
	{
		return new Whale(this.worldObj);
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
