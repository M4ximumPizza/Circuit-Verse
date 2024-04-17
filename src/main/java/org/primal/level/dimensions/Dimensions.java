package org.primal.level.dimensions;

import org.craftmine.core.bootstrap.Bootstrappable;
import org.craftmine.core.registry.Registry;
import org.craftmine.registries.BuiltInRegistries;
import org.craftmine.resources.Identifier;
import org.primal.level.Dimension;

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
