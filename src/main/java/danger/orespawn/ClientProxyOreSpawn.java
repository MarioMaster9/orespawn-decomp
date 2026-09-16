package danger.orespawn;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

























public class ClientProxyOreSpawn extends CommonProxyOreSpawn
{
	public void registerRenderThings() {
		MinecraftForge.EVENT_BUS.register(new GirlfriendOverlayGui(Minecraft.getMinecraft()));
		
		
		RenderingRegistry.registerEntityRenderingHandler(Girlfriend.class, new RenderGirlfriend(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Boyfriend.class, new RenderBoyfriend(new ModelBiped(), 0.55F));
		RenderingRegistry.registerEntityRenderingHandler(RedCow.class, new RenderEnchantedCow(new ModelCow(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(GoldCow.class, new RenderEnchantedCow(new ModelCow(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EnchantedCow.class, new RenderEnchantedCow(new ModelCow(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(CrystalCow.class, new RenderEnchantedCow(new ModelCow(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(Shoes.class, new RenderShoe());
		RenderingRegistry.registerEntityRenderingHandler(SunspotUrchin.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(WaterBall.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(InkSack.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(LaserBall.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(IceBall.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(Acid.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(DeadIrukandji.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(BerthaHit.class, new RenderItemUrchin());
		RenderingRegistry.registerEntityRenderingHandler(EntityCage.class, new RenderCage());
		RenderingRegistry.registerEntityRenderingHandler(UltimateFishHook.class, new RenderFish());
		RenderingRegistry.registerEntityRenderingHandler(UltimateArrow.class, new RenderArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownRock.class, new RenderThrownRock());
		RenderingRegistry.registerEntityRenderingHandler(EntityButterfly.class, new RenderButterfly(new ModelButterfly(1.0F), 0.3F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Firefly.class, new RenderFirefly(new ModelFirefly(2.5F), 0.2F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLunaMoth.class, new RenderButterfly(new ModelButterfly(0.75F), 0.4F, 1.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosquito.class, new RenderMosquito(new ModelMosquito(), 0.3F, 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Ghost.class, new RenderGhost(new ModelGhost(), 0.0F, 0.65F));
		RenderingRegistry.registerEntityRenderingHandler(GhostSkelly.class, new RenderGhostSkelly(new ModelGhostSkelly(), 0.0F, 1.05F));
		RenderingRegistry.registerEntityRenderingHandler(Mothra.class, new RenderButterfly(new ModelButterfly(0.2F), 0.75F, 10.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityAnt.class, new RenderAnt(new ModelAnt(), 0.1F, 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRedAnt.class, new RenderAnt(new ModelAnt(), 0.15F, 0.35F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRainbowAnt.class, new RenderAnt(new ModelAnt(), 0.1F, 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityUnstableAnt.class, new RenderAnt(new ModelAnt(), 0.1F, 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(Alosaurus.class, new RenderAlosaurus(new ModelAlosaurus(0.22F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(TRex.class, new RenderTRex(new ModelTRex(0.2F), 1.0F, 1.2F));
		RenderingRegistry.registerEntityRenderingHandler(Tshirt.class, new RenderTshirt(new ModelTshirt(0.22F), 1.0F, 0.33F));
		RenderingRegistry.registerEntityRenderingHandler(Cryolophosaurus.class, new RenderCryolophosaurus(new ModelCryolophosaurus(0.75F), 0.75F, 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Basilisk.class, new RenderBasilisk(new ModelBasilisk(0.3F), 0.5F, 1.25F));
		RenderingRegistry.registerEntityRenderingHandler(Camarasaurus.class, new RenderCamarasaurus(new ModelCamarasaurus(0.65F), 0.65F, 0.65F));
		RenderingRegistry.registerEntityRenderingHandler(Hydrolisc.class, new RenderHydrolisc(new ModelHydrolisc(0.65F), 0.65F, 0.65F));
		RenderingRegistry.registerEntityRenderingHandler(VelocityRaptor.class, new RenderVelocityRaptor(new ModelVelocityRaptor(1.25F), 0.55F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(Dragonfly.class, new RenderDragonfly(new ModelDragonfly(2.0F), 0.3F, 1.5F));
		RenderingRegistry.registerEntityRenderingHandler(Bee.class, new RenderBee(new ModelBee(2.0F), 0.9F, 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(EmperorScorpion.class, new RenderEmperorScorpion(new ModelEmperorScorpion(0.22F), 0.95F, 1.5F));
		RenderingRegistry.registerEntityRenderingHandler(Spyro.class, new RenderSpyro(new ModelSpyro(0.65F), 0.65F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(Baryonyx.class, new RenderBaryonyx(new ModelBaryonyx(0.25F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(GammaMetroid.class, new RenderGammaMetroid(new ModelGammaMetroid(0.45F), 0.75F, 0.9F));
		RenderingRegistry.registerEntityRenderingHandler(Cockateil.class, new RenderCockateil(new ModelCockateil(1.0F), 0.3F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(RubyBird.class, new RenderCockateil(new ModelCockateil(1.0F), 0.3F, 0.75F));
		
		RenderingRegistry.registerEntityRenderingHandler(Kyuubi.class, new RenderKyuubi(new ModelKyuubi(0.5F), 0.1F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Scorpion.class, new RenderScorpion(new ModelScorpion(0.62F), 0.35F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(CaveFisher.class, new RenderCaveFisher(new ModelCaveFisher(0.62F), 0.35F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(Alien.class, new RenderAlien(new ModelAlien(0.22F), 0.35F, 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(WaterDragon.class, new RenderWaterDragon(new ModelWaterDragon(0.5F), 0.85F, 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(AttackSquid.class, new RenderAttackSquid(new ModelAttackSquid(1.0F), 0.25F, 0.9F));
		RenderingRegistry.registerEntityRenderingHandler(Elevator.class, new RenderElevator());
		RenderingRegistry.registerEntityRenderingHandler(Robot1.class, new RenderRobot1(new ModelRobot1(2.0F), 0.3F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Robot2.class, new RenderRobot2(new ModelRobot2(1.0F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Robot3.class, new RenderRobot3(new ModelRobot3(1.0F), 1.0F, 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(Robot4.class, new RenderRobot4(new ModelRobot4(1.0F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Robot5.class, new RenderRobot5(new ModelRobot5(1.0F), 0.5F, 1.0F));
		
		RenderingRegistry.registerEntityRenderingHandler(Kraken.class, new RenderKraken(new ModelKraken(1.0F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Lizard.class, new RenderLizard(new ModelLizard(0.65F), 0.75F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Cephadrome.class, new RenderCephadrome(new ModelCephadrome(0.55F), 1.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Dragon.class, new RenderDragon(new ModelDragon(0.65F), 1.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Chipmunk.class, new RenderChipmunk(new ModelChipmunk(1.0F), 0.15F, 0.9F));
		RenderingRegistry.registerEntityRenderingHandler(Gazelle.class, new RenderGazelle(new ModelGazelle(0.65F), 0.45F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Ostrich.class, new RenderOstrich(new ModelOstrich(0.65F), 0.55F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(TrooperBug.class, new RenderTrooperBug(new ModelTrooperBug(0.22F), 0.95F, 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(SpitBug.class, new RenderSpitBug(new ModelSpitBug(0.55F), 0.55F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(StinkBug.class, new RenderStinkBug(new ModelStinkBug(0.75F), 0.35F, 0.85F));
		RenderingRegistry.registerEntityRenderingHandler(Island.class, new RenderIsland(new ModelIsland(1.0F), 0.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(IslandToo.class, new RenderIslandToo(new ModelIsland(1.0F), 0.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(CreepingHorror.class, new RenderCreepingHorror(new ModelCreepingHorror(), 0.45F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(TerribleTerror.class, new RenderTerribleTerror(new ModelTerribleTerror(), 0.45F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(CliffRacer.class, new RenderCliffRacer(new ModelCliffRacer(1.0F), 0.3F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Triffid.class, new RenderTriffid(new ModelTriffid(1.0F), 0.3F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(PitchBlack.class, new RenderPitchBlack(new ModelPitchBlack(0.65F), 1.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(LurkingTerror.class, new RenderLurkingTerror(new ModelLurkingTerror(), 0.45F, 0.85F));
		RenderingRegistry.registerEntityRenderingHandler(Godzilla.class, new RenderGodzilla(new ModelGodzilla(0.2F), 1.0F, 2.0F));
		RenderingRegistry.registerEntityRenderingHandler(GodzillaHead.class, new RenderGodzillaHead((ModelGodzilla)null, 0.0F, 0.0F));
		RenderingRegistry.registerEntityRenderingHandler(KingHead.class, new RenderKingHead((ModelTheKing)null, 0.0F, 0.0F));
		RenderingRegistry.registerEntityRenderingHandler(QueenHead.class, new RenderQueenHead((ModelTheQueen)null, 0.0F, 0.0F));
		
		RenderingRegistry.registerEntityRenderingHandler(WormSmall.class, new RenderWormSmall(new ModelWormSmall(), 0.1F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(WormMedium.class, new RenderWormMedium(new ModelWormMedium(), 0.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(WormLarge.class, new RenderWormLarge(new ModelWormLarge(), 0.9F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Cassowary.class, new RenderCassowary(new ModelCassowary(0.55F), 0.5F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(GoldFish.class, new RenderGoldFish(new ModelGoldFish(0.7F), 0.2F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(CloudShark.class, new RenderCloudShark(new ModelCloudShark(1.0F), 0.5F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(LeafMonster.class, new RenderLeafMonster(new ModelLeafMonster(), 0.65F, 1.0F));
		
		RenderingRegistry.registerEntityRenderingHandler(EnderKnight.class, new RenderEnderKnight(new ModelEnderKnight(0.21F), 0.3F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EnderReaper.class, new RenderEnderReaper(new ModelEnderReaper(0.23F), 0.2F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Beaver.class, new RenderBeaver(new ModelBeaver(0.5F), 0.15F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(Termite.class, new RenderAnt(new ModelAnt(), 0.15F, 0.35F));
		
		RenderingRegistry.registerEntityRenderingHandler(Fairy.class, new RenderFairy(new ModelFairy(1.5F), 0.1F, 0.35F));
		RenderingRegistry.registerEntityRenderingHandler(Peacock.class, new RenderPeacock(new ModelPeacock(0.75F), 0.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Rotator.class, new RenderRotator(new ModelRotator(0.25F), 0.1F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Vortex.class, new RenderVortex(new ModelVortex(0.25F), 0.1F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(DungeonBeast.class, new RenderDungeonBeast(new ModelDungeonBeast(0.62F), 0.25F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Rat.class, new RenderRat(new ModelRat(1.0F), 0.1F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(Flounder.class, new RenderFlounder(new ModelFlounder(), 0.1F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Whale.class, new RenderWhale(new ModelWhale(), 0.1F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Irukandji.class, new RenderIrukandji(new ModelIrukandji(1.0F), 0.1F, 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(Skate.class, new RenderSkate(new ModelSkate(1.0F), 0.1F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(Urchin.class, new RenderUrchin(new ModelUrchin(1.0F), 0.35F, 1.25F));
		
		RenderingRegistry.registerEntityRenderingHandler(Mantis.class, new RenderMantis(new ModelMantis(2.0F), 0.9F, 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(HerculesBeetle.class, new RenderHerculesBeetle(new ModelHerculesBeetle(1.0F), 0.99F, 1.1F));
		RenderingRegistry.registerEntityRenderingHandler(Stinky.class, new RenderStinky(new ModelStinky(0.65F), 0.75F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Coin.class, new RenderCoin(new ModelCoin(0.22F), 0.75F, 0.125F));
		
		RenderingRegistry.registerEntityRenderingHandler(TheKing.class, new RenderTheKing(new ModelTheKing(0.65F), 1.9F, 2.1F));
		RenderingRegistry.registerEntityRenderingHandler(TheQueen.class, new RenderTheQueen(new ModelTheQueen(0.65F), 1.9F, 2.0F));
		RenderingRegistry.registerEntityRenderingHandler(ThePrince.class, new RenderThePrince(new ModelThePrince(0.65F), 0.75F, 0.75F));
		
		RenderingRegistry.registerEntityRenderingHandler(Molenoid.class, new RenderMolenoid(new ModelMolenoid(0.5F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(SeaMonster.class, new RenderSeaMonster(new ModelSeaMonster(0.5F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(SeaViper.class, new RenderSeaViper(new ModelSeaViper(0.5F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EasterBunny.class, new RenderEasterBunny(new ModelEasterBunny(0.55F), 0.5F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(CaterKiller.class, new RenderCaterKiller(new ModelCaterKiller(0.22F), 1.0F, 1.25F));
		
		RenderingRegistry.registerEntityRenderingHandler(Leon.class, new RenderLeon(new ModelLeon(0.22F), 1.0F, 1.75F));
		RenderingRegistry.registerEntityRenderingHandler(Hammerhead.class, new RenderHammerhead(new ModelHammerhead(0.33F), 1.0F, 2.5F));
		RenderingRegistry.registerEntityRenderingHandler(RubberDucky.class, new RenderRubberDucky(new ModelRubberDucky(1.0F), 0.15F, 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(ThePrinceTeen.class, new RenderThePrinceTeen(new ModelThePrinceTeen(0.65F), 1.0F, 1.25F));
		RenderingRegistry.registerEntityRenderingHandler(BandP.class, new RenderBandP(new ModelBandP(0.4F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(RockBase.class, new RenderRockBase(new ModelRockBase(1.0F), 0.0F, 1.0F));
		
		RenderingRegistry.registerEntityRenderingHandler(PurplePower.class, new RenderPurplePower(new ModelPurplePower(1.0F), 0.3F, 2.75F));
		RenderingRegistry.registerEntityRenderingHandler(Brutalfly.class, new RenderBrutalfly(new ModelBrutalfly(0.2F), 0.75F, 9.0F));
		RenderingRegistry.registerEntityRenderingHandler(Nastysaurus.class, new RenderNastysaurus(new ModelNastysaurus(0.65F), 1.0F, 1.5F));
		RenderingRegistry.registerEntityRenderingHandler(Pointysaurus.class, new RenderPointysaurus(new ModelPointysaurus(1.0F), 1.0F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Cricket.class, new RenderCricket(new ModelCricket(2.5F), 0.15F, 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(ThePrincess.class, new RenderThePrincess(new ModelThePrincess(0.65F), 0.7F, 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(Frog.class, new RenderFrog(new ModelFrog(1.0F), 0.35F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(ThePrinceAdult.class, new RenderThePrinceAdult(new ModelThePrinceAdult(0.65F), 1.2F, 1.0F));
		
		RenderingRegistry.registerEntityRenderingHandler(SpiderRobot.class, new RenderSpiderRobot(new ModelSpiderRobot(1.0F), 0.99F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(SpiderDriver.class, new RenderSpiderDriver(new ModelSpider(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(GiantRobot.class, new RenderGiantRobot(new ModelGiantRobot(0.25F), 0.99F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(AntRobot.class, new RenderAntRobot(new ModelAntRobot(1.0F), 0.99F, 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(Crab.class, new RenderCrab(new ModelCrab(1.0F), 0.99F, 1.0F));
		
		
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MyBertha, new RenderBertha());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MySlice, new RenderSlice());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MyRoyal, new RenderRoyal());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MySquidZooka, new RenderSquidZooka());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MyHammy, new RenderHammy());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MyBattleAxe, new RenderBattleAxe());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MyChainsaw, new RenderChainsaw());
		MinecraftForgeClient.registerItemRenderer(OreSpawnMain.MyQueenBattleAxe, new RenderQueenBattleAxe());
	}

	
	
	
	
	
	
	
	
	
	
	public void registerSoundThings() {
		MinecraftForge.EVENT_BUS.register(new OreSpawnSounds());
	}

	
	public void registerKeyboardInput() {
		KeyHandler k = new KeyHandler();
		FMLCommonHandler.instance().bus().register(k);
		OreSpawnMain.MyKeyhandler = k;
	}

	
	public void registerNetworkStuff() {
		super.registerNetworkStuff();
		FMLCommonHandler.instance().bus().register(new RiderControl(this.getNetwork()));
	}

	
	
	
	public int setArmorPrefix(String string) {
		return RenderingRegistry.addNewArmourRendererPrefix(string);
	}
}
