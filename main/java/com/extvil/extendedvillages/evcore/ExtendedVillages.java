package com.extvil.extendedvillages.evcore;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.ChestGenHooks;

import com.extvil.extendedvillages.evcore.handler.ConfigHandler;
import com.extvil.extendedvillages.evworldgen.blocks.BlockFakeSandStone;
import com.extvil.extendedvillages.evworldgen.components.ComponentBakery;
import com.extvil.extendedvillages.evworldgen.components.ComponentFishHut;
import com.extvil.extendedvillages.evworldgen.components.ComponentLab;
import com.extvil.extendedvillages.evworldgen.components.ComponentMiner;
import com.extvil.extendedvillages.evworldgen.components.ComponentWindmill;
import com.extvil.extendedvillages.evworldgen.structurehandlers.BakeryHandler;
import com.extvil.extendedvillages.evworldgen.structurehandlers.FishHutHandler;
import com.extvil.extendedvillages.evworldgen.structurehandlers.LabHandler;
import com.extvil.extendedvillages.evworldgen.structurehandlers.MinerHandler;
import com.extvil.extendedvillages.evworldgen.structurehandlers.WindMillHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerBakeryTradeHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerFishHutTradeHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerLabTradeHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerMinerTradeHandler;
import com.extvil.extendedvillages.evworldgen.tradehandlers.VillagerWindMillTradeHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = "extvil", name = "Extended_Villages", version = "1.7.10-2.5", guiFactory = "com.extvil.extendedvillages.evcore.ExtendedVillagesGUIFactory")

public class ExtendedVillages {
	public static String modid = "extvil";
	@SidedProxy(clientSide = "com.extvil.extendedvillages.evcore.ExtendedVillagesClient", serverSide = "com.extvil.extendedvillages.evcore.ExtendedVillagesProxy")
	public static ExtendedVillagesProxy proxy;

	@Mod.Instance("extvil")
	public static ExtendedVillages instance;

	public static boolean isHalloween;
	public static boolean isChristmas;

	public static final ResourceLocation MILLER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/Miller.png");
	public static final ResourceLocation FISHER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/Fisher.png");
	public static final ResourceLocation BAKER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/Baker.png");
	public static final ResourceLocation MINER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/Miner.png");
	public static final ResourceLocation SCIENTIST_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/Scientist.png");

	public static final ResourceLocation HMILLER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/HMiller.png");
	public static final ResourceLocation HFISHER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/HFisher.png");
	public static final ResourceLocation HBAKER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/HBaker.png");
	public static final ResourceLocation HMINER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/HMiner.png");
	public static final ResourceLocation HSCIENTIST_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/HScientist.png");

	public static final ResourceLocation CMILLER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/CMiller.png");
	public static final ResourceLocation CFISHER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/CFisher.png");
	public static final ResourceLocation CBAKER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/CBaker.png");
	public static final ResourceLocation CMINER_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/CMiner.png");
	public static final ResourceLocation CSCIENTIST_TEXTURE = new ResourceLocation(
			"extvil:textures/entities/villager/CScientist.png");

	public static Block SmoothSand;
	public static Block DecoSand;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.LoadFile(event.getSuggestedConfigurationFile());

		SmoothSand = new BlockFakeSandStone(0, 0).setHardness(1.0F).setBlockName("SmoothSand")
				.setBlockTextureName("sandstone_smooth");
		DecoSand = new BlockFakeSandStone(0, 0).setHardness(1.0F).setBlockName("DecoSand")
				.setBlockTextureName("sandstone_carved");

		GameRegistry.registerBlock(SmoothSand, "SmoothSand");
		GameRegistry.registerBlock(DecoSand, "DecoSand");

	}

	@Mod.EventHandler
	public void Init(FMLInitializationEvent event) {

		int millvillager = ConfigHandler.MillerID;
		if (ConfigHandler.enableMill) {
			VillagerRegistry.instance().registerVillagerId(millvillager);
			VillagerRegistry.instance().registerVillageTradeHandler(millvillager, new VillagerWindMillTradeHandler());
			VillagerRegistry.instance().registerVillageCreationHandler(new WindMillHandler());

			MapGenStructureIO.func_143031_a(ComponentWindmill.class, "extvil:Windmill");
		}
		if (ConfigHandler.enableFishHut) {
			int fishvillager = ConfigHandler.FisherID;
			VillagerRegistry.instance().registerVillagerId(fishvillager);
			VillagerRegistry.instance().registerVillageTradeHandler(fishvillager, new VillagerFishHutTradeHandler());
			VillagerRegistry.instance().registerVillageCreationHandler(new FishHutHandler());

			MapGenStructureIO.func_143031_a(ComponentFishHut.class, "extvil:FishHut");
		}
		if (ConfigHandler.enableBakery) {
			int backeryvillager = ConfigHandler.BakerID;
			VillagerRegistry.instance().registerVillagerId(backeryvillager);
			VillagerRegistry.instance().registerVillageTradeHandler(backeryvillager, new VillagerBakeryTradeHandler());
			VillagerRegistry.instance().registerVillageCreationHandler(new BakeryHandler());

			MapGenStructureIO.func_143031_a(ComponentBakery.class, "extvil:Bakery");
		}
		if (ConfigHandler.enableMine) {
			int minervillager = ConfigHandler.MinerID;
			VillagerRegistry.instance().registerVillagerId(minervillager);
			VillagerRegistry.instance().registerVillageTradeHandler(minervillager, new VillagerMinerTradeHandler());
			VillagerRegistry.instance().registerVillageCreationHandler(new MinerHandler());

			MapGenStructureIO.func_143031_a(ComponentMiner.class, "extvil:Minery");
		}
		if (ConfigHandler.enableLab) {
			int scientistvillager = ConfigHandler.ScientistID;
			VillagerRegistry.instance().registerVillagerId(scientistvillager);
			VillagerRegistry.instance().registerVillageTradeHandler(scientistvillager, new VillagerLabTradeHandler());
			VillagerRegistry.instance().registerVillageCreationHandler(new LabHandler());

			MapGenStructureIO.func_143031_a(ComponentLab.class, "extvil:Lab");
		}
		proxy.registerRenderInformation();

	}
}
