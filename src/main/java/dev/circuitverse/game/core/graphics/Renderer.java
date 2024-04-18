package dev.circuitverse.game.core.graphics;

import dev.circuitverse.game.core.level.chunks.Chunk;
import dev.circuitverse.game.core.level.chunks.ChunkManager;
import dev.circuitverse.game.core.engine.graphics.Shader;
import dev.circuitverse.game.core.engine.io.Window;
import dev.circuitverse.game.core.engine.math.Matrix4f;
import dev.circuitverse.game.core.engine.math.Vector3f;
import dev.circuitverse.game.core.level.Dimension;
import dev.circuitverse.game.core.level.player.Camera;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import dev.circuitverse.game.CircuitVerse;
import dev.circuitverse.game.core.resources.ResourceTexture;
import dev.circuitverse.utils.Constants;

/**
 * This class is responsible for rendering the chunk. It is responsible for
 * rendering the chunk using the shader and the window.
 *
 * @author MrZombii
 * @author Logan Abernathy
 */
public class Renderer {
    private Shader shader;
    private Window window;
    private CircuitVerse mc;

    public Renderer(Window window, Shader shader) {
        this.shader = shader;
        this.window = window;
    }

    public void Init() {
        this.mc = CircuitVerse.getInstance();
    }

    public void RenderChunk(Chunk chunk, Camera camera) {
        ResourceTexture objectMaterial = new ResourceTexture("textures/TextureAtlas.png");
        int vao = chunk.getVAO();
        int ibo = chunk.getIBO();
        int indicesLength = chunk.getChunkIndices().length;
        Matrix4f modelMatrix = Matrix4f.transform(chunk.position, new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        Matrix4f viewMatrix = Matrix4f.view(camera.getPosition(), camera.getRotation());
        Matrix4f projectionMatrix = window.getProjectionMatrix();
        GL30.glBindVertexArray(vao);
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, objectMaterial.ID);
        shader.bind();
        shader.setUniform("model", modelMatrix);
        shader.setUniform("projection", projectionMatrix);
        shader.setUniform("view", viewMatrix);
        GL11.glDrawElements(GL11.GL_TRIANGLES, indicesLength, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    public void RenderDimension(Dimension dimension) {
        ResourceTexture objectMaterial = new ResourceTexture("textures/TextureAtlas.png");

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, objectMaterial.ID);
        shader.bind();
        shader.setUniform("projection", window.getProjectionMatrix());
        shader.setUniform("view", Matrix4f.view(mc.camera.getPosition(), mc.camera.getRotation()));

        ChunkManager.RenderDistanceVectors vec = ChunkManager.getRenderDistanceAtPlayer(Constants.Chunks.RenderDistance);
        Chunk[] chunks = mc.chunkManager.GenerateVisibleChunks(dimension.GetChunks());
        ChunkManager manager = mc.chunkManager;
        for (int i = 0; i < chunks.length; i++) {
            Chunk chunk = chunks[i];
            if (ChunkManager.ChunkIsWithinBounds(chunk, vec)){
                if (!chunk.IsGenerating) {
                    if (!chunk.HasGenerated) {
                        chunks[i] = manager.GenerateChunk(chunk);
                    }
                    if (chunk.HasGenerated && !chunk.HasMesh) {
                        chunk.BuildChunk();
                    }
                    if (chunk.HasGenerated && chunk.HasMesh) {
                        int vao = chunk.getVAO();
                        int ibo = chunk.getIBO();
                        int indicesLength = chunk.getChunkIndices().length;
                        GL30.glBindVertexArray(vao);
                        GL30.glEnableVertexAttribArray(0);
                        GL30.glEnableVertexAttribArray(1);
                        GL30.glEnableVertexAttribArray(2);
                        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
                        shader.setUniform("model", Matrix4f.transform(chunk.position, new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));
                        GL11.glDrawElements(GL11.GL_TRIANGLES, indicesLength, GL11.GL_UNSIGNED_INT, 0);
                        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
                        GL30.glDisableVertexAttribArray(0);
                        GL30.glDisableVertexAttribArray(1);
                        GL30.glDisableVertexAttribArray(2);
                        GL30.glBindVertexArray(0);
                    }
                }
            } else if (chunk.HasMesh) {
                chunk.Destroy();
                chunk.HasMesh = false;
            }
        }
        shader.unbind();
    }

}