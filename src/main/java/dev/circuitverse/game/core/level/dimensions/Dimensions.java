package dev.circuitverse.game.core.level.dimensions;

import dev.circuitverse.game.core.level.Dimension;
import dev.circuitverse.game.core.resources.Identifier;
import dev.circuitverse.registries.BuiltInRegistries;
import dev.circuitverse.game.core.bootstrap.Bootstrappable;
import dev.circuitverse.game.core.registry.Registry;

/**
 * This class is responsible for storing the dimensions. It is responsible
 * for creating and destroying the dimensions.
 *
 * @author MrZombii
 */

@Bootstrappable
public class Dimensions {
    public static Dimension OVERWORLD;
    public static Dimension TEST;
    public static Dimension RENDERCHUNKDEM;

    static {
        try {
            TEST = Registry.Register(BuiltInRegistries.DIMENSIONS, Identifier.fromString("craftmine:test"), new TestingDim());
            OVERWORLD = Registry.Register(BuiltInRegistries.DIMENSIONS, Identifier.fromString("craftmine:overworld"), new Overworld());
            RENDERCHUNKDEM = Registry.Register(BuiltInRegistries.DIMENSIONS, Identifier.fromString("craftmine:renderchunkdem"), new RenderChunkTest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
