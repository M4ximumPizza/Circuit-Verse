package org.resources;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.utils.FileUtils;

/**
 * ResourceTexture class for the for {@code Texture}
 *
 * @author MrZombii
 */
public class ResourceTexture {
    private Texture texture;
    private float width, height;
    public int ID;

    public ResourceTexture(String path) {
        try {
            texture = TextureLoader.getTexture(("/assets/"+path).split("[.]")[1],
                    FileUtils.class.getResource("/assets/"+path).openConnection().getInputStream(),
                    GL11.GL_NEAREST);
            width = texture.getWidth();
            height = texture.getHeight();
            ID = texture.getTextureID();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public void Destroy() { GL30.glDeleteVertexArrays(ID); }

    public float GetWidth() {
        return width;
    }

    public float GetHeight() {
        return height;
    }
}
