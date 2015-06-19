package com.extvil.extendedvillages.evvillager;

import java.util.Calendar;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.extvil.extendedvillages.evcore.ExtendedVillages;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderExtendedVillager extends RenderLiving
{
    private static final ResourceLocation villagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/villager.png");
    private static final ResourceLocation farmerVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/farmer.png");
    private static final ResourceLocation librarianVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/librarian.png");
    private static final ResourceLocation priestVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/priest.png");
    private static final ResourceLocation smithVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/smith.png");
    private static final ResourceLocation butcherVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/butcher.png");
    
    private static final ResourceLocation HvillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Hvillager.png");
    private static final ResourceLocation HfarmerVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Hfarmer.png");
    private static final ResourceLocation HlibrarianVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Hlibrarian.png");
    private static final ResourceLocation HpriestVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Hpriest.png");
    private static final ResourceLocation HsmithVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Hsmith.png");
    private static final ResourceLocation HbutcherVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Hbutcher.png");
    
    private static final ResourceLocation CvillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Cvillager.png");
    private static final ResourceLocation CfarmerVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Cfarmer.png");
    private static final ResourceLocation ClibrarianVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Clibrarian.png");
    private static final ResourceLocation CpriestVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Cpriest.png");
    private static final ResourceLocation CsmithVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Csmith.png");
    private static final ResourceLocation CbutcherVillagerTextures = new ResourceLocation("extvil:"+"textures/entities/villager/Cbutcher.png");
    
    /** Model of the villager. */
    protected ModelExtendedVillager villagerModel;
    private static final String __OBFID = "CL_00001032";

    public RenderExtendedVillager()
    {
        super(new ModelExtendedVillager(0.0F), 0.5F);
        this.villagerModel = (ModelExtendedVillager)this.mainModel;

    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityVillager p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return -1;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityVillager p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityVillager p_110775_1_)
    {
        if(ExtendedVillages.isHalloween)
        {
        switch (p_110775_1_.getProfession())
        {
            case 0:
                return HfarmerVillagerTextures;
            case 1:
                return HlibrarianVillagerTextures;
            case 2:
                return HpriestVillagerTextures;
            case 3:
                return HsmithVillagerTextures;
            case 4:
                return HbutcherVillagerTextures;
            default:
                return VillagerRegistry.getVillagerSkin(p_110775_1_.getProfession(), HvillagerTextures);
        }
        }
        else if(ExtendedVillages.isChristmas)
        {
            switch (p_110775_1_.getProfession())
            {
                case 0:
                    return CfarmerVillagerTextures;
                case 1:
                    return ClibrarianVillagerTextures;
                case 2:
                    return CpriestVillagerTextures;
                case 3:
                    return CsmithVillagerTextures;
                case 4:
                    return CbutcherVillagerTextures;
                default:
                    return VillagerRegistry.getVillagerSkin(p_110775_1_.getProfession(), CvillagerTextures);
            }
        }
        else
        {
            switch (p_110775_1_.getProfession())
            {
                case 0:
                    return farmerVillagerTextures;
                case 1:
                    return librarianVillagerTextures;
                case 2:
                    return priestVillagerTextures;
                case 3:
                    return smithVillagerTextures;
                case 4:
                    return butcherVillagerTextures;
                default:
                    return VillagerRegistry.getVillagerSkin(p_110775_1_.getProfession(), villagerTextures);
            }
        }
    }

    protected void renderEquippedItems(EntityVillager p_77029_1_, float p_77029_2_)
    {
        super.renderEquippedItems(p_77029_1_, p_77029_2_);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityVillager p_77041_1_, float p_77041_2_)
    {
        float f1 = 0.9375F;

        if (p_77041_1_.getGrowingAge() < 0)
        {
            f1 = (float)((double)f1 * 0.5D);
            this.shadowSize = 0.25F;
        }
        else
        {
            this.shadowSize = 0.5F;
        }

        GL11.glScalef(f1, f1, f1);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityVillager)p_77041_1_, p_77041_2_);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityVillager)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.renderEquippedItems((EntityVillager)p_77029_1_, p_77029_2_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityVillager)p_110775_1_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityVillager)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}