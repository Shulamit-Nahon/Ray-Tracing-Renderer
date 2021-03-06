package elements;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CameraIntegretionsTest {


    /**
     * Test helper function to count the intersections and compare with expected value
     * @param cam      camera for the test
     * @param geo      3D body to test the integration of the camer with
     * @param expected amount of intersections
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;

        List<Point3D> allpoints = null;

        cam.setViewPlaneSize(3, 3);
        cam.setDistance(1);
        int nX =3;
        int nY =3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRayThroughPixel(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }
        }

        System.out.format("there is %d points:%n", count);
        if (allpoints != null) {
            for (var item : allpoints) {
                System.out.println(item);
            }
        }
        System.out.println();

        assertEquals(expected, count, "Wrong amount of intersections");
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    void testSphereWithCam(){
        Camera camera1=new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);
        Camera camera2=new Camera(new Point3D(0,0,0.5d),new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        // TC01: Small Sphere 2 points
        assertCountIntersections(camera1,new Sphere(1,new Point3D(0,0,-3)),2);

        // TC02: Big Sphere 18 points
        assertCountIntersections(camera2, new Sphere(2.5, new Point3D(0, 0, -2.5)), 18);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(camera2, new Sphere(2, new Point3D(0, 0, -2)), 10);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(camera2, new Sphere(4, new Point3D(0, 0, -1)), 9);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(camera1, new Sphere(0.5, new Point3D(0, 0, 1)), 0);

    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void testPlaneWithCam(){
        Camera camera=new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        // TC01: Plane against camera 9 points
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(camera, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void testTriangleWithCam(){
        Camera camera=new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        // TC01: Small triangle 1 point
        assertCountIntersections(camera, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -1, -2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(camera, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -20, -2)), 2);

    }
}
