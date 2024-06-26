package dev.circuitverse.game.core.level.blocks;

import dev.circuitverse.game.core.engine.math.Vector3f;
import dev.circuitverse.game.core.graphics.BlockModel;
import dev.circuitverse.game.core.graphics.UntexturedBlockModel;

import java.util.HashMap;

/**
 * Block class
 *
 * This class is the base class for all blocks in the game
 *
 * @author MrZombii
 * @author Logan Abernathy
 */
public abstract class Block {
    public Vector3f Position;
    public static UntexturedBlockModel BASIC_MODEL;
    public BlockType blockType;

    BlockModel model;

    public Block(BlockType type, Vector3f position) {
        this.Position = position;
        this.blockType = type;
    }

    abstract public BlockModel getModel();
//    abstract public BlockType getBlockType();


    Vector3f[] fixFace(Vector3f[] face, Vector3f position) {
        return new Vector3f[] {
                Vector3f.add(face[0], position),
                Vector3f.add(face[1], position),
                Vector3f.add(face[2], position),
                Vector3f.add(face[3], position)
        };
    }

    UntexturedBlockModel adjustModel(HashMap<Faces, UntexturedBlockModel.FaceData> faces, Vector3f position) {
//        System.out.println(position);
        return new UntexturedBlockModel(
                new UntexturedBlockModel.FaceData(fixFace(faces.get(Faces.Back).FaceVectors, position)),
                new UntexturedBlockModel.FaceData(fixFace(faces.get(Faces.Front).FaceVectors, position)),
                new UntexturedBlockModel.FaceData(fixFace(faces.get(Faces.Right).FaceVectors, position)),
                new UntexturedBlockModel.FaceData(fixFace(faces.get(Faces.Left).FaceVectors, position)),
                new UntexturedBlockModel.FaceData(fixFace(faces.get(Faces.Top).FaceVectors, position)),
                new UntexturedBlockModel.FaceData(fixFace(faces.get(Faces.Bottom).FaceVectors, position))
        );
    }

    static {
        BASIC_MODEL = new UntexturedBlockModel(
                new UntexturedBlockModel.FaceData(new Vector3f[]{
                        new Vector3f(0.5f,  0.5f, -0.5f),
                        new Vector3f(-0.5f, 0.5f, -0.5f),
                        new Vector3f( -0.5f, -0.5f, -0.5f),
                        new Vector3f( 0.5f,  -0.5f, -0.5f)
                }),
                new UntexturedBlockModel.FaceData(new Vector3f[]{
                        new Vector3f(-0.5f,  0.5f,  0.5f),
                        new Vector3f(0.5f, 0.5f,  0.5f),
                        new Vector3f( 0.5f, -0.5f,  0.5f),
                        new Vector3f( -0.5f,  -0.5f,  0.5f)
                }),
                new UntexturedBlockModel.FaceData(new Vector3f[]{
                        new Vector3f( 0.5f,  0.5f, 0.5f),
                        new Vector3f( 0.5f,  0.5f, -0.5f),
                        new Vector3f( 0.5f,  -0.5f,  -0.5f),
                        new Vector3f( 0.5f,  -0.5f,  0.5f)
                }),
                new UntexturedBlockModel.FaceData(new Vector3f[]{
                        new Vector3f(-0.5f,  0.5f, -0.5f),
                        new Vector3f(-0.5f, 0.5f, 0.5f),
                        new Vector3f(-0.5f, -0.5f,  0.5f),
                        new Vector3f(-0.5f,  -0.5f,  -0.5f)
                }),
                new UntexturedBlockModel.FaceData(new Vector3f[]{
                        new Vector3f(-0.5f,  0.5f,  -0.5f),
                        new Vector3f(0.5f,  0.5f, -0.5f),
                        new Vector3f( 0.5f,  0.5f, 0.5f),
                        new Vector3f( -0.5f,  0.5f,  0.5f)
                }),
                new UntexturedBlockModel.FaceData(new Vector3f[]{
                        new Vector3f(-0.5f, -0.5f,  0.5f),
                        new Vector3f(0.5f, -0.5f, 0.5f),
                        new Vector3f( 0.5f, -0.5f, -0.5f),
                        new Vector3f( -0.5f, -0.5f,  -0.5f)
                })
        );


    }

    public static enum Faces {
        Back,
        Front,
        Right,
        Left,
        Top,
        Bottom
    }

    public boolean isAir() {
        // This is a placeholder. Replace this with your actual implementation.
        return this instanceof Air;
    }

    public static class BlockType {
        public Class<? extends Block> BlockRelation;
        public BlockType(Class<? extends Block> blockRelation) {
            this.BlockRelation = blockRelation;
        }

        public Block New(Vector3f position) {
            try {
                return BlockRelation.getDeclaredConstructor(BlockType.class, Vector3f.class).newInstance(this, position);
            } catch (Exception ignore) {
                return null;
            }
        }
    }
}
