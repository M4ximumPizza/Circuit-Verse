package org.craftmine.core.level.blocks;

import org.craftmine.core.bootstrap.Bootstrappable;
import org.craftmine.core.registry.Registry;
import org.craftmine.registries.BuiltInRegistries;
import org.craftmine.resources.Identifier;
import org.craftmine.core.level.blocks.Block.BlockType;

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
