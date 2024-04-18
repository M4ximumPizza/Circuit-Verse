package org.level.player;

import org.core.level.Level;
import org.core.level.blocks.Block;
import org.engine.io.Input;
import org.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Level level;
    private Vector3f position, rotation;
    private float moveSpeed = 0.1f, mouseSenitivity = 0.15f, distance = 2.0f, horizontalAngle = 0, veritcalAngle = 0;
    private double oldMouseX = 0, oldMouseY = 0; double currentMouseX; double currentMouseY;
    private float verticalSpeed = 0.0f;
    private static final float GRAVITY = -0.02f;
    private static double playerHeight = 9.8;
    private static final float JUMP_SPEED = 1f;
    private static final float BLOCK_HEIGHT = 1.0f; // Height of a block in units
    private static final float PLAYER_HEIGHT_BLOCKS = 1.8f; // Player's height in blocks

    public Camera(Vector3f position, Vector3f rotation, Level level) {
        float playerHeightUnits = PLAYER_HEIGHT_BLOCKS * BLOCK_HEIGHT; // Player's height in units
        this.position = new Vector3f(position.getX(), playerHeightUnits, position.getZ());
        this.rotation = rotation;
        this.level = level;
    }

    public void update() {
        float x = (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;

        currentMouseX = Input.getMouseX();
        currentMouseY = Input.getMouseY();

        Vector3f newPosition = new Vector3f(position.getX(), position.getY(), position.getZ());

        if (Input.isKeyDown(GLFW.GLFW_KEY_A)) newPosition = Vector3f.add(newPosition, new Vector3f(-z, 0, x));
        if (Input.isKeyDown(GLFW.GLFW_KEY_D)) newPosition = Vector3f.add(newPosition, new Vector3f(z, 0, -x));
        if (Input.isKeyDown(GLFW.GLFW_KEY_W)) newPosition = Vector3f.add(newPosition, new Vector3f(-x, 0, -z));
        if (Input.isKeyDown(GLFW.GLFW_KEY_S)) newPosition = Vector3f.add(newPosition, new Vector3f(x, 0, z));

        // Apply gravity
        verticalSpeed += GRAVITY;

        // Jump
        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE) && isColliding(new Vector3f(position.getX(), position.getY() - 0.01f, position.getZ()))) {
            verticalSpeed = JUMP_SPEED;
        }

        newPosition.setY(newPosition.getY() + verticalSpeed); // Apply vertical movement

        // Check collision for each axis separately
        if (!isColliding(new Vector3f(newPosition.getX(), position.getY(), position.getZ()))) {
            position.setX(newPosition.getX());
        }
        if (!isColliding(new Vector3f(position.getX(), newPosition.getY(), position.getZ()))) {
            position.setY(newPosition.getY());
        } else {
            // If colliding with ground, reset vertical speed
            if (verticalSpeed < 0) {
                verticalSpeed = 0;
                newPosition.setY(position.getY()); // Reset the y position to the current position if colliding with the ground
            }
        }
        if (!isColliding(new Vector3f(position.getX(), position.getY(), newPosition.getZ()))) {
            position.setZ(newPosition.getZ());
        }

        float dx = (float) (currentMouseX - oldMouseX);
        float dy = (float) (currentMouseY - oldMouseY);
        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSenitivity, -dx * mouseSenitivity, 0));

        // Clamp the rotation.x to be between -90 and 90
        rotation.setX(Math.max(-90f, Math.min(90f, rotation.getX())));

        oldMouseX = currentMouseX;
        oldMouseY = currentMouseY;
    }

    private boolean isColliding(Vector3f position) {
        float playerWidth = 0.6f;
        float bufferZone = 0.1f;

        // Calculate the player's feet, middle, and head positions
        float feetY = position.getY() - PLAYER_HEIGHT_BLOCKS * BLOCK_HEIGHT;
        float middleY = position.getY() - PLAYER_HEIGHT_BLOCKS * BLOCK_HEIGHT / 2;
        float headY = position.getY();

        int minX = (int) Math.floor(position.getX() - playerWidth / 2 + bufferZone);
        int maxX = (int) Math.floor(position.getX() + playerWidth / 2 - bufferZone);
        int minZ = (int) Math.floor(position.getZ() - playerWidth / 2 + bufferZone);
        int maxZ = (int) Math.floor(position.getZ() + playerWidth / 2 - bufferZone);

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                // Check for collision at the player's feet, middle, and head
                if (isCollidingAt(x, (int) Math.floor(feetY + bufferZone), z) ||
                        isCollidingAt(x, (int) Math.floor(middleY + bufferZone), z) ||
                        isCollidingAt(x, (int) Math.floor(headY - bufferZone), z)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isCollidingAt(int x, int y, int z) {
        Block block = level.getCurrentChunk(new Vector3f(x, y, z)).getBlockAt(x, y, z);
        return block != null && !block.isAir();
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}