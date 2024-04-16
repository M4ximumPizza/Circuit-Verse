package org.primal.level.player;

import org.primal.engine.math.Vector3f;
import org.primal.Constants.Chunks;

/**
 * This class is responsible for storing the player. It is responsible
 * for creating and destroying the player. It is also responsible for
 * setting the player's position and rotation.
 *
 * @Author MrZombii
 */

@Deprecated(since = "For Now")
public class Player {
    
    public Camera Camera;

    public Player() {
        Camera = new Camera(
            new Vector3f(Chunks.MaxWidth / 2, Chunks.MaxHeight+1, Chunks.MaxWidth / 2),
            new Vector3f(0, 0, 0)
        );
    }

    public Player(Vector3f Position, Vector3f Rotation) {
        Camera = new Camera(
            Position,
            Rotation
        );
    }
}
