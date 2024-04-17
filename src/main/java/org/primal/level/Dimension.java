package org.primal.level;

import org.craftmine.core.level.chunks.Chunk;
import org.craftmine.core.level.chunks.ChunkManager;

import java.util.List;

/**
 * This is the interface for the dimensions. It is responsible for creating and destroying the dimensions.
 *
 * @author MrZombii
 */
public interface Dimension {
    
    public Dimension Init() throws Exception;
    public void SetChunks(List<Chunk> Chunks);
    public void Generate() throws Exception;
    public Chunk[] GetChunks();
    public void Destroy();
    public boolean IsInitialized();

}
