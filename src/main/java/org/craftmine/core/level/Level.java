package org.craftmine.core.level;

import org.craftmine.registries.BuiltInRegistries;
import org.primal.level.Dimension;
import org.primal.main.Minecraft;


public class Level {

    Dimension[] dimensions;
    int currentDimensionIndex = -1;

    private Minecraft mc;

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
        mc = Minecraft.getInstance();
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

    public void Render() {
        mc.renderer.RenderDimension(dimensions[currentDimensionIndex]);
    }

}
