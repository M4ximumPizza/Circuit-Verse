package org.craftmine.graphics;

import org.craftmine.core.level.blocks.Block;
import org.craftmine.core.registry.Registry;
import org.craftmine.registries.BuiltInRegistries;
import org.craftmine.resources.Identifier;
import org.primal.engine.math.Vector2f;

import java.util.HashMap;

/**
 * Resources class for the for {@code UVMap}
 *
 * @author MrZombii
 */
public class UVMap {
    HashMap<Block.Faces, UVData> uvs;

    public UVMap(
            UVData Back, UVData Front,
            UVData Right, UVData Left,
            UVData Top, UVData Bottom
    ) {
        this.uvs = new HashMap<>();
        uvs.put(Block.Faces.Back, Back);
        uvs.put(Block.Faces.Front, Front);
        uvs.put(Block.Faces.Right, Right);
        uvs.put(Block.Faces.Left, Left);
        uvs.put(Block.Faces.Top, Top);
        uvs.put(Block.Faces.Bottom, Bottom);
    }

    public UVData getFaceUV(Block.Faces face) {
        return uvs.get(face);
    }

    public static class UVData {
        public Vector2f[] UVVectors;

        public UVData(
                Vector2f[] UVVectors
        ) {
            this.UVVectors = UVVectors;
        }
    }
}
