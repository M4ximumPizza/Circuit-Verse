package dev.circuitverse.game.core.registry;

import dev.circuitverse.game.core.resources.Identifier;

/**
 * UsableRegistry interface for the for {@code SimpleRegistry}
 * @param <T>
 *
 * @author MrZombi
 */
public interface UsableRegistry<T> extends SimpleRegistry<T> {


    T register(Identifier identifier, T object);
    T get(Identifier identifier);
    T get(String string);
    T get(int id);

    T[] getRegisteredObjects();
}
