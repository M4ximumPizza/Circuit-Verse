package org.craftmine.graphics;

import org.craftmine.core.bootstrap.Bootstrappable;
import org.craftmine.core.registry.Registry;
import org.craftmine.registries.BuiltInRegistries;
import org.craftmine.resources.Identifier;
import org.primal.engine.math.Vector2f;

/**
 * Resources class for the for {@code UVMap}
 *
 * @author MrZombi
 */

@Bootstrappable
public class Resources {
    static {
        Registry.Register(BuiltInRegistries.UV_RESOURCES, Identifier.fromString("craftmine:grass"),
                new UVMap(
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.0f),
                                new Vector2f(1.0f, 0.0f),
                                new Vector2f(1.0f, 0.5f),
                                new Vector2f(0.5f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.0f),
                                new Vector2f(1.0f, 0.0f),
                                new Vector2f(1.0f, 0.5f),
                                new Vector2f(0.5f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.0f),
                                new Vector2f(1.0f, 0.0f),
                                new Vector2f(1.0f, 0.5f),
                                new Vector2f(0.5f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.0f),
                                new Vector2f(1.0f, 0.0f),
                                new Vector2f(1.0f, 0.5f),
                                new Vector2f(0.5f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 0.0f),
                                new Vector2f(0.5f, 0.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        })
                )
        );

        Registry.Register(BuiltInRegistries.UV_RESOURCES, Identifier.fromString("craftmine:dirt"),
                new UVMap(
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.0f, 0.5f),
                                new Vector2f(0.0f, 1.0f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(0.5f, 0.5f)
                        })
                )
        );

        Registry.Register(BuiltInRegistries.UV_RESOURCES, Identifier.fromString("craftmine:stone"),
                new UVMap(
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(1.0f, 1.0f),
                                new Vector2f(1.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(1.0f, 1.0f),
                                new Vector2f(1.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(1.0f, 1.0f),
                                new Vector2f(1.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(1.0f, 1.0f),
                                new Vector2f(1.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(1.0f, 1.0f),
                                new Vector2f(1.0f, 0.5f),
                        }),
                        new UVMap.UVData(new Vector2f[]{
                                new Vector2f(0.5f, 0.5f),
                                new Vector2f(0.5f, 1.0f),
                                new Vector2f(1.0f, 1.0f),
                                new Vector2f(1.0f, 0.5f)
                        })
                )
        );
    }
}
