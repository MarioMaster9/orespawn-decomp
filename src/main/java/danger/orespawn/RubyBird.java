package danger.orespawn;

import net.minecraft.world.World;

public class RubyBird extends Cockateil {
	public RubyBird(World par1World) {
		super(par1World);
	}

	protected void entityInit() {
		super.entityInit();
		this.birdtype = 5;
		this.setBirdType(this.birdtype);
		this.setFlyUp();
	}

	protected String getLivingSound() {
		return this.worldObj.isDaytime() && !this.worldObj.isRaining() ? "orespawn:rubybird" : null;
	}

	public boolean getCanSpawnHere() {
		return true;
	}
}
