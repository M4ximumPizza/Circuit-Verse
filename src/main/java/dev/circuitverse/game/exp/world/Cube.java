package dev.circuitverse.game.exp.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Cube {
    public ModelInstance instance;
    public Model model;

    public Cube(Vector3 position) {

        ModelBuilder builder = new ModelBuilder();
        Material material = new Material(
                TextureAttribute.createDiffuse(
                        new Texture(TextureFaker.textureFaker(16, 16))
                )
//                ColorAttribute.createDiffuse(Color.GREEN)
        );
        Model model = builder.createBox(1, 1, 1, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instance = new ModelInstance(model);
        instance.transform.setToTranslation(position);

    }

}
