package dev.circuitverse.game.core.registry;

import dev.circuitverse.game.core.resources.Identifier;

/**
 * FreezerRegistry interface for the for {@code FrozenRegistry}
 * @param <T> Type of item to be registered
 *
 * @author MrZombi
 */
public interface FreezerRegistry<T> extends SimpleRegistry<T> {
    T register(Identifier identifier, T object);

    T get(Identifier identifier);
    T get(int id);

    int toID(Identifier identifier);

    String[] getAllKeys();
    T[] getAllObjects();
    void freeze();
    boolean isFrozen();

    int getLength();
}
