package org.core.level.blocks;

import org.core.bootstrap.Bootstrappable;
import org.core.registry.Registry;
import org.core.level.blocks.Block.BlockType;
import org.registries.BuiltInRegistries;
import org.resources.Identifier;

/**
 * Registry for all blocks in the game
 *
 * @author MrZombii
 */

@Bootstrappable
public class Blocks {
    public static BlockType AIR, GRASS, DIRT, STONE;

    static {
        AIR = Registry.Register(
                BuiltInRegistries.BLOCK_TYPE,
                Identifier.fromString("craftmine:air"),
                new BlockType(Air.class)
        );
        GRASS = Registry.Register(
                BuiltInRegistries.BLOCK_TYPE,
                Identifier.fromString("craftmine:grass"),
                new BlockType(Grass.class)
        );
        DIRT = Registry.Register(
                BuiltInRegistries.BLOCK_TYPE,
                Identifier.fromString("craftmine:dirt"),
                new BlockType(Dirt.class)
        );
        STONE = Registry.Register(
                BuiltInRegistries.BLOCK_TYPE,
                Identifier.fromString("craftmine:stone"),
                new BlockType(Stone.class)
        );
    }
}
