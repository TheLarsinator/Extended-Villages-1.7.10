package com.extvil.extendedvillages.evworldgen.structurehandlers;

import java.util.List;
import java.util.Random;

import com.extvil.extendedvillages.evworldgen.components.ComponentFishHut;
import com.extvil.extendedvillages.evworldgen.components.ComponentMiner;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class MinerHandler implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(ComponentMiner.class, 3, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentMiner.class;
    }

    @Override
    public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        return ComponentMiner.buildComponent(startPiece, pieces, random, x, y, z, coordBaseMode, p5);
    }

}
