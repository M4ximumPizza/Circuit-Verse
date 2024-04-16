package org.craftmine.core.bootstrap;

import org.craftmine.core.registry.FreezerRegistry;
import org.craftmine.core.registry.Registry;
import org.craftmine.registries.BuiltInRegistries;
import org.reflections.Reflections;

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
