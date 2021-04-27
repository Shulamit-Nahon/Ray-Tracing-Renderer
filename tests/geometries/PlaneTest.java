package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        try {
            Plane plane = new Plane(
                    new Point3D(1d, 1d, 1d),
                    new Point3D(2d, 2d, 2d),
                    new Point3D(-3d, 3d, 3d));
            assertEquals(new Vector(0d, -0.7071067811865d, 0.7071067811865d), plane.getNormal(null), "ERROR: the actual normal is incorrect");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {

        Point3D q0 = new Point3D(-2d, 0d, 0d);
        Plane plane = new Plane(q0, new Vector(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray intersects the plane
        Ray ray2 = new Ray(new Point3D(0d, -4d, 0d), new Vector(0, 2, 1.5));
        assertEquals(1, plane.findIntersections(ray2).size(),
                "ERROR - TC01: Ray intersects the plane");

        //TC02: Ray does not intersect the plane
        Ray ray3 = new Ray(new Point3D(0d, 3d, 0d), new Vector(-2, 0.7, 0));
        assertEquals(null, plane.findIntersections(ray3)
                , "ERROR - TC02: Ray does not intersect the plane");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane (Two cases – the ray included or not included in the plane)

        //TC11: The ray does not included in the plane
        Ray ray1 = new Ray(new Point3D(0d, 3d, 0d), new Vector(-3, 0, 0));
        //check if "findIntersections" returns null because of the ineligible Zero in denominator
        assertEquals(null, plane.findIntersections(ray1),
                "ERROR - TC11: Ray is parallel to the plane, the ray not included in the plane");

        //TC12: The ray included in the plane
        Ray ray4 = new Ray(new Point3D(2d, 0d, 0d), new Vector(-2, 0, 1));
        assertEquals(null, plane.findIntersections(ray4),
                "ERROR - TC12: Ray included in the plane-the ray included in the plane");


        // **** Group: Ray is orthogonal to the plane (Three cases – according to 𝑃0 (before, in, after the plane)

        //TC13: Ray starts after the plane
        Ray ray5 = new Ray(new Point3D(0d, 3d, 0d), new Vector(0, 2, 0));
        assertEquals(null, plane.findIntersections(ray5),
                "ERROR - TC13: Ray is orthogonal to the plane (after the plane)");

        //TC14: Ray starts before the plane
        Ray ray6 = new Ray(new Point3D(0d, -4d, 0d), new Vector(0, 2, 0));
        assertEquals(null, plane.findIntersections(ray6),
                "ERROR - TC14: Ray is orthogonal to the plane (before the plane)");

        //TC15: Ray starts in the plane
        Ray ray7 = new Ray(new Point3D(4d, 0d, 0d), new Vector(0, 2, 0));
        assertEquals(null, plane.findIntersections(ray6),
                "ERROR - TC15: Ray is orthogonal to the plane (in the plane)");

        //**** Group: Ray is neither orthogonal nor parallel to and begins at the plane

        //TC16: 𝑃0 is in the plane, but not the ray
        Ray ray8 = new Ray(new Point3D(4d, 0d, 0d), new Vector(-4, -2, 0));
        assertEquals(null, plane.findIntersections(ray8),
                "ERROR - TC16: p0 is in the plane, but not the ray");

        //TC17: The same point which appears as reference point in the plane (Q)
        Ray ray = new Ray(q0, new Vector(-1, 1.5, 0));
        assertEquals(null, plane.findIntersections(ray),
                "ERROR - TC17: Q0=P0 "); //Check 𝑄0 = 𝑃0

    }
}