package org.core.registry.impl;

import org.core.registry.FreezerRegistry;
import org.resources.Identifier;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Frozen registry for all items in the game that cannot be modified
 * @param <T> Type of item to be registered
 *
 * @author MrZombi
 */
@SuppressWarnings("unchecked")
public class FrozenRegistry<T> implements FreezerRegistry<T> {

    HashMap<Integer, T> registeredItems;
    HashMap<String, Integer> registeredIDs;

    int currentID = -1;
    T[] baseType;
    boolean frozen = false;

    public FrozenRegistry(Class<T> baseType) {
        registeredItems = new HashMap<>();
        registeredIDs = new HashMap<>();
        this.baseType = (T[]) Array.newInstance(baseType, 0);
    }

    @Override
    public T register(Identifier identifier, T object) {
        if (frozen) {
            System.out.println("Registry Frozen Cannot Register Item");
            return null;
        }

        currentID++;
        registeredIDs.put(String.valueOf(identifier), currentID);
        registeredItems.put(currentID, object);
        return object;
    }

    @Override
    public T get(Identifier identifier) {
        if (!registeredItems.containsKey(registeredIDs.get(identifier.toString()))) return registeredItems.values().toArray(baseType)[0];
        return get(registeredIDs.get(identifier.toString()));
    }

    @Override
    public T get(int id) {
        try {
            return registeredItems.get(id);
        } catch (Exception ignore) {
            return registeredItems.get(0);
        }
    }

    @Override
    public int toID(Identifier identifier) {
        return registeredIDs.get(identifier.toString());
    }

    @Override
    public String[] getAllKeys() {
        return registeredItems.keySet().toArray(new String[0]);
    }

    @Override
    public T[] getAllObjects() {
        return registeredItems.values().toArray(baseType);
    }

    @Override
    public void freeze() {
        frozen = true;
    }

    @Override
    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public int getLength() {
        return currentID+1;
    }
}
