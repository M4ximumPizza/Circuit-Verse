package org.craftmine.resources;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWErrorCallback.createPrint;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL20.GL_SHADING_LANGUAGE_VERSION;
import static org.lwjgl.opengl.GL20.glGetString;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * This class is responsible for stitching textures together. It is responsible for
 * stitching textures together and rendering the stitched texture.
 *
 * @author MrZombii
 */
public class TextureStitcherLWJGL {

    private long window;

    public static void main(String[] args) throws IOException {
        new TextureStitcherLWJGL().run();
    }

    public void run() throws IOException {
        init();
        loop();
        cleanup();
    }

    private void init() {
        glfwSetErrorCallback(createPrint(System.err));

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(800, 600, "Texture Stitcher", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    private void loop() throws IOException {
        glEnable(GL_TEXTURE_2D);

        // Replace these paths with the paths to your own input textures
        String[] inputTexturePaths = {"texture1.png", "texture2.png", "texture3.png"};

        int totalWidth = 0;
        int maxHeight = 0;

        int[] textures = new int[inputTexturePaths.length];

        for (int i = 0; i < inputTexturePaths.length; i++) {
            textures[i] = loadTexture(inputTexturePaths[i]);
            totalWidth += getTextureWidth(textures[i]);
            maxHeight = Math.max(maxHeight, getTextureHeight(textures[i]));
        }

        int stitchedTexture = createStitchedTexture(totalWidth, maxHeight, textures);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            renderStitchedTexture(stitchedTexture, totalWidth, maxHeight);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glDeleteTextures(stitchedTexture);
    }

    private int loadTexture(String filepath) throws IOException {
        int textureID = glGenTextures();

        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer image = STBImage.stbi_load(filepath, width, height, channels, 4);
            if (image != null) {
                glBindTexture(GL_TEXTURE_2D, textureID);

                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

                STBImage.stbi_image_free(image);
            } else {
                throw new IOException("Failed to load texture: " + filepath);
            }
        }

        return textureID;
    }

    private int createStitchedTexture(int width, int height, int[] textures) {
        int stitchedTextureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, stitchedTextureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);

        for (int i = 0; i < textures.length; i++) {
            int textureWidth = getTextureWidth(textures[i]);
            int textureHeight = getTextureHeight(textures[i]);

            glCopyTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 0, 0, textureWidth, textureHeight, 0);
            glCopyTexSubImage2D(GL_TEXTURE_2D, 0, 0, 0, 0, 0, textureWidth, textureHeight);

            ByteBuffer subBuffer = BufferUtils.createByteBuffer(textureWidth * textureHeight * 4);
            glGetTexImage(GL_TEXTURE_2D, 0, GL_RGBA, GL_UNSIGNED_BYTE, subBuffer);
            buffer.put(subBuffer);
        }

        buffer.flip();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return stitchedTextureID;
    }

    private void renderStitchedTexture(int textureID, int width, int height) {
        glViewport(0, 0, 800, 600);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 800, 0, 600, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);
        glEnd();
    }

    private int getTextureWidth(int textureID) {
        glBindTexture(GL_TEXTURE_2D, textureID);
        return glGetTexLevelParameteri(GL_TEXTURE_2D, 0, GL_TEXTURE_WIDTH);
    }

    private int getTextureHeight(int textureID) {
        glBindTexture(GL_TEXTURE_2D, textureID);
        return glGetTexLevelParameteri(GL_TEXTURE_2D, 0, GL_TEXTURE_HEIGHT);
    }

    private void cleanup() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
