package com.extvil.extendedvillages.evcore;

import net.minecraft.entity.passive.EntityVillager;

import com.extvil.extendedvillages.evcore.handler.ConfigHandler;
import com.extvil.extendedvillages.evvillager.ModelExtendedVillager;
import com.extvil.extendedvillages.evvillager.RenderExtendedVillager;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ExtendedVillagesClient extends ExtendedVillagesProxy
{
    public void registerRenderInformation()
    {
    	if(ConfigHandler.CustomRender)
    	RenderingRegistry.registerEntityRenderingHandler(EntityVillager.class, new RenderExtendedVillager());

    }
}
