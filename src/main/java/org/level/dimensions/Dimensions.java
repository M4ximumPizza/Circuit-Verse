package org.level.dimensions;

import org.core.bootstrap.Bootstrappable;
import org.core.registry.Registry;
import org.level.Dimension;
import org.registries.BuiltInRegistries;
import org.resources.Identifier;

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
