package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
        Sphere sphere=new Sphere(12.6,new Point3D(1d,2d,-6d));
        assertEquals(new Vector(0,0d,1d), sphere.getNormal(new Point3D(1d,2d,5d)),"ERROR: the actual normal is incorrect");
    }
}