package com.extvil.extendedvillages.evcore;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import com.extvil.extendedvillages.evcore.handler.ConfigHandler;

import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class ExtendedVillagesConfigGUI extends GuiConfig 
{
    public ExtendedVillagesConfigGUI(GuiScreen parent) 
    {
    	super(parent, getConfigElements(), "extvil", false, false, "Extended Villages Config");
    }
    
    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyCategoryElement("Villager Type IDs", "Villagers", ExtendedVillagesConfig.class));
        list.add(new DummyCategoryElement("Custom Villager Render", "Core", ExtendedVillagesRenderConfig.class));
        list.add(new DummyCategoryElement("Disable buidlings", "buildings", BuildingsConfig.class));
        return list;
    }
    
    public static class ExtendedVillagesConfig extends CategoryEntry
    {
        public ExtendedVillagesConfig(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
        }
        
        @Override
        protected GuiScreen buildChildScreen()
        {
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's entryList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen, 
                    new ConfigElement(ConfigHandler.config.getCategory("villagers")).getChildElements(), "extvil", false, false, "Extended Villages Config", GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
        }
    }
    
    public static class ExtendedVillagesRenderConfig extends CategoryEntry
    {
        public ExtendedVillagesRenderConfig(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
        }
        
        @Override
        protected GuiScreen buildChildScreen()
        {
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's entryList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen, 
                    new ConfigElement(ConfigHandler.config.getCategory("customrender")).getChildElements(), "extvil", false, false, "Extended Villages Custom Render", GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
        }
    }
    
    public static class BuildingsConfig extends CategoryEntry
    {
        public BuildingsConfig(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop)
        {
            super(owningScreen, owningEntryList, prop);
        }
        
        @Override
        protected GuiScreen buildChildScreen()
        {
            // This GuiConfig object specifies the configID of the object and as such will force-save when it is closed. The parent
            // GuiConfig object's entryList will also be refreshed to reflect the changes.
            return new GuiConfig(this.owningScreen, 
                    new ConfigElement(ConfigHandler.config.getCategory("buildings")).getChildElements(), "extvil", false, false, "Extended Villages Building Settings", GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
        }
    }
}