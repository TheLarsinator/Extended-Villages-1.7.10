package com.extvil.extendedvillages.evworldgen.components;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.extvil.extendedvillages.evcore.ExtendedVillages;
import com.extvil.extendedvillages.evcore.handler.ConfigHandler;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class ComponentBakery extends StructureVillagePieces.Village{
	
	private int averageGroundLevel = -1;
	private boolean isHalloween;
    private StructureVillagePieces.Start startPiece;

	public ComponentBakery()
	{
		
	}
	
    public ComponentBakery(Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, int coordBaseMode) {
        super();
        this.coordBaseMode = coordBaseMode;
        this.boundingBox = sbb;

    }
    
	public static ComponentBakery buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, coordBaseMode);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ComponentBakery(villagePiece, p5, random, box, coordBaseMode) : null;
    }
	
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox sbb) {
        if (this.averageGroundLevel < 0) 
        {
            this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);

            if (this.averageGroundLevel < 0)
                return true;

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4, 0);
        }
        int x = this.boundingBox.minX;
        int y = this.boundingBox.minY;
        int z = this.boundingBox.minZ;
        
        Block walls;
        Block roof;
        Block Deco;
        Block Ground;
        Block Path;
        Block Floor;
        
    	BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);
        Calendar calendar = Calendar.getInstance();

        if((calendar.get(2) + 1 == 10 && calendar.get(5) >= 28 && calendar.get(5) <= 31) || (calendar.get(2) + 1 == 11 && calendar.get(5) >= 1 && calendar.get(5) <= 2))
        {  
        	isHalloween = true;
    	}
        else
        {
        	isHalloween = false;
        }
    	if(biome == BiomeGenBase.desert)
    	{
    		walls = Blocks.sandstone;
    		roof = ExtendedVillages.SmoothSand;
    		Deco = Blocks.sandstone;
    		Ground = Blocks.sand;
    		Path = Blocks.sandstone;
    		Floor = Blocks.planks;
    	}
    	else if(isHalloween)
    	{
    		walls = Blocks.stained_hardened_clay;
    		roof = Blocks.netherrack;
    		Deco = Blocks.hardened_clay;
    		Ground = Blocks.soul_sand;
    		Floor = Blocks.nether_brick;
    		Path = Blocks.gravel;
    	}
    	else
    	{
    		walls = Blocks.log;
    		roof = Blocks.cobblestone;
    		Deco = Blocks.log;
    		Ground = Blocks.grass;
    		Floor = Blocks.planks;
    		Path = Blocks.gravel;
    	}
    	
        spawnVillagers(world, sbb, 3, 1, 4, 2);

        //Floor
        fillWithBlocks(world, sbb, 2, 0, 2, 9, 0, 6, Floor, Floor, false);
        


        //front
        fillWithBlocks(world, sbb, 2, 1, 2, 9, 3, 2, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.air, 3, 1, 2, 0, sbb);
        placeBlockAtCurrentPosition(world, Blocks.air, 3, 2, 2, 0, sbb);
        placeDoorAtCurrentPosition(world, boundingBox, random, 3, 1, 2, 0);        
        fillWithBlocks(world, sbb, 5, 2, 2, 7, 2, 2, Blocks.glass, Blocks.glass, false);

        //Side
        fillWithBlocks(world, sbb, 2, 1, 2, 2, 4, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 2, 2, 3, 2, 2, 5, Blocks.glass, Blocks.glass, false);

        //Back
        fillWithBlocks(world, sbb, 3, 1, 6, 9, 3, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 3, 2, 6, 7, 2, 6, Blocks.glass, Blocks.glass, false);

        //Oven
        fillWithBlocks(world, sbb, 8, 1, 3, 9, 4, 5, Blocks.brick_block, Blocks.brick_block, false);
        fillWithBlocks(world, sbb, 9, 5, 4, 9, 7, 4, Blocks.brick_block, Blocks.brick_block, false);
        fillWithBlocks(world, sbb, 10, 1, 4, 10, 3, 4, Blocks.brick_block, Blocks.brick_block, false);
        fillWithBlocks(world, sbb, 8, 4, 3, 8, 4, 5, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.air, 0, 8, 2, 4, sbb);
        placeBlockAtCurrentPosition(world, Blocks.furnace, 0, 9, 2, 4, sbb);

        
        //Interior
        placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 0, 4, 1, 4, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wooden_slab, 0, 4, 1, 5, sbb);
        
        fillWithBlocks(world, sbb, 5, 1, 4, 5, 1, 5, Blocks.fence, Blocks.fence, false);
        fillWithBlocks(world, sbb, 5, 2, 4, 5, 2, 5, Blocks.wooden_pressure_plate, Blocks.wooden_pressure_plate, false);
        fillWithBlocks(world, sbb, 5, 2, 6, 5, 2, 6, walls, walls, true);
        
        //Roof
        fillWithBlocks(world, sbb, 1, 3, 1, 10, 3, 1, roof, roof, false);
        fillWithBlocks(world, sbb, 1, 4, 2, 10, 4, 2, roof, roof, false);
        fillWithBlocks(world, sbb, 1, 5, 3, 10, 5, 5, roof, roof, false);
        fillWithBlocks(world, sbb, 1, 4, 6, 10, 4, 6, roof, roof, false);
        fillWithBlocks(world, sbb, 1, 3, 7, 10, 3, 7, roof, roof, false);
        
        
        return true;    	
	}
    protected int func_151557_c(Block p_151557_1_, int p_151557_2_)
    {
        BiomeEvent.GetVillageBlockMeta event = new BiomeEvent.GetVillageBlockMeta(startPiece == null ? null : startPiece.biome, p_151557_1_, p_151557_2_);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.replacement;
        if (this.isHalloween)
        {
            if (p_151557_1_ == Blocks.stained_hardened_clay)
            {
                return 1;
            }
        }

        return p_151557_2_;
    }
    @Override
    protected int getVillagerType(int par1) {
        return ConfigHandler.BakerID;
    }
}