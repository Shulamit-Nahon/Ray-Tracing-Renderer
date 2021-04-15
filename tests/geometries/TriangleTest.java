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
    void testNormal(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr=new Triangle(new Point3D(0,0,1),new Point3D(1,1,1),new Point3D(1,0,1));
        assertEquals(new Vector(0d,0d,-1d), tr.getNormal(null),"ERROR: the actual normal is incorrect");
    }

    @Test
    void findIntersections() {

        Triangle triangle=new Triangle(new Point3D(-3d,0d,0d), new Point3D(-1d,0d,0d),new Point3D(-4d,5d,0d));
        //EP: Three cases:
        //• Inside triangle
        Ray ray=new Ray(new Point3D(0d,0d,3d),new Vector(-3,2,-3));
        assertEquals(List.of(new Point3D(-3d,2d,0d)),triangle.findIntersections(ray),"ERROR: the intersection point is (-3,2,-3) ");
        //• Outside against edge
        Ray ray1=new Ray(new Point3D(0d,0d,4d),new Vector(2,3,-4));
        assertEquals(null,triangle.findIntersections(ray1),"ERROR:the ray outside the triangle against edge");
        //• Outside against vertex
        Ray ray2=new Ray(new Point3D(0d,0d,4d),new Vector(-3,0,-3));
        assertEquals(null,triangle.findIntersections(ray2),"ERROR:the ray outside the triangle against vertex ");

        //BVA: Three cases (the ray begins
        //"before" the plane)
        //• On edge
        //• In vertex
        //• On edge's continuation
    }
}

