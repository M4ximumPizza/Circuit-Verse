package org.graphics;

import org.core.level.blocks.Block.Faces;
import org.engine.math.Vector2f;
import org.engine.math.Vector3f;

import java.util.HashMap;

/**
 * This class is responsible for storing the data for the block model
 *
 * @author MrZombii
 */
public class BlockModel {

    HashMap<Faces, CombinedFaceData> faces;

    public BlockModel(
            UntexturedBlockModel model,
            UVMap uv
    ) {
        faces = new HashMap<>();
        faces.put(Faces.Back, new CombinedFaceData(model.getFace(Faces.Back), uv.getFaceUV(Faces.Back)));
        faces.put(Faces.Front, new CombinedFaceData(model.getFace(Faces.Front), uv.getFaceUV(Faces.Front)));
        faces.put(Faces.Right, new CombinedFaceData(model.getFace(Faces.Right), uv.getFaceUV(Faces.Right)));
        faces.put(Faces.Left, new CombinedFaceData(model.getFace(Faces.Left), uv.getFaceUV(Faces.Left)));
        faces.put(Faces.Top, new CombinedFaceData(model.getFace(Faces.Top), uv.getFaceUV(Faces.Top)));
        faces.put(Faces.Bottom, new CombinedFaceData(model.getFace(Faces.Bottom), uv.getFaceUV(Faces.Bottom)));
    }

    public CombinedFaceData getFace(Faces face) {
        return faces.get(face);
    }
    public HashMap<Faces, CombinedFaceData> getFaces() {
        return faces;
    }

    public static class CombinedFaceData {
        public Vector3f[] FaceVectors;
        public Vector2f[] UVVectors;

        public CombinedFaceData(
                UntexturedBlockModel.FaceData faceData,
                UVMap.UVData uvData
        ) {
            this.FaceVectors = faceData.FaceVectors;
            this.UVVectors = uvData.UVVectors;
        }

    }
}
