package com.extvil.extendedvillages.evworldgen.structurehandlers;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

import com.extvil.extendedvillages.evworldgen.components.ComponentLab;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class LabHandler implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(ComponentLab.class, 3, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentLab.class;
    }

    @Override
    public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        return ComponentLab.buildComponent(startPiece, pieces, random, x, y, z, coordBaseMode, p5);
    }

}
