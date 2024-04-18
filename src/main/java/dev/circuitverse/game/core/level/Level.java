package dev.circuitverse.game.core.level;

import dev.circuitverse.game.CircuitVerse;
import dev.circuitverse.game.core.level.chunks.Chunk;
import dev.circuitverse.registries.BuiltInRegistries;
import dev.circuitverse.game.core.engine.math.Vector3f;
import dev.circuitverse.game.core.level.Dimension;


public class Level {

    Dimension[] dimensions;
    int currentDimensionIndex = -1;

    private CircuitVerse mc;

    public Level() {}

    public void switchDimension(int index) {
        if (index == currentDimensionIndex) {
            return;
        }
        Dimension d = dimensions[index];
        if (!d.IsInitialized()) {
            try {
                d = d.Init();
            } catch (Exception ignore) {}
        }
        dimensions[index] = d;
        this.currentDimensionIndex = index;
    }

    public Level Initialize() {
        dimensions = new Dimension[BuiltInRegistries.DIMENSIONS.getLength()];
        for (int i = 0; i < dimensions.length; i++) {
            dimensions[i] = BuiltInRegistries.DIMENSIONS.get(i);
        }
        switchDimension(0);
        mc = CircuitVerse.getInstance();
        return this;
    }

    public void Destroy() {
        for (Dimension dimension : dimensions) {
            dimension.Destroy();
        }
    }

    public void Update() {
//        mc.camera.update();
    }

    public Chunk getCurrentChunk(Vector3f playerPosition) {
        // Logic to determine the current chunk based on the player's position
        // This is a placeholder. Replace this with your actual implementation.
        return dimensions[currentDimensionIndex].GetChunks()[0]; // Replace 0 with the index of the current chunk
    }

    public void Render() {
        mc.renderer.RenderDimension(dimensions[currentDimensionIndex]);
    }

}
