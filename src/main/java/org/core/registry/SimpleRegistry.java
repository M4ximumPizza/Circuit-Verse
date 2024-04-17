package org.core.registry;

import org.resources.Identifier;

/**
 * SimpleRegistry interface for the for {@code Registry}
 * @param <T>
 *
 * @author MrZombi
 */
public interface SimpleRegistry<T> extends Registry<T> {
    T register(Identifier identifier, T object);
}
