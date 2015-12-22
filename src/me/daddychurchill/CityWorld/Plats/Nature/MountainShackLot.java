package me.daddychurchill.CityWorld.Plats.Nature;

import org.bukkit.generator.ChunkGenerator.BiomeGrid;

import me.daddychurchill.CityWorld.CityWorldGenerator;
import me.daddychurchill.CityWorld.Context.DataContext;
import me.daddychurchill.CityWorld.Plats.PlatLot;
import me.daddychurchill.CityWorld.Support.InitialBlocks;
import me.daddychurchill.CityWorld.Support.PlatMap;
import me.daddychurchill.CityWorld.Support.RealBlocks;

public class MountainShackLot extends MountainFlatLot {

	public MountainShackLot(PlatMap platmap, int chunkX, int chunkZ) {
		super(platmap, chunkX, chunkZ);
		
	}
	
	@Override
	public PlatLot newLike(PlatMap platmap, int chunkX, int chunkZ) {
		return new MountainShackLot(platmap, chunkX, chunkZ);
	}

	@Override
	protected void generateActualChunk(CityWorldGenerator generator,
			PlatMap platmap, InitialBlocks chunk, BiomeGrid biomes,
			DataContext context, int platX, int platZ) {
		
		// empty it out and add the retainer wall, as needed
		generateRetainerLot(generator, chunk, context);
	}

	@Override
	protected void generateActualBlocks(CityWorldGenerator generator, PlatMap platmap, RealBlocks chunk, DataContext context, int platX, int platZ) {
		reportLocation(generator, "Shack", chunk.getOriginX(), chunk.getOriginZ());

		// now make a shack
		int floors = generator.houseProvider.generateShack(generator, chunk, context, chunkOdds, 
				blockYs.averageHeight + 1, 5);
		
		// not a happy place?
		if (generator.settings.includeDecayedBuildings)
			destroyBuilding(generator, blockYs.averageHeight + 1, floors);
	}
}
