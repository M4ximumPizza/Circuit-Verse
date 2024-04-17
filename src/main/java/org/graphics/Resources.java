package org.graphics;

import org.core.bootstrap.Bootstrappable;
import org.core.registry.Registry;
import org.engine.math.Vector2f;
import org.registries.BuiltInRegistries;
import org.resources.Identifier;

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
