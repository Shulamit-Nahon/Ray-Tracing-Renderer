package bowling_ball;

import elements.Camera;
import elements.DirectionalLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class BowlingBall {
    private Scene scene = new Scene("bowling ball");
    private Camera camera = new Camera(new Point3D(25, 0, 1000), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);


    @Test
    public void bowlingballTest() {
        scene.geometries.add( //
                new Sphere(56, new Point3D(0, -10, 0)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setDiffuse(0).setSpecular(10000).setShininess(10000)),
                new Sphere(7.6, new Point3D(-17, -38, 42)) //
                        .setEmission(new Color(java.awt.Color.black)) //
                        .setMaterial(new Material().setDiffuse(0).setSpecular(0).setShininess(0)), ////
                new Sphere(7.6, new Point3D(11, -39, 43)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setDiffuse(0).setSpecular(0).setShininess(0)), ////
                new Sphere(7.6, new Point3D(-3, -52, 34)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setDiffuse(0).setSpecular(0).setShininess(0)) ////


        );
        scene.lights.add(
                //new DirectionalLight(new Color())//
                new SpotLight(new Color(255, 255, 255), new Point3D(-88, -88, 120), new Vector(1, 1, -3))//
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("bowling-ball", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
