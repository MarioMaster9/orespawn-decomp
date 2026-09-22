package danger.orespawn;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;











public abstract class MyEntityAITarget extends EntityAIBase
{
	protected EntityLiving taskOwner;
	protected float targetDistance;
	protected boolean shouldCheckSight;
	private boolean field_75303_a;
	private int field_75301_b;
	private int field_75302_c;
	private int field_75298_g;

	public MyEntityAITarget(EntityLiving par1EntityLiving, float par2, boolean par3)
	{
		this(par1EntityLiving, par2, par3, false);
	}

	public MyEntityAITarget(EntityLiving par1EntityLiving, float par2, boolean par3, boolean par4)
	{
		this.field_75301_b = 0;
		this.field_75302_c = 0;
		this.field_75298_g = 0;
		this.taskOwner = par1EntityLiving;
		this.targetDistance = par2;
		this.shouldCheckSight = par3;
		this.field_75303_a = par4;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		EntityLivingBase var1 = this.taskOwner.getAttackTarget();
		
		if (var1 == null)
		{
			return false;
		}
		if (!var1.isEntityAlive())
		{
			this.taskOwner.setAttackTarget(null);
			return false;
		}
		if (this.taskOwner.getDistanceSqToEntity(var1) > (double)(this.targetDistance * this.targetDistance))
		{
			return false;
		}
		
		if (this.taskOwner instanceof EntityTameable && ((EntityTameable)this.taskOwner).isTamed())
		{
			if (var1 instanceof EntityTameable && ((EntityTameable)var1).isTamed())
			{
				
				return false;
			}
		}
		
		if (this.shouldCheckSight)
		{
			if (this.taskOwner.getEntitySenses().canSee(var1))
			{
				this.field_75298_g = 0;
			}
			else if (++this.field_75298_g > 60)
			{
				return false;
			}
		}

		return true;
	}

	
	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.field_75301_b = 0;
		this.field_75302_c = 0;
		this.field_75298_g = 0;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.taskOwner.setAttackTarget((EntityLiving)null);
	}

	
	
	/**
	 * A method used to see if an entity is a suitable target through a number of checks.
	 */
	protected boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
		if (par1EntityLiving == null)
		{
			
			return false;
		}
		
		if (par1EntityLiving == this.taskOwner)
		{
			
			return false;
		}
		
		
		
		if (!par1EntityLiving.isEntityAlive())
		{
			
			return false;
		}
		
		if (this.taskOwner instanceof EntityTameable && ((EntityTameable)this.taskOwner).isTamed())
		{
			if (par1EntityLiving instanceof EntityTameable && ((EntityTameable)par1EntityLiving).isTamed())
			{
				
				return false;
			}
			if (par1EntityLiving == ((EntityTameable)this.taskOwner).getOwner())
			{
				
				return false;
			}
		}

		if (par1EntityLiving instanceof EntityPlayer)
		{
			
			if (OreSpawnMain.valentines_day != 0) return true;
			return false;
		}
		
		if (par1EntityLiving instanceof EntityPigZombie)
		{
			
			return false;
		}
		if (par1EntityLiving instanceof EntityEnderman)
		{
			
			return false;
		}
		if (par1EntityLiving instanceof Mothra)
		{
			return true;
		}
		
		if (this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee(par1EntityLiving))
		{
			
			return false;
		}
		if (par1EntityLiving instanceof EntityCreeper)
		{
			
			return true;
		}
		
		if (par1EntityLiving instanceof EntityGhast)
		{
			
			return true;
		}
		if (this.field_75303_a)
		{
			if (--this.field_75302_c <= 0)
			{
				this.field_75301_b = 0;
			}

			if (this.field_75301_b == 0)
			{
				this.field_75301_b = this.func_75295_a(par1EntityLiving) ? 1 : 2;
			}

			if (this.field_75301_b == 2)
			{
				
				return false;
			}
		}

		return true;
	}

	private boolean func_75295_a(EntityLivingBase par1EntityLiving)
	{
		this.field_75302_c = 10 + this.taskOwner.getRNG().nextInt(5);
		PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(par1EntityLiving);
		
		if (var2 == null)
		{
			return false;
		}
		else
		{
			PathPoint var3 = var2.getFinalPathPoint();
			
			if (var3 == null)
			{
				return false;
			}
			else
			{
				int var4 = var3.xCoord - MathHelper.floor_double(par1EntityLiving.posX);
				int var5 = var3.zCoord - MathHelper.floor_double(par1EntityLiving.posZ);
				return (double)(var4 * var4 + var5 * var5) <= 2.25D;
			}
		}
	}
}
