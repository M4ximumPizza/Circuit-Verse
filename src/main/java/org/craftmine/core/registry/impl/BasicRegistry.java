package org.craftmine.core.registry.impl;

import org.craftmine.resources.Identifier;
import org.craftmine.core.registry.UsableRegistry;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Basic registry for all items in the game that can be used
 * @param <T> Type of item to be registered
 *
 * @author MrZombi
 */
@SuppressWarnings("unchecked")
public class BasicRegistry<T> implements UsableRegistry<T> {

    HashMap<String, T> registeredItems;
    T[] baseType;

    public BasicRegistry(Class<T> baseType) {
        registeredItems = new HashMap<>();
        this.baseType = (T[]) Array.newInstance(baseType, 0);
    }

    @Override
    public T register(Identifier identifier, T object) {
        return registeredItems.put(String.valueOf(identifier), object);
    }

    @Override
    public T get(Identifier identifier) {
        return get(identifier.toString());
    }

    @Override
    public T get(String string) {
        if (!registeredItems.containsKey(Identifier.fromString(string).toString())) return registeredItems.values().toArray(baseType)[0];
        return registeredItems.get(Identifier.fromString(string).toString());
    }

    @Override
    public T get(int id) {
        try {
            return registeredItems.values().toArray(baseType)[id];
        } catch (Exception ignore) {
            return registeredItems.values().toArray(baseType)[0];
        }
    }

    @Override
    public T[] getRegisteredObjects() {
        return registeredItems.values().toArray(baseType);
    }
}
