package org.primal.engine.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.primal.utils.FileUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.stb.STBImage.*;

/**
 * This class is responsible for parsing the image. It is responsible for
 * loading the image and getting the width and height of the image.
 *
 * @author MrZombii
 */
public class ImageParser {
    public ByteBuffer GetImage() {
        return image;
    }

    public int GetWidth() {
        return width;
    }

    public int GetHeight() {
        return heigh;
    }

    private ByteBuffer image;
    private int width, heigh;

    ImageParser(int width, int heigh, ByteBuffer image) {
        this.image = image;
        this.heigh = heigh;
        this.width = width;
    }

    public static ImageParser LoadImage(String path) throws Exception {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            image = stbi_load(path, w, h, comp, 4);
            if (image == null) {
                throw new Exception("Could not load image resources.");
            }
            width = w.get();
            height = h.get();
        }
        return new ImageParser(width, height, image);
    }

    public static ImageParser LoadResourceImage(String path) throws Exception {
        byte[] imageData = FileUtils.ReadResource(path);
        ByteBuffer image;
        ByteBuffer imageBuffer = BufferUtils.createByteBuffer(imageData.length);
        imageBuffer.put(imageData);
        imageBuffer.flip();
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            image = stbi_load_from_memory(imageBuffer, w, h, comp, 4);
            if (image == null) {
                throw new Exception("Could not load image resources.");
            }
            width = w.get();
            height = h.get();
        }
        return new ImageParser(width, height, image);
    }

}