package com.extvil.extendedvillages.evcore;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import com.extvil.extendedvillages.evworldgen.ComponentFishHut;
import com.extvil.extendedvillages.evworldgen.ComponentWindmill;
import com.extvil.extendedvillages.evworldgen.FishHutHandler;
import com.extvil.extendedvillages.evworldgen.VillagerFishHutTradeHandler;
import com.extvil.extendedvillages.evworldgen.VillagerWindMillTradeHandler;
import com.extvil.extendedvillages.evworldgen.WindMillHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;



@Mod (modid = "extvil", name = "Extended Villages", version = "1.7.10-1.1")


public class ExtendedVillages 
{
	public static String modid = "extvil";	
	@SidedProxy(clientSide = "com.extvil.extendedvillages.evcore.ExtendedVillagesClient", serverSide = "com.extvil.extendedvillages.evcore.ExtendedVillagesProxy")
	public static ExtendedVillagesProxy proxy;

	@Mod.Instance("extvil")
	public static ExtendedVillages instance;

    public static final ResourceLocation MILLER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Miller.png");
    public static final ResourceLocation FISHER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Fisher.png");
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{   
		
	}
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
		int millvillager = 350;
        VillagerRegistry.instance().registerVillagerId(millvillager);
        VillagerRegistry.instance().registerVillageTradeHandler(millvillager, new VillagerWindMillTradeHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new WindMillHandler());
		VillagerRegistry.instance().registerVillagerSkin(millvillager, this.MILLER_TEXTURE);
		
		MapGenStructureIO.func_143031_a(ComponentWindmill.class, "extvil:Windmill");
		
		int fishvillager = 351;
        VillagerRegistry.instance().registerVillagerId(fishvillager);
        VillagerRegistry.instance().registerVillageTradeHandler(fishvillager, new VillagerFishHutTradeHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new FishHutHandler());
		VillagerRegistry.instance().registerVillagerSkin(fishvillager, this.FISHER_TEXTURE);
		
		MapGenStructureIO.func_143031_a(ComponentFishHut.class, "extvil:FishHut");
	}
}
