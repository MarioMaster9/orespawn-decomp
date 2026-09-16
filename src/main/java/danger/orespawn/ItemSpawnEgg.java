package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;








public class ItemSpawnEgg extends Item
{
	public int my_id = 0;

	
	public ItemSpawnEgg(int i, int j)
	{
		this.my_id = j;
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	
	
	
	
	
	
	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par3World.isRemote)
		{
			
			return true;
		}
		
		Entity ent = spawn_something(this.my_id, par3World, (double)par4 + 0.5D, (double)par5 + 1.01, (double)par6 + 0.5D);
		
		if (ent != null)
		{
			
			if (ent instanceof EntityLiving && par1ItemStack.hasDisplayName())
			{
				((EntityLiving)ent).setCustomNameTag(par1ItemStack.getDisplayName());
			}
		}

		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		return true;
	}

	
	
	
	
	
	public static Entity spawn_something(int id, World world, double d0, double d1, double d2)
	{
		int entityID = 0;
		int skelly_type = 0;
		String name = null;
		
		switch (id) {
			case OreSpawnConstants.WitherSkeletonEggIndex:
				skelly_type = 1;
				entityID = 51; break;
			case OreSpawnConstants.EnderDragonEggIndex:
				entityID = 63; break;
			case OreSpawnConstants.SnowGolemEggIndex:
				entityID = 97; break;
			case OreSpawnConstants.IronGolemEggIndex:
				entityID = 99; break;
			case OreSpawnConstants.WitherBossEggIndex:
				entityID = 64; break;
			case OreSpawnConstants.GirlfriendEggIndex:
				name = "Girlfriend"; break;
			case OreSpawnConstants.RedCowEggIndex:
				name = "Apple Cow"; break;
			case OreSpawnConstants.CrystalCowEggIndex:
				name = "Crystal Apple Cow"; break;
			case OreSpawnConstants.GoldCowEggIndex:
				name = "Golden Apple Cow"; break;
			case OreSpawnConstants.EnchantedCowEggIndex:
				name = "Enchanted Golden Apple Cow"; break;
			case OreSpawnConstants.AloEggIndex:
				name = "Alosaurus"; break;
			case OreSpawnConstants.CryoEggIndex:
				name = "Cryolophosaurus"; break;
			case OreSpawnConstants.CamaEggIndex:
				name = "Camarasaurus"; break;
			case OreSpawnConstants.VeloEggIndex:
				name = "Velocity Raptor"; break;
			case OreSpawnConstants.HydroEggIndex:
				name = "Hydrolisc"; break;
			case OreSpawnConstants.BasilEggIndex:
				name = "Basilisk"; break;
			case OreSpawnConstants.MOTHRAEggIndex:
				name = "Mothra"; break;
			case OreSpawnConstants.DragonflyEggIndex:
				name = "Dragonfly"; break;
			case OreSpawnConstants.EmperorScorpionEggIndex:
				name = "Emperor Scorpion"; break;
			case OreSpawnConstants.ScorpionEggIndex:
				name = "Scorpion"; break;
			case OreSpawnConstants.CaveFisherEggIndex:
				name = "CaveFisher"; break;
			case OreSpawnConstants.SpyroEggIndex:
				name = "Baby Dragon"; break;
			case OreSpawnConstants.BaryonyxEggIndex:
				name = "Baryonyx"; break;
			case OreSpawnConstants.GammaMetroidEggIndex:
				name = "WTF?"; break;
			case OreSpawnConstants.CockateilEggIndex:
				name = "Bird"; break;
			case OreSpawnConstants.KyuubiEggIndex:
				name = "Kyuubi"; break;
			case OreSpawnConstants.AlienEggIndex:
				name = "Alien"; break;
			case OreSpawnConstants.AttackSquidEggIndex:
				name = "Attack Squid"; break;
			case OreSpawnConstants.WaterDragonEggIndex:
				name = "Water Dragon"; break;
			case OreSpawnConstants.KrakenEggIndex:
				name = "The Kraken"; break;
			case OreSpawnConstants.LizardEggIndex:
				name = "Lizard"; break;
			case OreSpawnConstants.CephadromeEggIndex:
				name = "Cephadrome"; break;
			case OreSpawnConstants.DragonEggIndex:
				name = "Dragon"; break;
			case OreSpawnConstants.BeeEggIndex:
				name = "Bee"; break;
			case OreSpawnConstants.TrooperEggIndex:
				name = "Jumpy Bug"; break;
			case OreSpawnConstants.SpitEggIndex:
				name = "Spit Bug"; break;
			case OreSpawnConstants.StinkEggIndex:
				name = "Stink Bug"; break;
			case OreSpawnConstants.OstrichEggIndex:
				name = "Ostrich"; break;
			case OreSpawnConstants.GazelleEggIndex:
				name = "Gazelle"; break;
			case OreSpawnConstants.ChipmunkEggIndex:
				name = "Chipmunk"; break;
			case OreSpawnConstants.CreepingHorrorEggIndex:
				name = "Creeping Horror"; break;
			case OreSpawnConstants.TerribleTerrorEggIndex:
				name = "Terrible Terror"; break;
			case OreSpawnConstants.CliffRacerEggIndex:
				name = "Cliff Racer"; break;
			case OreSpawnConstants.TriffidEggIndex:
				name = "Triffid"; break;
			case OreSpawnConstants.PitchBlackEggIndex:
				name = "Nightmare"; break;
			case OreSpawnConstants.LurkingTerrorEggIndex:
				name = "Lurking Terror"; break;
			case OreSpawnConstants.SmallWormEggIndex:
				name = "Small Worm"; break;
			case OreSpawnConstants.MediumWormEggIndex:
				name = "Medium Worm"; break;
			case OreSpawnConstants.LargeWormEggIndex:
				name = "Large Worm"; break;
			case OreSpawnConstants.CassowaryEggIndex:
				name = "Cassowary"; break;
			case OreSpawnConstants.CloudSharkEggIndex:
				name = "Cloud Shark"; break;
			case OreSpawnConstants.GoldFishEggIndex:
				name = "Gold Fish"; break;
			case OreSpawnConstants.LeafMonsterEggIndex:
				name = "Leaf Monster"; break;
			case OreSpawnConstants.TshirtEggIndex:
				name = "T-Shirt"; break;
			case OreSpawnConstants.GodzillaEggIndex:
				name = "Mobzilla"; break;
			case OreSpawnConstants.EnderKnightEggIndex:
				name = "Ender Knight"; break;
			case OreSpawnConstants.EnderReaperEggIndex:
				name = "Ender Reaper"; break;
			case OreSpawnConstants.BeaverEggIndex:
				name = "Beaver"; break;
			case OreSpawnConstants.DungeonBeastEggIndex:
				name = "Dungeon Beast"; break;
			case OreSpawnConstants.VortexEggIndex:
				name = "Vortex"; break;
			case OreSpawnConstants.RotatorEggIndex:
				name = "Rotator"; break;
			case OreSpawnConstants.PeacockEggIndex:
				name = "Peacock"; break;
			case OreSpawnConstants.FairyEggIndex:
				name = "Fairy"; break;
			case OreSpawnConstants.RatEggIndex:
				name = "Rat"; break;
			case OreSpawnConstants.FlounderEggIndex:
				name = "Flounder"; break;
			case OreSpawnConstants.WhaleEggIndex:
				name = "Whale"; break;
			case OreSpawnConstants.IrukandjiEggIndex:
				name = "Irukandji"; break;
			case OreSpawnConstants.SkateEggIndex:
				name = "Skate"; break;
			case OreSpawnConstants.UrchinEggIndex:
				name = "Crystal Urchin"; break;
			case OreSpawnConstants.Robot1EggIndex:
				name = "Bomb-Omb"; break;
			case OreSpawnConstants.Robot2EggIndex:
				name = "Robo-Pounder"; break;
			case OreSpawnConstants.Robot3EggIndex:
				name = "Robo-Gunner"; break;
			case OreSpawnConstants.Robot4EggIndex:
				name = "Robo-Warrior"; break;
			case OreSpawnConstants.GhostEggIndex:
				name = "Ghost"; break;
			case OreSpawnConstants.GhostSkellyEggIndex:
				name = "Ghost Pumpkin Skelly"; break;
			case OreSpawnConstants.BrownAntEggIndex:
				name = "Ant"; break;
			case OreSpawnConstants.RedAntEggIndex:
				name = "Red Ant"; break;
			case OreSpawnConstants.RainbowAntEggIndex:
				name = "Rainbow Ant"; break;
			case OreSpawnConstants.UnstableAntEggIndex:
				name = "Unstable Ant"; break;
			case OreSpawnConstants.TermiteEggIndex:
				name = "Termite"; break;
			case OreSpawnConstants.ButterflyEggIndex:
				name = "Butterfly"; break;
			case OreSpawnConstants.MothEggIndex:
				name = "Moth"; break;
			case OreSpawnConstants.MosquitoEggIndex:
				name = "Mosquito"; break;
			case OreSpawnConstants.FireflyEggIndex:
				name = "Firefly"; break;
			case OreSpawnConstants.TRexEggIndex:
				name = "T. Rex"; break;
			case OreSpawnConstants.HerculesEggIndex:
				name = "Hercules Beetle"; break;
			case OreSpawnConstants.MantisEggIndex:
				name = "Mantis"; break;
			case OreSpawnConstants.StinkyEggIndex:
				name = "Stinky"; break;
			case OreSpawnConstants.Robot5EggIndex:
				name = "Robo-Sniper"; break;
			case OreSpawnConstants.CoinEggIndex:
				name = "Coin"; break;
			case OreSpawnConstants.BoyfriendEggIndex:
				name = "Boyfriend"; break;
			case OreSpawnConstants.TheKingEggIndex:
				name = "The King"; break;
			case OreSpawnConstants.TheQueenEggIndex:
				name = "The Queen"; break;
			case OreSpawnConstants.ThePrinceEggIndex:
				name = "The Prince"; break;
			case OreSpawnConstants.EasterBunnyEggIndex:
				name = "Easter Bunny"; break;
			case OreSpawnConstants.MolenoidEggIndex:
				name = "Molenoid"; break;
			case OreSpawnConstants.SeaMonsterEggIndex:
				name = "Sea Monster"; break;
			case OreSpawnConstants.SeaViperEggIndex:
				name = "Sea Viper"; break;
			case OreSpawnConstants.CaterKillerEggIndex:
				name = "CaterKiller"; break;
			case OreSpawnConstants.LeonEggIndex:
				name = "Leonopteryx"; break;
			case OreSpawnConstants.HammerheadEggIndex:
				name = "Hammerhead"; break;
			case OreSpawnConstants.RubberDuckyEggIndex:
				name = "Rubber Ducky"; break;
			case OreSpawnConstants.CriminalEggIndex:
				name = "Criminal"; break;
			case OreSpawnConstants.BrutalflyEggIndex:
				name = "Brutalfly"; break;
			case OreSpawnConstants.NastysaurusEggIndex:
				name = "Nastysaurus"; break;
			case OreSpawnConstants.PointysaurusEggIndex:
				name = "Pointysaurus"; break;
			case OreSpawnConstants.CricketEggIndex:
				name = "Cricket"; break;
			case OreSpawnConstants.ThePrincessEggIndex:
				name = "The Princess"; break;
			case OreSpawnConstants.FrogEggIndex:
				name = "Frog"; break;
			case OreSpawnConstants.JefferyEggIndex:
				name = "Jeffery"; break;
			case OreSpawnConstants.AntRobotEggIndex:
				name = "Robot Red Ant"; break;
			case OreSpawnConstants.SpiderRobotEggIndex:
				name = "Robot Spider"; break;
			case OreSpawnConstants.SpiderDriverEggIndex:
				name = "Spider Driver"; break;
			case OreSpawnConstants.CrabEggIndex:
				name = "Crab"; break;
			default:
				break;
		}
		
		
		Entity ent = null;
		
		if (entityID != 0 || name != null)
		{
			
			
			ent = spawnCreature(world, entityID, name, d0, d1, d2);
			
			
			
			
			if (ent != null)
			{
				if (entityID == 51 && skelly_type != 0)
				{
					EntitySkeleton sk = (EntitySkeleton)ent;
					sk.setSkeletonType(skelly_type);
				}
			}
		}

		
		return ent;
	}

	
	
	
	
	
	
	
	public static Entity spawnCreature(World par0World, int par1, String name, double par2, double par4, double par6)
	{
		Entity var8 = null;
		
		
		
		
		
		if (name == null) {
			var8 = EntityList.createEntityByID(par1, par0World);
		} else {
			var8 = EntityList.createEntityByName(name, par0World);
		}
		
		if (var8 != null)
		{
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			par0World.spawnEntityInWorld(var8);
			
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}

	
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
