package dev.circuitverse.game.exp.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.Random;

public class TextureFaker {

    public static final Pixmap textureFaker(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
        Random random = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int nextInt = random.nextInt(256*256*256);
                pixmap.drawPixel(x, y, Color.valueOf(String.format("%06x", nextInt)).toIntBits());
            }
        }
        return pixmap;
    }

}
