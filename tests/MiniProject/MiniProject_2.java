package MiniProject;

import elements.Camera;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class MiniProject_2 {

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(150, 150) //
            .setDistance(1000);
    @Test
    void BoundaryVolume() {

        for (int i = -6; i <=6; i++) {
            for (int j = -6; j <=6; j++) {
                scene.geometries.add(
                        new Sphere(5, new Point3D(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.RED)) //
                                .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)));
            }
        }

        scene.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene.lights.add( new SpotLight(new Color(1000, 600, 0),new Point3D(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));

        ImageWriter imageWriter = new ImageWriter("MiniProject_2", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    void BoundaryVolumeHierarchy() {

        for (int i = -6; i <=6; i++) {
            Geometries row = new Geometries();
            for (int j = -6; j <=6; j++) {
                row.add(
                        new Sphere(5, new Point3D(i*10,j*10,0))
                                .setEmission(new Color(java.awt.Color.RED)) //
                                .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)));
            }
            scene.geometries.add(row);
        }

        scene.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene.lights.add( new SpotLight(new Color(1000, 600, 0),new Point3D(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));

        ImageWriter imageWriter = new ImageWriter("MiniProject_2_Hierarchy", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
