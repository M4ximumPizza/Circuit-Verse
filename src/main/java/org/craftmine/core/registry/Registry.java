package org.craftmine.core.registry;

import org.craftmine.resources.Identifier;

/**
 * Registry interface for the for {@code SimpleRegistry}
 * @param <T>
 *
 * @author MrZombi
 */
public interface Registry<T> {

    static <T> T Register(Registry<T> registry, Identifier identifier, T object) {
        System.out.println("--------" + registry + "  " + identifier);
        return ((SimpleRegistry<T>) registry).register(identifier, object);
    }

}
