package geometries;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.*;

class BorderTest {

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);


    @Test
    void bordable() {
        scene.setAmbientLight(new AmbientLight(new Color(100,200,150), 1));
        scene.geometries.add( //
                new Polygon(new Point3D(150,-150,0),
                            new Point3D(150,150,0),
                            new Point3D(-150,150,0),
                            new Point3D(-150,-150,0)));
        scene.lights.add( //
                new PointLight(new Color(400, 240, 0), new Point3D(-100, -100, 200)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("BordableTest", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}