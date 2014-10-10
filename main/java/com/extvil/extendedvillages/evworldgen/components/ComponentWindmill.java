package com.extvil.extendedvillages.evworldgen.components;

import java.util.List;
import java.util.Random;

import com.extvil.extendedvillages.evcore.ExtendedVillages;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class ComponentWindmill extends StructureVillagePieces.Village{
	
	private int averageGroundLevel = -1;
	
	public ComponentWindmill()
	{
		
	}
	
    public ComponentWindmill(Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, int coordBaseMode) {
        super();
        this.coordBaseMode = coordBaseMode;
        this.boundingBox = sbb;

    }
    
	public static ComponentWindmill buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, coordBaseMode);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ComponentWindmill(villagePiece, p5, random, box, coordBaseMode) : null;
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
    	
    	if(biome == BiomeGenBase.desert)
    	{
    		walls = Blocks.sandstone;
    		roof = ExtendedVillages.SmoothSand;
    		Deco = Blocks.sandstone;
    		Ground = Blocks.sand;
    		Path = Blocks.sandstone;
    		Floor = Blocks.planks;
    	}
    	else
    	{
    		walls = Blocks.cobblestone;
    		roof = Blocks.planks;
    		Deco = Blocks.log;
    		Ground = Blocks.grass;
    		Floor = Blocks.planks;
    		Path = Blocks.gravel;
    	}
        
        //Clear the area
        fillWithBlocks(world, sbb, 2, 0, 2, 10, 19, 10, Blocks.air, Blocks.air, false);
        
        //Floor
        fillWithBlocks(world, sbb, 2, 0, 2, 10, 0, 10, Floor, Floor, false);
        
        //Grass outside
        fillWithBlocks(world, sbb, 2, 0, 2, 3, 0, 3, Ground, Ground, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 4, 0, 2, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 2, 0, 4, sbb);
        
        fillWithBlocks(world, sbb, 9, 0, 2, 10, 0, 3, Ground, Ground, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 8, 0, 2, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 10, 0, 4, sbb);
        
        fillWithBlocks(world, sbb, 9, 0, 9, 10, 0, 10, Ground, Ground, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 8, 0, 10, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 10, 0, 8, sbb);
        
        fillWithBlocks(world, sbb, 2, 0, 9, 3, 0, 10, Ground, Ground, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 2, 0, 8, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 4, 0, 10, sbb);

        //Molensteen
        fillWithBlocks(world, sbb, 5, 1, 5, 7, 1, 7, walls, walls, false);
        
        //Wall Layer 1+2
        fillWithBlocks(world, sbb, 5, 1, 2, 5, 3, 2, walls, walls, false);
        fillWithBlocks(world, sbb, 7, 1, 2, 7, 3, 2, walls, walls, false);
        

        
        fillWithBlocks(world, sbb, 4, 1, 3, 4, 3, 3, walls, walls, false);
        fillWithBlocks(world, sbb, 8, 1, 3, 8, 3, 3, walls, walls, false);
        
        fillWithBlocks(world, sbb, 3, 1, 4, 3, 3, 4, walls, walls, false);
        fillWithBlocks(world, sbb, 9, 1, 4, 9, 3, 4, walls, walls, false);
        
        fillWithBlocks(world, sbb, 2, 1, 5, 2, 3, 7, walls, walls, false);
        fillWithBlocks(world, sbb, 10, 1, 5, 10, 3, 7, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 10, 2, 6, sbb);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 2, 2, 6, sbb);

        fillWithBlocks(world, sbb, 3, 1, 8, 3, 3, 8, walls, walls, false);
        fillWithBlocks(world, sbb, 9, 1, 8, 9, 3, 8, walls, walls, false);
        
        fillWithBlocks(world, sbb, 4, 1, 9, 4, 3, 9, walls, walls, false);
        fillWithBlocks(world, sbb, 8, 1, 9, 8, 3, 9, walls, walls, false);
        
        fillWithBlocks(world, sbb, 5, 1, 10, 7, 3, 10, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 6, 2, 10, sbb);
        placeBlockAtCurrentPosition(world, walls, 0, 6, 3, 2, sbb);

        //Wooden Roof
        	//Layer 1
        placeBlockAtCurrentPosition(world, roof, 0, 5, 3, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 3, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 3, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 3, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 3, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 10, 3, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 11, 3, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 11, 3, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 11, 3, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 10, 3, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 3, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 3, 10, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 3, 11, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 3, 11, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 3, 11, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 3, 10, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 3, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 3, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 3, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 3, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 3, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 3, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 3, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 3, 2, sbb);
        
        	//Layer 2
        placeBlockAtCurrentPosition(world, roof, 0, 5, 4, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 4, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 4, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 4, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 4, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 10, 4, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 10, 4, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 10, 4, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 4, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 4, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 4, 10, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 4, 10, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 4, 10, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 4, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 4, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 4, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 4, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 4, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 4, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 4, 3, sbb);
        	
        	//Layer3
        placeBlockAtCurrentPosition(world, roof, 0, 5, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 5, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 5, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 5, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 5, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 5, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 5, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 5, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 5, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 5, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 5, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 5, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 5, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 5, 4, sbb);
        
        	//Layer4
        placeBlockAtCurrentPosition(world, roof, 0, 6, 6, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 6, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 6, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 6, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 6, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 6, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 6, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 6, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 6, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 6, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 6, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 6, 4, sbb);

        //Tower
        fillWithBlocks(world, sbb, 6, 7, 4, 6, 13, 4, walls, walls, false);
        fillWithBlocks(world, sbb, 7, 7, 5, 7, 13, 5, walls, walls, false);
        fillWithBlocks(world, sbb, 8, 7, 6, 8, 13, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 7, 7, 7, 7, 13, 7, walls, walls, false);
        fillWithBlocks(world, sbb, 6, 7, 8, 6, 13, 8, walls, walls, false);
        fillWithBlocks(world, sbb, 5, 7, 7, 5, 13, 7, walls, walls, false);
        fillWithBlocks(world, sbb, 4, 7, 6, 4, 13, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 5, 7, 5, 5, 13, 5, walls, walls, false);

        //Fencepole
        fillWithBlocks(world, sbb, 6, 2, 6, 6, 14, 6, Blocks.fence, Blocks.fence, false);

        //Wooden Roof 2
        	
        	//Layer1
        fillWithBlocks(world, sbb, 3, 13, 4, 4, 13, 5, roof, roof, false);
        fillWithBlocks(world, sbb, 8, 13, 4, 9, 13, 5, roof, roof, false);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 13, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 13, 4, sbb);
        
        placeBlockAtCurrentPosition(world, roof, 0, 3, 13, 6, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 13, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 13, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 13, 9, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 7, 13, 8, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 8, 13, 7, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 13, 6, sbb);
        
        	//Layer2
        fillWithBlocks(world, sbb, 4, 14, 4, 4, 14, 6, roof, roof, false);
        fillWithBlocks(world, sbb, 8, 14, 4, 8, 14, 6, roof, roof, false);
        fillWithBlocks(world, sbb, 5, 14, 7, 7, 14, 7, roof, roof, false);
        placeBlockAtCurrentPosition(world, roof, 0, 6, 14, 8, sbb);

        	//Layer3
        fillWithBlocks(world, sbb, 5, 15, 4, 7, 15, 6, roof, roof, false);
        	
        	//Layer4
        fillWithBlocks(world, sbb, 6, 16, 4, 6, 16, 5, roof, roof, false);

        //Wieken As
        fillWithBlocks(world, sbb, 5, 14, 4, 5, 14, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 7, 14, 4, 7, 14, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 6, 14, 2, 6, 14, 5, Blocks.log, Blocks.log, false);

        //Wieken
        	//Wit1
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 2, 10, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 3, 11, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 4, 12, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 5, 13, 2, sbb);
        	//Rood1
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 2, 11, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 3, 12, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 4, 13, 2, sbb);
    		
        	//Wit2
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 10, 10, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 9, 11, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 8, 12, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 7, 13, 2, sbb);
    		//Rood2
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 9, 10, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 8, 11, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 7, 12, 2, sbb);

    		//Wit3
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 10, 18, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 9, 17, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 8, 16, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 7, 15, 2, sbb);
        	//Rood3
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 10, 17, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 9, 16, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 8, 15, 2, sbb);
        
			//Wit4
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 5, 15, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 4, 16, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 3, 17, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 0, 2, 18, 2, sbb);
        	//Rood4
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 4, 17, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 3, 18, 2, sbb);
        placeBlockAtCurrentPosition(world, Blocks.wool, 14, 5, 16, 2, sbb);
        
        placeDoorAtCurrentPosition(world, boundingBox, random, 6, 1, 2, 1);
        placeBlockAtCurrentPosition(world, Path, 0, 6, 0, 0, sbb);
        placeBlockAtCurrentPosition(world, Path, 0, 6, 0, 1, sbb);

        spawnVillagers(world, sbb, 0, 1, 0, 2);

        
        return true;
	}
	
    @Override
    protected int getVillagerType(int par1) {
        return 10;
    }

}
