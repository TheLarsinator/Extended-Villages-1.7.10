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

public class ComponentMiner extends StructureVillagePieces.Village{
	
	private int averageGroundLevel = -1;
	
	public ComponentMiner()
	{
		
	}
	
    public ComponentMiner(Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, int coordBaseMode) {
        super();
        this.coordBaseMode = coordBaseMode;
        this.boundingBox = sbb;

    }
    
	public static ComponentMiner buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, coordBaseMode);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ComponentMiner(villagePiece, p5, random, box, coordBaseMode) : null;
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
    		Deco = Blocks.fence;
    		Ground = Blocks.sand;
    		Path = Blocks.sandstone;
    		Floor = Blocks.planks;
    	}
    	else
    	{
    		walls = Blocks.cobblestone;
    		roof = Blocks.log;
    		Deco = Blocks.fence;
    		Ground = Blocks.grass;
    		Floor = Blocks.planks;
    		Path = Blocks.gravel;
    	}       
                        
        //Beam 1-4
        fillWithBlocks(world, sbb, 1, 1, 1, 1, 14, 1, Deco, Deco, false);
        fillWithBlocks(world, sbb, 5, 1, 1, 5, 14, 1, Deco, Deco, false);
        fillWithBlocks(world, sbb, 5, 1, 5, 5, 14, 5, Deco, Deco, false);
        fillWithBlocks(world, sbb, 1, 1, 5, 1, 14, 5, Deco, Deco, false);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 0, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 0, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 0, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 0, 5, sbb);

        //Sidebars 2-1
        placeBlockAtCurrentPosition(world, roof, 0, 4, 1, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 2, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 3, 1, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 2, 5, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 6, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 7, 1, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 4, 9, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 10, 1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 11, 1, sbb);
        fillWithBlocks(world, sbb, 2, 13, 1, 4, 13, 1, roof, roof, false);
        
        //Sidebars 3-2
        placeBlockAtCurrentPosition(world, roof, 0, 5, 1, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 2, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 3, 2, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 5, 5, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 6, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 7, 4, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 5, 9, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 10, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 5, 11, 2, sbb);
        fillWithBlocks(world, sbb, 5, 13, 2, 5, 13, 4, roof, roof, false);
        
        //Sidebars 4-3
        placeBlockAtCurrentPosition(world, roof, 0, 2, 1, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 2, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 3, 5, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 4, 5, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 6, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 7, 5, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 2, 9, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 10, 5, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 4, 11, 5, sbb);
        fillWithBlocks(world, sbb, 2, 13, 5, 4, 13, 5, roof, roof, false);

        //Sidebars 1-4
        placeBlockAtCurrentPosition(world, roof, 0, 1, 1, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 2, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 3, 4, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 1, 5, 4, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 6, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 7, 2, sbb);

        placeBlockAtCurrentPosition(world, roof, 0, 1, 9, 2, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 10, 3, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 1, 11, 4, sbb);
        fillWithBlocks(world, sbb, 1, 13, 2, 1, 13, 4, roof, roof, false);
        
        //Hole
        int dept = new Random().nextInt(5)+2;
        fillWithBlocks(world, sbb, 2, -dept, 2, 4, 0, 4, Blocks.air, Blocks.air, true);

        //Drill
        fillWithBlocks(world, sbb, 3, 14, 1, 3, 14, 5, Blocks.cobblestone_wall, Blocks.cobblestone_wall, false);
        fillWithBlocks(world, sbb, 1, 14, 3, 5, 14, 3, Blocks.cobblestone_wall, Blocks.cobblestone_wall, false);

        fillWithBlocks(world, sbb, 3, -1, 3, 3, 13, 3, Blocks.cobblestone_wall, Blocks.cobblestone_wall, false);
        
        fillWithBlocks(world, sbb, 3, -1, 2, 3, -1, 4, Blocks.cobblestone_wall, Blocks.cobblestone_wall, false);
        fillWithBlocks(world, sbb, 2, -1, 3, 4, -1, 3, Blocks.cobblestone_wall, Blocks.cobblestone_wall, false);


        spawnVillagers(world, sbb, 0, 1, 0, 2);

        
        return true;
	}
	
    @Override
    protected int getVillagerType(int par1) {
        return 13;
    }

}
