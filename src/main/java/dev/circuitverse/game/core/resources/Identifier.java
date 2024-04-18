package dev.circuitverse.game.core.resources;

/**
 * Identifier class for the for {@code Registry}
 *
 * @author MrZombii
 */
public class Identifier {

    private String namespace = "craftmine";
    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    public Identifier(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }

    public String getName() { return name; }
    public String getNamespace() { return namespace; }

    public static Identifier fromString(String str) {
        if (!str.contains(":")) {
            return new Identifier("craftmine", str);
        }
        String[] s = str.split(":");
        return new Identifier(s[0], s[1]);
    }

    @Override
    public String toString() {
        return namespace+":"+name;
    }
}
