package danger.orespawn;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.world.World;


public class CrystalCow extends RedCow
{
	public CrystalCow(World world)
	{
		super(world);
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + par2);
		int var4;
		
		for (var4 = 0; var4 < var3; var4++)
		{
			this.dropItem(OreSpawnMain.MyCrystalApple, 1);
		}

		this.dropItem(Items.apple, 1);
		
		super.dropFewItems(par1, par2);
	}

	public EntityCow createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	
	
	public CrystalCow spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new CrystalCow(this.worldObj);
	}
}
