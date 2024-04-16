package org.craftmine.registries;

import org.craftmine.core.bootstrap.Bootstrappable;
import org.craftmine.core.level.blocks.Block;
import org.craftmine.core.registry.FreezerRegistry;
import org.craftmine.core.registry.Registry;
import org.craftmine.core.registry.SimpleRegistry;
import org.craftmine.core.registry.impl.FrozenRegistry;
import org.craftmine.graphics.UVMap;
import org.craftmine.resources.Identifier;
import org.primal.level.Dimension;
import org.craftmine.core.registry.impl.BasicRegistry;
import org.craftmine.core.registry.UsableRegistry;

/**
 * BuiltInRegistries class for the for
 * {@code BuiltInRegistries}
 *
 * @Author MrZombii
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
