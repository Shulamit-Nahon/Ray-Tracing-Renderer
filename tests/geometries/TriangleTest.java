package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Triangles
 */
class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
    @Test
    void testNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 1, 1), new Point3D(1, 0, 1));
        assertEquals(new Vector(0d, 0d, -1d), tr.getNormal(null), "ERROR: the actual normal is incorrect");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {

        Triangle triangle = new Triangle(new Point3D(-3d, 0d, 0d), new Point3D(-1d, 0d, 0d), new Point3D(-4d, 5d, 0d));

        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray's line is inside the triangle
        Ray ray = new Ray(new Point3D(0d, 0d, 3d), new Vector(-3, 2, -3));
        assertEquals(List.of(new Point3D(-3d, 2d, 0d)), triangle.findIntersections(ray),
                "ERROR - TC01: the intersection point is (-3,2,-3) ");

        //TC02: Ray's line is outside the triangle against the edge
        Ray ray1 = new Ray(new Point3D(0d, 0d, 4d), new Vector(2, 3, -4));
        assertEquals(null, triangle.findIntersections(ray1),
                "ERROR - TC02: Ray's line is outside the triangle against the edge");

        //TC03: Ray's line is outside the triangle against the vertex
        Ray ray2 = new Ray(new Point3D(0d, 0d, 4d), new Vector(-3, 0, -3));
        assertEquals(null, triangle.findIntersections(ray2),
                "ERROR - TC03: Ray's line is outside the triangle against the vertex");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line begins before the plane
        //TC11: On edge
        Ray ray3 = new Ray(new Point3D(-2d, -5d, 0d), new Vector(-2.1, 10.6, 0));
        assertEquals(null, triangle.findIntersections(ray3),
                "ERROR - TC11: Ray's line begins before the plane on edge ");

        //TC12: In vertex
        Ray ray4 = new Ray(new Point3D(2d, -5d, 0d), new Vector(-6.5, 10.8, 0));
        assertEquals(null, triangle.findIntersections(ray4),
                "ERROR  - TC12: Ray's line begins before the plane in vertex");

        //TC13: On edge's continuation
        Ray ray5 = new Ray(new Point3D(-1d, 0d, 0d), new Vector(-4, 0, 0));
        assertEquals(null, triangle.findIntersections(ray5),
                "ERROR - TC13: Ray's line begins before the plane on edge's continuation");

    }
}

