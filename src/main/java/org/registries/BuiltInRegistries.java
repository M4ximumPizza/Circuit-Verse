package org.registries;

import org.core.level.blocks.Block;
import org.core.registry.FreezerRegistry;
import org.core.registry.Registry;
import org.core.registry.impl.FrozenRegistry;
import org.graphics.UVMap;
import org.level.Dimension;
import org.resources.Identifier;

/**
 * BuiltInRegistries class for the for
 * {@code BuiltInRegistries}
 *
 * @author MrZombii
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class BuiltInRegistries {

    public static FreezerRegistry<Registry> REGISTRIES = new FrozenRegistry(Registry.class);
    public static FreezerRegistry<Block.BlockType> BLOCK_TYPE;
    public static FreezerRegistry<UVMap> UV_RESOURCES;
    public static FreezerRegistry<Dimension> DIMENSIONS;

    static {
        BLOCK_TYPE = (FreezerRegistry<Block.BlockType>) Registry.Register(
                REGISTRIES,
                Identifier.fromString("craftmine:block_type"),
                new FrozenRegistry<>(Block.BlockType.class)
        );
        UV_RESOURCES = (FreezerRegistry<UVMap>) Registry.Register(
                REGISTRIES,
                Identifier.fromString("craftmine:uv_resources"),
                new FrozenRegistry<>(UVMap.class)
        );
        DIMENSIONS = (FreezerRegistry<Dimension>) Registry.Register(
                REGISTRIES,
                Identifier.fromString("craftmine:dimensions"),
                new FrozenRegistry<>(Dimension.class)
        );
        REGISTRIES.freeze();
    }
}
