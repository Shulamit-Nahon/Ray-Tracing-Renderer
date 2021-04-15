package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tubes
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}
     */
    @Test
    void getNormal() {

        Tube tube = new Tube(5d,new Ray(new Point3D(3d,-2d,0d),new Vector(2d, 2d, 0d))); //creates first tube
        Tube tube2 = new Tube(5d,new Ray(new Point3D(3d,-2d,0d),new Vector(2d, 0d, 0d))); //creates second tube

        // ============ Equivalence Partitions Tests ==============
        //TC01: Point on the cylinder shell
        assertEquals(new Vector(0,0,1),tube.getNormal(new Point3D(5d,0d,5d)),
                "ERROR - TC01: The actual normal is incorrect");

        //TC02: Point outside the cylinder
        assertEquals(new Vector(0,0,-1),tube.getNormal(new Point3D(1d,-4d,-5d)),
                "ERROR - TC02: The actual normal is incorrect");

        // =============== Boundary Values Tests ==================
        //TC10:
        assertEquals(new Vector(0,1,0),tube2.getNormal(new Point3D(3d,3d,0d)),
                "ERROR - TC03: The actual normal is incorrect");
    }
}