package dev.circuitverse.game.exp;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import org.lwjgl.opengl.GL20;
import dev.circuitverse.game.exp.world.Cube;

public class BlockyGame implements ApplicationListener {

    public static Lwjgl3Application app;
    public static Camera camera;

    @Override
    public void create() {
        app = (Lwjgl3Application) Gdx.app;
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        modelBatch = new ModelBatch();

        camera.position.set(10, 10, 10);
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        cube = new Cube(new Vector3(0, 0 ,0));

    }

    @Override
    public void resize(int i, int i1) {

    }

    private ModelBatch modelBatch;

    static Cube cube;

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        app.getGraphics().setTitle("Circuit Verse v1.0.0 | FPS: " + Gdx.graphics.getFramesPerSecond());

        camera.update();

        modelBatch.begin(camera);
        modelBatch.render(cube.instance);
        modelBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        cube.model.dispose();
    }
}
