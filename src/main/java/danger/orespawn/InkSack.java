package danger.orespawn;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class InkSack extends EntityThrowable {
	private float my_rotation = 0.0F;
	private int my_index = OreSpawnConstants.InkSackIndex;

	public InkSack(World par1World) {
		super(par1World);
	}

	public InkSack(World par1World, int par2) {
		super(par1World);
	}

	public InkSack(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public InkSack(World par1World, EntityLiving par2EntityLiving, int par3) {
		super(par1World, par2EntityLiving);
	}

	public InkSack(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	public int getInkSackIndex() {
		return this.my_index;
	}

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			float var2 = 1.0F;
			if (par1MovingObjectPosition.entityHit instanceof EntityCreeper) {
				var2 = 4.0F;
			}

			if (par1MovingObjectPosition.entityHit instanceof WaterDragon) {
				return;
			}

			if (par1MovingObjectPosition.entityHit instanceof AttackSquid) {
				return;
			}

			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), var2);
			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase && this.worldObj.rand.nextInt(2) == 0) {
				((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.blindness.id, 100 + 50 * this.worldObj.rand.nextInt(8), 0));
			}
		}

		for (int var3 = 0; var3 < 4; ++var3) {
			this.worldObj.spawnParticle("smoke", this.posX + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), this.posY + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), this.posZ + (double)this.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
		}

		this.playSound("random.splash", 0.5F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5F);
		if (!this.worldObj.isRemote) {
			this.setDead();
		}

	}

	public void onUpdate() {
		super.onUpdate();

		for (this.my_rotation += 30.0F; this.my_rotation > 360.0F; this.my_rotation -= 360.0F) {
		}

		this.rotationPitch = this.prevRotationPitch = this.my_rotation;
	}
}
