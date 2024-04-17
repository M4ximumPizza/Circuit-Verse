package org.craftmine.core.level.chunks;

import org.craftmine.core.level.chunks.Chunk;
import org.primal.Constants;
import org.primal.engine.math.Vector3f;
import org.primal.main.Minecraft;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChunkManager {

  public static ChunkManager INSTANCE;
  private static Minecraft mc;
  private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

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
    if (!chunk.HasGenerated && !chunk.IsGenerating) {
      chunk.IsGenerating = true;
      executorService.execute(() -> {
        chunk.Generate();
        chunk.IsGenerating = false;
      });
    }
    return chunk;
  }

  public Chunk[] GenerateVisibleChunks(Chunk[] oldChunks) {
    List<Chunk> chunks = new ArrayList<>();
    RenderDistanceVectors vec = getRenderDistanceAtPlayer(Constants.Chunks.RenderDistance);
    for (int i = 0; i < oldChunks.length; i++) {
      Chunk chunk = oldChunks[i];
      if (ChunkIsWithinBounds(chunk, vec)) {
        if (!chunk.HasGenerated && !chunk.IsGenerating) {
          chunks.add(GenerateChunk(chunk));
        } else {
          chunks.add(chunk);
        }
      }
    }
    return chunks.toArray(new Chunk[0]);
  }

  public Chunk[] RemoveInvisibleChunks(Chunk[] oldChunks) {
    List<Chunk> chunks = new ArrayList<>();
    RenderDistanceVectors vec = getRenderDistanceAtPlayer(Constants.Chunks.RenderDistance);
    for (int i = 0; i < oldChunks.length; i++) {
      Chunk chunk = oldChunks[i];
      if (ChunkIsWithinBounds(chunk, vec)) {
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