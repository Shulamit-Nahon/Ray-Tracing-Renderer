package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {
;
    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {

        Geometries g=new Geometries();
        Ray ray=new Ray(new Point3D(0d,4d,4d),new Vector(-1d,0.33d,0d));

        // ============ Equivalence Partitions Tests ==============

        // ** Group: Some (but not all) shapes are cut

        // TC01: Checks if there are indeed 3 points of intersection
        Geometries g1=new Geometries();
        g1.intersectables.add(new Triangle(new Point3D(-2, 0, 0), new Point3D(0, -4, 0), new Point3D(2, 0, 0)));
        g1.intersectables.add(new Plane(new Point3D(0, 0, 6), new Point3D(-8, 0, 0),new Point3D(0, 6, 0)));
        g1.intersectables.add(new Sphere(1,new Point3D(0, 0, 2)));
        Ray ray1=new Ray(new Point3D(0d,0d,4d),new Vector(0, -2, -5));
        assertEquals(3,g1.findIntersections(ray1).size(),"ERROR:There should be 3 points of intersection");

        // =============== Boundary Values Tests ==================

        // TC11: Check if the body collection is empty (BVA), then need to return null - Empty body collection (BVA)
        assertEquals(null,g.findIntersections(ray),"ERROR: empty Geometries do not have Intersections ");

        Intersectable intersectable= new Sphere(2d,new Point3D(-5d,0d,0d));
        g.add(intersectable); //We added sphere to the collection of geometry

        // TC12: Check if non of the geometries has Intersections with the ray - No cut shape (BVA)
        assertEquals(null,g.findIntersections(ray),"ERROR: no geometry has Intersections with the ray");

        Intersectable intersectable1=new Plane(new Point3D(3d,0d,0d),new Vector(1,1,1));
        g.add(intersectable1);

        // TC13: Check if there is indeed only one intersection point between the plane and the ray - Only one shape is cut (BVA)
        assertEquals(1,g.findIntersections(ray).size(),"ERROR: there is one Intersections between the plane and the ray");

        // TC14: All shapes are cut (BVA)
        Ray ray2=new Ray(new Point3D(0.05d,-2.5d,-1d),new Vector(-0.05, 4.5, 5));
        assertEquals(4,g1.findIntersections(ray2).size(),"ERROR: aAll shapes are cut with the ray, 4 points in total ");

    }
}
