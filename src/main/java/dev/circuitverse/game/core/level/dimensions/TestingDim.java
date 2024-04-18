package dev.circuitverse.game.core.level.dimensions;

import dev.circuitverse.game.CircuitVerse;
import dev.circuitverse.game.core.level.Dimension;
import dev.circuitverse.game.core.level.chunks.Chunk;
import dev.circuitverse.game.core.engine.math.ENoiseGenerator;
import dev.circuitverse.game.core.engine.math.Vector3f;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestingDim implements Dimension {

    static CircuitVerse mc;
    public TestingDim() {
        mc = CircuitVerse.getInstance();
    }

    private List<Chunk> Chunks = new ArrayList<>();
    private boolean init = false;

    @Override
    public Dimension Init() {
        init = true;
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
        int numChunks = 100000;
        System.out.println("[WARNING]: GENERATING A LARGE QUANTITY OF CHUNKS " + numChunks);
        int sideLength = (int) Math.sqrt(numChunks);
        int centerX = sideLength / 2;
        int centerZ = sideLength / 2;

        ENoiseGenerator noiseGen = new ENoiseGenerator(LocalDateTime.now().getSecond());

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
        return Chunks.toArray(new Chunk[0]);
    }

}
