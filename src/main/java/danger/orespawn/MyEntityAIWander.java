package danger.orespawn;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.Vec3;



public class MyEntityAIWander extends EntityAIBase
{
	private EntityCreature entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private float speed;

	public MyEntityAIWander(EntityCreature par1EntityCreature, float par2)
	{
		this.entity = par1EntityCreature;
		this.speed = par2;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{
		if (this.entity.getRNG().nextInt(90) != 0)
		{
			return false;
		}
		Vec3 var1;
		if (this.entity instanceof EntityTameable && ((EntityTameable)this.entity).isSitting())
		{
			return false;
		}
		else
		{
			var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
			
			if (var1 == null)
			{
				return false;
			}
			else
			{
				this.xPosition = var1.xCoord;
				this.yPosition = var1.yCoord;
				this.zPosition = var1.zCoord;
				return true;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting()
	{
		if (this.entity != null) {
			if (this.entity instanceof EntityTameable) {
				EntityTameable gf = (EntityTameable)this.entity;
				EntityLivingBase var1 = gf.getOwner();
				if (var1 != null) {
					if ((int)gf.posZ == (int)var1.posZ) {
						if ((int)gf.posX == (int)var1.posX) {
							if ((int)gf.posY < (int)var1.posY + 2) {
								if ((int)gf.posY > (int)var1.posY - 2) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return !this.entity.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, (double)this.speed);
	}
}
