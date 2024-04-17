package org.core.bootstrap;

import org.core.registry.FreezerRegistry;
import org.core.registry.Registry;
import org.reflections.Reflections;
import org.registries.BuiltInRegistries;

import java.util.Set;

/**
 * This class is responsible for bootstrapping the game. It is responsible for
 * initializing the game.
 */
@SuppressWarnings({"rawtypes"})
public class Bootstrap {

    public static void bootstrap() {
        Reflections reflections = new Reflections("org");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Bootstrappable.class);

        for (Class<?> bootstrap : classes) {
            try {
                bootstrap.newInstance();
            } catch (Exception e) {
                System.out.printf("Could Not Bootstrap %v.class\n", bootstrap.getName());
                e.printStackTrace();
            }
        }

        for (Registry registry : BuiltInRegistries.REGISTRIES.getAllObjects()) {
            if (registry instanceof FreezerRegistry) {
                ((FreezerRegistry<?>) registry).freeze();
            }
        }
    }
}
