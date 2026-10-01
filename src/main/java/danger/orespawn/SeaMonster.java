package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;







































public class SeaMonster extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int hurt_timer = 0;
	private float moveSpeed = 0.25F;

	public SeaMonster(World par1World)
	{
		super(par1World);
		this.moveSpeed = 0.25F;
		this.setSize(1.25F, 2.5F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 150;
		this.fireResistance = 30;
		this.isImmuneToFire = false;
		
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.SeaMonster_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		if (this.renderdata == null) {
			this.renderdata = new RenderInfo();
		}
		this.renderdata.rf1 = 0.0F;
		this.renderdata.rf2 = 0.0F;
		this.renderdata.rf3 = 0.0F;
		this.renderdata.rf4 = 0.0F;
		this.renderdata.ri1 = 0;
		this.renderdata.ri2 = 0;
		this.renderdata.ri3 = 0;
		this.renderdata.ri4 = 0;
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
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

	public int mygetMaxHealth()
	{
		return OreSpawnMain.SeaMonster_stats.health;
	}




	public RenderInfo getRenderInfo()
	{
		return this.renderdata;
	}

	public void setRenderInfo(RenderInfo r)
	{
		this.renderdata.rf1 = r.rf1;
		this.renderdata.rf2 = r.rf2;
		this.renderdata.rf3 = r.rf3;
		this.renderdata.rf4 = r.rf4;
		this.renderdata.ri1 = r.ri1;
		this.renderdata.ri2 = r.ri2;
		this.renderdata.ri3 = r.ri3;
		this.renderdata.ri4 = r.ri4;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.SeaMonster_stats.defense;
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
		
		
		
		if (this.isInWater()) {
			this.moveSpeed = 0.55F;
		} else {
			this.moveSpeed = 0.25F;
		}
	}



	public int getSeaMonsterHealth()
	{
		return (int)this.getHealth();
	}


	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (this.rand.nextInt(3) == 0) {
			return "orespawn:seamonster_living";
		}
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:seamonster_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:seamonster_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 1.0F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
     */
	protected float getSoundPitch() {
		return 1.0F;
	}






	protected Item getDropItem()
	{
		return Items.fish;
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}




	protected void dropFewItems(boolean par1, int par2)
	{
		int var4, var5, unused1;
		ItemStack is = null;
		
		this.dropItemRand(OreSpawnMain.SeaMonsterScale, 1);
		this.dropItemRand(Items.item_frame, 1);
		
		var5 = 9 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < var5; var4++) {
			this.dropItemRand(Items.fish, 1);
		}

		var4 = this.worldObj.rand.nextInt(20);
		switch (var4) {
			
			case 1:
				is = this.dropItemRand(Items.iron_ingot, 1);
				break;
			
			case 3:
				is = this.dropItemRand(Items.iron_sword, 1);
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
				break;
			case 4:
				is = this.dropItemRand(Items.iron_shovel, 1);
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
				break;
			case 5:
				is = this.dropItemRand(Items.iron_pickaxe, 1);
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
				break;
			case 6:
				is = this.dropItemRand(Items.iron_axe, 1);
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
				break;
			case 7:
				is = this.dropItemRand(Items.iron_hoe, 1);
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
				break;
			case 8:
				is = this.dropItemRand(Items.iron_helmet, 1);
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
				break;
			case 9:
				is = this.dropItemRand(Items.iron_chestplate, 1);
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				break;
			case 10:
				is = this.dropItemRand(Items.iron_leggings, 1);
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				break;
			case 11:
				is = this.dropItemRand(Items.iron_boots, 1);
				if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
				if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
				break;
			case 13:
				this.dropItemRand(Item.getItemFromBlock(Blocks.iron_block), 1);
				break;
			case 2:
			case 12:
			default:
				
				break;
		}
	}



	/**
	 * Initialize this creature.
	 */
	public void initCreature() {}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}




	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity != null && par1Entity instanceof EntityLivingBase)
			{
				
				double ks = 0.6;
				double inair = 0.1;
				float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
				if (par1Entity.isDead || par1Entity instanceof EntityPlayer) inair *= 2.0D;
				par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			}
			return true;
		}
		else
		{
			return false;
		}
	}


	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		
		
		if (par1DamageSource.getDamageType().equals("cactus")) {
			return false;
		}
		
		
		Entity e = par1DamageSource.getEntity();
		
		
		if (this.hurt_timer <= 0) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			this.hurt_timer = 8;
		}

		if (e != null && e instanceof EntityLiving)
		{
			if (e instanceof SeaMonster) return false;
			this.setAttackTarget((EntityLiving)e);
			this.setTarget(e);
			this.getNavigator().tryMoveToEntityLiving((EntityLiving)e, 1.2);
		}

		return ret;
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


	protected void updateAITasks()
	{
		int i, j;
		if (this.isDead) return;
		super.updateAITasks();
		if (this.hurt_timer > 0) this.hurt_timer--;
		
		if (!this.isInWater() && this.worldObj.rand.nextInt(25) == 0) {
			
			
			this.closest = 99999;
			this.tx = this.ty = this.tz = 0;
			for (i = 1; i < 12; i++) {
				j = i;
				if (j > 10) j = 10;
				if (this.scan_it((int)this.posX, (int)this.posY - 1, (int)this.posZ, i, j, i) == true) break;
				if (i >= 5) i++;
			}

			if (this.closest < 99999) {
				this.getNavigator().tryMoveToXYZ((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
			} else {
				if (this.worldObj.rand.nextInt(40) == 1)
					this.heal(-1.0F);
				if (this.getHealth() <= 0.0F) {
					this.setDead();
					return;
				}
			}
		}
		
		
		if (this.worldObj.rand.nextInt(5) == 1) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				this.faceEntity(e, 10.0F, 10.0F);
				if (this.getDistanceSqToEntity(e) < (double)((4.0F + e.width / 2.0F) * (4.0F + e.width / 2.0F))) {
					this.setAttacking(1);
					
					if (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1)
					{
						this.attackEntityAsMob(e);
					}
				} else {
					this.getNavigator().tryMoveToEntityLiving(e, 1.0D);
				}
			} else {
				this.setAttacking(0);
			}
		}
		
		
		if (this.worldObj.rand.nextInt(120) == 1 && this.isInWater()) {
			if (this.getHealth() < (float)this.mygetMaxHealth()) {
				this.playSound("splash", 1.5F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
				
				this.heal(1.0F);
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
		if (par1EntityLiving instanceof SeaMonster)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntityMob)
		{
			return true;
		}
		if (OreSpawnMain.OreSpawnUtils.isAttackableNonMob(par1EntityLiving)) {
			return true;
		}
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 4.0D, 16.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		EntityLivingBase e;
		Entity var3 = null;
		EntityLivingBase var4 = null;
		
		e = this.getAttackTarget();
		if (e != null && e.isEntityAlive()) {
			return e;
		}
		this.setAttackTarget(null);

		
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







	public final int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
	}


	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		SeaMonster target = null;
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
							if (s.equals("Sea Monster")) return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) return false;
		
		if (this.worldObj.isDaytime()) return false;
		if (!this.isValidLightLevel()) return false;
		target = (SeaMonster)this.worldObj.findNearestEntityWithinAABB(SeaMonster.class, this.boundingBox.expand(16.0D, 5.0D, 16.0D), this);
		if (target != null)
		{
			return false;
		}
		return true;
	}


	public boolean canBreatheUnderwater()
	{
		return true;
	}
}
