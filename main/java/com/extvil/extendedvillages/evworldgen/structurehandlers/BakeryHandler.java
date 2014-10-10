package com.extvil.extendedvillages.evworldgen.structurehandlers;

import java.util.List;
import java.util.Random;

import com.extvil.extendedvillages.evworldgen.components.ComponentBakery;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class BakeryHandler implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(ComponentBakery.class, 3, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentBakery.class;
    }

    @Override
    public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        return ComponentBakery.buildComponent(startPiece, pieces, random, x, y, z, coordBaseMode, p5);
    }

}
