package renderer;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import scene.Scene;
import xml.XmlSceneParser;

import java.util.Collections;


/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {
    private Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setDistance(100) //
            .setViewPlaneSize(500, 500);

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
                .setBackground(new Color(75, 127, 90));

        scene.geometries.add(new Sphere(50, new Point3D(0, 0, -100)),
                new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)), // up
                // left
                new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up
                // right
                new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)), // down
                // left
                new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down
        // right

        ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));

        render.renderImage();
        render.printGrid(100, new Color(java.awt.Color.YELLOW));
        render.writeToImage();
    }

    /**
     * Test for XML based scene - for bonus
     */
    @Test
    public void basicRenderXml() {
        Scene scene = new XmlSceneParser().parse("images/basicRenderTestTwoColors.xml");

        ImageWriter imageWriter = new ImageWriter("xml render test", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));

        render.renderImage();
        render.printGrid(100, new Color(java.awt.Color.YELLOW));
        render.writeToImage();
    }

    // For stage 6 - please disregard in stage 5
    /**
     * Produce a scene with basic 3D model - including individual lights of the bodies
     * and render it into a png image with a grid
     */
    @Test
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2)); //

        scene.geometries.add(new Sphere(50, new Point3D(0, 0, -100)) //
                        .setEmission(new Color(java.awt.Color.CYAN)), //
                new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)) // up left
                        .setEmission(new Color(java.awt.Color.GREEN)),
                new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up right
                new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)) // down left
                        .setEmission(new Color(java.awt.Color.RED)),
                new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100)) // down right
                        .setEmission(new Color(java.awt.Color.BLUE)));

        ImageWriter imageWriter = new ImageWriter("color render test", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));

        render.renderImage();
        render.printGrid(100, new Color(java.awt.Color.WHITE));
        render.writeToImage();
    }
    @Test
    public void jaggedEdgesTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
                .setBackground(new Color(75, 127, 90));

        Triangle bottomLeft = new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)); // down

        scene.geometries.add(new Sphere(50, new Point3D(0, 0, -100)),
                new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)), // up
                // Top left
                new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up
                // Top right
                bottomLeft,
                new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down
                // Bottom right

        Ray ray = camera.constructRayThroughPixel(1000,1000,303,503);

        assertEquals(null, bottomLeft.findIntersections(ray), "There is an intersection that is not supposed to be");
    }
}
