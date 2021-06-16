package MiniProject;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.*;
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



    @Test
    public void Testping_pong_background3() {
        scene.setBackground(new Color(java.awt.Color.WHITE));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.15));

        scene.geometries.add(
                new Polygon(// the flour
                        new Point3D(-150,150,-15),
                        new Point3D(0,100,-15),
                        new Point3D(0,0,-15),
                        new Point3D(0,-150,-15),
                        new Point3D(-150,-150,-15))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),

                new Polygon(//right wall--
                        new Point3D(150,0,-10),
                        new Point3D(0,0,-10),
                        new Point3D(-20,-133,-10),
                        new Point3D(122,-138,-10))
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Polygon(//left wall
                        new Point3D(150,0,-9),
                        new Point3D(112,138,-9),
                        new Point3D(-40,150,-9),
                        new Point3D(0,0,-9))
                        .setEmission(new Color(java.awt.Color.darkGray))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Polygon(//walls sapert
                        new Point3D(0,0,-8),
                        new Point3D(0,0.5,-8),
                        new Point3D(150,0.5,-8),
                        new Point3D(150,0,-8))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10))


        );
        scene.geometries.add(//Black mesh
                new Polygon(
                        new Point3D(20, 35, 20),
                        new Point3D(35, 33, 20),
                        new Point3D(25, -47, 20),
                        new Point3D(10, -45, 20))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.19)),
                new Cylinder(new Ray(new Point3D(25, -45, 20),new Vector(-15,0,-10)),2,20)
                        .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Cylinder(new Ray(new Point3D(35, 33, 10),new Vector(-15,2,0)),2,20)
                        .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)));

        scene.geometries.add( //

                new Polygon( //the main polygon
                        new Point3D(35, -95, 0),
                        new Point3D(45, -10, 0),
                        new Point3D(-5, 90, 0),
                        new Point3D(-15, 5, 0))
                        .setEmission(new Color(java.awt.Color.BLUE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10))
        );

        scene.geometries.add(
                new Polygon( //the border of the main polygon
                        new Point3D(-5, 90, 0),
                        new Point3D(-15, 5, 0),
                        new Point3D(-20, 5, 0),
                        new Point3D(-10, 90, 0))
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10).setKr(0.0003)),

                new Polygon( //the border of the main polygon
                        new Point3D(-15, 5, 0),
                        new Point3D(-20, 5, 0),
                        new Point3D(30, -95, 0),
                        new Point3D(35, -95, 0))
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(1000))
        );

        scene.geometries.add(
                //left bottom leg
                new Cylinder(new Ray(new Point3D(-65, 60, 0), new Vector(290, 0, -10)), 1.5, 5)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Cylinder(new Ray(new Point3D(-65, 60, 0), new Vector(290, 0, -10)), 1.5, 55)
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),


                //right bottom leg
                new Cylinder(new Ray(new Point3D(-72, -5, 0), new Vector(290, 0, -10)), 1.5, 5)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Cylinder(new Ray(new Point3D(-72, -5, 0), new Vector(290, 0, -10)), 1.5, 65)
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),

                //left top leg
                new Cylinder(new Ray(new Point3D(-26, -90, 0), new Vector(290, 0, -10)), 1.5, 5)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Cylinder(new Ray(new Point3D(-26, -90, 0), new Vector(290, 0, -10)), 1.5, 55)
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),

                //right top leg
                new Cylinder(new Ray(new Point3D(-18, -35, 0), new Vector(290, 0, -10)), 1.5, 5)
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Cylinder(new Ray(new Point3D(-18, -35, 0), new Vector(290, 0, -10)), 1.5, 20)
                        .setEmission(new Color(java.awt.Color.DARK_GRAY))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(10))
        );

        //Ping pong racket
        scene.geometries.add(

                new Cylinder(new Ray(new Point3D(10,28,7), new Vector(8, -2, 6)), 10, 2)
                        .setEmission(new Color(java.awt.Color.RED)),
                new Cylinder(new Ray(new Point3D(10,28,7), new Vector(8, -2, 6)), 11, 2)
                        .setEmission(new Color(153,76,0)),
                new Cylinder(new Ray(new Point3D(10, 17, 3), new Vector(0, -12.5, -3)), 2, 15)
                        .setEmission(new Color(153,76,0))
                        .setMaterial(new Material().setShininess(100).setSpecular(0.5).setDiffuse(0.5)),//
                new Sphere(3,new Point3D(3,14,2)).setEmission(new Color(204,102,0))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),

                new Cylinder(new Ray(new Point3D(29,-49,7), new Vector(7, 1, 7)), 10, 2)
                        .setEmission(new Color(java.awt.Color.BLACK)),
                new Cylinder(new Ray(new Point3D(29,-49,7), new Vector(7, 1, 7)), 11, 2)
                        .setEmission(new Color(153,76,0)),
                new Cylinder(new Ray(new Point3D(29, -39, 6), new Vector(-2, 9, 0)), 2, 15)
                        .setEmission(new Color(153,76,0))
                        .setMaterial(new Material().setShininess(100).setSpecular(0.5).setDiffuse(0.5)),
                new Sphere(3,new Point3D(31,-52,15)).setEmission(new Color(204,102,0))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),
                new Polygon(new Point3D(-9, 54, 1),
                        new Point3D(41, -40, 1),
                        new Point3D(41, -43, 1),
                        new Point3D(-9, 48, 1)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setDiffuse(0.5).setDiffuse(0.5).setSpecular(0.5).setShininess(10)),


                //lights
                new Cylinder(new Ray(new Point3D(100,46,0),new Vector(-14,0,0)),1,45)//עמוד תלייה
                        .setEmission(new Color(32,32,32)),
                new Polygon(new Point3D(60,46,0),new Point3D(40,53,0),new Point3D(40,39,0))
                .setEmission(new Color(32,32,32)),
                new Sphere(5,new Point3D(42,46,-7.5)).setEmission(new Color(java.awt.Color.WHITE)),

                //second light source
                new Cylinder(new Ray(new Point3D(100,20,0),new Vector(-25,0,0)),1,35)
                        .setEmission(new Color(32,32,32)),
                new Polygon(new Point3D(70,20,0),new Point3D(50,27,0),new Point3D(50,13,0))
                        .setEmission(new Color(32,32,32)),
                new Sphere(5,new Point3D(52,20,-7.5)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setDiffuse(0.25).setSpecular(0.25).setShininess(20)),
                //third light
                new Cylinder(new Ray(new Point3D(100,-3,0),new Vector(-25,0,0)),1,25)
                        .setEmission(new Color(32,32,32)),
                new Polygon(new Point3D(77,-3,0),new Point3D(58,5,0),new Point3D(58,-10,0))
                        .setEmission(new Color(32,32,32)),
                new Sphere(5,new Point3D(60,-3,-7.5)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setDiffuse(0.4).setSpecular(0.3).setShininess(100).setKt(0.3))

        );


        scene.lights.add(new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
                .setKl(0.0001).setKq(0.000005));
        scene.lights.add( new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(-100, -100, 500),  new Vector(-1, -1, -2))
                .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(40,-3,10),new Vector(-13,2,0))
               .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(30,20,10),new Vector(-13,2,0))
                .setKl(0.0004).setKq(0.0000006));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(20,46,10),new Vector(-13,2,0))
                .setKl(0.0004).setKq(0.0000006));

//        scene.lights.add(
//                new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(10, -10, -130), new Vector(-2, -2, -1))
//                        .setKl(0.0001).setKq(0.000005));
//        scene.lights.add(
//                new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(400, 0, 600), new Vector(1, 1, -5))//
//                        .setKl(1E-5).setKq(1.5E-7));
        //scene.lights.add( new SpotLight(new Color(java.awt.Color.WHITE),new Point3D(-100, -100, 500),  new Vector(-1, -1, -2))
            //    .setKl(0.00005).setKq(0.00005));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("ping_pong_background3", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }




}
