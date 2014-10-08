package com.extvil.extendedvillages.evworldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class WindMillHandler implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {
        return new PieceWeight(ComponentWindmill.class, 3, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return ComponentWindmill.class;
    }

    @Override
    public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int x, int y, int z, int coordBaseMode, int p5) {
        return ComponentWindmill.buildComponent(startPiece, pieces, random, x, y, z, coordBaseMode, p5);
    }

}
