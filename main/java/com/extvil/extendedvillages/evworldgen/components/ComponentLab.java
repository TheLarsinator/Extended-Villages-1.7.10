package com.extvil.extendedvillages.evworldgen.components;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.extvil.extendedvillages.evcore.ExtendedVillages;
import com.extvil.extendedvillages.evcore.handler.ConfigHandler;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class ComponentLab extends StructureVillagePieces.Village{
	
	private int averageGroundLevel = -1;
	private boolean isHalloween;
    private boolean ChestPlaced;
    
    public static final String LabContent       = "labChest";
	public static final WeightedRandomChestContent[] LabChestContents = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.water_bucket, 0, 1, 1, 1), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.potionitem, 0, 1, 3, 15), new WeightedRandomChestContent(Items.fermented_spider_eye, 0, 1, 3, 15), new WeightedRandomChestContent(Items.glass_bottle, 0, 1, 1, 5), new WeightedRandomChestContent(Items.glowstone_dust, 0, 1, 1, 5), new WeightedRandomChestContent(Items.redstone, 0, 1, 1, 5), new WeightedRandomChestContent(Items.nether_wart, 0, 1, 1, 5), new WeightedRandomChestContent(Items.ghast_tear, 0, 1, 1, 1), new WeightedRandomChestContent(Items.gunpowder, 0, 1, 1, 5)};
    private static final HashMap<String, ChestGenHooks> chestInfo = new HashMap<String, ChestGenHooks>();

	
    private StructureVillagePieces.Start startPiece;

	public ComponentLab()
	{
		
	}
	
    public ComponentLab(Start villagePiece, int par2, Random par3Random, StructureBoundingBox sbb, int coordBaseMode) {
        super();
        this.coordBaseMode = coordBaseMode;
        this.boundingBox = sbb;

    }
    
	public static ComponentLab buildComponent(Start villagePiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 11, 6, 11, coordBaseMode);
        return canVillageGoDeeper(box) && StructureComponent.findIntersecting(pieces, box) == null ? new ComponentLab(villagePiece, p5, random, box, coordBaseMode) : null;
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
    		roof = Blocks.stone_slab;
    		Deco = Blocks.bookshelf;
    		Ground = Blocks.sand;
    		Path = Blocks.sandstone;
    		Floor = Blocks.stained_hardened_clay;
    	}
    	else if(isHalloween)
    	{
    		walls = Blocks.quartz_block;
    		roof = Blocks.stone_slab;
    		Deco = Blocks.tnt;
    		Ground = Blocks.soul_sand;
    		Floor = Blocks.nether_brick;
    		Path = Blocks.gravel;
    	}
    	else if(ExtendedVillages.isChristmas)
    	{
    		walls = Blocks.snow;
    		roof = Blocks.stone_slab;
    		Deco = Blocks.leaves;
    		Ground = Blocks.snow;
    		Floor = Blocks.planks;
    		Path = Blocks.gravel;
    	}
    	else
    	{
    		walls = Blocks.brick_block;
    		roof = Blocks.stone_slab;
    		Deco = Blocks.bookshelf;
    		Ground = Blocks.grass;
    		Floor = Blocks.stained_hardened_clay;
    		Path = Blocks.gravel;
    	}       

        //Front
        fillWithBlocks(world, sbb, 4, 1, 2-1, 8, 4, 2-1, walls, walls, false);
        fillWithBlocks(world, sbb, 6, 1, 2-1, 6, 2, 2-1, Blocks.air, Blocks.air, false);
        placeDoorAtCurrentPosition(world, boundingBox, random, 6, 1, 2-1, 0);      
        fillWithBlocks(world, sbb, 4, 5, 2-1, 8, 5, 2-1, roof, roof, false);
        placeBlockAtCurrentPosition(world, Path, 0, 6, 0, 1-1, sbb);
        placeBlockAtCurrentPosition(world, Path, 0, 6, 0, 0-1, sbb);
        placeBlockAtCurrentPosition(world, walls, 0, 6, 0, 2-1, sbb);

        //Row 2
        fillWithBlocks(world, sbb, 3, 1, 3-1, 9, 4, 3-1, walls, walls, false);
        fillWithBlocks(world, sbb, 4, 1, 3-1, 8, 3, 3-1, Blocks.air, Blocks.air, false);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 3, 2, 3-1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.glass, 0, 9, 2, 3-1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 3, 5, 3-1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 9, 5, 3-1, sbb);

        //Row 3
        fillWithBlocks(world, sbb, 2, 1, 4-1, 10, 4, 4-1, walls, walls, false);
        fillWithBlocks(world, sbb, 3, 1, 4-1, 9, 3, 4-1, Blocks.air, Blocks.air, false);
        placeBlockAtCurrentPosition(world, roof, 0, 2, 5, 4-1, sbb);
        placeBlockAtCurrentPosition(world, roof, 0, 10, 5, 4-1, sbb);
        
        //Right Side
        fillWithBlocks(world, sbb, 1, 1, 5-1, 1, 4, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 1, 2, 6-1, 1, 2, 9-1, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 1, 5, 5-1, 1, 5, 10-1, roof, roof, false);
        
        //Left Side
        fillWithBlocks(world, sbb, 11, 1, 5-1, 11, 4, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 11, 2, 8-1, 11, 2, 9-1, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 11, 5, 5-1, 11, 5, 10-1, roof, roof, false);
        
        //Back side
        fillWithBlocks(world, sbb, 2, 1, 11-1, 10, 4, 11-1, walls, walls, false);
        fillWithBlocks(world, sbb, 4, 2, 11-1, 5, 2, 11-1, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 8, 2, 11-1, 9, 2, 11-1, Blocks.glass, Blocks.glass, false);
        fillWithBlocks(world, sbb, 2, 5, 11-1, 10, 5, 11-1, roof, roof, false); 
        
        //Bookshelf
        fillWithBlocks(world, sbb, 10, 1, 5-1, 10, 3, 6-1, Deco, Deco, false);
        
        //Inside room
        fillWithBlocks(world, sbb, 7, 1, 7-1, 10, 3, 7-1, walls, walls, false);
        placeDoorAtCurrentPosition(world, boundingBox, random, 7, 1, 8-1, 0);      
        fillWithBlocks(world, sbb, 7, 1, 9-1, 7, 3, 10-1, walls, walls, false);
        placeBlockAtCurrentPosition(world, walls, 0, 7, 3, 8-1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.bed, 0, 9, 1, 9-1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.bed, 6, 9, 1, 10-1, sbb);

        //Redstone
        fillWithBlocks(world, sbb, 2, 4, 5-1, 10, 4, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 6, 4, 5-1, 6, 4, 8-1, Blocks.redstone_lamp, Blocks.redstone_lamp, false);
        fillWithBlocks(world, sbb, 4, 5, 5-1, 4, 5, 8-1, Blocks.daylight_detector, Blocks.daylight_detector, false);
        fillWithBlocks(world, sbb, 8, 5, 5-1, 8, 5, 8-1, Blocks.daylight_detector, Blocks.daylight_detector, false);

        fillWithBlocks(world, sbb, 9, 5, 5-1, 9, 5, 10-1, Blocks.redstone_wire, Blocks.redstone_wire, false);
        fillWithBlocks(world, sbb, 3, 5, 5-1, 3, 5, 10-1, Blocks.redstone_wire, Blocks.redstone_wire, false);
        fillWithBlocks(world, sbb, 6, 5, 5-1, 6, 5, 8-1, Blocks.redstone_wire, Blocks.redstone_wire, false);
        fillWithBlocks(world, sbb, 4, 5, 10-1, 5, 5, 10-1, Blocks.redstone_wire, Blocks.redstone_wire, false);
        fillWithBlocks(world, sbb, 7, 5, 10-1, 8, 5, 10-1, Blocks.redstone_wire, Blocks.redstone_wire, false);

        fillWithBlocks(world, sbb, 6, 5, 9-1, 6, 5, 10-1, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.redstone_wire, 0, 6, 6, 9-1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.redstone_torch, 0, 6, 6, 10-1, sbb);

        //Decorations
        placeChest(world, 2, 1, 5-1, 3, random, sbb);
        fillWithBlocks(world, sbb, 2, 1, 6-1, 2, 1, 9-1, walls, walls, false);
        placeBlockAtCurrentPosition(world, Blocks.brewing_stand, 0, 2, 2, 6-1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.brewing_stand, 0, 2, 2, 9-1, sbb);
        placeBlockAtCurrentPosition(world, Blocks.crafting_table, 0, 2, 1, 10-1, sbb);

        //Floor
        fillWithBlocks(world, sbb, 2, 0, 5-1, 2, 0, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 4, 0, 3-1, 4, 0, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 6, 0, 3-1, 6, 0, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 8, 0, 3-1, 8, 0, 10-1, walls, walls, false);
        fillWithBlocks(world, sbb, 10, 0, 5-1, 10, 0, 10-1, walls, walls, false);
        
        fillWithBlocks(world, sbb, 3, 0, 4-1, 3, 0, 10-1, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, false);
        fillWithBlocks(world, sbb, 5, 0, 3-1, 5, 0, 10-1, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, false);
        fillWithBlocks(world, sbb, 7, 0, 3-1, 7, 0, 10-1, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, false);
        fillWithBlocks(world, sbb, 9, 0, 4-1, 9, 0, 10-1, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, false);
        
        spawnVillagers(world, sbb, 0, 1, 0, 2);


        
        return true;
	}
    protected int func_151557_c(Block p_151557_1_, int p_151557_2_)
    {
        BiomeEvent.GetVillageBlockMeta event = new BiomeEvent.GetVillageBlockMeta(startPiece == null ? null : startPiece.biome, p_151557_1_, p_151557_2_);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == Result.DENY) return event.replacement;

            if (p_151557_1_ == Blocks.stained_hardened_clay)
            {
                return 14;
            }
        

        return p_151557_2_;
    }
    @Override
    protected int getVillagerType(int par1) {
        return ConfigHandler.ScientistID;
    }
    
    @Override
    protected void func_143012_a(NBTTagCompound nbt) {
        super.func_143012_a(nbt);
        nbt.setBoolean("Chest", ChestPlaced);
    }

    @Override
    protected void func_143011_b(NBTTagCompound nbt) {
        super.func_143011_b(nbt);
        ChestPlaced = nbt.getBoolean("Chest");
    }
    private static void addInfo(String category, WeightedRandomChestContent[] items, int min, int max)
    {
        chestInfo.put(category, new ChestGenHooks(category, items, min, max));
    }
    private void placeChest(World world, int x, int y, int z, int meta, Random rand, StructureBoundingBox sbb) {
        

        addInfo(LabContent, this.LabChestContents,   3,  7);

    	
    	int xx = this.getXWithOffset(x, z);
        int yy = this.getYWithOffset(y);
        int zz = this.getZWithOffset(x, z);

        if (!ChestPlaced && sbb.isVecInside(xx, yy, zz)) {
        	ChestPlaced = true;
            if (world.getBlock(xx, yy, zz) != Blocks.chest) {
                world.setBlock(xx, yy, zz, Blocks.chest, getMetadataWithOffset(Blocks.chest, meta), 2);
                TileEntityChest chest = (TileEntityChest) world.getTileEntity(xx, yy, zz);

                if (chest != null)
                    WeightedRandomChestContent.generateChestContents(rand, LabChestContents, chest, 10);
            }
        }
    }

}
