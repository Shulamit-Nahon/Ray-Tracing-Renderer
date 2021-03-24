package primitives;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Vectors
 */
class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(0, 3, -2);
    Vector v3 = new Vector(-2, -4, -6);
    Point3D p1 = new Point3D(1, 2, 3);

    @Test
    void add() {
        //test add
        Vector u=v1.add(v2);
        assertEquals(u,new Vector(1d,5d,1d),"ERROR: Vector + Vector does not work correctly");
        // Test add with points and vectors
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            out.println("ERROR: Point + Vector does not work correctly");
    }

    @Test
    void substract() {
        //test substract
        Vector u=v1.substract(v2);
        assertEquals(u,new Vector(1d,-1d,5d),"ERROR: Vector - Vector does not work correctly");

    }

    @Test
    void scale() {
        //test scale
        Vector u=v1.scale(2);
        assertEquals(u,new Vector(2d,4d,6d),"ERROR: Vector * scale does not work correctly");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void CrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals( v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue( isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors

        //  assertThrows("crossProduct() for parallel vectors does not throw an exception",
        // IllegalArgumentException.class, () -> v1.crossProduct(v3));
        try {
            v1.crossProduct(v3);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (IllegalArgumentException e) {}
    }



    @Test
    void dotProduct() {
        // test Dot-Product
        if (!isZero(v1.dotProduct(v2)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v3) + 28))
            out.println("ERROR: dotProduct() wrong value");
    }

    @Test
    void lengthSquared() {
        // if (!isZero(v1.lengthSquared() - 14))
        //fail("ERROR: lengthSquared() wrong value");
        double result=v1.lengthSquared();
        assertTrue(isZero(result-14),"ERROR: lengthSquared() wrong value");
    }

    @Test
    void length() {
        // test length..
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            fail("ERROR: length() wrong value");
    }

    @Test
    void normalize() {
        //test normalize
        Vector vCopy = new Vector(v1.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            fail("ERROR: normalize() function creates a new vector");
    }

    @Test
    void normalized() {
        //test normalized
        Vector u = v1.normalized();
        if (u == v1)
            fail("ERROR: normalizated() function does not create a new vector");
    }
}