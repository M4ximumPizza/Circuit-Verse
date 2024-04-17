package org.core.level.blocks;

import org.engine.math.Vector3f;
import org.graphics.BlockModel;
import org.graphics.UVMap;
import org.registries.BuiltInRegistries;
import org.resources.Identifier;

/**
 * Stone block class
 *
 * @author MrZombii
 */
public class Stone extends Block {
    public Stone(BlockType type, Vector3f position) {
            super(type, position);
            UVMap TextureData = BuiltInRegistries.UV_RESOURCES.get(Identifier.fromString("craftmine:stone"));
            this.model = new BlockModel(
                    adjustModel(Block.BASIC_MODEL.getFaces(), position),
                    TextureData
            );
    }

    public BlockModel getModel() {
        return this.model;
    }
}
