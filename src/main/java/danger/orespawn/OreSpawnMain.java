package danger.orespawn;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;


















































@Mod(modid = "OreSpawn", name = "OreSpawn", version = "1.7.10.20.3")
public class OreSpawnMain
{
	
	
	@SidedProxy(clientSide = "danger.orespawn.ClientProxyOreSpawn", serverSide = "danger.orespawn.CommonProxyOreSpawn")
	public static CommonProxyOreSpawn proxy;
	@Instance("OreSpawn")
	public static OreSpawnMain instance;
	public static KeyHandler MyKeyhandler = null;
	public static int flyup_keystate = 0;
	
	public static int BaseBlockID = 2700;
	public static int BaseItemID = 9000;
	public static int BaseBiomeID = 120;
	public static int BaseDimensionID = 80;
	
	public static int BiomeUtopiaID = 0;
	public static int BiomeIslandsID = 0;
	public static int BiomeCrystalID = 0;
	public static int BiomeVillageID = 0;
	public static int BiomeChaosID = 0;
	public static int DimensionID = 0;
	public static int DimensionID2 = 0;
	public static int DimensionID3 = 0;
	public static int DimensionID4 = 0;
	public static int DimensionID5 = 0;
	public static int DimensionID6 = 0;
	
	public static int godzilla_has_spawned = 0;
	public static int current_dimension = 0;
	public static int valentines_day = 0;
	public static int easter_day = 0;
	public static int ultimate_sword_pvp = 0;
	public static int big_bertha_pvp = 0;
	public static int bro_mode = 0;
	public static int enableduplicatortree = 1;
	public static int RoyalGlideEnable = 1;
	public static int DragonflyHorseFriendly = 0;
	public static int PlayNicely = 0;
	public static int MinersDreamExpensive = 0;
	public static int DisableOverworldDungeons = 0;
	public static int FullPowerKingEnable = 0;
	
	public static ArmorStats Amethyst_armorstats = null;
	public static ArmorStats Emerald_armorstats = null;
	public static ArmorStats Experience_armorstats = null;
	public static ArmorStats MothScale_armorstats = null;
	public static ArmorStats LavaEel_armorstats = null;
	public static ArmorStats Ultimate_armorstats = null;
	public static ArmorStats Pink_armorstats = null;
	public static ArmorStats TigersEye_armorstats = null;
	public static ArmorStats Peacock_armorstats = null;
	public static ArmorStats Mobzilla_armorstats = null;
	public static ArmorStats Ruby_armorstats = null;
	public static ArmorStats Royal_armorstats = null;
	public static ArmorStats Lapis_armorstats = null;
	public static ArmorStats Queen_armorstats = null;
	
	public static int AllMobsDisable = 0;
	public static int MosquitoEnable = 1;
	public static int RockEnable = 1;
	public static int GhostEnable = 1;
	public static int GhostSkellyEnable = 1;
	public static int SpiderDriverEnable = 1;
	public static int JefferyEnable = 1;
	public static int MothraEnable = 1;
	public static int BrutalflyEnable = 1;
	public static int NastysaurusEnable = 1;
	public static int PointysaurusEnable = 1;
	public static int CricketEnable = 1;
	public static int FrogEnable = 1;
	public static int MothraPeaceful = 0;
	public static int BlackAntEnable = 1;
	public static int RedAntEnable = 1;
	public static int TermiteEnable = 1;
	public static int UnstableAntEnable = 1;
	public static int RainbowAntEnable = 1;
	public static int AlosaurusEnable = 1;
	public static int HammerheadEnable = 1;
	public static int LeonEnable = 1;
	public static int CaterKillerEnable = 1;
	public static int MolenoidEnable = 1;
	public static int TRexEnable = 1;
	public static int CriminalEnable = 1;
	public static int CryolophosaurusEnable = 1;
	public static int RatEnable = 1;
	public static int RatPlayerFriendly = 0;
	public static int RatPetFriendly = 0;
	public static int UrchinEnable = 1;
	public static int CamarasaurusEnable = 1;
	public static int ChipmunkEnable = 1;
	public static int OstrichEnable = 1;
	public static int GazelleEnable = 1;
	public static int VelocityRaptorEnable = 1;
	public static int HydroliscEnable = 1;
	public static int SpyroEnable = 1;
	public static int BaryonyxEnable = 1;
	public static int CockateilEnable = 1;
	public static int CassowaryEnable = 1;
	public static int EasterBunnyEnable = 1;
	public static int PeacockEnable = 1;
	public static int KyuubiEnable = 1;
	public static int CephadromeEnable = 1;
	public static int DragonEnable = 1;
	public static int GammaMetroidEnable = 1;
	public static int BasiliskEnable = 1;
	public static int DragonflyEnable = 1;
	public static int EmperorScorpionEnable = 1;
	public static int TrooperBugEnable = 1;
	public static int SpitBugEnable = 1;
	public static int StinkBugEnable = 1;
	public static int ScorpionEnable = 1;
	public static int CaveFisherEnable = 1;
	public static int AlienEnable = 1;
	public static int WaterDragonEnable = 1;
	public static int SeaMonsterEnable = 1;
	public static int SeaViperEnable = 1;
	public static int AttackSquidEnable = 1;
	public static int GodzillaEnable = 1;
	public static int LessOre = 0;
	public static int LessLag = 0;
	public static int Robot1Enable = 1;
	public static int Robot2Enable = 1;
	public static int Robot3Enable = 1;
	public static int Robot4Enable = 1;
	public static int Robot5Enable = 1;
	public static int RotatorEnable = 1;
	public static int VortexEnable = 1;
	public static int DungeonBeastEnable = 1;
	public static int KrakenEnable = 1;
	public static int LizardEnable = 1;
	public static int RubberDuckyEnable = 1;
	public static int GirlfriendEnable = 1;
	public static int BoyfriendEnable = 0;
	public static int FireflyEnable = 1;
	public static int FairyEnable = 1;
	public static int BeeEnable = 1;
	public static int TheKingEnable = 1;
	public static int TheQueenEnable = 1;
	public static int ThePrinceEnable = 1;
	public static int ThePrincessEnable = 1;
	public static int MantisEnable = 1;
	public static int StinkyEnable = 1;
	public static int HerculesBeetleEnable = 1;
	public static int CowEnable = 1;
	public static int ButterflyEnable = 1;
	public static int MothEnable = 1;
	public static int TshirtEnable = 1;
	public static int CoinEnable = 1;
	public static int CreepingHorrorEnable = 1;
	public static int TerribleTerrorEnable = 1;
	public static int CliffRacerEnable = 1;
	public static int TriffidEnable = 1;
	public static int UltimateSwordMagic = 10;
	public static int UltimateBowDamage = 10;
	public static int PitchBlackEnable = 1;
	public static int NightmareSize = 0;
	public static int LurkingTerrorEnable = 1;
	public static int IslandSpeedFactor = 2;
	public static int IslandSizeFactor = 2;
	public static int GinormousEmeraldTreeEnable = 1;
	public static int GuiOverlayEnable = 1;
	public static int FastGraphicsLeaves = 0;
	public static int WormEnable = 1;
	public static int CloudSharkEnable = 1;
	public static int GoldFishEnable = 1;
	public static int LeafMonsterEnable = 1;
	public static int EnderKnightEnable = 1;
	public static int EnderReaperEnable = 1;
	public static int BeaverEnable = 1;
	public static int IrukandjiEnable = 1;
	public static int SkateEnable = 1;
	public static int WhaleEnable = 1;
	public static int FlounderEnable = 1;
	public static int CrabEnable = 1;
	
	
	
	public OreSpawnWorld OreSpawnGen = new OreSpawnWorld();
	public static Random OreSpawnRand = new Random(151L);
	public static Trees OreSpawnTrees = null;
	public static BasiliskMaze BMaze;
	public static RubyBirdDungeon RubyDungeon;
	public static GenericDungeon MyDungeon;
	public static MyUtils OreSpawnUtils;
	public static ChunkOreGenerator Chunker;
	public static OreGenericEgg MySpiderSpawnBlock;
	public static OreGenericEgg MyBatSpawnBlock;
	public static OreGenericEgg MyCowSpawnBlock;
	public static OreGenericEgg MyPigSpawnBlock;
	public static OreGenericEgg MySquidSpawnBlock;
	public static OreGenericEgg MyChickenSpawnBlock;
	public static OreGenericEgg MyCreeperSpawnBlock;
	public static OreGenericEgg MySkeletonSpawnBlock;
	public static OreGenericEgg MyZombieSpawnBlock;
	public static OreGenericEgg MySlimeSpawnBlock;
	public static OreGenericEgg MyGhastSpawnBlock;
	public static OreGenericEgg MyZombiePigmanSpawnBlock;
	public static OreGenericEgg MyEndermanSpawnBlock;
	public static OreGenericEgg MyCaveSpiderSpawnBlock;
	public static OreGenericEgg MySilverfishSpawnBlock;
	public static OreGenericEgg MyMagmaCubeSpawnBlock;
	public static OreGenericEgg MyWitchSpawnBlock;
	public static OreGenericEgg MySheepSpawnBlock;
	public static OreGenericEgg MyWolfSpawnBlock;
	public static OreGenericEgg MyMooshroomSpawnBlock;
	public static OreGenericEgg MyOcelotSpawnBlock;
	public static OreGenericEgg MyBlazeSpawnBlock;
	public static OreGenericEgg MyWitherSkeletonSpawnBlock;
	public static OreGenericEgg MyEnderDragonSpawnBlock;
	public static OreGenericEgg MySnowGolemSpawnBlock;
	public static OreGenericEgg MyIronGolemSpawnBlock;
	public static OreGenericEgg MyWitherBossSpawnBlock;
	public static OreGenericEgg MyGirlfriendSpawnBlock;
	public static OreGenericEgg MyBoyfriendSpawnBlock;
	public static OreGenericEgg MyRedCowSpawnBlock;
	public static OreGenericEgg MyCrystalCowSpawnBlock;
	public static OreGenericEgg MyVillagerSpawnBlock;
	public static OreGenericEgg MyGoldCowSpawnBlock;
	public static OreGenericEgg MyEnchantedCowSpawnBlock;
	public static OreGenericEgg MyMOTHRASpawnBlock;
	public static OreGenericEgg MyAloSpawnBlock;
	public static OreGenericEgg MyCryoSpawnBlock;
	public static OreGenericEgg MyCamaSpawnBlock;
	public static OreGenericEgg MyVeloSpawnBlock;
	public static OreGenericEgg MyHydroSpawnBlock;
	public static OreGenericEgg MyBasilSpawnBlock;
	public static OreGenericEgg MyDragonflySpawnBlock;
	public static OreGenericEgg MyEmperorScorpionSpawnBlock;
	public static OreGenericEgg MyScorpionSpawnBlock;
	public static OreGenericEgg MyCaveFisherSpawnBlock;
	public static OreGenericEgg MySpyroSpawnBlock;
	public static OreGenericEgg MyBaryonyxSpawnBlock;
	public static OreGenericEgg MyGammaMetroidSpawnBlock;
	public static OreGenericEgg MyCockateilSpawnBlock;
	public static OreGenericEgg MyKyuubiSpawnBlock;
	public static OreGenericEgg MyAlienSpawnBlock;
	public static OreGenericEgg MyAttackSquidSpawnBlock;
	public static OreGenericEgg MyWaterDragonSpawnBlock;
	public static OreGenericEgg MyKrakenSpawnBlock;
	public static OreGenericEgg MyLizardSpawnBlock;
	public static OreGenericEgg MyCephadromeSpawnBlock;
	public static OreGenericEgg MyDragonSpawnBlock;
	public static OreGenericEgg MyBeeSpawnBlock;
	public static OreGenericEgg MyHorseSpawnBlock;
	public static OreGenericEgg MyTrooperBugSpawnBlock;
	public static OreGenericEgg MySpitBugSpawnBlock;
	public static OreGenericEgg MyStinkBugSpawnBlock;
	public static OreGenericEgg MyOstrichSpawnBlock;
	public static OreGenericEgg MyGazelleSpawnBlock;
	public static OreGenericEgg MyChipmunkSpawnBlock;
	public static OreGenericEgg MyCreepingHorrorSpawnBlock;
	public static OreGenericEgg MyTerribleTerrorSpawnBlock;
	public static OreGenericEgg MyCliffRacerSpawnBlock;
	public static OreGenericEgg MyTriffidSpawnBlock;
	public static OreGenericEgg MyPitchBlackSpawnBlock;
	public static OreGenericEgg MyLurkingTerrorSpawnBlock;
	public static OreGenericEgg MyGodzillaPartSpawnBlock;
	public static OreGenericEgg MyGodzillaSpawnBlock;
	public static OreGenericEgg MyTheKingPartSpawnBlock;
	public static OreGenericEgg MyTheQueenPartSpawnBlock;
	public static OreGenericEgg MyTheKingSpawnBlock;
	public static OreGenericEgg MyTheQueenSpawnBlock;
	public static OreGenericEgg MySmallWormSpawnBlock;
	public static OreGenericEgg MyMediumWormSpawnBlock;
	public static OreGenericEgg MyLargeWormSpawnBlock;
	public static OreGenericEgg MyCassowarySpawnBlock;
	public static OreGenericEgg MyCloudSharkSpawnBlock;
	public static OreGenericEgg MyGoldFishSpawnBlock;
	public static OreGenericEgg MyLeafMonsterSpawnBlock;
	public static OreGenericEgg MyTshirtSpawnBlock;
	public static OreGenericEgg MyEnderKnightSpawnBlock;
	public static OreGenericEgg MyEnderReaperSpawnBlock;
	public static OreGenericEgg MyBeaverSpawnBlock;
	public static OreGenericEgg MyUrchinSpawnBlock;
	public static OreGenericEgg MyFlounderSpawnBlock;
	public static OreGenericEgg MySkateSpawnBlock;
	public static OreGenericEgg MyRotatorSpawnBlock;
	public static OreGenericEgg MyPeacockSpawnBlock;
	public static OreGenericEgg MyFairySpawnBlock;
	public static OreGenericEgg MyDungeonBeastSpawnBlock;
	public static OreGenericEgg MyVortexSpawnBlock;
	public static OreGenericEgg MyRatSpawnBlock;
	public static OreGenericEgg MyWhaleSpawnBlock;
	public static OreGenericEgg MyIrukandjiSpawnBlock;
	public static OreGenericEgg MyTRexSpawnBlock;
	public static OreGenericEgg MyHerculesSpawnBlock;
	public static OreGenericEgg MyMantisSpawnBlock;
	public static OreGenericEgg MyStinkySpawnBlock;
	public static OreGenericEgg MyEasterBunnySpawnBlock;
	public static OreGenericEgg MyCaterKillerSpawnBlock;
	public static OreGenericEgg MyMolenoidSpawnBlock;
	public static OreGenericEgg MySeaMonsterSpawnBlock;
	public static OreGenericEgg MySeaViperSpawnBlock;
	public static OreGenericEgg MyLeonSpawnBlock;
	public static OreGenericEgg MyHammerheadSpawnBlock;
	public static OreGenericEgg MyRubberDuckySpawnBlock;
	public static OreGenericEgg MyCriminalSpawnBlock;
	public static OreGenericEgg MyBrutalflySpawnBlock;
	public static OreGenericEgg MyNastysaurusSpawnBlock;
	public static OreGenericEgg MyPointysaurusSpawnBlock;
	public static OreGenericEgg MyCricketSpawnBlock;
	public static OreGenericEgg MyFrogSpawnBlock;
	public static OreGenericEgg MySpiderDriverSpawnBlock;
	public static OreGenericEgg MyCrabSpawnBlock;
	public static Block MyOreUraniumBlock;
	public static Block MyOreTitaniumBlock;
	public static Item MyIngotUranium;
	public static Item MyIngotTitanium;
	public static Block MyBlockUraniumBlock;
	public static Block MyBlockTitaniumBlock;
	public static Block MyBlockMobzillaScaleBlock;
	public static Block MyBlockRubyBlock;
	public static Block MyBlockAmethystBlock;
	public static Block MyLavafoamBlock;
	public static Block MyPizzaBlock;
	public static Item MyPizzaItem;
	public static Block MyDuctTapeBlock;
	public static Item MyDuctTapeItem;
	public static Block MyAntBlock;
	public static Block MyRedAntBlock;
	public static Block TermiteBlock;
	public static Block CrystalTermiteBlock;
	public static Block MyRainbowAntBlock;
	public static Block MyUnstableAntBlock;
	public static Block MyFlowerPinkBlock;
	public static Block MyFlowerBlueBlock;
	public static Block MyFlowerBlackBlock;
	public static Block MyFlowerScaryBlock;
	public static Block CrystalFlowerRedBlock;
	public static Block CrystalFlowerGreenBlock;
	public static Block CrystalFlowerBlueBlock;
	public static Block CrystalFlowerYellowBlock;
	public static Block CrystalPlanksBlock;
	public static Block CrystalWorkbenchBlock;
	public static CrystalFurnace CrystalFurnaceBlock;
	public static Block CrystalFurnaceOnBlock;
	public static Item MyUltimateSword;
	public static Item MyUltimatePickaxe;
	public static Item MyUltimateShovel;
	public static Item MyUltimateHoe;
	public static Item MyUltimateAxe;
	public static Item MyNightmareSword;
	public static Item MyBertha;
	public static Item MyHammy;
	public static Item MyBattleAxe;
	public static Item MyQueenBattleAxe;
	public static Item MyChainsaw;
	public static Item MySquidZooka;
	public static Item MySlice;
	public static Item MyRoyal;
	public static Item MyEmeraldSword;
	public static Item MyEmeraldPickaxe;
	public static Item MyEmeraldShovel;
	public static Item MyEmeraldHoe;
	public static Item MyEmeraldAxe;
	public static Item MyExperienceSword;
	public static Item MyPoisonSword;
	public static Item MyRatSword;
	public static Item MyFairySword;
	public static Item MyMantisClaw;
	public static Item MyBigHammer;
	public static Item MyRubySword;
	public static Item MyRubyPickaxe;
	public static Item MyRubyShovel;
	public static Item MyRubyHoe;
	public static Item MyRubyAxe;
	public static Item MyAmethystSword;
	public static Item MyAmethystPickaxe;
	public static Item MyAmethystShovel;
	public static Item MyAmethystHoe;
	public static Item MyAmethystAxe;
	public static Item MyRoseSword;
	static Item.ToolMaterial toolULTIMATE;
	static Item.ToolMaterial toolNIGHTMARE;
	static Item.ToolMaterial toolBERTHA;
	static Item.ToolMaterial toolCRYSTALWOOD;
	static Item.ToolMaterial toolCRYSTALSTONE;
	static Item.ToolMaterial toolCRYSTALPINK;
	static Item.ToolMaterial toolTIGERSEYE;
	static Item.ToolMaterial toolRUBY;
	static Item.ToolMaterial toolAMETHYST;
	static Item.ToolMaterial toolEMERALD;
	static Item.ToolMaterial toolROYAL;
	static Item.ToolMaterial toolHAMMY;
	static Item.ToolMaterial toolBATTLE;
	static Item.ToolMaterial toolCHAINSAW;
	static Item.ToolMaterial toolQUEENBATTLE;
	
	
	
	
	
	
	
	
	
	static WeaponStats ultimate_stats = null;
	static WeaponStats nightmare_stats = null;
	static WeaponStats bertha_stats = null;
	static WeaponStats crystalwood_stats = null;
	static WeaponStats crystalstone_stats = null;
	static WeaponStats crystalpink_stats = null;
	static WeaponStats tigerseye_stats = null;
	static WeaponStats ruby_stats = null;
	static WeaponStats amethyst_stats = null;
	static WeaponStats emerald_stats = null;
	static WeaponStats royal_stats = null;
	static WeaponStats hammy_stats = null;
	static WeaponStats battleaxe_stats = null;
	static WeaponStats queenbattleaxe_stats = null;
	static WeaponStats chainsaw_stats = null;
	public static Item MyCrystalWoodSword;
	public static Item MyCrystalWoodPickaxe;
	public static Item MyCrystalWoodShovel;
	public static Item MyCrystalWoodHoe;
	public static Item MyCrystalWoodAxe;
	public static Item MyCrystalPinkSword;
	public static Item MyCrystalPinkPickaxe;
	public static Item MyCrystalPinkShovel;
	public static Item MyCrystalPinkHoe;
	public static Item MyCrystalPinkAxe;
	public static Item MyTigersEyeSword;
	public static Item MyTigersEyePickaxe;
	public static Item MyTigersEyeShovel;
	public static Item MyTigersEyeHoe;
	public static Item MyTigersEyeAxe;
	public static Item MyCrystalStoneSword;
	public static Item MyCrystalStonePickaxe;
	public static Item MyCrystalStoneShovel;
	public static Item MyCrystalStoneHoe;
	public static Item MyCrystalStoneAxe;
	public static Item MyCrystalPinkIngot;
	public static Block MyCrystalPinkBlock;
	public static Item MyTigersEyeIngot;
	public static Block MyTigersEyeBlock;
	public static Item MyItemShoes;
	public static Item MyItemShoes_1;
	public static Item MyItemShoes_2;
	public static Item MyItemShoes_3;
	public static Item MyItemGameController;
	public static Item MyUltimateBow;
	public static Item MySkateBow;
	public static Item MyUltimateFishingRod;
	public static ItemStack UltimateFishingRod;
	public static Item MyFireFish;
	public static Item MySunFish;
	public static Item MyLavaEel;
	public static Item MyMothScale;
	public static Item MyQueenScale;
	public static Item MyNightmareScale;
	public static Item MyEmperorScorpionScale;
	public static Item MyBasiliskScale;
	public static Item MyWaterDragonScale;
	public static Item MyJumpyBugScale;
	public static Item MyKrakenTooth;
	public static Item MyGodzillaScale;
	public static Item GreenGoo;
	public static Item SpiderRobotKit;
	public static Item AntRobotKit;
	public static Item ZooKeeper;
	public static Item CreeperLauncher;
	public static Item NetherLost;
	public static Item CrystalSticks;
	public static Item Sifter;
	public static Item MySunspotUrchin;
	public static Item MySparkFish;
	public static Item MyWaterBall;
	public static Item MyLaserBall;
	public static Item MyRayGun;
	public static Item MyThunderStaff;
	public static Item MyWrench;
	public static Item MyIceBall;
	public static Item MySmallRock;
	public static Item MyRock;
	public static Item MyRedRock;
	public static Item MyCrystalRedRock;
	public static Item MyCrystalGreenRock;
	public static Item MyCrystalBlueRock;
	public static Item MyCrystalTNTRock;
	public static Item MyBlueRock;
	public static Item MyGreenRock;
	public static Item MyPurpleRock;
	public static Item MySpikeyRock;
	public static Item MyTNTRock;
	public static Item MyAcid;
	public static Item MyIrukandji;
	public static Item MyIrukandjiArrow;
	public static Item MyGreenFish;
	public static Item MyBlueFish;
	public static Item MyPinkFish;
	public static Item MyRockFish;
	public static Item MyWoodFish;
	public static Item MyGreyFish;
	public static Item BerthaHandle;
	public static Item BerthaGuard;
	public static Item BerthaBlade;
	public static Item MolenoidNose;
	public static Item SeaMonsterScale;
	public static Item WormTooth;
	public static Item TRexTooth;
	public static Item CaterKillerJaw;
	public static Item SeaViperTongue;
	public static Item VortexEye;
	public static Item MyStepUp;
	public static Item MyStepDown;
	public static Item MyStepAccross;
	static ItemArmor.ArmorMaterial armorULTIMATE;
	static ItemArmor.ArmorMaterial armorMOBZILLA;
	static ItemArmor.ArmorMaterial armorLAVAEEL;
	static ItemArmor.ArmorMaterial armorMOTHSCALE;
	static ItemArmor.ArmorMaterial armorEMERALD;
	static ItemArmor.ArmorMaterial armorEXPERIENCE;
	static ItemArmor.ArmorMaterial armorRUBY;
	static ItemArmor.ArmorMaterial armorAMETHYST;
	static ItemArmor.ArmorMaterial armorPINK;
	static ItemArmor.ArmorMaterial armorTIGERSEYE;
	static ItemArmor.ArmorMaterial armorPEACOCK;
	static ItemArmor.ArmorMaterial armorROYAL;
	static ItemArmor.ArmorMaterial armorLAPIS;
	static ItemArmor.ArmorMaterial armorQUEEN;
	public static ItemOreSpawnArmor UltimateHelmet;
	public static ItemOreSpawnArmor UltimateBody;
	public static ItemOreSpawnArmor UltimateLegs;
	public static ItemOreSpawnArmor UltimateBoots;
	public static ItemOreSpawnArmor LavaEelHelmet;
	public static ItemOreSpawnArmor LavaEelBody;
	public static ItemOreSpawnArmor LavaEelLegs;
	public static ItemOreSpawnArmor LavaEelBoots;
	public static ItemOreSpawnArmor MothScaleHelmet;
	public static ItemOreSpawnArmor MothScaleBody;
	public static ItemOreSpawnArmor MothScaleLegs;
	public static ItemOreSpawnArmor MothScaleBoots;
	public static ItemOreSpawnArmor EmeraldHelmet;
	public static ItemOreSpawnArmor EmeraldBody;
	public static ItemOreSpawnArmor EmeraldLegs;
	public static ItemOreSpawnArmor EmeraldBoots;
	public static ItemOreSpawnArmor ExperienceHelmet;
	public static ItemOreSpawnArmor ExperienceBody;
	public static ItemOreSpawnArmor ExperienceLegs;
	public static ItemOreSpawnArmor ExperienceBoots;
	public static ItemOreSpawnArmor RubyHelmet;
	public static ItemOreSpawnArmor RubyBody;
	public static ItemOreSpawnArmor RubyLegs;
	public static ItemOreSpawnArmor RubyBoots;
	public static ItemOreSpawnArmor AmethystHelmet;
	public static ItemOreSpawnArmor AmethystBody;
	public static ItemOreSpawnArmor AmethystLegs;
	public static ItemOreSpawnArmor AmethystBoots;
	public static ItemOreSpawnArmor CrystalPinkHelmet;
	public static ItemOreSpawnArmor CrystalPinkBody;
	public static ItemOreSpawnArmor CrystalPinkLegs;
	public static ItemOreSpawnArmor CrystalPinkBoots;
	public static ItemOreSpawnArmor TigersEyeHelmet;
	public static ItemOreSpawnArmor TigersEyeBody;
	public static ItemOreSpawnArmor TigersEyeLegs;
	public static ItemOreSpawnArmor TigersEyeBoots;
	public static Block TigersEye;
	public static ItemOreSpawnArmor PeacockFeatherBoots;
	public static ItemOreSpawnArmor PeacockFeatherHelmet;
	public static ItemOreSpawnArmor PeacockFeatherBody;
	public static ItemOreSpawnArmor PeacockFeatherLegs;
	public static ItemOreSpawnArmor MobzillaHelmet;
	public static ItemOreSpawnArmor MobzillaBody;
	public static ItemOreSpawnArmor MobzillaLegs;
	public static ItemOreSpawnArmor MobzillaBoots;
	public static ItemOreSpawnArmor RoyalHelmet;
	public static ItemOreSpawnArmor RoyalBody;
	public static ItemOreSpawnArmor RoyalLegs;
	public static ItemOreSpawnArmor RoyalBoots;
	public static ItemOreSpawnArmor LapisHelmet;
	public static ItemOreSpawnArmor LapisBody;
	public static ItemOreSpawnArmor LapisLegs;
	public static ItemOreSpawnArmor LapisBoots;
	public static ItemOreSpawnArmor QueenHelmet;
	public static ItemOreSpawnArmor QueenBody;
	public static ItemOreSpawnArmor QueenLegs;
	public static ItemOreSpawnArmor QueenBoots;
	public static Block MyOreSaltBlock;
	public static Block MyRTPBlock;
	public static Block MyMoleDirtBlock;
	public static Item MySalt;
	public static Item MyPopcorn;
	public static Item MyButteredPopcorn;
	public static Item MyButteredSaltedPopcorn;
	public static Item MyPopcornBag;
	public static Item MyButter;
	public static Item MyCornDog;
	public static Item MyRawCornDog;
	public static Item MyPeacock;
	public static Item MyRawPeacock;
	public static Item MyElevator;
	public static Block MyOreRubyBlock;
	public static Item MyRuby;
	public static Item MyBacon;
	public static Item MyRawBacon;
	public static Item MyCrabMeat;
	public static Item MyRawCrabMeat;
	public static Item MyButterCandy;
	public static Block MyOreAmethystBlock;
	public static Item MyAmethyst;
	public static Item UraniumNugget;
	public static Item TitaniumNugget;
	public static Item MySalad;
	public static Item MyBLT;
	public static Item MyCrabbyPatty;
	public static Block CrystalStone;
	public static Block CrystalRat;
	public static Block CrystalFairy;
	public static Block CrystalCoal;
	public static Block CrystalGrass;
	public static Block CrystalCrystal;
	public static Block RedAntTroll;
	public static Block TermiteTroll;
	public static Item CageEmpty;
	public static Item CagedSpider;
	public static Item CagedBat;
	public static Item CagedCow;
	public static Item CagedPig;
	public static Item CagedSquid;
	public static Item CagedChicken;
	public static Item CagedCreeper;
	public static Item CagedSkeleton;
	public static Item CagedZombie;
	public static Item CagedSlime;
	public static Item CagedGhast;
	public static Item CagedZombiePigman;
	public static Item CagedEnderman;
	public static Item CagedCaveSpider;
	public static Item CagedSilverfish;
	public static Item CagedMagmaCube;
	public static Item CagedWitch;
	public static Item CagedSheep;
	public static Item CagedWolf;
	public static Item CagedMooshroom;
	public static Item CagedOcelot;
	public static Item CagedBlaze;
	public static Item CagedGirlfriend;
	public static Item CagedBoyfriend;
	public static Item CagedWitherSkeleton;
	public static Item CagedEnderDragon;
	public static Item CagedSnowGolem;
	public static Item CagedIronGolem;
	public static Item CagedWitherBoss;
	public static Item CagedRedCow;
	public static Item CagedCrystalCow;
	public static Item CagedVillager;
	public static Item CagedGoldCow;
	public static Item CagedEnchantedCow;
	public static Item CagedMOTHRA;
	public static Item CagedAlo;
	public static Item CagedCryo;
	public static Item CagedCama;
	public static Item CagedVelo;
	public static Item CagedHydro;
	public static Item CagedBasil;
	public static Item CagedDragonfly;
	public static Item CagedEmperorScorpion;
	public static Item CagedScorpion;
	public static Item CagedCaveFisher;
	public static Item CagedSpyro;
	public static Item CagedBaryonyx;
	public static Item CagedGammaMetroid;
	public static Item CagedCockateil;
	public static Item CagedKyuubi;
	public static Item CagedAlien;
	public static Item CagedAttackSquid;
	public static Item CagedWaterDragon;
	public static Item CagedCephadrome;
	public static Item CagedDragon;
	public static Item CagedKraken;
	public static Item CagedLizard;
	public static Item CagedBee;
	public static Item CagedHorse;
	public static Item CagedFirefly;
	public static Item CagedChipmunk;
	public static Item CagedGazelle;
	public static Item CagedOstrich;
	public static Item CagedTrooper;
	public static Item CagedSpit;
	public static Item CagedStink;
	public static Item CagedCreepingHorror;
	public static Item CagedTerribleTerror;
	public static Item CagedCliffRacer;
	public static Item CagedTriffid;
	public static Item CagedPitchBlack;
	public static Item CagedLurkingTerror;
	public static Item CagedSmallWorm;
	public static Item CagedMediumWorm;
	public static Item CagedLargeWorm;
	public static Item CagedCassowary;
	public static Item CagedCloudShark;
	public static Item CagedGoldFish;
	public static Item CagedLeafMonster;
	public static Item CagedEnderKnight;
	public static Item CagedEnderReaper;
	public static Item CagedBeaver;
	public static Item CagedUrchin;
	public static Item CagedFlounder;
	public static Item CagedSkate;
	public static Item CagedRotator;
	public static Item CagedPeacock;
	public static Item CagedFairy;
	public static Item CagedDungeonBeast;
	public static Item CagedVortex;
	public static Item CagedRat;
	public static Item CagedWhale;
	public static Item CagedIrukandji;
	public static Item CagedTRex;
	public static Item CagedHercules;
	public static Item CagedMantis;
	public static Item CagedStinky;
	public static Item CagedEasterBunny;
	public static Item CagedCaterKiller;
	public static Item CagedMolenoid;
	public static Item CagedSeaMonster;
	public static Item CagedSeaViper;
	public static Item CagedLeon;
	public static Item CagedHammerhead;
	public static Item CagedRubberDucky;
	public static Item CagedCriminal;
	public static Item CagedBrutalfly;
	public static Item CagedNastysaurus;
	public static Item CagedPointysaurus;
	public static Item CagedCricket;
	public static Item CagedFrog;
	public static Item CagedSpiderDriver;
	public static Item CagedCrab;
	public static Item WitherSkeletonEgg;
	public static Item EnderDragonEgg;
	public static Item SnowGolemEgg;
	public static Item IronGolemEgg;
	public static Item WitherBossEgg;
	public static Item GirlfriendEgg;
	public static Item RedCowEgg;
	public static Item CrystalCowEgg;
	public static Item GoldCowEgg;
	public static Item EnchantedCowEgg;
	public static Item MOTHRAEgg;
	public static Item AloEgg;
	public static Item CryoEgg;
	public static Item CamaEgg;
	public static Item VeloEgg;
	public static Item HydroEgg;
	public static Item BasilEgg;
	public static Item DragonflyEgg;
	public static Item EmperorScorpionEgg;
	public static Item ScorpionEgg;
	public static Item CaveFisherEgg;
	public static Item SpyroEgg;
	public static Item BaryonyxEgg;
	public static Item GammaMetroidEgg;
	public static Item CockateilEgg;
	public static Item KyuubiEgg;
	public static Item AlienEgg;
	public static Item AttackSquidEgg;
	public static Item WaterDragonEgg;
	public static Item CephadromeEgg;
	public static Item DragonEgg;
	public static Item KrakenEgg;
	public static Item LizardEgg;
	public static Item BeeEgg;
	public static Item TrooperBugEgg;
	public static Item SpitBugEgg;
	public static Item StinkBugEgg;
	public static Item OstrichEgg;
	public static Item GazelleEgg;
	public static Item ChipmunkEgg;
	public static Item CreepingHorrorEgg;
	public static Item TerribleTerrorEgg;
	public static Item CliffRacerEgg;
	public static Item TriffidEgg;
	public static Item PitchBlackEgg;
	public static Item LurkingTerrorEgg;
	public static Item GodzillaEgg;
	public static Item SmallWormEgg;
	public static Item MediumWormEgg;
	public static Item LargeWormEgg;
	public static Item CassowaryEgg;
	public static Item CloudSharkEgg;
	public static Item GoldFishEgg;
	public static Item LeafMonsterEgg;
	public static Item TshirtEgg;
	public static Item EnderKnightEgg;
	public static Item EnderReaperEgg;
	public static Item BeaverEgg;
	public static Item RotatorEgg;
	public static Item VortexEgg;
	public static Item PeacockEgg;
	public static Item FairyEgg;
	public static Item DungeonBeastEgg;
	public static Item RatEgg;
	public static Item FlounderEgg;
	public static Item WhaleEgg;
	public static Item IrukandjiEgg;
	public static Item SkateEgg;
	public static Item UrchinEgg;
	public static Item Robot1Egg;
	public static Item Robot2Egg;
	public static Item Robot3Egg;
	public static Item Robot4Egg;
	public static Item GhostEgg;
	public static Item GhostSkellyEgg;
	public static Item BrownAntEgg;
	public static Item RedAntEgg;
	public static Item RainbowAntEgg;
	public static Item UnstableAntEgg;
	public static Item TermiteEgg;
	public static Item ButterflyEgg;
	public static Item MothEgg;
	public static Item MosquitoEgg;
	public static Item FireflyEgg;
	public static Item TRexEgg;
	public static Item HerculesEgg;
	public static Item MantisEgg;
	public static Item StinkyEgg;
	public static Item Robot5Egg;
	public static Item CoinEgg;
	public static Item BoyfriendEgg;
	public static Item TheKingEgg;
	public static Item TheQueenEgg;
	public static Item ThePrinceEgg;
	public static Item EasterBunnyEgg;
	public static Item MolenoidEgg;
	public static Item SeaMonsterEgg;
	public static Item SeaViperEgg;
	public static Item CaterKillerEgg;
	public static Item LeonEgg;
	public static Item HammerheadEgg;
	public static Item RubberDuckyEgg;
	public static Item CriminalEgg;
	public static Item BrutalflyEgg;
	public static Item NastysaurusEgg;
	public static Item PointysaurusEgg;
	public static Item CricketEgg;
	public static Item ThePrincessEgg;
	public static Item FrogEgg;
	public static Item JefferyEgg;
	public static Item AntRobotEgg;
	public static Item SpiderRobotEgg;
	public static Item SpiderDriverEgg;
	public static Item CrabEgg;
	public static Item MyStrawberry;
	public static Item MyCrystalApple;
	public static Item MyLove;
	public static Item MyCheese;
	public static Item MyCherry;
	public static Item MyPeach;
	public static Item MyStrawberrySeed;
	public static Block MyStrawberryPlant;
	public static Item MyButterflySeed;
	public static Block MyButterflyPlant;
	public static Item MyMothSeed;
	public static Block MyMothPlant;
	public static Item MyMosquitoSeed;
	public static Block MyMosquitoPlant;
	public static Item MyFireflySeed;
	public static Block MyFireflyPlant;
	public static Item MyRadish;
	public static Item MyRice;
	public static Block MyRadishPlant;
	public static Block MyRicePlant;
	public static Block MyCornPlant1;
	public static Block MyCornPlant2;
	public static Block MyCornPlant3;
	public static Block MyCornPlant4;
	public static Item MyCornCob;
	public static Block MyQuinoaPlant1;
	public static Block MyQuinoaPlant2;
	public static Block MyQuinoaPlant3;
	public static Block MyQuinoaPlant4;
	public static Item MyQuinoa;
	public static Block MyTomatoPlant1;
	public static Block MyTomatoPlant2;
	public static Block MyTomatoPlant3;
	public static Block MyTomatoPlant4;
	public static Item MyTomato;
	public static Block MyLettucePlant1;
	public static Block MyLettucePlant2;
	public static Block MyLettucePlant3;
	public static Block MyLettucePlant4;
	public static Item MyLettuce;
	public static Item MagicApple;
	public static Item RandomDungeon;
	public static Item MinersDream;
	public static Block ExtremeTorch;
	public static Block MyEnderPearlBlock;
	public static Block MyEyeOfEnderBlock;
	public static Block MyExperiencePlant;
	public static Block KrakenRepellent;
	public static Block MyIslandBlock;
	public static Block CreeperRepellent;
	public static Item ZooCage2;
	public static Item ZooCage4;
	public static Item ZooCage6;
	public static Item ZooCage8;
	public static Item ZooCage10;
	public static Item InstantShelter;
	public static Item InstantGarden;
	public static Block CrystalTorch;
	public static Item MyPeacockFeather;
	public static Block MyKingSpawnerBlock;
	public static Block MyQueenSpawnerBlock;
	public static Block MyDungeonSpawnerBlock;
	public static Block MyCrystalPlant;
	public static Block MyCrystalPlant2;
	public static Block MyCrystalPlant3;
	public static Block MyAppleLeaves;
	public static Item MyAppleSeed;
	public static Item MyCherrySeed;
	public static Item MyPeachSeed;
	public static Block MySkyTreeLog;
	public static Block MyDT;
	public static Block MyExperienceLeaves;
	public static Block MyScaryLeaves;
	public static Block MyCherryLeaves;
	public static Block MyPeachLeaves;
	public static Item MyExperienceCatcher;
	public static Item MyExperienceTreeSeed;
	public static Item MyDeadStinkBug;
	public static Block MyCrystalLeaves;
	public static Block MyCrystalLeaves2;
	public static Block MyCrystalLeaves3;
	public static Block MyCrystalTreeLog;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int GirlfriendID = 0;
	public static int BoyfriendID = 0;
	public static int RedCowID = 0;
	public static int GoldCowID = 0;
	public static int CrystalCowID = 0;
	public static int ButterflyID = 0;
	public static int FireflyID = 0;
	public static int FairyID = 0;
	public static int BeeID = 0;
	public static int TheKingID = 0;
	public static int TheQueenID = 0;
	public static int ThePrinceID = 0;
	public static int ThePrincessID = 0;
	public static int ThePrinceTeenID = 0;
	public static int ThePrinceAdultID = 0;
	public static int MantisID = 0;
	public static int StinkyID = 0;
	public static int HerculesBeetleID = 0;
	public static int LunaMothID = 0;
	public static int MosquitoID = 0;
	public static int GhostID = 0;
	public static int GhostSkellyID = 0;
	public static int SpiderRobotID = 0;
	public static int AntRobotID = 0;
	public static int JefferyID = 0;
	public static int SpiderDriverID = 0;
	public static int MothraID = 0;
	public static int BrutalflyID = 0;
	public static int NastysaurusID = 0;
	public static int PointysaurusID = 0;
	public static int CricketID = 0;
	public static int FrogID = 0;
	public static int EnchantedCowID = 0;
	public static int AntID = 0;
	public static int UnstableAntID = 0;
	public static int RedAntID = 0;
	public static int TermiteID = 0;
	public static int RockBaseID = 0;
	public static int RainbowAntID = 0;
	public static int AlosaurusID = 0;
	public static int LeonID = 0;
	public static int CaterKillerID = 0;
	public static int MolenoidID = 0;
	public static int TRexID = 0;
	public static int BandPID = 0;
	public static int CryolophosaurusID = 0;
	public static int RatID = 0;
	public static int UrchinID = 0;
	public static int CamarasaurusID = 0;
	public static int VelocityRaptorID = 0;
	public static int HydroliscID = 0;
	public static int SpyroID = 0;
	public static int BaryonyxID = 0;
	public static int CassowaryID = 0;
	public static int EasterBunnyID = 0;
	public static int PeacockID = 0;
	public static int CockateilID = 0;
	public static int RubyBirdID = 0;
	public static int KyuubiID = 0;
	public static int CephadromeID = 0;
	public static int DragonID = 0;
	public static int GammaMetroidID = 0;
	public static int BasiliskID = 0;
	public static int DragonflyID = 0;
	public static int EmperorScorpionID = 0;
	public static int TrooperBugID = 0;
	public static int SpitBugID = 0;
	public static int StinkBugID = 0;
	public static int ScorpionID = 0;
	public static int CaveFisherID = 0;
	public static int AlienID = 0;
	public static int WaterDragonID = 0;
	public static int SeaMonsterID = 0;
	public static int SeaViperID = 0;
	public static int AttackSquidID = 0;
	public static int ElevatorID = 0;
	public static int Robot1ID = 0;
	public static int Robot2ID = 0;
	public static int Robot3ID = 0;
	public static int Robot4ID = 0;
	public static int Robot5ID = 0;
	public static int RotatorID = 0;
	public static int VortexID = 0;
	public static int DungeonBeastID = 0;
	public static int KrakenID = 0;
	public static int LizardID = 0;
	public static int RubberDuckyID = 0;
	public static int ChipmunkID = 0;
	public static int OstrichID = 0;
	public static int GazelleID = 0;
	public static int TshirtID = 0;
	public static int CoinID = 0;
	public static int IslandID = 0;
	public static int IslandTooID = 0;
	public static int CreepingHorrorID = 0;
	public static int TerribleTerrorID = 0;
	public static int CliffRacerID = 0;
	public static int TriffidID = 0;
	public static int PitchBlackID = 0;
	public static int LurkingTerrorID = 0;
	public static int GodzillaID = 0;
	public static int WormSmallID = 0;
	public static int WormMediumID = 0;
	public static int WormLargeID = 0;
	public static int CloudSharkID = 0;
	public static int GoldFishID = 0;
	public static int LeafMonsterID = 0;
	public static int GodzillaHeadID = 0;
	public static int KingHeadID = 0;
	public static int QueenHeadID = 0;
	public static int EnderKnightID = 0;
	public static int EnderReaperID = 0;
	public static int BeaverID = 0;
	public static int SkateID = 0;
	public static int IrukandjiID = 0;
	public static int FlounderID = 0;
	public static int WhaleID = 0;
	public static int HammerheadID = 0;
	public static int CrabID = 0;
	
	
	public static MobStats Bee_stats = null;
	public static MobStats Mantis_stats = null;
	public static MobStats HerculesBeetle_stats = null;
	public static MobStats Mothra_stats = null;
	public static MobStats Brutalfly_stats = null;
	public static MobStats Nastysaurus_stats = null;
	public static MobStats Pointysaurus_stats = null;
	public static MobStats Alosaurus_stats = null;
	public static MobStats SpiderRobot_stats = null;
	public static MobStats AntRobot_stats = null;
	public static MobStats Jeffery_stats = null;
	public static MobStats Hammerhead_stats = null;
	public static MobStats Leon_stats = null;
	public static MobStats CaterKiller_stats = null;
	public static MobStats Molenoid_stats = null;
	public static MobStats TRex_stats = null;
	public static MobStats BandP_stats = null;
	public static MobStats Cryolophosaurus_stats = null;
	public static MobStats Rat_stats = null;
	public static MobStats Urchin_stats = null;
	public static MobStats Kyuubi_stats = null;
	public static MobStats GammaMetroid_stats = null;
	public static MobStats Basilisk_stats = null;
	public static MobStats EmperorScorpion_stats = null;
	public static MobStats TrooperBug_stats = null;
	public static MobStats SpitBug_stats = null;
	public static MobStats Alien_stats = null;
	public static MobStats WaterDragon_stats = null;
	public static MobStats SeaMonster_stats = null;
	public static MobStats SeaViper_stats = null;
	public static MobStats Robot2_stats = null;
	public static MobStats Robot3_stats = null;
	public static MobStats Robot4_stats = null;
	public static MobStats Robot5_stats = null;
	public static MobStats Rotator_stats = null;
	public static MobStats Vortex_stats = null;
	public static MobStats DungeonBeast_stats = null;
	public static MobStats Triffid_stats = null;
	public static MobStats LurkingTerror_stats = null;
	public static MobStats WormSmall_stats = null;
	public static MobStats WormMedium_stats = null;
	public static MobStats WormLarge_stats = null;
	public static MobStats EnderKnight_stats = null;
	public static MobStats EnderReaper_stats = null;
	public static MobStats Irukandji_stats = null;
	public static MobStats AttackSquid_stats = null;
	public static MobStats CaveFisher_stats = null;
	public static MobStats CloudShark_stats = null;
	public static MobStats CreepingHorror_stats = null;
	public static MobStats Godzilla_stats = null;
	public static MobStats Kraken_stats = null;
	public static MobStats LeafMonster_stats = null;
	public static MobStats PitchBlack_stats = null;
	public static MobStats Crab_stats = null;
	public static MobStats Scorpion_stats = null;
	public static MobStats Skate_stats = null;
	public static MobStats TerribleTerror_stats = null;
	public static MobStats TheKing_stats = null;
	public static MobStats TheQueen_stats = null;
	
	public static OreStats Ruby_stats = null;
	public static OreStats BlkRuby_stats = null;
	public static OreStats Uranium_stats = null;
	public static OreStats Titanium_stats = null;
	public static OreStats Amethyst_stats = null;
	public static OreStats Salt_stats = null;
	public static OreStats SpawnOres_stats = null;
	public static OreStats Diamond_stats = null;
	public static OreStats BlkDiamond_stats = null;
	public static OreStats Emerald_stats = null;
	public static OreStats BlkEmerald_stats = null;
	public static OreStats Gold_stats = null;
	public static OreStats BlkGold_stats = null;

	
	
	
	
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		String ids = "OreSpawnIDS";
		String mobs = "OreSpawnMOBS";
		String tweaks = "OreSpawnTWEAKS";
		String weapons = "OreSpawnWEAPONS";
		String ores = "OreSpawnORES";
		
		config.load();
		
		
		
		
		
		
		
		BaseBlockID = config.get(ids, "BaseBlockID", 2700).getInt();
		BaseItemID = config.get(ids, "BaseItemID", 9000).getInt();
		BaseBiomeID = config.get(ids, "BaseBiomeID", 120).getInt();
		BaseDimensionID = config.get(ids, "BaseDimensionID", 80).getInt();
		
		this.getMobs(config, mobs);
		
		
		AllMobsDisable = config.get(tweaks, "AllMobsDisable", 0).getInt();
		LessOre = config.get(tweaks, "LessOre", 0).getInt();
		LessLag = config.get(tweaks, "LessLag", 0).getInt();
		RatPlayerFriendly = config.get(tweaks, "RatPlayerFriendly", 1).getInt();
		RatPetFriendly = config.get(tweaks, "RatPetFriendly", 1).getInt();
		NightmareSize = config.get(tweaks, "NightmareSize", 0).getInt();
		IslandSpeedFactor = config.get(tweaks, "IslandSpeedFactor", 2).getInt();
		IslandSizeFactor = config.get(tweaks, "IslandSizeFactor", 2).getInt();
		GinormousEmeraldTreeEnable = config.get(tweaks, "GinormousEmeraldTreeEnable", 1).getInt();
		GuiOverlayEnable = config.get(tweaks, "GuiOverlayEnable", 1).getInt();
		ultimate_sword_pvp = config.get(tweaks, "UltimateSwordPvp", 0).getInt();
		big_bertha_pvp = config.get(tweaks, "BigBerthaPvp", 0).getInt();
		bro_mode = config.get(tweaks, "BoyfriendBroMode", 0).getInt();
		enableduplicatortree = config.get(tweaks, "DuplicatorTreeEnable", 1).getInt();
		RoyalGlideEnable = config.get(tweaks, "RoyalGlideEnable", 1).getInt();
		DragonflyHorseFriendly = config.get(tweaks, "DragonflyHorseFriendly", 0).getInt();
		PlayNicely = config.get(tweaks, "PlayNicely", 0).getInt();
		MinersDreamExpensive = config.get(tweaks, "MinersDreamExpensive", 0).getInt();
		DisableOverworldDungeons = config.get(tweaks, "DisableOverworldDungeons", 0).getInt();
		FullPowerKingEnable = config.get(tweaks, "FullPowerKingEnable", 0).getInt();
		
		Amethyst_armorstats = this.get_armorstats(config, "Amethyst", 100, 4, 8, 7, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
		Emerald_armorstats = this.get_armorstats(config, "Emerald", 60, 3, 8, 6, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
		Experience_armorstats = this.get_armorstats(config, "Experience", 70, 5, 9, 7, 4, 50, 0, 0, 2, 0, 1, 0, 0, 1);
		MothScale_armorstats = this.get_armorstats(config, "MothScale", 50, 2, 7, 5, 2, 50, 0, 0, 3, 3, 3, 0, 0, 5);
		LavaEel_armorstats = this.get_armorstats(config, "LavaEel", 40, 2, 7, 5, 2, 35, 1, 2, 3, 2, 10, 0, 0, 2);
		Ultimate_armorstats = this.get_armorstats(config, "Ultimate", 200, 6, 12, 10, 6, 100, 2, 3, 5, 5, 5, 5, 0, 3);
		Pink_armorstats = this.get_armorstats(config, "Pink", 50, 3, 7, 5, 2, 40, 0, 0, 0, 0, 0, 0, 0, 0);
		TigersEye_armorstats = this.get_armorstats(config, "TigersEye", 80, 4, 8, 7, 4, 55, 0, 0, 0, 0, 0, 0, 0, 0);
		Peacock_armorstats = this.get_armorstats(config, "Peacock", 40, 2, 5, 4, 2, 30, 0, 0, 0, 0, 0, 0, 0, 10);
		Mobzilla_armorstats = this.get_armorstats(config, "Mobzilla", 1000, 7, 13, 11, 7, 150, 0, 0, 10, 10, 10, 10, 5, 10);
		Ruby_armorstats = this.get_armorstats(config, "Ruby", 90, 4, 9, 8, 4, 40, 0, 0, 0, 0, 0, 0, 0, 0);
		Royal_armorstats = this.get_armorstats(config, "Royal", 2000, 8, 14, 12, 8, 200, 1, 2, 10, 10, 10, 10, 5, 10);
		Lapis_armorstats = this.get_armorstats(config, "Lapis", 60, 2, 7, 5, 2, 60, 1, 1, 1, 0, 0, 1, 0, 0);
		Queen_armorstats = this.get_armorstats(config, "Queen", 1500, 9, 16, 14, 9, 150, 0, 0, 0, 0, 0, 0, 0, 0);
		
		
		ultimate_stats = this.get_weaponstats(config, weapons, "Ultimate", 10, 3000, 15, 36, 100);
		nightmare_stats = this.get_weaponstats(config, weapons, "Nightmare", 3, 1800, 12, 26, 60);
		bertha_stats = this.get_weaponstats(config, weapons, "Bertha", 3, 9000, 15, 496, 100);
		crystalwood_stats = this.get_weaponstats(config, weapons, "CrystalWood", 2, 300, 3, 2, 15);
		crystalstone_stats = this.get_weaponstats(config, weapons, "CrystalStone", 3, 800, 6, 5, 45);
		crystalpink_stats = this.get_weaponstats(config, weapons, "Pink", 4, 1100, 10, 7, 65);
		tigerseye_stats = this.get_weaponstats(config, weapons, "TigersEye", 4, 1600, 12, 8, 75);
		ruby_stats = this.get_weaponstats(config, weapons, "Ruby", 5, 1500, 11, 16, 85);
		amethyst_stats = this.get_weaponstats(config, weapons, "Amethyst", 4, 2000, 11, 11, 70);
		emerald_stats = this.get_weaponstats(config, weapons, "Emerald", 3, 1300, 10, 6, 75);
		royal_stats = this.get_weaponstats(config, weapons, "Royal", 3, 10000, 15, 746, 150);
		hammy_stats = this.get_weaponstats(config, weapons, "Attitude", 5, 2000, 15, 82, 100);
		battleaxe_stats = this.get_weaponstats(config, weapons, "BattleAxe", 3, 1500, 15, 46, 75);
		chainsaw_stats = this.get_weaponstats(config, weapons, "Chainsaw", 3, 1500, 10, 56, 75);
		queenbattleaxe_stats = this.get_weaponstats(config, weapons, "QueenBattleAxe", 3, 2200, 15, 662, 100);
		
		UltimateSwordMagic = config.get(weapons, "UltimateSwordEnchantmentLevel", 5).getInt();
		UltimateBowDamage = config.get(weapons, "UltimateBowDamage", 10).getInt();
		
		if (UltimateSwordMagic < 1) UltimateSwordMagic = 1;
		if (UltimateSwordMagic > 10) UltimateSwordMagic = 10;
		if (UltimateBowDamage < 2) UltimateBowDamage = 2;
		if (UltimateBowDamage > 20) UltimateBowDamage = 20;

		if (IslandSpeedFactor < 1) IslandSpeedFactor = 1;
		if (IslandSpeedFactor > 5) IslandSpeedFactor = 5;
		if (IslandSizeFactor < 1) IslandSizeFactor = 1;
		if (IslandSizeFactor > 5) IslandSizeFactor = 5;

		if (NightmareSize < 0) NightmareSize = 0;
		if (NightmareSize > 5) NightmareSize = 5;
		if (LessLag < 0) LessLag = 0;
		if (LessLag > 2) LessLag = 2;
		if (LessLag == 1) {
			if (IslandSizeFactor > 2) IslandSizeFactor = 2;
			if (IslandSpeedFactor > 2) IslandSpeedFactor = 2;
		}
		if (LessLag == 2) {
			if (IslandSizeFactor > 1) IslandSizeFactor = 1;
			if (IslandSpeedFactor > 1) IslandSpeedFactor = 1;
			LessOre = 1;
		}

		Ruby_stats = this.get_orestats(config, ores, "Ruby", 10, 1, 0, 50);
		BlkRuby_stats = this.get_orestats(config, ores, "BlockRuby", 1, 2, 0, 15);
		Uranium_stats = this.get_orestats(config, ores, "Uranium", 3, 4, 0, 30);
		Titanium_stats = this.get_orestats(config, ores, "Titanium", 3, 4, 0, 20);
		Amethyst_stats = this.get_orestats(config, ores, "Amethyst", 2, 6, 0, 25);
		Salt_stats = this.get_orestats(config, ores, "Salt", 5, 12, 50, 128);
		SpawnOres_stats = this.get_orestats(config, ores, "SpawnOres", 28, 4, 50, 128);
		Diamond_stats = this.get_orestats(config, ores, "Diamond", 4, 6, 0, 30);
		BlkDiamond_stats = this.get_orestats(config, ores, "BlockDiamond", 2, 4, 0, 20);
		Emerald_stats = this.get_orestats(config, ores, "Emerald", 4, 6, 0, 40);
		BlkEmerald_stats = this.get_orestats(config, ores, "BlockEmerald", 2, 4, 0, 20);
		Gold_stats = this.get_orestats(config, ores, "Gold", 4, 8, 0, 40);
		BlkGold_stats = this.get_orestats(config, ores, "BlockGold", 2, 4, 0, 25);
		
		
		config.save();
		
		if (AllMobsDisable != 0) {
			this.disableAllMobs();
		}

		

		
		BiomeUtopiaID = BaseBiomeID;
		BiomeIslandsID = BaseBiomeID + 1;
		BiomeCrystalID = BaseBiomeID + 2;
		BiomeVillageID = BaseBiomeID + 3;
		BiomeChaosID = BaseBiomeID + 4;
		DimensionID = BaseDimensionID;
		DimensionID2 = BaseDimensionID + 1;
		DimensionID3 = BaseDimensionID + 2;
		DimensionID4 = BaseDimensionID + 3;
		DimensionID5 = BaseDimensionID + 4;
		DimensionID6 = BaseDimensionID + 5;
		
		
		proxy.registerSoundThings();
		
		this.laySomeEggs();
		
		
		
		MyOreUraniumBlock = (new OreUranium(BaseBlockID + OreSpawnConstants.UraniumBlockID)).setBlockName("oreuranium");
		MyOreTitaniumBlock = (new OreTitanium(BaseBlockID + OreSpawnConstants.TitaniumBlockID)).setBlockName("oretitanium");
		MyIngotUranium = (new IngotUranium(BaseItemID + OreSpawnConstants.UraniumIngotItemID)).setUnlocalizedName("ingoturanium");
		MyIngotTitanium = (new IngotTitanium(BaseItemID + OreSpawnConstants.TitaniumIngotItemID)).setUnlocalizedName("ingottitanium");
		MyBlockUraniumBlock = (new BlockUranium(BaseBlockID + OreSpawnConstants.BlockUraniumBlockID)).setBlockName("blockuranium");
		MyBlockTitaniumBlock = (new BlockTitanium(BaseBlockID + OreSpawnConstants.BlockTitaniumBlockID)).setBlockName("blocktitanium");
		MyBlockMobzillaScaleBlock = (new BlockRuby(BaseBlockID + OreSpawnConstants.BlockMobzillaScaleBlockID)).setBlockName("blockmobzillascale");
		MyLavafoamBlock = (new Lavafoam(BaseBlockID + OreSpawnConstants.LavafoamBlockID)).setBlockName("lavafoam");
		MyBlockRubyBlock = (new BlockRuby(BaseBlockID + OreSpawnConstants.BlockRubyBlockID)).setBlockName("blockruby");
		MyBlockAmethystBlock = (new BlockRuby(BaseBlockID + OreSpawnConstants.BlockAmethystBlockID)).setBlockName("blockamethyst");
		MyCrystalPinkBlock = (new BlockCrystal(BaseBlockID + OreSpawnConstants.CrystalPinkBlockBlockID)).setBlockName("crystalpink_block");
		MyCrystalPinkIngot = (new IngotUranium(BaseItemID + OreSpawnConstants.CrystalPinkIngotItemID)).setUnlocalizedName("crystalpink_ingot");
		MyTigersEyeBlock = (new BlockCrystal(BaseBlockID + OreSpawnConstants.TigersEyeBlockBlockID)).setBlockName("tigerseye_block");
		MyTigersEyeIngot = (new IngotUranium(BaseItemID + OreSpawnConstants.TigersEyeIngotItemID)).setUnlocalizedName("tigerseye_ingot");
		
		MyPizzaBlock = (new BlockPizza(BaseBlockID + OreSpawnConstants.PizzaBlockID)).setBlockName("pizza");
		MyPizzaItem = (new ItemPizza(BaseItemID + OreSpawnConstants.PizzaItemID, MyPizzaBlock)).setMaxStackSize(1).setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName("pizza");
		MyDuctTapeBlock = (new BlockDuctTape(BaseBlockID + OreSpawnConstants.DuctTapeBlockID)).setBlockName("ducttape");
		MyDuctTapeItem = (new ItemDuctTape(BaseItemID + OreSpawnConstants.DuctTapeItemID, MyDuctTapeBlock)).setMaxStackSize(1).setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("ducttape");
		
		toolULTIMATE = EnumHelper.addToolMaterial("ULTIMATE", ultimate_stats.harvestlevel, ultimate_stats.maxuses, (float)ultimate_stats.efficiency, (float)ultimate_stats.damage, ultimate_stats.enchantability);
		
		toolNIGHTMARE = EnumHelper.addToolMaterial("NIGHTMARE", nightmare_stats.harvestlevel, nightmare_stats.maxuses, (float)nightmare_stats.efficiency, (float)nightmare_stats.damage, nightmare_stats.enchantability);
		
		toolEMERALD = EnumHelper.addToolMaterial("REALEMERALD", emerald_stats.harvestlevel, emerald_stats.maxuses, (float)emerald_stats.efficiency, (float)emerald_stats.damage, emerald_stats.enchantability);
		
		toolRUBY = EnumHelper.addToolMaterial("RUBY", ruby_stats.harvestlevel, ruby_stats.maxuses, (float)ruby_stats.efficiency, (float)ruby_stats.damage, ruby_stats.enchantability);
		
		toolAMETHYST = EnumHelper.addToolMaterial("AMETHYST", amethyst_stats.harvestlevel, amethyst_stats.maxuses, (float)amethyst_stats.efficiency, (float)amethyst_stats.damage, amethyst_stats.enchantability);
		
		toolBERTHA = EnumHelper.addToolMaterial("BERTHA", bertha_stats.harvestlevel, bertha_stats.maxuses, (float)bertha_stats.efficiency, (float)bertha_stats.damage, bertha_stats.enchantability);
		
		toolCRYSTALWOOD = EnumHelper.addToolMaterial("CRYSTALWOOD", crystalwood_stats.harvestlevel, crystalwood_stats.maxuses, (float)crystalwood_stats.efficiency, (float)crystalwood_stats.damage, crystalwood_stats.enchantability);
		
		toolCRYSTALSTONE = EnumHelper.addToolMaterial("CRYSTALSTONE", crystalstone_stats.harvestlevel, crystalstone_stats.maxuses, (float)crystalstone_stats.efficiency, (float)crystalstone_stats.damage, crystalstone_stats.enchantability);
		
		toolCRYSTALPINK = EnumHelper.addToolMaterial("CRYSTALPINK", crystalpink_stats.harvestlevel, crystalpink_stats.maxuses, (float)crystalpink_stats.efficiency, (float)crystalpink_stats.damage, crystalpink_stats.enchantability);
		
		toolTIGERSEYE = EnumHelper.addToolMaterial("TIGERSEYE", tigerseye_stats.harvestlevel, tigerseye_stats.maxuses, (float)tigerseye_stats.efficiency, (float)tigerseye_stats.damage, tigerseye_stats.enchantability);
		
		toolROYAL = EnumHelper.addToolMaterial("ROYAL", royal_stats.harvestlevel, royal_stats.maxuses, (float)royal_stats.efficiency, (float)royal_stats.damage, royal_stats.enchantability);
		
		toolHAMMY = EnumHelper.addToolMaterial("HAMMY", hammy_stats.harvestlevel, hammy_stats.maxuses, (float)hammy_stats.efficiency, (float)hammy_stats.damage, hammy_stats.enchantability);
		
		toolBATTLE = EnumHelper.addToolMaterial("BATTLE", battleaxe_stats.harvestlevel, battleaxe_stats.maxuses, (float)battleaxe_stats.efficiency, (float)battleaxe_stats.damage, battleaxe_stats.enchantability);
		
		toolCHAINSAW = EnumHelper.addToolMaterial("CHAINSAW", chainsaw_stats.harvestlevel, chainsaw_stats.maxuses, (float)chainsaw_stats.efficiency, (float)chainsaw_stats.damage, chainsaw_stats.enchantability);
		
		toolQUEENBATTLE = EnumHelper.addToolMaterial("QUEENBATTLE", queenbattleaxe_stats.harvestlevel, queenbattleaxe_stats.maxuses, (float)queenbattleaxe_stats.efficiency, (float)queenbattleaxe_stats.damage, queenbattleaxe_stats.enchantability);
		
		
		
		MyUltimateSword = (new UltimateSword(BaseItemID + OreSpawnConstants.UltimateSwordItemID, toolULTIMATE)).setUnlocalizedName("ultimatesword");
		MyUltimatePickaxe = (new UltimatePickaxe(BaseItemID + OreSpawnConstants.UltimatePickaxeItemID, toolULTIMATE)).setUnlocalizedName("ultimatepickaxe");
		MyUltimatePickaxe.setHarvestLevel("pickaxe", ultimate_stats.harvestlevel);
		MyUltimateShovel = (new UltimateShovel(BaseItemID + OreSpawnConstants.UltimateShovelItemID, toolULTIMATE)).setUnlocalizedName("ultimateshovel");
		MyUltimateShovel.setHarvestLevel("shovel", ultimate_stats.harvestlevel);
		MyUltimateHoe = (new UltimateHoe(BaseItemID + OreSpawnConstants.UltimateHoeItemID, toolULTIMATE)).setUnlocalizedName("ultimatehoe");
		MyUltimateAxe = (new UltimateAxe(BaseItemID + OreSpawnConstants.UltimateAxeItemID, toolULTIMATE)).setUnlocalizedName("ultimateaxe");
		MyUltimateAxe.setHarvestLevel("axe", ultimate_stats.harvestlevel);
		MyNightmareSword = (new NightmareSword(BaseItemID + OreSpawnConstants.NightmareSwordItemID, toolNIGHTMARE)).setUnlocalizedName("nightmaresword");
		MyBertha = (new Bertha(BaseItemID + OreSpawnConstants.BerthaItemID, toolBERTHA)).setUnlocalizedName("berthasmall");
		MySlice = (new Bertha(BaseItemID + OreSpawnConstants.SliceItemID, toolBERTHA)).setUnlocalizedName("slicesmall");
		MyRoyal = (new Bertha(BaseItemID + OreSpawnConstants.RoyalItemID, toolROYAL)).setUnlocalizedName("royalsmall");
		MyHammy = (new Bertha(BaseItemID + OreSpawnConstants.HammyItemID, toolHAMMY)).setUnlocalizedName("hammysmall");
		MyBattleAxe = (new UltimateSword(BaseItemID + OreSpawnConstants.BattleAxeItemID, toolBATTLE)).setUnlocalizedName("battleaxesmall");
		MyChainsaw = (new UltimateSword(BaseItemID + OreSpawnConstants.ChainsawItemID, toolCHAINSAW)).setUnlocalizedName("chainsawsmall");
		MyQueenBattleAxe = (new UltimateSword(BaseItemID + OreSpawnConstants.QueenBattleAxeItemID, toolQUEENBATTLE)).setUnlocalizedName("queenbattleaxesmall");
		
		MyEmeraldSword = (new EmeraldSword(BaseItemID + OreSpawnConstants.EmeraldSwordItemID, toolEMERALD)).setUnlocalizedName("emeraldsword");
		MyEmeraldPickaxe = (new EmeraldPickaxe(BaseItemID + OreSpawnConstants.EmeraldPickaxeItemID, toolEMERALD)).setUnlocalizedName("emeraldpickaxe");
		MyEmeraldShovel = (new EmeraldShovel(BaseItemID + OreSpawnConstants.EmeraldShovelItemID, toolEMERALD)).setUnlocalizedName("emeraldshovel");
		MyEmeraldHoe = (new EmeraldHoe(BaseItemID + OreSpawnConstants.EmeraldHoeItemID, toolEMERALD)).setUnlocalizedName("emeraldhoe");
		MyEmeraldAxe = (new EmeraldAxe(BaseItemID + OreSpawnConstants.EmeraldAxeItemID, toolEMERALD)).setUnlocalizedName("emeraldaxe");
		MyExperienceSword = (new ExperienceSword(BaseItemID + OreSpawnConstants.ExperienceSwordItemID, toolEMERALD)).setUnlocalizedName("experiencesword");
		MyPoisonSword = (new PoisonSword(BaseItemID + OreSpawnConstants.PoisonSwordItemID, toolEMERALD)).setUnlocalizedName("poisonsword");
		MyRatSword = (new RatSword(BaseItemID + OreSpawnConstants.RatSwordItemID, toolEMERALD)).setUnlocalizedName("ratsword");
		MyFairySword = (new FairySword(BaseItemID + OreSpawnConstants.FairySwordItemID, toolEMERALD)).setUnlocalizedName("fairysword");
		MyMantisClaw = (new MantisClaw(BaseItemID + OreSpawnConstants.MantisClawItemID, toolEMERALD)).setUnlocalizedName("mantisclaw");
		MyBigHammer = (new BigHammer(BaseItemID + OreSpawnConstants.BigHammerItemID, toolAMETHYST)).setUnlocalizedName("bighammer");
		MyRubySword = (new RubySword(BaseItemID + OreSpawnConstants.RubySwordItemID, toolRUBY)).setUnlocalizedName("rubysword");
		MyRubyPickaxe = (new RubyPickaxe(BaseItemID + OreSpawnConstants.RubyPickaxeItemID, toolRUBY)).setUnlocalizedName("rubypickaxe");
		MyRubyPickaxe.setHarvestLevel("pickaxe", ruby_stats.harvestlevel);
		MyRubyShovel = (new RubyShovel(BaseItemID + OreSpawnConstants.RubyShovelItemID, toolRUBY)).setUnlocalizedName("rubyshovel");
		MyRubyShovel.setHarvestLevel("shovel", ruby_stats.harvestlevel);
		MyRubyHoe = (new RubyHoe(BaseItemID + OreSpawnConstants.RubyHoeItemID, toolRUBY)).setUnlocalizedName("rubyhoe");
		MyRubyAxe = (new RubyAxe(BaseItemID + OreSpawnConstants.RubyAxeItemID, toolRUBY)).setUnlocalizedName("rubyaxe");
		MyRubyAxe.setHarvestLevel("axe", ruby_stats.harvestlevel);
		MyAmethystSword = (new AmethystSword(BaseItemID + OreSpawnConstants.AmethystSwordItemID, toolAMETHYST)).setUnlocalizedName("amethystsword");
		MyAmethystPickaxe = (new AmethystPickaxe(BaseItemID + OreSpawnConstants.AmethystPickaxeItemID, toolAMETHYST)).setUnlocalizedName("amethystpickaxe");
		MyAmethystPickaxe.setHarvestLevel("pickaxe", amethyst_stats.harvestlevel);
		MyAmethystShovel = (new AmethystShovel(BaseItemID + OreSpawnConstants.AmethystShovelItemID, toolAMETHYST)).setUnlocalizedName("amethystshovel");
		MyAmethystShovel.setHarvestLevel("shovel", amethyst_stats.harvestlevel);
		MyAmethystHoe = (new AmethystHoe(BaseItemID + OreSpawnConstants.AmethystHoeItemID, toolAMETHYST)).setUnlocalizedName("amethysthoe");
		MyAmethystAxe = (new AmethystAxe(BaseItemID + OreSpawnConstants.AmethystAxeItemID, toolAMETHYST)).setUnlocalizedName("amethystaxe");
		MyAmethystAxe.setHarvestLevel("axe", amethyst_stats.harvestlevel);
		MyCrystalWoodSword = (new CrystalSword(BaseItemID + OreSpawnConstants.CrystalWoodSwordItemID, toolCRYSTALWOOD)).setUnlocalizedName("crystalwoodsword");
		MyCrystalWoodPickaxe = (new CrystalPickaxe(BaseItemID + OreSpawnConstants.CrystalWoodPickaxeItemID, toolCRYSTALWOOD)).setUnlocalizedName("crystalwoodpickaxe");
		MyCrystalWoodShovel = (new CrystalShovel(BaseItemID + OreSpawnConstants.CrystalWoodShovelItemID, toolCRYSTALWOOD)).setUnlocalizedName("crystalwoodshovel");
		MyCrystalWoodHoe = (new CrystalHoe(BaseItemID + OreSpawnConstants.CrystalWoodHoeItemID, toolCRYSTALWOOD)).setUnlocalizedName("crystalwoodhoe");
		MyCrystalWoodAxe = (new CrystalAxe(BaseItemID + OreSpawnConstants.CrystalWoodAxeItemID, toolCRYSTALWOOD)).setUnlocalizedName("crystalwoodaxe");
		MyCrystalPinkSword = (new CrystalSword(BaseItemID + OreSpawnConstants.CrystalPinkSwordItemID, toolCRYSTALPINK)).setUnlocalizedName("crystalpinksword");
		MyCrystalPinkPickaxe = (new CrystalPickaxe(BaseItemID + OreSpawnConstants.CrystalPinkPickaxeItemID, toolCRYSTALPINK)).setUnlocalizedName("crystalpinkpickaxe");
		MyCrystalPinkShovel = (new CrystalShovel(BaseItemID + OreSpawnConstants.CrystalPinkShovelItemID, toolCRYSTALPINK)).setUnlocalizedName("crystalpinkshovel");
		MyCrystalPinkHoe = (new CrystalHoe(BaseItemID + OreSpawnConstants.CrystalPinkHoeItemID, toolCRYSTALPINK)).setUnlocalizedName("crystalpinkhoe");
		MyCrystalPinkAxe = (new CrystalAxe(BaseItemID + OreSpawnConstants.CrystalPinkAxeItemID, toolCRYSTALPINK)).setUnlocalizedName("crystalpinkaxe");
		MyCrystalStoneSword = (new CrystalSword(BaseItemID + OreSpawnConstants.CrystalStoneSwordItemID, toolCRYSTALSTONE)).setUnlocalizedName("crystalstonesword");
		MyCrystalStonePickaxe = (new CrystalPickaxe(BaseItemID + OreSpawnConstants.CrystalStonePickaxeItemID, toolCRYSTALSTONE)).setUnlocalizedName("crystalstonepickaxe");
		MyCrystalStoneShovel = (new CrystalShovel(BaseItemID + OreSpawnConstants.CrystalStoneShovelItemID, toolCRYSTALSTONE)).setUnlocalizedName("crystalstoneshovel");
		MyCrystalStoneHoe = (new CrystalHoe(BaseItemID + OreSpawnConstants.CrystalStoneHoeItemID, toolCRYSTALSTONE)).setUnlocalizedName("crystalstonehoe");
		MyCrystalStoneAxe = (new CrystalAxe(BaseItemID + OreSpawnConstants.CrystalStoneAxeItemID, toolCRYSTALSTONE)).setUnlocalizedName("crystalstoneaxe");
		MyTigersEyeSword = (new CrystalSword(BaseItemID + OreSpawnConstants.TigersEyeSwordItemID, toolTIGERSEYE)).setUnlocalizedName("tigerseye_sword");
		MyTigersEyePickaxe = (new CrystalPickaxe(BaseItemID + OreSpawnConstants.TigersEyePickaxeItemID, toolTIGERSEYE)).setUnlocalizedName("tigerseye_pickaxe");
		MyTigersEyeShovel = (new CrystalShovel(BaseItemID + OreSpawnConstants.TigersEyeShovelItemID, toolTIGERSEYE)).setUnlocalizedName("tigerseye_shovel");
		MyTigersEyeHoe = (new CrystalHoe(BaseItemID + OreSpawnConstants.TigersEyeHoeItemID, toolTIGERSEYE)).setUnlocalizedName("tigerseye_hoe");
		MyTigersEyeAxe = (new CrystalAxe(BaseItemID + OreSpawnConstants.TigersEyeAxeItemID, toolTIGERSEYE)).setUnlocalizedName("tigerseye_axe");
		MyRoseSword = (new EmeraldSword(BaseItemID + OreSpawnConstants.RoseSwordItemID, toolEMERALD)).setUnlocalizedName("rosesword");
		
		
		MyItemShoes = (new ItemShoes(BaseItemID + OreSpawnConstants.RedHeelsItemID, OreSpawnConstants.RedHeelsIndex)).setUnlocalizedName("redheels");
		MyItemShoes_1 = (new ItemShoes(BaseItemID + OreSpawnConstants.BlackHeelsItemID, OreSpawnConstants.BlackHeelsIndex)).setUnlocalizedName("blackheels");
		MyItemShoes_2 = (new ItemShoes(BaseItemID + OreSpawnConstants.SlippersItemID, OreSpawnConstants.SlippersIndex)).setUnlocalizedName("slippers");
		MyItemShoes_3 = (new ItemShoes(BaseItemID + OreSpawnConstants.BootsItemID, OreSpawnConstants.BootsIndex)).setUnlocalizedName("boots");
		MyItemGameController = (new ItemShoes(BaseItemID + OreSpawnConstants.GameControllerItemID, OreSpawnConstants.GameControllerIndex)).setUnlocalizedName("gamecontroller");
		
		MyUltimateBow = (new UltimateBow(BaseItemID + OreSpawnConstants.UltimateBowItemID)).setUnlocalizedName("ultimatebow");
		MySkateBow = (new SkateBow(BaseItemID + OreSpawnConstants.SkateBowItemID)).setUnlocalizedName("skatebow");
		
		MyUltimateFishingRod = (new UltimateFishingRod(BaseItemID + OreSpawnConstants.UltimateFishingRodItemID)).setUnlocalizedName("ultimatefishingrod");
		UltimateFishingRod = new ItemStack(MyUltimateFishingRod);
		
		MyFireFish = (new ItemFireFish(BaseItemID + OreSpawnConstants.FireFishItemID, 4, 0.6F, false)).setUnlocalizedName("firefish");
		MySunFish = (new ItemSunFish(BaseItemID + OreSpawnConstants.SunFishItemID, 6, 0.6F, false)).setUnlocalizedName("sunfish");
		MyLavaEel = (new ItemLavaEel(BaseItemID + OreSpawnConstants.LavaEelItemID, 2, 0.6F, false)).setUnlocalizedName("lavaeel");
		MyMothScale = (new ItemSalt(BaseItemID + OreSpawnConstants.MothScaleItemID)).setUnlocalizedName("mothscale");
		MyQueenScale = (new ItemSalt(BaseItemID + OreSpawnConstants.QueenScaleItemID)).setUnlocalizedName("queenscale");
		MyNightmareScale = (new ItemSalt(BaseItemID + OreSpawnConstants.NightmareScaleItemID)).setUnlocalizedName("nightmarescale");
		MyEmperorScorpionScale = (new ItemSalt(BaseItemID + OreSpawnConstants.EmperorScorpionScaleItemID)).setUnlocalizedName("emperorscorpionscale");
		MyBasiliskScale = (new ItemSalt(BaseItemID + OreSpawnConstants.BasiliskScaleItemID)).setUnlocalizedName("basiliskscale");
		MyWaterDragonScale = (new ItemSalt(BaseItemID + OreSpawnConstants.WaterDragonScaleItemID)).setUnlocalizedName("waterdragonscale");
		MyPeacockFeather = (new ItemSalt(BaseItemID + OreSpawnConstants.PeacockFeatherItemID)).setUnlocalizedName("peacockfeather");
		MyJumpyBugScale = (new ItemSalt(BaseItemID + OreSpawnConstants.JumpyBugScaleItemID)).setUnlocalizedName("jumpybugscale");
		MyKrakenTooth = (new ItemSalt(BaseItemID + OreSpawnConstants.KrakenToothItemID)).setUnlocalizedName("krakentooth");
		MyGodzillaScale = (new ItemSalt(BaseItemID + OreSpawnConstants.GodzillaScaleItemID)).setUnlocalizedName("godzillascale");
		GreenGoo = (new ItemSalt(BaseItemID + OreSpawnConstants.GreenGooItemID)).setUnlocalizedName("greengoo");
		SpiderRobotKit = (new ItemSpiderRobotKit(BaseItemID + OreSpawnConstants.SpiderRobotKitItemID)).setUnlocalizedName("spiderrobotkit");
		AntRobotKit = (new ItemSpiderRobotKit(BaseItemID + OreSpawnConstants.AntRobotKitItemID)).setUnlocalizedName("antrobotkit");
		ZooKeeper = (new ItemZooKeeper(BaseItemID + OreSpawnConstants.ZooKeeperItemID)).setUnlocalizedName("zookeeper");
		CreeperLauncher = (new ItemCreeperLauncher(BaseItemID + OreSpawnConstants.CreeperLauncherItemID)).setUnlocalizedName("creeperlauncher");
		NetherLost = (new ItemNetherLost(BaseItemID + OreSpawnConstants.NetherLostItemID)).setUnlocalizedName("netherlost");
		CrystalSticks = (new ItemCrystalSticks(BaseItemID + OreSpawnConstants.CrystalSticksItemID)).setUnlocalizedName("crystalsticks");
		MySunspotUrchin = (new ItemSunspotUrchin(BaseItemID + OreSpawnConstants.SunspotUrchinItemID)).setUnlocalizedName("sunspoturchin");
		MySparkFish = (new ItemSparkFish(BaseItemID + OreSpawnConstants.SparkFishItemID, 1, 0.2F, false)).setUnlocalizedName("sparkfish");
		MyWaterBall = (new ItemWaterBall(BaseItemID + OreSpawnConstants.WaterBallItemID)).setUnlocalizedName("waterball");
		MyLaserBall = (new ItemLaserBall(BaseItemID + OreSpawnConstants.LaserBallItemID)).setUnlocalizedName("laserball");
		MyIceBall = (new ItemIceBall(BaseItemID + OreSpawnConstants.IceBallItemID)).setUnlocalizedName("iceball");
		MySmallRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockSmallItemID)).setUnlocalizedName("rocksmall");
		MyRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockItemID)).setUnlocalizedName("rock");
		MyRedRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockRedItemID)).setUnlocalizedName("rockred");
		MyCrystalRedRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockCrystalRedItemID)).setUnlocalizedName("rockcrystalred");
		MyCrystalGreenRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockCrystalGreenItemID)).setUnlocalizedName("rockcrystalgreen");
		MyCrystalBlueRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockCrystalBlueItemID)).setUnlocalizedName("rockcrystalblue");
		MyCrystalTNTRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockCrystalTNTItemID)).setUnlocalizedName("rockcrystaltnt");
		MyGreenRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockGreenItemID)).setUnlocalizedName("rockgreen");
		MyBlueRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockBlueItemID)).setUnlocalizedName("rockblue");
		MyPurpleRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockPurpleItemID)).setUnlocalizedName("rockpurple");
		MySpikeyRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockSpikeyItemID)).setUnlocalizedName("rockspikey");
		MyTNTRock = (new ItemRock(BaseItemID + OreSpawnConstants.RockTNTItemID)).setUnlocalizedName("rocktnt");
		MyRayGun = (new ItemRayGun(BaseItemID + OreSpawnConstants.RayGunItemID)).setUnlocalizedName("RayGun");
		MyThunderStaff = (new ItemThunderStaff(BaseItemID + OreSpawnConstants.ThunderStaffItemID)).setUnlocalizedName("thunderstaff");
		MyWrench = (new ItemWrench(BaseItemID + OreSpawnConstants.WrenchItemID)).setUnlocalizedName("wrench");
		MyAcid = (new ItemAcid(BaseItemID + OreSpawnConstants.AcidItemID)).setUnlocalizedName("acid");
		MyIrukandji = (new ItemIrukandji(BaseItemID + OreSpawnConstants.IrukandjiItemID)).setUnlocalizedName("deadirukandji");
		MyIrukandjiArrow = (new ItemIrukandjiArrow(BaseItemID + OreSpawnConstants.IrukandjiArrowItemID)).setUnlocalizedName("irukandjiarrow");
		MyGreenFish = (new ItemGenericFish(BaseItemID + OreSpawnConstants.GreenFishItemID, 3, 0.5F, false)).setUnlocalizedName("greenfish");
		MyBlueFish = (new ItemGenericFish(BaseItemID + OreSpawnConstants.BlueFishItemID, 4, 0.4F, false)).setUnlocalizedName("bluefish");
		MyPinkFish = (new ItemGenericFish(BaseItemID + OreSpawnConstants.PinkFishItemID, 4, 0.6F, false)).setUnlocalizedName("pinkfish");
		MyRockFish = (new ItemGenericFish(BaseItemID + OreSpawnConstants.RockFishItemID, 3, 0.7F, false)).setUnlocalizedName("rockfish");
		MyWoodFish = (new ItemGenericFish(BaseItemID + OreSpawnConstants.WoodFishItemID, 5, 0.7F, false)).setUnlocalizedName("woodfish");
		MyGreyFish = (new ItemGenericFish(BaseItemID + OreSpawnConstants.GreyFishItemID, 5, 0.5F, false)).setUnlocalizedName("greyfish");
		Sifter = (new ItemSifter(BaseItemID + OreSpawnConstants.SifterItemID)).setUnlocalizedName("sifter");
		MySquidZooka = (new ItemSquidZooka(BaseItemID + OreSpawnConstants.SquidZookaItemID)).setUnlocalizedName("squidzookasmall");
		
		BerthaHandle = (new ItemSalt(BaseItemID + OreSpawnConstants.BerthaHandleItemID)).setUnlocalizedName("bbhandle");
		BerthaGuard = (new ItemSalt(BaseItemID + OreSpawnConstants.BerthaGuardItemID)).setUnlocalizedName("bbguard");
		BerthaBlade = (new ItemSalt(BaseItemID + OreSpawnConstants.BerthaBladeItemID)).setUnlocalizedName("bbblade");
		MolenoidNose = (new ItemSalt(BaseItemID + OreSpawnConstants.MolenoidNoseItemID)).setUnlocalizedName("molenoidnose");
		SeaMonsterScale = (new ItemSalt(BaseItemID + OreSpawnConstants.SeaMonsterScaleItemID)).setUnlocalizedName("seamonsterscale");
		WormTooth = (new ItemSalt(BaseItemID + OreSpawnConstants.WormToothItemID)).setUnlocalizedName("wormtooth");
		TRexTooth = (new ItemSalt(BaseItemID + OreSpawnConstants.TRexToothItemID)).setUnlocalizedName("trextooth");
		CaterKillerJaw = (new ItemSalt(BaseItemID + OreSpawnConstants.CaterKillerJawItemID)).setUnlocalizedName("caterkillerjaw");
		SeaViperTongue = (new ItemSalt(BaseItemID + OreSpawnConstants.SeaViperTongueItemID)).setUnlocalizedName("seavipertongue");
		VortexEye = (new ItemSalt(BaseItemID + OreSpawnConstants.VortexEyeItemID)).setUnlocalizedName("vortexeye");
		
		
		
		armorULTIMATE = EnumHelper.addArmorMaterial("ULTIMATE", Ultimate_armorstats.durability, new int[]{Ultimate_armorstats.head_protection, Ultimate_armorstats.chest_protection, Ultimate_armorstats.leg_protection, Ultimate_armorstats.boot_protection}, Ultimate_armorstats.enchantability);
		
		
		armorMOBZILLA = EnumHelper.addArmorMaterial("MOBZILLA", Mobzilla_armorstats.durability, new int[]{Mobzilla_armorstats.head_protection, Mobzilla_armorstats.chest_protection, Mobzilla_armorstats.leg_protection, Mobzilla_armorstats.boot_protection}, Mobzilla_armorstats.enchantability);
		
		
		armorLAVAEEL = EnumHelper.addArmorMaterial("LAVAEEL", LavaEel_armorstats.durability, new int[]{LavaEel_armorstats.head_protection, LavaEel_armorstats.chest_protection, LavaEel_armorstats.leg_protection, LavaEel_armorstats.boot_protection}, LavaEel_armorstats.enchantability);
		
		
		armorMOTHSCALE = EnumHelper.addArmorMaterial("MOTHSCALE", MothScale_armorstats.durability, new int[]{MothScale_armorstats.head_protection, MothScale_armorstats.chest_protection, MothScale_armorstats.leg_protection, MothScale_armorstats.boot_protection}, MothScale_armorstats.enchantability);
		
		
		armorEMERALD = EnumHelper.addArmorMaterial("EMERALD", Emerald_armorstats.durability, new int[]{Emerald_armorstats.head_protection, Emerald_armorstats.chest_protection, Emerald_armorstats.leg_protection, Emerald_armorstats.boot_protection}, Emerald_armorstats.enchantability);
		
		
		armorEXPERIENCE = EnumHelper.addArmorMaterial("EXPERIENCE", Experience_armorstats.durability, new int[]{Experience_armorstats.head_protection, Experience_armorstats.chest_protection, Experience_armorstats.leg_protection, Experience_armorstats.boot_protection}, Experience_armorstats.enchantability);
		
		
		armorRUBY = EnumHelper.addArmorMaterial("RUBY", Ruby_armorstats.durability, new int[]{Ruby_armorstats.head_protection, Ruby_armorstats.chest_protection, Ruby_armorstats.leg_protection, Ruby_armorstats.boot_protection}, Ruby_armorstats.enchantability);
		
		
		armorAMETHYST = EnumHelper.addArmorMaterial("AMETHYST", Amethyst_armorstats.durability, new int[]{Amethyst_armorstats.head_protection, Amethyst_armorstats.chest_protection, Amethyst_armorstats.leg_protection, Amethyst_armorstats.boot_protection}, Amethyst_armorstats.enchantability);
		
		
		armorPINK = EnumHelper.addArmorMaterial("PINK", Pink_armorstats.durability, new int[]{Pink_armorstats.head_protection, Pink_armorstats.chest_protection, Pink_armorstats.leg_protection, Pink_armorstats.boot_protection}, Pink_armorstats.enchantability);
		
		
		armorTIGERSEYE = EnumHelper.addArmorMaterial("TIGERSEYE", TigersEye_armorstats.durability, new int[]{TigersEye_armorstats.head_protection, TigersEye_armorstats.chest_protection, TigersEye_armorstats.leg_protection, TigersEye_armorstats.boot_protection}, TigersEye_armorstats.enchantability);
		
		
		armorPEACOCK = EnumHelper.addArmorMaterial("PEACOCK", Peacock_armorstats.durability, new int[]{Peacock_armorstats.head_protection, Peacock_armorstats.chest_protection, Peacock_armorstats.leg_protection, Peacock_armorstats.boot_protection}, Peacock_armorstats.enchantability);
		
		
		armorROYAL = EnumHelper.addArmorMaterial("ROYAL", Royal_armorstats.durability, new int[]{Royal_armorstats.head_protection, Royal_armorstats.chest_protection, Royal_armorstats.leg_protection, Royal_armorstats.boot_protection}, Royal_armorstats.enchantability);
		
		
		armorLAPIS = EnumHelper.addArmorMaterial("LAPIS", Lapis_armorstats.durability, new int[]{Lapis_armorstats.head_protection, Lapis_armorstats.chest_protection, Lapis_armorstats.leg_protection, Lapis_armorstats.boot_protection}, Lapis_armorstats.enchantability);
		
		
		armorQUEEN = EnumHelper.addArmorMaterial("QUEEN", Queen_armorstats.durability, new int[]{Queen_armorstats.head_protection, Queen_armorstats.chest_protection, Queen_armorstats.leg_protection, Queen_armorstats.boot_protection}, Queen_armorstats.enchantability);
		
		
		
		UltimateHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.UltimateHelmetItemID, armorULTIMATE, proxy.setArmorPrefix("ultimate"), 0)).setUnlocalizedName("ultimate_helmet");
		UltimateBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.UltimateBodyItemID, armorULTIMATE, proxy.setArmorPrefix("ultimate"), 1)).setUnlocalizedName("ultimate_chest");
		UltimateLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.UltimateLegsItemID, armorULTIMATE, proxy.setArmorPrefix("ultimate"), 2)).setUnlocalizedName("ultimate_leggings");
		UltimateBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.UltimateBootsItemID, armorULTIMATE, proxy.setArmorPrefix("ultimate"), 3)).setUnlocalizedName("ultimate_boots");
		LavaEelHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LavaEelHelmetItemID, armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 0)).setUnlocalizedName("lavaeel_helmet");
		LavaEelBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LavaEelBodyItemID, armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 1)).setUnlocalizedName("lavaeel_chest");
		LavaEelLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LavaEelLegsItemID, armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 2)).setUnlocalizedName("lavaeel_leggings");
		LavaEelBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LavaEelBootsItemID, armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 3)).setUnlocalizedName("lavaeel_boots");
		MothScaleHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MothScaleHelmetItemID, armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 0)).setUnlocalizedName("mothscale_helmet");
		MothScaleBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MothScaleBodyItemID, armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 1)).setUnlocalizedName("mothscale_chest");
		MothScaleLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MothScaleLegsItemID, armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 2)).setUnlocalizedName("mothscale_leggings");
		MothScaleBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MothScaleBootsItemID, armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 3)).setUnlocalizedName("mothscale_boots");
		EmeraldHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.EmeraldHelmetItemID, armorEMERALD, proxy.setArmorPrefix("emerald"), 0)).setUnlocalizedName("emerald_helmet");
		EmeraldBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.EmeraldBodyItemID, armorEMERALD, proxy.setArmorPrefix("emerald"), 1)).setUnlocalizedName("emerald_chest");
		EmeraldLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.EmeraldLegsItemID, armorEMERALD, proxy.setArmorPrefix("emerald"), 2)).setUnlocalizedName("emerald_leggings");
		EmeraldBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.EmeraldBootsItemID, armorEMERALD, proxy.setArmorPrefix("emerald"), 3)).setUnlocalizedName("emerald_boots");
		ExperienceHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.ExperienceHelmetItemID, armorEXPERIENCE, proxy.setArmorPrefix("experience"), 0)).setUnlocalizedName("experience_helmet");
		ExperienceBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.ExperienceBodyItemID, armorEXPERIENCE, proxy.setArmorPrefix("experience"), 1)).setUnlocalizedName("experience_chest");
		ExperienceLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.ExperienceLegsItemID, armorEXPERIENCE, proxy.setArmorPrefix("experience"), 2)).setUnlocalizedName("experience_leggings");
		ExperienceBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.ExperienceBootsItemID, armorEXPERIENCE, proxy.setArmorPrefix("experience"), 3)).setUnlocalizedName("experience_boots");
		RubyHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RubyHelmetItemID, armorRUBY, proxy.setArmorPrefix("ruby"), 0)).setUnlocalizedName("ruby_helmet");
		RubyBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RubyBodyItemID, armorRUBY, proxy.setArmorPrefix("ruby"), 1)).setUnlocalizedName("ruby_chest");
		RubyLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RubyLegsItemID, armorRUBY, proxy.setArmorPrefix("ruby"), 2)).setUnlocalizedName("ruby_leggings");
		RubyBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RubyBootsItemID, armorRUBY, proxy.setArmorPrefix("ruby"), 3)).setUnlocalizedName("ruby_boots");
		AmethystHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.AmethystHelmetItemID, armorAMETHYST, proxy.setArmorPrefix("amethyst"), 0)).setUnlocalizedName("amethyst_helmet");
		AmethystBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.AmethystBodyItemID, armorAMETHYST, proxy.setArmorPrefix("amethyst"), 1)).setUnlocalizedName("amethyst_chest");
		AmethystLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.AmethystLegsItemID, armorAMETHYST, proxy.setArmorPrefix("amethyst"), 2)).setUnlocalizedName("amethyst_leggings");
		AmethystBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.AmethystBootsItemID, armorAMETHYST, proxy.setArmorPrefix("amethyst"), 3)).setUnlocalizedName("amethyst_boots");
		CrystalPinkHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.CrystalPinkHelmetItemID, armorPINK, proxy.setArmorPrefix("pink"), 0)).setUnlocalizedName("pink_helmet");
		CrystalPinkBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.CrystalPinkBodyItemID, armorPINK, proxy.setArmorPrefix("pink"), 1)).setUnlocalizedName("pink_chest");
		CrystalPinkLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.CrystalPinkLegsItemID, armorPINK, proxy.setArmorPrefix("pink"), 2)).setUnlocalizedName("pink_leggings");
		CrystalPinkBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.CrystalPinkBootsItemID, armorPINK, proxy.setArmorPrefix("pink"), 3)).setUnlocalizedName("pink_boots");
		TigersEyeHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.TigersEyeHelmetItemID, armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 0)).setUnlocalizedName("tigerseye_helmet");
		TigersEyeBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.TigersEyeBodyItemID, armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 1)).setUnlocalizedName("tigerseye_chest");
		TigersEyeLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.TigersEyeLegsItemID, armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 2)).setUnlocalizedName("tigerseye_leggings");
		TigersEyeBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.TigersEyeBootsItemID, armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 3)).setUnlocalizedName("tigerseye_boots");
		PeacockFeatherBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.PeacockFeatherBootsItemID, armorPEACOCK, proxy.setArmorPrefix("peacock"), 3)).setUnlocalizedName("peacock_boots");
		PeacockFeatherHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.PeacockFeatherHelmetItemID, armorPEACOCK, proxy.setArmorPrefix("peacock"), 0)).setUnlocalizedName("peacock_helmet");
		PeacockFeatherBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.PeacockFeatherBodyItemID, armorPEACOCK, proxy.setArmorPrefix("peacock"), 1)).setUnlocalizedName("peacock_chest");
		PeacockFeatherLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.PeacockFeatherLegsItemID, armorPEACOCK, proxy.setArmorPrefix("peacock"), 2)).setUnlocalizedName("peacock_leggings");
		MobzillaHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MobzillaHelmetItemID, armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 0)).setUnlocalizedName("mobzilla_helmet");
		MobzillaBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MobzillaBodyItemID, armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 1)).setUnlocalizedName("mobzilla_chest");
		MobzillaLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MobzillaLegsItemID, armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 2)).setUnlocalizedName("mobzilla_leggings");
		MobzillaBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.MobzillaBootsItemID, armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 3)).setUnlocalizedName("mobzilla_boots");
		RoyalHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RoyalHelmetItemID, armorROYAL, proxy.setArmorPrefix("royal"), 0)).setUnlocalizedName("royal_helmet");
		RoyalBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RoyalBodyItemID, armorROYAL, proxy.setArmorPrefix("royal"), 1)).setUnlocalizedName("royal_chest");
		RoyalLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RoyalLegsItemID, armorROYAL, proxy.setArmorPrefix("royal"), 2)).setUnlocalizedName("royal_leggings");
		RoyalBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.RoyalBootsItemID, armorROYAL, proxy.setArmorPrefix("royal"), 3)).setUnlocalizedName("royal_boots");
		LapisHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LapisHelmetItemID, armorLAPIS, proxy.setArmorPrefix("lapis"), 0)).setUnlocalizedName("lapis_helmet");
		LapisBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LapisBodyItemID, armorLAPIS, proxy.setArmorPrefix("lapis"), 1)).setUnlocalizedName("lapis_chest");
		LapisLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LapisLegsItemID, armorLAPIS, proxy.setArmorPrefix("lapis"), 2)).setUnlocalizedName("lapis_leggings");
		LapisBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.LapisBootsItemID, armorLAPIS, proxy.setArmorPrefix("lapis"), 3)).setUnlocalizedName("lapis_boots");
		QueenHelmet = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.QueenHelmetItemID, armorQUEEN, proxy.setArmorPrefix("queen"), 0)).setUnlocalizedName("queen_helmet");
		QueenBody = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.QueenBodyItemID, armorQUEEN, proxy.setArmorPrefix("queen"), 1)).setUnlocalizedName("queen_chest");
		QueenLegs = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.QueenLegsItemID, armorQUEEN, proxy.setArmorPrefix("queen"), 2)).setUnlocalizedName("queen_leggings");
		QueenBoots = (ItemOreSpawnArmor)(new ItemOreSpawnArmor(BaseItemID + OreSpawnConstants.QueenBootsItemID, armorQUEEN, proxy.setArmorPrefix("queen"), 3)).setUnlocalizedName("queen_boots");
		
		
		
		MyOreSaltBlock = (new OreSalt(BaseBlockID + OreSpawnConstants.OreSaltBlockID)).setBlockName("oresalt");
		MySalt = (new ItemSalt(BaseItemID + OreSpawnConstants.SaltItemID)).setUnlocalizedName("salt");
		MyPopcorn = (new ItemPopcorn(BaseItemID + OreSpawnConstants.PopcornItemID, 1, 0.5F, false)).setUnlocalizedName("popcorn");
		MyButteredPopcorn = (new ItemPopcorn(BaseItemID + OreSpawnConstants.ButteredPopcornItemID, 2, 0.6F, false)).setUnlocalizedName("popcorn_buttered");
		MyButteredSaltedPopcorn = (new ItemPopcorn(BaseItemID + OreSpawnConstants.ButteredSaltedPopcornItemID, 3, 0.75F, false)).setUnlocalizedName("popcorn_buttered_salted");
		MyPopcornBag = (new ItemPopcorn(BaseItemID + OreSpawnConstants.PopcornBagItemID, 10, 1.25F, false)).setUnlocalizedName("popcorn_bag");
		MyButter = (new ItemPopcorn(BaseItemID + OreSpawnConstants.ButterItemID, 1, 0.5F, false)).setUnlocalizedName("butter");
		MyCornDog = (new ItemPopcorn(BaseItemID + OreSpawnConstants.CornDogItemID, 16, 2.5F, false)).setUnlocalizedName("corndog_cooked");
		MyRawCornDog = (new ItemPopcorn(BaseItemID + OreSpawnConstants.RawCornDogItemID, 4, 0.6F, false)).setUnlocalizedName("corndog_raw");
		MyButterCandy = (new ItemSunFish(BaseItemID + OreSpawnConstants.ButterCandyItemID, 4, 0.5F, false)).setUnlocalizedName("buttercandy");
		MyBacon = (new ItemSunFish(BaseItemID + OreSpawnConstants.BaconItemID, 14, 1.5F, false)).setUnlocalizedName("cookedbacon");
		MyRawBacon = (new ItemPopcorn(BaseItemID + OreSpawnConstants.RawBaconItemID, 8, 1.0F, false)).setUnlocalizedName("bacon");
		MyCrabMeat = (new ItemSunFish(BaseItemID + OreSpawnConstants.CrabMeatItemID, 6, 0.75F, false)).setUnlocalizedName("cookedcrabmeat");
		MyRawCrabMeat = (new ItemPopcorn(BaseItemID + OreSpawnConstants.RawCrabMeatItemID, 4, 0.25F, false)).setUnlocalizedName("crabmeat");
		MyCheese = (new ItemPopcorn(BaseItemID + OreSpawnConstants.CheeseItemID, 4, 0.5F, false)).setUnlocalizedName("cheese");
		MySalad = (new ItemPopcorn(BaseItemID + OreSpawnConstants.SaladItemID, 10, 0.95F, false)).setUnlocalizedName("salad");
		MyBLT = (new ItemPopcorn(BaseItemID + OreSpawnConstants.BLTItemID, 12, 0.95F, false)).setUnlocalizedName("blt_sandwich");
		MyCrabbyPatty = (new ItemPopcorn(BaseItemID + OreSpawnConstants.CrabbyPattyItemID, 16, 2.35F, false)).setUnlocalizedName("crabbypatty");
		MyOreRubyBlock = (new OreRuby(BaseBlockID + OreSpawnConstants.OreRubyBlockID)).setBlockName("oreruby");
		MyRuby = (new ItemSalt(BaseItemID + OreSpawnConstants.RubyItemID)).setUnlocalizedName("ruby");
		MyOreAmethystBlock = (new OreAmethyst(BaseBlockID + OreSpawnConstants.OreAmethystBlockID)).setBlockName("oreamethyst");
		MyAmethyst = (new ItemSalt(BaseItemID + OreSpawnConstants.AmethystItemID)).setUnlocalizedName("amethyst");
		UraniumNugget = (new ItemSalt(BaseItemID + OreSpawnConstants.UraniumNuggetItemID)).setUnlocalizedName("uranium_nugget");
		TitaniumNugget = (new ItemSalt(BaseItemID + OreSpawnConstants.TitaniumNuggetItemID)).setUnlocalizedName("titanium_nugget");
		CrystalStone = (new OreBasicStone(BaseBlockID + OreSpawnConstants.CrystalStoneBlockID, 2.0F, 10.0F)).setBlockName("crystalstone");
		CrystalCoal = (new OreCrystal(BaseBlockID + OreSpawnConstants.CrystalCoalBlockID, 0.6F, 6.0F, 20.0F)).setBlockName("crystalcoal");
		CrystalGrass = (new CrystalGrass(BaseBlockID + OreSpawnConstants.CrystalGrassBlockID, 0.6F, 2.0F)).setBlockName("crystalgrass");
		CrystalCrystal = (new OreCrystalCrystal(BaseBlockID + OreSpawnConstants.CrystalCrystalBlockID, 0.4F, 12.0F, 40.0F)).setBlockName("crystalcrystal");
		TigersEye = (new OreCrystalCrystal(BaseBlockID + OreSpawnConstants.TigersEyeBlockID, 0.5F, 15.0F, 60.0F)).setBlockName("tigerseye");
		CrystalPlanksBlock = (new CrystalWood(BaseBlockID + OreSpawnConstants.CrystalPlanksBlockID, 1.5F, 4.0F)).setBlockName("crystalplanks");
		CrystalWorkbenchBlock = (new CrystalWorkbench(BaseBlockID + OreSpawnConstants.CrystalWorkbenchBlockID, 1.0F, 5.0F)).setBlockName("crystalworkbench");
		CrystalFurnaceBlock =  (CrystalFurnace)((new CrystalFurnace(BaseBlockID + OreSpawnConstants.CrystalFurnaceBlockID, false, 2.0F, 10.0F)).setBlockName("crystalfurnace"));
		CrystalFurnaceOnBlock = (new CrystalFurnace(BaseBlockID + OreSpawnConstants.CrystalFurnaceBlockOnID, true, 2.0F, 10.0F)).setBlockName("crystalfurnace");
		MyPeacock = (new ItemPopcorn(BaseItemID + OreSpawnConstants.PeacockItemID, 12, 1.4F, false)).setUnlocalizedName("cookedpeacock");
		MyRawPeacock = (new ItemPopcorn(BaseItemID + OreSpawnConstants.RawPeacockItemID, 6, 0.7F, false)).setUnlocalizedName("rawpeacock");
		CrystalRat = (new OreBasicStone(BaseBlockID + OreSpawnConstants.CrystalRatBlockID, 2.5F, 14.0F)).setBlockName("crystalrat");
		CrystalFairy = (new OreBasicStone(BaseBlockID + OreSpawnConstants.CrystalFairyBlockID, 2.5F, 14.0F)).setBlockName("crystalfairy");
		RedAntTroll = (new OreBasicStone(BaseBlockID + OreSpawnConstants.RedAntTrollBlockID, 2.5F, 14.0F)).setBlockName("redanttroll");
		TermiteTroll = (new OreBasicStone(BaseBlockID + OreSpawnConstants.TermiteTrollBlockID, 2.5F, 14.0F)).setBlockName("termitetroll");
		
		
		MyRTPBlock = (new RTPBlock(BaseBlockID + OreSpawnConstants.RTPBlockID)).setBlockName("blockteleport").setStepSound(Block.soundTypeStone);
		MyStepUp = (new StepUp(BaseItemID + OreSpawnConstants.StepUpItemID)).setUnlocalizedName("step_up");
		MyStepDown = (new StepDown(BaseItemID + OreSpawnConstants.StepDownItemID)).setUnlocalizedName("step_down");
		MyStepAccross = (new StepAccross(BaseItemID + OreSpawnConstants.StepAccrossItemID)).setUnlocalizedName("step_accross");
		MyMoleDirtBlock = (new MoleDirtBlock(BaseBlockID + OreSpawnConstants.MoleDirtBlockID)).setHardness(0.6F).setBlockName("moledirt").setStepSound(new Block.SoundType("gravel", 1.0F, 1.0F));
		
		
		this.initializeCagesAndEggs();
		
		
		MyStrawberry = (new ItemStrawberry(BaseItemID + OreSpawnConstants.StrawberryItemID, 2, 0.65F, false)).setUnlocalizedName("strawberry");
		MyStrawberryPlant = new BlockStrawberry(BaseBlockID + OreSpawnConstants.StrawberryPlantBlockID);
		MyStrawberrySeed = (new ItemStrawberrySeed(BaseItemID + OreSpawnConstants.StrawberrySeedItemID, MyStrawberryPlant, Blocks.farmland)).setUnlocalizedName("strawberry_seed");
		MyButterflyPlant = new BlockButterflyPlant(BaseBlockID + OreSpawnConstants.ButterflyPlantBlockID);
		MyButterflySeed = (new ItemButterflySeed(BaseItemID + OreSpawnConstants.ButterflySeedItemID, MyButterflyPlant, Blocks.farmland)).setUnlocalizedName("butterfly_seed");
		MyMothPlant = new BlockMothPlant(BaseBlockID + OreSpawnConstants.MothPlantBlockID);
		MyMothSeed = (new ItemMothSeed(BaseItemID + OreSpawnConstants.MothSeedItemID, MyMothPlant, Blocks.farmland)).setUnlocalizedName("moth_seed");
		MyMosquitoPlant = new BlockMosquitoPlant(BaseBlockID + OreSpawnConstants.MosquitoPlantBlockID);
		MyMosquitoSeed = (new ItemMosquitoSeed(BaseItemID + OreSpawnConstants.MosquitoSeedItemID, MyMosquitoPlant, Blocks.farmland)).setUnlocalizedName("mosquito_seed");
		MyFireflyPlant = new BlockFireflyPlant(BaseBlockID + OreSpawnConstants.FireflyPlantBlockID);
		MyFireflySeed = (new ItemFireflySeed(BaseItemID + OreSpawnConstants.FireflySeedItemID, MyFireflyPlant, Blocks.farmland)).setUnlocalizedName("firefly_seed");
		MyRadishPlant = new BlockRadish(BaseBlockID + OreSpawnConstants.RadishPlantBlockID);
		MyRadish = (new ItemRadish(BaseItemID + OreSpawnConstants.RadishItemID, 2, 0.45F, MyRadishPlant, Blocks.farmland)).setUnlocalizedName("radish");
		MyCherry = (new ItemStrawberry(BaseItemID + OreSpawnConstants.CherryItemID, 3, 0.45F, false)).setUnlocalizedName("cherries");
		MyPeach = (new ItemStrawberry(BaseItemID + OreSpawnConstants.PeachItemID, 4, 0.55F, false)).setUnlocalizedName("peach");
		MyCrystalApple = (new ItemSunFish(BaseItemID + OreSpawnConstants.CrystalAppleItemID, 5, 0.85F, false)).setUnlocalizedName("crystalapple");
		MyLove = (new ItemSunFish(BaseItemID + OreSpawnConstants.LoveItemID, 8, 0.95F, false)).setUnlocalizedName("heart");
		MyRicePlant = new BlockRice(BaseBlockID + OreSpawnConstants.RicePlantBlockID);
		MyRice = (new ItemRadish(BaseItemID + OreSpawnConstants.RiceItemID, 5, 0.65F, MyRicePlant, CrystalGrass)).setUnlocalizedName("rice");
		
		MyElevator = (new ItemElevator(BaseItemID + OreSpawnConstants.ElevatorItemID)).setUnlocalizedName("elevator");
		
		MyCornPlant1 = (new BlockCorn(BaseBlockID + OreSpawnConstants.CornPlant1BlockID)).setBlockName("corn_0");
		MyCornPlant2 = (new BlockCorn(BaseBlockID + OreSpawnConstants.CornPlant2BlockID)).setBlockName("corn_1");
		MyCornPlant3 = (new BlockCorn(BaseBlockID + OreSpawnConstants.CornPlant3BlockID)).setBlockName("corn_2");
		MyCornPlant4 = (new BlockCorn(BaseBlockID + OreSpawnConstants.CornPlant4BlockID)).setBlockName("corn_3");
		MyCornCob = (new ItemCornCob(BaseItemID + OreSpawnConstants.CornCobItemID, 6, 0.75F, MyCornPlant1, Blocks.farmland)).setUnlocalizedName("corn_seed");
		MyQuinoaPlant1 = (new BlockQuinoa(BaseBlockID + OreSpawnConstants.QuinoaPlant1BlockID)).setBlockName("quinoa_0");
		MyQuinoaPlant2 = (new BlockQuinoa(BaseBlockID + OreSpawnConstants.QuinoaPlant2BlockID)).setBlockName("quinoa_1");
		MyQuinoaPlant3 = (new BlockQuinoa(BaseBlockID + OreSpawnConstants.QuinoaPlant3BlockID)).setBlockName("quinoa_2");
		MyQuinoaPlant4 = (new BlockQuinoa(BaseBlockID + OreSpawnConstants.QuinoaPlant4BlockID)).setBlockName("quinoa_3");
		MyQuinoa = (new ItemCornCob(BaseItemID + OreSpawnConstants.QuinoaItemID, 7, 0.85F, MyQuinoaPlant1, CrystalGrass)).setUnlocalizedName("quinoa");
		
		MyTomatoPlant1 = (new BlockTomato(BaseBlockID + OreSpawnConstants.TomatoPlant1BlockID)).setBlockName("tomato_0");
		MyTomatoPlant2 = (new BlockTomato(BaseBlockID + OreSpawnConstants.TomatoPlant2BlockID)).setBlockName("tomato_1");
		MyTomatoPlant3 = (new BlockTomato(BaseBlockID + OreSpawnConstants.TomatoPlant3BlockID)).setBlockName("tomato_2");
		MyTomatoPlant4 = (new BlockTomato(BaseBlockID + OreSpawnConstants.TomatoPlant4BlockID)).setBlockName("tomato_3");
		MyTomato = (new ItemTomato(BaseItemID + OreSpawnConstants.TomatoItemID, 4, 0.55F, MyTomatoPlant1, Blocks.farmland)).setUnlocalizedName("tomato_seed");
		MyLettucePlant1 = (new BlockLettuce(BaseBlockID + OreSpawnConstants.LettucePlant1BlockID)).setBlockName("lettuce_0");
		MyLettucePlant2 = (new BlockLettuce(BaseBlockID + OreSpawnConstants.LettucePlant2BlockID)).setBlockName("lettuce_1");
		MyLettucePlant3 = (new BlockLettuce(BaseBlockID + OreSpawnConstants.LettucePlant3BlockID)).setBlockName("lettuce_2");
		MyLettucePlant4 = (new BlockLettuce(BaseBlockID + OreSpawnConstants.LettucePlant4BlockID)).setBlockName("lettuce_3");
		MyLettuce = (new ItemLettuce(BaseItemID + OreSpawnConstants.LettuceItemID, 3, 0.45F, MyLettucePlant1, Blocks.farmland)).setUnlocalizedName("lettuce_seed");
		
		MagicApple = (new ItemMagicApple(BaseItemID + OreSpawnConstants.MagicAppleItemID)).setUnlocalizedName("magicapple");
		MinersDream = (new ItemMinersDream(BaseItemID + OreSpawnConstants.MinersDreamItemID)).setUnlocalizedName("minersdream");
		ExtremeTorch = (new BlockExtremeTorch(BaseBlockID + OreSpawnConstants.ExtremeTorchBlockID)).setLightLevel(1.0F).setBlockName("extremetorch");
		KrakenRepellent = (new KrakenRepellent(BaseBlockID + OreSpawnConstants.KrakenRepellentBlockID)).setLightLevel(0.8F).setBlockName("krakenrepellent");
		MyIslandBlock = (new IslandBlock(BaseBlockID + OreSpawnConstants.IslandBlockID)).setLightLevel(0.9F).setBlockName("island");
		CreeperRepellent = (new CreeperRepellent(BaseBlockID + OreSpawnConstants.CreeperRepellentBlockID)).setLightLevel(0.8F).setBlockName("creeperrepellent");
		ZooCage2 = (new ZooCage(BaseItemID + OreSpawnConstants.ZooCage2ItemID, 3)).setUnlocalizedName("zoo2");
		ZooCage4 = (new ZooCage(BaseItemID + OreSpawnConstants.ZooCage4ItemID, 5)).setUnlocalizedName("zoo4");
		ZooCage6 = (new ZooCage(BaseItemID + OreSpawnConstants.ZooCage6ItemID, 9)).setUnlocalizedName("zoo6");
		ZooCage8 = (new ZooCage(BaseItemID + OreSpawnConstants.ZooCage8ItemID, 13)).setUnlocalizedName("zoo8");
		ZooCage10 = (new ZooCage(BaseItemID + OreSpawnConstants.ZooCage10ItemID, 17)).setUnlocalizedName("zoo10");
		InstantShelter = (new InstantShelter(BaseItemID + OreSpawnConstants.InstantShelterItemID)).setUnlocalizedName("instantshelter");
		InstantGarden = (new InstantGarden(BaseItemID + OreSpawnConstants.InstantGardenItemID)).setUnlocalizedName("instantgarden");
		CrystalTorch = (new BlockCrystalTorch(BaseBlockID + OreSpawnConstants.CrystalTorchBlockID)).setLightLevel(0.99F).setBlockName("crystaltorch");
		MyKingSpawnerBlock = (new KingSpawnerBlock(BaseBlockID + OreSpawnConstants.KingSpawnerBlockID)).setLightLevel(0.9F).setBlockName("kingspawner");
		MyQueenSpawnerBlock = (new QueenSpawnerBlock(BaseBlockID + OreSpawnConstants.QueenSpawnerBlockID)).setLightLevel(0.9F).setBlockName("queenspawner");
		RandomDungeon = (new ItemRandomDungeon(BaseItemID + OreSpawnConstants.RandomDungeonItemID)).setUnlocalizedName("randomdungeon");
		MyDungeonSpawnerBlock = (new DungeonSpawnerBlock(BaseBlockID + OreSpawnConstants.DungeonSpawnerBlockID)).setLightLevel(0.9F).setBlockName("dungeonspawner");
		
		
		MyAppleLeaves = (BlockAppleLeaves)(new BlockAppleLeaves(BaseBlockID + OreSpawnConstants.AppleLeavesBlockID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("leaves_apple");
		MyAppleSeed = (new ItemAppleSeed(BaseItemID + OreSpawnConstants.AppleSeedItemID)).setUnlocalizedName("appletree_seed");
		MySkyTreeLog = (BlockSkyTreeLog)(new BlockSkyTreeLog(BaseBlockID + OreSpawnConstants.SkyTreeLogBlockID, 20)).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("skytreelog");
		
		
		MyDT = (BlockDuplicatorLog)(new BlockDuplicatorLog(BaseBlockID + OreSpawnConstants.DuplicatorLogBlockID)).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("duplicatortreelog");
		MyExperienceLeaves = (BlockExperienceLeaves)(new BlockExperienceLeaves(BaseBlockID + OreSpawnConstants.ExperienceLeavesBlockID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("leaves_experience");
		MyExperienceCatcher = (new ExperienceCatcher(BaseItemID + OreSpawnConstants.ExperienceCatcherItemID)).setUnlocalizedName("experiencecatcher");
		MyExperienceTreeSeed = (new ItemExperienceTreeSeed(BaseItemID + OreSpawnConstants.ExperienceTreeSeedItemID)).setUnlocalizedName("experiencetree_seed");
		MyExperiencePlant = (new BlockExperiencePlant(BaseBlockID + OreSpawnConstants.ExperiencePlantBlockID)).setBlockName("experiencesapling");
		MyDeadStinkBug = (new ItemSalt(BaseItemID + OreSpawnConstants.DeadStinkBugItemID)).setUnlocalizedName("deadstinkbug");
		MyFlowerPinkBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.FlowerPinkID)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("flower_pink");
		MyFlowerBlueBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.FlowerBlueID)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("flower_blue");
		MyFlowerBlackBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.FlowerBlackID)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("flower_black");
		MyFlowerScaryBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.FlowerScaryID)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("flower_scary");
		MyScaryLeaves = (BlockScaryLeaves)(new BlockScaryLeaves(BaseBlockID + OreSpawnConstants.LeavesScaryID)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("leaves_scary");
		MyCherryLeaves = (BlockScaryLeaves)(new BlockScaryLeaves(BaseBlockID + OreSpawnConstants.LeavesCherryID)).setHardness(0.15F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("leaves_cherry");
		MyPeachLeaves = (BlockScaryLeaves)(new BlockScaryLeaves(BaseBlockID + OreSpawnConstants.LeavesPeachID)).setHardness(0.15F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("leaves_peach");
		MyCherrySeed = (new ItemAppleSeed(BaseItemID + OreSpawnConstants.CherrySeedItemID)).setUnlocalizedName("cherrytree_seed");
		MyPeachSeed = (new ItemAppleSeed(BaseItemID + OreSpawnConstants.PeachSeedItemID)).setUnlocalizedName("peachtree_seed");
		CrystalFlowerRedBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.CrystalFlowerRedBlockID)).setHardness(0.0F).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystalflower_red");
		CrystalFlowerGreenBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.CrystalFlowerGreenBlockID)).setHardness(0.0F).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystalflower_green");
		CrystalFlowerBlueBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.CrystalFlowerBlueBlockID)).setHardness(0.0F).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystalflower_blue");
		CrystalFlowerYellowBlock = (MyBlockFlower)(new MyBlockFlower(BaseBlockID + OreSpawnConstants.CrystalFlowerYellowBlockID)).setHardness(0.0F).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystalflower_yellow");
		MyCrystalLeaves = (BlockCrystalLeaves)(new BlockCrystalLeaves(BaseBlockID + OreSpawnConstants.CrystalTreeLeavesBlockID)).setHardness(0.2F).setLightOpacity(1).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystaltreeleaves");
		MyCrystalTreeLog = (BlockCrystalTreeLog)(new BlockCrystalTreeLog(BaseBlockID + OreSpawnConstants.CrystalTreeLogBlockID, 20)).setHardness(0.2F).setStepSound(new Block.SoundType("wood", 1.0F, 1.0F)).setBlockName("crystaltreelog");
		MyCrystalLeaves2 = (BlockCrystalLeaves)(new BlockCrystalLeaves(BaseBlockID + OreSpawnConstants.CrystalTreeLeaves2BlockID)).setHardness(0.25F).setLightOpacity(1).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystaltreeleaves2");
		MyCrystalLeaves3 = (BlockCrystalLeaves)(new BlockCrystalLeaves(BaseBlockID + OreSpawnConstants.CrystalTreeLeaves3BlockID)).setHardness(0.25F).setLightOpacity(1).setStepSound(new Block.SoundType("grass", 1.0F, 1.0F)).setBlockName("crystaltreeleaves3");
		MyCrystalPlant = (new BlockCrystalPlant(BaseBlockID + OreSpawnConstants.CrystalPlantBlockID)).setBlockName("crystalsapling");
		MyCrystalPlant2 = (new BlockCrystalPlant(BaseBlockID + OreSpawnConstants.CrystalPlant2BlockID)).setBlockName("crystalsapling2");
		MyCrystalPlant3 = (new BlockCrystalPlant(BaseBlockID + OreSpawnConstants.CrystalPlant3BlockID)).setBlockName("crystalsapling3");
		
		MyEnderPearlBlock = (new OreGenericEgg(BaseBlockID + OreSpawnConstants.EnderPearlBlockID)).setBlockName("blockenderpearl");
		MyEyeOfEnderBlock = (new OreGenericEgg(BaseBlockID + OreSpawnConstants.EyeOfEnderBlockID)).setBlockName("blockeyeofender");
		
		
		
		this.make_some_more_things();
	}

	
	
	private void make_some_more_things() {
		GameRegistry.registerBlock(MySpiderSpawnBlock, "OreSpawn_SpiderSpawnBlock");
		GameRegistry.registerBlock(MyBatSpawnBlock, "OreSpawn_BatSpawnBlock");
		GameRegistry.registerBlock(MyCowSpawnBlock, "OreSpawn_CowSpawnBlock");
		GameRegistry.registerBlock(MyPigSpawnBlock, "OreSpawn_PigSpawnBlock");
		GameRegistry.registerBlock(MySquidSpawnBlock, "OreSpawn_SquidSpawnBlock");
		GameRegistry.registerBlock(MyChickenSpawnBlock, "OreSpawn_ChickenSpawnBlock");
		GameRegistry.registerBlock(MyCreeperSpawnBlock, "OreSpawn_CreeperSpawnBlock");
		GameRegistry.registerBlock(MySkeletonSpawnBlock, "OreSpawn_SkeletonSpawnBlock");
		GameRegistry.registerBlock(MyZombieSpawnBlock, "OreSpawn_ZombieSpawnBlock");
		GameRegistry.registerBlock(MySlimeSpawnBlock, "OreSpawn_SlimeSpawnBlock");
		GameRegistry.registerBlock(MyGhastSpawnBlock, "OreSpawn_GhastSpawnBlock");
		GameRegistry.registerBlock(MyZombiePigmanSpawnBlock, "OreSpawn_ZombiePigmanSpawnBlock");
		GameRegistry.registerBlock(MyEndermanSpawnBlock, "OreSpawn_EndermanSpawnBlock");
		GameRegistry.registerBlock(MyCaveSpiderSpawnBlock, "OreSpawn_CaveSpiderSpawnBlock");
		GameRegistry.registerBlock(MySilverfishSpawnBlock, "OreSpawn_SilverfishSpawnBlock");
		GameRegistry.registerBlock(MyMagmaCubeSpawnBlock, "OreSpawn_MagmaCubeSpawnBlock");
		GameRegistry.registerBlock(MyWitchSpawnBlock, "OreSpawn_WitchSpawnBlock");
		GameRegistry.registerBlock(MySheepSpawnBlock, "OreSpawn_SheepSpawnBlock");
		GameRegistry.registerBlock(MyWolfSpawnBlock, "OreSpawn_WolfSpawnBlock");
		GameRegistry.registerBlock(MyMooshroomSpawnBlock, "OreSpawn_MooshroomSpawnBlock");
		GameRegistry.registerBlock(MyWitherBossSpawnBlock, "OreSpawn_WitherBossSpawnBlock");
		GameRegistry.registerBlock(MyGirlfriendSpawnBlock, "OreSpawn_GirlfriendSpawnBlock");
		GameRegistry.registerBlock(MyBoyfriendSpawnBlock, "OreSpawn_BoyfriendSpawnBlock");
		GameRegistry.registerBlock(MyRedCowSpawnBlock, "OreSpawn_RedCowSpawnBlock");
		GameRegistry.registerBlock(MyCrystalCowSpawnBlock, "OreSpawn_CrystalCowSpawnBlock");
		GameRegistry.registerBlock(MyVillagerSpawnBlock, "OreSpawn_VillagerSpawnBlock");
		GameRegistry.registerBlock(MyGoldCowSpawnBlock, "OreSpawn_GoldCowSpawnBlock");
		GameRegistry.registerBlock(MyEnchantedCowSpawnBlock, "OreSpawn_EnchantedCowSpawnBlock");
		GameRegistry.registerBlock(MyMOTHRASpawnBlock, "OreSpawn_MOTHRASpawnBlock");
		GameRegistry.registerBlock(MyAloSpawnBlock, "OreSpawn_AloSpawnBlock");
		GameRegistry.registerBlock(MyCryoSpawnBlock, "OreSpawn_CryoSpawnBlock");
		GameRegistry.registerBlock(MyCamaSpawnBlock, "OreSpawn_CamaSpawnBlock");
		GameRegistry.registerBlock(MyVeloSpawnBlock, "OreSpawn_VeloSpawnBlock");
		GameRegistry.registerBlock(MyHydroSpawnBlock, "OreSpawn_HydroSpawnBlock");
		GameRegistry.registerBlock(MyBasilSpawnBlock, "OreSpawn_BasilSpawnBlock");
		GameRegistry.registerBlock(MyDragonflySpawnBlock, "OreSpawn_DragonflySpawnBlock");
		GameRegistry.registerBlock(MyEmperorScorpionSpawnBlock, "OreSpawn_EmperorScorpionSpawnBlock");
		GameRegistry.registerBlock(MyScorpionSpawnBlock, "OreSpawn_ScorpionSpawnBlock");
		GameRegistry.registerBlock(MyCaveFisherSpawnBlock, "OreSpawn_CaveFisherSpawnBlock");
		GameRegistry.registerBlock(MySpyroSpawnBlock, "OreSpawn_SpyroSpawnBlock");
		GameRegistry.registerBlock(MyBaryonyxSpawnBlock, "OreSpawn_BaryonyxSpawnBlock");
		GameRegistry.registerBlock(MyGammaMetroidSpawnBlock, "OreSpawn_GammaMetroidSpawnBlock");
		GameRegistry.registerBlock(MyCockateilSpawnBlock, "OreSpawn_CockateilSpawnBlock");
		GameRegistry.registerBlock(MyKyuubiSpawnBlock, "OreSpawn_KyuubiSpawnBlock");
		GameRegistry.registerBlock(MyAlienSpawnBlock, "OreSpawn_AlienSpawnBlock");
		GameRegistry.registerBlock(MyIronGolemSpawnBlock, "OreSpawn_IronGolemSpawnBlock");
		GameRegistry.registerBlock(MySnowGolemSpawnBlock, "OreSpawn_SnowGolemSpawnBlock");
		GameRegistry.registerBlock(MyEnderDragonSpawnBlock, "OreSpawn_EnderDragonSpawnBlock");
		GameRegistry.registerBlock(MyOcelotSpawnBlock, "OreSpawn_OcelotSpawnBlock");
		GameRegistry.registerBlock(MyWitherSkeletonSpawnBlock, "OreSpawn_WitherSkeletonSpawnBlock");
		GameRegistry.registerBlock(MyBlazeSpawnBlock, "OreSpawn_BlazeSpawnBlock");
		GameRegistry.registerBlock(MyAttackSquidSpawnBlock, "OreSpawn_AttackSquidSpawnBlock");
		GameRegistry.registerBlock(MyWaterDragonSpawnBlock, "OreSpawn_WaterDragonSpawnBlock");
		GameRegistry.registerBlock(MyCephadromeSpawnBlock, "OreSpawn_CephadromeSpawnBlock");
		GameRegistry.registerBlock(MyKrakenSpawnBlock, "OreSpawn_KrakenSpawnBlock");
		GameRegistry.registerBlock(MyLizardSpawnBlock, "OreSpawn_LizardSpawnBlock");
		GameRegistry.registerBlock(MyDragonSpawnBlock, "OreSpawn_DragonSpawnBlock");
		GameRegistry.registerBlock(MyBeeSpawnBlock, "OreSpawn_BeeSpawnBlock");
		GameRegistry.registerBlock(MyHorseSpawnBlock, "OreSpawn_HorseSpawnBlock");
		GameRegistry.registerBlock(MyTrooperBugSpawnBlock, "OreSpawn_TrooperBugSpawnBlock");
		GameRegistry.registerBlock(MySpitBugSpawnBlock, "OreSpawn_SpitBugSpawnBlock");
		GameRegistry.registerBlock(MyStinkBugSpawnBlock, "OreSpawn_StinkBugSpawnBlock");
		GameRegistry.registerBlock(MyOstrichSpawnBlock, "OreSpawn_OstrichSpawnBlock");
		GameRegistry.registerBlock(MyGazelleSpawnBlock, "OreSpawn_GazelleSpawnBlock");
		GameRegistry.registerBlock(MyChipmunkSpawnBlock, "OreSpawn_ChipmunkSpawnBlock");
		GameRegistry.registerBlock(MyCreepingHorrorSpawnBlock, "OreSpawn_CreepingHorrorSpawnBlock");
		GameRegistry.registerBlock(MyTerribleTerrorSpawnBlock, "OreSpawn_TerribleTerrorSpawnBlock");
		GameRegistry.registerBlock(MyCliffRacerSpawnBlock, "OreSpawn_CliffRacerSpawnBlock");
		GameRegistry.registerBlock(MyTriffidSpawnBlock, "OreSpawn_TriffidSpawnBlock");
		GameRegistry.registerBlock(MyPitchBlackSpawnBlock, "OreSpawn_PitchBlackSpawnBlock");
		GameRegistry.registerBlock(MyLurkingTerrorSpawnBlock, "OreSpawn_LurkingTerrorSpawnBlock");
		GameRegistry.registerBlock(MyGodzillaPartSpawnBlock, "OreSpawn_GodzillaPartSpawnBlock");
		GameRegistry.registerBlock(MyGodzillaSpawnBlock, "OreSpawn_GodzillaSpawnBlock");
		GameRegistry.registerBlock(MyTheKingPartSpawnBlock, "OreSpawn_TheKingPartSpawnBlock");
		GameRegistry.registerBlock(MyTheKingSpawnBlock, "OreSpawn_TheKingSpawnBlock");
		GameRegistry.registerBlock(MyTheQueenPartSpawnBlock, "OreSpawn_TheQueenPartSpawnBlock");
		GameRegistry.registerBlock(MyTheQueenSpawnBlock, "OreSpawn_TheQueenSpawnBlock");
		GameRegistry.registerBlock(MySmallWormSpawnBlock, "OreSpawn_SmallWormSpawnBlock");
		GameRegistry.registerBlock(MyMediumWormSpawnBlock, "OreSpawn_MediumWormSpawnBlock");
		GameRegistry.registerBlock(MyLargeWormSpawnBlock, "OreSpawn_LargeWormSpawnBlock");
		GameRegistry.registerBlock(MyCassowarySpawnBlock, "OreSpawn_CassowarySpawnBlock");
		GameRegistry.registerBlock(MyCloudSharkSpawnBlock, "OreSpawn_CloudSharkSpawnBlock");
		GameRegistry.registerBlock(MyGoldFishSpawnBlock, "OreSpawn_GoldFishSpawnBlock");
		GameRegistry.registerBlock(MyLeafMonsterSpawnBlock, "OreSpawn_LeafMonsterSpawnBlock");
		GameRegistry.registerBlock(MyTshirtSpawnBlock, "OreSpawn_TshirtSpawnBlock");
		GameRegistry.registerBlock(MyEnderKnightSpawnBlock, "OreSpawn_EnderKnightSpawnBlock");
		GameRegistry.registerBlock(MyEnderReaperSpawnBlock, "OreSpawn_EnderReaperSpawnBlock");
		GameRegistry.registerBlock(MyBeaverSpawnBlock, "OreSpawn_BeaverSpawnBlock");
		GameRegistry.registerBlock(MyUrchinSpawnBlock, "OreSpawn_UrchinSpawnBlock");
		GameRegistry.registerBlock(MyFlounderSpawnBlock, "OreSpawn_FlounderSpawnBlock");
		GameRegistry.registerBlock(MySkateSpawnBlock, "OreSpawn_SkateSpawnBlock");
		GameRegistry.registerBlock(MyRotatorSpawnBlock, "OreSpawn_RotatorSpawnBlock");
		GameRegistry.registerBlock(MyPeacockSpawnBlock, "OreSpawn_PeacockSpawnBlock");
		GameRegistry.registerBlock(MyFairySpawnBlock, "OreSpawn_FairySpawnBlock");
		GameRegistry.registerBlock(MyDungeonBeastSpawnBlock, "OreSpawn_DungeonBeastSpawnBlock");
		GameRegistry.registerBlock(MyVortexSpawnBlock, "OreSpawn_VortexSpawnBlock");
		GameRegistry.registerBlock(MyRatSpawnBlock, "OreSpawn_RatSpawnBlock");
		GameRegistry.registerBlock(MyWhaleSpawnBlock, "OreSpawn_WhaleSpawnBlock");
		GameRegistry.registerBlock(MyIrukandjiSpawnBlock, "OreSpawn_IrukandjiSpawnBlock");
		GameRegistry.registerBlock(MyTRexSpawnBlock, "OreSpawn_TRexSpawnBlock");
		GameRegistry.registerBlock(MyHerculesSpawnBlock, "OreSpawn_HerculesSpawnBlock");
		GameRegistry.registerBlock(MyMantisSpawnBlock, "OreSpawn_MantisSpawnBlock");
		GameRegistry.registerBlock(MyStinkySpawnBlock, "OreSpawn_StinkySpawnBlock");
		GameRegistry.registerBlock(MyEasterBunnySpawnBlock, "OreSpawn_EasterBunnySpawnBlock");
		GameRegistry.registerBlock(MyCaterKillerSpawnBlock, "OreSpawn_CaterKillerSpawnBlock");
		GameRegistry.registerBlock(MyMolenoidSpawnBlock, "OreSpawn_MolenoidSpawnBlock");
		GameRegistry.registerBlock(MySeaMonsterSpawnBlock, "OreSpawn_SeaMonsterSpawnBlock");
		GameRegistry.registerBlock(MySeaViperSpawnBlock, "OreSpawn_SeaViperSpawnBlock");
		GameRegistry.registerBlock(MyLeonSpawnBlock, "OreSpawn_LeonSpawnBlock");
		GameRegistry.registerBlock(MyHammerheadSpawnBlock, "OreSpawn_HammerheadSpawnBlock");
		GameRegistry.registerBlock(MyRubberDuckySpawnBlock, "OreSpawn_RubberDuckySpawnBlock");
		GameRegistry.registerBlock(MyCriminalSpawnBlock, "OreSpawn_CriminalSpawnBlock");
		GameRegistry.registerBlock(MyBrutalflySpawnBlock, "OreSpawn_BrutalflySpawnBlock");
		GameRegistry.registerBlock(MyNastysaurusSpawnBlock, "OreSpawn_NastysaurusSpawnBlock");
		GameRegistry.registerBlock(MyPointysaurusSpawnBlock, "OreSpawn_PointysaurusSpawnBlock");
		GameRegistry.registerBlock(MyCricketSpawnBlock, "OreSpawn_CricketSpawnBlock");
		GameRegistry.registerBlock(MyFrogSpawnBlock, "OreSpawn_FrogSpawnBlock");
		GameRegistry.registerBlock(MySpiderDriverSpawnBlock, "OreSpawn_SpiderDriverSpawnBlock");
		GameRegistry.registerBlock(MyCrabSpawnBlock, "OreSpawn_CrabSpawnBlock");
		
		GameRegistry.registerBlock(MyOreSaltBlock, "OreSpawn_OreSaltBlock");
		GameRegistry.registerBlock(MyRTPBlock, "OreSpawn_RTPBlock");
		GameRegistry.registerBlock(MyMoleDirtBlock, "OreSpawn_MoleDirtBlock");
		GameRegistry.registerBlock(MyOreTitaniumBlock, "OreSpawn_OreTitaniumBlock");
		GameRegistry.registerBlock(MyOreUraniumBlock, "OreSpawn_OreUraniumBlock");
		GameRegistry.registerBlock(MyBlockTitaniumBlock, "OreSpawn_BlockTitaniumBlock");
		GameRegistry.registerBlock(MyBlockMobzillaScaleBlock, "OreSpawn_BlockMobzillaScaleBlock");
		GameRegistry.registerBlock(MyBlockUraniumBlock, "OreSpawn_BlockUraniumBlock");
		GameRegistry.registerBlock(MyLavafoamBlock, "OreSpawn_LavafoamBlock");
		GameRegistry.registerBlock(MyOreRubyBlock, "OreSpawn_OreRubyBlock");
		GameRegistry.registerBlock(MyBlockRubyBlock, "OreSpawn_BlockRubyBlock");
		GameRegistry.registerBlock(MyOreAmethystBlock, "OreSpawn_OreAmethystBlock");
		GameRegistry.registerBlock(MyBlockAmethystBlock, "OreSpawn_BlockAmethystBlock");
		GameRegistry.registerBlock(MyCrystalPinkBlock, "OreSpawn_CrystalPinkBlock");
		GameRegistry.registerBlock(MyTigersEyeBlock, "OreSpawn_TigersEyeBlock");
		GameRegistry.registerBlock(MyPizzaBlock, "OreSpawn_PizzaBlock");
		GameRegistry.registerItem(MyPizzaItem, "OreSpawn_PizzaItem");
		GameRegistry.registerBlock(MyDuctTapeBlock, "OreSpawn_DuctTapeBlock");
		GameRegistry.registerItem(MyDuctTapeItem, "OreSpawn_DuctTapeItem");
		GameRegistry.registerBlock(CrystalStone, "OreSpawn_OreCrystalStoneBlock");
		GameRegistry.registerBlock(CrystalRat, "OreSpawn_OreCrystalRatBlock");
		GameRegistry.registerBlock(RedAntTroll, "OreSpawn_OreRedAntTrollBlock");
		GameRegistry.registerBlock(TermiteTroll, "OreSpawn_OreTermiteTrollBlock");
		GameRegistry.registerBlock(CrystalFairy, "OreSpawn_OreCrystalFairyBlock");
		GameRegistry.registerBlock(CrystalCoal, "OreSpawn_OreCrystalCoalBlock");
		GameRegistry.registerBlock(CrystalGrass, "OreSpawn_OreCrystalGrassBlock");
		GameRegistry.registerBlock(CrystalCrystal, "OreSpawn_OreCrystalCrystalBlock");
		GameRegistry.registerBlock(TigersEye, "OreSpawn_OreTigersEyeBlock");
		GameRegistry.registerBlock(CrystalPlanksBlock, "OreSpawn_CrystalPlanksBlock");
		GameRegistry.registerBlock(CrystalWorkbenchBlock, "OreSpawn_CrystalWorkbenchBlock");
		GameRegistry.registerBlock(CrystalFurnaceBlock, "OreSpawn_CrystalFurnaceBlock");
		GameRegistry.registerBlock(CrystalFurnaceOnBlock, "OreSpawn_CrystalFurnaceOnBlock");
		
		GameRegistry.registerBlock(MyStrawberryPlant, "OreSpawn_StrawberryPlant");
		GameRegistry.registerBlock(MyRadishPlant, "OreSpawn_RadishPlant");
		GameRegistry.registerBlock(MyRicePlant, "OreSpawn_RicePlant");
		GameRegistry.registerBlock(MyButterflyPlant, "OreSpawn_ButterflyPlant");
		GameRegistry.registerBlock(MyMothPlant, "OreSpawn_MothPlant");
		GameRegistry.registerBlock(MyMosquitoPlant, "OreSpawn_MosquitoPlant");
		GameRegistry.registerBlock(MyFireflyPlant, "OreSpawn_FireflyPlant");
		GameRegistry.registerBlock(MyCornPlant1, "OreSpawn_CornPlant1");
		GameRegistry.registerBlock(MyCornPlant2, "OreSpawn_CornPlant2");
		GameRegistry.registerBlock(MyCornPlant3, "OreSpawn_CornPlant3");
		GameRegistry.registerBlock(MyCornPlant4, "OreSpawn_CornPlant4");
		GameRegistry.registerBlock(MyQuinoaPlant1, "OreSpawn_QuinoaPlant1");
		GameRegistry.registerBlock(MyQuinoaPlant2, "OreSpawn_QuinoaPlant2");
		GameRegistry.registerBlock(MyQuinoaPlant3, "OreSpawn_QuinoaPlant3");
		GameRegistry.registerBlock(MyQuinoaPlant4, "OreSpawn_QuinoaPlant4");
		GameRegistry.registerBlock(MyTomatoPlant1, "OreSpawn_TomatoPlant1");
		GameRegistry.registerBlock(MyTomatoPlant2, "OreSpawn_TomatoPlant2");
		GameRegistry.registerBlock(MyTomatoPlant3, "OreSpawn_TomatoPlant3");
		GameRegistry.registerBlock(MyTomatoPlant4, "OreSpawn_TomatoPlant4");
		GameRegistry.registerBlock(MyLettucePlant1, "OreSpawn_LettucePlant1");
		GameRegistry.registerBlock(MyLettucePlant2, "OreSpawn_LettucePlant2");
		GameRegistry.registerBlock(MyLettucePlant3, "OreSpawn_LettucePlant3");
		GameRegistry.registerBlock(MyLettucePlant4, "OreSpawn_LettucePlant4");
		GameRegistry.registerBlock(MyAppleLeaves, "OreSpawn_AppleLeaves");
		GameRegistry.registerBlock(MyExperienceLeaves, "OreSpawn_ExperienceLeaves");
		GameRegistry.registerBlock(MyScaryLeaves, "OreSpawn_ScaryLeaves");
		GameRegistry.registerBlock(MyCherryLeaves, "OreSpawn_CherryLeaves");
		GameRegistry.registerBlock(MyPeachLeaves, "OreSpawn_PeachLeaves");
		GameRegistry.registerBlock(MySkyTreeLog, "OreSpawn_SkyTreeLog");
		GameRegistry.registerBlock(MyDT, "OreSpawn_DuplicatorLog");
		GameRegistry.registerBlock(MyExperiencePlant, "OreSpawn_ExperiencePlant");
		GameRegistry.registerBlock(MyCrystalPlant, "OreSpawn_CrystalPlant");
		GameRegistry.registerBlock(MyCrystalPlant2, "OreSpawn_CrystalPlant2");
		GameRegistry.registerBlock(MyCrystalPlant3, "OreSpawn_CrystalPlant3");
		GameRegistry.registerBlock(MyFlowerPinkBlock, "OreSpawn_FlowerPink");
		GameRegistry.registerBlock(MyFlowerBlueBlock, "OreSpawn_FlowerBlue");
		GameRegistry.registerBlock(MyFlowerBlackBlock, "OreSpawn_FlowerBlack");
		GameRegistry.registerBlock(MyFlowerScaryBlock, "OreSpawn_FlowerScary");
		GameRegistry.registerBlock(CrystalFlowerRedBlock, "OreSpawn_CrystalFlowerRed");
		GameRegistry.registerBlock(CrystalFlowerGreenBlock, "OreSpawn_CrystalFlowerGreen");
		GameRegistry.registerBlock(CrystalFlowerBlueBlock, "OreSpawn_CrystalFlowerBlue");
		GameRegistry.registerBlock(CrystalFlowerYellowBlock, "OreSpawn_CrystalFlowerYellow");
		GameRegistry.registerBlock(MyCrystalLeaves, "OreSpawn_CrystalLeaves");
		GameRegistry.registerBlock(MyCrystalLeaves2, "OreSpawn_CrystalLeaves2");
		GameRegistry.registerBlock(MyCrystalLeaves3, "OreSpawn_CrystalLeaves3");
		GameRegistry.registerBlock(MyCrystalTreeLog, "OreSpawn_CrystalTreeLog");
		
		GameRegistry.registerBlock(ExtremeTorch, "OreSpawn_ExtremeTorch");
		GameRegistry.registerBlock(CrystalTorch, "OreSpawn_CrystalTorch");
		GameRegistry.registerBlock(KrakenRepellent, "OreSpawn_KrakenRepellent");
		GameRegistry.registerBlock(CreeperRepellent, "OreSpawn_CreeperRepellent");
		GameRegistry.registerBlock(MyIslandBlock, "OreSpawn_Island");
		GameRegistry.registerBlock(MyKingSpawnerBlock, "OreSpawn_KingSpawner");
		GameRegistry.registerBlock(MyQueenSpawnerBlock, "OreSpawn_QueenSpawner");
		GameRegistry.registerBlock(MyDungeonSpawnerBlock, "OreSpawn_DungeonSpawner");
		
		GameRegistry.registerBlock(MyEnderPearlBlock, "OreSpawn_EnderPearlBlock");
		GameRegistry.registerBlock(MyEyeOfEnderBlock, "OreSpawn_EyeOfEnderBlock");
		GameRegistry.registerBlock(MyAntBlock, "OreSpawn_AntBlock");
		GameRegistry.registerBlock(MyRedAntBlock, "OreSpawn_RedAntBlock");
		GameRegistry.registerBlock(TermiteBlock, "OreSpawn_TermiteBlock");
		GameRegistry.registerBlock(CrystalTermiteBlock, "OreSpawn_CrystalTermiteBlock");
		GameRegistry.registerBlock(MyRainbowAntBlock, "OreSpawn_RainbowAntBlock");
		GameRegistry.registerBlock(MyUnstableAntBlock, "OreSpawn_UnstableAntBlock");
		
		GameRegistry.registerItem(MyIngotUranium, "OreSpawn_IngotUranium");
		GameRegistry.registerItem(MyCrystalPinkIngot, "OreSpawn_CrystalPinkIngot");
		GameRegistry.registerItem(MyTigersEyeIngot, "OreSpawn_TigersEyeIngot");
		GameRegistry.registerItem(MyIngotTitanium, "OreSpawn_IngotTitanium");
		GameRegistry.registerItem(MyUltimateSword, "OreSpawn_UltimateSword");
		GameRegistry.registerItem(MyNightmareSword, "OreSpawn_NightmareSword");
		GameRegistry.registerItem(MyBertha, "OreSpawn_Bertha");
		GameRegistry.registerItem(MyHammy, "OreSpawn_Hammy");
		GameRegistry.registerItem(MySlice, "OreSpawn_Slice");
		GameRegistry.registerItem(MyRoyal, "OreSpawn_Royal");
		GameRegistry.registerItem(MyBattleAxe, "OreSpawn_BattleAxe");
		GameRegistry.registerItem(MyQueenBattleAxe, "OreSpawn_QueenBattleAxe");
		GameRegistry.registerItem(MyChainsaw, "OreSpawn_Chainsaw");
		GameRegistry.registerItem(MyUltimatePickaxe, "OreSpawn_UltimatePickaxe");
		GameRegistry.registerItem(MyUltimateShovel, "OreSpawn_UltimateShovel");
		GameRegistry.registerItem(MyUltimateHoe, "OreSpawn_UltimateHoe");
		GameRegistry.registerItem(MyUltimateAxe, "OreSpawn_UltimateAxe");
		GameRegistry.registerItem(MyEmeraldSword, "OreSpawn_EmeraldSword");
		GameRegistry.registerItem(MyRoseSword, "OreSpawn_RoseSword");
		GameRegistry.registerItem(MyExperienceSword, "OreSpawn_ExperienceSword");
		GameRegistry.registerItem(MyPoisonSword, "OreSpawn_PoisonSword");
		GameRegistry.registerItem(MyRatSword, "OreSpawn_RatSword");
		GameRegistry.registerItem(MyFairySword, "OreSpawn_FairySword");
		GameRegistry.registerItem(MyMantisClaw, "OreSpawn_MantisClaw");
		GameRegistry.registerItem(MyBigHammer, "OreSpawn_BigHammer");
		GameRegistry.registerItem(MyEmeraldPickaxe, "OreSpawn_EmeraldPickaxe");
		GameRegistry.registerItem(MyEmeraldShovel, "OreSpawn_EmeraldShovel");
		GameRegistry.registerItem(MyEmeraldHoe, "OreSpawn_EmeraldHoe");
		GameRegistry.registerItem(MyEmeraldAxe, "OreSpawn_EmeraldAxe");
		GameRegistry.registerItem(MyCrystalWoodSword, "OreSpawn_CrystalWoodSword");
		GameRegistry.registerItem(MyCrystalWoodPickaxe, "OreSpawn_CrystalWoodPickaxe");
		GameRegistry.registerItem(MyCrystalWoodShovel, "OreSpawn_CrystalWoodShovel");
		GameRegistry.registerItem(MyCrystalWoodHoe, "OreSpawn_CrystalWoodHoe");
		GameRegistry.registerItem(MyCrystalWoodAxe, "OreSpawn_CrystalWoodAxe");
		GameRegistry.registerItem(MyCrystalPinkSword, "OreSpawn_CrystalPinkSword");
		GameRegistry.registerItem(MyCrystalPinkPickaxe, "OreSpawn_CrystalPinkPickaxe");
		GameRegistry.registerItem(MyCrystalPinkShovel, "OreSpawn_CrystalPinkShovel");
		GameRegistry.registerItem(MyCrystalPinkHoe, "OreSpawn_CrystalPinkHoe");
		GameRegistry.registerItem(MyCrystalPinkAxe, "OreSpawn_CrystalPinkAxe");
		GameRegistry.registerItem(MyTigersEyeSword, "OreSpawn_TigersEyeSword");
		GameRegistry.registerItem(MyTigersEyePickaxe, "OreSpawn_TigersEyePickaxe");
		GameRegistry.registerItem(MyTigersEyeShovel, "OreSpawn_TigersEyeShovel");
		GameRegistry.registerItem(MyTigersEyeHoe, "OreSpawn_TigersEyeHoe");
		GameRegistry.registerItem(MyTigersEyeAxe, "OreSpawn_TigersEyeAxe");
		GameRegistry.registerItem(MyCrystalStoneSword, "OreSpawn_CrystalStoneSword");
		GameRegistry.registerItem(MyCrystalStonePickaxe, "OreSpawn_CrystalStonePickaxe");
		GameRegistry.registerItem(MyCrystalStoneShovel, "OreSpawn_CrystalStoneShovel");
		GameRegistry.registerItem(MyCrystalStoneHoe, "OreSpawn_CrystalStoneHoe");
		GameRegistry.registerItem(MyCrystalStoneAxe, "OreSpawn_CrystalStoneAxe");
		GameRegistry.registerItem(MyRubySword, "OreSpawn_RubySword");
		GameRegistry.registerItem(MyRubyPickaxe, "OreSpawn_RubyPickaxe");
		GameRegistry.registerItem(MyRubyShovel, "OreSpawn_RubyShovel");
		GameRegistry.registerItem(MyRubyHoe, "OreSpawn_RubyHoe");
		GameRegistry.registerItem(MyRubyAxe, "OreSpawn_RubyAxe");
		GameRegistry.registerItem(MyAmethystSword, "OreSpawn_AmethystSword");
		GameRegistry.registerItem(MyAmethystPickaxe, "OreSpawn_AmethystPickaxe");
		GameRegistry.registerItem(MyAmethystShovel, "OreSpawn_AmethystShovel");
		GameRegistry.registerItem(MyAmethystHoe, "OreSpawn_AmethystHoe");
		GameRegistry.registerItem(MyAmethystAxe, "OreSpawn_AmethystAxe");
		GameRegistry.registerItem(MyItemShoes, "OreSpawn_RedHeels");
		GameRegistry.registerItem(MyItemShoes_1, "OreSpawn_BlackHeels");
		GameRegistry.registerItem(MyItemShoes_2, "OreSpawn_Slippers");
		GameRegistry.registerItem(MyItemShoes_3, "OreSpawn_Boots");
		GameRegistry.registerItem(MyItemGameController, "OreSpawn_GameController");
		GameRegistry.registerItem(MyUltimateBow, "OreSpawn_UltimateBow");
		GameRegistry.registerItem(MySkateBow, "OreSpawn_SkateBow");
		GameRegistry.registerItem(MyUltimateFishingRod, "OreSpawn_UltimateFishingRod");
		GameRegistry.registerItem(MyFireFish, "OreSpawn_FireFish");
		GameRegistry.registerItem(MySunFish, "OreSpawn_SunFish");
		GameRegistry.registerItem(MyLavaEel, "OreSpawn_LavaEel");
		GameRegistry.registerItem(MyMothScale, "OreSpawn_MothScale");
		GameRegistry.registerItem(MyQueenScale, "OreSpawn_QueenScale");
		GameRegistry.registerItem(MyNightmareScale, "OreSpawn_NightmareScale");
		GameRegistry.registerItem(MyEmperorScorpionScale, "OreSpawn_EmperorScorpionScale");
		GameRegistry.registerItem(MyBasiliskScale, "OreSpawn_BasiliskScale");
		GameRegistry.registerItem(MyWaterDragonScale, "OreSpawn_WaterDragonScale");
		GameRegistry.registerItem(MyPeacockFeather, "OreSpawn_PeacockFeather");
		GameRegistry.registerItem(MyJumpyBugScale, "OreSpawn_JumpyBugScale");
		GameRegistry.registerItem(MyKrakenTooth, "OreSpawn_KrakenTooth");
		GameRegistry.registerItem(MyGodzillaScale, "OreSpawn_GodzillaScale");
		GameRegistry.registerItem(GreenGoo, "OreSpawn_GreenGoo");
		GameRegistry.registerItem(SpiderRobotKit, "OreSpawn_SpiderRobotKit");
		GameRegistry.registerItem(AntRobotKit, "OreSpawn_AntRobotKit");
		GameRegistry.registerItem(ZooKeeper, "OreSpawn_ZooKeeper");
		GameRegistry.registerItem(CreeperLauncher, "OreSpawn_CreeperLauncher");
		GameRegistry.registerItem(NetherLost, "OreSpawn_NetherLost");
		GameRegistry.registerItem(CrystalSticks, "OreSpawn_CrystalSticks");
		GameRegistry.registerItem(Sifter, "OreSpawn_Sifter");
		GameRegistry.registerItem(MySunspotUrchin, "OreSpawn_SunspotUrchin");
		GameRegistry.registerItem(MyWaterBall, "OreSpawn_WaterBall");
		GameRegistry.registerItem(MyLaserBall, "OreSpawn_LaserBall");
		GameRegistry.registerItem(MyIceBall, "OreSpawn_IceBall");
		GameRegistry.registerItem(MySmallRock, "OreSpawn_SmallRock");
		GameRegistry.registerItem(MyRock, "OreSpawn_Rock");
		GameRegistry.registerItem(MyRedRock, "OreSpawn_RedRock");
		GameRegistry.registerItem(MyCrystalRedRock, "OreSpawn_CrystalRedRock");
		GameRegistry.registerItem(MyCrystalGreenRock, "OreSpawn_CrystalGreenRock");
		GameRegistry.registerItem(MyCrystalBlueRock, "OreSpawn_CrystalBlueRock");
		GameRegistry.registerItem(MyCrystalTNTRock, "OreSpawn_CrystalTNTRock");
		GameRegistry.registerItem(MyGreenRock, "OreSpawn_GreenRock");
		GameRegistry.registerItem(MyBlueRock, "OreSpawn_BlueRock");
		GameRegistry.registerItem(MyPurpleRock, "OreSpawn_PurpleRock");
		GameRegistry.registerItem(MySpikeyRock, "OreSpawn_SpikeyRock");
		GameRegistry.registerItem(MyTNTRock, "OreSpawn_TNTRock");
		GameRegistry.registerItem(MyAcid, "OreSpawn_Acid");
		GameRegistry.registerItem(MyIrukandji, "OreSpawn_Irukandji");
		GameRegistry.registerItem(MyIrukandjiArrow, "OreSpawn_IrukandjiArrow");
		GameRegistry.registerItem(MyRayGun, "OreSpawn_RayGun");
		GameRegistry.registerItem(MySquidZooka, "OreSpawn_SquidZooka");
		GameRegistry.registerItem(MySparkFish, "OreSpawn_SparkFish");
		GameRegistry.registerItem(MySalt, "OreSpawn_Salt");
		GameRegistry.registerItem(MyPopcorn, "OreSpawn_Popcorn");
		GameRegistry.registerItem(MyButteredPopcorn, "OreSpawn_ButteredPopcorn");
		GameRegistry.registerItem(MyButteredSaltedPopcorn, "OreSpawn_ButteredSaltedPopcorn");
		GameRegistry.registerItem(MyPopcornBag, "OreSpawn_PopcornBag");
		GameRegistry.registerItem(MyButter, "OreSpawn_Butter");
		GameRegistry.registerItem(MyCornDog, "OreSpawn_CornDog");
		GameRegistry.registerItem(MyCheese, "OreSpawn_Cheese");
		GameRegistry.registerItem(MyRawCornDog, "OreSpawn_RawCornDog");
		GameRegistry.registerItem(MyPeacock, "OreSpawn_Peacock");
		GameRegistry.registerItem(MyRawPeacock, "OreSpawn_RawPeacock");
		GameRegistry.registerItem(MyRuby, "OreSpawn_Ruby");
		GameRegistry.registerItem(MyAmethyst, "OreSpawn_Amethyst");
		GameRegistry.registerItem(MyThunderStaff, "OreSpawn_ThunderStaff");
		GameRegistry.registerItem(MyWrench, "OreSpawn_Wrench");
		GameRegistry.registerItem(MyRawBacon, "OreSpawn_RawBacon");
		GameRegistry.registerItem(MyBacon, "OreSpawn_Bacon");
		GameRegistry.registerItem(MyRawCrabMeat, "OreSpawn_RawCrabMeat");
		GameRegistry.registerItem(MyCrabMeat, "OreSpawn_CrabMeat");
		GameRegistry.registerItem(MyButterCandy, "OreSpawn_ButterCandy");
		GameRegistry.registerItem(UraniumNugget, "OreSpawn_UraniumNugget");
		GameRegistry.registerItem(TitaniumNugget, "OreSpawn_TitaniumNugget");
		GameRegistry.registerItem(MyGreenFish, "OreSpawn_GreenFish");
		GameRegistry.registerItem(MyBlueFish, "OreSpawn_BlueFish");
		GameRegistry.registerItem(MyPinkFish, "OreSpawn_PinkFish");
		GameRegistry.registerItem(MyRockFish, "OreSpawn_RockFish");
		GameRegistry.registerItem(MyWoodFish, "OreSpawn_WoodFish");
		GameRegistry.registerItem(MyGreyFish, "OreSpawn_GreyFish");
		GameRegistry.registerItem(MySalad, "OreSpawn_Salad");
		GameRegistry.registerItem(MyBLT, "OreSpawn_BLT");
		GameRegistry.registerItem(MyCrabbyPatty, "OreSpawn_CrabbyPatty");
		
		GameRegistry.registerItem(BerthaHandle, "OreSpawn_BerthaHandle");
		GameRegistry.registerItem(BerthaGuard, "OreSpawn_BerthaGuard");
		GameRegistry.registerItem(BerthaBlade, "OreSpawn_BerthaBlade");
		GameRegistry.registerItem(MolenoidNose, "OreSpawn_MolenoidNose");
		GameRegistry.registerItem(SeaMonsterScale, "OreSpawn_SeaMonsterScale");
		GameRegistry.registerItem(WormTooth, "OreSpawn_WormTooth");
		GameRegistry.registerItem(TRexTooth, "OreSpawn_TRexTooth");
		GameRegistry.registerItem(CaterKillerJaw, "OreSpawn_CaterKillerJaw");
		GameRegistry.registerItem(SeaViperTongue, "OreSpawn_SeaViperTongue");
		GameRegistry.registerItem(VortexEye, "OreSpawn_VortexEye");
		
		
		
		GameRegistry.registerItem(WitherSkeletonEgg, "OreSpawn_WitherSkeletonEgg");
		GameRegistry.registerItem(EnderDragonEgg, "OreSpawn_EnderDragonEgg");
		GameRegistry.registerItem(SnowGolemEgg, "OreSpawn_SnowGolemEgg");
		GameRegistry.registerItem(IronGolemEgg, "OreSpawn_IronGolemEgg");
		GameRegistry.registerItem(WitherBossEgg, "OreSpawn_WitherBossEgg");
		GameRegistry.registerItem(GirlfriendEgg, "OreSpawn_GirlfriendEgg");
		GameRegistry.registerItem(BoyfriendEgg, "OreSpawn_BoyfriendEgg");
		GameRegistry.registerItem(TheKingEgg, "OreSpawn_TheKingEgg");
		GameRegistry.registerItem(TheQueenEgg, "OreSpawn_TheQueenEgg");
		GameRegistry.registerItem(ThePrinceEgg, "OreSpawn_ThePrinceEgg");
		GameRegistry.registerItem(RedCowEgg, "OreSpawn_RedCowEgg");
		GameRegistry.registerItem(CrystalCowEgg, "OreSpawn_CrystalCowEgg");
		GameRegistry.registerItem(GoldCowEgg, "OreSpawn_GoldCowEgg");
		GameRegistry.registerItem(EnchantedCowEgg, "OreSpawn_EnchantedCowEgg");
		GameRegistry.registerItem(MOTHRAEgg, "OreSpawn_MOTHRAEgg");
		GameRegistry.registerItem(AloEgg, "OreSpawn_AloEgg");
		GameRegistry.registerItem(CryoEgg, "OreSpawn_CryoEgg");
		GameRegistry.registerItem(CamaEgg, "OreSpawn_CamaEgg");
		GameRegistry.registerItem(VeloEgg, "OreSpawn_VeloEgg");
		GameRegistry.registerItem(HydroEgg, "OreSpawn_HydroEgg");
		GameRegistry.registerItem(BasilEgg, "OreSpawn_BasilEgg");
		GameRegistry.registerItem(DragonflyEgg, "OreSpawn_DragonflyEgg");
		GameRegistry.registerItem(EmperorScorpionEgg, "OreSpawn_EmperorScorpionEgg");
		GameRegistry.registerItem(ScorpionEgg, "OreSpawn_ScorpionEgg");
		GameRegistry.registerItem(CaveFisherEgg, "OreSpawn_CaveFisherEgg");
		GameRegistry.registerItem(SpyroEgg, "OreSpawn_SpyroEgg");
		GameRegistry.registerItem(BaryonyxEgg, "OreSpawn_BaryonyxEgg");
		GameRegistry.registerItem(GammaMetroidEgg, "OreSpawn_GammaMetroidEgg");
		GameRegistry.registerItem(CockateilEgg, "OreSpawn_CockateilEgg");
		GameRegistry.registerItem(KyuubiEgg, "OreSpawn_KyuubiEgg");
		GameRegistry.registerItem(AlienEgg, "OreSpawn_AlienEgg");
		GameRegistry.registerItem(AttackSquidEgg, "OreSpawn_AttackSquidEgg");
		GameRegistry.registerItem(WaterDragonEgg, "OreSpawn_WaterDragonEgg");
		GameRegistry.registerItem(CephadromeEgg, "OreSpawn_CephadromeEgg");
		GameRegistry.registerItem(KrakenEgg, "OreSpawn_KrakenEgg");
		GameRegistry.registerItem(LizardEgg, "OreSpawn_LizardEgg");
		GameRegistry.registerItem(DragonEgg, "OreSpawn_DragonEgg");
		GameRegistry.registerItem(BeeEgg, "OreSpawn_BeeEgg");
		GameRegistry.registerItem(TrooperBugEgg, "OreSpawn_TrooperBugEgg");
		GameRegistry.registerItem(SpitBugEgg, "OreSpawn_SpitBugEgg");
		GameRegistry.registerItem(StinkBugEgg, "OreSpawn_StinkBugEgg");
		GameRegistry.registerItem(OstrichEgg, "OreSpawn_OstrichEgg");
		GameRegistry.registerItem(GazelleEgg, "OreSpawn_GazelleEgg");
		GameRegistry.registerItem(ChipmunkEgg, "OreSpawn_ChipmunkEgg");
		GameRegistry.registerItem(CreepingHorrorEgg, "OreSpawn_CreepingHorrorEgg");
		GameRegistry.registerItem(TerribleTerrorEgg, "OreSpawn_TerribleTerrorEgg");
		GameRegistry.registerItem(CliffRacerEgg, "OreSpawn_CliffRacerEgg");
		GameRegistry.registerItem(TriffidEgg, "OreSpawn_TriffidEgg");
		GameRegistry.registerItem(PitchBlackEgg, "OreSpawn_PitchBlackEgg");
		GameRegistry.registerItem(LurkingTerrorEgg, "OreSpawn_LurkingTerrorEgg");
		GameRegistry.registerItem(GodzillaEgg, "OreSpawn_GodzillaEgg");
		GameRegistry.registerItem(SmallWormEgg, "OreSpawn_SmallWormEgg");
		GameRegistry.registerItem(MediumWormEgg, "OreSpawn_MediumWormEgg");
		GameRegistry.registerItem(LargeWormEgg, "OreSpawn_LargeWormEgg");
		GameRegistry.registerItem(CassowaryEgg, "OreSpawn_CassowaryEgg");
		GameRegistry.registerItem(CloudSharkEgg, "OreSpawn_CloudSharkEgg");
		GameRegistry.registerItem(GoldFishEgg, "OreSpawn_GoldFishEgg");
		GameRegistry.registerItem(LeafMonsterEgg, "OreSpawn_LeafMonsterEgg");
		GameRegistry.registerItem(TshirtEgg, "OreSpawn_TshirtEgg");
		GameRegistry.registerItem(EnderKnightEgg, "OreSpawn_EnderKnightEgg");
		GameRegistry.registerItem(EnderReaperEgg, "OreSpawn_EnderReaperEgg");
		GameRegistry.registerItem(BeaverEgg, "OreSpawn_BeaverEgg");
		GameRegistry.registerItem(DungeonBeastEgg, "OreSpawn_DungeonBeastEgg");
		GameRegistry.registerItem(RotatorEgg, "OreSpawn_RotatorEgg");
		GameRegistry.registerItem(VortexEgg, "OreSpawn_VortexEgg");
		GameRegistry.registerItem(PeacockEgg, "OreSpawn_PeacockEgg");
		GameRegistry.registerItem(FairyEgg, "OreSpawn_FairyEgg");
		GameRegistry.registerItem(RatEgg, "OreSpawn_RatEgg");
		GameRegistry.registerItem(FlounderEgg, "OreSpawn_FlounderEgg");
		GameRegistry.registerItem(WhaleEgg, "OreSpawn_WhaleEgg");
		GameRegistry.registerItem(IrukandjiEgg, "OreSpawn_IrukandjiEgg");
		GameRegistry.registerItem(SkateEgg, "OreSpawn_SkateEgg");
		GameRegistry.registerItem(UrchinEgg, "OreSpawn_UrchinEgg");
		GameRegistry.registerItem(Robot1Egg, "OreSpawn_Robot1Egg");
		GameRegistry.registerItem(Robot2Egg, "OreSpawn_Robot2Egg");
		GameRegistry.registerItem(Robot3Egg, "OreSpawn_Robot3Egg");
		GameRegistry.registerItem(Robot4Egg, "OreSpawn_Robot4Egg");
		GameRegistry.registerItem(GhostEgg, "OreSpawn_GhostEgg");
		GameRegistry.registerItem(GhostSkellyEgg, "OreSpawn_GhostSkellyEgg");
		GameRegistry.registerItem(BrownAntEgg, "OreSpawn_BrownAntEgg");
		GameRegistry.registerItem(RedAntEgg, "OreSpawn_RedAntEgg");
		GameRegistry.registerItem(RainbowAntEgg, "OreSpawn_RainbowAntEgg");
		GameRegistry.registerItem(UnstableAntEgg, "OreSpawn_UnstableAntEgg");
		GameRegistry.registerItem(TermiteEgg, "OreSpawn_TermiteEgg");
		GameRegistry.registerItem(ButterflyEgg, "OreSpawn_ButterflyEgg");
		GameRegistry.registerItem(MothEgg, "OreSpawn_MothEgg");
		GameRegistry.registerItem(MosquitoEgg, "OreSpawn_MosquitoEgg");
		GameRegistry.registerItem(FireflyEgg, "OreSpawn_FireflyEgg");
		GameRegistry.registerItem(TRexEgg, "OreSpawn_TRexEgg");
		GameRegistry.registerItem(HerculesEgg, "OreSpawn_HerculesEgg");
		GameRegistry.registerItem(MantisEgg, "OreSpawn_MantisEgg");
		GameRegistry.registerItem(StinkyEgg, "OreSpawn_StinkyEgg");
		GameRegistry.registerItem(Robot5Egg, "OreSpawn_Robot5Egg");
		GameRegistry.registerItem(CoinEgg, "OreSpawn_CoinEgg");
		GameRegistry.registerItem(EasterBunnyEgg, "OreSpawn_EasterBunnyEgg");
		GameRegistry.registerItem(MolenoidEgg, "OreSpawn_MolenoidEgg");
		GameRegistry.registerItem(SeaMonsterEgg, "OreSpawn_SeaMonsterEgg");
		GameRegistry.registerItem(SeaViperEgg, "OreSpawn_SeaViperEgg");
		GameRegistry.registerItem(CaterKillerEgg, "OreSpawn_CaterKillerEgg");
		GameRegistry.registerItem(RubberDuckyEgg, "OreSpawn_RubberDuckyEgg");
		GameRegistry.registerItem(HammerheadEgg, "OreSpawn_HammerheadEgg");
		GameRegistry.registerItem(LeonEgg, "OreSpawn_LeonEgg");
		GameRegistry.registerItem(CriminalEgg, "OreSpawn_CriminalEgg");
		GameRegistry.registerItem(BrutalflyEgg, "OreSpawn_BrutalflyEgg");
		GameRegistry.registerItem(NastysaurusEgg, "OreSpawn_NastysaurusEgg");
		GameRegistry.registerItem(PointysaurusEgg, "OreSpawn_PointysaurusEgg");
		GameRegistry.registerItem(CricketEgg, "OreSpawn_CricketEgg");
		GameRegistry.registerItem(ThePrincessEgg, "OreSpawn_ThePrincessEgg");
		GameRegistry.registerItem(FrogEgg, "OreSpawn_FrogEgg");
		GameRegistry.registerItem(JefferyEgg, "OreSpawn_JefferyEgg");
		GameRegistry.registerItem(AntRobotEgg, "OreSpawn_AntRobotEgg");
		GameRegistry.registerItem(SpiderRobotEgg, "OreSpawn_SpiderRobotEgg");
		GameRegistry.registerItem(SpiderDriverEgg, "OreSpawn_SpiderDriverEgg");
		GameRegistry.registerItem(CrabEgg, "OreSpawn_CrabEgg");
		
		
		GameRegistry.registerItem(CageEmpty, "OreSpawn_CageEmpty");
		GameRegistry.registerItem(CagedSpider, "OreSpawn_CagedSpider");
		GameRegistry.registerItem(CagedBat, "OreSpawn_CagedBat");
		GameRegistry.registerItem(CagedCow, "OreSpawn_CagedCow");
		GameRegistry.registerItem(CagedPig, "OreSpawn_CagePig");
		GameRegistry.registerItem(CagedSquid, "OreSpawn_CagedSquid");
		GameRegistry.registerItem(CagedChicken, "OreSpawn_CagedChicken");
		GameRegistry.registerItem(CagedCreeper, "OreSpawn_CagedCreeper");
		GameRegistry.registerItem(CagedSkeleton, "OreSpawn_CagedSkeleton");
		GameRegistry.registerItem(CagedZombie, "OreSpawn_CagedZombie");
		GameRegistry.registerItem(CagedSlime, "OreSpawn_CagedSlime");
		GameRegistry.registerItem(CagedGhast, "OreSpawn_CagedGhast");
		GameRegistry.registerItem(CagedZombiePigman, "OreSpawn_CagedZombiePigman");
		GameRegistry.registerItem(CagedEnderman, "OreSpawn_CagedEnderman");
		GameRegistry.registerItem(CagedCaveSpider, "OreSpawn_CagedCaveSpider");
		GameRegistry.registerItem(CagedSilverfish, "OreSpawn_CagedSilverfish");
		GameRegistry.registerItem(CagedMagmaCube, "OreSpawn_CagedMagmaCube");
		GameRegistry.registerItem(CagedWitch, "OreSpawn_CagedWitch");
		GameRegistry.registerItem(CagedSheep, "OreSpawn_CagedSheep");
		GameRegistry.registerItem(CagedWolf, "OreSpawn_CagedWolf");
		GameRegistry.registerItem(CagedMooshroom, "OreSpawn_CagedMooshroom");
		GameRegistry.registerItem(CagedOcelot, "OreSpawn_CagedOcelot");
		GameRegistry.registerItem(CagedBlaze, "OreSpawn_CagedBlaze");
		GameRegistry.registerItem(CagedGirlfriend, "OreSpawn_CagedGirlfriend");
		GameRegistry.registerItem(CagedBoyfriend, "OreSpawn_CagedBoyfriend");
		GameRegistry.registerItem(CagedWitherSkeleton, "OreSpawn_CagedWitherSkeleton");
		GameRegistry.registerItem(CagedEnderDragon, "OreSpawn_CagedEnderDragon");
		GameRegistry.registerItem(CagedSnowGolem, "OreSpawn_CagedSnowGolem");
		GameRegistry.registerItem(CagedIronGolem, "OreSpawn_CagedIronGolem");
		GameRegistry.registerItem(CagedWitherBoss, "OreSpawn_CagedWitherBoss");
		GameRegistry.registerItem(CagedRedCow, "OreSpawn_CagedRedCow");
		GameRegistry.registerItem(CagedCrystalCow, "OreSpawn_CagedCrystalCow");
		GameRegistry.registerItem(CagedVillager, "OreSpawn_CagedVillager");
		GameRegistry.registerItem(CagedGoldCow, "OreSpawn_CagedGoldCow");
		GameRegistry.registerItem(CagedEnchantedCow, "OreSpawn_CagedEnchantedCow");
		GameRegistry.registerItem(CagedMOTHRA, "OreSpawn_CagedMOTHRA");
		GameRegistry.registerItem(CagedAlo, "OreSpawn_CagedAlo");
		GameRegistry.registerItem(CagedCryo, "OreSpawn_CagedCryo");
		GameRegistry.registerItem(CagedCama, "OreSpawn_CagedCama");
		GameRegistry.registerItem(CagedVelo, "OreSpawn_CagedVelo");
		GameRegistry.registerItem(CagedHydro, "OreSpawn_CagedHydro");
		GameRegistry.registerItem(CagedBasil, "OreSpawn_CagedBasil");
		GameRegistry.registerItem(CagedDragonfly, "OreSpawn_CagedDragonfly");
		GameRegistry.registerItem(CagedEmperorScorpion, "OreSpawn_CagedEmperorScorpion");
		GameRegistry.registerItem(CagedScorpion, "OreSpawn_CagedScorpion");
		GameRegistry.registerItem(CagedCaveFisher, "OreSpawn_CagedCaveFisher");
		GameRegistry.registerItem(CagedSpyro, "OreSpawn_CagedSpyro");
		GameRegistry.registerItem(CagedBaryonyx, "OreSpawn_CagedBaryonyx");
		GameRegistry.registerItem(CagedGammaMetroid, "OreSpawn_CagedGammaMetroid");
		GameRegistry.registerItem(CagedCockateil, "OreSpawn_CagedCockateil");
		GameRegistry.registerItem(CagedKyuubi, "OreSpawn_CagedKyuubi");
		GameRegistry.registerItem(CagedAlien, "OreSpawn_CagedAlien");
		GameRegistry.registerItem(MyElevator, "OreSpawn_Elevator");
		GameRegistry.registerItem(CagedAttackSquid, "OreSpawn_CagedAttackSquid");
		GameRegistry.registerItem(CagedWaterDragon, "OreSpawn_CagedWaterDragon");
		GameRegistry.registerItem(CagedCephadrome, "OreSpawn_CagedCephadrome");
		GameRegistry.registerItem(CagedKraken, "OreSpawn_CagedKraken");
		GameRegistry.registerItem(CagedLizard, "OreSpawn_CagedLizard");
		GameRegistry.registerItem(CagedDragon, "OreSpawn_CagedDragon");
		GameRegistry.registerItem(CagedBee, "OreSpawn_CagedBee");
		GameRegistry.registerItem(CagedHorse, "OreSpawn_CagedHorse");
		GameRegistry.registerItem(CagedFirefly, "OreSpawn_CagedFirefly");
		GameRegistry.registerItem(CagedChipmunk, "OreSpawn_CagedChipmunk");
		GameRegistry.registerItem(CagedGazelle, "OreSpawn_CagedGazelle");
		GameRegistry.registerItem(CagedOstrich, "OreSpawn_CagedOstrich");
		GameRegistry.registerItem(CagedTrooper, "OreSpawn_CagedTrooper");
		GameRegistry.registerItem(CagedSpit, "OreSpawn_CagedSpit");
		GameRegistry.registerItem(CagedStink, "OreSpawn_CagedStink");
		GameRegistry.registerItem(CagedCreepingHorror, "OreSpawn_CagedCreepingHorror");
		GameRegistry.registerItem(CagedTerribleTerror, "OreSpawn_CagedTerribleTerror");
		GameRegistry.registerItem(CagedCliffRacer, "OreSpawn_CagedCliffRacer");
		GameRegistry.registerItem(CagedTriffid, "OreSpawn_CagedTriffid");
		GameRegistry.registerItem(CagedPitchBlack, "OreSpawn_CagedPitchBlack");
		GameRegistry.registerItem(CagedLurkingTerror, "OreSpawn_CagedLurkingTerror");
		GameRegistry.registerItem(CagedSmallWorm, "OreSpawn_CagedSmallWorm");
		GameRegistry.registerItem(CagedMediumWorm, "OreSpawn_CagedMediumWorm");
		GameRegistry.registerItem(CagedLargeWorm, "OreSpawn_CagedLargeWorm");
		GameRegistry.registerItem(CagedCassowary, "OreSpawn_CagedCassowary");
		GameRegistry.registerItem(CagedCloudShark, "OreSpawn_CagedCloudShark");
		GameRegistry.registerItem(CagedGoldFish, "OreSpawn_CagedGoldFish");
		GameRegistry.registerItem(CagedLeafMonster, "OreSpawn_CagedLeafMonster");
		GameRegistry.registerItem(CagedEnderKnight, "OreSpawn_CagedEnderKnight");
		GameRegistry.registerItem(CagedEnderReaper, "OreSpawn_CagedEnderReaper");
		GameRegistry.registerItem(CagedBeaver, "OreSpawn_CagedBeaver");
		GameRegistry.registerItem(CagedUrchin, "OreSpawn_CagedUrchin");
		GameRegistry.registerItem(CagedFlounder, "OreSpawn_CagedFlounder");
		GameRegistry.registerItem(CagedSkate, "OreSpawn_CagedSkate");
		GameRegistry.registerItem(CagedRotator, "OreSpawn_CagedRotator");
		GameRegistry.registerItem(CagedPeacock, "OreSpawn_CagedPeacock");
		GameRegistry.registerItem(CagedFairy, "OreSpawn_CagedFairy");
		GameRegistry.registerItem(CagedDungeonBeast, "OreSpawn_CagedDungeonBeast");
		GameRegistry.registerItem(CagedVortex, "OreSpawn_CagedVortex");
		GameRegistry.registerItem(CagedRat, "OreSpawn_CagedRat");
		GameRegistry.registerItem(CagedWhale, "OreSpawn_CagedWhale");
		GameRegistry.registerItem(CagedIrukandji, "OreSpawn_CagedIrukandji");
		GameRegistry.registerItem(CagedTRex, "OreSpawn_CagedTRex");
		GameRegistry.registerItem(CagedHercules, "OreSpawn_CagedHercules");
		GameRegistry.registerItem(CagedMantis, "OreSpawn_CagedMantis");
		GameRegistry.registerItem(CagedStinky, "OreSpawn_CagedStinky");
		GameRegistry.registerItem(CagedEasterBunny, "OreSpawn_CagedEasterBunny");
		GameRegistry.registerItem(CagedCaterKiller, "OreSpawn_CagedCaterKiller");
		GameRegistry.registerItem(CagedMolenoid, "OreSpawn_CagedMolenoid");
		GameRegistry.registerItem(CagedSeaMonster, "OreSpawn_CagedSeaMonster");
		GameRegistry.registerItem(CagedSeaViper, "OreSpawn_CagedSeaViper");
		GameRegistry.registerItem(CagedLeon, "OreSpawn_CagedLeon");
		GameRegistry.registerItem(CagedHammerhead, "OreSpawn_CagedHammerhead");
		GameRegistry.registerItem(CagedRubberDucky, "OreSpawn_CagedRubberDucky");
		GameRegistry.registerItem(CagedCriminal, "OreSpawn_CagedCriminal");
		GameRegistry.registerItem(CagedBrutalfly, "OreSpawn_CagedBrutalfly");
		GameRegistry.registerItem(CagedNastysaurus, "OreSpawn_CagedNastysaurus");
		GameRegistry.registerItem(CagedPointysaurus, "OreSpawn_CagedPointysaurus");
		GameRegistry.registerItem(CagedCricket, "OreSpawn_CagedCricket");
		GameRegistry.registerItem(CagedFrog, "OreSpawn_CagedFrog");
		GameRegistry.registerItem(CagedSpiderDriver, "OreSpawn_CagedSpiderDriver");
		GameRegistry.registerItem(CagedCrab, "OreSpawn_CagedCrab");
		
		GameRegistry.registerItem(MyStrawberry, "OreSpawn_Strawberry");
		GameRegistry.registerItem(MyCrystalApple, "OreSpawn_CrystalApple");
		GameRegistry.registerItem(MyLove, "OreSpawn_Love");
		GameRegistry.registerItem(MyCherry, "OreSpawn_Cherry");
		GameRegistry.registerItem(MyPeach, "OreSpawn_Peach");
		GameRegistry.registerItem(MyRadish, "OreSpawn_Radish");
		GameRegistry.registerItem(MyRice, "OreSpawn_Rice");
		GameRegistry.registerItem(MyCornCob, "OreSpawn_CornCob");
		GameRegistry.registerItem(MyQuinoa, "OreSpawn_Quinoa");
		GameRegistry.registerItem(MyTomato, "OreSpawn_Tomato");
		GameRegistry.registerItem(MyLettuce, "OreSpawn_Lettuce");
		GameRegistry.registerItem(MyStrawberrySeed, "OreSpawn_StrawberrySeed");
		GameRegistry.registerItem(MyButterflySeed, "OreSpawn_ButterflySeed");
		GameRegistry.registerItem(MyMothSeed, "OreSpawn_MothSeed");
		GameRegistry.registerItem(MyMosquitoSeed, "OreSpawn_MosquitoSeed");
		GameRegistry.registerItem(MyFireflySeed, "OreSpawn_FireflySeed");
		GameRegistry.registerItem(MagicApple, "OreSpawn_MagicApple");
		GameRegistry.registerItem(RandomDungeon, "OreSpawn_RandomDungeon");
		GameRegistry.registerItem(MinersDream, "OreSpawn_MinersDream");
		GameRegistry.registerItem(UltimateHelmet, "OreSpawn_UltimateHelmet");
		GameRegistry.registerItem(UltimateBody, "OreSpawn_UltimateBody");
		GameRegistry.registerItem(UltimateLegs, "OreSpawn_UltimateLegs");
		GameRegistry.registerItem(UltimateBoots, "OreSpawn_UltimateBoots");
		GameRegistry.registerItem(LavaEelHelmet, "OreSpawn_LavaEelHelmet");
		GameRegistry.registerItem(LavaEelBody, "OreSpawn_LavaEelBody");
		GameRegistry.registerItem(LavaEelLegs, "OreSpawn_LavaEelLegs");
		GameRegistry.registerItem(LavaEelBoots, "OreSpawn_LavaEelBoots");
		GameRegistry.registerItem(MothScaleHelmet, "OreSpawn_MothScaleHelmet");
		GameRegistry.registerItem(MothScaleBody, "OreSpawn_MothScaleBody");
		GameRegistry.registerItem(MothScaleLegs, "OreSpawn_MothScaleLegs");
		GameRegistry.registerItem(MothScaleBoots, "OreSpawn_MothScaleBoots");
		GameRegistry.registerItem(MyAppleSeed, "OreSpawn_AppleSeed");
		GameRegistry.registerItem(MyCherrySeed, "OreSpawn_CherrySeed");
		GameRegistry.registerItem(MyPeachSeed, "OreSpawn_PeachSeed");
		GameRegistry.registerItem(MyStepUp, "OreSpawn_StepUp");
		GameRegistry.registerItem(MyStepDown, "OreSpawn_StepDown");
		GameRegistry.registerItem(MyStepAccross, "OreSpawn_StepAccross");
		GameRegistry.registerItem(EmeraldHelmet, "OreSpawn_EmeraldHelmet");
		GameRegistry.registerItem(EmeraldBody, "OreSpawn_EmeraldBody");
		GameRegistry.registerItem(EmeraldLegs, "OreSpawn_EmeraldLegs");
		GameRegistry.registerItem(EmeraldBoots, "OreSpawn_EmeraldBoots");
		GameRegistry.registerItem(MyExperienceCatcher, "OreSpawn_ExperienceCatcher");
		GameRegistry.registerItem(MyDeadStinkBug, "OreSpawn_DeadStinkBug");
		GameRegistry.registerItem(MyExperienceTreeSeed, "OreSpawn_ExperienceTreeSeed");
		GameRegistry.registerItem(ExperienceHelmet, "OreSpawn_ExperienceHelmet");
		GameRegistry.registerItem(ExperienceBody, "OreSpawn_ExperienceBody");
		GameRegistry.registerItem(ExperienceLegs, "OreSpawn_ExperienceLegs");
		GameRegistry.registerItem(ExperienceBoots, "OreSpawn_ExperienceBoots");
		GameRegistry.registerItem(RubyHelmet, "OreSpawn_RubyHelmet");
		GameRegistry.registerItem(RubyBody, "OreSpawn_RubyBody");
		GameRegistry.registerItem(RubyLegs, "OreSpawn_RubyLegs");
		GameRegistry.registerItem(RubyBoots, "OreSpawn_RubyBoots");
		GameRegistry.registerItem(AmethystHelmet, "OreSpawn_AmethystHelmet");
		GameRegistry.registerItem(AmethystBody, "OreSpawn_AmethystBody");
		GameRegistry.registerItem(AmethystLegs, "OreSpawn_AmethystLegs");
		GameRegistry.registerItem(AmethystBoots, "OreSpawn_AmethystBoots");
		GameRegistry.registerItem(ZooCage2, "OreSpawn_ZooCage2");
		GameRegistry.registerItem(ZooCage4, "OreSpawn_ZooCage4");
		GameRegistry.registerItem(ZooCage6, "OreSpawn_ZooCage6");
		GameRegistry.registerItem(ZooCage8, "OreSpawn_ZooCage8");
		GameRegistry.registerItem(ZooCage10, "OreSpawn_ZooCage10");
		GameRegistry.registerItem(InstantShelter, "OreSpawn_InstantShelter");
		GameRegistry.registerItem(InstantGarden, "OreSpawn_InstantGarden");
		GameRegistry.registerItem(CrystalPinkHelmet, "OreSpawn_CrystalPinkHelmet");
		GameRegistry.registerItem(CrystalPinkBody, "OreSpawn_CrystalPinkBody");
		GameRegistry.registerItem(CrystalPinkLegs, "OreSpawn_CrystalPinkLegs");
		GameRegistry.registerItem(CrystalPinkBoots, "OreSpawn_CrystalPinkBoots");
		GameRegistry.registerItem(TigersEyeHelmet, "OreSpawn_TigersEyeHelmet");
		GameRegistry.registerItem(TigersEyeBody, "OreSpawn_TigersEyeBody");
		GameRegistry.registerItem(TigersEyeLegs, "OreSpawn_TigersEyeLegs");
		GameRegistry.registerItem(TigersEyeBoots, "OreSpawn_TigersEyeBoots");
		GameRegistry.registerItem(PeacockFeatherBoots, "OreSpawn_PeacockFeatherBoots");
		GameRegistry.registerItem(PeacockFeatherHelmet, "OreSpawn_PeacockFeatherHelmet");
		GameRegistry.registerItem(PeacockFeatherBody, "OreSpawn_PeacockFeatherBody");
		GameRegistry.registerItem(PeacockFeatherLegs, "OreSpawn_PeacockFeatherLegs");
		GameRegistry.registerItem(MobzillaHelmet, "OreSpawn_MobzillaHelmet");
		GameRegistry.registerItem(MobzillaBody, "OreSpawn_MobzillaBody");
		GameRegistry.registerItem(MobzillaLegs, "OreSpawn_MobzillaLegs");
		GameRegistry.registerItem(MobzillaBoots, "OreSpawn_MobzillaBoots");
		GameRegistry.registerItem(RoyalHelmet, "OreSpawn_RoyalHelmet");
		GameRegistry.registerItem(RoyalBody, "OreSpawn_RoyalBody");
		GameRegistry.registerItem(RoyalLegs, "OreSpawn_RoyalLegs");
		GameRegistry.registerItem(RoyalBoots, "OreSpawn_RoyalBoots");
		GameRegistry.registerItem(LapisHelmet, "OreSpawn_LapisHelmet");
		GameRegistry.registerItem(LapisBody, "OreSpawn_LapisBody");
		GameRegistry.registerItem(LapisLegs, "OreSpawn_LapisLegs");
		GameRegistry.registerItem(LapisBoots, "OreSpawn_LapisBoots");
		GameRegistry.registerItem(QueenHelmet, "OreSpawn_QueenHelmet");
		GameRegistry.registerItem(QueenBody, "OreSpawn_QueenBody");
		GameRegistry.registerItem(QueenLegs, "OreSpawn_QueenLegs");
		GameRegistry.registerItem(QueenBoots, "OreSpawn_QueenBoots");
		
		
		
		
		ItemStack OreSpiderEggStack = new ItemStack(MySpiderSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySpiderSpawnBlock, "en_US", "Ancient Dried Spider Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 52), new Object[]{new ItemStack(Items.water_bucket), OreSpiderEggStack});
		
		ItemStack OreBatEggStack = new ItemStack(MyBatSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBatSpawnBlock, "en_US", "Ancient Dried Bat Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 65), new Object[]{new ItemStack(Items.water_bucket), OreBatEggStack});
		
		ItemStack OreCowEggStack = new ItemStack(MyCowSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCowSpawnBlock, "en_US", "Ancient Dried Cow Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 92), new Object[]{new ItemStack(Items.water_bucket), OreCowEggStack});
		
		ItemStack OrePigEggStack = new ItemStack(MyPigSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyPigSpawnBlock, "en_US", "Ancient Dried Pig Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 90), new Object[]{new ItemStack(Items.water_bucket), OrePigEggStack});
		
		ItemStack OreSquidEggStack = new ItemStack(MySquidSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySquidSpawnBlock, "en_US", "Ancient Dried Squid Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 94), new Object[]{new ItemStack(Items.water_bucket), OreSquidEggStack});
		
		ItemStack OreChickenEggStack = new ItemStack(MyChickenSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyChickenSpawnBlock, "en_US", "Ancient Dried Chicken Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 93), new Object[]{new ItemStack(Items.water_bucket), OreChickenEggStack});
		
		ItemStack OreCreeperEggStack = new ItemStack(MyCreeperSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCreeperSpawnBlock, "en_US", "Ancient Dried Creeper Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 50), new Object[]{new ItemStack(Items.water_bucket), OreCreeperEggStack});
		
		ItemStack OreSkeletonEggStack = new ItemStack(MySkeletonSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySkeletonSpawnBlock, "en_US", "Ancient Dried Skeleton Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 51), new Object[]{new ItemStack(Items.water_bucket), OreSkeletonEggStack});
		
		ItemStack OreZombieEggStack = new ItemStack(MyZombieSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyZombieSpawnBlock, "en_US", "Ancient Dried Zombie Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 54), new Object[]{new ItemStack(Items.water_bucket), OreZombieEggStack});
		
		ItemStack OreSlimeEggStack = new ItemStack(MySlimeSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySlimeSpawnBlock, "en_US", "Ancient Dried Slime Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 55), new Object[]{new ItemStack(Items.water_bucket), OreSlimeEggStack});
		
		ItemStack OreGhastEggStack = new ItemStack(MyGhastSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyGhastSpawnBlock, "en_US", "Ancient Dried Ghast Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 56), new Object[]{new ItemStack(Items.water_bucket), OreGhastEggStack});
		
		ItemStack OreZombiePigmanEggStack = new ItemStack(MyZombiePigmanSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyZombiePigmanSpawnBlock, "en_US", "Ancient Dried Zombie Pigman Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 57), new Object[]{new ItemStack(Items.water_bucket), OreZombiePigmanEggStack});
		
		ItemStack OreEndermanEggStack = new ItemStack(MyEndermanSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyEndermanSpawnBlock, "en_US", "Ancient Dried Enderman Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 58), new Object[]{new ItemStack(Items.water_bucket), OreEndermanEggStack});
		
		ItemStack OreCaveSpiderEggStack = new ItemStack(MyCaveSpiderSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCaveSpiderSpawnBlock, "en_US", "Ancient Dried Cave Spider Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 59), new Object[]{new ItemStack(Items.water_bucket), OreCaveSpiderEggStack});
		
		ItemStack OreSilverfishEggStack = new ItemStack(MySilverfishSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySilverfishSpawnBlock, "en_US", "Ancient Dried Silverfish Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 60), new Object[]{new ItemStack(Items.water_bucket), OreSilverfishEggStack});
		
		ItemStack OreMagmaCubeEggStack = new ItemStack(MyMagmaCubeSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyMagmaCubeSpawnBlock, "en_US", "Ancient Dried Magma Cube Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 62), new Object[]{new ItemStack(Items.water_bucket), OreMagmaCubeEggStack});
		
		ItemStack OreWitchEggStack = new ItemStack(MyWitchSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyWitchSpawnBlock, "en_US", "Ancient Dried Witch Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 66), new Object[]{new ItemStack(Items.water_bucket), OreWitchEggStack});
		
		ItemStack OreSheepEggStack = new ItemStack(MySheepSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySheepSpawnBlock, "en_US", "Ancient Dried Sheep Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 91), new Object[]{new ItemStack(Items.water_bucket), OreSheepEggStack});
		
		ItemStack OreWolfEggStack = new ItemStack(MyWolfSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyWolfSpawnBlock, "en_US", "Ancient Dried Wolf Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 95), new Object[]{new ItemStack(Items.water_bucket), OreWolfEggStack});
		
		ItemStack OreMooshroomEggStack = new ItemStack(MyMooshroomSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyMooshroomSpawnBlock, "en_US", "Ancient Dried Mooshroom Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 96), new Object[]{new ItemStack(Items.water_bucket), OreMooshroomEggStack});
		
		ItemStack OreOcelotEggStack = new ItemStack(MyOcelotSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOcelotSpawnBlock, "en_US", "Ancient Dried Ocelot Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 98), new Object[]{new ItemStack(Items.water_bucket), OreOcelotEggStack});
		
		ItemStack OreBlazeEggStack = new ItemStack(MyBlazeSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBlazeSpawnBlock, "en_US", "Ancient Dried Blaze Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 61), new Object[]{new ItemStack(Items.water_bucket), OreBlazeEggStack});
		
		ItemStack OreWitherSkeletonEggStack = new ItemStack(MyWitherSkeletonSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyWitherSkeletonSpawnBlock, "en_US", "Ancient Dried Wither Skeleton Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(WitherSkeletonEgg), new Object[]{new ItemStack(Items.water_bucket), OreWitherSkeletonEggStack});
		
		ItemStack OreEnderDragonEggStack = new ItemStack(MyEnderDragonSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyEnderDragonSpawnBlock, "en_US", "Ancient Dried Ender Dragon Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(EnderDragonEgg), new Object[]{new ItemStack(Items.water_bucket), OreEnderDragonEggStack});
		
		ItemStack OreSnowGolemEggStack = new ItemStack(MySnowGolemSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySnowGolemSpawnBlock, "en_US", "Ancient Dried Snow Golem Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(SnowGolemEgg), new Object[]{new ItemStack(Items.water_bucket), OreSnowGolemEggStack});
		
		ItemStack OreIronGolemEggStack = new ItemStack(MyIronGolemSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyIronGolemSpawnBlock, "en_US", "Ancient Dried Iron Golem Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(IronGolemEgg), new Object[]{new ItemStack(Items.water_bucket), OreIronGolemEggStack});
		
		ItemStack OreWitherBossEggStack = new ItemStack(MyWitherBossSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyWitherBossSpawnBlock, "en_US", "Ancient Dried Wither Boss Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(WitherBossEgg, 1, 64), new Object[]{new ItemStack(Items.water_bucket), OreWitherBossEggStack});
		
		ItemStack OreGirlfriendEggStack = new ItemStack(MyGirlfriendSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyGirlfriendSpawnBlock, "en_US", "Ancient Dried Girlfriend Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(GirlfriendEgg), new Object[]{new ItemStack(Items.water_bucket), OreGirlfriendEggStack});
		
		ItemStack OreBoyfriendEggStack = new ItemStack(MyBoyfriendSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBoyfriendSpawnBlock, "en_US", "Ancient Dried Boyfriend Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(BoyfriendEgg), new Object[]{new ItemStack(Items.water_bucket), OreBoyfriendEggStack});
		
		ItemStack OreRedCowEggStack = new ItemStack(MyRedCowSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyRedCowSpawnBlock, "en_US", "Ancient Dried Apple Cow Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(RedCowEgg), new Object[]{new ItemStack(Items.water_bucket), OreRedCowEggStack});
		
		ItemStack OreCrystalCowEggStack = new ItemStack(MyCrystalCowSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCrystalCowSpawnBlock, "en_US", "Ancient Dried Crystal Cow Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(CrystalCowEgg), new Object[]{new ItemStack(Items.water_bucket), OreCrystalCowEggStack});
		
		ItemStack OreVillagerEggStack = new ItemStack(MyVillagerSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyVillagerSpawnBlock, "en_US", "Ancient Dried Villager Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 120), new Object[]{new ItemStack(Items.water_bucket), OreVillagerEggStack});
		
		ItemStack OreGoldCowEggStack = new ItemStack(MyGoldCowSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyGoldCowSpawnBlock, "en_US", "Ancient Dried Golden Apple Cow Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(GoldCowEgg), new Object[]{new ItemStack(Items.water_bucket), OreGoldCowEggStack});
		
		ItemStack OreEnchantedCowEggStack = new ItemStack(MyEnchantedCowSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyEnchantedCowSpawnBlock, "en_US", "Ancient Dried Enchanted Golden Apple Cow Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(EnchantedCowEgg), new Object[]{new ItemStack(Items.water_bucket), OreEnchantedCowEggStack});
		
		ItemStack OreMOTHRAEggStack = new ItemStack(MyMOTHRASpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyMOTHRASpawnBlock, "en_US", "Ancient Dried MOTHRA Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(MOTHRAEgg), new Object[]{new ItemStack(Items.water_bucket), OreMOTHRAEggStack});
		
		ItemStack OreAloEggStack = new ItemStack(MyAloSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyAloSpawnBlock, "en_US", "Ancient Dried Alosaurus Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(AloEgg), new Object[]{new ItemStack(Items.water_bucket), OreAloEggStack});
		
		ItemStack OreCryoEggStack = new ItemStack(MyCryoSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCryoSpawnBlock, "en_US", "Ancient Dried Cryolophosaurus Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(CryoEgg), new Object[]{new ItemStack(Items.water_bucket), OreCryoEggStack});
		
		ItemStack OreCamaEggStack = new ItemStack(MyCamaSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCamaSpawnBlock, "en_US", "Ancient Dried Camarasaurus Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(CamaEgg), new Object[]{new ItemStack(Items.water_bucket), OreCamaEggStack});
		
		ItemStack OreVeloEggStack = new ItemStack(MyVeloSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyVeloSpawnBlock, "en_US", "Ancient Dried Velocity Raptor Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(VeloEgg), new Object[]{new ItemStack(Items.water_bucket), OreVeloEggStack});
		
		ItemStack OreHydroEggStack = new ItemStack(MyHydroSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyHydroSpawnBlock, "en_US", "Ancient Dried Hydrolisc Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(HydroEgg), new Object[]{new ItemStack(Items.water_bucket), OreHydroEggStack});
		
		ItemStack OreBasilEggStack = new ItemStack(MyBasilSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBasilSpawnBlock, "en_US", "Ancient Dried Basilisk Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(BasilEgg), new Object[]{new ItemStack(Items.water_bucket), OreBasilEggStack});
		
		ItemStack OreDragonflyEggStack = new ItemStack(MyDragonflySpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyDragonflySpawnBlock, "en_US", "Ancient Dried Dragonfly Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(DragonflyEgg), new Object[]{new ItemStack(Items.water_bucket), OreDragonflyEggStack});
		
		ItemStack OreEmperorScorpionEggStack = new ItemStack(MyEmperorScorpionSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyEmperorScorpionSpawnBlock, "en_US", "Ancient Dried Emperor Scorpion Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(EmperorScorpionEgg), new Object[]{new ItemStack(Items.water_bucket), OreEmperorScorpionEggStack});
		
		ItemStack OreScorpionEggStack = new ItemStack(MyScorpionSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyScorpionSpawnBlock, "en_US", "Ancient Dried Scorpion Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(ScorpionEgg), new Object[]{new ItemStack(Items.water_bucket), OreScorpionEggStack});
		
		ItemStack OreCaveFisherEggStack = new ItemStack(MyCaveFisherSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCaveFisherSpawnBlock, "en_US", "Ancient Dried Cave Fisher Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(CaveFisherEgg), new Object[]{new ItemStack(Items.water_bucket), OreCaveFisherEggStack});
		
		ItemStack OreSpyroEggStack = new ItemStack(MySpyroSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySpyroSpawnBlock, "en_US", "Ancient Dried Baby Dragon Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(SpyroEgg), new Object[]{new ItemStack(Items.water_bucket), OreSpyroEggStack});
		
		ItemStack OreBaryonyxEggStack = new ItemStack(MyBaryonyxSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBaryonyxSpawnBlock, "en_US", "Ancient Dried Baryonyx Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(BaryonyxEgg), new Object[]{new ItemStack(Items.water_bucket), OreBaryonyxEggStack});
		
		ItemStack OreGammaMetroidEggStack = new ItemStack(MyGammaMetroidSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyGammaMetroidSpawnBlock, "en_US", "Ancient Dried WTF? Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(GammaMetroidEgg), new Object[]{new ItemStack(Items.water_bucket), OreGammaMetroidEggStack});
		
		ItemStack OreCockateilEggStack = new ItemStack(MyCockateilSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCockateilSpawnBlock, "en_US", "Ancient Dried Bird Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(CockateilEgg), new Object[]{new ItemStack(Items.water_bucket), OreCockateilEggStack});
		
		ItemStack OreKyuubiEggStack = new ItemStack(MyKyuubiSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyKyuubiSpawnBlock, "en_US", "Ancient Dried Kyuubi Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(KyuubiEgg), new Object[]{new ItemStack(Items.water_bucket), OreKyuubiEggStack});
		
		ItemStack OreAlienEggStack = new ItemStack(MyAlienSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyAlienSpawnBlock, "en_US", "Ancient Dried Alien Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(AlienEgg), new Object[]{new ItemStack(Items.water_bucket), OreAlienEggStack});
		
		ItemStack OreAttackSquidEggStack = new ItemStack(MyAttackSquidSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyAttackSquidSpawnBlock, "en_US", "Ancient Dried Attack Squid Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(AttackSquidEgg), new Object[]{new ItemStack(Items.water_bucket), OreAttackSquidEggStack});
		
		ItemStack OreWaterDragonEggStack = new ItemStack(MyWaterDragonSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyWaterDragonSpawnBlock, "en_US", "Ancient Dried WaterDragon Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(WaterDragonEgg), new Object[]{new ItemStack(Items.water_bucket), OreWaterDragonEggStack});
		
		ItemStack OreKrakenEggStack = new ItemStack(MyKrakenSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyKrakenSpawnBlock, "en_US", "Ancient Dried Kraken Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(KrakenEgg), new Object[]{new ItemStack(Items.water_bucket), OreKrakenEggStack});
		
		ItemStack OreLizardEggStack = new ItemStack(MyLizardSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyLizardSpawnBlock, "en_US", "Ancient Dried Lizard Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(LizardEgg), new Object[]{new ItemStack(Items.water_bucket), OreLizardEggStack});
		
		ItemStack OreCephadromeEggStack = new ItemStack(MyCephadromeSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCephadromeSpawnBlock, "en_US", "Ancient Dried Cephadrome Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(CephadromeEgg), new Object[]{new ItemStack(Items.water_bucket), OreCephadromeEggStack});
		
		ItemStack OreDragonEggStack = new ItemStack(MyDragonSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyDragonSpawnBlock, "en_US", "Ancient Dried Dragon Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(DragonEgg), new Object[]{new ItemStack(Items.water_bucket), OreDragonEggStack});
		
		ItemStack OreBeeEggStack = new ItemStack(MyBeeSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBeeSpawnBlock, "en_US", "Ancient Dried Bee Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(BeeEgg), new Object[]{new ItemStack(Items.water_bucket), OreBeeEggStack});
		
		ItemStack OreHorseEggStack = new ItemStack(MyHorseSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyHorseSpawnBlock, "en_US", "Ancient Dried Horse Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 100), new Object[]{new ItemStack(Items.water_bucket), OreHorseEggStack});
		
		ItemStack OreTrooperBugEggStack = new ItemStack(MyTrooperBugSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyTrooperBugSpawnBlock, "en_US", "Ancient Dried Jumpy Bug Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(TrooperBugEgg), new Object[]{new ItemStack(Items.water_bucket), OreTrooperBugEggStack});
		
		ItemStack OreSpitBugEggStack = new ItemStack(MySpitBugSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MySpitBugSpawnBlock, "en_US", "Ancient Dried Spit Bug Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(SpitBugEgg), new Object[]{new ItemStack(Items.water_bucket), OreSpitBugEggStack});
		
		ItemStack OreStinkBugEggStack = new ItemStack(MyStinkBugSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyStinkBugSpawnBlock, "en_US", "Ancient Dried Stink Bug Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(StinkBugEgg), new Object[]{new ItemStack(Items.water_bucket), OreStinkBugEggStack});
		
		ItemStack OreOstrichEggStack = new ItemStack(MyOstrichSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOstrichSpawnBlock, "en_US", "Ancient Dried Ostrich Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(OstrichEgg), new Object[]{new ItemStack(Items.water_bucket), OreOstrichEggStack});
		
		ItemStack OreGazelleEggStack = new ItemStack(MyGazelleSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyGazelleSpawnBlock, "en_US", "Ancient Dried Gazelle Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(GazelleEgg), new Object[]{new ItemStack(Items.water_bucket), OreGazelleEggStack});
		
		ItemStack OreChipmunkEggStack = new ItemStack(MyChipmunkSpawnBlock);
		
		LanguageRegistry.instance().addNameForObject(MyChipmunkSpawnBlock, "en_US", "Ancient Dried Chipmunk Spawn Egg");
		GameRegistry.addShapelessRecipe(new ItemStack(ChipmunkEgg), new Object[]{new ItemStack(Items.water_bucket), OreChipmunkEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCreepingHorrorSpawnBlock, "en_US", "Ancient Dried Creeping Horror Spawn Egg");
		ItemStack OreCreepingHorrorEggStack = new ItemStack(MyCreepingHorrorSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CreepingHorrorEgg), new Object[]{new ItemStack(Items.water_bucket), OreCreepingHorrorEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyTerribleTerrorSpawnBlock, "en_US", "Ancient Dried Terrible Terror Spawn Egg");
		ItemStack OreTerribleTerrorEggStack = new ItemStack(MyTerribleTerrorSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(TerribleTerrorEgg), new Object[]{new ItemStack(Items.water_bucket), OreTerribleTerrorEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCliffRacerSpawnBlock, "en_US", "Ancient Dried Cliff Racer Spawn Egg");
		ItemStack OreCliffRacerEggStack = new ItemStack(MyCliffRacerSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CliffRacerEgg), new Object[]{new ItemStack(Items.water_bucket), OreCliffRacerEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyTriffidSpawnBlock, "en_US", "Ancient Dried Triffid Spawn Egg");
		ItemStack OreTriffidEggStack = new ItemStack(MyTriffidSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(TriffidEgg), new Object[]{new ItemStack(Items.water_bucket), OreTriffidEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyPitchBlackSpawnBlock, "en_US", "Ancient Dried Nightmare Spawn Egg");
		ItemStack OrePitchBlackEggStack = new ItemStack(MyPitchBlackSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(PitchBlackEgg), new Object[]{new ItemStack(Items.water_bucket), OrePitchBlackEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyLurkingTerrorSpawnBlock, "en_US", "Ancient Dried Lurking Terror Spawn Egg");
		ItemStack OreLurkingTerrorEggStack = new ItemStack(MyLurkingTerrorSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(LurkingTerrorEgg), new Object[]{new ItemStack(Items.water_bucket), OreLurkingTerrorEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyEnderKnightSpawnBlock, "en_US", "Ancient Dried Ender Knight Spawn Egg");
		ItemStack OreEnderKnightEggStack = new ItemStack(MyEnderKnightSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(EnderKnightEgg), new Object[]{new ItemStack(Items.water_bucket), OreEnderKnightEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyEnderReaperSpawnBlock, "en_US", "Ancient Dried Ender Reaper Spawn Egg");
		ItemStack OreEnderReaperEggStack = new ItemStack(MyEnderReaperSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(EnderReaperEgg), new Object[]{new ItemStack(Items.water_bucket), OreEnderReaperEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyGodzillaPartSpawnBlock, "en_US", "Ancient Dried Mobzilla Spawn Egg Part");
		ItemStack OreGodzillaPartEggStack = new ItemStack(MyGodzillaPartSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(MyGodzillaSpawnBlock), new Object[]{OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack, OreGodzillaPartEggStack});
		
		
		
		LanguageRegistry.instance().addNameForObject(MyGodzillaSpawnBlock, "en_US", "Ancient Dried Mobzilla Spawn Egg");
		ItemStack OreGodzillaEggStack = new ItemStack(MyGodzillaSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(GodzillaEgg), new Object[]{new ItemStack(Items.water_bucket), OreGodzillaEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyTheKingPartSpawnBlock, "en_US", "Ancient Dried The King Spawn Egg Part");
		ItemStack OreTheKingPartEggStack = new ItemStack(MyTheKingPartSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(MyTheKingSpawnBlock), new Object[]{OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack, OreTheKingPartEggStack});
		
		
		
		LanguageRegistry.instance().addNameForObject(MyTheKingSpawnBlock, "en_US", "Ancient Dried The King Spawn Egg");
		ItemStack OreTheKingEggStack = new ItemStack(MyTheKingSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(TheKingEgg), new Object[]{new ItemStack(Items.water_bucket), OreTheKingEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyTheQueenPartSpawnBlock, "en_US", "Ancient Dried The Queen Spawn Egg Part");
		ItemStack OreTheQueenPartEggStack = new ItemStack(MyTheQueenPartSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(MyTheQueenSpawnBlock), new Object[]{OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack, OreTheQueenPartEggStack});
		
		
		
		LanguageRegistry.instance().addNameForObject(MyTheQueenSpawnBlock, "en_US", "Ancient Dried The Queen Spawn Egg");
		ItemStack OreTheQueenEggStack = new ItemStack(MyTheQueenSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(TheQueenEgg), new Object[]{new ItemStack(Items.water_bucket), OreTheQueenEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MySmallWormSpawnBlock, "en_US", "Ancient Dried Small Worm Spawn Egg");
		ItemStack OreSmallWormEggStack = new ItemStack(MySmallWormSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(SmallWormEgg), new Object[]{new ItemStack(Items.water_bucket), OreSmallWormEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyMediumWormSpawnBlock, "en_US", "Ancient Dried Medium Worm Spawn Egg");
		ItemStack OreMediumWormEggStack = new ItemStack(MyMediumWormSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(MediumWormEgg), new Object[]{new ItemStack(Items.water_bucket), OreMediumWormEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyLargeWormSpawnBlock, "en_US", "Ancient Dried Large Worm Spawn Egg");
		ItemStack OreLargeWormEggStack = new ItemStack(MyLargeWormSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(LargeWormEgg), new Object[]{new ItemStack(Items.water_bucket), OreLargeWormEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCassowarySpawnBlock, "en_US", "Ancient Dried Cassowary Spawn Egg");
		ItemStack OreCassowaryEggStack = new ItemStack(MyCassowarySpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CassowaryEgg), new Object[]{new ItemStack(Items.water_bucket), OreCassowaryEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCloudSharkSpawnBlock, "en_US", "Ancient Dried Cloud Shark Spawn Egg");
		ItemStack OreCloudSharkEggStack = new ItemStack(MyCloudSharkSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CloudSharkEgg), new Object[]{new ItemStack(Items.water_bucket), OreCloudSharkEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyGoldFishSpawnBlock, "en_US", "Ancient Dried Gold Fish Spawn Egg");
		ItemStack OreGoldFishEggStack = new ItemStack(MyGoldFishSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(GoldFishEgg), new Object[]{new ItemStack(Items.water_bucket), OreGoldFishEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyLeafMonsterSpawnBlock, "en_US", "Ancient Dried Leaf Monster Spawn Egg");
		ItemStack OreLeafMonsterEggStack = new ItemStack(MyLeafMonsterSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(LeafMonsterEgg), new Object[]{new ItemStack(Items.water_bucket), OreLeafMonsterEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyTshirtSpawnBlock, "en_US", "Ancient Dried T-Shirt Spawn Egg");
		ItemStack OreTshirtEggStack = new ItemStack(MyTshirtSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(TshirtEgg), new Object[]{new ItemStack(Items.water_bucket), OreTshirtEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyBeaverSpawnBlock, "en_US", "Ancient Dried Beaver Spawn Egg");
		ItemStack OreBeaverEggStack = new ItemStack(MyBeaverSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(BeaverEgg), new Object[]{new ItemStack(Items.water_bucket), OreBeaverEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyUrchinSpawnBlock, "en_US", "Ancient Dried Urchin Spawn Egg");
		ItemStack OreUrchinEggStack = new ItemStack(MyUrchinSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(UrchinEgg), new Object[]{new ItemStack(Items.water_bucket), OreUrchinEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyFlounderSpawnBlock, "en_US", "Ancient Dried Flounder Spawn Egg");
		ItemStack OreFlounderEggStack = new ItemStack(MyFlounderSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(FlounderEgg), new Object[]{new ItemStack(Items.water_bucket), OreFlounderEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MySkateSpawnBlock, "en_US", "Ancient Dried Skate Spawn Egg");
		ItemStack OreSkateEggStack = new ItemStack(MySkateSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(SkateEgg), new Object[]{new ItemStack(Items.water_bucket), OreSkateEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyRotatorSpawnBlock, "en_US", "Ancient Dried Rotator Spawn Egg");
		ItemStack OreRotatorEggStack = new ItemStack(MyRotatorSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(RotatorEgg), new Object[]{new ItemStack(Items.water_bucket), OreRotatorEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyPeacockSpawnBlock, "en_US", "Ancient Dried Peacock Spawn Egg");
		ItemStack OrePeacockEggStack = new ItemStack(MyPeacockSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(PeacockEgg), new Object[]{new ItemStack(Items.water_bucket), OrePeacockEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyFairySpawnBlock, "en_US", "Ancient Dried Fairy Spawn Egg");
		ItemStack OreFairyEggStack = new ItemStack(MyFairySpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(FairyEgg), new Object[]{new ItemStack(Items.water_bucket), OreFairyEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyDungeonBeastSpawnBlock, "en_US", "Ancient Dried Dungeon Beast Spawn Egg");
		ItemStack OreDungeonBeastEggStack = new ItemStack(MyDungeonBeastSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(DungeonBeastEgg), new Object[]{new ItemStack(Items.water_bucket), OreDungeonBeastEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyVortexSpawnBlock, "en_US", "Ancient Dried Vortex Spawn Egg");
		ItemStack OreVortexEggStack = new ItemStack(MyVortexSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(VortexEgg), new Object[]{new ItemStack(Items.water_bucket), OreVortexEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyRatSpawnBlock, "en_US", "Ancient Dried Rat Spawn Egg");
		ItemStack OreRatEggStack = new ItemStack(MyRatSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(RatEgg), new Object[]{new ItemStack(Items.water_bucket), OreRatEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyWhaleSpawnBlock, "en_US", "Ancient Dried Whale Spawn Egg");
		ItemStack OreWhaleEggStack = new ItemStack(MyWhaleSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(WhaleEgg), new Object[]{new ItemStack(Items.water_bucket), OreWhaleEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyIrukandjiSpawnBlock, "en_US", "Ancient Dried Irukandji Spawn Egg");
		ItemStack OreIrukandjiEggStack = new ItemStack(MyIrukandjiSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(IrukandjiEgg), new Object[]{new ItemStack(Items.water_bucket), OreIrukandjiEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyTRexSpawnBlock, "en_US", "Ancient Dried T. Rex Spawn Egg");
		ItemStack OreTRexEggStack = new ItemStack(MyTRexSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(TRexEgg), new Object[]{new ItemStack(Items.water_bucket), OreTRexEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyHerculesSpawnBlock, "en_US", "Ancient Dried Hercules Beetle Spawn Egg");
		ItemStack OreHerculesEggStack = new ItemStack(MyHerculesSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(HerculesEgg), new Object[]{new ItemStack(Items.water_bucket), OreHerculesEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyMantisSpawnBlock, "en_US", "Ancient Dried Mantis Spawn Egg");
		ItemStack OreMantisEggStack = new ItemStack(MyMantisSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(MantisEgg), new Object[]{new ItemStack(Items.water_bucket), OreMantisEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyStinkySpawnBlock, "en_US", "Ancient Dried Stinky Spawn Egg");
		ItemStack OreStinkyEggStack = new ItemStack(MyStinkySpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(StinkyEgg), new Object[]{new ItemStack(Items.water_bucket), OreStinkyEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyEasterBunnySpawnBlock, "en_US", "Ancient Dried Easter Bunny Spawn Egg");
		ItemStack OreEasterBunnyEggStack = new ItemStack(MyEasterBunnySpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(EasterBunnyEgg), new Object[]{new ItemStack(Items.water_bucket), OreEasterBunnyEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCriminalSpawnBlock, "en_US", "Ancient Dried Criminal Spawn Egg");
		ItemStack OreCriminalEggStack = new ItemStack(MyCriminalSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CriminalEgg), new Object[]{new ItemStack(Items.water_bucket), OreCriminalEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyBrutalflySpawnBlock, "en_US", "Ancient Dried Brutalfly Spawn Egg");
		ItemStack OreBrutalflyEggStack = new ItemStack(MyBrutalflySpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(BrutalflyEgg), new Object[]{new ItemStack(Items.water_bucket), OreBrutalflyEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyNastysaurusSpawnBlock, "en_US", "Ancient Dried Nastysaurus Spawn Egg");
		ItemStack OreNastysaurusEggStack = new ItemStack(MyNastysaurusSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(NastysaurusEgg), new Object[]{new ItemStack(Items.water_bucket), OreNastysaurusEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyPointysaurusSpawnBlock, "en_US", "Ancient Dried Pointysaurus Spawn Egg");
		ItemStack OrePointysaurusEggStack = new ItemStack(MyPointysaurusSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(PointysaurusEgg), new Object[]{new ItemStack(Items.water_bucket), OrePointysaurusEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCricketSpawnBlock, "en_US", "Ancient Dried Cricket Spawn Egg");
		ItemStack OreCricketEggStack = new ItemStack(MyCricketSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CricketEgg), new Object[]{new ItemStack(Items.water_bucket), OreCricketEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyFrogSpawnBlock, "en_US", "Ancient Dried Frog Spawn Egg");
		ItemStack OreFrogEggStack = new ItemStack(MyFrogSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(FrogEgg), new Object[]{new ItemStack(Items.water_bucket), OreFrogEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MySpiderDriverSpawnBlock, "en_US", "Ancient Dried Spider Driver Spawn Egg");
		ItemStack OreSpiderDriverEggStack = new ItemStack(MySpiderDriverSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(SpiderDriverEgg), new Object[]{new ItemStack(Items.water_bucket), OreSpiderDriverEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCrabSpawnBlock, "en_US", "Ancient Dried Crab Spawn Egg");
		ItemStack OreCrabEggStack = new ItemStack(MyCrabSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CrabEgg), new Object[]{new ItemStack(Items.water_bucket), OreCrabEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyCaterKillerSpawnBlock, "en_US", "Ancient Dried CaterKiller Spawn Egg");
		ItemStack OreCaterKillerEggStack = new ItemStack(MyCaterKillerSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(CaterKillerEgg), new Object[]{new ItemStack(Items.water_bucket), OreCaterKillerEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyMolenoidSpawnBlock, "en_US", "Ancient Dried Molenoid Spawn Egg");
		ItemStack OreMolenoidEggStack = new ItemStack(MyMolenoidSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(MolenoidEgg), new Object[]{new ItemStack(Items.water_bucket), OreMolenoidEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MySeaMonsterSpawnBlock, "en_US", "Ancient Dried Sea Monster Spawn Egg");
		ItemStack OreSeaMonsterEggStack = new ItemStack(MySeaMonsterSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(SeaMonsterEgg), new Object[]{new ItemStack(Items.water_bucket), OreSeaMonsterEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MySeaViperSpawnBlock, "en_US", "Ancient Dried Sea Viper Spawn Egg");
		ItemStack OreSeaViperEggStack = new ItemStack(MySeaViperSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(SeaViperEgg), new Object[]{new ItemStack(Items.water_bucket), OreSeaViperEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyRubberDuckySpawnBlock, "en_US", "Ancient Dried Rubber Ducky Spawn Egg");
		ItemStack OreRubberDuckyEggStack = new ItemStack(MyRubberDuckySpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(RubberDuckyEgg), new Object[]{new ItemStack(Items.water_bucket), OreRubberDuckyEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyHammerheadSpawnBlock, "en_US", "Ancient Dried Hammerhead Spawn Egg");
		ItemStack OreHammerheadEggStack = new ItemStack(MyHammerheadSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(HammerheadEgg), new Object[]{new ItemStack(Items.water_bucket), OreHammerheadEggStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyLeonSpawnBlock, "en_US", "Ancient Dried Leonopteryx Spawn Egg");
		ItemStack OreLeonEggStack = new ItemStack(MyLeonSpawnBlock);
		GameRegistry.addShapelessRecipe(new ItemStack(LeonEgg), new Object[]{new ItemStack(Items.water_bucket), OreLeonEggStack});
		
		
		ItemStack OreUraniumStack = new ItemStack(MyOreUraniumBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOreUraniumBlock, "en_US", "Uranium Ore");
		
		ItemStack OreTitaniumStack = new ItemStack(MyOreTitaniumBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOreTitaniumBlock, "en_US", "Titanium Ore");
		
		ItemStack OreSaltStack = new ItemStack(MyOreSaltBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOreSaltBlock, "en_US", "Salt Ore");
		
		ItemStack OreCrystalStoneStack = new ItemStack(CrystalStone);
		
		LanguageRegistry.instance().addNameForObject(CrystalStone, "en_US", "Kyanite");
		
		ItemStack OreCrystalRatStack = new ItemStack(CrystalRat);
		
		LanguageRegistry.instance().addNameForObject(CrystalRat, "en_US", "Crystalized Rats");
		
		ItemStack OreRedAntTrollStack = new ItemStack(RedAntTroll);
		
		LanguageRegistry.instance().addNameForObject(RedAntTroll, "en_US", "Red Ant Troll Block");
		
		ItemStack OreTermiteTrollStack = new ItemStack(TermiteTroll);
		
		LanguageRegistry.instance().addNameForObject(TermiteTroll, "en_US", "Termite Troll Block");
		
		ItemStack OreCrystalFairyStack = new ItemStack(CrystalFairy);
		
		LanguageRegistry.instance().addNameForObject(CrystalFairy, "en_US", "Crystalized Fairies");
		
		ItemStack OreCrystalCrystalStack = new ItemStack(CrystalCrystal);
		
		LanguageRegistry.instance().addNameForObject(CrystalCrystal, "en_US", "Pink Tourmaline");
		
		ItemStack OreTigersEyeStack = new ItemStack(TigersEye);
		
		LanguageRegistry.instance().addNameForObject(TigersEye, "en_US", "Tiger's Eye");
		
		ItemStack OreCrystalCoalStack = new ItemStack(CrystalCoal);
		
		LanguageRegistry.instance().addNameForObject(CrystalCoal, "en_US", "Crystal Energy");
		
		ItemStack OreCrystalGrassStack = new ItemStack(CrystalGrass);
		
		LanguageRegistry.instance().addNameForObject(CrystalGrass, "en_US", "Crystal Grass");
		
		ItemStack OreRubyStack = new ItemStack(MyOreRubyBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOreRubyBlock, "en_US", "Ruby Ore");
		
		ItemStack OreAmethystStack = new ItemStack(MyOreAmethystBlock);
		
		LanguageRegistry.instance().addNameForObject(MyOreAmethystBlock, "en_US", "Amethyst Ore");
		
		
		ItemStack BlockUraniumStack = new ItemStack(MyBlockUraniumBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBlockUraniumBlock, "en_US", "Uranium Block");
		
		ItemStack LavafoamStack = new ItemStack(MyLavafoamBlock);
		
		LanguageRegistry.instance().addNameForObject(MyLavafoamBlock, "en_US", "Lava Foam");
		
		ItemStack BlockTitaniumStack = new ItemStack(MyBlockTitaniumBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBlockTitaniumBlock, "en_US", "Titanium Block");
		
		ItemStack BlockMobzillaScaleStack = new ItemStack(MyBlockMobzillaScaleBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBlockMobzillaScaleBlock, "en_US", "Mobzilla Scale Block");
		
		ItemStack BlockRubyStack = new ItemStack(MyBlockRubyBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBlockRubyBlock, "en_US", "Ruby Block");
		
		ItemStack BlockAmethystStack = new ItemStack(MyBlockAmethystBlock);
		
		LanguageRegistry.instance().addNameForObject(MyBlockAmethystBlock, "en_US", "Amethyst Block");
		
		ItemStack BlockCrystalPinkStack = new ItemStack(MyCrystalPinkBlock);
		
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkBlock, "en_US", "Pink Tourmaline Block");
		
		ItemStack BlockTigersEyeStack = new ItemStack(MyTigersEyeBlock);
		
		LanguageRegistry.instance().addNameForObject(MyTigersEyeBlock, "en_US", "Tiger's Eye Block");
		
		ItemStack EnderPearlStack = new ItemStack(MyEnderPearlBlock);
		
		LanguageRegistry.instance().addNameForObject(MyEnderPearlBlock, "en_US", "Ender-Pearl Block");
		
		ItemStack EyeOfEnderStack = new ItemStack(MyEyeOfEnderBlock);
		
		LanguageRegistry.instance().addNameForObject(MyEyeOfEnderBlock, "en_US", "Eye-of-Ender Block");
		
		
		LanguageRegistry.instance().addNameForObject(MyRTPBlock, "en_US", "Random Teleport Block");
		
		
		LanguageRegistry.instance().addNameForObject(MyMoleDirtBlock, "en_US", "Molenoid Dirt");
		
		ItemStack CrystalPlanksStack = new ItemStack(CrystalPlanksBlock);
		
		LanguageRegistry.instance().addNameForObject(CrystalPlanksBlock, "en_US", "Crystal Planks");
		
		ItemStack CrystalWorkbenchStack = new ItemStack(CrystalWorkbenchBlock);
		
		LanguageRegistry.instance().addNameForObject(CrystalWorkbenchBlock, "en_US", "Crystal Workbench");
		
		
		ItemStack CrystalFurnaceStack = new ItemStack((Block)CrystalFurnaceBlock);
		
		LanguageRegistry.instance().addNameForObject(CrystalFurnaceBlock, "en_US", "Crystal Furnace");
		
		ItemStack CrystalFurnaceOnStack = new ItemStack(CrystalFurnaceOnBlock);
		
		LanguageRegistry.instance().addNameForObject(CrystalFurnaceOnBlock, "en_US", "Crystal Furnace");
		
		GameRegistry.addShapelessRecipe(new ItemStack(CrystalPlanksBlock, 4), new Object[]{MyCrystalTreeLog});
		GameRegistry.addShapelessRecipe(new ItemStack(CrystalWorkbenchBlock), new Object[]{CrystalPlanksBlock, CrystalPlanksBlock, CrystalPlanksBlock, CrystalPlanksBlock});
		
		GameRegistry.addRecipe(new ItemStack(CrystalFurnaceBlock), new Object[]{"FFF", "F F", "FFF", 'F', CrystalStone});
		
		
		GameRegistry.addRecipe(new ItemStack(Blocks.chest), new Object[]{"FFF", "F F", "FFF", 'F', CrystalPlanksBlock});
		
		
		GameRegistry.addRecipe(new ItemStack(Items.wooden_door), new Object[]{"FF ", "FF ", "FF ", 'F', CrystalPlanksBlock});
		
		
		GameRegistry.addRecipe(new ItemStack(Items.wooden_door), new Object[]{" FF", " FF", " FF", 'F', CrystalPlanksBlock});
		
		
		
		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyAntBlock, "en_US", "Ant Nest");
		LanguageRegistry.instance().addNameForObject(MyRedAntBlock, "en_US", "Red Ant Nest");
		LanguageRegistry.instance().addNameForObject(MyRainbowAntBlock, "en_US", "Rainbow Ant Nest");
		LanguageRegistry.instance().addNameForObject(MyUnstableAntBlock, "en_US", "Unstable Ant Nest");
		LanguageRegistry.instance().addNameForObject(TermiteBlock, "en_US", "Termite Nest");
		LanguageRegistry.instance().addNameForObject(CrystalTermiteBlock, "en_US", "Crystal Termite Nest");
		
		
		
		
		
		GameRegistry.addSmelting(MyOreUraniumBlock, new ItemStack(UraniumNugget), 0.3F);
		LanguageRegistry.instance().addNameForObject(MyIngotUranium, "en_US", "Uranium Ingot");
		GameRegistry.addSmelting(MyOreTitaniumBlock, new ItemStack(TitaniumNugget), 0.3F);
		LanguageRegistry.instance().addNameForObject(MyIngotTitanium, "en_US", "Titanium Ingot");
		GameRegistry.addSmelting(MyOreSaltBlock, new ItemStack(MySalt, 8), 0.1F);
		LanguageRegistry.instance().addNameForObject(MySalt, "en_US", "Salt");
		GameRegistry.addSmelting(MyCornCob, new ItemStack(MyPopcorn), 0.1F);
		LanguageRegistry.instance().addNameForObject(MyPopcorn, "en_US", "Popcorn");
		GameRegistry.addSmelting(MyRawCornDog, new ItemStack(MyCornDog), 0.4F);
		LanguageRegistry.instance().addNameForObject(MyCornDog, "en_US", "Corn Dog");
		GameRegistry.addSmelting(MyRawBacon, new ItemStack(MyBacon), 0.2F);
		LanguageRegistry.instance().addNameForObject(MyBacon, "en_US", "Bacon!");
		GameRegistry.addSmelting(CrystalCrystal, new ItemStack(MyCrystalPinkIngot), 0.3F);
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkIngot, "en_US", "Pink Tourmaline Ingot");
		GameRegistry.addSmelting(TigersEye, new ItemStack(MyTigersEyeIngot), 0.3F);
		LanguageRegistry.instance().addNameForObject(MyTigersEyeIngot, "en_US", "Tiger's Eye Ingot");
		GameRegistry.addSmelting(MyRawPeacock, new ItemStack(MyPeacock), 0.4F);
		LanguageRegistry.instance().addNameForObject(MyPeacock, "en_US", "Cooked Peacock");
		GameRegistry.addSmelting(MyRawCrabMeat, new ItemStack(MyCrabMeat), 0.2F);
		LanguageRegistry.instance().addNameForObject(MyCrabMeat, "en_US", "Crab Meat!");
		
		
		GameRegistry.addSmelting(MyGreenFish, new ItemStack(Items.cooked_fished), 0.2F);
		GameRegistry.addSmelting(MyBlueFish, new ItemStack(Items.cooked_fished), 0.2F);
		GameRegistry.addSmelting(MyPinkFish, new ItemStack(Items.cooked_fished), 0.2F);
		GameRegistry.addSmelting(MyRockFish, new ItemStack(Items.cooked_fished), 0.2F);
		GameRegistry.addSmelting(MyWoodFish, new ItemStack(Items.cooked_fished), 0.2F);
		GameRegistry.addSmelting(MyGreyFish, new ItemStack(Items.cooked_fished), 0.2F);
		
		LanguageRegistry.instance().addNameForObject(MyRuby, "en_US", "Ruby");
		LanguageRegistry.instance().addNameForObject(MyAmethyst, "en_US", "Amethyst");
		LanguageRegistry.instance().addNameForObject(UraniumNugget, "en_US", "Uranium Nugget");
		LanguageRegistry.instance().addNameForObject(TitaniumNugget, "en_US", "Titanium Nugget");
		
		
		LanguageRegistry.instance().addNameForObject(MyButter, "en_US", "Butter");
		LanguageRegistry.instance().addNameForObject(MyButterCandy, "en_US", "Butter Candy!");
		LanguageRegistry.instance().addNameForObject(MyButteredPopcorn, "en_US", "Buttered Popcorn");
		LanguageRegistry.instance().addNameForObject(MyButteredSaltedPopcorn, "en_US", "Buttered and Salted Popcorn");
		LanguageRegistry.instance().addNameForObject(MyPopcornBag, "en_US", "Bag of Popcorn");
		LanguageRegistry.instance().addNameForObject(MyRawCornDog, "en_US", "Raw Corn Dog");
		LanguageRegistry.instance().addNameForObject(MyRawBacon, "en_US", "Raw Bacon");
		LanguageRegistry.instance().addNameForObject(MyRawCrabMeat, "en_US", "Raw Crab Meat");
		LanguageRegistry.instance().addNameForObject(MySalad, "en_US", "Garden Salad");
		LanguageRegistry.instance().addNameForObject(MyBLT, "en_US", "BLT Sandwich!");
		LanguageRegistry.instance().addNameForObject(MyRawPeacock, "en_US", "Raw Peacock");
		LanguageRegistry.instance().addNameForObject(MyCrabbyPatty, "en_US", "A Crabby Patty!");
		
		
		LanguageRegistry.instance().addNameForObject(MyUltimateSword, "en_US", "The Ultimate Sword");
		LanguageRegistry.instance().addNameForObject(MyUltimatePickaxe, "en_US", "The Ultimate Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyUltimateShovel, "en_US", "The Ultimate Shovel");
		LanguageRegistry.instance().addNameForObject(MyUltimateHoe, "en_US", "The Ultimate Hoe");
		LanguageRegistry.instance().addNameForObject(MyUltimateAxe, "en_US", "The Ultimate Axe");
		LanguageRegistry.instance().addNameForObject(MyUltimateBow, "en_US", "The Ultimate Bow");
		LanguageRegistry.instance().addNameForObject(MySkateBow, "en_US", "Skate String Bow");
		LanguageRegistry.instance().addNameForObject(MyUltimateFishingRod, "en_US", "The Ultimate Fishing Rod");
		LanguageRegistry.instance().addNameForObject(MyNightmareSword, "en_US", "Nightmare Sword");
		LanguageRegistry.instance().addNameForObject(MyBertha, "en_US", "Big Bertha");
		LanguageRegistry.instance().addNameForObject(MySlice, "en_US", "Slice");
		LanguageRegistry.instance().addNameForObject(MyRoyal, "en_US", "Royal Guardian Sword");
		LanguageRegistry.instance().addNameForObject(MyHammy, "en_US", "Attitude Adjuster");
		LanguageRegistry.instance().addNameForObject(MyBattleAxe, "en_US", "Battle Axe");
		LanguageRegistry.instance().addNameForObject(MyChainsaw, "en_US", "Chainsaw");
		LanguageRegistry.instance().addNameForObject(MyQueenBattleAxe, "en_US", "Queen Scale Battle Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateSword), new Object[]{" T ", " U ", " I ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateSword), new Object[]{"T  ", "U  ", "I  ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateSword), new Object[]{"  T", "  U", "  I", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimatePickaxe), new Object[]{"TUT", " U ", " I ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateShovel), new Object[]{" U ", " T ", " I ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateShovel), new Object[]{"U  ", "T  ", "I  ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateShovel), new Object[]{"  U", "  T", "  I", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateHoe), new Object[]{"TU ", " I ", " I ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateAxe), new Object[]{"TU ", "TI ", " I ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateBow), " TS", "I S", " US", 'S', Items.string, 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium);
		
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MySkateBow), new Object[]{" TS", "T S", " TS", 'S', Items.string, 'T', CrystalSticks});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyUltimateFishingRod), new Object[]{"  T", " US", "I S", 'S', Items.string, 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyNightmareSword), new Object[]{"ODO", "RTR", "OIO", 'I', Items.iron_ingot, 'O', MyNightmareScale, 'D', Items.diamond, 'R', Items.redstone, 'T', MyIngotTitanium});
		
		
		
		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyEmeraldSword, "en_US", "Emerald Sword");
		LanguageRegistry.instance().addNameForObject(MyRoseSword, "en_US", "Rose Sword");
		LanguageRegistry.instance().addNameForObject(MyEmeraldPickaxe, "en_US", "Emerald Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyEmeraldShovel, "en_US", "Emerald Shovel");
		LanguageRegistry.instance().addNameForObject(MyEmeraldHoe, "en_US", "Emerald Hoe");
		LanguageRegistry.instance().addNameForObject(MyEmeraldAxe, "en_US", "Emerald Axe");
		LanguageRegistry.instance().addNameForObject(MyExperienceSword, "en_US", "Experience Sword");
		LanguageRegistry.instance().addNameForObject(MyPoisonSword, "en_US", "Poison Sword");
		LanguageRegistry.instance().addNameForObject(MyRatSword, "en_US", "Rat Sword");
		LanguageRegistry.instance().addNameForObject(MyFairySword, "en_US", "Fairy Sword");
		LanguageRegistry.instance().addNameForObject(MyMantisClaw, "en_US", "Mantis Claw");
		LanguageRegistry.instance().addNameForObject(MyBigHammer, "en_US", "Big Hammer");
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldSword), new Object[]{" E ", " E ", " I ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldSword), new Object[]{"E  ", "E  ", "I  ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldSword), new Object[]{"  E", "  E", "  I", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRoseSword), new Object[]{" E ", " E ", " I ", 'I', Items.stick, 'E', Blocks.red_flower});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRoseSword), new Object[]{"E  ", "E  ", "I  ", 'I', Items.stick, 'E', Blocks.red_flower});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRoseSword), new Object[]{"  E", "  E", "  I", 'I', Items.stick, 'E', Blocks.red_flower});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldPickaxe), new Object[]{"EEE", " I ", " I ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldShovel), new Object[]{" E ", " I ", " I ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldShovel), new Object[]{"E  ", "I  ", "I  ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldShovel), new Object[]{"  E", "  I", "  I", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldHoe), new Object[]{"EE ", " I ", " I ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyEmeraldAxe), new Object[]{"EE ", "EI ", " I ", 'I', Items.stick, 'E', Items.emerald});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyExperienceSword), new Object[]{"EEE", "EIE", "EEE", 'I', MyEmeraldSword, 'E', Items.experience_bottle});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyPoisonSword), new Object[]{"EEE", "EIE", "EEE", 'I', MyEmeraldSword, 'E', MyDeadStinkBug});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRatSword), new Object[]{" E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalRat});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRatSword), new Object[]{"E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalRat});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRatSword), new Object[]{"  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalRat});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyFairySword), new Object[]{" E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalFairy});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyFairySword), new Object[]{"E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalFairy});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyFairySword), new Object[]{"  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalFairy});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyCrystalWoodSword, "en_US", "Crystal Wood Sword");
		LanguageRegistry.instance().addNameForObject(MyCrystalWoodPickaxe, "en_US", "Crystal Wood Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyCrystalWoodShovel, "en_US", "Crystal Wood Shovel");
		LanguageRegistry.instance().addNameForObject(MyCrystalWoodHoe, "en_US", "Crystal Wood Hoe");
		LanguageRegistry.instance().addNameForObject(MyCrystalWoodAxe, "en_US", "Crystal Wood Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodSword), new Object[]{" E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodSword), new Object[]{"E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodSword), new Object[]{"  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodPickaxe), new Object[]{"EEE", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodShovel), new Object[]{" E ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodShovel), new Object[]{"E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodShovel), new Object[]{"  E", "  I", "  I", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodHoe), new Object[]{"EE ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalWoodAxe), new Object[]{"EE ", "EI ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock});
		
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(Blocks.chest), new Object[]{"EEE", "E E", "EEE", 'E', CrystalPlanksBlock});
		
		
		
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkSword, "en_US", "Pink Tourmaline Sword");
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkPickaxe, "en_US", "Pink Tourmaline Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkShovel, "en_US", "Pink Tourmaline Shovel");
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkHoe, "en_US", "Pink Tourmaline Hoe");
		LanguageRegistry.instance().addNameForObject(MyCrystalPinkAxe, "en_US", "Pink Tourmaline Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkSword), new Object[]{" E ", " E ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkSword), new Object[]{"E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkSword), new Object[]{"  E", "  E", "  I", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkPickaxe), new Object[]{"EEE", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkShovel), new Object[]{" E ", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkShovel), new Object[]{"E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkShovel), new Object[]{"  E", "  I", "  I", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkHoe), new Object[]{"EE ", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkAxe), new Object[]{"EE ", "EI ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(Items.bucket), "   ", "I I", " I ", 'I', MyCrystalPinkIngot);
		
		
		
		LanguageRegistry.instance().addNameForObject(MyTigersEyeSword, "en_US", "Tiger's Eye Sword");
		LanguageRegistry.instance().addNameForObject(MyTigersEyePickaxe, "en_US", "Tiger's Eye Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyTigersEyeShovel, "en_US", "Tiger's Eye Shovel");
		LanguageRegistry.instance().addNameForObject(MyTigersEyeHoe, "en_US", "Tiger's Eye Hoe");
		LanguageRegistry.instance().addNameForObject(MyTigersEyeAxe, "en_US", "Tiger's Eye Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeSword), new Object[]{" E ", " E ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeSword), new Object[]{"E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeSword), new Object[]{"  E", "  E", "  I", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyePickaxe), new Object[]{"EEE", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeShovel), new Object[]{" E ", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeShovel), new Object[]{"E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeShovel), new Object[]{"  E", "  I", "  I", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeHoe), new Object[]{"EE ", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeAxe), new Object[]{"EE ", "EI ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyCrystalStoneSword, "en_US", "Kyanite Sword");
		LanguageRegistry.instance().addNameForObject(MyCrystalStonePickaxe, "en_US", "Kyanite Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyCrystalStoneShovel, "en_US", "Kyanite Shovel");
		LanguageRegistry.instance().addNameForObject(MyCrystalStoneHoe, "en_US", "Kyanite Hoe");
		LanguageRegistry.instance().addNameForObject(MyCrystalStoneAxe, "en_US", "Kyanite Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneSword), new Object[]{" E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneSword), new Object[]{"E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneSword), new Object[]{"  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStonePickaxe), new Object[]{"EEE", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneShovel), new Object[]{" E ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneShovel), new Object[]{"E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneShovel), new Object[]{"  E", "  I", "  I", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneHoe), new Object[]{"EE ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalStoneAxe), new Object[]{"EE ", "EI ", " I ", 'I', CrystalSticks, 'E', CrystalStone});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyRubySword, "en_US", "Ruby Sword");
		LanguageRegistry.instance().addNameForObject(MyRubyPickaxe, "en_US", "Ruby Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyRubyShovel, "en_US", "Ruby Shovel");
		LanguageRegistry.instance().addNameForObject(MyRubyHoe, "en_US", "Ruby Hoe");
		LanguageRegistry.instance().addNameForObject(MyRubyAxe, "en_US", "Ruby Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyRubySword), new Object[]{" E ", " E ", " I ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubySword), new Object[]{"E  ", "E  ", "I  ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubySword), new Object[]{"  E", "  E", "  I", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubyPickaxe), new Object[]{"EEE", " I ", " I ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubyShovel), new Object[]{" E ", " I ", " I ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubyShovel), new Object[]{"E  ", "I  ", "I  ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubyShovel), new Object[]{"  E", "  I", "  I", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubyHoe), new Object[]{"EE ", " I ", " I ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyRubyAxe), new Object[]{"EE ", "EI ", " I ", 'I', Items.stick, 'E', MyRuby});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyAmethystSword, "en_US", "Amethyst Sword");
		LanguageRegistry.instance().addNameForObject(MyAmethystPickaxe, "en_US", "Amethyst Pickaxe");
		LanguageRegistry.instance().addNameForObject(MyAmethystShovel, "en_US", "Amethyst Shovel");
		LanguageRegistry.instance().addNameForObject(MyAmethystHoe, "en_US", "Amethyst Hoe");
		LanguageRegistry.instance().addNameForObject(MyAmethystAxe, "en_US", "Amethyst Axe");
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystSword), new Object[]{" E ", " E ", " I ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystSword), new Object[]{"E  ", "E  ", "I  ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystSword), new Object[]{"  E", "  E", "  I", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystPickaxe), new Object[]{"EEE", " I ", " I ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystShovel), new Object[]{" E ", " I ", " I ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystShovel), new Object[]{"E  ", "I  ", "I  ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystShovel), new Object[]{"  E", "  I", "  I", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystHoe), new Object[]{"EE ", " I ", " I ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyAmethystAxe), new Object[]{"EE ", "EI ", " I ", 'I', Items.stick, 'E', MyAmethyst});
		
		
		
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyHammy), new Object[]{MyUltimateSword, MyUltimateSword, MyBigHammer, GreenGoo});
		GameRegistry.addShapelessRecipe(new ItemStack(MyBattleAxe), new Object[]{MyUltimateSword, MyUltimateAxe, GreenGoo});
		GameRegistry.addRecipe(new ItemStack(MyChainsaw), new Object[]{"EEE", "EIE", "EEE", 'I', MyUltimateAxe, 'E', Blocks.redstone_block});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyQueenBattleAxe), new Object[]{"EIE", "EIE", " I ", 'I', Items.iron_ingot, 'E', MyQueenScale});
		
		
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyBertha), new Object[]{BerthaHandle, BerthaGuard, BerthaBlade});
		GameRegistry.addShapelessRecipe(new ItemStack(BerthaHandle), new Object[]{MyRayGun, MyBigHammer, MyMantisClaw, MyWaterDragonScale, GreenGoo});
		GameRegistry.addShapelessRecipe(new ItemStack(BerthaGuard), new Object[]{MolenoidNose, SeaMonsterScale, MyMothScale, MyBasiliskScale, MyNightmareScale, MyEmperorScorpionScale, MyJumpyBugScale});
		GameRegistry.addShapelessRecipe(new ItemStack(BerthaBlade), new Object[]{MyKrakenTooth, WormTooth, TRexTooth, MyUltimateSword, CaterKillerJaw, SeaViperTongue, VortexEye});
		GameRegistry.addShapelessRecipe(new ItemStack(MySlice), new Object[]{MyBertha, Items.iron_ingot});
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyIrukandjiArrow), new Object[]{MyPeacockFeather, MyIrukandji, CrystalSticks});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.bed), new Object[]{MyPeacockFeather, CrystalPlanksBlock, MyPeacockFeather, CrystalPlanksBlock, MyPeacockFeather, CrystalPlanksBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(MySquidZooka), new Object[]{Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, Items.dye, Items.dye, Items.dye, Items.iron_ingot, Items.iron_ingot, Items.iron_ingot});
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyIngotUranium), new Object[]{"UUU", "UUU", "UUU", 'U', UraniumNugget});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(UraniumNugget, 9), new Object[]{new ItemStack(MyIngotUranium)});
		GameRegistry.addRecipe(new ItemStack(MyIngotTitanium), new Object[]{"UUU", "UUU", "UUU", 'U', TitaniumNugget});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(TitaniumNugget, 9), new Object[]{new ItemStack(MyIngotTitanium)});
		
		GameRegistry.addRecipe(new ItemStack(MyBlockUraniumBlock), new Object[]{"UUU", "UUU", "UUU", 'U', MyIngotUranium});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyIngotUranium, 9), new Object[]{new ItemStack(MyBlockUraniumBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyBlockTitaniumBlock), new Object[]{"TTT", "TTT", "TTT", 'T', MyIngotTitanium});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyIngotTitanium, 9), new Object[]{new ItemStack(MyBlockTitaniumBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyBlockMobzillaScaleBlock), new Object[]{"TTT", "TTT", "TTT", 'T', MyGodzillaScale});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyGodzillaScale, 9), new Object[]{new ItemStack(MyBlockMobzillaScaleBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyBlockRubyBlock), new Object[]{"TTT", "TTT", "TTT", 'T', MyRuby});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyRuby, 9), new Object[]{new ItemStack(MyBlockRubyBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyBlockAmethystBlock), new Object[]{"TTT", "TTT", "TTT", 'T', MyAmethyst});
		
		
		GameRegistry.addRecipe(new ItemStack(MyCrystalPinkBlock), new Object[]{"TTT", "TTT", "TTT", 'T', MyCrystalPinkIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(MyTigersEyeBlock), new Object[]{"TTT", "TTT", "TTT", 'T', MyTigersEyeIngot});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyAmethyst, 9), new Object[]{new ItemStack(MyBlockAmethystBlock)});
		GameRegistry.addShapelessRecipe(new ItemStack(MyCrystalPinkIngot, 9), new Object[]{new ItemStack(MyCrystalPinkBlock)});
		GameRegistry.addShapelessRecipe(new ItemStack(MyTigersEyeIngot, 9), new Object[]{new ItemStack(MyTigersEyeBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyEnderPearlBlock), new Object[]{"TTT", "TTT", "TTT", 'T', Items.ender_pearl});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_pearl, 9), new Object[]{new ItemStack(MyEnderPearlBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyEyeOfEnderBlock), new Object[]{"TTT", "TTT", "TTT", 'T', Items.ender_eye});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_eye, 9), new Object[]{new ItemStack(MyEyeOfEnderBlock)});
		
		GameRegistry.addRecipe(new ItemStack(MyThunderStaff), new Object[]{"DR ", "RR ", "  R", 'D', Items.diamond, 'R', MyRuby});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(MyWrench), new Object[]{"D D", " D ", " D ", 'D', Items.iron_ingot});
		
		
		
		
		ItemStack MilkBucket = new ItemStack(Items.milk_bucket);
		ItemStack SomePaper = new ItemStack(Items.paper);
		GameRegistry.addShapelessRecipe(new ItemStack(MyButter, 4), new Object[]{MilkBucket, MilkBucket});
		GameRegistry.addShapelessRecipe(new ItemStack(MyCheese, 2), new Object[]{MilkBucket, MilkBucket, MilkBucket, MilkBucket});
		GameRegistry.addShapelessRecipe(new ItemStack(MyButteredPopcorn), new Object[]{MyPopcorn, MyButter});
		GameRegistry.addShapelessRecipe(new ItemStack(MyButteredSaltedPopcorn), new Object[]{MyButteredPopcorn, MySalt});
		GameRegistry.addShapelessRecipe(new ItemStack(MyButteredSaltedPopcorn), new Object[]{MyPopcorn, MySalt, MyButter});
		GameRegistry.addShapelessRecipe(new ItemStack(MyPopcornBag), new Object[]{MyButteredSaltedPopcorn, MyButteredSaltedPopcorn, MyButteredSaltedPopcorn, MyButteredSaltedPopcorn, MyButteredSaltedPopcorn, MyButteredSaltedPopcorn, SomePaper, SomePaper, SomePaper});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyRawCornDog, 4), new Object[]{MyCornCob, Items.chicken, Items.porkchop, Items.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(MyRawBacon, 2), new Object[]{MySalt, Items.porkchop});
		GameRegistry.addShapelessRecipe(new ItemStack(MyButterCandy, 4), new Object[]{MyButter, Items.sugar});
		
		GameRegistry.addShapelessRecipe(new ItemStack(MySalad, 1), new Object[]{MyLettuce, MyTomato, MyRadish, Items.carrot, Items.bowl});
		GameRegistry.addShapelessRecipe(new ItemStack(MyBLT, 1), new Object[]{MyBacon, MyLettuce, MyTomato, MyButter, Items.bread});
		GameRegistry.addShapelessRecipe(new ItemStack(MyPizzaItem, 1), new Object[]{MyTomato, MyCheese, MyBacon, Items.bread});
		GameRegistry.addRecipe(new ItemStack(MyDuctTapeItem), new Object[]{"   ", "AAA", "RRR", 'R', Items.string, 'A', Items.slime_ball});
		
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(MyCrabbyPatty, 1), new Object[]{MyCrabMeat, MyLettuce, MyTomato, Items.bread});
		
		LanguageRegistry.instance().addNameForObject(MyCheese, "en_US", "Cheese");
		LanguageRegistry.instance().addNameForObject(MyPizzaBlock, "en_US", "Pizza!");
		LanguageRegistry.instance().addNameForObject(MyPizzaItem, "en_US", "Pizza!");
		LanguageRegistry.instance().addNameForObject(MyDuctTapeBlock, "en_US", "Duct Tape!");
		LanguageRegistry.instance().addNameForObject(MyDuctTapeItem, "en_US", "Duct Tape!");
		
		LanguageRegistry.instance().addNameForObject(ZooCage2, "en_US", "Extra Small Zoo Cage");
		LanguageRegistry.instance().addNameForObject(ZooCage4, "en_US", "Small Zoo Cage");
		LanguageRegistry.instance().addNameForObject(ZooCage6, "en_US", "Medium Zoo Cage");
		LanguageRegistry.instance().addNameForObject(ZooCage8, "en_US", "Large Zoo Cage");
		LanguageRegistry.instance().addNameForObject(ZooCage10, "en_US", "Extra Large Zoo Cage");
		
		GameRegistry.addShapelessRecipe(new ItemStack(ZooCage2), new Object[]{Blocks.iron_block, Blocks.glass, Blocks.quartz_block});
		GameRegistry.addShapelessRecipe(new ItemStack(ZooCage4), new Object[]{ZooCage2, Blocks.iron_block, Blocks.glass, Blocks.quartz_block});
		GameRegistry.addShapelessRecipe(new ItemStack(ZooCage6), new Object[]{ZooCage4, Blocks.iron_block, Blocks.glass, Blocks.quartz_block});
		GameRegistry.addShapelessRecipe(new ItemStack(ZooCage8), new Object[]{ZooCage6, Blocks.iron_block, Blocks.glass, Blocks.quartz_block});
		GameRegistry.addShapelessRecipe(new ItemStack(ZooCage10), new Object[]{ZooCage8, Blocks.iron_block, Blocks.glass, Blocks.quartz_block});
		
		LanguageRegistry.instance().addNameForObject(InstantShelter, "en_US", "Instant Survival Shelter");
		LanguageRegistry.instance().addNameForObject(InstantGarden, "en_US", "Instant Survival Garden");
		GameRegistry.addShapelessRecipe(new ItemStack(InstantShelter), new Object[]{Blocks.redstone_block, Items.stick, Blocks.cobblestone});
		GameRegistry.addShapelessRecipe(new ItemStack(InstantGarden), new Object[]{Blocks.redstone_block, Items.wheat, Items.gunpowder});
		
		GameRegistry.addShapelessRecipe(new ItemStack(CreeperLauncher, 4), new Object[]{Items.paper, Items.redstone, Items.stick});
		GameRegistry.addShapelessRecipe(new ItemStack(NetherLost, 1), new Object[]{Items.nether_star, Blocks.netherrack});
		
		GameRegistry.addRecipe(new ItemStack(Sifter), new Object[]{"RRR", "RAR", "RRR", 'R', Items.stick, 'A', Items.string});
		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MagicApple, "en_US", "OMG! No! Don't do it!!!");
		GameRegistry.addRecipe(new ItemStack(MagicApple), new Object[]{"RRR", "RAR", "RRR", 'R', Blocks.redstone_block, 'A', Items.apple});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(RandomDungeon, "en_US", "Random Dungeon");
		GameRegistry.addRecipe(new ItemStack(RandomDungeon), new Object[]{"RRR", "RAR", "RRR", 'R', Blocks.redstone_block, 'A', Items.coal});
		
		
		
		
		if (MinersDreamExpensive == 0)
		{
			LanguageRegistry.instance().addNameForObject(MinersDream, "en_US", "Miner's Dream");
			GameRegistry.addRecipe(new ItemStack(MinersDream), new Object[]{"CCC", "RRR", "GGG", 'R', Blocks.redstone_block, 'C', Blocks.cactus, 'G', Items.gunpowder});
			
			
		}
		else
		{
			
			LanguageRegistry.instance().addNameForObject(MinersDream, "en_US", "Miner's Dream");
			GameRegistry.addRecipe(new ItemStack(MinersDream), new Object[]{"CCC", "RRR", "GGG", 'R', Blocks.redstone_block, 'C', Blocks.cactus, 'G', Blocks.tnt});
		}

		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyStepUp, "en_US", "Stairs going Up");
		GameRegistry.addRecipe(new ItemStack(MyStepUp, 8), new Object[]{"GC ", " C ", " C ", 'C', Blocks.cobblestone, 'G', Items.gunpowder});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyStepDown, "en_US", "Stairs going Down");
		GameRegistry.addRecipe(new ItemStack(MyStepDown, 8), new Object[]{" C ", " C ", "GC ", 'C', Blocks.cobblestone, 'G', Items.gunpowder});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyStepAccross, "en_US", "Insta-Bridge");
		GameRegistry.addRecipe(new ItemStack(MyStepAccross, 8), new Object[]{" C ", "GC ", " C ", 'C', Blocks.cobblestone, 'G', Items.gunpowder});
		
		
		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(ExtremeTorch, "en_US", "Extreme Torch");
		GameRegistry.addShapelessRecipe(new ItemStack(ExtremeTorch, 4), new Object[]{Items.redstone, Items.stick, Items.coal});
		GameRegistry.addShapelessRecipe(new ItemStack(ExtremeTorch, 1), new Object[]{Items.redstone, Blocks.torch});
		GameRegistry.addShapelessRecipe(new ItemStack(CrystalSticks, 6), new Object[]{CrystalPlanksBlock, CrystalPlanksBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(CrystalTorch, 6), new Object[]{CrystalCoal, CrystalSticks});
		
		LanguageRegistry.instance().addNameForObject(CrystalTorch, "en_US", "Crystal Torch");
		
		LanguageRegistry.instance().addNameForObject(KrakenRepellent, "en_US", "Kraken Repellent");
		LanguageRegistry.instance().addNameForObject(MyDeadStinkBug, "en_US", "Dead Stink Bug");
		GameRegistry.addRecipe(new ItemStack(KrakenRepellent, 1), new Object[]{"D D", "STS", "D D", 'D', MyDeadStinkBug, 'T', ExtremeTorch, 'S', Items.string});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(CreeperRepellent, "en_US", "Creeper Repellent");
		GameRegistry.addRecipe(new ItemStack(CreeperRepellent, 1), new Object[]{"D D", "STS", "D D", 'D', GreenGoo, 'T', ExtremeTorch, 'S', Items.string});
		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MyIslandBlock, "en_US", "Island Block");
		LanguageRegistry.instance().addNameForObject(MyKingSpawnerBlock, "en_US", "The King Spawner Block");
		LanguageRegistry.instance().addNameForObject(MyQueenSpawnerBlock, "en_US", "The Queen Spawner Block");
		LanguageRegistry.instance().addNameForObject(MyDungeonSpawnerBlock, "en_US", "Random Dungeon Spawner");
		
		LanguageRegistry.instance().addNameForObject(MyAppleSeed, "en_US", "Apple Tree Seed");
		GameRegistry.addShapelessRecipe(new ItemStack(MyAppleSeed, 6), new Object[]{Items.apple});
		LanguageRegistry.instance().addNameForObject(MyCherrySeed, "en_US", "Cherry Pit");
		GameRegistry.addShapelessRecipe(new ItemStack(MyCherrySeed, 1), new Object[]{MyCherry});
		LanguageRegistry.instance().addNameForObject(MyPeachSeed, "en_US", "Peach Pit");
		GameRegistry.addShapelessRecipe(new ItemStack(MyPeachSeed, 1), new Object[]{MyPeach});
		
		LanguageRegistry.instance().addNameForObject(MyExperienceCatcher, "en_US", "Experience Orb Catcher");
		GameRegistry.addShapelessRecipe(new ItemStack(MyExperienceCatcher, 1), new Object[]{Items.glass_bottle, Items.stick, Items.string});
		
		LanguageRegistry.instance().addNameForObject(MyExperienceTreeSeed, "en_US", "Experience Tree Seed");
		GameRegistry.addRecipe(new ItemStack(MyExperienceTreeSeed, 1), new Object[]{"EEE", "EAE", "EEE", 'A', MyAppleSeed, 'E', Items.experience_bottle});
		
		
		
		
		
		int hookid;
		int urchinid;
		int waterballid;
		int inksackid;
		int laserballid;
		int iceballid;
		int acidid;
		int berthahitid;
		int Irukandjiid;
		int rockid;
		int purplepowerid;
		
		
		hookid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(UltimateFishHook.class, "UltimateFishHook", hookid);
		
		urchinid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(SunspotUrchin.class, "SunspotUrchin", urchinid);
		EntityRegistry.instance().registerModEntity(SunspotUrchin.class, "SunspotUrchin", urchinid, this, 64, 1, true);
		
		waterballid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(WaterBall.class, "WaterBall", waterballid);
		EntityRegistry.instance().registerModEntity(WaterBall.class, "WaterBall", waterballid, this, 64, 1, true);
		
		inksackid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(InkSack.class, "InkSack", inksackid);
		EntityRegistry.instance().registerModEntity(InkSack.class, "InkSack", inksackid, this, 64, 1, true);
		
		laserballid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(LaserBall.class, "LaserBall", laserballid);
		EntityRegistry.instance().registerModEntity(LaserBall.class, "LaserBall", laserballid, this, 64, 1, true);
		
		iceballid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(IceBall.class, "IceBall", iceballid);
		EntityRegistry.instance().registerModEntity(IceBall.class, "IceBall", iceballid, this, 64, 1, true);
		
		acidid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Acid.class, "Acid", acidid);
		EntityRegistry.instance().registerModEntity(Acid.class, "Acid", acidid, this, 64, 1, true);
		
		Irukandjiid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(DeadIrukandji.class, "DeadIrukandji", Irukandjiid);
		EntityRegistry.instance().registerModEntity(DeadIrukandji.class, "DeadIrukandji", Irukandjiid, this, 64, 1, true);
		
		berthahitid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(BerthaHit.class, "BerthaHit", berthahitid);
		EntityRegistry.instance().registerModEntity(BerthaHit.class, "BerthaHit", berthahitid, this, 64, 1, true);
		
		purplepowerid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(PurplePower.class, "PurplePower", purplepowerid);
		EntityRegistry.instance().registerModEntity(PurplePower.class, "PurplePower", purplepowerid, this, 64, 1, true);
		
		rockid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityThrownRock.class, "EntityThrownRock", rockid);
		EntityRegistry.instance().registerModEntity(EntityThrownRock.class, "EntityThrownRock", rockid, this, 64, 1, true);
		
		LanguageRegistry.instance().addNameForObject(MyFireFish, "en_US", "Fire Fish");
		LanguageRegistry.instance().addNameForObject(MySunFish, "en_US", "Sun Fish");
		LanguageRegistry.instance().addNameForObject(MyLavaEel, "en_US", "Lava Eel");
		LanguageRegistry.instance().addNameForObject(MyMothScale, "en_US", "Moth Scale");
		LanguageRegistry.instance().addNameForObject(MyQueenScale, "en_US", "The Queen Scale");
		LanguageRegistry.instance().addNameForObject(MyNightmareScale, "en_US", "Nightmare Scale");
		LanguageRegistry.instance().addNameForObject(MyEmperorScorpionScale, "en_US", "Emperor Scorpion Scale");
		LanguageRegistry.instance().addNameForObject(MyBasiliskScale, "en_US", "Basilisk Scale");
		LanguageRegistry.instance().addNameForObject(MyWaterDragonScale, "en_US", "Water Dragon Scale");
		LanguageRegistry.instance().addNameForObject(MyJumpyBugScale, "en_US", "Jumpy Bug Scale");
		LanguageRegistry.instance().addNameForObject(MyKrakenTooth, "en_US", "Kraken Tooth");
		LanguageRegistry.instance().addNameForObject(MyGodzillaScale, "en_US", "Mobzilla Scale");
		LanguageRegistry.instance().addNameForObject(GreenGoo, "en_US", "Green Goo");
		LanguageRegistry.instance().addNameForObject(SpiderRobotKit, "en_US", "Spider Robot Kit");
		LanguageRegistry.instance().addNameForObject(AntRobotKit, "en_US", "Red Ant Robot Kit");
		LanguageRegistry.instance().addNameForObject(ZooKeeper, "en_US", "ZooKeeper Shard");
		LanguageRegistry.instance().addNameForObject(MySunspotUrchin, "en_US", "Sunspot Urchin");
		LanguageRegistry.instance().addNameForObject(MySparkFish, "en_US", "Spark Fish");
		LanguageRegistry.instance().addNameForObject(MyWaterBall, "en_US", "WaterDragon Charge");
		LanguageRegistry.instance().addNameForObject(MyLaserBall, "en_US", "Robot Laser Charge");
		LanguageRegistry.instance().addNameForObject(MyIceBall, "en_US", "Ice Ball");
		LanguageRegistry.instance().addNameForObject(MySmallRock, "en_US", "Small Rock");
		LanguageRegistry.instance().addNameForObject(MyRock, "en_US", "Big Rock");
		LanguageRegistry.instance().addNameForObject(MyRedRock, "en_US", "Flame Rock");
		LanguageRegistry.instance().addNameForObject(MyCrystalRedRock, "en_US", "Flame Crystal");
		LanguageRegistry.instance().addNameForObject(MyCrystalGreenRock, "en_US", "Poison Crystal");
		LanguageRegistry.instance().addNameForObject(MyCrystalBlueRock, "en_US", "Slowness Crystal");
		LanguageRegistry.instance().addNameForObject(MyCrystalTNTRock, "en_US", "Explosive Crystal");
		LanguageRegistry.instance().addNameForObject(MyBlueRock, "en_US", "Slowness Rock");
		LanguageRegistry.instance().addNameForObject(MyGreenRock, "en_US", "Poison Rock");
		LanguageRegistry.instance().addNameForObject(MyPurpleRock, "en_US", "Weakness Rock");
		LanguageRegistry.instance().addNameForObject(MySpikeyRock, "en_US", "Painful Rock");
		LanguageRegistry.instance().addNameForObject(MyTNTRock, "en_US", "Explosive Rock");
		LanguageRegistry.instance().addNameForObject(MyAcid, "en_US", "Acid");
		LanguageRegistry.instance().addNameForObject(MyIrukandji, "en_US", "Dead Irukandji");
		LanguageRegistry.instance().addNameForObject(MyIrukandjiArrow, "en_US", "Irukandji Arrow");
		LanguageRegistry.instance().addNameForObject(MyRayGun, "en_US", "A Freakin' Ray Gun!");
		LanguageRegistry.instance().addNameForObject(MySquidZooka, "en_US", "SquidZooka!");
		LanguageRegistry.instance().addNameForObject(MyThunderStaff, "en_US", "Thunder Staff");
		LanguageRegistry.instance().addNameForObject(MyWrench, "en_US", "Wrench");
		LanguageRegistry.instance().addNameForObject(MyGreenFish, "en_US", "Green Fish");
		LanguageRegistry.instance().addNameForObject(MyBlueFish, "en_US", "Blue Fish");
		LanguageRegistry.instance().addNameForObject(MyPinkFish, "en_US", "Pink Fish");
		LanguageRegistry.instance().addNameForObject(MyRockFish, "en_US", "Rock Fish");
		LanguageRegistry.instance().addNameForObject(MyWoodFish, "en_US", "Wood Fish");
		LanguageRegistry.instance().addNameForObject(MyGreyFish, "en_US", "Grey Fish");
		LanguageRegistry.instance().addNameForObject(Sifter, "en_US", "Sifter");
		LanguageRegistry.instance().addNameForObject(CreeperLauncher, "en_US", "Creeper Launcher");
		LanguageRegistry.instance().addNameForObject(NetherLost, "en_US", "Nether Tracker");
		LanguageRegistry.instance().addNameForObject(CrystalSticks, "en_US", "Crystal Shards");
		LanguageRegistry.instance().addNameForObject(MyPeacockFeather, "en_US", "Peacock Feather");
		
		LanguageRegistry.instance().addNameForObject(BerthaHandle, "en_US", "Big Bertha Handle");
		LanguageRegistry.instance().addNameForObject(BerthaGuard, "en_US", "Big Bertha Guard");
		LanguageRegistry.instance().addNameForObject(BerthaBlade, "en_US", "Big Bertha Blade");
		LanguageRegistry.instance().addNameForObject(MolenoidNose, "en_US", "Molenoid Nose");
		LanguageRegistry.instance().addNameForObject(SeaMonsterScale, "en_US", "Sea Monster Scale");
		LanguageRegistry.instance().addNameForObject(WormTooth, "en_US", "Worm Tooth");
		LanguageRegistry.instance().addNameForObject(TRexTooth, "en_US", "TRex Tooth");
		LanguageRegistry.instance().addNameForObject(CaterKillerJaw, "en_US", "CaterKiller Jaws");
		LanguageRegistry.instance().addNameForObject(SeaViperTongue, "en_US", "Sea Viper Tongue");
		LanguageRegistry.instance().addNameForObject(VortexEye, "en_US", "Vortex Eye");
		
		
		
		
		ItemStack RayStack = new ItemStack(MyRayGun);
		RayStack.setItemDamage(32767);
		GameRegistry.addShapelessRecipe(new ItemStack(MyRayGun), new Object[]{new ItemStack(Blocks.redstone_block), RayStack});
		
		
		ItemStack SquidStack = new ItemStack(MySquidZooka);
		SquidStack.setItemDamage(32767);
		GameRegistry.addShapelessRecipe(new ItemStack(MySquidZooka), new Object[]{new ItemStack(Items.dye), SquidStack});
		
		
		LanguageRegistry.instance().addNameForObject(MyStrawberry, "en_US", "Strawberry");
		LanguageRegistry.instance().addNameForObject(MyStrawberrySeed, "en_US", "Strawberry Plant");
		LanguageRegistry.instance().addNameForObject(MyButterflySeed, "en_US", "Butterfly Plant");
		LanguageRegistry.instance().addNameForObject(MyMothSeed, "en_US", "Moth Plant");
		LanguageRegistry.instance().addNameForObject(MyMosquitoSeed, "en_US", "Mosquito Plant");
		LanguageRegistry.instance().addNameForObject(MyFireflySeed, "en_US", "Firefly Plant");
		LanguageRegistry.instance().addNameForObject(MyRadish, "en_US", "Radish");
		LanguageRegistry.instance().addNameForObject(MyRice, "en_US", "Rice");
		LanguageRegistry.instance().addNameForObject(MyCherry, "en_US", "Cherries");
		LanguageRegistry.instance().addNameForObject(MyPeach, "en_US", "Peach");
		LanguageRegistry.instance().addNameForObject(MyCrystalApple, "en_US", "Crystal Apple");
		LanguageRegistry.instance().addNameForObject(MyLove, "en_US", "Love");
		
		
		LanguageRegistry.instance().addNameForObject(MyCornCob, "en_US", "Corn");
		LanguageRegistry.instance().addNameForObject(MyCornPlant1, "en_US", "Corn Plant");
		LanguageRegistry.instance().addNameForObject(MyCornPlant2, "en_US", "Corn Plant");
		LanguageRegistry.instance().addNameForObject(MyCornPlant3, "en_US", "Corn Plant");
		LanguageRegistry.instance().addNameForObject(MyCornPlant4, "en_US", "Corn Plant");
		LanguageRegistry.instance().addNameForObject(MyQuinoa, "en_US", "Quinoa");
		LanguageRegistry.instance().addNameForObject(MyQuinoaPlant1, "en_US", "Quinoa Plant");
		LanguageRegistry.instance().addNameForObject(MyQuinoaPlant2, "en_US", "Quinoa Plant");
		LanguageRegistry.instance().addNameForObject(MyQuinoaPlant3, "en_US", "Quinoa Plant");
		LanguageRegistry.instance().addNameForObject(MyQuinoaPlant4, "en_US", "Quinoa Plant");
		LanguageRegistry.instance().addNameForObject(MyTomato, "en_US", "Tomato");
		LanguageRegistry.instance().addNameForObject(MyTomatoPlant1, "en_US", "Tomato Plant");
		LanguageRegistry.instance().addNameForObject(MyTomatoPlant2, "en_US", "Tomato Plant");
		LanguageRegistry.instance().addNameForObject(MyTomatoPlant3, "en_US", "Tomato Plant");
		LanguageRegistry.instance().addNameForObject(MyTomatoPlant4, "en_US", "Tomato Plant");
		LanguageRegistry.instance().addNameForObject(MyLettuce, "en_US", "Lettuce");
		LanguageRegistry.instance().addNameForObject(MyLettucePlant1, "en_US", "Lettuce Plant");
		LanguageRegistry.instance().addNameForObject(MyLettucePlant2, "en_US", "Lettuce Plant");
		LanguageRegistry.instance().addNameForObject(MyLettucePlant3, "en_US", "Lettuce Plant");
		LanguageRegistry.instance().addNameForObject(MyLettucePlant4, "en_US", "Lettuce Plant");
		LanguageRegistry.instance().addNameForObject(MyExperiencePlant, "en_US", "Experience Tree Sapling");
		LanguageRegistry.instance().addNameForObject(MyFlowerPinkBlock, "en_US", "Pink Flower");
		LanguageRegistry.instance().addNameForObject(MyFlowerBlueBlock, "en_US", "Blue Flower");
		LanguageRegistry.instance().addNameForObject(MyFlowerBlackBlock, "en_US", "Black Flower");
		LanguageRegistry.instance().addNameForObject(MyFlowerScaryBlock, "en_US", "Dead Flower");
		LanguageRegistry.instance().addNameForObject(CrystalFlowerRedBlock, "en_US", "Red Crystal Flower");
		LanguageRegistry.instance().addNameForObject(CrystalFlowerGreenBlock, "en_US", "Green Crystal Flower");
		LanguageRegistry.instance().addNameForObject(CrystalFlowerBlueBlock, "en_US", "Blue Crystal Flower");
		LanguageRegistry.instance().addNameForObject(CrystalFlowerYellowBlock, "en_US", "Yellow Crystal Flower");
		LanguageRegistry.instance().addNameForObject(MyCrystalPlant, "en_US", "Red Crystal Tree Sapling");
		LanguageRegistry.instance().addNameForObject(MyCrystalPlant2, "en_US", "Yellow Crystal Tree Sapling");
		LanguageRegistry.instance().addNameForObject(MyCrystalPlant3, "en_US", "Blue Crystal Tree Sapling");
		
		
		
		
		GirlfriendID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Girlfriend.class, "Girlfriend", GirlfriendID);
		LanguageRegistry.instance().addStringLocalization("Girlfriend", "en_US", "Girlfriend");
		LanguageRegistry.instance().addStringLocalization("entity.Girlfriend.name", "en_US", "Girlfriend");
		EntityRegistry.instance().registerModEntity(Girlfriend.class, "Girlfriend", GirlfriendID, this, 64, 1, false);
		
		RedCowID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(RedCow.class, "Apple Cow", RedCowID);
		LanguageRegistry.instance().addStringLocalization("Apple Cow", "en_US", "Apple Cow");
		LanguageRegistry.instance().addStringLocalization("entity.Apple Cow.name", "en_US", "Apple Cow");
		EntityRegistry.instance().registerModEntity(RedCow.class, "Apple Cow", RedCowID, this, 64, 1, false);
		
		GoldCowID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(GoldCow.class, "Golden Apple Cow", GoldCowID);
		LanguageRegistry.instance().addStringLocalization("Golden Apple Cow", "en_US", "Golden Apple Cow");
		LanguageRegistry.instance().addStringLocalization("entity.Golden Apple Cow.name", "en_US", "Golden Apple Cow");
		EntityRegistry.instance().registerModEntity(GoldCow.class, "Golden Apple Cow", GoldCowID, this, 64, 1, false);
		
		EnchantedCowID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EnchantedCow.class, "Enchanted Golden Apple Cow", EnchantedCowID);
		LanguageRegistry.instance().addStringLocalization("Enchanted Golden Apple Cow", "en_US", "Enchanted Golden Apple Cow");
		LanguageRegistry.instance().addStringLocalization("entity.Enchanted Golden Apple Cow.name", "en_US", "Enchanted Golden Apple Cow");
		EntityRegistry.instance().registerModEntity(EnchantedCow.class, "Enchanted Golden Apple Cow", EnchantedCowID, this, 64, 1, false);
		
		ButterflyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityButterfly.class, "Butterfly", ButterflyID);
		LanguageRegistry.instance().addStringLocalization("entity.Butterfly.name", "en_US", "Butterfly");
		LanguageRegistry.instance().addStringLocalization("Butterfly", "en_US", "Butterfly");
		EntityRegistry.instance().registerModEntity(EntityButterfly.class, "Butterfly", ButterflyID, this, 32, 1, false);
		
		LunaMothID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityLunaMoth.class, "Moth", LunaMothID);
		LanguageRegistry.instance().addStringLocalization("entity.Moth.name", "en_US", "Moth");
		LanguageRegistry.instance().addStringLocalization("Moth", "en_US", "Moth");
		EntityRegistry.instance().registerModEntity(EntityLunaMoth.class, "Moth", LunaMothID, this, 32, 1, false);
		
		MosquitoID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityMosquito.class, "Mosquito", MosquitoID);
		LanguageRegistry.instance().addStringLocalization("entity.Mosquito.name", "en_US", "Mosquito");
		LanguageRegistry.instance().addStringLocalization("Mosquito", "en_US", "Mosquito");
		EntityRegistry.instance().registerModEntity(EntityMosquito.class, "Mosquito", MosquitoID, this, 16, 1, false);
		
		FireflyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Firefly.class, "Firefly", FireflyID);
		LanguageRegistry.instance().addStringLocalization("entity.Firefly.name", "en_US", "Firefly");
		LanguageRegistry.instance().addStringLocalization("Firefly", "en_US", "Firefly");
		EntityRegistry.instance().registerModEntity(Firefly.class, "Firefly", FireflyID, this, 64, 1, false);
		
		BeeID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Bee.class, "Bee", BeeID);
		LanguageRegistry.instance().addStringLocalization("Bee", "en_US", "Bee");
		LanguageRegistry.instance().addStringLocalization("entity.Bee.name", "en_US", "Bee");
		EntityRegistry.instance().registerModEntity(Bee.class, "Bee", BeeID, this, 64, 1, false);
		
		MothraID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Mothra.class, "Mothra", MothraID);
		LanguageRegistry.instance().addStringLocalization("Mothra", "en_US", "Mothra");
		LanguageRegistry.instance().addStringLocalization("entity.Mothra.name", "en_US", "Mothra");
		EntityRegistry.instance().registerModEntity(Mothra.class, "Mothra", MothraID, this, 128, 1, false);
		
		AntID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityAnt.class, "Ant", AntID);
		LanguageRegistry.instance().addStringLocalization("entity.Ant.name", "en_US", "Ant");
		LanguageRegistry.instance().addStringLocalization("Ant", "en_US", "Ant");
		EntityRegistry.instance().registerModEntity(EntityAnt.class, "Ant", AntID, this, 16, 1, false);
		RedAntID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityRedAnt.class, "Red Ant", RedAntID);
		LanguageRegistry.instance().addStringLocalization("entity.Red Ant.name", "en_US", "Red Ant");
		LanguageRegistry.instance().addStringLocalization("Red Ant", "en_US", "Red Ant");
		EntityRegistry.instance().registerModEntity(EntityRedAnt.class, "Red Ant", RedAntID, this, 16, 1, false);
		RainbowAntID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityRainbowAnt.class, "Rainbow Ant", RainbowAntID);
		LanguageRegistry.instance().addStringLocalization("entity.Rainbow Ant.name", "en_US", "Rainbow Ant");
		LanguageRegistry.instance().addStringLocalization("Rainbow Ant", "en_US", "Rainbow Ant");
		EntityRegistry.instance().registerModEntity(EntityRainbowAnt.class, "Rainbow Ant", RainbowAntID, this, 16, 1, false);
		UnstableAntID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityUnstableAnt.class, "Unstable Ant", UnstableAntID);
		LanguageRegistry.instance().addStringLocalization("entity.Unstable Ant.name", "en_US", "Unstable Ant");
		LanguageRegistry.instance().addStringLocalization("Unstable Ant", "en_US", "Unstable Ant");
		EntityRegistry.instance().registerModEntity(EntityUnstableAnt.class, "Unstable Ant", UnstableAntID, this, 16, 1, false);
		
		
		Robot1ID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Robot1.class, "Bomb-Omb", Robot1ID);
		LanguageRegistry.instance().addStringLocalization("entity.Bomb-Omb.name", "en_US", "Bomb-Omb");
		LanguageRegistry.instance().addStringLocalization("Bomb-Omb", "en_US", "Bomb-Omb");
		EntityRegistry.instance().registerModEntity(Robot1.class, "Bomb-Omb", Robot1ID, this, 32, 1, false);
		
		Robot2ID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Robot2.class, "Robo-Pounder", Robot2ID);
		LanguageRegistry.instance().addStringLocalization("entity.Robo-Pounder.name", "en_US", "Robo-Pounder");
		LanguageRegistry.instance().addStringLocalization("Robo-Pounder", "en_US", "Robo-Pounder");
		EntityRegistry.instance().registerModEntity(Robot2.class, "Robo-Pounder", Robot2ID, this, 64, 1, false);
		
		Robot3ID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Robot3.class, "Robo-Gunner", Robot3ID);
		LanguageRegistry.instance().addStringLocalization("entity.Robo-Gunner.name", "en_US", "Robo-Gunner");
		LanguageRegistry.instance().addStringLocalization("Robo-Gunner", "en_US", "Robo-Gunner");
		EntityRegistry.instance().registerModEntity(Robot3.class, "Robo-Gunner", Robot3ID, this, 64, 1, false);
		
		Robot4ID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Robot4.class, "Robo-Warrior", Robot4ID);
		LanguageRegistry.instance().addStringLocalization("entity.Robo-Warrior.name", "en_US", "Robo-Warrior");
		LanguageRegistry.instance().addStringLocalization("Robo-Warrior", "en_US", "Robo-Warrior");
		EntityRegistry.instance().registerModEntity(Robot4.class, "Robo-Warrior", Robot4ID, this, 64, 1, false);
		
		Robot5ID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Robot5.class, "Robo-Sniper", Robot5ID);
		LanguageRegistry.instance().addStringLocalization("entity.Robo-Sniper.name", "en_US", "Robo-Sniper");
		LanguageRegistry.instance().addStringLocalization("Robo-Sniper", "en_US", "Robo-Sniper");
		EntityRegistry.instance().registerModEntity(Robot5.class, "Robo-Sniper", Robot5ID, this, 64, 1, false);
		
		
		AlosaurusID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Alosaurus.class, "Alosaurus", AlosaurusID);
		LanguageRegistry.instance().addStringLocalization("Alosaurus", "en_US", "Alosaurus");
		LanguageRegistry.instance().addStringLocalization("entity.Alosaurus.name", "en_US", "Alosaurus");
		EntityRegistry.instance().registerModEntity(Alosaurus.class, "Alosaurus", AlosaurusID, this, 64, 1, false);
		CryolophosaurusID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Cryolophosaurus.class, "Cryolophosaurus", CryolophosaurusID);
		LanguageRegistry.instance().addStringLocalization("Cryolophosaurus", "en_US", "Cryolophosaurus");
		LanguageRegistry.instance().addStringLocalization("entity.Cryolophosaurus.name", "en_US", "Cryolophosaurus");
		EntityRegistry.instance().registerModEntity(Cryolophosaurus.class, "Cryolophosaurus", CryolophosaurusID, this, 64, 1, false);
		BasiliskID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Basilisk.class, "Basilisk", BasiliskID);
		LanguageRegistry.instance().addStringLocalization("Basilisk", "en_US", "Basilisk");
		LanguageRegistry.instance().addStringLocalization("entity.Basilisk.name", "en_US", "Basilisk");
		EntityRegistry.instance().registerModEntity(Basilisk.class, "Basilisk", BasiliskID, this, 64, 1, false);
		CamarasaurusID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Camarasaurus.class, "Camarasaurus", CamarasaurusID);
		LanguageRegistry.instance().addStringLocalization("Camarasaurus", "en_US", "Camarasaurus");
		LanguageRegistry.instance().addStringLocalization("entity.Camarasaurus.name", "en_US", "Camarasaurus");
		EntityRegistry.instance().registerModEntity(Camarasaurus.class, "Camarasaurus", CamarasaurusID, this, 64, 1, false);
		HydroliscID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Hydrolisc.class, "Hydrolisc", HydroliscID);
		LanguageRegistry.instance().addStringLocalization("Hydrolisc", "en_US", "Hydrolisc");
		LanguageRegistry.instance().addStringLocalization("entity.Hydrolisc.name", "en_US", "Hydrolisc");
		EntityRegistry.instance().registerModEntity(Hydrolisc.class, "Hydrolisc", HydroliscID, this, 64, 1, false);
		VelocityRaptorID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(VelocityRaptor.class, "Velocity Raptor", VelocityRaptorID);
		LanguageRegistry.instance().addStringLocalization("Velocity Raptor", "en_US", "Velocity Raptor");
		LanguageRegistry.instance().addStringLocalization("entity.Velocity Raptor.name", "en_US", "Velocity Raptor");
		EntityRegistry.instance().registerModEntity(VelocityRaptor.class, "Velocity Raptor", VelocityRaptorID, this, 64, 1, false);
		
		DragonflyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Dragonfly.class, "Dragonfly", DragonflyID);
		LanguageRegistry.instance().addStringLocalization("Dragonfly", "en_US", "Dragonfly");
		LanguageRegistry.instance().addStringLocalization("entity.Dragonfly.name", "en_US", "Dragonfly");
		EntityRegistry.instance().registerModEntity(Dragonfly.class, "Dragonfly", DragonflyID, this, 64, 1, false);
		
		EmperorScorpionID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EmperorScorpion.class, "Emperor Scorpion", EmperorScorpionID);
		LanguageRegistry.instance().addStringLocalization("Emperor Scorpion", "en_US", "Emperor Scorpion");
		LanguageRegistry.instance().addStringLocalization("entity.Emperor Scorpion.name", "en_US", "Emperor Scorpion");
		EntityRegistry.instance().registerModEntity(EmperorScorpion.class, "Emperor Scorpion", EmperorScorpionID, this, 64, 1, false);
		
		ScorpionID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Scorpion.class, "Scorpion", ScorpionID);
		LanguageRegistry.instance().addStringLocalization("Scorpion", "en_US", "Scorpion");
		LanguageRegistry.instance().addStringLocalization("entity.Scorpion.name", "en_US", "Scorpion");
		EntityRegistry.instance().registerModEntity(Scorpion.class, "Scorpion", ScorpionID, this, 32, 1, false);
		
		CaveFisherID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(CaveFisher.class, "CaveFisher", CaveFisherID);
		LanguageRegistry.instance().addStringLocalization("CaveFisher", "en_US", "CaveFisher");
		LanguageRegistry.instance().addStringLocalization("entity.CaveFisher.name", "en_US", "CaveFisher");
		EntityRegistry.instance().registerModEntity(CaveFisher.class, "CaveFisher", CaveFisherID, this, 32, 1, false);
		
		SpyroID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Spyro.class, "Baby Dragon", SpyroID);
		LanguageRegistry.instance().addStringLocalization("Baby Dragon", "en_US", "Baby Dragon");
		LanguageRegistry.instance().addStringLocalization("entity.Baby Dragon.name", "en_US", "Baby Dragon");
		EntityRegistry.instance().registerModEntity(Spyro.class, "Baby Dragon", SpyroID, this, 64, 1, false);
		
		BaryonyxID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Baryonyx.class, "Baryonyx", BaryonyxID);
		LanguageRegistry.instance().addStringLocalization("Baryonyx", "en_US", "Baryonyx");
		LanguageRegistry.instance().addStringLocalization("entity.Baryonyx.name", "en_US", "Baryonyx");
		EntityRegistry.instance().registerModEntity(Baryonyx.class, "Baryonyx", BaryonyxID, this, 64, 1, false);
		
		GammaMetroidID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(GammaMetroid.class, "WTF?", GammaMetroidID);
		LanguageRegistry.instance().addStringLocalization("WTF?", "en_US", "WTF?");
		LanguageRegistry.instance().addStringLocalization("entity.WTF?.name", "en_US", "WTF?");
		EntityRegistry.instance().registerModEntity(GammaMetroid.class, "WTF?", GammaMetroidID, this, 64, 1, false);
		
		CockateilID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Cockateil.class, "Bird", CockateilID);
		LanguageRegistry.instance().addStringLocalization("Bird", "en_US", "Bird");
		LanguageRegistry.instance().addStringLocalization("entity.Bird.name", "en_US", "Bird");
		EntityRegistry.instance().registerModEntity(Cockateil.class, "Bird", CockateilID, this, 32, 1, false);
		
		RubyBirdID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(RubyBird.class, "Ruby Bird", RubyBirdID);
		LanguageRegistry.instance().addStringLocalization("Ruby Bird", "en_US", "Ruby Bird");
		LanguageRegistry.instance().addStringLocalization("entity.Ruby Bird.name", "en_US", "Ruby Bird");
		EntityRegistry.instance().registerModEntity(RubyBird.class, "Ruby Bird", RubyBirdID, this, 32, 1, false);
		
		KyuubiID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Kyuubi.class, "Kyuubi", KyuubiID);
		LanguageRegistry.instance().addStringLocalization("Kyuubi", "en_US", "Kyuubi");
		LanguageRegistry.instance().addStringLocalization("entity.Kyuubi.name", "en_US", "Kyuubi");
		EntityRegistry.instance().registerModEntity(Kyuubi.class, "Kyuubi", KyuubiID, this, 64, 1, false);
		
		WaterDragonID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(WaterDragon.class, "Water Dragon", WaterDragonID);
		LanguageRegistry.instance().addStringLocalization("Water Dragon", "en_US", "Water Dragon");
		LanguageRegistry.instance().addStringLocalization("entity.Water Dragon.name", "en_US", "Water Dragon");
		EntityRegistry.instance().registerModEntity(WaterDragon.class, "Water Dragon", WaterDragonID, this, 64, 1, false);
		
		AttackSquidID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(AttackSquid.class, "Attack Squid", AttackSquidID);
		LanguageRegistry.instance().addStringLocalization("AttackSquid", "en_US", "Attack Squid");
		LanguageRegistry.instance().addStringLocalization("entity.AttackSquid.name", "en_US", "Attack Squid");
		EntityRegistry.instance().registerModEntity(AttackSquid.class, "Attack Squid", AttackSquidID, this, 32, 1, false);
		
		AlienID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Alien.class, "Alien", AlienID);
		LanguageRegistry.instance().addStringLocalization("Alien", "en_US", "Alien");
		LanguageRegistry.instance().addStringLocalization("entity.Alien.name", "en_US", "Alien");
		EntityRegistry.instance().registerModEntity(Alien.class, "Alien", AlienID, this, 64, 1, false);
		
		
		ElevatorID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Elevator.class, "Hoverboard", ElevatorID);
		LanguageRegistry.instance().addStringLocalization("Hoverboard", "en_US", "Hoverboard");
		LanguageRegistry.instance().addStringLocalization("entity.Hoverboard.name", "en_US", "Hoverboard");
		
		EntityRegistry.instance().registerModEntity(Elevator.class, "Hoverboard", ElevatorID, this, 128, 1, true);
		
		KrakenID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Kraken.class, "The Kraken", KrakenID);
		LanguageRegistry.instance().addStringLocalization("The Kraken", "en_US", "The Kraken");
		LanguageRegistry.instance().addStringLocalization("entity.The Kraken.name", "en_US", "The Kraken");
		EntityRegistry.instance().registerModEntity(Kraken.class, "The Kraken", KrakenID, this, 128, 1, false);
		
		LizardID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Lizard.class, "Lizard", LizardID);
		LanguageRegistry.instance().addStringLocalization("Lizard", "en_US", "Lizard");
		LanguageRegistry.instance().addStringLocalization("entity.Lizard.name", "en_US", "Lizard");
		EntityRegistry.instance().registerModEntity(Lizard.class, "Lizard", LizardID, this, 64, 1, false);
		
		CephadromeID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Cephadrome.class, "Cephadrome", CephadromeID);
		LanguageRegistry.instance().addStringLocalization("Cephadrome", "en_US", "Cephadrome");
		LanguageRegistry.instance().addStringLocalization("entity.Cephadrome.name", "en_US", "Cephadrome");
		
		EntityRegistry.instance().registerModEntity(Cephadrome.class, "Cephadrome", CephadromeID, this, 128, 1, true);
		
		DragonID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Dragon.class, "Dragon", DragonID);
		LanguageRegistry.instance().addStringLocalization("entity.Dragon.name", "en_US", "Dragon");
		LanguageRegistry.instance().addStringLocalization("Dragon", "en_US", "Dragon");
		
		EntityRegistry.instance().registerModEntity(Dragon.class, "Dragon", DragonID, this, 128, 1, true);
		
		ChipmunkID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Chipmunk.class, "Chipmunk", ChipmunkID);
		LanguageRegistry.instance().addStringLocalization("Chipmunk", "en_US", "Chipmunk");
		LanguageRegistry.instance().addStringLocalization("entity.Chipmunk.name", "en_US", "Chipmunk");
		EntityRegistry.instance().registerModEntity(Chipmunk.class, "Chipmunk", ChipmunkID, this, 32, 1, false);
		
		GazelleID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Gazelle.class, "Gazelle", GazelleID);
		LanguageRegistry.instance().addStringLocalization("Gazelle", "en_US", "Gazelle");
		LanguageRegistry.instance().addStringLocalization("entity.Gazelle.name", "en_US", "Gazelle");
		EntityRegistry.instance().registerModEntity(Gazelle.class, "Gazelle", GazelleID, this, 64, 1, false);
		
		OstrichID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Ostrich.class, "Ostrich", OstrichID);
		LanguageRegistry.instance().addStringLocalization("Ostrich", "en_US", "Ostrich");
		LanguageRegistry.instance().addStringLocalization("entity.Ostrich.name", "en_US", "Ostrich");
		
		EntityRegistry.instance().registerModEntity(Ostrich.class, "Ostrich", OstrichID, this, 64, 1, true);
		
		TrooperBugID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(TrooperBug.class, "Jumpy Bug", TrooperBugID);
		LanguageRegistry.instance().addStringLocalization("Jumpy Bug", "en_US", "Jumpy Bug");
		LanguageRegistry.instance().addStringLocalization("entity.Jumpy Bug.name", "en_US", "Jumpy Bug");
		EntityRegistry.instance().registerModEntity(TrooperBug.class, "Jumpy Bug", TrooperBugID, this, 64, 1, false);
		
		SpitBugID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(SpitBug.class, "Spit Bug", SpitBugID);
		LanguageRegistry.instance().addStringLocalization("Spit Bug", "en_US", "Spit Bug");
		LanguageRegistry.instance().addStringLocalization("entity.Spit Bug.name", "en_US", "Spit Bug");
		EntityRegistry.instance().registerModEntity(SpitBug.class, "Spit Bug", SpitBugID, this, 64, 1, false);
		
		StinkBugID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(StinkBug.class, "Stink Bug", StinkBugID);
		LanguageRegistry.instance().addStringLocalization("Stink Bug", "en_US", "Stink Bug");
		LanguageRegistry.instance().addStringLocalization("entity.Stink Bug.name", "en_US", "Stink Bug");
		EntityRegistry.instance().registerModEntity(StinkBug.class, "Stink Bug", StinkBugID, this, 32, 1, false);
		
		TshirtID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Tshirt.class, "T-Shirt", TshirtID);
		LanguageRegistry.instance().addStringLocalization("entity.T-Shirt.name", "en_US", "T-Shirt!");
		LanguageRegistry.instance().addStringLocalization("T-Shirt", "en_US", "T-Shirt!");
		EntityRegistry.instance().registerModEntity(Tshirt.class, "T-Shirt", TshirtID, this, 32, 1, false);
		
		IslandID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Island.class, "Island", IslandID);
		LanguageRegistry.instance().addStringLocalization("Island", "en_US", "Light Floating Island");
		LanguageRegistry.instance().addStringLocalization("entity.Island.name", "en_US", "Light Floating Island");
		EntityRegistry.instance().registerModEntity(Island.class, "Island", IslandID, this, 64, 1, false);
		
		IslandTooID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(IslandToo.class, "IslandToo", IslandTooID);
		LanguageRegistry.instance().addStringLocalization("IslandToo", "en_US", "Dark Floating Island");
		LanguageRegistry.instance().addStringLocalization("entity.IslandToo.name", "en_US", "Dark Floating Island");
		EntityRegistry.instance().registerModEntity(IslandToo.class, "IslandToo", IslandTooID, this, 64, 1, false);
		
		CreepingHorrorID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(CreepingHorror.class, "Creeping Horror", CreepingHorrorID);
		LanguageRegistry.instance().addStringLocalization("Creeping Horror", "en_US", "Creeping Horror");
		LanguageRegistry.instance().addStringLocalization("entity.Creeping Horror.name", "en_US", "Creeping Horror");
		EntityRegistry.instance().registerModEntity(CreepingHorror.class, "Creeping Horror", CreepingHorrorID, this, 64, 1, false);
		
		TerribleTerrorID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(TerribleTerror.class, "Terrible Terror", TerribleTerrorID);
		LanguageRegistry.instance().addStringLocalization("Terrible Terror", "en_US", "Terrible Terror");
		LanguageRegistry.instance().addStringLocalization("entity.Terrible Terror.name", "en_US", "Terrible Terror");
		EntityRegistry.instance().registerModEntity(TerribleTerror.class, "Terrible Terror", TerribleTerrorID, this, 64, 1, false);
		
		CliffRacerID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(CliffRacer.class, "Cliff Racer", CliffRacerID);
		LanguageRegistry.instance().addStringLocalization("Cliff Racer", "en_US", "Cliff Racer");
		LanguageRegistry.instance().addStringLocalization("entity.Cliff Racer.name", "en_US", "Cliff Racer");
		EntityRegistry.instance().registerModEntity(CliffRacer.class, "Cliff Racer", CliffRacerID, this, 32, 1, false);
		
		TriffidID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Triffid.class, "Triffid", TriffidID);
		LanguageRegistry.instance().addStringLocalization("Triffid", "en_US", "Triffid");
		LanguageRegistry.instance().addStringLocalization("entity.Triffid.name", "en_US", "Triffid");
		EntityRegistry.instance().registerModEntity(Triffid.class, "Triffid", TriffidID, this, 64, 1, false);
		
		PitchBlackID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(PitchBlack.class, "Nightmare", PitchBlackID);
		LanguageRegistry.instance().addStringLocalization("Nightmare", "en_US", "Nightmare");
		LanguageRegistry.instance().addStringLocalization("entity.Nightmare.name", "en_US", "Nightmare");
		EntityRegistry.instance().registerModEntity(PitchBlack.class, "Nightmare", PitchBlackID, this, 64, 1, false);
		
		LurkingTerrorID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(LurkingTerror.class, "Lurking Terror", LurkingTerrorID);
		LanguageRegistry.instance().addStringLocalization("Lurking Terror", "en_US", "Lurking Terror");
		LanguageRegistry.instance().addStringLocalization("entity.Lurking Terror.name", "en_US", "Lurking Terror");
		EntityRegistry.instance().registerModEntity(LurkingTerror.class, "Lurking Terror", LurkingTerrorID, this, 64, 1, false);
		
		GodzillaID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Godzilla.class, "Mobzilla", GodzillaID);
		LanguageRegistry.instance().addStringLocalization("Mobzilla", "en_US", "Mobzilla");
		LanguageRegistry.instance().addStringLocalization("entity.Mobzilla.name", "en_US", "Mobzilla");
		EntityRegistry.instance().registerModEntity(Godzilla.class, "Mobzilla", GodzillaID, this, 128, 1, false);
		
		
		GhostID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Ghost.class, "Ghost", GhostID);
		LanguageRegistry.instance().addStringLocalization("entity.Ghost.name", "en_US", "Ghost");
		LanguageRegistry.instance().addStringLocalization("Ghost", "en_US", "Ghost");
		EntityRegistry.instance().registerModEntity(Ghost.class, "Ghost", GhostID, this, 32, 1, false);
		
		
		GhostSkellyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(GhostSkelly.class, "Ghost Pumpkin Skelly", GhostSkellyID);
		LanguageRegistry.instance().addStringLocalization("entity.Ghost Pumpkin Skelly.name", "en_US", "Ghost Pumpkin Skelly");
		LanguageRegistry.instance().addStringLocalization("Ghost Pumpkin Skelly", "en_US", "Ghost Pumpkin Skelly");
		EntityRegistry.instance().registerModEntity(GhostSkelly.class, "Ghost Pumpkin Skelly", GhostSkellyID, this, 64, 1, false);
		
		
		WormSmallID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(WormSmall.class, "Small Worm", WormSmallID);
		LanguageRegistry.instance().addStringLocalization("Small Worm", "en_US", "Small Worm");
		LanguageRegistry.instance().addStringLocalization("entity.Small Worm.name", "en_US", "Small Worm");
		EntityRegistry.instance().registerModEntity(WormSmall.class, "Small Worm", WormSmallID, this, 32, 1, false);
		
		WormMediumID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(WormMedium.class, "Medium Worm", WormMediumID);
		LanguageRegistry.instance().addStringLocalization("Medium Worm", "en_US", "Medium Worm");
		LanguageRegistry.instance().addStringLocalization("entity.Medium Worm.name", "en_US", "Medium Worm");
		EntityRegistry.instance().registerModEntity(WormMedium.class, "Medium Worm", WormMediumID, this, 64, 1, false);
		
		WormLargeID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(WormLarge.class, "Large Worm", WormLargeID);
		LanguageRegistry.instance().addStringLocalization("Large Worm", "en_US", "Large Worm");
		LanguageRegistry.instance().addStringLocalization("entity.Large Worm.name", "en_US", "Large Worm");
		EntityRegistry.instance().registerModEntity(WormLarge.class, "Large Worm", WormLargeID, this, 64, 1, false);
		
		
		CassowaryID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Cassowary.class, "Cassowary", CassowaryID);
		LanguageRegistry.instance().addStringLocalization("Cassowary", "en_US", "Cassowary");
		LanguageRegistry.instance().addStringLocalization("entity.Cassowary.name", "en_US", "Cassowary");
		EntityRegistry.instance().registerModEntity(Cassowary.class, "Cassowary", CassowaryID, this, 64, 1, false);
		
		CloudSharkID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(CloudShark.class, "Cloud Shark", CloudSharkID);
		LanguageRegistry.instance().addStringLocalization("Cloud Shark", "en_US", "Cloud Shark");
		LanguageRegistry.instance().addStringLocalization("entity.Cloud Shark.name", "en_US", "Cloud Shark");
		EntityRegistry.instance().registerModEntity(CloudShark.class, "Cloud Shark", CloudSharkID, this, 64, 1, false);
		
		GoldFishID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(GoldFish.class, "Gold Fish", GoldFishID);
		LanguageRegistry.instance().addStringLocalization("Gold Fish", "en_US", "Goldfish");
		LanguageRegistry.instance().addStringLocalization("entity.Gold Fish.name", "en_US", "Goldfish");
		EntityRegistry.instance().registerModEntity(GoldFish.class, "Gold Fish", GoldFishID, this, 32, 1, false);
		
		LeafMonsterID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(LeafMonster.class, "Leaf Monster", LeafMonsterID);
		LanguageRegistry.instance().addStringLocalization("Leaf Monster", "en_US", "LeafMonster");
		LanguageRegistry.instance().addStringLocalization("entity.Leaf Monster.name", "en_US", "LeafMonster");
		EntityRegistry.instance().registerModEntity(LeafMonster.class, "Leaf Monster", LeafMonsterID, this, 64, 1, false);
		
		GodzillaHeadID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(GodzillaHead.class, "MobzillaHead", GodzillaHeadID);
		EntityRegistry.instance().registerModEntity(GodzillaHead.class, "MobzillaHead", GodzillaHeadID, this, 128, 10, true);
		
		EnderKnightID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EnderKnight.class, "Ender Knight", EnderKnightID);
		LanguageRegistry.instance().addStringLocalization("Ender Knight", "en_US", "Ender Knight");
		LanguageRegistry.instance().addStringLocalization("entity.Ender Knight.name", "en_US", "Ender Knight");
		EntityRegistry.instance().registerModEntity(EnderKnight.class, "Ender Knight", EnderKnightID, this, 64, 1, false);
		
		EnderReaperID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EnderReaper.class, "Ender Reaper", EnderReaperID);
		LanguageRegistry.instance().addStringLocalization("Ender Reaper", "en_US", "Ender Reaper");
		LanguageRegistry.instance().addStringLocalization("entity.Ender Reaper.name", "en_US", "Ender Reaper");
		EntityRegistry.instance().registerModEntity(EnderReaper.class, "Ender Reaper", EnderReaperID, this, 64, 1, false);
		
		BeaverID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Beaver.class, "Beaver", BeaverID);
		LanguageRegistry.instance().addStringLocalization("Beaver", "en_US", "Beaver");
		LanguageRegistry.instance().addStringLocalization("entity.Beaver.name", "en_US", "Beaver");
		EntityRegistry.instance().registerModEntity(Beaver.class, "Beaver", BeaverID, this, 64, 1, false);
		
		TermiteID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Termite.class, "Termite", TermiteID);
		LanguageRegistry.instance().addStringLocalization("Termite", "en_US", "Termite");
		LanguageRegistry.instance().addStringLocalization("entity.Termite.name", "en_US", "Termite");
		EntityRegistry.instance().registerModEntity(Termite.class, "Termite", TermiteID, this, 32, 1, false);
		
		FairyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Fairy.class, "Fairy", FairyID);
		LanguageRegistry.instance().addStringLocalization("Fairy", "en_US", "Fairy");
		LanguageRegistry.instance().addStringLocalization("entity.Fairy.name", "en_US", "Fairy");
		EntityRegistry.instance().registerModEntity(Fairy.class, "Fairy", FairyID, this, 32, 1, false);
		
		PeacockID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Peacock.class, "Peacock", PeacockID);
		LanguageRegistry.instance().addStringLocalization("Peacock", "en_US", "Peacock");
		LanguageRegistry.instance().addStringLocalization("entity.Peacock.name", "en_US", "Peacock");
		EntityRegistry.instance().registerModEntity(Peacock.class, "Peacock", PeacockID, this, 64, 1, false);
		
		RotatorID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Rotator.class, "Rotator", RotatorID);
		LanguageRegistry.instance().addStringLocalization("Rotator", "en_US", "Rotator");
		LanguageRegistry.instance().addStringLocalization("entity.Rotator.name", "en_US", "Rotator");
		EntityRegistry.instance().registerModEntity(Rotator.class, "Rotator", RotatorID, this, 64, 1, false);
		
		VortexID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Vortex.class, "Vortex", VortexID);
		LanguageRegistry.instance().addStringLocalization("Vortex", "en_US", "Vortex");
		LanguageRegistry.instance().addStringLocalization("entity.Vortex.name", "en_US", "Vortex");
		EntityRegistry.instance().registerModEntity(Vortex.class, "Vortex", VortexID, this, 64, 1, false);
		
		DungeonBeastID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(DungeonBeast.class, "Dungeon Beast", DungeonBeastID);
		LanguageRegistry.instance().addStringLocalization("Dungeon Beast", "en_US", "Dungeon Beast");
		LanguageRegistry.instance().addStringLocalization("entity.Dungeon Beast.name", "en_US", "Dungeon Beast");
		EntityRegistry.instance().registerModEntity(DungeonBeast.class, "Dungeon Beast", DungeonBeastID, this, 64, 1, false);
		
		RatID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Rat.class, "Rat", RatID);
		LanguageRegistry.instance().addStringLocalization("Rat", "en_US", "Rat");
		LanguageRegistry.instance().addStringLocalization("entity.Rat.name", "en_US", "Rat");
		EntityRegistry.instance().registerModEntity(Rat.class, "Rat", RatID, this, 32, 1, false);
		
		FlounderID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Flounder.class, "Flounder", FlounderID);
		LanguageRegistry.instance().addStringLocalization("Flounder", "en_US", "Flounder");
		LanguageRegistry.instance().addStringLocalization("entity.Flounder.name", "en_US", "Flounder");
		EntityRegistry.instance().registerModEntity(Flounder.class, "Flounder", FlounderID, this, 32, 1, false);
		
		WhaleID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Whale.class, "Whale", WhaleID);
		LanguageRegistry.instance().addStringLocalization("Whale", "en_US", "Whale");
		LanguageRegistry.instance().addStringLocalization("entity.Whale.name", "en_US", "Whale");
		EntityRegistry.instance().registerModEntity(Whale.class, "Whale", WhaleID, this, 64, 1, false);
		
		IrukandjiID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Irukandji.class, "Irukandji", IrukandjiID);
		LanguageRegistry.instance().addStringLocalization("Irukandji", "en_US", "Irukandji");
		LanguageRegistry.instance().addStringLocalization("entity.Irukandji.name", "en_US", "Irukandji");
		EntityRegistry.instance().registerModEntity(Irukandji.class, "Irukandji", IrukandjiID, this, 32, 1, false);
		
		SkateID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Skate.class, "Skate", SkateID);
		LanguageRegistry.instance().addStringLocalization("Skate", "en_US", "Skate");
		LanguageRegistry.instance().addStringLocalization("entity.Skate.name", "en_US", "Skate");
		EntityRegistry.instance().registerModEntity(Skate.class, "Skate", SkateID, this, 32, 1, false);
		
		UrchinID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Urchin.class, "Crystal Urchin", UrchinID);
		LanguageRegistry.instance().addStringLocalization("Crystal Urchin", "en_US", "Crystal Urchin");
		LanguageRegistry.instance().addStringLocalization("entity.Crystal Urchin.name", "en_US", "Crystal Urchin");
		EntityRegistry.instance().registerModEntity(Urchin.class, "Crystal Urchin", UrchinID, this, 64, 1, false);
		
		
		MantisID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Mantis.class, "Mantis", MantisID);
		LanguageRegistry.instance().addStringLocalization("Mantis", "en_US", "Mantis");
		LanguageRegistry.instance().addStringLocalization("entity.Mantis.name", "en_US", "Mantis");
		EntityRegistry.instance().registerModEntity(Mantis.class, "Mantis", MantisID, this, 64, 1, false);
		
		
		HerculesBeetleID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(HerculesBeetle.class, "Hercules Beetle", HerculesBeetleID);
		LanguageRegistry.instance().addStringLocalization("Hercules Beetle", "en_US", "Hercules Beetle");
		LanguageRegistry.instance().addStringLocalization("entity.Hercules Beetle.name", "en_US", "Hercules Beetle");
		EntityRegistry.instance().registerModEntity(HerculesBeetle.class, "Hercules Beetle", HerculesBeetleID, this, 64, 1, false);
		
		TRexID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(TRex.class, "T. Rex", TRexID);
		LanguageRegistry.instance().addStringLocalization("T. Rex", "en_US", "T. Rex");
		LanguageRegistry.instance().addStringLocalization("entity.T. Rex.name", "en_US", "T. Rex");
		EntityRegistry.instance().registerModEntity(TRex.class, "T. Rex", TRexID, this, 64, 1, false);
		
		StinkyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Stinky.class, "Stinky", StinkyID);
		LanguageRegistry.instance().addStringLocalization("Stinky", "en_US", "Stinky");
		LanguageRegistry.instance().addStringLocalization("entity.Stinky.name", "en_US", "Stinky");
		EntityRegistry.instance().registerModEntity(Stinky.class, "Stinky", StinkyID, this, 64, 1, false);
		
		CoinID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Coin.class, "Coin", CoinID);
		LanguageRegistry.instance().addStringLocalization("entity.Coin.name", "en_US", "Coin!");
		LanguageRegistry.instance().addStringLocalization("Coin", "en_US", "Coin!");
		EntityRegistry.instance().registerModEntity(Coin.class, "Coin", CoinID, this, 64, 1, false);
		
		
		TheKingID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(TheKing.class, "The King", TheKingID);
		LanguageRegistry.instance().addStringLocalization("The King", "en_US", "The King");
		LanguageRegistry.instance().addStringLocalization("entity.The King.name", "en_US", "The King");
		EntityRegistry.instance().registerModEntity(TheKing.class, "The King", TheKingID, this, 128, 1, false);
		
		KingHeadID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(KingHead.class, "KingHead", KingHeadID);
		EntityRegistry.instance().registerModEntity(KingHead.class, "KingHead", KingHeadID, this, 128, 10, true);
		
		TheQueenID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(TheQueen.class, "The Queen", TheQueenID);
		LanguageRegistry.instance().addStringLocalization("The Queen", "en_US", "The Queen");
		LanguageRegistry.instance().addStringLocalization("entity.The Queen.name", "en_US", "The Queen");
		EntityRegistry.instance().registerModEntity(TheQueen.class, "The Queen", TheQueenID, this, 128, 1, false);
		
		QueenHeadID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(QueenHead.class, "QueenHead", QueenHeadID);
		EntityRegistry.instance().registerModEntity(QueenHead.class, "QueenHead", QueenHeadID, this, 128, 10, true);
		
		BoyfriendID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Boyfriend.class, "Boyfriend", BoyfriendID);
		LanguageRegistry.instance().addStringLocalization("Boyfriend", "en_US", "Boyfriend");
		LanguageRegistry.instance().addStringLocalization("entity.Boyfriend.name", "en_US", "Boyfriend");
		EntityRegistry.instance().registerModEntity(Boyfriend.class, "Boyfriend", BoyfriendID, this, 64, 1, false);
		
		ThePrinceID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(ThePrince.class, "The Prince", ThePrinceID);
		LanguageRegistry.instance().addStringLocalization("The Prince", "en_US", "The Prince");
		LanguageRegistry.instance().addStringLocalization("entity.The Prince.name", "en_US", "The Prince");
		EntityRegistry.instance().registerModEntity(ThePrince.class, "The Prince", ThePrinceID, this, 64, 1, false);
		
		MolenoidID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Molenoid.class, "Molenoid", MolenoidID);
		LanguageRegistry.instance().addStringLocalization("Molenoid", "en_US", "Molenoid");
		LanguageRegistry.instance().addStringLocalization("entity.Molenoid.name", "en_US", "Molenoid");
		EntityRegistry.instance().registerModEntity(Molenoid.class, "Molenoid", MolenoidID, this, 64, 1, false);
		
		SeaMonsterID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(SeaMonster.class, "Sea Monster", SeaMonsterID);
		LanguageRegistry.instance().addStringLocalization("Sea Monster", "en_US", "Sea Monster");
		LanguageRegistry.instance().addStringLocalization("entity.Sea Monster.name", "en_US", "Sea Monster");
		EntityRegistry.instance().registerModEntity(SeaMonster.class, "Sea Monster", SeaMonsterID, this, 64, 1, false);
		
		SeaViperID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(SeaViper.class, "Sea Viper", SeaViperID);
		LanguageRegistry.instance().addStringLocalization("Sea Viper", "en_US", "Sea Viper");
		LanguageRegistry.instance().addStringLocalization("entity.Sea Viper.name", "en_US", "Sea Viper");
		EntityRegistry.instance().registerModEntity(SeaViper.class, "Sea Viper", SeaViperID, this, 64, 1, false);
		
		EasterBunnyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EasterBunny.class, "Easter Bunny", EasterBunnyID);
		LanguageRegistry.instance().addStringLocalization("Easter Bunny", "en_US", "Easter Bunny");
		LanguageRegistry.instance().addStringLocalization("entity.Easter Bunny.name", "en_US", "Easter Bunny");
		EntityRegistry.instance().registerModEntity(EasterBunny.class, "EasterBunny", EasterBunnyID, this, 64, 1, false);
		
		CaterKillerID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(CaterKiller.class, "CaterKiller", CaterKillerID);
		LanguageRegistry.instance().addStringLocalization("CaterKiller", "en_US", "CaterKiller");
		LanguageRegistry.instance().addStringLocalization("entity.CaterKiller.name", "en_US", "CaterKiller");
		EntityRegistry.instance().registerModEntity(CaterKiller.class, "CaterKiller", CaterKillerID, this, 64, 1, false);
		
		CrystalCowID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(CrystalCow.class, "Crystal Apple Cow", CrystalCowID);
		LanguageRegistry.instance().addStringLocalization("Crystal Apple Cow", "en_US", "Crystal Apple Cow");
		LanguageRegistry.instance().addStringLocalization("entity.Crystal Apple Cow.name", "en_US", "Crystal Apple Cow");
		EntityRegistry.instance().registerModEntity(CrystalCow.class, "Crystal Apple Cow", CrystalCowID, this, 64, 1, false);
		
		LeonID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Leon.class, "Leonopteryx", LeonID);
		LanguageRegistry.instance().addStringLocalization("Leonopteryx", "en_US", "Leonopteryx");
		LanguageRegistry.instance().addStringLocalization("entity.Leonopteryx.name", "en_US", "Leonopteryx");
		EntityRegistry.instance().registerModEntity(Leon.class, "Leonopteryx", LeonID, this, 64, 1, false);
		
		HammerheadID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Hammerhead.class, "Hammerhead", HammerheadID);
		LanguageRegistry.instance().addStringLocalization("Hammerhead", "en_US", "Hammerhead");
		LanguageRegistry.instance().addStringLocalization("entity.Hammerhead.name", "en_US", "Hammerhead");
		EntityRegistry.instance().registerModEntity(Hammerhead.class, "Hammerhead", HammerheadID, this, 64, 1, false);
		
		RubberDuckyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(RubberDucky.class, "Rubber Ducky", RubberDuckyID);
		LanguageRegistry.instance().addStringLocalization("Rubber Ducky", "en_US", "Rubber Ducky");
		LanguageRegistry.instance().addStringLocalization("entity.Rubber Ducky.name", "en_US", "Rubber Ducky");
		EntityRegistry.instance().registerModEntity(RubberDucky.class, "Rubber Ducky", RubberDuckyID, this, 64, 1, false);
		
		ThePrinceTeenID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(ThePrinceTeen.class, "The Young Prince", ThePrinceTeenID);
		LanguageRegistry.instance().addStringLocalization("The Young Prince", "en_US", "The Young Prince");
		LanguageRegistry.instance().addStringLocalization("entity.The Young Prince.name", "en_US", "The Young Prince");
		EntityRegistry.instance().registerModEntity(ThePrinceTeen.class, "The Young Prince", ThePrinceTeenID, this, 64, 1, false);
		
		BandPID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(BandP.class, "Criminal", BandPID);
		LanguageRegistry.instance().addStringLocalization("Criminal", "en_US", "Criminal");
		LanguageRegistry.instance().addStringLocalization("entity.Criminal.name", "en_US", "Criminal");
		EntityRegistry.instance().registerModEntity(BandP.class, "Criminal", BandPID, this, 64, 1, false);
		
		RockBaseID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(RockBase.class, "Rock", RockBaseID, 1118481, 16777215);
		LanguageRegistry.instance().addStringLocalization("Rock", "en_US", "Rock");
		LanguageRegistry.instance().addStringLocalization("entity.Rock.name", "en_US", "Rock");
		EntityRegistry.instance().registerModEntity(RockBase.class, "Rock", RockBaseID, this, 32, 1, false);
		
		BrutalflyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Brutalfly.class, "Brutalfly", BrutalflyID);
		LanguageRegistry.instance().addStringLocalization("Brutalfly", "en_US", "Brutalfly");
		LanguageRegistry.instance().addStringLocalization("entity.Brutalfly.name", "en_US", "Brutalfly");
		EntityRegistry.instance().registerModEntity(Brutalfly.class, "Brutalfly", BrutalflyID, this, 128, 1, false);
		
		NastysaurusID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Nastysaurus.class, "Nastysaurus", NastysaurusID);
		LanguageRegistry.instance().addStringLocalization("Nastysaurus", "en_US", "Nastysaurus");
		LanguageRegistry.instance().addStringLocalization("entity.Nastysaurus.name", "en_US", "Nastysaurus");
		EntityRegistry.instance().registerModEntity(Nastysaurus.class, "Nastysaurus", NastysaurusID, this, 128, 1, false);
		
		PointysaurusID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Pointysaurus.class, "Pointysaurus", PointysaurusID);
		LanguageRegistry.instance().addStringLocalization("Pointysaurus", "en_US", "Pointysaurus");
		LanguageRegistry.instance().addStringLocalization("entity.Pointysaurus.name", "en_US", "Pointysaurus");
		EntityRegistry.instance().registerModEntity(Pointysaurus.class, "Pointysaurus", PointysaurusID, this, 64, 1, false);
		
		CricketID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Cricket.class, "Cricket", CricketID);
		LanguageRegistry.instance().addStringLocalization("Cricket", "en_US", "Cricket");
		LanguageRegistry.instance().addStringLocalization("entity.Cricket.name", "en_US", "Cricket");
		EntityRegistry.instance().registerModEntity(Cricket.class, "Cricket", CricketID, this, 32, 1, false);
		
		ThePrincessID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(ThePrincess.class, "The Princess", ThePrincessID);
		LanguageRegistry.instance().addStringLocalization("The Princess", "en_US", "The Princess");
		LanguageRegistry.instance().addStringLocalization("entity.The Princess.name", "en_US", "The Princess");
		EntityRegistry.instance().registerModEntity(ThePrincess.class, "The Princess", ThePrincessID, this, 64, 1, false);
		
		FrogID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Frog.class, "Frog", FrogID);
		LanguageRegistry.instance().addStringLocalization("Frog", "en_US", "Frog");
		LanguageRegistry.instance().addStringLocalization("entity.Frog.name", "en_US", "Frog");
		EntityRegistry.instance().registerModEntity(Frog.class, "Frog", FrogID, this, 32, 1, false);
		
		ThePrinceAdultID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(ThePrinceAdult.class, "The Young Adult Prince", ThePrinceAdultID);
		LanguageRegistry.instance().addStringLocalization("The Young Adult Prince", "en_US", "The Young Adult Prince");
		LanguageRegistry.instance().addStringLocalization("entity.The Young Adult Prince.name", "en_US", "The Young Adult Prince");
		EntityRegistry.instance().registerModEntity(ThePrinceAdult.class, "The Young Adult Prince", ThePrinceAdultID, this, 128, 1, false);
		
		SpiderRobotID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(SpiderRobot.class, "Robot Spider", SpiderRobotID);
		LanguageRegistry.instance().addStringLocalization("entity.Robot Spider.name", "en_US", "Robot Spider");
		LanguageRegistry.instance().addStringLocalization("Robot Spider", "en_US", "Robot Spider");
		EntityRegistry.instance().registerModEntity(SpiderRobot.class, "Robot Spider", SpiderRobotID, this, 128, 1, false);
		
		SpiderDriverID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(SpiderDriver.class, "Spider Driver", SpiderDriverID);
		LanguageRegistry.instance().addStringLocalization("entity.Spider Driver.name", "en_US", "Spider Driver");
		LanguageRegistry.instance().addStringLocalization("Spider Driver", "en_US", "Spider Driver");
		EntityRegistry.instance().registerModEntity(SpiderDriver.class, "Spider Driver", SpiderDriverID, this, 64, 1, false);
		
		JefferyID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(GiantRobot.class, "Jeffery", JefferyID);
		LanguageRegistry.instance().addStringLocalization("entity.Jeffery.name", "en_US", "Jeffery");
		LanguageRegistry.instance().addStringLocalization("Jeffery", "en_US", "Jeffery");
		EntityRegistry.instance().registerModEntity(GiantRobot.class, "Jeffery", JefferyID, this, 128, 1, false);
		
		AntRobotID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(AntRobot.class, "Robot Red Ant", AntRobotID);
		LanguageRegistry.instance().addStringLocalization("entity.Robot Red Ant.name", "en_US", "Robot Red Ant");
		LanguageRegistry.instance().addStringLocalization("Robot Red Ant", "en_US", "Robot Red Ant");
		EntityRegistry.instance().registerModEntity(AntRobot.class, "Robot Red Ant", AntRobotID, this, 128, 1, false);
		
		CrabID = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Crab.class, "Crab", CrabID);
		LanguageRegistry.instance().addStringLocalization("Crab", "en_US", "Crab");
		LanguageRegistry.instance().addStringLocalization("entity.Crab.name", "en_US", "Crab");
		EntityRegistry.instance().registerModEntity(Crab.class, "Crab", CrabID, this, 64, 1, false);
		
		
		
		
		
		GregorianCalendar gcalendar = new GregorianCalendar();
		
		int nowmonth = gcalendar.get(2);
		int nowday = gcalendar.get(5);
		
		
		if (nowmonth == 9 && nowday == 31) {
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.beach});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau_F});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesa});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaigaHills});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaiga});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
			
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.beach});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau_F});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesa});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaigaHills});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaiga});
			EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}
		

		if (nowmonth == 1 && nowday == 14) {
			valentines_day = 1;
		}

		
		
		
		
		
		
		if (nowmonth == 3 && nowday == 20) {
			easter_day = 1;
		}

		
		
		
		
		
		
		
		if (GirlfriendEnable != 0) {
			EntityRegistry.addSpawn(Girlfriend.class, 30, 8, 15, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.beach});
			EntityRegistry.addSpawn(Girlfriend.class, 10, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Girlfriend.class, 8, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Girlfriend.class, 10, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Girlfriend.class, 10, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
			EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Girlfriend.class, 2, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Girlfriend.class, 2, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (BoyfriendEnable != 0) {
			EntityRegistry.addSpawn(Boyfriend.class, 30, 8, 15, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.beach});
			EntityRegistry.addSpawn(Boyfriend.class, 10, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Boyfriend.class, 8, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Boyfriend.class, 10, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Boyfriend.class, 10, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
			EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Boyfriend.class, 2, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Boyfriend.class, 2, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (BeaverEnable != 0) {
			EntityRegistry.addSpawn(Beaver.class, 10, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Beaver.class, 3, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Beaver.class, 2, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Beaver.class, 2, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Beaver.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Beaver.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.taiga});
		}

		if (CowEnable != 0) {
			
			EntityRegistry.addSpawn(RedCow.class, 8, 4, 8, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(RedCow.class, 8, 4, 8, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(RedCow.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(RedCow.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(RedCow.class, 8, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(RedCow.class, 2, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
			
			
			EntityRegistry.addSpawn(GoldCow.class, 5, 2, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(GoldCow.class, 5, 2, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(GoldCow.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(GoldCow.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.taiga});
			
			
			EntityRegistry.addSpawn(EnchantedCow.class, 3, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(EnchantedCow.class, 3, 2, 4, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(EnchantedCow.class, 5, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(EnchantedCow.class, 15, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.mushroomIsland});
		}

		if (CriminalEnable != 0) {
			EntityRegistry.addSpawn(BandP.class, 20, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(BandP.class, 20, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(BandP.class, 20, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
		}

		if (WormEnable != 0) {
			EntityRegistry.addSpawn(WormLarge.class, 25, 1, 1, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(WormLarge.class, 15, 1, 1, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(WormLarge.class, 10, 1, 1, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}
		

		if (ButterflyEnable != 0) {
			EntityRegistry.addSpawn(EntityButterfly.class, 8, 5, 15, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.beach});
			EntityRegistry.addSpawn(EntityButterfly.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(EntityButterfly.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(EntityButterfly.class, 30, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(EntityButterfly.class, 20, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(EntityButterfly.class, 20, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(EntityButterfly.class, 10, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(EntityButterfly.class, 20, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(EntityButterfly.class, 20, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(EntityButterfly.class, 20, 4, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(EntityButterfly.class, 10, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(EntityButterfly.class, 10, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}
		

		if (MothEnable != 0) {
			EntityRegistry.addSpawn(EntityLunaMoth.class, 8, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 8, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 20, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 20, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 20, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (CassowaryEnable != 0) {
			EntityRegistry.addSpawn(Cassowary.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(Cassowary.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(Cassowary.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsPlus});
			EntityRegistry.addSpawn(Cassowary.class, 5, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Cassowary.class, 5, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Cassowary.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Cassowary.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaigaHills});
			EntityRegistry.addSpawn(Cassowary.class, 3, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Cassowary.class, 10, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (EasterBunnyEnable != 0 && easter_day != 0) {
			EntityRegistry.addSpawn(EasterBunny.class, 10, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(EasterBunny.class, 10, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(EasterBunny.class, 10, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(EasterBunny.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(EasterBunny.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(EasterBunny.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(EasterBunny.class, 8, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
		}

		
		
		if (FireflyEnable != 0) {
			EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Firefly.class, 10, 4, 8, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Firefly.class, 10, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
			EntityRegistry.addSpawn(Firefly.class, 15, 3, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Firefly.class, 15, 3, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Firefly.class, 15, 2, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Firefly.class, 15, 2, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Firefly.class, 15, 2, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaigaHills});
			EntityRegistry.addSpawn(Firefly.class, 10, 2, 8, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Firefly.class, 10, 2, 8, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (WhaleEnable != 0) {
			EntityRegistry.addSpawn(Whale.class, 1, 1, 2, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.deepOcean});
		}

		if (BeeEnable != 0) {
			EntityRegistry.addSpawn(Bee.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Bee.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Bee.class, 5, 3, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Bee.class, 5, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Bee.class, 3, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Bee.class, 3, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Bee.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Bee.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Bee.class, 3, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Bee.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (MantisEnable != 0) {
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Mantis.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Mantis.class, 1, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (HerculesBeetleEnable != 0) {
			EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(HerculesBeetle.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taigaHills});
			EntityRegistry.addSpawn(HerculesBeetle.class, 5, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaigaHills});
			EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaigaHills});
		}

		if (MolenoidEnable != 0) {
			EntityRegistry.addSpawn(Molenoid.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Molenoid.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Molenoid.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}

		if (CaterKillerEnable != 0) {
			EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(CaterKiller.class, 4, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(CaterKiller.class, 4, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(CaterKiller.class, 6, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(CaterKiller.class, 10, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}

		if (ChipmunkEnable != 0) {
			EntityRegistry.addSpawn(Chipmunk.class, 8, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Chipmunk.class, 5, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Chipmunk.class, 4, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Chipmunk.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Chipmunk.class, 5, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Chipmunk.class, 4, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Chipmunk.class, 10, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
			EntityRegistry.addSpawn(Chipmunk.class, 2, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Chipmunk.class, 6, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
		}

		if (OstrichEnable != 0) {
			EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
			EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}
		

		if (CephadromeEnable != 0) {
			EntityRegistry.addSpawn(Cephadrome.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.icePlains});
			EntityRegistry.addSpawn(Cephadrome.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaiga});
		}
		

		if (MosquitoEnable != 0) {
			EntityRegistry.addSpawn(EntityMosquito.class, 30, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(EntityMosquito.class, 20, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(EntityMosquito.class, 20, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(EntityMosquito.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}
		

		if (GhostEnable != 0) {
			EntityRegistry.addSpawn(Ghost.class, 15, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaiga});
			EntityRegistry.addSpawn(Ghost.class, 10, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taigaHills});
			EntityRegistry.addSpawn(Ghost.class, 6, 4, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.frozenRiver});
			EntityRegistry.addSpawn(Ghost.class, 2, 1, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Ghost.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}
		

		if (GhostSkellyEnable != 0) {
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaiga});
			EntityRegistry.addSpawn(GhostSkelly.class, 10, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taigaHills});
			EntityRegistry.addSpawn(GhostSkelly.class, 6, 4, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.frozenRiver});
			EntityRegistry.addSpawn(GhostSkelly.class, 2, 1, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(GhostSkelly.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}

		if (DragonflyEnable != 0) {
			EntityRegistry.addSpawn(Dragonfly.class, 5, 3, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Dragonfly.class, 4, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
		}
		

		if (KyuubiEnable != 0) {
			EntityRegistry.addSpawn(Kyuubi.class, 10, 1, 1, EnumCreatureType.monster, new BiomeGenBase[]{BiomeGenBase.hell});
		}

		if (StinkyEnable != 0) {
			EntityRegistry.addSpawn(Stinky.class, 2, 1, 1, EnumCreatureType.monster, new BiomeGenBase[]{BiomeGenBase.hell});
			EntityRegistry.addSpawn(Stinky.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesa});
			EntityRegistry.addSpawn(Stinky.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau});
			EntityRegistry.addSpawn(Stinky.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau_F});
		}

		if (CockateilEnable != 0) {
			EntityRegistry.addSpawn(Cockateil.class, 10, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.beach});
			EntityRegistry.addSpawn(Cockateil.class, 10, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(Cockateil.class, 10, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(Cockateil.class, 25, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Cockateil.class, 20, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Cockateil.class, 35, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Cockateil.class, 25, 5, 10, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Cockateil.class, 10, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Cockateil.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Cockateil.class, 5, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
			EntityRegistry.addSpawn(Cockateil.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Cockateil.class, 5, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Cockateil.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Cockateil.class, 15, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Cockateil.class, 11, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Cockateil.class, 11, 1, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}
		

		if (HydroliscEnable != 0) {
			EntityRegistry.addSpawn(Hydrolisc.class, 25, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Hydrolisc.class, 15, 2, 5, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Hydrolisc.class, 10, 1, 3, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Hydrolisc.class, 5, 3, 6, EnumCreatureType.creature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
		}
		

		if (MothraEnable != 0) {
			EntityRegistry.addSpawn(Mothra.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(Mothra.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsPlus});
		}
		if (BrutalflyEnable != 0) {
			EntityRegistry.addSpawn(Brutalfly.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaigaHills});
			EntityRegistry.addSpawn(Brutalfly.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsPlus});
			EntityRegistry.addSpawn(Brutalfly.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau});
		}
		if (WaterDragonEnable != 0) {
			EntityRegistry.addSpawn(WaterDragon.class, 5, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(WaterDragon.class, 3, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(WaterDragon.class, 2, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean});
			EntityRegistry.addSpawn(WaterDragon.class, 2, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
		}
		if (SeaMonsterEnable != 0) {
			EntityRegistry.addSpawn(SeaMonster.class, 4, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean});
			EntityRegistry.addSpawn(SeaMonster.class, 2, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.swampland});
		}
		if (SeaViperEnable != 0) {
			EntityRegistry.addSpawn(SeaViper.class, 3, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean});
			EntityRegistry.addSpawn(SeaViper.class, 2, 1, 1, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
		}
		if (CrabEnable != 0) {
			EntityRegistry.addSpawn(Crab.class, 2, 3, 6, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean});
			EntityRegistry.addSpawn(Crab.class, 1, 3, 6, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Crab.class, 1, 2, 4, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
		}
		if (AttackSquidEnable != 0) {
			EntityRegistry.addSpawn(AttackSquid.class, 12, 6, 10, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(AttackSquid.class, 10, 5, 9, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(AttackSquid.class, 7, 4, 8, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean});
		}
		if (LizardEnable != 0) {
			EntityRegistry.addSpawn(Lizard.class, 5, 2, 4, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Lizard.class, 4, 2, 4, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Lizard.class, 2, 2, 4, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.ocean});
		}
		if (RubberDuckyEnable != 0) {
			EntityRegistry.addSpawn(RubberDucky.class, 10, 10, 20, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(RubberDucky.class, 4, 4, 6, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.stoneBeach});
		}
		if (BasiliskEnable != 0) {
			EntityRegistry.addSpawn(Basilisk.class, 3, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Basilisk.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Basilisk.class, 4, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Basilisk.class, 15, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}
		if (EmperorScorpionEnable != 0) {
			EntityRegistry.addSpawn(EmperorScorpion.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(EmperorScorpion.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
		}
		if (TrooperBugEnable != 0) {
			EntityRegistry.addSpawn(TrooperBug.class, 3, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(TrooperBug.class, 1, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesa});
		}
		if (SpitBugEnable != 0) {
			EntityRegistry.addSpawn(SpitBug.class, 6, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
		}
		if (StinkBugEnable != 0) {
			EntityRegistry.addSpawn(StinkBug.class, 10, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(StinkBug.class, 8, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(StinkBug.class, 6, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(StinkBug.class, 4, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(StinkBug.class, 8, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
		}
		if (ScorpionEnable != 0) {
			EntityRegistry.addSpawn(Scorpion.class, 15, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(Scorpion.class, 28, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
			EntityRegistry.addSpawn(Scorpion.class, 15, 3, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savanna});
			EntityRegistry.addSpawn(Scorpion.class, 15, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
			EntityRegistry.addSpawn(Scorpion.class, 6, 1, 3, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesa});
			EntityRegistry.addSpawn(Scorpion.class, 4, 1, 3, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau});
			EntityRegistry.addSpawn(Scorpion.class, 5, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau_F});
		}

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if (LeafMonsterEnable != 0) {
			EntityRegistry.addSpawn(LeafMonster.class, 5, 2, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(LeafMonster.class, 5, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(LeafMonster.class, 3, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(LeafMonster.class, 3, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(LeafMonster.class, 3, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(LeafMonster.class, 2, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(LeafMonster.class, 2, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(LeafMonster.class, 2, 2, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
		}

		if (EnderKnightEnable != 0) {
			EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(EnderKnight.class, 2, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(EnderKnight.class, 2, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(EnderKnight.class, 2, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(EnderKnight.class, 20, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}
		if (EnderReaperEnable != 0) {
			EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHills});
			EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.extremeHillsEdge});
			EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.desert});
			EntityRegistry.addSpawn(EnderReaper.class, 38, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}

		if (CoinEnable != 0) {
			EntityRegistry.addSpawn(Coin.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Coin.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Coin.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Coin.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Coin.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.coldTaiga});
			EntityRegistry.addSpawn(Coin.class, 2, 1, 1, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
		}

		if (CricketEnable != 0) {
			EntityRegistry.addSpawn(Cricket.class, 3, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forest});
			EntityRegistry.addSpawn(Cricket.class, 2, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.forestHills});
			EntityRegistry.addSpawn(Cricket.class, 3, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Cricket.class, 2, 3, 5, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungleHills});
			EntityRegistry.addSpawn(Cricket.class, 3, 4, 8, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.plains});
			EntityRegistry.addSpawn(Cricket.class, 2, 2, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForest});
			EntityRegistry.addSpawn(Cricket.class, 2, 2, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.birchForestHills});
			EntityRegistry.addSpawn(Cricket.class, 3, 1, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
			EntityRegistry.addSpawn(Cricket.class, 2, 1, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.megaTaiga});
			EntityRegistry.addSpawn(Cricket.class, 2, 1, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
			EntityRegistry.addSpawn(Cricket.class, 1, 1, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.savannaPlateau});
		}
		if (FrogEnable != 0) {
			EntityRegistry.addSpawn(Frog.class, 20, 3, 6, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Frog.class, 3, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.river});
			EntityRegistry.addSpawn(Frog.class, 3, 3, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.jungle});
			EntityRegistry.addSpawn(Frog.class, 20, 2, 6, EnumCreatureType.waterCreature, new BiomeGenBase[]{BiomeGenBase.swampland});
			EntityRegistry.addSpawn(Frog.class, 2, 2, 6, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.swampland});
		}

		if (PeacockEnable != 0) {
			EntityRegistry.addSpawn(Peacock.class, 1, 1, 3, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesa});
			EntityRegistry.addSpawn(Peacock.class, 1, 1, 3, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.mesaPlateau});
		}

		if (FairyEnable != 0) {
			EntityRegistry.addSpawn(Fairy.class, 25, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}
		if (RatEnable != 0) {
			EntityRegistry.addSpawn(Rat.class, 35, 10, 20, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
			EntityRegistry.addSpawn(Rat.class, 25, 2, 8, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.taiga});
		}
		if (DungeonBeastEnable != 0) {
			EntityRegistry.addSpawn(DungeonBeast.class, 20, 2, 4, EnumCreatureType.ambient, new BiomeGenBase[]{BiomeGenBase.roofedForest});
		}

		
		
		
		
		
		int shoeid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(Shoes.class, "Shoes", shoeid);
		EntityRegistry.instance().registerModEntity(Shoes.class, "Shoes", shoeid, this, 64, 1, true);
		LanguageRegistry.instance().addNameForObject(MyItemShoes, "en_US", "Red Heels");
		LanguageRegistry.instance().addNameForObject(MyItemShoes_1, "en_US", "Black Heels");
		LanguageRegistry.instance().addNameForObject(MyItemShoes_2, "en_US", "Slippers");
		LanguageRegistry.instance().addNameForObject(MyItemShoes_3, "en_US", "Boots");
		LanguageRegistry.instance().addNameForObject(MyItemGameController, "en_US", "Game Controller");
		
		
		LanguageRegistry.instance().addNameForObject(UltimateHelmet, "en_US", "The Ultimate Helmet");
		LanguageRegistry.instance().addNameForObject(UltimateBody, "en_US", "The Ultimate Chestplate");
		LanguageRegistry.instance().addNameForObject(UltimateLegs, "en_US", "The Ultimate Leggings");
		LanguageRegistry.instance().addNameForObject(UltimateBoots, "en_US", "The Ultimate Boots");
		
		GameRegistry.addRecipe(new ItemStack(UltimateHelmet), new Object[]{"   ", "TIT", "U U", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(UltimateHelmet), new Object[]{"TIT", "U U", "   ", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(UltimateBody), new Object[]{"I I", "TTT", "UUU", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(UltimateLegs), new Object[]{"III", "T T", "U U", 'I', Items.iron_ingot, 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(UltimateBoots), new Object[]{"   ", "T T", "U U", 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		GameRegistry.addRecipe(new ItemStack(UltimateBoots), new Object[]{"T T", "U U", "   ", 'U', MyIngotUranium, 'T', MyIngotTitanium});
		
		
		
		
		
		LanguageRegistry.instance().addNameForObject(LavaEelHelmet, "en_US", "Lava Eel Helmet");
		LanguageRegistry.instance().addNameForObject(LavaEelBody, "en_US", "Lava Eel Chestplate");
		LanguageRegistry.instance().addNameForObject(LavaEelLegs, "en_US", "Lava Eel Leggings");
		LanguageRegistry.instance().addNameForObject(LavaEelBoots, "en_US", "Lava Eel Boots");
		
		GameRegistry.addRecipe(new ItemStack(LavaEelHelmet), new Object[]{"   ", "***", "* *", '*', MyLavaEel});
		
		
		GameRegistry.addRecipe(new ItemStack(LavaEelHelmet), new Object[]{"***", "* *", "   ", '*', MyLavaEel});
		
		
		GameRegistry.addRecipe(new ItemStack(LavaEelBody), new Object[]{"* *", "***", "***", '*', MyLavaEel});
		
		
		GameRegistry.addRecipe(new ItemStack(LavaEelLegs), new Object[]{"***", "* *", "* *", '*', MyLavaEel});
		
		
		GameRegistry.addRecipe(new ItemStack(LavaEelBoots), new Object[]{"   ", "* *", "* *", '*', MyLavaEel});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(MothScaleHelmet, "en_US", "Moth Scale Helmet");
		LanguageRegistry.instance().addNameForObject(MothScaleBody, "en_US", "Moth Scale Chestplate");
		LanguageRegistry.instance().addNameForObject(MothScaleLegs, "en_US", "Moth Scale Leggings");
		LanguageRegistry.instance().addNameForObject(MothScaleBoots, "en_US", "Moth Scale Boots");
		
		GameRegistry.addRecipe(new ItemStack(MothScaleHelmet), new Object[]{"   ", "***", "* *", '*', MyMothScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MothScaleHelmet), new Object[]{"***", "* *", "   ", '*', MyMothScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MothScaleBody), new Object[]{"* *", "***", "***", '*', MyMothScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MothScaleLegs), new Object[]{"***", "* *", "* *", '*', MyMothScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MothScaleBoots), new Object[]{"   ", "* *", "* *", '*', MyMothScale});
		
		
		
		LanguageRegistry.instance().addNameForObject(EmeraldHelmet, "en_US", "Emerald Helmet");
		LanguageRegistry.instance().addNameForObject(EmeraldBody, "en_US", "Emerald Chestplate");
		LanguageRegistry.instance().addNameForObject(EmeraldLegs, "en_US", "Emerald Leggings");
		LanguageRegistry.instance().addNameForObject(EmeraldBoots, "en_US", "Emerald Boots");
		
		GameRegistry.addRecipe(new ItemStack(EmeraldHelmet), new Object[]{"   ", "***", "* *", '*', Items.emerald});
		
		
		GameRegistry.addRecipe(new ItemStack(EmeraldHelmet), new Object[]{"***", "* *", "   ", '*', Items.emerald});
		
		
		GameRegistry.addRecipe(new ItemStack(EmeraldBody), new Object[]{"* *", "***", "***", '*', Items.emerald});
		
		
		GameRegistry.addRecipe(new ItemStack(EmeraldLegs), new Object[]{"***", "* *", "* *", '*', Items.emerald});
		
		
		GameRegistry.addRecipe(new ItemStack(EmeraldBoots), new Object[]{"   ", "* *", "* *", '*', Items.emerald});
		
		
		
		LanguageRegistry.instance().addNameForObject(RubyHelmet, "en_US", "Ruby Helmet");
		LanguageRegistry.instance().addNameForObject(RubyBody, "en_US", "Ruby Chestplate");
		LanguageRegistry.instance().addNameForObject(RubyLegs, "en_US", "Ruby Leggings");
		LanguageRegistry.instance().addNameForObject(RubyBoots, "en_US", "Ruby Boots");
		
		GameRegistry.addRecipe(new ItemStack(RubyHelmet), new Object[]{"   ", "***", "* *", '*', MyRuby});
		
		
		GameRegistry.addRecipe(new ItemStack(RubyHelmet), new Object[]{"***", "* *", "   ", '*', MyRuby});
		
		
		GameRegistry.addRecipe(new ItemStack(RubyBody), new Object[]{"* *", "***", "***", '*', MyRuby});
		
		
		GameRegistry.addRecipe(new ItemStack(RubyLegs), new Object[]{"***", "* *", "* *", '*', MyRuby});
		
		
		GameRegistry.addRecipe(new ItemStack(RubyBoots), new Object[]{"   ", "* *", "* *", '*', MyRuby});
		
		
		
		LanguageRegistry.instance().addNameForObject(AmethystHelmet, "en_US", "Amethyst Helmet");
		LanguageRegistry.instance().addNameForObject(AmethystBody, "en_US", "Amethyst Chestplate");
		LanguageRegistry.instance().addNameForObject(AmethystLegs, "en_US", "Amethyst Leggings");
		LanguageRegistry.instance().addNameForObject(AmethystBoots, "en_US", "Amethyst Boots");
		
		GameRegistry.addRecipe(new ItemStack(AmethystHelmet), new Object[]{"   ", "***", "* *", '*', MyAmethyst});
		
		
		GameRegistry.addRecipe(new ItemStack(AmethystHelmet), new Object[]{"***", "* *", "   ", '*', MyAmethyst});
		
		
		GameRegistry.addRecipe(new ItemStack(AmethystBody), new Object[]{"* *", "***", "***", '*', MyAmethyst});
		
		
		GameRegistry.addRecipe(new ItemStack(AmethystLegs), new Object[]{"***", "* *", "* *", '*', MyAmethyst});
		
		
		GameRegistry.addRecipe(new ItemStack(AmethystBoots), new Object[]{"   ", "* *", "* *", '*', MyAmethyst});
		
		
		
		LanguageRegistry.instance().addNameForObject(CrystalPinkHelmet, "en_US", "Pink Tourmailine Helmet");
		LanguageRegistry.instance().addNameForObject(CrystalPinkBody, "en_US", "Pink Tourmailine Chestplate");
		LanguageRegistry.instance().addNameForObject(CrystalPinkLegs, "en_US", "Pink Tourmailine Leggings");
		LanguageRegistry.instance().addNameForObject(CrystalPinkBoots, "en_US", "Pink Tourmailine Boots");
		
		
		GameRegistry.addRecipe(new ItemStack(CrystalPinkHelmet), new Object[]{"   ", "***", "* *", '*', MyCrystalPinkIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(CrystalPinkHelmet), new Object[]{"***", "* *", "   ", '*', MyCrystalPinkIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(CrystalPinkBody), new Object[]{"* *", "***", "***", '*', MyCrystalPinkIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(CrystalPinkLegs), new Object[]{"***", "* *", "* *", '*', MyCrystalPinkIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(CrystalPinkBoots), new Object[]{"   ", "* *", "* *", '*', MyCrystalPinkIngot});
		
		
		
		LanguageRegistry.instance().addNameForObject(MobzillaHelmet, "en_US", "Mobzilla Scale Helmet");
		LanguageRegistry.instance().addNameForObject(MobzillaBody, "en_US", "Mobzilla Scale Chestplate");
		LanguageRegistry.instance().addNameForObject(MobzillaLegs, "en_US", "Mobzilla Scale Leggings");
		LanguageRegistry.instance().addNameForObject(MobzillaBoots, "en_US", "Mobzilla Scale Boots");
		
		LanguageRegistry.instance().addNameForObject(RoyalHelmet, "en_US", "Royal Guardian Helmet");
		LanguageRegistry.instance().addNameForObject(RoyalBody, "en_US", "Royal Guardian Chestplate");
		LanguageRegistry.instance().addNameForObject(RoyalLegs, "en_US", "Royal Guardian Leggings");
		LanguageRegistry.instance().addNameForObject(RoyalBoots, "en_US", "Royal Guardian Boots");
		
		LanguageRegistry.instance().addNameForObject(LapisHelmet, "en_US", "Lapis Lazuli Helmet");
		LanguageRegistry.instance().addNameForObject(LapisBody, "en_US", "Lapis Lazuli Chestplate");
		LanguageRegistry.instance().addNameForObject(LapisLegs, "en_US", "Lapis Lazuli Leggings");
		LanguageRegistry.instance().addNameForObject(LapisBoots, "en_US", "Lapis Lazuli Boots");
		
		LanguageRegistry.instance().addNameForObject(QueenHelmet, "en_US", "Queen Scale Helmet");
		LanguageRegistry.instance().addNameForObject(QueenBody, "en_US", "Queen Scale Chestplate");
		LanguageRegistry.instance().addNameForObject(QueenLegs, "en_US", "Queen Scale Leggings");
		LanguageRegistry.instance().addNameForObject(QueenBoots, "en_US", "Queen Scale Boots");
		
		
		GameRegistry.addRecipe(new ItemStack(MobzillaHelmet), new Object[]{"   ", "***", "* *", '*', MyGodzillaScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MobzillaHelmet), new Object[]{"***", "* *", "   ", '*', MyGodzillaScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MobzillaBody), new Object[]{"* *", "***", "***", '*', MyGodzillaScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MobzillaLegs), new Object[]{"***", "* *", "* *", '*', MyGodzillaScale});
		
		
		GameRegistry.addRecipe(new ItemStack(MobzillaBoots), new Object[]{"   ", "* *", "* *", '*', MyGodzillaScale});
		
		
		
		GameRegistry.addRecipe(new ItemStack(LapisHelmet), new Object[]{"   ", "***", "* *", '*', Blocks.lapis_block});
		
		
		GameRegistry.addRecipe(new ItemStack(LapisHelmet), new Object[]{"***", "* *", "   ", '*', Blocks.lapis_block});
		
		
		GameRegistry.addRecipe(new ItemStack(LapisBody), new Object[]{"* *", "***", "***", '*', Blocks.lapis_block});
		
		
		GameRegistry.addRecipe(new ItemStack(LapisLegs), new Object[]{"***", "* *", "* *", '*', Blocks.lapis_block});
		
		
		GameRegistry.addRecipe(new ItemStack(LapisBoots), new Object[]{"   ", "* *", "* *", '*', Blocks.lapis_block});
		
		
		
		GameRegistry.addRecipe(new ItemStack(QueenHelmet), new Object[]{"   ", "***", "* *", '*', MyQueenScale});
		
		
		GameRegistry.addRecipe(new ItemStack(QueenHelmet), new Object[]{"***", "* *", "   ", '*', MyQueenScale});
		
		
		GameRegistry.addRecipe(new ItemStack(QueenBody), new Object[]{"* *", "***", "***", '*', MyQueenScale});
		
		
		GameRegistry.addRecipe(new ItemStack(QueenLegs), new Object[]{"***", "* *", "* *", '*', MyQueenScale});
		
		
		GameRegistry.addRecipe(new ItemStack(QueenBoots), new Object[]{"   ", "* *", "* *", '*', MyQueenScale});
		
		
		
		
		LanguageRegistry.instance().addNameForObject(PeacockFeatherBoots, "en_US", "Peacock Feather Boots");
		LanguageRegistry.instance().addNameForObject(PeacockFeatherHelmet, "en_US", "Peacock Feather Helmet");
		LanguageRegistry.instance().addNameForObject(PeacockFeatherBody, "en_US", "Peacock Feather Chestplate");
		LanguageRegistry.instance().addNameForObject(PeacockFeatherLegs, "en_US", "Peacock Feather Leggings");
		
		GameRegistry.addRecipe(new ItemStack(PeacockFeatherHelmet), new Object[]{"   ", "***", "* *", '*', MyPeacockFeather});
		
		
		GameRegistry.addRecipe(new ItemStack(PeacockFeatherHelmet), new Object[]{"***", "* *", "   ", '*', MyPeacockFeather});
		
		
		GameRegistry.addRecipe(new ItemStack(PeacockFeatherBody), new Object[]{"* *", "***", "***", '*', MyPeacockFeather});
		
		
		GameRegistry.addRecipe(new ItemStack(PeacockFeatherLegs), new Object[]{"***", "* *", "* *", '*', MyPeacockFeather});
		
		
		GameRegistry.addRecipe(new ItemStack(PeacockFeatherBoots), new Object[]{"   ", "* *", "* *", '*', MyPeacockFeather});
		
		
		
		LanguageRegistry.instance().addNameForObject(TigersEyeHelmet, "en_US", "Tiger's Eye Helmet");
		LanguageRegistry.instance().addNameForObject(TigersEyeBody, "en_US", "Tiger's Eye Chestplate");
		LanguageRegistry.instance().addNameForObject(TigersEyeLegs, "en_US", "Tiger's Eye Leggings");
		LanguageRegistry.instance().addNameForObject(TigersEyeBoots, "en_US", "Tiger's Eye Boots");
		
		GameRegistry.addRecipe(new ItemStack(TigersEyeHelmet), new Object[]{"   ", "***", "* *", '*', MyTigersEyeIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(TigersEyeHelmet), new Object[]{"***", "* *", "   ", '*', MyTigersEyeIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(TigersEyeBody), new Object[]{"* *", "***", "***", '*', MyTigersEyeIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(TigersEyeLegs), new Object[]{"***", "* *", "* *", '*', MyTigersEyeIngot});
		
		
		GameRegistry.addRecipe(new ItemStack(TigersEyeBoots), new Object[]{"   ", "* *", "* *", '*', MyTigersEyeIngot});
		
		
		
		LanguageRegistry.instance().addNameForObject(ExperienceHelmet, "en_US", "Experience Helmet");
		LanguageRegistry.instance().addNameForObject(ExperienceBody, "en_US", "Experience Chestplate");
		LanguageRegistry.instance().addNameForObject(ExperienceLegs, "en_US", "Experience Leggings");
		LanguageRegistry.instance().addNameForObject(ExperienceBoots, "en_US", "Experience Boots");
		
		GameRegistry.addRecipe(new ItemStack(ExperienceHelmet), new Object[]{"EEE", "EAE", "EEE", 'A', EmeraldHelmet, 'E', Items.experience_bottle});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExperienceBody), new Object[]{"EEE", "EAE", "EEE", 'A', EmeraldBody, 'E', Items.experience_bottle});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExperienceLegs), new Object[]{"EEE", "EAE", "EEE", 'A', EmeraldLegs, 'E', Items.experience_bottle});
		
		
		
		GameRegistry.addRecipe(new ItemStack(ExperienceBoots), new Object[]{"EEE", "EAE", "EEE", 'A', EmeraldBoots, 'E', Items.experience_bottle});
		
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(Blocks.web), new Object[]{"***", "* *", "***", '*', Items.string});
		
		
		
		
		int cageid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(EntityCage.class, "EntityCage", cageid);
		EntityRegistry.instance().registerModEntity(EntityCage.class, "EntityCage", cageid, this, 64, 1, true);
		
		LanguageRegistry.instance().addNameForObject(CageEmpty, "en_US", "Empty Critter Cage");
		LanguageRegistry.instance().addNameForObject(CagedSpider, "en_US", "Caged Spider");
		LanguageRegistry.instance().addNameForObject(CagedBat, "en_US", "Caged Bat");
		LanguageRegistry.instance().addNameForObject(CagedCow, "en_US", "Caged Cow");
		LanguageRegistry.instance().addNameForObject(CagedPig, "en_US", "Caged Pig");
		LanguageRegistry.instance().addNameForObject(CagedSquid, "en_US", "Caged Squid");
		LanguageRegistry.instance().addNameForObject(CagedChicken, "en_US", "Caged Chicken");
		LanguageRegistry.instance().addNameForObject(CagedCreeper, "en_US", "Caged Creeper");
		LanguageRegistry.instance().addNameForObject(CagedSkeleton, "en_US", "Caged Skeleton");
		LanguageRegistry.instance().addNameForObject(CagedZombie, "en_US", "Caged Zombie");
		LanguageRegistry.instance().addNameForObject(CagedSlime, "en_US", "Caged Slime");
		LanguageRegistry.instance().addNameForObject(CagedGhast, "en_US", "Caged Ghast");
		LanguageRegistry.instance().addNameForObject(CagedZombiePigman, "en_US", "Caged ZombiePigman");
		LanguageRegistry.instance().addNameForObject(CagedEnderman, "en_US", "Caged Enderman");
		LanguageRegistry.instance().addNameForObject(CagedCaveSpider, "en_US", "Caged Cave Spider");
		LanguageRegistry.instance().addNameForObject(CagedSilverfish, "en_US", "Caged Silverfish");
		LanguageRegistry.instance().addNameForObject(CagedMagmaCube, "en_US", "Caged Magma Cube");
		LanguageRegistry.instance().addNameForObject(CagedWitch, "en_US", "Caged Witch");
		LanguageRegistry.instance().addNameForObject(CagedSheep, "en_US", "Caged Sheep");
		LanguageRegistry.instance().addNameForObject(CagedWolf, "en_US", "Caged Wolf");
		LanguageRegistry.instance().addNameForObject(CagedMooshroom, "en_US", "Caged Mooshroom");
		LanguageRegistry.instance().addNameForObject(CagedOcelot, "en_US", "Caged Ocelot");
		LanguageRegistry.instance().addNameForObject(CagedBlaze, "en_US", "Caged Blaze");
		LanguageRegistry.instance().addNameForObject(CagedGirlfriend, "en_US", "Caged Girlfriend");
		LanguageRegistry.instance().addNameForObject(CagedBoyfriend, "en_US", "Caged Boyfriend");
		LanguageRegistry.instance().addNameForObject(CagedWitherSkeleton, "en_US", "Caged Wither Skeleton");
		LanguageRegistry.instance().addNameForObject(CagedEnderDragon, "en_US", "Caged Ender Dragon");
		LanguageRegistry.instance().addNameForObject(CagedSnowGolem, "en_US", "Caged Snow Golem");
		LanguageRegistry.instance().addNameForObject(CagedIronGolem, "en_US", "Caged Iron Golem");
		LanguageRegistry.instance().addNameForObject(CagedWitherBoss, "en_US", "Caged Wither Boss");
		LanguageRegistry.instance().addNameForObject(CagedRedCow, "en_US", "Caged Apple Cow");
		LanguageRegistry.instance().addNameForObject(CagedCrystalCow, "en_US", "Caged Crystal Cow");
		LanguageRegistry.instance().addNameForObject(CagedVillager, "en_US", "Caged Villager");
		LanguageRegistry.instance().addNameForObject(CagedGoldCow, "en_US", "Caged Golden Apple Cow");
		LanguageRegistry.instance().addNameForObject(CagedEnchantedCow, "en_US", "Caged Enchanted Golden Apple Cow");
		LanguageRegistry.instance().addNameForObject(CagedMOTHRA, "en_US", "Caged MOTHRA");
		LanguageRegistry.instance().addNameForObject(CagedAlo, "en_US", "Caged Alosaurus");
		LanguageRegistry.instance().addNameForObject(CagedCryo, "en_US", "Caged Cryosaurus");
		LanguageRegistry.instance().addNameForObject(CagedCama, "en_US", "Caged Camarasaurus");
		LanguageRegistry.instance().addNameForObject(CagedVelo, "en_US", "Caged Velocity Raptor");
		LanguageRegistry.instance().addNameForObject(CagedHydro, "en_US", "Caged Hydrolisc");
		LanguageRegistry.instance().addNameForObject(CagedBasil, "en_US", "Caged Basilisk");
		LanguageRegistry.instance().addNameForObject(CagedDragonfly, "en_US", "Caged Dragonfly");
		LanguageRegistry.instance().addNameForObject(CagedEmperorScorpion, "en_US", "Caged Emperor Scorpion");
		LanguageRegistry.instance().addNameForObject(CagedScorpion, "en_US", "Caged Scorpion");
		LanguageRegistry.instance().addNameForObject(CagedCaveFisher, "en_US", "Caged Cave Fisher");
		LanguageRegistry.instance().addNameForObject(CagedSpyro, "en_US", "Caged Baby Dragon");
		LanguageRegistry.instance().addNameForObject(CagedBaryonyx, "en_US", "Caged Baryonyx");
		LanguageRegistry.instance().addNameForObject(CagedGammaMetroid, "en_US", "Caged WTF?");
		LanguageRegistry.instance().addNameForObject(CagedCockateil, "en_US", "Caged Bird");
		LanguageRegistry.instance().addNameForObject(CagedKyuubi, "en_US", "Caged Kyuubi");
		LanguageRegistry.instance().addNameForObject(CagedAlien, "en_US", "Caged Alien");
		LanguageRegistry.instance().addNameForObject(MyElevator, "en_US", "Hoverboard");
		LanguageRegistry.instance().addNameForObject(CagedAttackSquid, "en_US", "Caged Attack Squid");
		LanguageRegistry.instance().addNameForObject(CagedWaterDragon, "en_US", "Caged Water Dragon");
		LanguageRegistry.instance().addNameForObject(CagedCephadrome, "en_US", "Caged Cephadrome");
		LanguageRegistry.instance().addNameForObject(CagedKraken, "en_US", "Caged Kraken");
		LanguageRegistry.instance().addNameForObject(CagedLizard, "en_US", "Caged Lizard");
		LanguageRegistry.instance().addNameForObject(CagedDragon, "en_US", "Caged Dragon");
		LanguageRegistry.instance().addNameForObject(CagedBee, "en_US", "Caged Bee");
		LanguageRegistry.instance().addNameForObject(CagedHorse, "en_US", "Caged Horse");
		LanguageRegistry.instance().addNameForObject(CagedFirefly, "en_US", "Caged Firefly");
		LanguageRegistry.instance().addNameForObject(CagedChipmunk, "en_US", "Caged Chipmunk");
		LanguageRegistry.instance().addNameForObject(CagedGazelle, "en_US", "Caged Gazelle");
		LanguageRegistry.instance().addNameForObject(CagedOstrich, "en_US", "Caged Ostrich");
		LanguageRegistry.instance().addNameForObject(CagedTrooper, "en_US", "Caged Jumpy Bug");
		LanguageRegistry.instance().addNameForObject(CagedSpit, "en_US", "Caged Spit Bug");
		LanguageRegistry.instance().addNameForObject(CagedStink, "en_US", "Caged Stink Bug");
		LanguageRegistry.instance().addNameForObject(CagedCreepingHorror, "en_US", "Caged Creeping Horror");
		LanguageRegistry.instance().addNameForObject(CagedTerribleTerror, "en_US", "Caged Terrible Terror");
		LanguageRegistry.instance().addNameForObject(CagedCliffRacer, "en_US", "Caged Cliff Racer");
		LanguageRegistry.instance().addNameForObject(CagedTriffid, "en_US", "Caged Triffid");
		LanguageRegistry.instance().addNameForObject(CagedPitchBlack, "en_US", "Caged Nightmare");
		LanguageRegistry.instance().addNameForObject(CagedLurkingTerror, "en_US", "Caged Lurking Terror");
		LanguageRegistry.instance().addNameForObject(CagedSmallWorm, "en_US", "Caged Small Worm");
		LanguageRegistry.instance().addNameForObject(CagedMediumWorm, "en_US", "Caged Medium Worm");
		LanguageRegistry.instance().addNameForObject(CagedLargeWorm, "en_US", "Caged Large Worm");
		LanguageRegistry.instance().addNameForObject(CagedCassowary, "en_US", "Caged Cassowary");
		LanguageRegistry.instance().addNameForObject(CagedCloudShark, "en_US", "Caged Cloud Shark");
		LanguageRegistry.instance().addNameForObject(CagedGoldFish, "en_US", "Caged Gold Fish");
		LanguageRegistry.instance().addNameForObject(CagedLeafMonster, "en_US", "Caged Leaf Monster");
		LanguageRegistry.instance().addNameForObject(CagedEnderKnight, "en_US", "Caged Ender Knight");
		LanguageRegistry.instance().addNameForObject(CagedEnderReaper, "en_US", "Caged Ender Reaper");
		LanguageRegistry.instance().addNameForObject(CagedBeaver, "en_US", "Caged Beaver");
		LanguageRegistry.instance().addNameForObject(CagedUrchin, "en_US", "Caged Crystal Urchin");
		LanguageRegistry.instance().addNameForObject(CagedFlounder, "en_US", "Caged Flounder");
		LanguageRegistry.instance().addNameForObject(CagedSkate, "en_US", "Caged Skate");
		LanguageRegistry.instance().addNameForObject(CagedRotator, "en_US", "Caged Rotator");
		LanguageRegistry.instance().addNameForObject(CagedPeacock, "en_US", "Caged Peacock");
		LanguageRegistry.instance().addNameForObject(CagedFairy, "en_US", "Caged Fairy");
		LanguageRegistry.instance().addNameForObject(CagedDungeonBeast, "en_US", "Caged Dungeon Beast");
		LanguageRegistry.instance().addNameForObject(CagedVortex, "en_US", "Caged Vortex");
		LanguageRegistry.instance().addNameForObject(CagedRat, "en_US", "Caged Rat");
		LanguageRegistry.instance().addNameForObject(CagedWhale, "en_US", "Caged Whale");
		LanguageRegistry.instance().addNameForObject(CagedIrukandji, "en_US", "Caged Irukandji");
		LanguageRegistry.instance().addNameForObject(CagedTRex, "en_US", "Caged T. Rex");
		LanguageRegistry.instance().addNameForObject(CagedHercules, "en_US", "Caged Hercules Beetle");
		LanguageRegistry.instance().addNameForObject(CagedMantis, "en_US", "Caged Mantis");
		LanguageRegistry.instance().addNameForObject(CagedStinky, "en_US", "Caged Stinky");
		LanguageRegistry.instance().addNameForObject(CagedEasterBunny, "en_US", "Caged Easter Bunny");
		LanguageRegistry.instance().addNameForObject(CagedCaterKiller, "en_US", "Caged CaterKiller");
		LanguageRegistry.instance().addNameForObject(CagedMolenoid, "en_US", "Caged Molenoid");
		LanguageRegistry.instance().addNameForObject(CagedSeaMonster, "en_US", "Caged Sea Monster");
		LanguageRegistry.instance().addNameForObject(CagedSeaViper, "en_US", "Caged Sea Viper");
		LanguageRegistry.instance().addNameForObject(CagedLeon, "en_US", "Caged Leonopteryx");
		LanguageRegistry.instance().addNameForObject(CagedHammerhead, "en_US", "Caged Hammerhead");
		LanguageRegistry.instance().addNameForObject(CagedRubberDucky, "en_US", "Caged Rubber Ducky");
		LanguageRegistry.instance().addNameForObject(CagedCriminal, "en_US", "Caged Criminal");
		LanguageRegistry.instance().addNameForObject(CagedNastysaurus, "en_US", "Caged Nastysaurus");
		LanguageRegistry.instance().addNameForObject(CagedPointysaurus, "en_US", "Caged Pointysaurus");
		LanguageRegistry.instance().addNameForObject(CagedCricket, "en_US", "Caged Cricket");
		LanguageRegistry.instance().addNameForObject(CagedBrutalfly, "en_US", "Caged Brutalfly");
		LanguageRegistry.instance().addNameForObject(CagedFrog, "en_US", "Caged Frog");
		LanguageRegistry.instance().addNameForObject(CagedSpiderDriver, "en_US", "Caged Spider Driver");
		LanguageRegistry.instance().addNameForObject(CagedCrab, "en_US", "Caged Crab");
		
		
		LanguageRegistry.instance().addNameForObject(WitherSkeletonEgg, "en_US", "Spawn Wither Skeleton");
		LanguageRegistry.instance().addNameForObject(EnderDragonEgg, "en_US", "Spawn Ender Dragon");
		LanguageRegistry.instance().addNameForObject(SnowGolemEgg, "en_US", "Spawn Snow Golem");
		LanguageRegistry.instance().addNameForObject(IronGolemEgg, "en_US", "Spawn Iron Golem");
		LanguageRegistry.instance().addNameForObject(WitherBossEgg, "en_US", "Spawn Wither Boss");
		LanguageRegistry.instance().addNameForObject(GirlfriendEgg, "en_US", "Spawn Girlfriend");
		LanguageRegistry.instance().addNameForObject(BoyfriendEgg, "en_US", "Spawn Boyfriend");
		LanguageRegistry.instance().addNameForObject(TheKingEgg, "en_US", "Spawn The King");
		LanguageRegistry.instance().addNameForObject(TheQueenEgg, "en_US", "Spawn The Queen");
		LanguageRegistry.instance().addNameForObject(ThePrinceEgg, "en_US", "Spawn The Prince");
		LanguageRegistry.instance().addNameForObject(RedCowEgg, "en_US", "Spawn Apple Cow");
		LanguageRegistry.instance().addNameForObject(CrystalCowEgg, "en_US", "Spawn Crystal Cow");
		LanguageRegistry.instance().addNameForObject(GoldCowEgg, "en_US", "Spawn Golden Apple Cow");
		LanguageRegistry.instance().addNameForObject(EnchantedCowEgg, "en_US", "Spawn Enchanted Golden Apple Cow");
		LanguageRegistry.instance().addNameForObject(MOTHRAEgg, "en_US", "Spawn MOTHRA!");
		LanguageRegistry.instance().addNameForObject(AloEgg, "en_US", "Spawn Alosaurus");
		LanguageRegistry.instance().addNameForObject(CryoEgg, "en_US", "Spawn Cryolophosaurus");
		LanguageRegistry.instance().addNameForObject(CamaEgg, "en_US", "Spawn Camarasaurus");
		LanguageRegistry.instance().addNameForObject(VeloEgg, "en_US", "Spawn Velocity Raptor");
		LanguageRegistry.instance().addNameForObject(HydroEgg, "en_US", "Spawn Hydrolisc");
		LanguageRegistry.instance().addNameForObject(BasilEgg, "en_US", "Spawn Basilisk");
		LanguageRegistry.instance().addNameForObject(DragonflyEgg, "en_US", "Spawn Dragonfly");
		LanguageRegistry.instance().addNameForObject(EmperorScorpionEgg, "en_US", "Spawn Emperor Scorpion!");
		LanguageRegistry.instance().addNameForObject(ScorpionEgg, "en_US", "Spawn Scorpion");
		LanguageRegistry.instance().addNameForObject(CaveFisherEgg, "en_US", "Spawn Cave Fisher");
		LanguageRegistry.instance().addNameForObject(SpyroEgg, "en_US", "Spawn Baby Dragon");
		LanguageRegistry.instance().addNameForObject(BaryonyxEgg, "en_US", "Spawn Baryonyx");
		LanguageRegistry.instance().addNameForObject(GammaMetroidEgg, "en_US", "Spawn WTF?");
		LanguageRegistry.instance().addNameForObject(CockateilEgg, "en_US", "Spawn Bird");
		LanguageRegistry.instance().addNameForObject(KyuubiEgg, "en_US", "Spawn Kyuubi");
		LanguageRegistry.instance().addNameForObject(AlienEgg, "en_US", "Spawn Alien");
		LanguageRegistry.instance().addNameForObject(AttackSquidEgg, "en_US", "Spawn Attack Squid");
		LanguageRegistry.instance().addNameForObject(WaterDragonEgg, "en_US", "Spawn Water Dragon");
		LanguageRegistry.instance().addNameForObject(CephadromeEgg, "en_US", "Spawn Cephadrome");
		LanguageRegistry.instance().addNameForObject(KrakenEgg, "en_US", "Uh, no. Don't.");
		LanguageRegistry.instance().addNameForObject(LizardEgg, "en_US", "Spawn Lizard");
		LanguageRegistry.instance().addNameForObject(DragonEgg, "en_US", "Spawn Dragon");
		LanguageRegistry.instance().addNameForObject(BeeEgg, "en_US", "Spawn Bee");
		LanguageRegistry.instance().addNameForObject(TrooperBugEgg, "en_US", "Spawn Jumpy Bug");
		LanguageRegistry.instance().addNameForObject(SpitBugEgg, "en_US", "Spawn Spit Bug");
		LanguageRegistry.instance().addNameForObject(StinkBugEgg, "en_US", "Spawn Stink Bug");
		LanguageRegistry.instance().addNameForObject(OstrichEgg, "en_US", "Spawn Ostrich");
		LanguageRegistry.instance().addNameForObject(GazelleEgg, "en_US", "Spawn Gazelle");
		LanguageRegistry.instance().addNameForObject(ChipmunkEgg, "en_US", "Spawn Chipmunk");
		LanguageRegistry.instance().addNameForObject(CreepingHorrorEgg, "en_US", "Spawn Creeping Horror");
		LanguageRegistry.instance().addNameForObject(TerribleTerrorEgg, "en_US", "Spawn Terrible Terror");
		LanguageRegistry.instance().addNameForObject(CliffRacerEgg, "en_US", "Spawn Cliff Racer");
		LanguageRegistry.instance().addNameForObject(TriffidEgg, "en_US", "Spawn Triffid");
		LanguageRegistry.instance().addNameForObject(PitchBlackEgg, "en_US", "Spawn Nightmare!!!");
		LanguageRegistry.instance().addNameForObject(LurkingTerrorEgg, "en_US", "Spawn Lurking Terror");
		LanguageRegistry.instance().addNameForObject(GodzillaEgg, "en_US", "Spawn Mobzilla");
		LanguageRegistry.instance().addNameForObject(SmallWormEgg, "en_US", "Spawn Small Worm");
		LanguageRegistry.instance().addNameForObject(MediumWormEgg, "en_US", "Spawn Medium Worm");
		LanguageRegistry.instance().addNameForObject(LargeWormEgg, "en_US", "Spawn Large Worm");
		LanguageRegistry.instance().addNameForObject(CassowaryEgg, "en_US", "Spawn Cassowary");
		LanguageRegistry.instance().addNameForObject(CloudSharkEgg, "en_US", "Spawn Cloud Shark");
		LanguageRegistry.instance().addNameForObject(GoldFishEgg, "en_US", "Spawn Gold Fish");
		LanguageRegistry.instance().addNameForObject(LeafMonsterEgg, "en_US", "Spawn Leaf Monster");
		LanguageRegistry.instance().addNameForObject(TshirtEgg, "en_US", "Spawn T-Shirt!");
		LanguageRegistry.instance().addNameForObject(EnderKnightEgg, "en_US", "Spawn Ender Knight");
		LanguageRegistry.instance().addNameForObject(EnderReaperEgg, "en_US", "Spawn Ender Reaper");
		LanguageRegistry.instance().addNameForObject(BeaverEgg, "en_US", "Spawn Beaver");
		LanguageRegistry.instance().addNameForObject(FairyEgg, "en_US", "Spawn Fairy");
		LanguageRegistry.instance().addNameForObject(VortexEgg, "en_US", "Spawn Vortex");
		LanguageRegistry.instance().addNameForObject(RotatorEgg, "en_US", "Spawn Rotator");
		LanguageRegistry.instance().addNameForObject(PeacockEgg, "en_US", "Spawn Peacock");
		LanguageRegistry.instance().addNameForObject(DungeonBeastEgg, "en_US", "Spawn Dungeon Beast");
		LanguageRegistry.instance().addNameForObject(RatEgg, "en_US", "Spawn Rat");
		LanguageRegistry.instance().addNameForObject(FlounderEgg, "en_US", "Spawn Flounder");
		LanguageRegistry.instance().addNameForObject(WhaleEgg, "en_US", "Spawn Whale");
		LanguageRegistry.instance().addNameForObject(IrukandjiEgg, "en_US", "Spawn Irukandji");
		LanguageRegistry.instance().addNameForObject(SkateEgg, "en_US", "Spawn Skate");
		LanguageRegistry.instance().addNameForObject(UrchinEgg, "en_US", "Spawn Crystal Urchin");
		LanguageRegistry.instance().addNameForObject(Robot1Egg, "en_US", "Spawn Bomb-Omb");
		LanguageRegistry.instance().addNameForObject(Robot2Egg, "en_US", "Spawn Robo-Pounder");
		LanguageRegistry.instance().addNameForObject(Robot3Egg, "en_US", "Spawn Robo-Gunner");
		LanguageRegistry.instance().addNameForObject(Robot4Egg, "en_US", "Spawn Robo-Warrior");
		LanguageRegistry.instance().addNameForObject(GhostEgg, "en_US", "Spawn Ghost");
		LanguageRegistry.instance().addNameForObject(GhostSkellyEgg, "en_US", "Spawn Ghost Pumpkin Skelly");
		LanguageRegistry.instance().addNameForObject(BrownAntEgg, "en_US", "Spawn Brown Ant");
		LanguageRegistry.instance().addNameForObject(RedAntEgg, "en_US", "Spawn Red Ant");
		LanguageRegistry.instance().addNameForObject(RainbowAntEgg, "en_US", "Spawn Rainbow Ant");
		LanguageRegistry.instance().addNameForObject(UnstableAntEgg, "en_US", "Spawn Unstable Ant");
		LanguageRegistry.instance().addNameForObject(TermiteEgg, "en_US", "Spawn Termite");
		LanguageRegistry.instance().addNameForObject(ButterflyEgg, "en_US", "Spawn Butterfly");
		LanguageRegistry.instance().addNameForObject(MothEgg, "en_US", "Spawn Moth");
		LanguageRegistry.instance().addNameForObject(MosquitoEgg, "en_US", "Spawn Mosquito");
		LanguageRegistry.instance().addNameForObject(FireflyEgg, "en_US", "Spawn Firefly");
		LanguageRegistry.instance().addNameForObject(TRexEgg, "en_US", "Spawn T. Rex");
		LanguageRegistry.instance().addNameForObject(HerculesEgg, "en_US", "Spawn Hercules Beetle");
		LanguageRegistry.instance().addNameForObject(MantisEgg, "en_US", "Spawn Mantis");
		LanguageRegistry.instance().addNameForObject(StinkyEgg, "en_US", "Spawn Stinky");
		LanguageRegistry.instance().addNameForObject(Robot5Egg, "en_US", "Spawn Robo-Sniper");
		LanguageRegistry.instance().addNameForObject(CoinEgg, "en_US", "Spawn Coin");
		LanguageRegistry.instance().addNameForObject(EasterBunnyEgg, "en_US", "Spawn Easter Bunny");
		LanguageRegistry.instance().addNameForObject(MolenoidEgg, "en_US", "Spawn Molenoid");
		LanguageRegistry.instance().addNameForObject(SeaMonsterEgg, "en_US", "Spawn Sea Monster");
		LanguageRegistry.instance().addNameForObject(SeaViperEgg, "en_US", "Spawn Sea Viper");
		LanguageRegistry.instance().addNameForObject(CaterKillerEgg, "en_US", "Spawn CaterKiller");
		LanguageRegistry.instance().addNameForObject(LeonEgg, "en_US", "Spawn Leonopteryx");
		LanguageRegistry.instance().addNameForObject(HammerheadEgg, "en_US", "Spawn Hammerhead");
		LanguageRegistry.instance().addNameForObject(RubberDuckyEgg, "en_US", "Spawn Rubber Ducky");
		LanguageRegistry.instance().addNameForObject(CriminalEgg, "en_US", "Spawn Criminal");
		LanguageRegistry.instance().addNameForObject(BrutalflyEgg, "en_US", "Spawn Brutalfly");
		LanguageRegistry.instance().addNameForObject(NastysaurusEgg, "en_US", "Spawn Nastysaurus");
		LanguageRegistry.instance().addNameForObject(PointysaurusEgg, "en_US", "Spawn Pointysaurus");
		LanguageRegistry.instance().addNameForObject(CricketEgg, "en_US", "Spawn Cricket");
		LanguageRegistry.instance().addNameForObject(ThePrincessEgg, "en_US", "Spawn The Princess");
		LanguageRegistry.instance().addNameForObject(FrogEgg, "en_US", "Spawn Frog");
		LanguageRegistry.instance().addNameForObject(JefferyEgg, "en_US", "Spawn Jeffery");
		LanguageRegistry.instance().addNameForObject(AntRobotEgg, "en_US", "Spawn Red Ant Robot");
		LanguageRegistry.instance().addNameForObject(SpiderRobotEgg, "en_US", "Spawn Giant Spider Robot");
		LanguageRegistry.instance().addNameForObject(SpiderDriverEgg, "en_US", "Spawn Spider Robot Driver");
		LanguageRegistry.instance().addNameForObject(CrabEgg, "en_US", "Spawn Crab");
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(CageEmpty, 2), new Object[]{"IWI", "W W", "IWI", 'W', Items.stick, 'I', Items.iron_ingot});
		
		
		
		
		GameRegistry.addRecipe(new ItemStack(CageEmpty, 2), new Object[]{"IWI", "W W", "IWI", 'W', CrystalSticks, 'I', MyCrystalPinkIngot});
		
		
		
		
		
		
		int arrowid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(UltimateArrow.class, "UltimateArrow", arrowid);
		
		int irukandiarrowid = EntityRegistry.instance().findGlobalUniqueEntityId();
		EntityRegistry.instance().registerGlobalEntityID(IrukandjiArrow.class, "IrukandjiArrow", irukandiarrowid);
		
		LanguageRegistry.instance().addNameForObject(MyAppleLeaves, "en_US", "Apple Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MySkyTreeLog, "en_US", "Sky Tree Wood");
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 4), new Object[]{new ItemStack(MySkyTreeLog)});
		LanguageRegistry.instance().addNameForObject(MyDT, "en_US", "Duplicator Tree Wood");
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 4), new Object[]{new ItemStack(MyDT)});
		LanguageRegistry.instance().addNameForObject(MyScaryLeaves, "en_US", "Scary Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyCherryLeaves, "en_US", "Cherry Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyPeachLeaves, "en_US", "Peach Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyExperienceLeaves, "en_US", "Experience Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyCrystalLeaves, "en_US", "Crystal Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyCrystalLeaves2, "en_US", "Crystal Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyCrystalLeaves3, "en_US", "Crystal Tree Leaves");
		LanguageRegistry.instance().addNameForObject(MyCrystalTreeLog, "en_US", "Crystal Tree Wood");
		
		
		GameRegistry.addRecipe(new ItemStack(MyElevator), new Object[]{"   ", "WWW", "DRD", 'W', Blocks.planks, 'R', Items.redstone, 'D', Items.diamond});
		
		
		
		
		
		
		
		
		GameRegistry.registerWorldGenerator(this.OreSpawnGen, 10);
		
		
		
		proxy.registerRenderThings();
		
		proxy.registerKeyboardInput();
		
		proxy.registerNetworkStuff();
		
		
		
		
		DimensionManager.registerProviderType(DimensionID, WorldProviderOreSpawn.class, true);
		DimensionManager.registerDimension(DimensionID, DimensionID);
		
		DimensionManager.registerProviderType(DimensionID2, WorldProviderOreSpawn2.class, true);
		DimensionManager.registerDimension(DimensionID2, DimensionID2);
		
		DimensionManager.registerProviderType(DimensionID3, WorldProviderOreSpawn3.class, true);
		DimensionManager.registerDimension(DimensionID3, DimensionID3);
		
		DimensionManager.registerProviderType(DimensionID4, WorldProviderOreSpawn4.class, true);
		DimensionManager.registerDimension(DimensionID4, DimensionID4);
		
		DimensionManager.registerProviderType(DimensionID5, WorldProviderOreSpawn5.class, true);
		DimensionManager.registerDimension(DimensionID5, DimensionID5);
		
		DimensionManager.registerProviderType(DimensionID6, WorldProviderOreSpawn6.class, true);
		DimensionManager.registerDimension(DimensionID6, DimensionID6);
		
		
		ChestGenHooks chestGenHooksDungeon = ChestGenHooks.getInfo("dungeonChest");
		chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(MyRuby), 1, 1, 3));
		chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(MyAmethyst), 1, 1, 3));
		chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(MyThunderStaff), 1, 1, 2));
		
		ChestGenHooks chestGenHooksJungleChest = ChestGenHooks.getInfo("pyramidJungleChest");
		chestGenHooksJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(MyRuby), 1, 1, 3));
		chestGenHooksJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(MyAmethyst), 1, 1, 3));
		chestGenHooksJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(AntRobotKit), 1, 1, 3));
		
		ChestGenHooks chestGenHooksDesertChest = ChestGenHooks.getInfo("pyramidDesertyChest");
		chestGenHooksDesertChest.addItem(new WeightedRandomChestContent(new ItemStack(MyRuby), 1, 1, 2));
		chestGenHooksDesertChest.addItem(new WeightedRandomChestContent(new ItemStack(MyAmethyst), 1, 1, 2));
		chestGenHooksDesertChest.addItem(new WeightedRandomChestContent(new ItemStack(SpiderRobotKit), 1, 1, 2));
		
		
		GameRegistry.registerTileEntity(TileEntityCrystalFurnace.class, "TileEntityCrystalFurnace");
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new OreSpawnGUIHandler());
		
		this.DoDispenserRegistrations();
	
	}
	
	
	public void initializeCagesAndEggs() {
		CageEmpty = (new CritterCage(BaseItemID + OreSpawnConstants.EmptyCageItemID, OreSpawnConstants.EmptyCageIndex)).setUnlocalizedName("cageempty");
		CagedSpider = (new CritterCage(BaseItemID + OreSpawnConstants.SpiderCageItemID, OreSpawnConstants.SpiderCageIndex)).setUnlocalizedName("cagespider");
		CagedBat = (new CritterCage(BaseItemID + OreSpawnConstants.BatCageItemID, OreSpawnConstants.BatCageIndex)).setUnlocalizedName("cagebat");
		CagedCow = (new CritterCage(BaseItemID + OreSpawnConstants.CowCageItemID, OreSpawnConstants.CowCageIndex)).setUnlocalizedName("cagecow");
		CagedPig = (new CritterCage(BaseItemID + OreSpawnConstants.PigCageItemID, OreSpawnConstants.PigCageIndex)).setUnlocalizedName("cagepig");
		CagedSquid = (new CritterCage(BaseItemID + OreSpawnConstants.SquidCageItemID, OreSpawnConstants.SquidCageIndex)).setUnlocalizedName("cagesquid");
		CagedChicken = (new CritterCage(BaseItemID + OreSpawnConstants.ChickenCageItemID, OreSpawnConstants.ChickenCageIndex)).setUnlocalizedName("cagechicken");
		CagedCreeper = (new CritterCage(BaseItemID + OreSpawnConstants.CreeperCageItemID, OreSpawnConstants.CreeperCageIndex)).setUnlocalizedName("cagecreeper");
		CagedSkeleton = (new CritterCage(BaseItemID + OreSpawnConstants.SkeletonCageItemID, OreSpawnConstants.SkeletonCageIndex)).setUnlocalizedName("cageskeleton");
		CagedZombie = (new CritterCage(BaseItemID + OreSpawnConstants.ZombieCageItemID, OreSpawnConstants.ZombieCageIndex)).setUnlocalizedName("cagezombie");
		CagedSlime = (new CritterCage(BaseItemID + OreSpawnConstants.SlimeCageItemID, OreSpawnConstants.SlimeCageIndex)).setUnlocalizedName("cageslime");
		CagedGhast = (new CritterCage(BaseItemID + OreSpawnConstants.GhastCageItemID, OreSpawnConstants.GhastCageIndex)).setUnlocalizedName("cageghast");
		CagedZombiePigman = (new CritterCage(BaseItemID + OreSpawnConstants.ZombiePigmanCageItemID, OreSpawnConstants.ZombiePigmanCageIndex)).setUnlocalizedName("cagezombiepigman");
		CagedEnderman = (new CritterCage(BaseItemID + OreSpawnConstants.EndermanCageItemID, OreSpawnConstants.EndermanCageIndex)).setUnlocalizedName("cageenderman");
		CagedCaveSpider = (new CritterCage(BaseItemID + OreSpawnConstants.CaveSpiderCageItemID, OreSpawnConstants.CaveSpiderCageIndex)).setUnlocalizedName("cagecavespider");
		CagedSilverfish = (new CritterCage(BaseItemID + OreSpawnConstants.SilverfishCageItemID, OreSpawnConstants.SilverfishCageIndex)).setUnlocalizedName("cagesilverfish");
		CagedMagmaCube = (new CritterCage(BaseItemID + OreSpawnConstants.MagmaCubeCageItemID, OreSpawnConstants.MagmaCubeCageIndex)).setUnlocalizedName("cagemagmacube");
		CagedWitch = (new CritterCage(BaseItemID + OreSpawnConstants.WitchCageItemID, OreSpawnConstants.WitchCageIndex)).setUnlocalizedName("cagewitch");
		CagedSheep = (new CritterCage(BaseItemID + OreSpawnConstants.SheepCageItemID, OreSpawnConstants.SheepCageIndex)).setUnlocalizedName("cagesheep");
		CagedWolf = (new CritterCage(BaseItemID + OreSpawnConstants.WolfCageItemID, OreSpawnConstants.WolfCageIndex)).setUnlocalizedName("cagewolf");
		CagedMooshroom = (new CritterCage(BaseItemID + OreSpawnConstants.MooshroomCageItemID, OreSpawnConstants.MooshroomCageIndex)).setUnlocalizedName("cagemooshroom");
		CagedOcelot = (new CritterCage(BaseItemID + OreSpawnConstants.OcelotCageItemID, OreSpawnConstants.OcelotCageIndex)).setUnlocalizedName("cageocelot");
		CagedBlaze = (new CritterCage(BaseItemID + OreSpawnConstants.BlazeCageItemID, OreSpawnConstants.BlazeCageIndex)).setUnlocalizedName("cageblaze");
		CagedGirlfriend = (new CritterCage(BaseItemID + OreSpawnConstants.GirlfriendCageItemID, OreSpawnConstants.GirlfriendCageIndex)).setUnlocalizedName("cagegirlfriend");
		CagedBoyfriend = (new CritterCage(BaseItemID + OreSpawnConstants.BoyfriendCageItemID, OreSpawnConstants.BoyfriendCageIndex)).setUnlocalizedName("cageboyfriend");
		CagedWitherSkeleton = (new CritterCage(BaseItemID + OreSpawnConstants.WitherSkeletonCageItemID, OreSpawnConstants.WitherSkeletonCageIndex)).setUnlocalizedName("cagewitherskeleton");
		CagedEnderDragon = (new CritterCage(BaseItemID + OreSpawnConstants.EnderDragonCageItemID, OreSpawnConstants.EnderDragonCageIndex)).setUnlocalizedName("cageenderdragon");
		CagedSnowGolem = (new CritterCage(BaseItemID + OreSpawnConstants.SnowGolemCageItemID, OreSpawnConstants.SnowGolemCageIndex)).setUnlocalizedName("cagesnowgolem");
		CagedIronGolem = (new CritterCage(BaseItemID + OreSpawnConstants.IronGolemCageItemID, OreSpawnConstants.IronGolemCageIndex)).setUnlocalizedName("cageirongolem");
		CagedWitherBoss = (new CritterCage(BaseItemID + OreSpawnConstants.WitherBossCageItemID, OreSpawnConstants.WitherBossCageIndex)).setUnlocalizedName("cagewitherboss");
		CagedRedCow = (new CritterCage(BaseItemID + OreSpawnConstants.RedCowCageItemID, OreSpawnConstants.RedCowCageIndex)).setUnlocalizedName("cageredcow");
		CagedGoldCow = (new CritterCage(BaseItemID + OreSpawnConstants.GoldCowCageItemID, OreSpawnConstants.GoldCowCageIndex)).setUnlocalizedName("cagegoldcow");
		CagedEnchantedCow = (new CritterCage(BaseItemID + OreSpawnConstants.EnchantedCowCageItemID, OreSpawnConstants.EnchantedCowCageIndex)).setUnlocalizedName("cageenchantedcow");
		CagedMOTHRA = (new CritterCage(BaseItemID + OreSpawnConstants.MOTHRACageItemID, OreSpawnConstants.MOTHRACageIndex)).setUnlocalizedName("cageMOTHRA");
		CagedAlo = (new CritterCage(BaseItemID + OreSpawnConstants.AloCageItemID, OreSpawnConstants.AloCageIndex)).setUnlocalizedName("cagealosaurus");
		CagedCryo = (new CritterCage(BaseItemID + OreSpawnConstants.CryoCageItemID, OreSpawnConstants.CryoCageIndex)).setUnlocalizedName("cagecryolophosaurus");
		CagedCama = (new CritterCage(BaseItemID + OreSpawnConstants.CamaCageItemID, OreSpawnConstants.CamaCageIndex)).setUnlocalizedName("cagecamarasaurus");
		CagedVelo = (new CritterCage(BaseItemID + OreSpawnConstants.VeloCageItemID, OreSpawnConstants.VeloCageIndex)).setUnlocalizedName("cagevelocityraptor");
		CagedHydro = (new CritterCage(BaseItemID + OreSpawnConstants.HydroCageItemID, OreSpawnConstants.HydroCageIndex)).setUnlocalizedName("cagehydrolisc");
		CagedBasil = (new CritterCage(BaseItemID + OreSpawnConstants.BasilCageItemID, OreSpawnConstants.BasilCageIndex)).setUnlocalizedName("cagebasilisc");
		CagedDragonfly = (new CritterCage(BaseItemID + OreSpawnConstants.DragonflyCageItemID, OreSpawnConstants.DragonflyCageIndex)).setUnlocalizedName("cagedragonfly");
		CagedEmperorScorpion = (new CritterCage(BaseItemID + OreSpawnConstants.EmperorScorpionCageItemID, OreSpawnConstants.EmperorScorpionCageIndex)).setUnlocalizedName("cageemperorscorpion");
		CagedScorpion = (new CritterCage(BaseItemID + OreSpawnConstants.ScorpionCageItemID, OreSpawnConstants.ScorpionCageIndex)).setUnlocalizedName("cagescorpion");
		CagedCaveFisher = (new CritterCage(BaseItemID + OreSpawnConstants.CaveFisherCageItemID, OreSpawnConstants.CaveFisherCageIndex)).setUnlocalizedName("cagecavefisher");
		CagedSpyro = (new CritterCage(BaseItemID + OreSpawnConstants.SpyroCageItemID, OreSpawnConstants.SpyroCageIndex)).setUnlocalizedName("cagespyro");
		CagedBaryonyx = (new CritterCage(BaseItemID + OreSpawnConstants.BaryonyxCageItemID, OreSpawnConstants.BaryonyxCageIndex)).setUnlocalizedName("cagebaryonyx");
		CagedGammaMetroid = (new CritterCage(BaseItemID + OreSpawnConstants.GammaMetroidCageItemID, OreSpawnConstants.GammaMetroidCageIndex)).setUnlocalizedName("cagegammametroid");
		CagedCockateil = (new CritterCage(BaseItemID + OreSpawnConstants.CockateilCageItemID, OreSpawnConstants.CockateilCageIndex)).setUnlocalizedName("cagecockateil");
		CagedKyuubi = (new CritterCage(BaseItemID + OreSpawnConstants.KyuubiCageItemID, OreSpawnConstants.KyuubiCageIndex)).setUnlocalizedName("cagekyuubi");
		CagedAlien = (new CritterCage(BaseItemID + OreSpawnConstants.AlienCageItemID, OreSpawnConstants.AlienCageIndex)).setUnlocalizedName("cagealien");
		CagedAttackSquid = (new CritterCage(BaseItemID + OreSpawnConstants.AttackSquidCageItemID, OreSpawnConstants.AttackSquidCageIndex)).setUnlocalizedName("cageattacksquid");
		CagedWaterDragon = (new CritterCage(BaseItemID + OreSpawnConstants.WaterDragonCageItemID, OreSpawnConstants.WaterDragonCageIndex)).setUnlocalizedName("cagewaterdragon");
		CagedCephadrome = (new CritterCage(BaseItemID + OreSpawnConstants.CephadromeCageItemID, OreSpawnConstants.CephadromeCageIndex)).setUnlocalizedName("cagecephadrome");
		CagedKraken = (new CritterCage(BaseItemID + OreSpawnConstants.KrakenCageItemID, OreSpawnConstants.KrakenCageIndex)).setUnlocalizedName("cagekraken");
		CagedLizard = (new CritterCage(BaseItemID + OreSpawnConstants.LizardCageItemID, OreSpawnConstants.LizardCageIndex)).setUnlocalizedName("cagelizard");
		CagedDragon = (new CritterCage(BaseItemID + OreSpawnConstants.DragonCageItemID, OreSpawnConstants.DragonCageIndex)).setUnlocalizedName("cagedragon");
		CagedBee = (new CritterCage(BaseItemID + OreSpawnConstants.BeeCageItemID, OreSpawnConstants.BeeCageIndex)).setUnlocalizedName("cagebee");
		CagedHorse = (new CritterCage(BaseItemID + OreSpawnConstants.HorseCageItemID, OreSpawnConstants.HorseCageIndex)).setUnlocalizedName("cagehorse");
		CagedFirefly = (new CritterCage(BaseItemID + OreSpawnConstants.FireflyCageItemID, OreSpawnConstants.FireflyCageIndex)).setUnlocalizedName("cagefirefly");
		CagedChipmunk = (new CritterCage(BaseItemID + OreSpawnConstants.ChipmunkCageItemID, OreSpawnConstants.ChipmunkCageIndex)).setUnlocalizedName("cagechipmunk");
		CagedGazelle = (new CritterCage(BaseItemID + OreSpawnConstants.GazelleCageItemID, OreSpawnConstants.GazelleCageIndex)).setUnlocalizedName("cagegazelle");
		CagedOstrich = (new CritterCage(BaseItemID + OreSpawnConstants.OstrichCageItemID, OreSpawnConstants.OstrichCageIndex)).setUnlocalizedName("cageostrich");
		CagedTrooper = (new CritterCage(BaseItemID + OreSpawnConstants.TrooperCageItemID, OreSpawnConstants.TrooperCageIndex)).setUnlocalizedName("cagetrooper");
		CagedSpit = (new CritterCage(BaseItemID + OreSpawnConstants.SpitCageItemID, OreSpawnConstants.SpitCageIndex)).setUnlocalizedName("cagespit");
		CagedStink = (new CritterCage(BaseItemID + OreSpawnConstants.StinkCageItemID, OreSpawnConstants.StinkCageIndex)).setUnlocalizedName("cagestink");
		CagedCreepingHorror = (new CritterCage(BaseItemID + OreSpawnConstants.CreepingHorrorCageItemID, OreSpawnConstants.CreepingHorrorCageIndex)).setUnlocalizedName("cagecreepinghorror");
		CagedTerribleTerror = (new CritterCage(BaseItemID + OreSpawnConstants.TerribleTerrorCageItemID, OreSpawnConstants.TerribleTerrorCageIndex)).setUnlocalizedName("cageterribleterror");
		CagedCliffRacer = (new CritterCage(BaseItemID + OreSpawnConstants.CliffRacerCageItemID, OreSpawnConstants.CliffRacerCageIndex)).setUnlocalizedName("cagecliffracer");
		CagedTriffid = (new CritterCage(BaseItemID + OreSpawnConstants.TriffidCageItemID, OreSpawnConstants.TriffidCageIndex)).setUnlocalizedName("cagetriffid");
		CagedPitchBlack = (new CritterCage(BaseItemID + OreSpawnConstants.PitchBlackCageItemID, OreSpawnConstants.PitchBlackCageIndex)).setUnlocalizedName("cagenightmare");
		CagedLurkingTerror = (new CritterCage(BaseItemID + OreSpawnConstants.LurkingTerrorCageItemID, OreSpawnConstants.LurkingTerrorCageIndex)).setUnlocalizedName("cagelurkingterror");
		CagedSmallWorm = (new CritterCage(BaseItemID + OreSpawnConstants.WormSmallCageItemID, OreSpawnConstants.WormSmallCageIndex)).setUnlocalizedName("cagesmallworm");
		CagedMediumWorm = (new CritterCage(BaseItemID + OreSpawnConstants.WormMediumCageItemID, OreSpawnConstants.WormMediumCageIndex)).setUnlocalizedName("cagemediumworm");
		CagedLargeWorm = (new CritterCage(BaseItemID + OreSpawnConstants.WormLargeCageItemID, OreSpawnConstants.WormLargeCageIndex)).setUnlocalizedName("cagelargeworm");
		CagedCassowary = (new CritterCage(BaseItemID + OreSpawnConstants.CassowaryCageItemID, OreSpawnConstants.CassowaryCageIndex)).setUnlocalizedName("cagecassowary");
		CagedCloudShark = (new CritterCage(BaseItemID + OreSpawnConstants.CloudSharkCageItemID, OreSpawnConstants.CloudSharkCageIndex)).setUnlocalizedName("cagecloudshark");
		CagedGoldFish = (new CritterCage(BaseItemID + OreSpawnConstants.GoldFishCageItemID, OreSpawnConstants.GoldFishCageIndex)).setUnlocalizedName("cagegoldfish");
		CagedLeafMonster = (new CritterCage(BaseItemID + OreSpawnConstants.LeafMonsterCageItemID, OreSpawnConstants.LeafMonsterCageIndex)).setUnlocalizedName("cageleafmonster");
		CagedEnderKnight = (new CritterCage(BaseItemID + OreSpawnConstants.EnderKnightCageItemID, OreSpawnConstants.EnderKnightCageIndex)).setUnlocalizedName("cageenderknight");
		CagedEnderReaper = (new CritterCage(BaseItemID + OreSpawnConstants.EnderReaperCageItemID, OreSpawnConstants.EnderReaperCageIndex)).setUnlocalizedName("cageenderreaper");
		CagedBeaver = (new CritterCage(BaseItemID + OreSpawnConstants.BeaverCageItemID, OreSpawnConstants.BeaverCageIndex)).setUnlocalizedName("cagebeaver");
		CagedUrchin = (new CritterCage(BaseItemID + OreSpawnConstants.UrchinCageItemID, OreSpawnConstants.UrchinCageIndex)).setUnlocalizedName("cageurchin");
		CagedFlounder = (new CritterCage(BaseItemID + OreSpawnConstants.FlounderCageItemID, OreSpawnConstants.FlounderCageIndex)).setUnlocalizedName("cageflounder");
		CagedSkate = (new CritterCage(BaseItemID + OreSpawnConstants.SkateCageItemID, OreSpawnConstants.SkateCageIndex)).setUnlocalizedName("cageskate");
		CagedRotator = (new CritterCage(BaseItemID + OreSpawnConstants.RotatorCageItemID, OreSpawnConstants.RotatorCageIndex)).setUnlocalizedName("cagerotator");
		CagedPeacock = (new CritterCage(BaseItemID + OreSpawnConstants.PeacockCageItemID, OreSpawnConstants.PeacockCageIndex)).setUnlocalizedName("cagepeacock");
		CagedFairy = (new CritterCage(BaseItemID + OreSpawnConstants.FairyCageItemID, OreSpawnConstants.FairyCageIndex)).setUnlocalizedName("cagefairy");
		CagedDungeonBeast = (new CritterCage(BaseItemID + OreSpawnConstants.DungeonBeastCageItemID, OreSpawnConstants.DungeonBeastCageIndex)).setUnlocalizedName("cagedungeonbeast");
		CagedVortex = (new CritterCage(BaseItemID + OreSpawnConstants.VortexCageItemID, OreSpawnConstants.VortexCageIndex)).setUnlocalizedName("cagevortex");
		CagedRat = (new CritterCage(BaseItemID + OreSpawnConstants.RatCageItemID, OreSpawnConstants.RatCageIndex)).setUnlocalizedName("cagerat");
		CagedWhale = (new CritterCage(BaseItemID + OreSpawnConstants.WhaleCageItemID, OreSpawnConstants.WhaleCageIndex)).setUnlocalizedName("cagewhale");
		CagedIrukandji = (new CritterCage(BaseItemID + OreSpawnConstants.IrukandjiCageItemID, OreSpawnConstants.IrukandjiCageIndex)).setUnlocalizedName("cageirukandji");
		CagedTRex = (new CritterCage(BaseItemID + OreSpawnConstants.TRexCageItemID, OreSpawnConstants.TRexCageIndex)).setUnlocalizedName("cagetrex");
		CagedHercules = (new CritterCage(BaseItemID + OreSpawnConstants.HerculesCageItemID, OreSpawnConstants.HerculesCageIndex)).setUnlocalizedName("cagehercules");
		CagedMantis = (new CritterCage(BaseItemID + OreSpawnConstants.MantisCageItemID, OreSpawnConstants.MantisCageIndex)).setUnlocalizedName("cagemantis");
		CagedStinky = (new CritterCage(BaseItemID + OreSpawnConstants.StinkyCageItemID, OreSpawnConstants.StinkyCageIndex)).setUnlocalizedName("cagestinky");
		CagedEasterBunny = (new CritterCage(BaseItemID + OreSpawnConstants.EasterBunnyCageItemID, OreSpawnConstants.EasterBunnyCageIndex)).setUnlocalizedName("cageeasterbunny");
		CagedCaterKiller = (new CritterCage(BaseItemID + OreSpawnConstants.CaterKillerCageItemID, OreSpawnConstants.CaterKillerCageIndex)).setUnlocalizedName("cagecaterkiller");
		CagedMolenoid = (new CritterCage(BaseItemID + OreSpawnConstants.MolenoidCageItemID, OreSpawnConstants.MolenoidCageIndex)).setUnlocalizedName("cagemolenoid");
		CagedSeaMonster = (new CritterCage(BaseItemID + OreSpawnConstants.SeaMonsterCageItemID, OreSpawnConstants.SeaMonsterCageIndex)).setUnlocalizedName("cageseamonster");
		CagedSeaViper = (new CritterCage(BaseItemID + OreSpawnConstants.SeaViperCageItemID, OreSpawnConstants.SeaViperCageIndex)).setUnlocalizedName("cageseaviper");
		CagedLeon = (new CritterCage(BaseItemID + OreSpawnConstants.LeonCageItemID, OreSpawnConstants.LeonCageIndex)).setUnlocalizedName("cageleon");
		CagedHammerhead = (new CritterCage(BaseItemID + OreSpawnConstants.HammerheadCageItemID, OreSpawnConstants.HammerheadCageIndex)).setUnlocalizedName("cagehammerhead");
		CagedRubberDucky = (new CritterCage(BaseItemID + OreSpawnConstants.RubberDuckyCageItemID, OreSpawnConstants.RubberDuckyCageIndex)).setUnlocalizedName("cagerubberducky");
		CagedCrystalCow = (new CritterCage(BaseItemID + OreSpawnConstants.CrystalCowCageItemID, OreSpawnConstants.CrystalCowCageIndex)).setUnlocalizedName("cagecrystalcow");
		CagedVillager = (new CritterCage(BaseItemID + OreSpawnConstants.VillagerCageItemID, OreSpawnConstants.VillagerCageIndex)).setUnlocalizedName("cagevillager");
		CagedCriminal = (new CritterCage(BaseItemID + OreSpawnConstants.CriminalCageItemID, OreSpawnConstants.CriminalCageIndex)).setUnlocalizedName("cagecriminal");
		CagedBrutalfly = (new CritterCage(BaseItemID + OreSpawnConstants.BrutalflyCageItemID, OreSpawnConstants.BrutalflyCageIndex)).setUnlocalizedName("cagebrutalfly");
		CagedNastysaurus = (new CritterCage(BaseItemID + OreSpawnConstants.NastysaurusCageItemID, OreSpawnConstants.NastysaurusCageIndex)).setUnlocalizedName("cagenastysaurus");
		CagedPointysaurus = (new CritterCage(BaseItemID + OreSpawnConstants.PointysaurusCageItemID, OreSpawnConstants.PointysaurusCageIndex)).setUnlocalizedName("cagepointysaurus");
		CagedCricket = (new CritterCage(BaseItemID + OreSpawnConstants.CricketCageItemID, OreSpawnConstants.CricketCageIndex)).setUnlocalizedName("cagecricket");
		CagedFrog = (new CritterCage(BaseItemID + OreSpawnConstants.FrogCageItemID, OreSpawnConstants.FrogCageIndex)).setUnlocalizedName("cagefrog");
		CagedSpiderDriver = (new CritterCage(BaseItemID + OreSpawnConstants.SpiderDriverCageItemID, OreSpawnConstants.SpiderDriverCageIndex)).setUnlocalizedName("cagespiderdriver");
		CagedCrab = (new CritterCage(BaseItemID + OreSpawnConstants.CrabCageItemID, OreSpawnConstants.CrabCageIndex)).setUnlocalizedName("cagecrab");
		
		
		WitherSkeletonEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.WitherSkeletonEggItemID, OreSpawnConstants.WitherSkeletonEggIndex)).setUnlocalizedName("eggwitherskeleton");
		EnderDragonEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.EnderDragonEggItemID, OreSpawnConstants.EnderDragonEggIndex)).setUnlocalizedName("eggenderdragon");
		SnowGolemEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SnowGolemEggItemID, OreSpawnConstants.SnowGolemEggIndex)).setUnlocalizedName("eggsnowgolem");
		IronGolemEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.IronGolemEggItemID, OreSpawnConstants.IronGolemEggIndex)).setUnlocalizedName("eggirongolem");
		WitherBossEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.WitherBossEggItemID, OreSpawnConstants.WitherBossEggIndex)).setUnlocalizedName("eggwitherboss");
		GirlfriendEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GirlfriendEggItemID, OreSpawnConstants.GirlfriendEggIndex)).setUnlocalizedName("egggirlfriend");
		RedCowEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.RedCowEggItemID, OreSpawnConstants.RedCowEggIndex)).setUnlocalizedName("eggredcow");
		CrystalCowEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CrystalCowEggItemID, OreSpawnConstants.CrystalCowEggIndex)).setUnlocalizedName("eggcrystalcow");
		GoldCowEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GoldCowEggItemID, OreSpawnConstants.GoldCowEggIndex)).setUnlocalizedName("egggoldcow");
		EnchantedCowEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.EnchantedCowEggItemID, OreSpawnConstants.EnchantedCowEggIndex)).setUnlocalizedName("eggenchantedcow");
		MOTHRAEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.MOTHRAEggItemID, OreSpawnConstants.MOTHRAEggIndex)).setUnlocalizedName("eggMOTHRA");
		AloEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.AloEggItemID, OreSpawnConstants.AloEggIndex)).setUnlocalizedName("eggalosaurus");
		CryoEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CryoEggItemID, OreSpawnConstants.CryoEggIndex)).setUnlocalizedName("eggcryolophosaurus");
		CamaEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CamaEggItemID, OreSpawnConstants.CamaEggIndex)).setUnlocalizedName("eggcamarasaurus");
		VeloEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.VeloEggItemID, OreSpawnConstants.VeloEggIndex)).setUnlocalizedName("eggvelocityraptor");
		HydroEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.HydroEggItemID, OreSpawnConstants.HydroEggIndex)).setUnlocalizedName("egghydrolisc");
		BasilEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BasilEggItemID, OreSpawnConstants.BasilEggIndex)).setUnlocalizedName("eggbasilisc");
		DragonflyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.DragonflyEggItemID, OreSpawnConstants.DragonflyEggIndex)).setUnlocalizedName("eggdragonfly");
		EmperorScorpionEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.EmperorScorpionEggItemID, OreSpawnConstants.EmperorScorpionEggIndex)).setUnlocalizedName("eggemperorscorpion");
		ScorpionEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.ScorpionEggItemID, OreSpawnConstants.ScorpionEggIndex)).setUnlocalizedName("eggscorpion");
		CaveFisherEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CaveFisherEggItemID, OreSpawnConstants.CaveFisherEggIndex)).setUnlocalizedName("eggcavefisher");
		SpyroEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SpyroEggItemID, OreSpawnConstants.SpyroEggIndex)).setUnlocalizedName("eggspyro");
		BaryonyxEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BaryonyxEggItemID, OreSpawnConstants.BaryonyxEggIndex)).setUnlocalizedName("eggbaryonyx");
		GammaMetroidEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GammaMetroidEggItemID, OreSpawnConstants.GammaMetroidEggIndex)).setUnlocalizedName("egggammametroid");
		CockateilEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CockateilEggItemID, OreSpawnConstants.CockateilEggIndex)).setUnlocalizedName("eggcockateil");
		KyuubiEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.KyuubiEggItemID, OreSpawnConstants.KyuubiEggIndex)).setUnlocalizedName("eggkyuubi");
		AlienEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.AlienEggItemID, OreSpawnConstants.AlienEggIndex)).setUnlocalizedName("eggalien");
		AttackSquidEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.AttackSquidEggItemID, OreSpawnConstants.AttackSquidEggIndex)).setUnlocalizedName("eggattacksquid");
		WaterDragonEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.WaterDragonEggItemID, OreSpawnConstants.WaterDragonEggIndex)).setUnlocalizedName("eggwaterdragon");
		CephadromeEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CephadromeEggItemID, OreSpawnConstants.CephadromeEggIndex)).setUnlocalizedName("eggcephadrome");
		KrakenEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.KrakenEggItemID, OreSpawnConstants.KrakenEggIndex)).setUnlocalizedName("eggkraken");
		LizardEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.LizardEggItemID, OreSpawnConstants.LizardEggIndex)).setUnlocalizedName("egglizard");
		DragonEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.DragonEggItemID, OreSpawnConstants.DragonEggIndex)).setUnlocalizedName("eggdragon");
		BeeEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BeeEggItemID, OreSpawnConstants.BeeEggIndex)).setUnlocalizedName("eggbee");
		TrooperBugEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TrooperEggItemID, OreSpawnConstants.TrooperEggIndex)).setUnlocalizedName("eggtrooper");
		SpitBugEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SpitEggItemID, OreSpawnConstants.SpitEggIndex)).setUnlocalizedName("eggspit");
		StinkBugEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.StinkEggItemID, OreSpawnConstants.StinkEggIndex)).setUnlocalizedName("eggstink");
		OstrichEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.OstrichEggItemID, OreSpawnConstants.OstrichEggIndex)).setUnlocalizedName("eggostrich");
		GazelleEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GazelleEggItemID, OreSpawnConstants.GazelleEggIndex)).setUnlocalizedName("egggazelle");
		ChipmunkEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.ChipmunkEggItemID, OreSpawnConstants.ChipmunkEggIndex)).setUnlocalizedName("eggchipmunk");
		CreepingHorrorEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CreepingHorrorEggItemID, OreSpawnConstants.CreepingHorrorEggIndex)).setUnlocalizedName("eggcreepinghorror");
		TerribleTerrorEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TerribleTerrorEggItemID, OreSpawnConstants.TerribleTerrorEggIndex)).setUnlocalizedName("eggterribleterror");
		CliffRacerEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CliffRacerEggItemID, OreSpawnConstants.CliffRacerEggIndex)).setUnlocalizedName("eggcliffracer");
		TriffidEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TriffidEggItemID, OreSpawnConstants.TriffidEggIndex)).setUnlocalizedName("eggtriffid");
		PitchBlackEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.PitchBlackEggItemID, OreSpawnConstants.PitchBlackEggIndex)).setUnlocalizedName("eggnightmare");
		LurkingTerrorEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.LurkingTerrorEggItemID, OreSpawnConstants.LurkingTerrorEggIndex)).setUnlocalizedName("egglurkingterror");
		GodzillaEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GodzillaEggItemID, OreSpawnConstants.GodzillaEggIndex)).setUnlocalizedName("egggodzilla");
		SmallWormEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SmallWormEggItemID, OreSpawnConstants.SmallWormEggIndex)).setUnlocalizedName("eggsmallworm");
		MediumWormEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.MediumWormEggItemID, OreSpawnConstants.MediumWormEggIndex)).setUnlocalizedName("eggmediumworm");
		LargeWormEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.LargeWormEggItemID, OreSpawnConstants.LargeWormEggIndex)).setUnlocalizedName("egglargeworm");
		CassowaryEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CassowaryEggItemID, OreSpawnConstants.CassowaryEggIndex)).setUnlocalizedName("eggcassowary");
		CloudSharkEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CloudSharkEggItemID, OreSpawnConstants.CloudSharkEggIndex)).setUnlocalizedName("eggcloudshark");
		GoldFishEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GoldFishEggItemID, OreSpawnConstants.GoldFishEggIndex)).setUnlocalizedName("egggoldfish");
		LeafMonsterEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.LeafMonsterEggItemID, OreSpawnConstants.LeafMonsterEggIndex)).setUnlocalizedName("eggleafmonster");
		TshirtEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TshirtEggItemID, OreSpawnConstants.TshirtEggIndex)).setUnlocalizedName("eggtshirt");
		EnderKnightEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.EnderKnightEggItemID, OreSpawnConstants.EnderKnightEggIndex)).setUnlocalizedName("eggenderknight");
		EnderReaperEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.EnderReaperEggItemID, OreSpawnConstants.EnderReaperEggIndex)).setUnlocalizedName("eggenderreaper");
		BeaverEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BeaverEggItemID, OreSpawnConstants.BeaverEggIndex)).setUnlocalizedName("eggbeaver");
		RotatorEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.RotatorEggItemID, OreSpawnConstants.RotatorEggIndex)).setUnlocalizedName("eggrotator");
		VortexEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.VortexEggItemID, OreSpawnConstants.VortexEggIndex)).setUnlocalizedName("eggvortex");
		PeacockEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.PeacockEggItemID, OreSpawnConstants.PeacockEggIndex)).setUnlocalizedName("eggpeacock");
		FairyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.FairyEggItemID, OreSpawnConstants.FairyEggIndex)).setUnlocalizedName("eggfairy");
		DungeonBeastEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.DungeonBeastEggItemID, OreSpawnConstants.DungeonBeastEggIndex)).setUnlocalizedName("eggdungeonbeast");
		RatEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.RatEggItemID, OreSpawnConstants.RatEggIndex)).setUnlocalizedName("eggrat");
		FlounderEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.FlounderEggItemID, OreSpawnConstants.FlounderEggIndex)).setUnlocalizedName("eggflounder");
		WhaleEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.WhaleEggItemID, OreSpawnConstants.WhaleEggIndex)).setUnlocalizedName("eggwhale");
		IrukandjiEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.IrukandjiEggItemID, OreSpawnConstants.IrukandjiEggIndex)).setUnlocalizedName("eggirukandji");
		SkateEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SkateEggItemID, OreSpawnConstants.SkateEggIndex)).setUnlocalizedName("eggskate");
		UrchinEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.UrchinEggItemID, OreSpawnConstants.UrchinEggIndex)).setUnlocalizedName("eggurchin");
		Robot1Egg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.Robot1EggItemID, OreSpawnConstants.Robot1EggIndex)).setUnlocalizedName("eggrobot1");
		Robot2Egg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.Robot2EggItemID, OreSpawnConstants.Robot2EggIndex)).setUnlocalizedName("eggrobot2");
		Robot3Egg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.Robot3EggItemID, OreSpawnConstants.Robot3EggIndex)).setUnlocalizedName("eggrobot3");
		Robot4Egg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.Robot4EggItemID, OreSpawnConstants.Robot4EggIndex)).setUnlocalizedName("eggrobot4");
		GhostEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GhostEggItemID, OreSpawnConstants.GhostEggIndex)).setUnlocalizedName("eggghost");
		GhostSkellyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.GhostSkellyEggItemID, OreSpawnConstants.GhostSkellyEggIndex)).setUnlocalizedName("eggghostskelly");
		BrownAntEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BrownAntEggItemID, OreSpawnConstants.BrownAntEggIndex)).setUnlocalizedName("eggbrownant");
		RedAntEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.RedAntEggItemID, OreSpawnConstants.RedAntEggIndex)).setUnlocalizedName("eggredant");
		RainbowAntEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.RainbowAntEggItemID, OreSpawnConstants.RainbowAntEggIndex)).setUnlocalizedName("eggrainbowant");
		UnstableAntEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.UnstableAntEggItemID, OreSpawnConstants.UnstableAntEggIndex)).setUnlocalizedName("eggunstableant");
		TermiteEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TermiteEggItemID, OreSpawnConstants.TermiteEggIndex)).setUnlocalizedName("eggtermite");
		ButterflyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.ButterflyEggItemID, OreSpawnConstants.ButterflyEggIndex)).setUnlocalizedName("eggbutterfly");
		MothEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.MothEggItemID, OreSpawnConstants.MothEggIndex)).setUnlocalizedName("eggmoth");
		MosquitoEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.MosquitoEggItemID, OreSpawnConstants.MosquitoEggIndex)).setUnlocalizedName("eggmosquito");
		FireflyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.FireflyEggItemID, OreSpawnConstants.FireflyEggIndex)).setUnlocalizedName("eggfirefly");
		TRexEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TRexEggItemID, OreSpawnConstants.TRexEggIndex)).setUnlocalizedName("eggtrex");
		HerculesEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.HerculesEggItemID, OreSpawnConstants.HerculesEggIndex)).setUnlocalizedName("egghercules");
		MantisEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.MantisEggItemID, OreSpawnConstants.MantisEggIndex)).setUnlocalizedName("eggmantis");
		StinkyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.StinkyEggItemID, OreSpawnConstants.StinkyEggIndex)).setUnlocalizedName("eggstinky");
		Robot5Egg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.Robot5EggItemID, OreSpawnConstants.Robot5EggIndex)).setUnlocalizedName("eggrobot5");
		CoinEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CoinEggItemID, OreSpawnConstants.CoinEggIndex)).setUnlocalizedName("eggcoin");
		BoyfriendEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BoyfriendEggItemID, OreSpawnConstants.BoyfriendEggIndex)).setUnlocalizedName("eggboyfriend");
		TheKingEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TheKingEggItemID, OreSpawnConstants.TheKingEggIndex)).setUnlocalizedName("eggtheking");
		TheQueenEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.TheQueenEggItemID, OreSpawnConstants.TheQueenEggIndex)).setUnlocalizedName("eggthequeen");
		ThePrinceEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.ThePrinceEggItemID, OreSpawnConstants.ThePrinceEggIndex)).setUnlocalizedName("eggtheprince");
		EasterBunnyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.EasterBunnyEggItemID, OreSpawnConstants.EasterBunnyEggIndex)).setUnlocalizedName("eggeasterbunny");
		MolenoidEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.MolenoidEggItemID, OreSpawnConstants.MolenoidEggIndex)).setUnlocalizedName("eggmolenoid");
		SeaMonsterEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SeaMonsterEggItemID, OreSpawnConstants.SeaMonsterEggIndex)).setUnlocalizedName("eggseamonster");
		SeaViperEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SeaViperEggItemID, OreSpawnConstants.SeaViperEggIndex)).setUnlocalizedName("eggseaviper");
		CaterKillerEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CaterKillerEggItemID, OreSpawnConstants.CaterKillerEggIndex)).setUnlocalizedName("eggcaterkiller");
		RubberDuckyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.RubberDuckyEggItemID, OreSpawnConstants.RubberDuckyEggIndex)).setUnlocalizedName("eggrubberducky");
		HammerheadEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.HammerheadEggItemID, OreSpawnConstants.HammerheadEggIndex)).setUnlocalizedName("egghammerhead");
		LeonEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.LeonEggItemID, OreSpawnConstants.LeonEggIndex)).setUnlocalizedName("eggleon");
		CriminalEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CriminalEggItemID, OreSpawnConstants.CriminalEggIndex)).setUnlocalizedName("eggcriminal");
		BrutalflyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.BrutalflyEggItemID, OreSpawnConstants.BrutalflyEggIndex)).setUnlocalizedName("eggbrutalfly");
		NastysaurusEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.NastysaurusEggItemID, OreSpawnConstants.NastysaurusEggIndex)).setUnlocalizedName("eggnastysaurus");
		PointysaurusEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.PointysaurusEggItemID, OreSpawnConstants.PointysaurusEggIndex)).setUnlocalizedName("eggpointysaurus");
		CricketEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CricketEggItemID, OreSpawnConstants.CricketEggIndex)).setUnlocalizedName("eggcricket");
		ThePrincessEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.ThePrincessEggItemID, OreSpawnConstants.ThePrincessEggIndex)).setUnlocalizedName("eggtheprincess");
		FrogEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.FrogEggItemID, OreSpawnConstants.FrogEggIndex)).setUnlocalizedName("eggfrog");
		JefferyEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.JefferyEggItemID, OreSpawnConstants.JefferyEggIndex)).setUnlocalizedName("eggrobot6");
		AntRobotEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.AntRobotEggItemID, OreSpawnConstants.AntRobotEggIndex)).setUnlocalizedName("eggantrobot");
		SpiderRobotEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SpiderRobotEggItemID, OreSpawnConstants.SpiderRobotEggIndex)).setUnlocalizedName("eggspiderrobot");
		SpiderDriverEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.SpiderDriverEggItemID, OreSpawnConstants.SpiderDriverEggIndex)).setUnlocalizedName("eggspiderdriver");
		CrabEgg = (new ItemSpawnEgg(BaseItemID + OreSpawnConstants.CrabEggItemID, OreSpawnConstants.CrabEggIndex)).setUnlocalizedName("eggcrab");
	
	
	}
	
	
	
	
	private void DoDispenserRegistrations() {
		BlockDispenser.dispenseBehaviorRegistry.putObject(LizardEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(WitherSkeletonEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(EnderDragonEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SnowGolemEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(IronGolemEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(WitherBossEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GirlfriendEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BoyfriendEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TheKingEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TheQueenEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(ThePrinceEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(RedCowEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CrystalCowEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GoldCowEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(EnchantedCowEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MOTHRAEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(AloEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CryoEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CamaEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(VeloEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(HydroEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BasilEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(DragonflyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(EmperorScorpionEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(ScorpionEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CaveFisherEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SpyroEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BaryonyxEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GammaMetroidEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CockateilEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(KyuubiEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(AlienEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(AttackSquidEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(WaterDragonEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CephadromeEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(DragonEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(KrakenEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(LizardEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BeeEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TrooperBugEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SpitBugEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(StinkBugEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(OstrichEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GazelleEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(ChipmunkEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CreepingHorrorEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TerribleTerrorEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CliffRacerEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TriffidEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(PitchBlackEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(LurkingTerrorEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GodzillaEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SmallWormEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MediumWormEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(LargeWormEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CassowaryEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CloudSharkEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GoldFishEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(LeafMonsterEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TshirtEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(EnderKnightEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(EnderReaperEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BeaverEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(RotatorEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(VortexEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(PeacockEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(FairyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(DungeonBeastEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(RatEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(FlounderEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(WhaleEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(IrukandjiEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SkateEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(UrchinEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Robot1Egg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Robot2Egg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Robot3Egg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Robot4Egg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GhostEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(GhostSkellyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BrownAntEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(RedAntEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(RainbowAntEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(UnstableAntEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TermiteEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(ButterflyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MothEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MosquitoEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(FireflyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(TRexEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(HerculesEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MantisEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(StinkyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(Robot5Egg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CoinEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(EasterBunnyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MolenoidEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SeaMonsterEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SeaViperEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CaterKillerEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(LeonEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(HammerheadEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(RubberDuckyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CriminalEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(BrutalflyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(NastysaurusEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(PointysaurusEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CricketEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(ThePrincessEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(FrogEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(JefferyEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(AntRobotEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SpiderRobotEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(SpiderDriverEgg, new DispenserBehaviorOreSpawnEgg());
		BlockDispenser.dispenseBehaviorRegistry.putObject(CrabEgg, new DispenserBehaviorOreSpawnEgg());
		
		
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyIrukandjiArrow, new MyDispenserBehaviorArrow());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyWaterBall, new MyDispenserBehaviorWDCharge());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MySunspotUrchin, new MyDispenserBehaviorSunspotUrchin());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyAcid, new MyDispenserBehaviorAcid());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyIceBall, new MyDispenserBehaviorIceball());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyIrukandji, new MyDispenserBehaviorDeadIrukandji());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyLaserBall, new MyDispenserBehaviorLaserball());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MySmallRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyRedRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyCrystalRedRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyCrystalGreenRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyCrystalBlueRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyCrystalTNTRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyBlueRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyGreenRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyPurpleRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MySpikeyRock, new MyDispenserBehaviorRock());
		BlockDispenser.dispenseBehaviorRegistry.putObject(MyTNTRock, new MyDispenserBehaviorRock());
	
	}
	
	
	
	
	@EventHandler
	public void load(FMLInitializationEvent event) {}

	
	
	
	
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		BMaze = new BasiliskMaze();
		RubyDungeon = new RubyBirdDungeon();
		MyDungeon = new GenericDungeon();
		OreSpawnTrees = new Trees();
		OreSpawnUtils = new MyUtils();
		Chunker = new ChunkOreGenerator();
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SideOnly(Side.CLIENT)
	public Entity spawnEntity(int entityId, World world, double scaledX, double scaledY, double scaledZ) {
		return null;
	}

	public static Entity getPointedAtEntity(World world, EntityPlayer player, double dist) {
		Entity pointedAt = null;
		if (player != null)
		{
			
			if (world != null)
			{
				
				double d0 = dist;
				double d1 = dist;
				Vec3 vec3 = player.getPosition(1.0F);
				Vec3 vec31 = player.getLook(1.0F);
				Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
				pointedAt = null;
				float f1 = 1.0F;
				List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand((double)f1, (double)f1, (double)f1));
				double d2 = d1;
	
				for (int i = 0; i < list.size(); i++)
				{
					Entity entity = (Entity)list.get(i);
					
					if (entity.canBeCollidedWith())
					{
						float f2 = entity.getCollisionBorderSize();
						AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
						MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
						
						if (axisalignedbb.isVecInside(vec3))
						{
							if (0.0D < d2 || d2 == 0.0D)
							{
								pointedAt = entity;
								d2 = 0.0D;
							}
							
						} else if (movingobjectposition != null)
						{
							double d3 = vec3.distanceTo(movingobjectposition.hitVec);
							
							if (d3 < d2 || d2 == 0.0D)
							{
								if (entity == player.ridingEntity && !entity.canRiderInteract())
								{
									if (d2 == 0.0D)
									{
										pointedAt = entity;
									}
								}
								else
								{
									pointedAt = entity;
									d2 = d3;
								}
							}
						}
					}
				}
			}
		}
		return pointedAt;
	}

	
	
	
	
	public static boolean setBlockFast(World world, int par1, int par2, int par3, Block par4, int par5, int par6) {
		if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
		{
			if (par2 < 0)
			{
				return false;
			}
			if (par2 >= 256)
			{
				return false;
			}
			
			
			Chunk chunk = world.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
			
			Block k1 = Blocks.air;
			if ((par6 & 1) != 0)
			{
				k1 = chunk.getBlock(par1 & 15, par2, par3 & 15);
			}

			boolean flag = setBlockIDWithMetadataFast(chunk, par1 & 15, par2, par3 & 15, par4, par5);
			
			
			if (flag)
			{
				if ((par6 & 2) != 0 && (!world.isRemote || (par6 & 4) == 0))
				{
					world.markBlockForUpdate(par1, par2, par3);
				}

				if (!world.isRemote && (par6 & 1) != 0)
				{
					world.notifyBlockChange(par1, par2, par3, k1);
				}
			}
			

			return flag;
		}
		
		
		
		return false;
	}

	
	
	
	public static boolean setBlockSuperFast(World world, int par1, int par2, int par3, Block par4, int par5, int par6, Chunk refChunk) {
		if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
		{
			if (par2 < 0)
			{
				return false;
			}
			if (par2 >= 256)
			{
				return false;
			}
			
			
			Chunk chunk = world.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
			boolean flag = true;
			if (chunk != refChunk)
			{
				Block k1 = Blocks.air;
				if ((par6 & 1) != 0)
				{
					k1 = chunk.getBlock(par1 & 15, par2, par3 & 15);
				}

				flag = setBlockIDWithMetadataFast(chunk, par1 & 15, par2, par3 & 15, par4, par5);
				
				if (flag)
				{
					if ((par6 & 2) != 0 && (!world.isRemote || (par6 & 4) == 0))
					{
						world.markBlockForUpdate(par1, par2, par3);
					}

					if (!world.isRemote && (par6 & 1) != 0)
					{
						world.notifyBlockChange(par1, par2, par3, k1);
					}
				}
			
			} else {
				setBlockIDWithMetadataFast(chunk, par1 & 15, par2, par3 & 15, par4, par5);
			}

			return flag;
		}
		
		
		
		return false;
	}

	
	
	
	
	
	
	
	public static boolean setBlockIDWithMetadataFast(Chunk chunk, int par1, int par2, int par3, Block par4, int par5) {
		if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000)
		{
			if (par2 < 0 || par2 > 255) return false;
			
			ExtendedBlockStorage[] mystorage = chunk.getBlockStorageArray();
			ExtendedBlockStorage extendedblockstorage = mystorage[par2 >> 4];
			
			if (extendedblockstorage == null)
			{
				if (par4 == Blocks.air)
				{
					return false;
				}
				extendedblockstorage = mystorage[par2 >> 4] = new ExtendedBlockStorage(par2 >> 4 << 4, !chunk.worldObj.provider.hasNoSky);
				chunk.setStorageArrays(mystorage);
			}

			extendedblockstorage.func_150818_a(par1, par2 & 15, par3, par4);
			extendedblockstorage.setExtBlockMetadata(par1, par2 & 15, par3, par5);
			
			return true;
		}
		return false;
	}

	
	
	public static Block getBlockIDInChunk(Chunk chunk, int par1, int par2, int par3) {
		if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
			if (par1 >> 4 != chunk.xPosition) return Blocks.air;
			if (par3 >> 4 != chunk.zPosition) return Blocks.air;
			if (par2 < 0 || par2 > 255) return Blocks.air;
			return chunk.getBlock(par1 & 15, par2, par3 & 15);
		}
		return Blocks.air;
	}

	
	
	public static boolean setBlockIDWithMetadataInChunk(Chunk chunk, int par1, int par2, int par3, Block par4, int par5) {
		if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
			if (par1 >> 4 != chunk.xPosition) return false;
			if (par3 >> 4 != chunk.zPosition) return false;
			if (par2 < 0 || par2 > 255) return false;
			
			ExtendedBlockStorage[] mystorage = chunk.getBlockStorageArray();
			ExtendedBlockStorage extendedblockstorage = mystorage[par2 >> 4];
			
			par1 &= 15;
			par3 &= 15;
			
			if (extendedblockstorage == null) {
				if (par4 == Blocks.air || par4 == null) {
					return false;
				}
				extendedblockstorage = mystorage[par2 >> 4] = new ExtendedBlockStorage(par2 >> 4 << 4, !chunk.worldObj.provider.hasNoSky);
				chunk.setStorageArrays(mystorage);
			}
			extendedblockstorage.func_150818_a(par1, par2 & 15, par3, par4);
			extendedblockstorage.setExtBlockMetadata(par1, par2 & 15, par3, par5);
			
			return true;
		}
		return false;
	}

	
	
	
	private ArmorStats get_armorstats(Configuration config, String s, int dura, int head, int chest, int leg, int boots, int enchant, int e_resp, int e_aqua, int e_prot, int e_fire, int e_blast, int e_proj, int e_unbreak, int e_feather) {
		ArmorStats a = new ArmorStats();
		String arm = "OreSpawnARMOR";
		
		a.durability = config.get(arm, s + "_durability", dura).getInt();
		if (a.durability < dura / 2) a.durability = dura / 2;
		if (a.durability > dura * 2) a.durability = dura * 2;
		a.head_protection = config.get(arm, s + "_head_damage_reduce", head).getInt();
		if (a.head_protection < head - 2) a.head_protection = head - 2;
		a.chest_protection = config.get(arm, s + "_chest_damage_reduce", chest).getInt();
		if (a.chest_protection < chest - 2) a.chest_protection = chest - 2;
		a.leg_protection = config.get(arm, s + "_leggings_damage_reduce", leg).getInt();
		if (a.leg_protection < leg - 2) a.leg_protection = leg - 2;
		a.boot_protection = config.get(arm, s + "_boots_damage_reduce", boots).getInt();
		if (a.boot_protection < boots - 2) a.boot_protection = boots - 2;
		a.enchantability = config.get(arm, s + "_enchantability", enchant).getInt();
		if (a.enchantability < enchant / 2) a.enchantability = enchant / 2;
		if (a.enchantability > enchant * 2) a.enchantability = enchant * 2;

		a.e_respiration = config.get(arm, s + "_enchant_respiration", e_resp).getInt();
		if (a.e_respiration < e_resp / 2) a.e_respiration = e_resp / 2;
		a.e_aquaaffinity = config.get(arm, s + "_enchant_aquaaffinity", e_aqua).getInt();
		if (a.e_aquaaffinity < e_aqua / 2) a.e_aquaaffinity = e_aqua / 2;
		a.e_protection = config.get(arm, s + "_enchant_protection", e_prot).getInt();
		if (a.e_protection < e_prot / 2) a.e_protection = e_prot / 2;
		a.e_fireprotection = config.get(arm, s + "_enchant_fireprotection", e_fire).getInt();
		if (a.e_fireprotection < e_fire / 2) a.e_fireprotection = e_fire / 2;
		a.e_blastprotection = config.get(arm, s + "_enchant_blastprotection", e_blast).getInt();
		if (a.e_blastprotection < e_blast / 2) a.e_blastprotection = e_blast / 2;
		a.e_projectileprotection = config.get(arm, s + "_enchant_projectileprotection", e_proj).getInt();
		if (a.e_projectileprotection < e_proj / 2) a.e_projectileprotection = e_proj / 2;
		a.e_unbreaking = config.get(arm, s + "_enchant_unbreaking", e_unbreak).getInt();
		if (a.e_unbreaking < e_unbreak / 2) a.e_unbreaking = e_unbreak / 2;
		a.e_featherfalling = config.get(arm, s + "_enchant_featherfalling", e_feather).getInt();
		if (a.e_featherfalling < e_feather / 2) a.e_featherfalling = e_feather / 2;

		return a;
	}

	
	private WeaponStats get_weaponstats(Configuration config, String arm, String s, int harvest, int maxuses, int efficiency, int damage, int enchantability) {
		WeaponStats w = new WeaponStats();
		
		w.harvestlevel = config.get(arm, s + "_harvestlevel", harvest).getInt();
		if (w.harvestlevel < harvest - 1) w.harvestlevel = harvest;
		w.maxuses = config.get(arm, s + "_maxuses", maxuses).getInt();
		if (w.maxuses < maxuses / 2) w.maxuses = maxuses / 2;
		if (w.maxuses > maxuses * 2) w.maxuses = maxuses * 2;
		w.efficiency = config.get(arm, s + "_efficiency", efficiency).getInt();
		if (w.efficiency < efficiency / 2) w.efficiency = efficiency / 2;
		if (w.efficiency > efficiency * 2) w.efficiency = efficiency * 2;
		w.damage = config.get(arm, s + "_damage", damage).getInt();
		if (w.damage < damage / 2) w.damage = damage / 2;
		if (w.damage > damage * 2) w.damage = damage * 2;
		w.enchantability = config.get(arm, s + "_enchantability", enchantability).getInt();
		if (w.enchantability < enchantability / 2) w.enchantability = enchantability / 2;
		if (w.enchantability > enchantability * 2) w.enchantability = enchantability * 2;

		return w;
	}

	
	private MobStats get_mobstats(Configuration config, String arm, String s, int health, int attack, int defense) {
		MobStats m = new MobStats();
		
		m.health = config.get(arm, s + "_health", health).getInt();
		if (m.health < health / 2) m.health = health / 2;
		if (m.health > health * 2) m.health = health * 2;
		m.attack = config.get(arm, s + "_attack", attack).getInt();
		if (m.attack < attack / 2) m.attack = attack / 2;
		if (m.attack > attack * 2) m.attack = attack * 2;
		m.defense = config.get(arm, s + "_defense", defense).getInt();
		if (m.defense < defense - 4) m.defense = defense - 4;
		if (m.defense > defense + 4) m.defense = defense + 4;
		if (m.defense > 22) m.defense = 22;
		if (m.defense < 0) m.defense = 0;

		return m;
	}

	
	private OreStats get_orestats(Configuration config, String arm, String s, int rate, int clumpsize, int min, int max) {
		OreStats o = new OreStats();
		
		o.rate = config.get(arm, s + "_rate", rate).getInt();
		if (o.rate < rate / 2) o.rate = rate / 2;
		if (o.rate > rate * 2) o.rate = rate * 2;
		o.clumpsize = config.get(arm, s + "_clumpsize", clumpsize).getInt();
		if (o.clumpsize < clumpsize / 2) o.clumpsize = clumpsize / 2;
		if (o.clumpsize > clumpsize * 2) o.clumpsize = clumpsize * 2;
		if (o.clumpsize < 1) o.clumpsize = 1;
		o.mindepth = config.get(arm, s + "_mindepth", min).getInt();
		if (o.mindepth < 0) o.mindepth = 0;
		o.maxdepth = config.get(arm, s + "_maxdepth", max).getInt();
		if (o.maxdepth < 0) o.maxdepth = 0;
		if (o.maxdepth - o.mindepth < 10) {
			o.mindepth = min;
			o.maxdepth = max;
		}
		return o;
	}

	
	
	private void disableAllMobs() {
		MosquitoEnable = 0;
		GhostEnable = 0;
		GhostSkellyEnable = 0;
		SpiderDriverEnable = 0;
		CrabEnable = 0;
		JefferyEnable = 0;
		MothraEnable = 0;
		BrutalflyEnable = 0;
		NastysaurusEnable = 0;
		PointysaurusEnable = 0;
		MothraPeaceful = 0;
		BlackAntEnable = 0;
		RedAntEnable = 0;
		TermiteEnable = 0;
		UnstableAntEnable = 0;
		RainbowAntEnable = 0;
		AlosaurusEnable = 0;
		HammerheadEnable = 0;
		LeonEnable = 0;
		CaterKillerEnable = 0;
		MolenoidEnable = 0;
		TRexEnable = 0;
		CriminalEnable = 0;
		CryolophosaurusEnable = 0;
		RatEnable = 0;
		UrchinEnable = 0;
		CamarasaurusEnable = 0;
		VelocityRaptorEnable = 0;
		HydroliscEnable = 0;
		SpyroEnable = 0;
		BaryonyxEnable = 0;
		CockateilEnable = 0;
		CassowaryEnable = 0;
		EasterBunnyEnable = 0;
		PeacockEnable = 0;
		KyuubiEnable = 0;
		CephadromeEnable = 0;
		DragonEnable = 0;
		GammaMetroidEnable = 0;
		BasiliskEnable = 0;
		DragonflyEnable = 0;
		EmperorScorpionEnable = 0;
		TrooperBugEnable = 0;
		SpitBugEnable = 0;
		StinkBugEnable = 0;
		ScorpionEnable = 0;
		CaveFisherEnable = 0;
		AlienEnable = 0;
		WaterDragonEnable = 0;
		SeaMonsterEnable = 0;
		SeaViperEnable = 0;
		AttackSquidEnable = 0;
		Robot1Enable = 0;
		Robot2Enable = 0;
		Robot3Enable = 0;
		Robot4Enable = 0;
		Robot5Enable = 0;
		RotatorEnable = 0;
		VortexEnable = 0;
		DungeonBeastEnable = 0;
		KrakenEnable = 0;
		LizardEnable = 0;
		RubberDuckyEnable = 0;
		GirlfriendEnable = 0;
		BoyfriendEnable = 0;
		FireflyEnable = 0;
		FairyEnable = 0;
		BeeEnable = 0;
		TheKingEnable = 0;
		TheQueenEnable = 0;
		MantisEnable = 0;
		StinkyEnable = 0;
		HerculesBeetleEnable = 0;
		ChipmunkEnable = 0;
		OstrichEnable = 0;
		GazelleEnable = 0;
		CowEnable = 0;
		ButterflyEnable = 0;
		MothEnable = 0;
		TshirtEnable = 0;
		CoinEnable = 0;
		CreepingHorrorEnable = 0;
		TerribleTerrorEnable = 0;
		CliffRacerEnable = 0;
		TriffidEnable = 0;
		WormEnable = 0;
		CloudSharkEnable = 0;
		GoldFishEnable = 0;
		LeafMonsterEnable = 0;
		EnderKnightEnable = 0;
		EnderReaperEnable = 0;
		BeaverEnable = 0;
		IrukandjiEnable = 0;
		SkateEnable = 0;
		WhaleEnable = 0;
		FlounderEnable = 0;
		PitchBlackEnable = 0;
		LurkingTerrorEnable = 0;
		GodzillaEnable = 0;
		CrabEnable = 0;
	}

	
	
	private void laySomeEggs() {
		MySpiderSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SpiderBlockID)).setBlockName("orespider"));
		MyBatSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BatBlockID)).setBlockName("orebat"));
		MyCowSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CowBlockID)).setBlockName("orecow"));
		MyPigSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.PigBlockID)).setBlockName("orepig"));
		MySquidSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SquidBlockID)).setBlockName("oresquid"));
		MyChickenSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.ChickenBlockID)).setBlockName("orechicken"));
		MyCreeperSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CreeperBlockID)).setBlockName("orecreeper"));
		MySkeletonSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SkeletonBlockID)).setBlockName("oreskeleton"));
		MyZombieSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.ZombieBlockID)).setBlockName("orezombie"));
		MySlimeSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SlimeBlockID)).setBlockName("oreslime"));
		MyGhastSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GhastBlockID)).setBlockName("oreghast"));
		MyZombiePigmanSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.ZombiePigmanBlockID)).setBlockName("orezombiepigman"));
		MyEndermanSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EndermanBlockID)).setBlockName("oreenderman"));
		MyCaveSpiderSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CaveSpiderBlockID)).setBlockName("orecavespider"));
		MySilverfishSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SilverfishBlockID)).setBlockName("oresilverfish"));
		MyMagmaCubeSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.MagmaCubeBlockID)).setBlockName("oremagmacube"));
		MyWitchSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.WitchBlockID)).setBlockName("orewitch"));
		MySheepSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SheepBlockID)).setBlockName("oresheep"));
		MyWolfSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.WolfBlockID)).setBlockName("orewolf"));
		MyMooshroomSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.MooshroomBlockID)).setBlockName("oremooshroom"));
		MyOcelotSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.OcelotBlockID)).setBlockName("oreocelot"));
		MyBlazeSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BlazeBlockID)).setBlockName("oreblaze"));
		MyWitherSkeletonSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.WitherSkeletonBlockID)).setBlockName("orewitherskeleton"));
		MyEnderDragonSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EnderDragonBlockID)).setBlockName("oreenderdragon"));
		MySnowGolemSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SnowGolemBlockID)).setBlockName("oresnowgolem"));
		MyIronGolemSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.IronGolemBlockID)).setBlockName("oreirongolem"));
		MyWitherBossSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.WitherBossBlockID)).setBlockName("orewitherboss"));
		MyGirlfriendSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GirlfriendBlockID)).setBlockName("oregirlfriend"));
		MyBoyfriendSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BoyfriendBlockID)).setBlockName("oreboyfriend"));
		MyRedCowSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.RedCowBlockID)).setBlockName("oreredcow"));
		MyCrystalCowSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CrystalCowBlockID)).setBlockName("orecrystalcow"));
		MyVillagerSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.VillagerBlockID)).setBlockName("orevillager"));
		MyGoldCowSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GoldCowBlockID)).setBlockName("oregoldcow"));
		MyEnchantedCowSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EnchantedCowBlockID)).setBlockName("oreenchantedcow"));
		MyMOTHRASpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.MOTHRABlockID)).setBlockName("oreMOTHRA"));
		MyAntBlock = (new AntBlock(BaseBlockID + OreSpawnConstants.AntBlockID)).setBlockName("AntBlock");
		MyRedAntBlock = (new AntBlock(BaseBlockID + OreSpawnConstants.RedAntBlockID)).setBlockName("RedAntBlock");
		TermiteBlock = (new AntBlock(BaseBlockID + OreSpawnConstants.TermiteBlockID)).setBlockName("TermiteBlock");
		CrystalTermiteBlock = (new CrystalAntBlock(BaseBlockID + OreSpawnConstants.CrystalTermiteBlockID)).setBlockName("CrystalTermiteBlock");
		MyRainbowAntBlock = (new AntBlock(BaseBlockID + OreSpawnConstants.RainbowAntBlockID)).setBlockName("RainbowAntBlock");
		MyUnstableAntBlock = (new AntBlock(BaseBlockID + OreSpawnConstants.UnstableAntBlockID)).setBlockName("UnstableAntBlock");
		MyAloSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.AloBlockID)).setBlockName("orealosaurus"));
		MyCryoSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CryoBlockID)).setBlockName("orecryolophosaurus"));
		MyCamaSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CamaBlockID)).setBlockName("orecamarasaurus"));
		MyVeloSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.VeloBlockID)).setBlockName("orevelocityraptor"));
		MyHydroSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.HydroBlockID)).setBlockName("orehydrolisc"));
		MyBasilSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BasilBlockID)).setBlockName("orebasilisc"));
		MyDragonflySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.DragonflyBlockID)).setBlockName("oredragonfly"));
		MyEmperorScorpionSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EmperorScorpionBlockID)).setBlockName("oreemperorscorpion"));
		MyScorpionSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.ScorpionBlockID)).setBlockName("orescorpion"));
		MyCaveFisherSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CaveFisherBlockID)).setBlockName("orecavefisher"));
		MySpyroSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SpyroBlockID)).setBlockName("orespyro"));
		MyBaryonyxSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BaryonyxBlockID)).setBlockName("orebaryonyx"));
		MyGammaMetroidSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GammaMetroidBlockID)).setBlockName("oregammametroid"));
		MyCockateilSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CockateilBlockID)).setBlockName("orecockateil"));
		MyKyuubiSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.KyuubiBlockID)).setBlockName("orekyuubi"));
		MyAlienSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.AlienBlockID)).setBlockName("orealien"));
		MyAttackSquidSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.AttackSquidBlockID)).setBlockName("oreattacksquid"));
		MyWaterDragonSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.WaterDragonBlockID)).setBlockName("orewaterdragon"));
		MyCephadromeSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CephadromeBlockID)).setBlockName("orecephadrome"));
		MyDragonSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.DragonBlockID)).setBlockName("oredragon"));
		MyKrakenSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.KrakenBlockID)).setBlockName("orekraken"));
		MyLizardSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.LizardBlockID)).setBlockName("orelizard"));
		MyBeeSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BeeBlockID)).setBlockName("orebee"));
		MyHorseSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.HorseBlockID)).setBlockName("orehorse"));
		MyTrooperBugSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TrooperBugBlockID)).setBlockName("oretrooper"));
		MySpitBugSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SpitBugBlockID)).setBlockName("orespit"));
		MyStinkBugSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.StinkBugBlockID)).setBlockName("orestink"));
		MyOstrichSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.OstrichBlockID)).setBlockName("oreostrich"));
		MyGazelleSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GazelleBlockID)).setBlockName("oregazelle"));
		MyChipmunkSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.ChipmunkBlockID)).setBlockName("orechipmunk"));
		MyCreepingHorrorSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CreepingHorrorBlockID)).setBlockName("orecreepinghorror"));
		MyTerribleTerrorSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TerribleTerrorBlockID)).setBlockName("oreterribleterror"));
		MyCliffRacerSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CliffRacerBlockID)).setBlockName("orecliffracer"));
		MyTriffidSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TriffidBlockID)).setBlockName("oretriffid"));
		MyPitchBlackSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.PitchBlackBlockID)).setBlockName("orenightmare"));
		MyLurkingTerrorSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.LurkingTerrorBlockID)).setBlockName("orelurkingterror"));
		MyGodzillaPartSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GodzillaPartBlockID)).setBlockName("oregodzillapart"));
		MyGodzillaSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GodzillaBlockID)).setBlockName("oregodzilla"));
		MySmallWormSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SmallWormBlockID)).setBlockName("oresmallworm"));
		MyMediumWormSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.MediumWormBlockID)).setBlockName("oremediumworm"));
		MyLargeWormSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.LargeWormBlockID)).setBlockName("orelargeworm"));
		MyCassowarySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CassowaryBlockID)).setBlockName("orecassowary"));
		MyCloudSharkSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CloudSharkBlockID)).setBlockName("orecloudshark"));
		MyGoldFishSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.GoldFishBlockID)).setBlockName("oregoldfish"));
		MyLeafMonsterSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.LeafMonsterBlockID)).setBlockName("oreleafmonster"));
		MyTshirtSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TshirtBlockID)).setBlockName("oretshirt"));
		MyEnderKnightSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EnderKnightBlockID)).setBlockName("oreenderknight"));
		MyEnderReaperSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EnderReaperBlockID)).setBlockName("oreenderreaper"));
		MyBeaverSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BeaverBlockID)).setBlockName("orebeaver"));
		MyUrchinSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.UrchinBlockID)).setBlockName("oreurchin"));
		MyFlounderSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.FlounderBlockID)).setBlockName("oreflounder"));
		MySkateSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SkateBlockID)).setBlockName("oreskate"));
		MyRotatorSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.RotatorBlockID)).setBlockName("orerotator"));
		MyPeacockSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.PeacockBlockID)).setBlockName("orepeacock"));
		MyFairySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.FairyBlockID)).setBlockName("orefairy"));
		MyDungeonBeastSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.DungeonBeastBlockID)).setBlockName("oredungeonbeast"));
		MyVortexSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.VortexBlockID)).setBlockName("orevortex"));
		MyRatSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.RatBlockID)).setBlockName("orerat"));
		MyWhaleSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.WhaleBlockID)).setBlockName("orewhale"));
		MyIrukandjiSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.IrukandjiBlockID)).setBlockName("oreirukandji"));
		MyTRexSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TRexBlockID)).setBlockName("oretrex"));
		MyHerculesSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.HerculesBlockID)).setBlockName("orehercules"));
		MyMantisSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.MantisBlockID)).setBlockName("oremantis"));
		MyStinkySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.StinkyBlockID)).setBlockName("orestinky"));
		MyTheKingPartSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TheKingPartBlockID)).setBlockName("orethekingpart"));
		MyTheKingSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TheKingBlockID)).setBlockName("oretheking"));
		MyTheQueenPartSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TheQueenPartBlockID)).setBlockName("orethequeenpart"));
		MyTheQueenSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.TheQueenBlockID)).setBlockName("orethequeen"));
		MyEasterBunnySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.EasterBunnyBlockID)).setBlockName("oreeasterbunny"));
		MyCaterKillerSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CaterKillerBlockID)).setBlockName("orecaterkiller"));
		MyMolenoidSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.MolenoidBlockID)).setBlockName("oremolenoid"));
		MySeaMonsterSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SeaMonsterBlockID)).setBlockName("oreseamonster"));
		MySeaViperSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SeaViperBlockID)).setBlockName("oreseaviper"));
		MyLeonSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.LeonBlockID)).setBlockName("oreleon"));
		MyHammerheadSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.HammerheadBlockID)).setBlockName("orehammerhead"));
		MyRubberDuckySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.RubberDuckyBlockID)).setBlockName("orerubberducky"));
		MyCriminalSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CriminalBlockID)).setBlockName("orecriminal"));
		MyBrutalflySpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.BrutalflyBlockID)).setBlockName("orebrutalfly"));
		MyNastysaurusSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.NastysaurusBlockID)).setBlockName("orenastysaurus"));
		MyPointysaurusSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.PointysaurusBlockID)).setBlockName("orepointysaurus"));
		MyCricketSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CricketBlockID)).setBlockName("orecricket"));
		MyFrogSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.FrogBlockID)).setBlockName("orefrog"));
		MySpiderDriverSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.SpiderDriverBlockID)).setBlockName("orespiderdriver"));
		MyCrabSpawnBlock = (OreGenericEgg)((new OreGenericEgg(BaseBlockID + OreSpawnConstants.CrabBlockID)).setBlockName("orecrab"));
	}

	
	private void getMobs(Configuration config, String mobs) {
		MosquitoEnable = config.get(mobs, "MosquitoEnable", 1).getInt();
		RockEnable = config.get(mobs, "RockEnable", 1).getInt();
		GhostEnable = config.get(mobs, "GhostEnable", 1).getInt();
		GhostSkellyEnable = config.get(mobs, "GhostSkellyEnable", 1).getInt();
		SpiderDriverEnable = config.get(mobs, "SpiderDriverEnable", 1).getInt();
		JefferyEnable = config.get(mobs, "JefferyEnable", 1).getInt();
		MothraEnable = config.get(mobs, "MothraEnable", 1).getInt();
		BrutalflyEnable = config.get(mobs, "BrutalflyEnable", 1).getInt();
		NastysaurusEnable = config.get(mobs, "NastysaurusEnable", 1).getInt();
		PointysaurusEnable = config.get(mobs, "PointysaurusEnable", 1).getInt();
		CricketEnable = config.get(mobs, "CricketEnable", 1).getInt();
		FrogEnable = config.get(mobs, "FrogEnable", 1).getInt();
		MothraPeaceful = config.get(mobs, "MothraPeaceful", 0).getInt();
		BlackAntEnable = config.get(mobs, "BlackAntEnable", 1).getInt();
		RedAntEnable = config.get(mobs, "RedAntEnable", 1).getInt();
		TermiteEnable = config.get(mobs, "TermiteEnable", 1).getInt();
		UnstableAntEnable = config.get(mobs, "UnstableAntEnable", 1).getInt();
		RainbowAntEnable = config.get(mobs, "RainbowedAntEnable", 1).getInt();
		AlosaurusEnable = config.get(mobs, "AlosaurusEnable", 1).getInt();
		HammerheadEnable = config.get(mobs, "HammerheadEnable", 1).getInt();
		LeonEnable = config.get(mobs, "LeonEnable", 1).getInt();
		CaterKillerEnable = config.get(mobs, "CaterKillerEnable", 1).getInt();
		MolenoidEnable = config.get(mobs, "MolenoidEnable", 1).getInt();
		TRexEnable = config.get(mobs, "TRexEnable", 1).getInt();
		CriminalEnable = config.get(mobs, "CriminalEnable", 1).getInt();
		CryolophosaurusEnable = config.get(mobs, "CryolophosaurusEnable", 1).getInt();
		RatEnable = config.get(mobs, "RatEnable", 1).getInt();
		UrchinEnable = config.get(mobs, "UrchinEnable", 1).getInt();
		CamarasaurusEnable = config.get(mobs, "CamarasaurusEnable", 1).getInt();
		VelocityRaptorEnable = config.get(mobs, "VelocityRaptorEnable", 1).getInt();
		HydroliscEnable = config.get(mobs, "HydroliscEnable", 1).getInt();
		SpyroEnable = config.get(mobs, "SpyroEnable", 1).getInt();
		BaryonyxEnable = config.get(mobs, "BaryonyxEnable", 1).getInt();
		CockateilEnable = config.get(mobs, "BirdEnable", 1).getInt();
		CassowaryEnable = config.get(mobs, "CassowaryEnable", 1).getInt();
		EasterBunnyEnable = config.get(mobs, "EasterBunnyEnable", 1).getInt();
		PeacockEnable = config.get(mobs, "PeacockEnable", 1).getInt();
		KyuubiEnable = config.get(mobs, "KyuubiEnable", 1).getInt();
		CephadromeEnable = config.get(mobs, "CephadromeEnable", 1).getInt();
		DragonEnable = config.get(mobs, "DragonEnable", 1).getInt();
		GammaMetroidEnable = config.get(mobs, "GammaMetroidEnable", 1).getInt();
		BasiliskEnable = config.get(mobs, "BasiliskEnable", 1).getInt();
		DragonflyEnable = config.get(mobs, "DragonflyEnable", 1).getInt();
		EmperorScorpionEnable = config.get(mobs, "EmperorScorpionEnable", 1).getInt();
		TrooperBugEnable = config.get(mobs, "TrooperBugEnable", 1).getInt();
		SpitBugEnable = config.get(mobs, "SpitBugEnable", 1).getInt();
		StinkBugEnable = config.get(mobs, "StinkBugEnable", 1).getInt();
		ScorpionEnable = config.get(mobs, "ScorpionEnable", 1).getInt();
		CaveFisherEnable = config.get(mobs, "CaveFisherEnable", 1).getInt();
		AlienEnable = config.get(mobs, "AlienEnable", 1).getInt();
		WaterDragonEnable = config.get(mobs, "WaterDragonEnable", 1).getInt();
		SeaMonsterEnable = config.get(mobs, "SeaMonsterEnable", 1).getInt();
		SeaViperEnable = config.get(mobs, "SeaViperEnable", 1).getInt();
		AttackSquidEnable = config.get(mobs, "AttackSquidEnable", 1).getInt();
		Robot1Enable = config.get(mobs, "Robot1Enable", 1).getInt();
		Robot2Enable = config.get(mobs, "Robot2Enable", 1).getInt();
		Robot3Enable = config.get(mobs, "Robot3Enable", 1).getInt();
		Robot4Enable = config.get(mobs, "Robot4Enable", 1).getInt();
		Robot5Enable = config.get(mobs, "Robot5Enable", 1).getInt();
		RotatorEnable = config.get(mobs, "RotatorEnable", 1).getInt();
		VortexEnable = config.get(mobs, "VortexEnable", 1).getInt();
		DungeonBeastEnable = config.get(mobs, "DungeonBeastEnable", 1).getInt();
		KrakenEnable = config.get(mobs, "KrakenEnable", 1).getInt();
		LizardEnable = config.get(mobs, "LizardEnable", 1).getInt();
		RubberDuckyEnable = config.get(mobs, "RubberDuckyEnable", 1).getInt();
		GirlfriendEnable = config.get(mobs, "GirlfriendEnable", 1).getInt();
		BoyfriendEnable = config.get(mobs, "BoyfriendEnable", 0).getInt();
		FireflyEnable = config.get(mobs, "FireflyEnable", 1).getInt();
		FairyEnable = config.get(mobs, "FairyEnable", 1).getInt();
		BeeEnable = config.get(mobs, "BeeEnable", 1).getInt();
		TheKingEnable = config.get(mobs, "TheKingEnable", 1).getInt();
		TheQueenEnable = config.get(mobs, "TheQueenEnable", 1).getInt();
		MantisEnable = config.get(mobs, "MantisEnable", 1).getInt();
		StinkyEnable = config.get(mobs, "StinkyEnable", 1).getInt();
		HerculesBeetleEnable = config.get(mobs, "HerculesBeetleEnable", 1).getInt();
		ChipmunkEnable = config.get(mobs, "ChipmunkEnable", 1).getInt();
		OstrichEnable = config.get(mobs, "OstrichEnable", 1).getInt();
		GazelleEnable = config.get(mobs, "GazelleEnable", 1).getInt();
		CowEnable = config.get(mobs, "CowEnable", 1).getInt();
		ButterflyEnable = config.get(mobs, "ButterflyEnable", 1).getInt();
		MothEnable = config.get(mobs, "MothEnable", 1).getInt();
		TshirtEnable = config.get(mobs, "TshirtEnable", 1).getInt();
		CoinEnable = config.get(mobs, "CoinEnable", 1).getInt();
		CreepingHorrorEnable = config.get(mobs, "CreepingHorrorEnable", 1).getInt();
		TerribleTerrorEnable = config.get(mobs, "TerribleTerrorEnable", 1).getInt();
		CliffRacerEnable = config.get(mobs, "CliffRacerEnable", 1).getInt();
		TriffidEnable = config.get(mobs, "TriffidEnable", 1).getInt();
		WormEnable = config.get(mobs, "WormEnable", 1).getInt();
		CloudSharkEnable = config.get(mobs, "CloudSharkEnable", 1).getInt();
		GoldFishEnable = config.get(mobs, "GoldFishEnable", 1).getInt();
		LeafMonsterEnable = config.get(mobs, "LeafMonsterEnable", 1).getInt();
		EnderKnightEnable = config.get(mobs, "EnderKnightEnable", 1).getInt();
		EnderReaperEnable = config.get(mobs, "EnderReaperEnable", 1).getInt();
		BeaverEnable = config.get(mobs, "BeaverEnable", 1).getInt();
		IrukandjiEnable = config.get(mobs, "IrukandjiEnable", 1).getInt();
		SkateEnable = config.get(mobs, "SkateEnable", 1).getInt();
		WhaleEnable = config.get(mobs, "WhaleEnable", 1).getInt();
		FlounderEnable = config.get(mobs, "FlounderEnable", 1).getInt();
		PitchBlackEnable = config.get(mobs, "NightmareEnable", 1).getInt();
		LurkingTerrorEnable = config.get(mobs, "LurkingTerrorEnable", 1).getInt();
		GodzillaEnable = config.get(mobs, "GodzillaEnable", 1).getInt();
		CrabEnable = config.get(mobs, "CrabEnable", 1).getInt();
		
		Bee_stats = this.get_mobstats(config, mobs, "Bee", 80, 12, 5);
		Mantis_stats = this.get_mobstats(config, mobs, "Mantis", 120, 16, 10);
		HerculesBeetle_stats = this.get_mobstats(config, mobs, "HerculesBeetle", 250, 30, 19);
		Mothra_stats = this.get_mobstats(config, mobs, "Mothra", 150, 12, 8);
		Brutalfly_stats = this.get_mobstats(config, mobs, "Brutalfly", 110, 10, 6);
		Nastysaurus_stats = this.get_mobstats(config, mobs, "Nastysaurus", 200, 32, 17);
		Pointysaurus_stats = this.get_mobstats(config, mobs, "Pointysaurus", 80, 10, 16);
		Alosaurus_stats = this.get_mobstats(config, mobs, "Alosaurus", 110, 18, 8);
		SpiderRobot_stats = this.get_mobstats(config, mobs, "SpiderRobot", 1500, 100, 16);
		AntRobot_stats = this.get_mobstats(config, mobs, "AntRobot", 300, 30, 16);
		Jeffery_stats = this.get_mobstats(config, mobs, "Jeffery", 550, 40, 18);
		Hammerhead_stats = this.get_mobstats(config, mobs, "Hammerhead", 240, 75, 20);
		Molenoid_stats = this.get_mobstats(config, mobs, "Molenoid", 200, 18, 12);
		TRex_stats = this.get_mobstats(config, mobs, "TRex", 160, 22, 14);
		BandP_stats = this.get_mobstats(config, mobs, "BandP", 100, 1, 18);
		CaterKiller_stats = this.get_mobstats(config, mobs, "CaterKiller", 450, 32, 19);
		Cryolophosaurus_stats = this.get_mobstats(config, mobs, "Cryolophosaurus", 10, 3, 1);
		Rat_stats = this.get_mobstats(config, mobs, "Rat", 5, 3, 1);
		Urchin_stats = this.get_mobstats(config, mobs, "Urchin", 25, 10, 4);
		Kyuubi_stats = this.get_mobstats(config, mobs, "Kyuubi", 125, 10, 10);
		GammaMetroid_stats = this.get_mobstats(config, mobs, "GammaMetroid", 100, 10, 12);
		Basilisk_stats = this.get_mobstats(config, mobs, "Basilisk", 200, 24, 15);
		EmperorScorpion_stats = this.get_mobstats(config, mobs, "EmperorScorpion", 350, 35, 20);
		TrooperBug_stats = this.get_mobstats(config, mobs, "TrooperBug", 200, 20, 15);
		SpitBug_stats = this.get_mobstats(config, mobs, "SpitBug", 100, 10, 12);
		Alien_stats = this.get_mobstats(config, mobs, "Alien", 100, 12, 8);
		WaterDragon_stats = this.get_mobstats(config, mobs, "WaterDragon", 150, 20, 8);
		SeaMonster_stats = this.get_mobstats(config, mobs, "SeaMonster", 110, 14, 8);
		SeaViper_stats = this.get_mobstats(config, mobs, "SeaViper", 160, 22, 12);
		Robot2_stats = this.get_mobstats(config, mobs, "Robot2", 200, 22, 18);
		Robot3_stats = this.get_mobstats(config, mobs, "Robot3", 80, 16, 14);
		Robot4_stats = this.get_mobstats(config, mobs, "Robot4", 170, 12, 18);
		Robot5_stats = this.get_mobstats(config, mobs, "Robot5", 20, 5, 6);
		Rotator_stats = this.get_mobstats(config, mobs, "Rotator", 35, 10, 8);
		Vortex_stats = this.get_mobstats(config, mobs, "Vortex", 150, 26, 10);
		DungeonBeast_stats = this.get_mobstats(config, mobs, "DungeonBeast", 65, 12, 6);
		Triffid_stats = this.get_mobstats(config, mobs, "Triffid", 100, 20, 12);
		LurkingTerror_stats = this.get_mobstats(config, mobs, "LurkingTerror", 30, 6, 5);
		WormSmall_stats = this.get_mobstats(config, mobs, "WormSmall", 10, 3, 0);
		WormMedium_stats = this.get_mobstats(config, mobs, "WormMedium", 30, 10, 8);
		WormLarge_stats = this.get_mobstats(config, mobs, "WormLarge", 90, 18, 14);
		EnderKnight_stats = this.get_mobstats(config, mobs, "EnderKnight", 60, 12, 6);
		EnderReaper_stats = this.get_mobstats(config, mobs, "EnderReaper", 90, 18, 8);
		Irukandji_stats = this.get_mobstats(config, mobs, "Irukandji", 1, 20, 0);
		AttackSquid_stats = this.get_mobstats(config, mobs, "AttackSquid", 10, 8, 0);
		CaveFisher_stats = this.get_mobstats(config, mobs, "CaveFisher", 10, 4, 4);
		CloudShark_stats = this.get_mobstats(config, mobs, "CloudShark", 15, 6, 5);
		CreepingHorror_stats = this.get_mobstats(config, mobs, "CreepingHorror", 10, 3, 2);
		Godzilla_stats = this.get_mobstats(config, mobs, "Mobzilla", 4000, 175, 21);
		Kraken_stats = this.get_mobstats(config, mobs, "Kraken", 1000, 40, 10);
		LeafMonster_stats = this.get_mobstats(config, mobs, "LeafMonster", 6, 2, 1);
		PitchBlack_stats = this.get_mobstats(config, mobs, "Nightmare", 250, 30, 10);
		Scorpion_stats = this.get_mobstats(config, mobs, "Scorpion", 15, 4, 10);
		Skate_stats = this.get_mobstats(config, mobs, "Skate", 8, 8, 4);
		TerribleTerror_stats = this.get_mobstats(config, mobs, "TerribleTerror", 10, 5, 3);
		TheKing_stats = this.get_mobstats(config, mobs, "TheKing", 7000, 350, 21);
		TheQueen_stats = this.get_mobstats(config, mobs, "TheQueen", 6000, 225, 21);
		Leon_stats = this.get_mobstats(config, mobs, "Leonopteryx", 150, 20, 8);
		Crab_stats = this.get_mobstats(config, mobs, "Crab", 180, 24, 16);
	}

	
	
	
	public String getVersion() {
		return "1.7.10.20.3";
	}
}
