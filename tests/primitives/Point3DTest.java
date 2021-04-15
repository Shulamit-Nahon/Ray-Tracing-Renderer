package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Points3D
 */
class Point3DTest {

    // ============ Equivalence Partitions Tests ==============

    Point3D p1=new Point3D(1d,0d,1d);
    Point3D p2=new Point3D(1d,0d,-1d);

    /**
     * Test method for {@link primitives.Point3D#subtract(Point3D)}.
     */
    @Test
    void subtract() {
        assertEquals(new Vector(new Point3D(0d,0d,2d)),p1.subtract(p2),
                "ERROR: The function subtract between two point is incorrect");
    }

    /**
     * Test method for {@link primitives.Point3D#distanceSquared(Point3D)}.
     */
    @Test
    void distanceSquared() {
        assertEquals(4d,p1.distanceSquared(p2),
                "ERROR: The function distanceSquared is incorrect ");
    }

    /**
     * Test method for {@link primitives.Point3D#distance(Point3D)}.
     */
    @Test
    void distance() {
        assertEquals(2d,p1.distance(p2),
                "ERROR: The function distance is incorrect ");
    }

    /**
     * Test method for {@link primitives.Point3D#add(Vector)}.
     */
    @Test
    void add() {
        assertEquals(new Point3D(2d,0d,0d),p1.add(new Vector(new Point3D(1d,0d,-1d))),
                "ERROR: The function add is incorrect");
    }
}