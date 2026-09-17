package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;










public class CritterCage extends Item {
	
	
	public int cage_id = 0;

	
	public CritterCage(int i, int j) {
		this.cage_id = j;
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	
	
	
	
	
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		CritterCage cc = (CritterCage)OreSpawnMain.CageEmpty;
		
		
		if (this.cage_id == cc.cage_id)
		{
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				--par1ItemStack.stackSize;
			}

			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			
			if (!par2World.isRemote)
			{
				
				par2World.spawnEntityInWorld(new EntityCage(par2World, par3EntityPlayer, this.cage_id));
			}
		}
		

		return par1ItemStack;
	}

	
	
	
	
	
	
	
	
	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		CritterCage cc = (CritterCage)OreSpawnMain.CageEmpty;
		
		
		
		if (this.cage_id == cc.cage_id)
		{
			
			return false;
		}
		
		
		for (int var3 = 0; var3 < 6; ++var3)
		{
			par2EntityPlayer.worldObj.spawnParticle("smoke", (double)((float)par4 + 0.5F), (double)((float)par5 + 1.25F), (double)((float)par6 + 0.5F), 0.0D, 0.0D, 0.0D);
			par2EntityPlayer.worldObj.spawnParticle("explode", (double)((float)par4 + 0.5F), (double)((float)par5 + 1.25F), (double)((float)par6 + 0.5F), 0.0D, 0.0D, 0.0D);
			par2EntityPlayer.worldObj.spawnParticle("reddust", (double)((float)par4 + 0.5F), (double)((float)par5 + 1.25F), (double)((float)par6 + 0.5F), 0.0D, 0.0D, 0.0D);
		}
		par2EntityPlayer.worldObj.playSoundAtEntity(par2EntityPlayer, "random.explode", 1.0F, 1.5F);
		
		if (par3World.isRemote)
		{
			
			return true;
		}


			
			
		int entityID = 0;
		int skelly_type = 0;
		String name = null;
		
		switch (this.cage_id) {
			case OreSpawnConstants.SpiderCageIndex:
				entityID = 52; break;
			case OreSpawnConstants.BatCageIndex:
				entityID = 65; break;
			case OreSpawnConstants.CowCageIndex:
				entityID = 92; break;
			case OreSpawnConstants.PigCageIndex:
				entityID = 90; break;
			case OreSpawnConstants.SquidCageIndex:
				entityID = 94; break;
			case OreSpawnConstants.ChickenCageIndex:
				entityID = 93; break;
			case OreSpawnConstants.CreeperCageIndex:
				entityID = 50; break;
			case OreSpawnConstants.WitherSkeletonCageIndex:
				skelly_type = 1;
			case OreSpawnConstants.SkeletonCageIndex:
				entityID = 51; break;
			case OreSpawnConstants.ZombieCageIndex:
				entityID = 54; break;
			case OreSpawnConstants.SlimeCageIndex:
				entityID = 55; break;
			case OreSpawnConstants.GhastCageIndex:
				entityID = 56; break;
			case OreSpawnConstants.ZombiePigmanCageIndex:
				entityID = 57; break;
			case OreSpawnConstants.EndermanCageIndex:
				entityID = 58; break;
			case OreSpawnConstants.CaveSpiderCageIndex:
				entityID = 59; break;
			case OreSpawnConstants.SilverfishCageIndex:
				entityID = 60; break;
			case OreSpawnConstants.MagmaCubeCageIndex:
				entityID = 62; break;
			case OreSpawnConstants.WitchCageIndex:
				entityID = 66; break;
			case OreSpawnConstants.SheepCageIndex:
				entityID = 91; break;
			case OreSpawnConstants.WolfCageIndex:
				entityID = 95; break;
			case OreSpawnConstants.MooshroomCageIndex:
				entityID = 96; break;
			case OreSpawnConstants.OcelotCageIndex:
				entityID = 98; break;
			case OreSpawnConstants.BlazeCageIndex:
				entityID = 61; break;
			case OreSpawnConstants.EnderDragonCageIndex:
				entityID = 63; break;
			case OreSpawnConstants.SnowGolemCageIndex:
				entityID = 97; break;
			case OreSpawnConstants.IronGolemCageIndex:
				entityID = 99; break;
			case OreSpawnConstants.WitherBossCageIndex:
				entityID = 64; break;
			case OreSpawnConstants.HorseCageIndex:
				entityID = 100; break;
			case OreSpawnConstants.VillagerCageIndex:
				entityID = 120; break;
			case OreSpawnConstants.GirlfriendCageIndex:
				name = "Girlfriend"; break;
			case OreSpawnConstants.BoyfriendCageIndex:
				name = "Boyfriend"; break;
			case OreSpawnConstants.RedCowCageIndex:
				name = "Apple Cow"; break;
			case OreSpawnConstants.GoldCowCageIndex:
				name = "Golden Apple Cow"; break;
			case OreSpawnConstants.EnchantedCowCageIndex:
				name = "Enchanted Golden Apple Cow"; break;
			case OreSpawnConstants.MOTHRACageIndex:
				name = "Mothra"; break;
			case OreSpawnConstants.AloCageIndex:
				name = "Alosaurus"; break;
			case OreSpawnConstants.CryoCageIndex:
				name = "Cryolophosaurus"; break;
			case OreSpawnConstants.CamaCageIndex:
				name = "Camarasaurus"; break;
			case OreSpawnConstants.VeloCageIndex:
				name = "Velocity Raptor"; break;
			case OreSpawnConstants.HydroCageIndex:
				name = "Hydrolisc"; break;
			case OreSpawnConstants.BasilCageIndex:
				name = "Basilisk"; break;
			case OreSpawnConstants.DragonflyCageIndex:
				name = "Dragonfly"; break;
			case OreSpawnConstants.EmperorScorpionCageIndex:
				name = "Emperor Scorpion"; break;
			case OreSpawnConstants.ScorpionCageIndex:
				name = "Scorpion"; break;
			case OreSpawnConstants.CaveFisherCageIndex:
				name = "CaveFisher"; break;
			case OreSpawnConstants.SpyroCageIndex:
				name = "Baby Dragon"; break;
			case OreSpawnConstants.BaryonyxCageIndex:
				name = "Baryonyx"; break;
			case OreSpawnConstants.GammaMetroidCageIndex:
				name = "WTF?"; break;
			case OreSpawnConstants.CockateilCageIndex:
				name = "Bird"; break;
			case OreSpawnConstants.KyuubiCageIndex:
				name = "Kyuubi"; break;
			case OreSpawnConstants.AlienCageIndex:
				name = "Alien"; break;
			case OreSpawnConstants.AttackSquidCageIndex:
				name = "Attack Squid"; break;
			case OreSpawnConstants.WaterDragonCageIndex:
				name = "Water Dragon"; break;
			case OreSpawnConstants.KrakenCageIndex:
				name = "The Kraken"; break;
			case OreSpawnConstants.LizardCageIndex:
				name = "Lizard"; break;
			case OreSpawnConstants.CephadromeCageIndex:
				name = "Cephadrome"; break;
			case OreSpawnConstants.DragonCageIndex:
				name = "Dragon"; break;
			case OreSpawnConstants.BeeCageIndex:
				name = "Bee"; break;
			case OreSpawnConstants.FireflyCageIndex:
				name = "Firefly"; break;
			case OreSpawnConstants.ChipmunkCageIndex:
				name = "Chipmunk"; break;
			case OreSpawnConstants.GazelleCageIndex:
				name = "Gazelle"; break;
			case OreSpawnConstants.OstrichCageIndex:
				name = "Ostrich"; break;
			case OreSpawnConstants.TrooperCageIndex:
				name = "Jumpy Bug"; break;
			case OreSpawnConstants.SpitCageIndex:
				name = "Spit Bug"; break;
			case OreSpawnConstants.StinkCageIndex:
				name = "Stink Bug"; break;
			case OreSpawnConstants.CreepingHorrorCageIndex:
				name = "Creeping Horror"; break;
			case OreSpawnConstants.TerribleTerrorCageIndex:
				name = "Terrible Terror"; break;
			case OreSpawnConstants.CliffRacerCageIndex:
				name = "Cliff Racer"; break;
			case OreSpawnConstants.TriffidCageIndex:
				name = "Triffid"; break;
			case OreSpawnConstants.PitchBlackCageIndex:
				name = "Nightmare"; break;
			case OreSpawnConstants.LurkingTerrorCageIndex:
				name = "Lurking Terror"; break;
			case OreSpawnConstants.WormSmallCageIndex:
				name = "Small Worm"; break;
			case OreSpawnConstants.WormLargeCageIndex:
				name = "Large Worm"; break;
			case OreSpawnConstants.WormMediumCageIndex:
				name = "Medium Worm"; break;
			case OreSpawnConstants.CassowaryCageIndex:
				name = "Cassowary"; break;
			case OreSpawnConstants.CloudSharkCageIndex:
				name = "Cloud Shark"; break;
			case OreSpawnConstants.GoldFishCageIndex:
				name = "Gold Fish"; break;
			case OreSpawnConstants.LeafMonsterCageIndex:
				name = "Leaf Monster"; break;
			case OreSpawnConstants.EnderKnightCageIndex:
				name = "Ender Knight"; break;
			case OreSpawnConstants.EnderReaperCageIndex:
				name = "Ender Reaper"; break;
			case OreSpawnConstants.BeaverCageIndex:
				name = "Beaver"; break;
			case OreSpawnConstants.UrchinCageIndex:
				name = "Crystal Urchin"; break;
			case OreSpawnConstants.FlounderCageIndex:
				name = "Flounder"; break;
			case OreSpawnConstants.SkateCageIndex:
				name = "Skate"; break;
			case OreSpawnConstants.RotatorCageIndex:
				name = "Rotator"; break;
			case OreSpawnConstants.PeacockCageIndex:
				name = "Peacock"; break;
			case OreSpawnConstants.FairyCageIndex:
				name = "Fairy"; break;
			case OreSpawnConstants.DungeonBeastCageIndex:
				name = "Dungeon Beast"; break;
			case OreSpawnConstants.VortexCageIndex:
				name = "Vortex"; break;
			case OreSpawnConstants.RatCageIndex:
				name = "Rat"; break;
			case OreSpawnConstants.WhaleCageIndex:
				name = "Whale"; break;
			case OreSpawnConstants.IrukandjiCageIndex:
				name = "Irukandji"; break;
			case OreSpawnConstants.TRexCageIndex:
				name = "T. Rex"; break;
			case OreSpawnConstants.HerculesCageIndex:
				name = "Hercules Beetle"; break;
			case OreSpawnConstants.MantisCageIndex:
				name = "Mantis"; break;
			case OreSpawnConstants.StinkyCageIndex:
				name = "Stinky"; break;
			case OreSpawnConstants.EasterBunnyCageIndex:
				name = "Easter Bunny"; break;
			case OreSpawnConstants.CaterKillerCageIndex:
				name = "CaterKiller"; break;
			case OreSpawnConstants.MolenoidCageIndex:
				name = "Molenoid"; break;
			case OreSpawnConstants.SeaMonsterCageIndex:
				name = "Sea Monster"; break;
			case OreSpawnConstants.SeaViperCageIndex:
				name = "Sea Viper"; break;
			case OreSpawnConstants.LeonCageIndex:
				name = "Leonopteryx"; break;
			case OreSpawnConstants.HammerheadCageIndex:
				name = "Hammerhead"; break;
			case OreSpawnConstants.RubberDuckyCageIndex:
				name = "Rubber Ducky"; break;
			case OreSpawnConstants.CrystalCowCageIndex:
				name = "Crystal Apple Cow"; break;
			case OreSpawnConstants.CriminalCageIndex:
				name = "Criminal"; break;
			case OreSpawnConstants.BrutalflyCageIndex:
				name = "Brutalfly"; break;
			case OreSpawnConstants.NastysaurusCageIndex:
				name = "Nastysaurus"; break;
			case OreSpawnConstants.PointysaurusCageIndex:
				name = "Pointysaurus"; break;
			case OreSpawnConstants.CricketCageIndex:
				name = "Cricket"; break;
			case OreSpawnConstants.FrogCageIndex:
				name = "Frog"; break;
			case OreSpawnConstants.SpiderDriverCageIndex:
				name = "Spider Driver"; break;
			case OreSpawnConstants.CrabCageIndex:
				name = "Crab"; break;
			default:
				break;
		}

		
		
		if (entityID != 0 || name != null) {
			
			
			
			
			Entity ent = null;
			ent = spawnCreature(par3World, entityID, name, (double)par4 + 0.5D, (double)par5 + 1.1, (double)par6 + 0.5D);
			
			
			
			
			if (ent != null) {
				ent.dropItem(OreSpawnMain.CageEmpty, 1);
				
				
				if (entityID == 51 && skelly_type != 0)
				{
					EntitySkeleton sk = (EntitySkeleton)ent;
					sk.setSkeletonType(skelly_type);
				}

				if (ent instanceof EntityLiving && par1ItemStack.hasDisplayName())
				{
					((EntityLiving)ent).setCustomNameTag(par1ItemStack.getDisplayName());
				}
			}
		

		} else {
			return false;
		}
		
		
		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		return true;
	}

	
	
	
	
	
	public static Entity spawnCreature(World par0World, int par1, String name, double par2, double par4, double par6) {
		Entity var8 = null;
		
		
		if (name == null) {
			var8 = EntityList.createEntityByID(par1, par0World);
		} else {
			var8 = EntityList.createEntityByName(name, par0World);
		}

		if (var8 != null) {
			
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			if ((par1 == 100 || par1 == 120) && var8 instanceof EntityLiving)
			{
				EntityLiving sk = (EntityLiving)var8;
				sk.onSpawnWithEgg((IEntityLivingData)null);
			}
			par0World.spawnEntityInWorld(var8);
			
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}

	
	
	
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
