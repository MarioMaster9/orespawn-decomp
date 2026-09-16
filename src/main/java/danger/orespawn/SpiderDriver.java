package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;








public class SpiderDriver extends EntitySpider
{
	private GenericTargetSorter TargetSorter = null;

	public SpiderDriver(World par1World) {
		super(par1World);
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(3, new MyEntityAIWander(this, 0.65F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		if (this.ridingEntity != null) return false;
		return true;
	}

	
	
	
	
	public boolean isAIEnabled() {
		return true;
	}

	
	protected Entity findPlayerToAttack() {
		double d0 = 16.0D;
		return this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);
	}

	
	
	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.rand.nextInt(5) == 0 && this.ridingEntity == null) {
				EntityLivingBase e = this.findSpiderRobot();
				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if (this.getDistanceSqToEntity(e) < (double)((4.0F + e.width / 2.0F) * (4.0F + e.width / 2.0F))) {
						this.mountEntity(e);
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 0.55);
					}
				}
			}

			if (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.rand.nextInt(4) == 0 && this.ridingEntity != null) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if ((this.getDistanceSqToEntity(e) < (double)((11.0F + e.width / 2.0F) * (11.0F + e.width / 2.0F)))) {
						
					} else if (this.ridingEntity instanceof SpiderRobot) {
						SpiderRobot sp = (SpiderRobot)this.ridingEntity;
						
						double d1 = e.posZ - this.posZ;
						double d2 = e.posX - this.posX;
						double dd = Math.atan2(d1, d2);
						sp.goThisWay(0.35 * Math.cos(dd), 0.35 * Math.sin(dd));
					}
				}
			}
		}
	}

	
	
	
	
	
	
	
	protected void attackEntity(Entity par1Entity, float par2) {
		if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 16;
			this.attackEntityAsMob(par1Entity);
			if (this.worldObj.rand.nextInt(2) == 0) {
				((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 60, 0));
			}
		}

	}

	
	
	
	public int getTotalArmorValue() {
		if (this.ridingEntity != null) return 8;
		return 20;
	}

	
	private EntityLivingBase findSpiderRobot() {
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(SpiderRobot.class, this.boundingBox.expand(25.0D, 15.0D, 25.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			if (var4.riddenByEntity == null) {
				return var4;
			}
		}
		return null;
	}

	
	
	
	
	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
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
		
		if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) return false;
		
		if (par1EntityLiving instanceof SpiderRobot)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof SpiderDriver)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntitySpider)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntityCaveSpider)
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
			if (p.capabilities.isCreativeMode) {
				return false;
			}
			return true;
		}
		if (this.getDistanceSqToEntity(par1EntityLiving) < 36.0D) return false;
		
		return true;
	}

	
	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)35.0F, 15.0D, (double)35.0F));
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

	
	
	
	
	public boolean getCanSpawnHere() {
		SpiderRobot target = null;
		target = (SpiderRobot)this.worldObj.findNearestEntityWithinAABB(SpiderRobot.class, this.boundingBox.expand((double)24.0F, 12.0D, (double)24.0F), this);
		if (target != null)
		{
			return true;
		}
		return super.getCanSpawnHere();
	}
}
