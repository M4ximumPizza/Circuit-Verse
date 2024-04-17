package org.graphics;

import org.core.level.blocks.Block;
import org.engine.math.Vector3f;

import java.util.HashMap;

/**
 * This class is responsible for storing the data for the block model.
 *
 * @author MrZombii
 */
public class UntexturedBlockModel {

    HashMap<Block.Faces, FaceData> faces;

    public UntexturedBlockModel(
            FaceData Back, FaceData Front,
            FaceData Right, FaceData Left,
            FaceData Top, FaceData Bottom
    ) {
        faces = new HashMap<>();
        faces.put(Block.Faces.Back, Back);
        faces.put(Block.Faces.Front, Front);
        faces.put(Block.Faces.Right, Right);
        faces.put(Block.Faces.Left, Left);
        faces.put(Block.Faces.Top, Top);
        faces.put(Block.Faces.Bottom, Bottom);
    }

    public FaceData getFace(Block.Faces face) { return faces.get(face); }
    public HashMap<Block.Faces, FaceData> getFaces() {
        return faces;
    }

    public static class FaceData {
        public Vector3f[] FaceVectors;

        public FaceData(
                Vector3f[] FaceVectors
        ) {
            this.FaceVectors = FaceVectors;
        }

    }

}
