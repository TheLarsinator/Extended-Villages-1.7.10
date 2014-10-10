package com.extvil.extendedvillages.evworldgen.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.extvil.extendedvillages.evcore.ExtendedVillages;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFakeSandStone extends Block
{
public BlockFakeSandStone(int par1, int par2)
{
         super(Material.rock);
         this.setCreativeTab(null);
}

public Item getItemDropped(int par1, Random par2Random, int par3)
{
	if(this == ExtendedVillages.SmoothSand)
	{
         return Item.getItemFromBlock(Blocks.sandstone);
	}
	else if(this == ExtendedVillages.DecoSand)
	{
        return Item.getItemFromBlock(Blocks.sandstone);
	}
	else
	{
		return null;
	}
}
/**
 * Determines the damage on the item the block drops. Used in cloth and wood.
 */
public int damageDropped(int p_149692_1_)
{
	if(this == ExtendedVillages.SmoothSand)
	{
         return 2;
	}
	else if(this == ExtendedVillages.DecoSand)
	{
        return 1;
	}
	else
	{
		return 0;
	}
}
@SideOnly(Side.CLIENT)
public IIcon getIcon(int p_149691_1_, int p_149691_2_)
{
    return p_149691_1_ != 1 && p_149691_1_ != 0 ? this.blockIcon : this.theIcon;
}
private IIcon theIcon;

@SideOnly(Side.CLIENT)
public void registerBlockIcons(IIconRegister p_149651_1_)
{
	if(this == ExtendedVillages.DecoSand)
    this.blockIcon = p_149651_1_.registerIcon("sandstone_carved");
	else if(this == ExtendedVillages.SmoothSand)
    this.blockIcon = p_149651_1_.registerIcon("sandstone_smooth");
    
	
	this.theIcon = p_149651_1_.registerIcon("sandstone_top");
}
}
