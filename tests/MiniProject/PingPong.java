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
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    private Camera camera1 = new Camera(new Point3D(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
            .setViewPlaneSize(150, 150) //
            .setDistance(1000);
    private Camera camera11 = new Camera(new Point3D(0, 0, 1999), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);
    private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200) //
            .setDistance(1000);

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
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),

                new Cylinder(new Ray(new Point3D(0,120,0),new Vector(0,0,-37)),2,40)
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100).setKt(0.5)),

                new Cylinder(new Ray(   new Point3D(-100,48,0),new Vector(0,-0.5,-37)),2,40)
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100).setKt(0.5)),
                new Cylinder(new Ray( new Point3D(52,-64,0),new Vector(-1,0,-37)),2,40)
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100).setKt(0.5)),

                new Cylinder(new Ray(new Point3D(103,-1,0),new Vector(0,0,-37)),2,40)
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100).setKt(0.5))
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
@Test
    public void TestPingPongfinal() {
        scene.setBackground(new Color(java.awt.Color.white));
        scene.geometries.add( //
                //TABLE
                new Polygon(new Point3D(-109,-43,0),new Point3D(22,-70,0),
                        new Point3D(40,100,0),new Point3D(-76,126,0))
                .setEmission(new Color(java.awt.Color.BLUE))
                .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                // WITHE POLYGON ON THE TABLE
                new Polygon(new Point3D(-51,-69,1),new Point3D(-44,-72,1),
                        new Point3D(-30,115,1),new Point3D(-21,113,1))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                //רשת שחורה
                new Polygon(new Point3D(-96,25,1.1),new Point3D(29,41,1.1),
                        new Point3D(31,14,1.1),new Point3D(-93,40,1.1))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3)),
                // table legs
                new Cylinder(new Ray(new Point3D(-109,-43,0),new Vector(-2,11,-50)),5,60)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                new Cylinder(new Ray(new Point3D(22,-70,0),new Vector(-6,2,-50)),5,60)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                new Cylinder(new Ray(new Point3D(40,100,0),new Vector(-1,-8,-50)),5,60)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                new Cylinder(new Ray(new Point3D(-78,126,0),new Vector(-3,-2,-50)),5,60)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                // ping pong bolls

                new Sphere(4,new Point3D(-13,-58,0)) .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Sphere(4,new Point3D(-5,-54,0)) .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Sphere(4,new Point3D(-3,-66,0)) .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10))


        );
        scene.lights.add(
                //new DirectionalLight(new Color())//
                new SpotLight(new Color(300, 255, 255), new Point3D(400, 0, 600), new Vector(1, 1, -5))//
                        .setKl(1E-5).setKq(1.5E-7));
//                new PointLight(new Color(500, 250, 250), new Point3D(-80, -80, -130)).setKl(0.00005).setKq(0.00005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("ping_pong_final", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

}
