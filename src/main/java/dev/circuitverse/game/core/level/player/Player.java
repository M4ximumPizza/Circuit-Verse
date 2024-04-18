package dev.circuitverse.game.core.level.player;

import dev.circuitverse.utils.Constants.Chunks;
import dev.circuitverse.game.core.level.Level;
import dev.circuitverse.game.core.engine.math.Vector3f;

/**
 * This class is responsible for storing the player. It is responsible
 * for creating and destroying the player. It is also responsible for
 * setting the player's position and rotation.
 *
 * @author MrZombii
 */

@Deprecated(since = "For Now")
public class Player {
    
    public dev.circuitverse.game.core.level.player.Camera Camera;

    public Player() {
        Level level = new Level(); // You need to initialize this properly
        Camera = new Camera(
                new Vector3f(Chunks.MaxWidth / 2, Chunks.MaxHeight + 10, Chunks.MaxWidth / 2), // Make sure the player is above the ground
                new Vector3f(0, 0, 0),
                level
        );
    }

    public Player(Vector3f Position, Vector3f Rotation) {
        Level level = new Level(); // You need to initialize this properly
        Camera = new Camera(
                Position,
                Rotation,
                level
        );
    }
}
