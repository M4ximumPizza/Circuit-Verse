package org.primal.level.dimensions;

import org.craftmine.core.level.chunks.Chunk;
import org.primal.engine.math.NoiseGenerator;
import org.primal.engine.math.Vector3f;
import org.primal.level.Dimension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for storing the dimensions. It is responsible
 * for creating and destroying the dimensions.
 *
 * @author MrZombii
 */
public class Overworld implements Dimension {
    private List<Chunk> Chunks = new ArrayList<>();

    public Overworld() {}

    NoiseGenerator noiseGenerator;

    private boolean init = false;
    @Override
    public Dimension Init() {
        init = true;
        noiseGenerator = new NoiseGenerator(391027843);
        Generate();
        return this;
    }

    @Override
    public void Destroy() {
        init = false;
        for (Chunk chunk: Chunks.toArray(Chunks.toArray(new Chunk[0]))) {
            chunk.Destroy();
        }
        Chunks.clear();
    }

    @Override
    public boolean IsInitialized() {
        return init;
    }

    @Override
    public void SetChunks(List<Chunk> Chunks) {
        this.Chunks = Chunks;
    }

    @Override
    public void Generate() {
        // Initial number of chunks
        int numChunks = 50;
        System.out.println("[WARNING]: GENERATING A LARGE QUANTITY OF CHUNKS " + numChunks);
        System.out.println("This chunk generation may take a while. Please be patient. [Zombii's Render distance tester]");
        int sideLength = (int) Math.sqrt(numChunks);
        int centerX = sideLength / 2;
        int centerZ = sideLength / 2;

        NoiseGenerator noiseGen = new NoiseGenerator(LocalDateTime.now().getSecond());
        for (int x = 0; x < sideLength; x++) {
            for (int z = 0; z < sideLength; z++) {
                int offsetX = x - centerX;
                int offsetZ = z - centerZ;
                Chunks.add(new Chunk(new Vector3f(offsetX * 16, 0, offsetZ * 16),  noiseGen));
            }
        }
    }

    @Override
    public Chunk[] GetChunks() {
        return this.Chunks.toArray(new Chunk[0]);
    }

}
