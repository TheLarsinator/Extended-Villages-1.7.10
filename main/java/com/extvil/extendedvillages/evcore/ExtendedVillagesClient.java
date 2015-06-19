package com.extvil.extendedvillages.evcore;

import java.util.Calendar;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

import com.extvil.extendedvillages.evcore.handler.ConfigHandler;
import com.extvil.extendedvillages.evvillager.ModelExtendedVillager;
import com.extvil.extendedvillages.evvillager.RenderExtendedVillager;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class ExtendedVillagesClient extends ExtendedVillagesProxy
{
    public static boolean isHalloween;
    public static final ResourceLocation MILLER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Miller.png");
    public static final ResourceLocation FISHER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Fisher.png");
    public static final ResourceLocation BAKER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Baker.png");
    public static final ResourceLocation MINER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Miner.png");
    public static final ResourceLocation SCIENTIST_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/Scientist.png");

    public static final ResourceLocation HMILLER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/HMiller.png");
    public static final ResourceLocation HFISHER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/HFisher.png");
    public static final ResourceLocation HBAKER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/HBaker.png");
    public static final ResourceLocation HMINER_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/HMiner.png");
    public static final ResourceLocation HSCIENTIST_TEXTURE = new ResourceLocation("extvil:textures/entities/villager/HScientist.png");
    
    public void registerRenderInformation()
    {
    	int millvillager = ConfigHandler.MillerID;
    	int fishvillager = ConfigHandler.FisherID;
    	int backeryvillager = ConfigHandler.BakerID;
    	int minervillager = ConfigHandler.MinerID;
    	int scientistvillager = ConfigHandler.ScientistID;
    	
		//Textures with Halloween Check
		Calendar calendar = Calendar.getInstance();

		if((calendar.get(2) + 1 == 10 && calendar.get(5) >= 28 && calendar.get(5) <= 31) || (calendar.get(2) + 1 == 11 && calendar.get(5) >= 1 && calendar.get(5) <= 2))
		{  
			isHalloween = true;
		}
		else
		{
			isHalloween = false;
		}
		if(isHalloween)
		{
			VillagerRegistry.instance().registerVillagerSkin(millvillager, this.HMILLER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(fishvillager, this.HFISHER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(backeryvillager, this.HBAKER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(minervillager, this.HMINER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(minervillager, this.HSCIENTIST_TEXTURE);
		}
		else
		{
			VillagerRegistry.instance().registerVillagerSkin(millvillager, this.MILLER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(fishvillager, this.FISHER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(backeryvillager, this.BAKER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(minervillager, this.MINER_TEXTURE);
			VillagerRegistry.instance().registerVillagerSkin(minervillager, this.SCIENTIST_TEXTURE);
		}

    	if(ConfigHandler.CustomRender)
    	RenderingRegistry.registerEntityRenderingHandler(EntityVillager.class, new RenderExtendedVillager());

    }
}
