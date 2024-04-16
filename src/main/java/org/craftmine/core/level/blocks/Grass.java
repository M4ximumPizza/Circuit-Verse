package org.craftmine.core.level.blocks;

import org.craftmine.graphics.BlockModel;
import org.craftmine.graphics.UVMap;
import org.craftmine.registries.BuiltInRegistries;
import org.craftmine.resources.Identifier;
import org.primal.engine.math.Vector3f;

/**
 * Grass block class
 *
 * @author MrZombii
 */
public class Grass extends Block {
    public Grass(BlockType type, Vector3f position) {
            super(type, position);
            UVMap TextureData = BuiltInRegistries.UV_RESOURCES.get(Identifier.fromString("craftmine:grass"));
            this.model = new BlockModel(
                    adjustModel(Block.BASIC_MODEL.getFaces(), position),
                    TextureData
            );
    }

    public BlockModel getModel() {
        return this.model;
    }
}
