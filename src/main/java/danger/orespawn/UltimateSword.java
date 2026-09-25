package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;







public class UltimateSword extends ItemSword
{
	private int swingtimer = 0;
	private boolean leaf = false;

	public UltimateSword(int par1, Item.ToolMaterial par2EnumToolMaterial)
	{
		super(par2EnumToolMaterial);
		
		this.maxStackSize = 1;
		this.setMaxDamage(3000);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (this == OreSpawnMain.MyChainsaw) return;
		
		if (this != OreSpawnMain.MyBattleAxe) {
			par1ItemStack.addEnchantment(Enchantment.sharpness, OreSpawnMain.UltimateSwordMagic);
			par1ItemStack.addEnchantment(Enchantment.smite, OreSpawnMain.UltimateSwordMagic);
			par1ItemStack.addEnchantment(Enchantment.baneOfArthropods, OreSpawnMain.UltimateSwordMagic);
			par1ItemStack.addEnchantment(Enchantment.knockback, 1 + OreSpawnMain.UltimateSwordMagic / 2);
			par1ItemStack.addEnchantment(Enchantment.looting, 1 + OreSpawnMain.UltimateSwordMagic / 2);
			par1ItemStack.addEnchantment(Enchantment.unbreaking, 1 + OreSpawnMain.UltimateSwordMagic / 2);
			par1ItemStack.addEnchantment(Enchantment.fireAspect, 1 + OreSpawnMain.UltimateSwordMagic / 3);
		} else {
			par1ItemStack.addEnchantment(Enchantment.looting, 1 + OreSpawnMain.UltimateSwordMagic / 2);
			par1ItemStack.addEnchantment(Enchantment.unbreaking, 1 + OreSpawnMain.UltimateSwordMagic / 2);
		}
		
		
	}
	
	
	/**
	 * Called when a entity tries to play the 'swing' animation.
	 *
	 * @param entityLiving The entity swinging the item.
	 * @param stack The Item stack
	 * @return True to cancel any further processing by EntityLiving
	 */
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		if (this == OreSpawnMain.MyChainsaw && entityLiving != null && this.swingtimer == 0) {
			entityLiving.playSound("orespawn:chainsawshort", 1.0F, entityLiving.worldObj.rand.nextFloat() * 0.2F + 0.9F);
			this.swingtimer = 50;
		}
		return false;
	}

	
	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		if (this == OreSpawnMain.MyChainsaw) return;
		
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, stack);
		if (lvl <= 0) {
			if (this != OreSpawnMain.MyBattleAxe) {
				stack.addEnchantment(Enchantment.sharpness, OreSpawnMain.UltimateSwordMagic);
				stack.addEnchantment(Enchantment.smite, OreSpawnMain.UltimateSwordMagic);
				stack.addEnchantment(Enchantment.baneOfArthropods, OreSpawnMain.UltimateSwordMagic);
				stack.addEnchantment(Enchantment.knockback, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.looting, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.unbreaking, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.fireAspect, 1 + OreSpawnMain.UltimateSwordMagic / 3);
			} else {
				stack.addEnchantment(Enchantment.looting, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.unbreaking, 1 + OreSpawnMain.UltimateSwordMagic / 2);
			}
		}
	}

	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if (this == OreSpawnMain.MyChainsaw) {
			if (this.swingtimer > 0) --this.swingtimer;

			if (par2World.isRemote && this.swingtimer > 0) {
				float f = 1.0F;
				
				float dx = (float)((double)f * Math.cos(Math.toRadians((double)(par3Entity.rotationYaw + 90.0F + 45.0F))));
				float dz = (float)((double)f * Math.sin(Math.toRadians((double)(par3Entity.rotationYaw + 90.0F + 45.0F))));
				
				if (par2World.rand.nextInt(8) == 0) {
					par2World.spawnParticle("flame", par3Entity.posX + (double)dx, par3Entity.posY, par3Entity.posZ + (double)dz, (double)((par2World.rand.nextFloat() - par2World.rand.nextFloat()) / 20.0F), (double)(par2World.rand.nextFloat() / 10.0F), (double)((par2World.rand.nextFloat() - par2World.rand.nextFloat()) / 20.0F));
				}

				
				
				
				if (par2World.rand.nextInt(2) == 0) {
					par2World.spawnParticle("smoke", par3Entity.posX + (double)dx, par3Entity.posY, par3Entity.posZ + (double)dz, (double)((par2World.rand.nextFloat() - par2World.rand.nextFloat()) / 20.0F), (double)(par2World.rand.nextFloat() / 10.0F), (double)((par2World.rand.nextFloat() - par2World.rand.nextFloat()) / 20.0F));
				}

				
				
				
				if (par2World.rand.nextInt(10) == 0) {
					par2World.spawnParticle("fireworksSpark", par3Entity.posX + (double)dx, par3Entity.posY, par3Entity.posZ + (double)dz, (double)((par2World.rand.nextFloat() - par2World.rand.nextFloat()) / 20.0F), (double)(par2World.rand.nextFloat() / 5.0F), (double)((par2World.rand.nextFloat() - par2World.rand.nextFloat()) / 20.0F));
				}
				
				
				
			}
			
			
			return;
		}
		
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, stack);
		if (lvl <= 0) {
			if (this != OreSpawnMain.MyBattleAxe) {
				stack.addEnchantment(Enchantment.sharpness, OreSpawnMain.UltimateSwordMagic);
				stack.addEnchantment(Enchantment.smite, OreSpawnMain.UltimateSwordMagic);
				stack.addEnchantment(Enchantment.baneOfArthropods, OreSpawnMain.UltimateSwordMagic);
				stack.addEnchantment(Enchantment.knockback, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.looting, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.unbreaking, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.fireAspect, 1 + OreSpawnMain.UltimateSwordMagic / 3);
			} else {
				stack.addEnchantment(Enchantment.looting, 1 + OreSpawnMain.UltimateSwordMagic / 2);
				stack.addEnchantment(Enchantment.unbreaking, 1 + OreSpawnMain.UltimateSwordMagic / 2);
			}
		}
	}

	
	public String getMaterialName()
	{
		return "Uranium/Titanium";
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (entity != null && OreSpawnMain.ultimate_sword_pvp == 0) {
			if (entity instanceof EntityPlayer || entity instanceof Girlfriend || entity instanceof Boyfriend) {
				return true;
			}
			if (entity instanceof EntityTameable) {
				EntityTameable t = (EntityTameable)entity;
				if (t.isTamed()) {
					return true;
				}
			}
		}

		
		if (this == OreSpawnMain.MyChainsaw) {
			if (player != null) {
				this.findSomethingToHit(player);
			}
		}
		return false;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 9000;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}

	private void findSomethingToHit(EntityPlayer player)
	{
		List var5 = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(5.0D, 5.0D, 5.0D));
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			
			if (this.isSuitableTarget(var4, false, player))
			{
				var4.attackEntityFrom(DamageSource.causePlayerDamage(player), (float)OreSpawnMain.chainsaw_stats.damage);
			}
		}
	}

	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2, EntityPlayer player)
	{
		if (par1EntityLiving == null)
		{
			return false;
		}
		if (par1EntityLiving == player)
		{
			return false;
		}
		if (!par1EntityLiving.isEntityAlive())
		{
			return false;
		}
		if (OreSpawnMain.ultimate_sword_pvp == 0) {
			if (par1EntityLiving instanceof EntityPlayer || par1EntityLiving instanceof Girlfriend || par1EntityLiving instanceof Boyfriend) {
				return false;
			}
			if (par1EntityLiving instanceof EntityTameable) {
				EntityTameable t = (EntityTameable)par1EntityLiving;
				if (t.isTamed()) {
					return false;
				}
			}
		}

		if (!this.MyCanSee(par1EntityLiving, player))
		{
			
			return false;
		}
		else
		{
			return true;
		}
	}

	public boolean MyCanSee(EntityLivingBase e, EntityPlayer player)
	{
		float startx;
		float starty;
		float startz;
		double cx, cz;
		float dx;
		float dy;
		float dz;
		int i;
		Block bid;
		int nblks = 10;
		
		cx = player.posX;
		cz = player.posZ;
		startx = (float)(cx);
		starty = (float)(player.posY + (double)1.4F);
		startz = (float)(cz);
		dx = (float)((e.posX - (double)startx) / 10.0D);
		dy = (float)((e.posY + (double)(e.height / 2.0F) - (double)starty) / 10.0D);
		dz = (float)((e.posZ - (double)startz) / 10.0D);
		
		if(Math.abs(dx) > 1.0){
			dy = dy/Math.abs(dx);
			dz = dz/Math.abs(dx);
			nblks *= Math.abs(dx);
			if(dx > 1)dx = 1;
			if(dx < -1)dx = -1;
		}
		if(Math.abs(dy) > 1.0){
			dx = dx/Math.abs(dy);
			dz = dz/Math.abs(dy);
			nblks *= Math.abs(dy);
			if(dy > 1)dy = 1;
			if(dy < -1)dy = -1;
		}
		if(Math.abs(dz) > 1.0){
			dy = dy/Math.abs(dz);
			dx = dx/Math.abs(dz);
			nblks *= Math.abs(dz);
			if(dz > 1)dz = 1;
			if(dz < -1)dz = -1;
		}

		for(i=0;i<nblks;i++){
			startx += dx;
			starty += dy;
			startz += dz;
			bid = player.worldObj.getBlock((int)startx, (int)starty, (int)startz);
			if (bid != Blocks.air) return false;
		}
		

		return true;
	}

	/**
	 * Returns if the item (tool) can harvest results from the block type.
	 */
	public boolean canHarvestBlock(Block par1Block)
	{
		return this.canCrush(par1Block);
	}

	private boolean canCrush(Block blockID)
	{
		if (this == OreSpawnMain.MyChainsaw) {
			if (blockID == Blocks.web) return true;
			if (blockID == Blocks.log) return true;
			if (blockID == Blocks.leaves) return true;
			if (blockID == Blocks.planks) return true;
			if (blockID == Blocks.sapling) return true;
			if (blockID == Blocks.tallgrass) return true;
			if (blockID == Blocks.cactus) return true;
			if (blockID == OreSpawnMain.CrystalPlanksBlock) return true;
			if (blockID == OreSpawnMain.MyAppleLeaves) return true;
			if (blockID == OreSpawnMain.MySkyTreeLog) return true;
			if (blockID == OreSpawnMain.MyDT) return true;
			if (blockID == OreSpawnMain.MyExperienceLeaves) return true;
			if (blockID == OreSpawnMain.MyScaryLeaves) return true;
			if (blockID == OreSpawnMain.MyCherryLeaves) return true;
			if (blockID == OreSpawnMain.MyPeachLeaves) return true;
			if (blockID == OreSpawnMain.MyCrystalLeaves) return true;
			if (blockID == OreSpawnMain.MyCrystalLeaves2) return true;
			if (blockID == OreSpawnMain.MyCrystalLeaves3) return true;
			if (blockID == OreSpawnMain.MyCrystalTreeLog) return true;
			
			return false;
		}
		return blockID == Blocks.web;
	}

	private boolean isLeaves(Block blockID)
	{
		if (blockID == Blocks.web) return true;
		if (blockID == Blocks.leaves) return true;
		if (blockID == Blocks.sapling) return true;
		if (blockID == Blocks.tallgrass) return true;
		if (blockID == OreSpawnMain.MyAppleLeaves) return true;
		if (blockID == OreSpawnMain.MyExperienceLeaves) return true;
		if (blockID == OreSpawnMain.MyScaryLeaves) return true;
		if (blockID == OreSpawnMain.MyCherryLeaves) return true;
		if (blockID == OreSpawnMain.MyPeachLeaves) return true;
		if (blockID == OreSpawnMain.MyCrystalLeaves) return true;
		if (blockID == OreSpawnMain.MyCrystalLeaves2) return true;
		if (blockID == OreSpawnMain.MyCrystalLeaves3) return true;
		
		return false;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
	{
		if (this == OreSpawnMain.MyChainsaw && !par2World.isRemote)
		{
			int i, j, k;
			Block bid;
			for (i = -5; i <= 5; i++) {
				for (j = -5; j <= 10; j++) {
					for (k = -5; k <= 5; k++) {
						bid = par2World.getBlock(par4 + i, par5 + j, par6 + k);
						if (this.leaf == true) {
							if (this.isLeaves(bid)) {
								this.dropItemRand(par2World, Item.getItemFromBlock(bid), 1, par4 + i, par5 + j, par6 + k);
								par2World.setBlock(par4 + i, par5 + j, par6 + k, Blocks.air);
							}
							
						} else if (this.canCrush(bid)) {
							this.dropItemRand(par2World, Item.getItemFromBlock(bid), 1, par4 + i, par5 + j, par6 + k);
							par2World.setBlock(par4 + i, par5 + j, par6 + k, Blocks.air);
						}
					}
				}
			}
		}

		return super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase);
	}

	private ItemStack dropItemRand(World world, Item index, int par1, int x, int y, int z)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(world, (double)(x + OreSpawnMain.OreSpawnRand.nextInt(5) - OreSpawnMain.OreSpawnRand.nextInt(5)), (double)y + 1.0D + (double)world.rand.nextInt(5), (double)(z + OreSpawnMain.OreSpawnRand.nextInt(5) - OreSpawnMain.OreSpawnRand.nextInt(5)), is);
		
		
		
		
		if (var3 != null) world.spawnEntityInWorld(var3);
		return is;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		if (par2Block != null) {
			if (this == OreSpawnMain.MyChainsaw) {
				this.leaf = this.isLeaves(par2Block);
				if (par2Block.getMaterial() == Material.wood || par2Block.getMaterial() == Material.plants || par2Block.getMaterial() == Material.vine) return (float)OreSpawnMain.chainsaw_stats.efficiency;
				if (this.canCrush(par2Block)) return (float)OreSpawnMain.chainsaw_stats.efficiency;
			}
		}
		return 2.0F;
	}
}
