package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;

public class Scene {

    private final String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    /**
     * constructor
     * @param name - reset the scene name
     */
    public Scene(String name) {
        this.name = name;
        geometries= new Geometries();
    }

    //chaining methods
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }


}
