package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;






















public class Beaver extends EntityAnimal
{
	private float moveSpeed = 0.15F;
	private GenericTargetSorter TargetSorter = null;

	public Beaver(World par1World)
	{
		super(par1World);
		
		this.setSize(0.6F, 0.8F);
		this.moveSpeed = 0.2F;
		this.fireResistance = 100;
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 5;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, 1.0D, 1.5D));
		this.tasks.addTask(4, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, 1.5D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(8, new EntityAILookIdle(this));
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
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	public boolean isWood(Block bid)
	{
		if (bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == OreSpawnMain.MySkyTreeLog) {
			return true;
		}
		if (bid == Blocks.fence || bid == Blocks.fence_gate || bid == Blocks.standing_sign) {
			return true;
		}
		return false;
	}

	private int closest = 99999;
	private int tx = 0, ty = 0, tz = 0;
	
	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;
		int i, j, d;
		Block bid;
		
		//Fixed x, scan two sides of 3d rectangle
		for(i=-dy;i<=dy;i++){
			for(j=-dz;j<=dz;j++){
				bid = this.worldObj.getBlock(x+dx, y+i, z+j);
				if(isWood(bid)){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x-dx, y+i, z+j);
				if(isWood(bid)){
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
				if(isWood(bid)){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y-dy, z+j);
				if(isWood(bid)){
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
				if(isWood(bid)){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y+j, z-dz);
				if(isWood(bid)){
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

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 4.0D + (double)this.worldObj.rand.nextInt(4), this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), is);
		
		
		
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	
	
	
	public void breakRecursor(World world, int x, int y, int z, int xf, int yf, int zf, int recursion)
	{
		int var7 = 1;
		
		if (recursion > 200) return;
		
		for (int var9 = -var7; var9 <= var7; var9++)
		{
			for (int var10 = -var7; var10 <= var7; var10++)
			{
				for (int var11 = -var7; var11 <= var7; var11++)
				{
					
					if (var9 == 0 && var10 == 0 && var11 == 0) continue;
					if (x + var9 == xf && y + var10 == yf && z + var11 == zf) continue;
					if (recursion > 0
							&& x + var9 >= xf - var7 && x + var9 <= xf + var7
							&& y + var10 >= yf - var7 && y + var10 <= yf + var7
							&& z + var11 >= zf - var7 && z + var11 <= zf + var7) continue;
					
					
					Block var12 = world.getBlock(x + var9, y + var10, z + var11);
					if (this.isWood(var12))
					{
						world.setBlock(x + var9, y + var10, z + var11, Blocks.air, 0, 2);
						this.dropItemRand(Item.getItemFromBlock(var12), 1);
						this.breakRecursor(world, x + var9, y + var10, z + var11, x, y, z, recursion + 1);
					}
				}
			}
		}
		
	}

	
	
	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		int i, j;
		if (this.isDead) return;
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		if (this.worldObj.rand.nextInt(30) == 0 && this.getBeaverHealth() < this.mygetMaxHealth() || this.worldObj.rand.nextInt(350) == 1)
		{
			if (OreSpawnMain.PlayNicely == 0)
			{
				
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;
				for (i = 1; i < 11; i++) {
					j = i;
					if (j > 2) j = 2;
					if (this.scan_it((int)this.posX, (int)this.posY + 1, (int)this.posZ, i, j, i) == true) break;
					if (i >= 6) i++;
				}
				i = 0;
				if (this.closest < 99999)
				{
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
					if (this.closest < 12)
					{
						if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
							this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
							this.breakRecursor(this.worldObj, this.tx, this.ty, this.tz, this.tx, this.ty, this.tz, i);
						}
						this.heal(1.0F);
						this.playSound("orespawn:chainsaw", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
					}
				}
			}
		}
		
		
		if (this.worldObj.rand.nextInt(200) == 1) {
			Beaver buddy = this.findBuddy();
			if (buddy != null) {
				this.getNavigator().tryMoveToXYZ(buddy.posX, buddy.posY, buddy.posZ, 0.5D);
			}
		}
		
		super.updateAITick();
		
	}
	
	private Beaver findBuddy()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(Beaver.class, this.boundingBox.expand(16.0D, 6.0D, 16.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		Beaver var4 = null;
		
		if (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (Beaver)var3;
			return var4;
		}
		return null;
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
		return 15;
	}

	
	
	public int getBeaverHealth()
	{
		return (int)this.getHealth();
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
		return Items.porkchop;
	}

	
	
	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch()
	{
		return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.0F;
	}

	
	
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (this.posY > 100.0D) return false;
		Block bid = this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ);
		if (bid != Blocks.dirt && bid != Blocks.grass && bid != Blocks.tallgrass && bid != Blocks.leaves) return false;
		return true;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		return false;
	}

	
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	
	
	public Beaver spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Beaver(this.worldObj);
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
