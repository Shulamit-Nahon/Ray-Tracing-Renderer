package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {

    private final String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=new AmbientLight();
    public Geometries geometries= new Geometries();
    public List<LightSource> lights;

    /**
     * constructor
     * @param name - reset the scene name
     */
    public Scene(String name) {
        this.name = name;
        geometries= new Geometries();
        lights =new LinkedList<LightSource>();
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

    public Scene setLights(List<LightSource> lights){
        this.lights =lights;
        return this;
    }

}
