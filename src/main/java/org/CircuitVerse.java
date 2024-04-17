package org;

import org.core.bootstrap.Bootstrap;
import org.core.level.Level;
import org.core.level.chunks.ChunkManager;
import org.engine.graphics.Shader;
import org.engine.io.Input;
import org.engine.io.Window;
import org.engine.math.Vector3f;
import org.graphics.Renderer;
import org.level.player.Camera;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;
import org.pmw.tinylog.Logger;
import org.utils.Constants;
import org.utils.Constants.Shaders;
import org.utils.Constants.Windows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for creating and destroying the game. It is responsible
 * for initializing the game and updating the game. It is also responsible for rendering
 * the game.
 *
 * @author MrZombii
 */
public class CircuitVerse implements Runnable{

    public static CircuitVerse instance;

    public Window window;
    private Shader shader;

    public Level level;
    public Renderer renderer;

    public Camera camera;
    public ChunkManager chunkManager;
    public ThreadPoolExecutor service;

    @Override
    public void run() {
        try {
            Init();
        } catch (Exception e) {
            System.err.println("Could Not Initalize CircuitVerse");
            e.printStackTrace();
            System.exit(-1);
        }

        service.execute(() -> {

            double time = (double)System.currentTimeMillis() / 1000d;
            while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
                if (System.currentTimeMillis() / 1000d > time + (1f / 900f)) {
                    time = (double)System.currentTimeMillis() / 1000d;
                    camera.update();
                }
            }
        });
        double time = (double)System.currentTimeMillis() / 1000d;

        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
//            this.camera.update();
            Render();
            if (System.currentTimeMillis() / 1000d > time + (1f / 220f)) {
                time = (double)System.currentTimeMillis() / 1000d;
                Update();
                window.update();
                window.DoFullscreenCheck();
            }

            if (!Input.isKeyDown(GLFW.GLFW_KEY_1) && e) { e = false;  }
            if (!Input.isKeyDown(GLFW.GLFW_KEY_2) && a) { a = false;  }
            if (!Input.isKeyDown(GLFW.GLFW_KEY_3) && c) { c = false;  }

//            if (Input.isKeyDown(GLFW.GLFW_KEY_1) && !e) {
//                level.switchDimension(BuiltInRegistries.DIMENSIONS.toID(Identifier.fromString("craftmine:overworld")));
//                e = true; }
//            if (Input.isKeyDown(GLFW.GLFW_KEY_2) && !a) {
//                level.switchDimension(BuiltInRegistries.DIMENSIONS.toID(Identifier.fromString("craftmine:test")));
//                a = true;
//            }
//            if (Input.isKeyDown(GLFW.GLFW_KEY_3) && !c) {
//                level.switchDimension(BuiltInRegistries.DIMENSIONS.toID(Identifier.fromString("craftmine:renderchunkdem")));
//                c = true;
//            }
        }
        Destroy();
    }

    public void Init() throws Exception {
        service = new ThreadPoolExecutor(2, 4, Long.MAX_VALUE, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20));
        window = new Window(Windows.Width, Windows.Height, Windows.Title);

        try {
            shader = new Shader(
                    Shaders.VertexPath,
                    Shaders.FragmentPath
            );
        } catch (Exception e) {
            System.err.println("Could Not Create Shaders");
            e.printStackTrace();
            System.exit(-1);
        }

        renderer = new Renderer(window, shader);
        window.SetColor(Windows.BackgroundColor);
        window.Create(false);
        window.SetWindowIcon("/assets/Icon.png");

        Bootstrap.bootstrap();

        chunkManager = new ChunkManager();

        level = new Level();
        shader.Create();

        GL30.glFrontFace(GL30.GL_CW);
        GL30.glEnable(GL30.GL_CULL_FACE);
        GL30.glCullFace(GL30.GL_BACK);

        level.Initialize();
        renderer.Init();

        camera = new Camera(
                new Vector3f(
                        Constants.Chunks.MaxWidth / 2f,
                        Constants.Chunks.MaxHeight + 1,
                        Constants.Chunks.MaxWidth / 2f
                ),
                new Vector3f(0 ,0 ,0),
                level
        );
    }

    private boolean e, a, c;

    public void Update() {
//        window.update();
//        window.DoFullscreenCheck();
        level.Update();

    }

    public void Render() {
        level.Render();
        window.swapBuffers();
    }

    public void Destroy() {
        window.destroy();
        level.Destroy();
        shader.destroy();
        service.shutdownNow();
        service.shutdown();
        service.purge();
        Logger.info("Game Closed");
        System.exit(0);
    }

    public static CircuitVerse getInstance() {
        return instance;
    }
}