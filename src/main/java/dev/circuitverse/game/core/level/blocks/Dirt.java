package dev.circuitverse.game.core.level.blocks;

import dev.circuitverse.game.core.resources.Identifier;
import dev.circuitverse.registries.BuiltInRegistries;
import dev.circuitverse.game.core.engine.math.Vector3f;
import dev.circuitverse.game.core.graphics.BlockModel;
import dev.circuitverse.game.core.graphics.UVMap;

/**
 * Dirt block class
 *
 * @author MrZombii
 */
public class Dirt extends Block {
    public Dirt(BlockType type, Vector3f position) {
        super(type, position);
        UVMap TextureData = BuiltInRegistries.UV_RESOURCES.get(Identifier.fromString("craftmine:dirt"));
        this.model = new BlockModel(
                adjustModel(Block.BASIC_MODEL.getFaces(), position),
                TextureData
        );
    }

    public BlockModel getModel() {
        return this.model;
    }
}
