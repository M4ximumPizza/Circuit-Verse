package org.level.player;

import org.core.level.Level;
import org.core.level.blocks.Block;
import org.engine.io.Input;
import org.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

/**
 * This class is responsible for creating and destroying the camera. It is responsible
 * for setting the camera position and rotation and updating the camera. It is also
 * responsible for moving the camera and rotating the camera.
 *
 * @author MrZombii
 * @author Logan Abernathy
 */
public class Camera {
    private Level level;
    private Vector3f position, rotation;
    private float moveSpeed = 0.05f, mouseSenitivity = 0.15f, distance = 2.0f, horizontalAngle = 0, veritcalAngle = 0;
    private double oldMouseX = 0, oldMouseY = 0; double currentMouseX; double currentMouseY;

    public Camera(Vector3f position, Vector3f rotation, Level level) {
        this.position = position;
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

        // Allow the player to move upwards or downwards at any time
        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE) && !isColliding(new Vector3f(position.getX(), newPosition.getY(), position.getZ()))) newPosition = Vector3f.add(newPosition, new Vector3f(0, moveSpeed, 0));
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT) && !isColliding(new Vector3f(position.getX(), newPosition.getY(), position.getZ()))) newPosition = Vector3f.add(newPosition, new Vector3f(0, -moveSpeed, 0));

        float dx = (float) (currentMouseX - oldMouseX);
        float dy = (float) (currentMouseY - oldMouseY);
        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSenitivity, -dx * mouseSenitivity, 0));

        // Clamp the rotation.x to be between -90 and 90
        rotation.setX(Math.max(-90f, Math.min(90f, rotation.getX())));

        // Check collision for each axis separately
        if (!isColliding(new Vector3f(newPosition.getX(), position.getY(), position.getZ()))) {
            position.setX(newPosition.getX());
        }
        if (!isColliding(new Vector3f(position.getX(), newPosition.getY(), position.getZ()))) {
            position.setY(newPosition.getY());
        }
        if (!isColliding(new Vector3f(position.getX(), position.getY(), newPosition.getZ()))) {
            position.setZ(newPosition.getZ());
        }

        oldMouseX = currentMouseX;
        oldMouseY = currentMouseY;
    }

    private boolean isColliding(Vector3f position) {
        int x = (int) Math.floor(position.getX());
        int y = (int) Math.floor(position.getY());
        int z = (int) Math.floor(position.getZ());

        Block blockAtFeet = level.getCurrentChunk(position).getBlockAt(x, y, z);
        Block blockAtHead = level.getCurrentChunk(position).getBlockAt(x, y + 1, z);

        return (blockAtFeet != null && !blockAtFeet.isAir()) || (blockAtHead != null && !blockAtHead.isAir());
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}