package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        try {
            Plane plane = new Plane(
                    new Point3D(1d, 1d, 1d),
                    new Point3D(2d, 2d, 2d),
                    new Point3D(-3d, 3d, 3d));
            assertEquals(new Vector(0d, -0.7071067811865d, 0.7071067811865d), plane.getNormal(null), "ERROR: the actual normal is incorrect");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findIntersections() {
    }
}