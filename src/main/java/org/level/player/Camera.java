package org.level.player;

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
    private Vector3f position, rotation;
    private float moveSpeed = 0.05f, mouseSenitivity = 0.15f, distance = 2.0f, horizontalAngle = 0, veritcalAngle = 0;
    private double oldMouseX = 0, oldMouseY = 0; double currentMouseX; double currentMouseY;

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void update() {
        currentMouseX = Input.getMouseX();
        currentMouseY = Input.getMouseY();

        float x = (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;

        if (Input.isKeyDown(GLFW.GLFW_KEY_A)) position = Vector3f.add(position, new Vector3f(-z, 0, x));
        if (Input.isKeyDown(GLFW.GLFW_KEY_D)) position = Vector3f.add(position, new Vector3f(z, 0, -x));
        if (Input.isKeyDown(GLFW.GLFW_KEY_W)) position = Vector3f.add(position, new Vector3f(-x, 0, -z));
        if (Input.isKeyDown(GLFW.GLFW_KEY_S)) position = Vector3f.add(position, new Vector3f(x, 0, z));
        if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));

        float dx = (float) (currentMouseX - oldMouseX);
        float dy = (float) (currentMouseY - oldMouseY);

        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSenitivity, -dx * mouseSenitivity, 0));

        // Clamp the rotation.x to be between -90 and 90
        rotation.setX(Math.max(-90f, Math.min(90f, rotation.getX())));

        oldMouseX = currentMouseX;
        oldMouseY = currentMouseY;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}