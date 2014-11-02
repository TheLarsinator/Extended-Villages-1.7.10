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

public class ComponentFishHut extends StructureVillagePieces.Village{
	
	private int averageGroundLevel = -1;
	private boolean isHalloween;
    private StructureVillagePieces.Start startPiece;

	public ComponentFishHut()
	{
		
	}
	
    public ComponentFishHut(Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, int coordBaseMode) {
        super();
        this.coordBaseMode = coordBaseMode;
        this.boundingBox = sbb;

    }
    
	public static ComponentFishHut buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, coordBaseMode);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ComponentFishHut(villagePiece, p5, random, box, coordBaseMode) : null;
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
        spawnVillagers(world, sbb, 0, 1, 0, 2);
        
        
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
    		Deco = ExtendedVillages.DecoSand;
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
    		walls = Blocks.cobblestone;
    		roof = Blocks.planks;
    		Deco = Blocks.log;
    		Ground = Blocks.grass;
    		Floor = Blocks.planks;

    	}
    	
        //Water
        fillWithBlocks(world, sbb, 1, 0, 5, 5, 0, 12, Blocks.water, Blocks.water, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 1, 0, 5, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 1, 0, 6, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 1, 0, 12, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 3, 0, 7, sbb);
        fillWithBlocks(world, sbb, 6, 0, 6, 6, 0, 14, Blocks.water, Blocks.water, false);
        fillWithBlocks(world, sbb, 7, 0, 8, 12, 0, 15, Blocks.water, Blocks.water, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 7, 0, 14, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 8, 0, 14, sbb);
        fillWithBlocks(world, sbb, 8, 0, 8, 12, 0, 8, Ground, Ground, false);
        fillWithBlocks(world, sbb, 9, 0, 9, 10, 0, 9, Ground, Ground, false);
        fillWithBlocks(world, sbb, 9, 1, 9, 10, 2, 9, Blocks.reeds, Blocks.reeds, false);
        placeBlockAtCurrentPosition(world, Ground, 0, 11, 0, 14, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 11, 0, 13, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 12, 0, 14, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 12, 0, 13, sbb);
        placeBlockAtCurrentPosition(world, Ground, 0, 12, 0, 12, sbb);

        //Stairs
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 10, 1, 1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 9, 2, 1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 8, 3, 1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 7, 4, 1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 4, 1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 4, 2, sbb);

        placeBlockAtCurrentPosition(world, Deco, 0, 9, 1, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 8, 1, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 7, 1, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 6, 1, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 8, 2, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 7, 2, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 6, 2, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 7, 3, 1, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 6, 3, 1, sbb);
        
        placeBlockAtCurrentPosition(world, Floor, 0, 9, 1, 2, sbb);
        placeBlockAtCurrentPosition(world, Floor, 0, 8, 2, 2, sbb);
        placeBlockAtCurrentPosition(world, Floor, 0, 7, 3, 2, sbb);
        fillWithBlocks(world, sbb, 6, 1, 2, 6, 3, 2, Deco, Deco, false);
        placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 4, 2, sbb);

        //Inside Floor (Done first to prevent ugly corners)
        fillWithBlocks(world, sbb, 3, 3, 3, 10, 3, 7, Floor, Floor, false);
        
        //Front
        fillWithBlocks(world, sbb, 10, 1, 3, 10, 6, 3, Deco, Deco, false);
        fillWithBlocks(world, sbb, 4, 2, 3, 9, 2, 3, Blocks.fence, Blocks.fence, false);
        fillWithBlocks(world, sbb, 3, 1, 3, 3, 6, 3, Deco, Deco, false);
        fillWithBlocks(world, sbb, 4, 4, 3, 10, 5, 3, Deco, Deco, false);
        fillWithBlocks(world, sbb, 4, 6, 3, 9, 6, 3, Floor, Floor, false);
        placeBlockAtCurrentPosition(world, Deco, 0, 6, 6, 3, sbb);
        placeBlockAtCurrentPosition(world, Deco, 0, 8, 6, 3, sbb);

        
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 4, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 5, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 9, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, Blocks.air, 0, 7, 5, 3, sbb);
        placeBlockAtCurrentPosition(world, Blocks.air, 0, 7, 4, 3, sbb);
        
        //Roof
        fillWithBlocks(world, sbb, 2, 6, 2, 11, 6, 2, roof, roof, false);
        fillWithBlocks(world, sbb, 2, 7, 3, 11, 7, 3, roof, roof, false);
        fillWithBlocks(world, sbb, 2, 8, 4, 11, 8, 4, roof, roof, false);
        fillWithBlocks(world, sbb, 2, 9, 5, 11, 9, 5, roof, roof, false);
        fillWithBlocks(world, sbb, 2, 8, 6, 11, 8, 6, roof, roof, false);
        fillWithBlocks(world, sbb, 2, 7, 7, 11, 7, 7, roof, roof, false);
        fillWithBlocks(world, sbb, 2, 6, 8, 11, 6, 8, roof, roof, false);

        //Sides
        fillWithBlocks(world, sbb, 3, 2, 4, 3, 2, 6, Blocks.fence, Blocks.fence, false);
        fillWithBlocks(world, sbb, 3, 4, 4, 3, 4, 6, Deco, Deco, false);
        fillWithBlocks(world, sbb, 3, 5, 4, 3, 5, 6, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 3, 6, 4, 3, 6, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 3, 7, 4, 3, 7, 6, walls, walls, false);
        placeBlockAtCurrentPosition(world, walls, 0, 3, 8, 5, sbb);
        
        fillWithBlocks(world, sbb, 10, 2, 4, 10, 2, 6, Blocks.fence, Blocks.fence, false);
        fillWithBlocks(world, sbb, 10, 4, 4, 10, 4, 6, Deco, Deco, false);
        fillWithBlocks(world, sbb, 10, 5, 4, 10, 5, 6, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 10, 6, 4, 10, 6, 6, walls, walls, false);
        fillWithBlocks(world, sbb, 10, 7, 4, 10, 7, 6, walls, walls, false);
        placeBlockAtCurrentPosition(world, walls, 0, 10, 8, 5, sbb);

        //Back
        fillWithBlocks(world, sbb, 3, 1, 7, 3, 6, 7, Deco, Deco, false);
        fillWithBlocks(world, sbb, 10, 1, 7, 10, 6, 7, Deco, Deco, false);
        fillWithBlocks(world, sbb, 4, 2, 7, 9, 2, 7, Blocks.fence, Blocks.fence, false);
        fillWithBlocks(world, sbb, 4, 4, 7, 9, 4, 7, Deco, Deco, false);
        fillWithBlocks(world, sbb, 4, 5, 7, 9, 5, 7, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 4, 6, 7, 9, 6, 7, Floor, Floor, false);

        placeDoorAtCurrentPosition(world, boundingBox, random, 7, 4, 3, 0);        
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
        return ConfigHandler.FisherID;
    }
}
