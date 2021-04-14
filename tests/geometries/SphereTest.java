package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Spheres
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sphere = new Sphere(12.6, new Point3D(1d, 2d, -6d));
        assertEquals(new Vector(0, 0d, 1d), sphere.getNormal(new Point3D(1d, 2d, 5d)), "ERROR: the actual normal is incorrect");
    }

    @Test
    void findIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));
        Sphere sphere1 = new Sphere(1d, new Point3D(0, 1.5, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals(null,
                sphere.findIntersections(new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(
                new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));

        assertEquals(2, result.size(), "Wrong number of points");

        //if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));

        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(1,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 2, 0),
                        new Vector(1, 0.5, 0))).size(),
                "Ray's line starts inside the sphere");


        // TC04: Ray starts after the sphere (0 points)
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(1, -2, 0),
                        new Vector(0.5, 0.5, 0))),
                "Ray's line starts after the sphere");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(1,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.5, 0),
                        new Vector(1, 0.5, 0))).size(),
                "Ray's line starts at the sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.5, 0),
                        new Vector(-2, -0.5, 0))),
                "Ray's line starts at sphere and goes outside ");


        // **** Group: Ray's line goes through the center ****

        // TC13: Ray starts before the sphere (2 points)
        assertEquals(2,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0, 1),
                        new Vector(1, 3, -1))).size(),
                "Ray's line starts at sphere and goes outside ");


        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals(1,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.5, 0),
                        new Vector(1, 2.5, 0))).size(),
                "Ray's line starts at sphere and goes outside ");


        // TC15: Ray starts inside (1 points)
        assertEquals(1,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 2, 0),
                        new Vector(-1, 1.4, 0))).size(),
                "Ray's line starts at sphere and goes outside ");


        // TC16: Ray starts at the center (1 points)
//        assertEquals(1,
//                sphere1.findIntersections(new Ray(
//                        new Point3D(0, 1.5, 0),
//                        new Vector(0.5, 1.5, 0))).size(),
//                "Ray's line starts at sphere and goes outside ");


        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.5, 0),
                        new Vector(-2, -1.5, 0))),
                "Ray's line starts at sphere and goes outside ");


        // TC18: Ray starts after sphere (0 points)
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.2, 0),
                        new Vector(-1, 0.3, 0))),
                "Ray's line starts at sphere and goes outside ");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(0.5, 0.5, 0),
                        new Vector(-2, 0, 0))),
                "Ray's line starts at sphere and goes outside ");


        // TC20: Ray starts at the tangent point
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.5, 0),
                        new Vector(-1, 0, 0))),
                "Ray's line starts at sphere and goes outside ");


        // TC21: Ray starts after the tangent point
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(-0.5, 0.5, 0),
                        new Vector(-1, 0, 0))),
                "Ray's line starts at sphere and goes outside ");


        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals(null,
                sphere1.findIntersections(new Ray(
                        new Point3D(0, 0.5, 0),
                        new Vector(-1, 0, 0))),
                "Ray's line starts at sphere and goes outside ");


    }
}