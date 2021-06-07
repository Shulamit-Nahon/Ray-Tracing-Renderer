package MiniProject;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Cylinder;
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
        scene.geometries.add( //
               new Cylinder(new Ray(new Point3D(0,-14,0),new Vector(0,-23,0)),15,60)
                       .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setDiffuse(0.5).setSpecular(0.5).setShininess(100)),
                new Cylinder(new Ray(new Point3D(-4,-5,-1),new Vector(2,-1,1)),40,50)
                .setEmission(new Color(java.awt.Color.BLUE))
                .setMaterial(new Material().setShininess(100).setSpecular(0.5).setDiffuse(0.5))

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
}
