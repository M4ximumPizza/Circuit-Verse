package org.primal;

public class Constants {
    public static final class Shaders {
        public static final String VertexPath = "/assets/shaders/mainVertex.glsl";
        public static final String FragmentPath = "/assets/shaders/mainFragment.glsl";
    }

    public static final class Windows {
        public static final int Width = 1280;
        public static final int Height = 760;
        public static final String Title = "Minecraft";
        // Light Blue  : true
        // Dark Purple : false
        public static final String BackgroundColor = true ? "#2994f2" : "#1a001a";
    }

    public static final class Textures {
        public static final String Atlas = "/assets/textures/TextureAtlas.png";
    }

    public static final class Chunks {
        public static final int RenderDistance = 12;

        public static final int MaxWidth = 16;
        public static final int MaxHeight = 1;
    }
}
