package org.core.level.chunks;

import org.core.level.blocks.Block;
import org.core.level.blocks.Blocks;
import org.core.level.blocks.Block.BlockType;
import org.core.level.blocks.Block.Faces;
import org.graphics.BlockModel.CombinedFaceData;
import org.engine.math.NoiseGenerator;
import org.engine.math.Vector2f;
import org.engine.math.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import org.utils.Constants;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the base class for all chunks in the game. It is responsible for
 * generating the blocks in the chunk, generating the faces of the blocks, and
 * building the chunk.
 *
 * @author MrZombii
 * @author Logan Abernathy
 */
public class Chunk {
    private final List<Vector3f> chunkVerts = new ArrayList<>();
    private final List<Vector2f> chunkUVs = new ArrayList<>();
    private final List<Integer> chunkIndices = new ArrayList<>();

    private static final int SIZE = Constants.Chunks.MaxWidth;
    private static final int HEIGHT = Constants.Chunks.MaxHeight;
    public Vector3f position;

    private int indexCount;
    private Block[][][] blocks;

    public boolean HasGenerated, IsGenerating, HasMesh = false;

    NoiseGenerator noiseGenerator;
    float[][] HeightMap;

    public Chunk(Vector3f position, NoiseGenerator noiseGenerator) {
        this.position = position;
        this.noiseGenerator = noiseGenerator;
    }

    public Chunk(Vector3f position) throws Exception {
        this.position = position;
        GenerateBlocks();
        GenerateFaces();
        BuildChunk();
    }

    public Chunk Generate() {
        IsGenerating = true;
        GenerateBlocks();
        GenerateFaces();
        HasGenerated = true;
        IsGenerating = false;
        return this;
    }

    public void BuildHeightMap() {
        HeightMap = new float[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int z = 0; z < SIZE; z++) {
                HeightMap[x][z] = (float) noiseGenerator.noise(x, z)*15;
            }
        }
    }

    public void GenerateBlocks() {
        blocks = new Block[SIZE][HEIGHT][SIZE];

        for (int x = 0; x < SIZE; x++) {
            for (int z = 0; z < SIZE; z++) {
                int maxHeight = (int)((float) (noiseGenerator.noise(position.getX() + x, position.getZ() + z)+1) * 22);
                maxHeight = Math.max(maxHeight, 1);

                for (int y = 0; y < HEIGHT; y++) {
                    BlockType block;
                    block = Blocks.GRASS;

                    blocks[x][y][z] = block.New(new Vector3f(x, y, z));
                }
            }
        }
    }

    public Chunk GenerateFaces() {
        for (int x = 0; x < SIZE; x++) {
            for (int z = 0; z < SIZE; z++) {
                for (int y = 0; y < HEIGHT; y++) {
                    int drawnFaces = 0;
                    Block block = blocks[x][y][z];

                    if (!block.blockType.equals(Blocks.AIR)) {
                        // Check Left face
                        if (x == 0 || blocks[x - 1][y][z].blockType.equals(Blocks.AIR)) {
                            AddFace(block, Faces.Left);
                            drawnFaces++;
                        }

                        // Check Right face
                        if (x == SIZE - 1 || blocks[x + 1][y][z].blockType.equals(Blocks.AIR)) {
                            AddFace(block, Faces.Right);
                            drawnFaces++;
                        }

                        // Check Back face
                        if (z == 0 || blocks[x][y][z - 1].blockType.equals(Blocks.AIR)) {
                            AddFace(block, Faces.Back);
                            drawnFaces++;
                        }

                        // Check Front face
                        if (z == SIZE - 1 || blocks[x][y][z + 1].blockType.equals(Blocks.AIR)) {
                            AddFace(block, Faces.Front);
                            drawnFaces++;
                        }

                        // Check Bottom face
                        if (y == 0 || blocks[x][y - 1][z].blockType.equals(Blocks.AIR)) {
                            AddFace(block, Faces.Bottom);
                            drawnFaces++;
                        }

                        // Check Top face
                        if (y == HEIGHT - 1 || blocks[x][y + 1][z].blockType.equals(Blocks.AIR)) {
                            AddFace(block, Faces.Top);
                            drawnFaces++;
                        }
                    }

                    // Add indices for the number of drawn faces
                    AddIndices(drawnFaces);
                }
            }
        }
        return this;
    }

    private void AddFace(Block block, Faces face) {
        CombinedFaceData faceData = block.getModel().getFace(face);
        chunkVerts.addAll(List.of(faceData.FaceVectors));
        chunkUVs.addAll(List.of(faceData.UVVectors));
    }

    private void AddIndices(int faces) {
        for(int i = 0; i < faces; i++) {
            chunkIndices.add(indexCount);
            chunkIndices.add(1 + indexCount);
            chunkIndices.add(3 + indexCount);
            chunkIndices.add(3 + indexCount);
            chunkIndices.add(1 + indexCount);
            chunkIndices.add(2 + indexCount);

            indexCount += 4;
        }
    }

    private int VAO, PBO, IBO, TBO;
    public void BuildChunk() {
        if (!this.HasGenerated) return;

        VAO = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(VAO);

        int numVertices = chunkVerts.size();
        int numUVs = chunkUVs.size();
        int numIndices = chunkIndices.size();

        // Allocate memory for position buffer
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(numVertices * 3);
        for (Vector3f vertex : chunkVerts) {
            positionBuffer.put(vertex.getX()).put(vertex.getY()).put(vertex.getZ());
        }
        positionBuffer.flip();
        PBO = storeData(positionBuffer, 0, 3);

        // Allocate memory for texture buffer
        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(numUVs * 2);
        for (Vector2f uv : chunkUVs) {
            textureBuffer.put(uv.getX()).put(uv.getY());
        }
        textureBuffer.flip();
        TBO = storeData(textureBuffer, 2, 2);

        // Allocate memory for indices buffer
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(numIndices);
        for (int index : chunkIndices) {
            indicesBuffer.put(index);
        }
        indicesBuffer.flip();
        IBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, IBO);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);

        // Release memory
        MemoryUtil.memFree(positionBuffer);
        MemoryUtil.memFree(textureBuffer);
        MemoryUtil.memFree(indicesBuffer);

        // Disable face culling
        GL11.glDisable(GL11.GL_CULL_FACE);

        HasMesh = true;

        // Re-enable face culling
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        return bufferID;
    }

    public void Destroy() {
        GL15.glDeleteBuffers(PBO);
        GL15.glDeleteBuffers(IBO);
        GL15.glDeleteBuffers(TBO);

        GL30.glDeleteVertexArrays(VAO);
    }

    float Map(float min, float max, float omin, float omax, float value)
    {
        return lerp(min, max, inverseLerp(omin, omax, value));
    }

    float lerp(float a, float b, float c) {
        return b + a * (c - b);
    }

    float inverseLerp(float a, float b, float c) {
        return (c - a) / (b - a);
    }

    private static float FastFloor(double f)
    {
        return (f >= 0.0f ? (int)f : (int)f - 1);
    }

    public List<Vector3f> getChunkVerts() {
        return chunkVerts;
    }

    public int[] getChunkIndices() {
        return Arrays.stream(chunkIndices.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
    }

    public List<Vector2f> getChunkUVs() {
        return chunkUVs;
    }

    public int getVAO() {
        return VAO;
    }

    public int getIBO() {
        return IBO;
    }

    public int getTBO() {
        return TBO;
    }

    public int getPBO() {
        return PBO;
    }

    public Block getBlockAt(int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0 || x >= SIZE || y >= HEIGHT || z >= SIZE) {
            return null;
        }
        return blocks[x][y][z];
    }
}
