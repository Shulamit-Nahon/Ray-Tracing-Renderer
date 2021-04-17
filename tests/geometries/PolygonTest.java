package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Polygons
 */
public class PolygonTest {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(Point3D...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }

    @Test
    void findIntersections() {
       Polygon polygon=new Polygon(new Point3D(-4d,0d,0d),new Point3D(-3d,0d,0d),new Point3D(0d,-4d,0d),new Point3D(0d,-6d,0d));
        // ============ Equivalence Partitions Tests ==============

        //TC01: Ray's line is inside the polygon
        Ray ray=new Ray(new Point3D(0d,0d,1d),new Vector(-0.61,-1.6,-0.5));
        assertEquals(List.of(new Point3D(-1.22d,-3.2d,0d)),polygon.findIntersections(ray),"ERROR: ray cut the polygon at (-1,-3.71,0) point");
        //TC02: Ray's line is outside the polygon against the edge
        Ray ray1=new Ray(new Point3D(0d,0d,3d),new Vector(-0.2,-0.46,-1));
        assertEquals(null,polygon.findIntersections(ray1),"ERROR:ray outside the polygon against edge ");
        //TC03: Ray's line is outside the polygon against the vertex
        Ray ray2=new Ray(new Point3D(0d,0d,4d),new Vector(-1,0,-2));
        assertEquals(null,polygon.findIntersections(ray2),"ERROR: ray outside the polygon against vertex");

        //BVA: Three cases (the ray begins
        //"before" the plane)
        //• On edge
        //• In vertex
        //• On edge's continuation

        //// =============== Boundary Values Tests ==================
        //
        //        // **** Group: Ray's line begins before the plane
    }
}
