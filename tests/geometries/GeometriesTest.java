package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {


    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {

        Geometries g = new Geometries();
        Ray ray = new Ray(new Point3D(0d, 4d, 4d), new Vector(-1d, 0.33d, 0d));

        // =5=========== Equivalence Partitions Tests ==============

        // **** Group: Some (but not all) shapes are cut

        // TC01: Checks if there are indeed 3 points of intersection, one with the plane and another two with the triangle (the sphere has no points of intersection)
        assertEquals(3, g.findIntersections(ray).size(), "ERROR:There should be 3 points of intersection, one with the plane and another two with the triangle");
        Intersectable intersectable3 = new Polygon(
                new Point3D[]{new Point3D(-2d, 5d, 2d), new Point3D(0d, 3d, 4d), new Point3D(0d, 3d, 2d), new Point3D(-1.5d, 4.5d, 0d)});
        g.add(intersectable3);//We added to the collection A polygon with 4 vertices that has one intersection point with the ray at one point


        // TC02: Now all the geometries (triangle, plane, polygon) are cut with the ray except the sphere
        assertEquals(3, g.findIntersections(ray).size(), "ERROR:There should be 3 points of intersection, one with the plane(that also the same point with the polygon)" +
                " and another two with the triangle");


        // =============== Boundary Values Tests ==================

        // TC11: Check if the body collection is empty (BVA), then need to return null
        assertEquals(null, g.findIntersections(ray), "ERROR: empty Geometries do not have Intersections ");
        Intersectable intersectable = new Sphere(2d, new Point3D(-5d, 0d, 0d));
        g.add(intersectable); // adds the sphere to the collection of geometry


        // TC12: check if non of the geometries has Intersections with the ra - No cut shape (BVA)
        assertEquals(null, g.findIntersections(ray), "ERROR: no geometry has Intersections with the ray");
        Intersectable intersectable1 = new Plane(new Point3D(3d, 0d, 0d), new Vector(1, 1, 1));
        g.add(intersectable1);


        // TC13: Check if there is indeed only one intersection point between the plane and the ray (Only one shape is cut) (BVA)
        assertEquals(1, g.findIntersections(ray).size(), "ERROR: there is one Intersections between the plane and the ray");
        Intersectable intersectable2 = new Triangle(new Point3D(0d, 3d, 0d), new Point3D(-5d, 5d, 0d), new Point3D(-2d, -3d, 0d));
        g.add(intersectable2); //The collection now consists of three geometries: sphere, triangle, and plane


        //same geometries with out the sphere, now all the geometris has intersection with the ray
        Geometries g1 = new Geometries();
        g1.add(intersectable1);
        g1.add(intersectable2);
        g1.add(intersectable2);

        // TC14: All shapes are cut (BVA)
        assertEquals(3, g.findIntersections(ray).size(), "ERROR: aAll shapes are cut with the ray, 3 points in total ");
    }
}