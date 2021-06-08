package MiniProject;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class PingPong {
    private Scene scene = new Scene("ping_pong");
    private Camera camera = new Camera(new Point3D(25, 0, 1000), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    /**
     *
     */
    @Test
    public void TestPingPong() {
        scene.setBackground(new Color(java.awt.Color.white));
        scene.geometries.add( //
               new Cylinder(new Ray(new Point3D(0,-5,0),new Vector(0,3,0)),15,60)
                       .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                new Cylinder(new Ray(new Point3D(-4,-5,-1),new Vector(2,-1,1)),40,50)
                .setEmission(new Color(java.awt.Color.BLUE))
                .setMaterial(new Material().setShininess(100).setSpecular(0.5).setDiffuse(0.5))
                //new Sphere(40,new Point3D(-30,30,30))

        );
        scene.lights.add(
                //new DirectionalLight(new Color())//
                new SpotLight(new Color(300, 255, 255), new Point3D(400, 0, 600), new Vector(1, 1, -5))//
                        .setKl(1E-5).setKq(1.5E-7));
//                new PointLight(new Color(500, 250, 250), new Point3D(-80, -80, -130)).setKl(0.00005).setKq(0.00005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("ping_pong", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void Testping_pong_background() {
        scene.setBackground(new Color(java.awt.Color.white));
        scene.geometries.add( //
               new Polygon(new Point3D(52,-64,0),
                       new Point3D(103,-1,0),
                       new Point3D(0,120,0),
                       new Point3D(-100,48,0))
                .setEmission(new Color(java.awt.Color.BLUE))
                       .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Polygon(new Point3D(-40,91,1),
                        new Point3D(-42,89,1),
                        new Point3D(80,-30,1),
                        new Point3D(81,-28,1)).setEmission(new Color(java.awt.Color.WHITE))
                .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),

                new Polygon(new Point3D(65,44,1.1),
                        new Point3D(54,56,1.1),
                        new Point3D(-21,-10,1.1),
                        new Point3D(-4,-22.5,1.1))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),

                new Sphere(4,new Point3D(-44,50,0)) .setEmission(new Color(java.awt.Color.ORANGE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Sphere(4,new Point3D(-50,40,0)) .setEmission(new Color(java.awt.Color.ORANGE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Sphere(4,new Point3D(-40,40,0)) .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10))
        );
        scene.lights.add(
                //new DirectionalLight(new Color())//
                new SpotLight(new Color(300, 255, 255), new Point3D(400, 0, 600), new Vector(1, 1, -5))//
                        .setKl(1E-5).setKq(1.5E-7));
//                new PointLight(new Color(500, 250, 250), new Point3D(-80, -80, -130)).setKl(0.00005).setKq(0.00005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("ping_pong_background", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
