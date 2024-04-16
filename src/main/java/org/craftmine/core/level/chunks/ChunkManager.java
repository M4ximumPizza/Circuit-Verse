package org.craftmine.core.level.chunks;


import org.primal.Constants;
import org.primal.engine.math.Vector3f;
import org.primal.main.Minecraft;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("removal")
public class ChunkManager {

  public static ChunkManager INSTANCE;
  private static Minecraft mc;

  public ChunkManager() {
    INSTANCE = this;
    mc = Minecraft.getInstance();
  }

  private static class Chunker implements Runnable {

    public Chunk chunk;

    public Chunker(Chunk chunk) {
      this.chunk = chunk;
    }

    @Override
    public void run() {
      chunk = chunk.Generate();
    }
  }

  public Chunk GenerateChunk(Chunk chunk) {
    Chunker ch = new Chunker(chunk);
    chunk.IsGenerating = true;
    if (!chunk.HasGenerated) {
      new Thread(ch).start();
    };
    return ch.chunk;
  }

  public Chunk[] RemoveInvisibleChunks(Chunk[] oldChunks) {
    List<Chunk> chunks = new ArrayList<>();
    RenderDistanceVectors vec = getRenderDistanceAtPlayer(Constants.Chunks.RenderDistance);
    for (int i = 0; i < oldChunks.length; i++) {
      Chunk chunk = oldChunks[i];
      if (ChunkIsWithinBounds(chunk, vec)) {
//        if (!chunk.HasGenerated) chunk = chunk.FinishGenerating();
        chunks.add(chunk);
      }
    }
    return chunks.toArray(new Chunk[0]);
  }

  public static RenderDistanceVectors getRenderDistanceAtPlayer(int renderDistance) {
    return new RenderDistanceVectors(
            new Vector3f(
                    mc.camera.getPosition().getX() + (renderDistance * ((float) Constants.Chunks.MaxWidth / 2)),
                    mc.camera.getPosition().getY() + (renderDistance * ((float) Constants.Chunks.MaxWidth / 2)),
                    mc.camera.getPosition().getZ() + (renderDistance * ((float) Constants.Chunks.MaxWidth / 2))
            ),
            new Vector3f(
                    mc.camera.getPosition().getX() + (-renderDistance * ((float) Constants.Chunks.MaxWidth / 2)),
                    mc.camera.getPosition().getY() + (-renderDistance * ((float) Constants.Chunks.MaxWidth / 2)),
                    mc.camera.getPosition().getZ() + (-renderDistance * ((float) Constants.Chunks.MaxWidth / 2))
            )
    );
  }

  public static class RenderDistanceVectors {
    public Vector3f pos, neg;

    public RenderDistanceVectors(Vector3f pos, Vector3f neg) {
      this.pos = pos;
      this.neg = neg;
    }
  }

  public static boolean ChunkIsWithinBounds(Chunk chunk, ChunkManager.RenderDistanceVectors bounds) {
    return chunk.position.getX() <= bounds.pos.getX() && chunk.position.getX() >= bounds.neg.getX() &&
            chunk.position.getZ() <= bounds.pos.getZ() && chunk.position.getZ() >= bounds.neg.getZ();
  }

}
