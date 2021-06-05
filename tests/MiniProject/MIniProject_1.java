package MiniProject;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class MIniProject_1 {

    private static Geometries square = new Geometries(new Polygon(new Point3D(0,0,-50),new Point3D(50,0,-100),new Point3D(50,50,-150),new Point3D(0,50,-100))
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
            new Polygon(new Point3D(0,0,-50),new Point3D(-50,0,-100),new Point3D(-50,50,-150),new Point3D(0,50,-100))
                    .setEmission(new Color(java.awt.Color.BLUE)) //
                    .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
            new Polygon(new Point3D(0,0,-50),new Point3D(-50,0,-100),new Point3D(-50,-50,-150),new Point3D(0,-50,-100))
                    .setEmission(new Color(java.awt.Color.BLUE)) //
                    .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
            new Polygon(new Point3D(0,0,-50),new Point3D(50,0,-100),new Point3D(50,-50,-150),new Point3D(0,-50,-100))
                    .setEmission(new Color(java.awt.Color.BLUE)) //
                    .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100))
    );
    private Scene scene1 = new Scene("Test scene");
    private Scene scene2 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(150, 150) //
            .setDistance(1000);
    private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200) //
            .setDistance(1000);

    private static Geometry triangle1 = new Triangle( //
            new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
    private static Geometry triangle2 = new Triangle( //
            new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
    private static Geometry sphere = new Sphere(50, new Point3D(0, 0, -50)) //
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100));
    Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(150, 150).setDistance(1000);

    /**
     *
     */
    @Test
    public void TestMiniProject1() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(300)),
                new Sphere(50, new Point3D(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),
                new Sphere(25, new Point3D(0, 0, -50)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                new Sphere(20, new Point3D(50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),
                new Sphere(15, new Point3D(70, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),
                new Sphere(20, new Point3D(-50, 0, 0)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),
                 new Sphere(15, new Point3D(-70, 0, 0)) //
                     .setEmission(new Color(java.awt.Color.RED)) //
                     .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),
                new Triangle( //
                        new Point3D(50, 50, 50), new Point3D(-15, 15, 15), new Point3D(-75, -75, 150))
                        .setEmission(new Color(java.awt.Color.BLACK)),
                new Triangle( //
                        new Point3D(-150, -100, -300), new Point3D(-50, 50, 1), new Point3D(-150, 150                  , -150))
                        .setEmission(new Color(java.awt.Color.YELLOW)));

        scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene2.lights.add( new SpotLight(new Color(1000, 600, 0),new Point3D(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));

        ImageWriter imageWriter = new ImageWriter("MiniProject1", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new RayTracerBasic(scene2));
        render.renderImage();
        render.writeToImage();
    }
}
