package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() { }

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {

        Geometries g=new Geometries();
        Ray ray=new Ray(new Point3D(0d,4d,4d),new Vector(-1d,0.33d,0d));

        //Check if the body collection is empty (BVA), then need to return null
        //Empty body collection (BVA)
        assertEquals(null,g.findIntersections(ray),"ERROR: empty Geometries do not have Intersections ");
        Intersectable intersectable= new Sphere(2d,new Point3D(-5d,0d,0d));
        g.add(intersectable);//We added sphere to the collection of geometry
        //check if non of the geometries has Intersections with the ray
        // No cut shape (BVA)
        assertEquals(null,g.findIntersections(ray),"ERROR: no geometry has Intersections with the ray");
        Intersectable intersectable1=new Plane(new Point3D(3d,0d,0d),new Vector(1,1,1));
        g.add(intersectable1);
        //Check if there is indeed only one intersection point between the plane and the ray
        // Only one shape is cut (BVA)
        assertEquals(1,g.findIntersections(ray).size(),"ERROR: there is one Intersections between the plane and the ray");
        Intersectable intersectable2=new Triangle(new Point3D(0d,3d,0d),new Point3D(-5d,5d,0d),new Point3D(-2d,-3d,0d));
        g.add(intersectable2);//The collection now consists of three geometries: sphere, triangle, and plane
        //Checks if there are indeed 3 points of intersection, one with the plane and another two with the triangle (the sphere has no points of intersection)
        // Some (but not all) shapes are cut (EP)1
        assertEquals(3,g.findIntersections(ray).size(),"ERROR:There should be 3 points of intersection, one with the plane and another two with the triangle");
        Intersectable intersectable3=new Polygon(
                new Point3D[]{new Point3D(-2d,5d,2d),new Point3D(0d,3d,4d),new Point3D(0d,3d,2d),new Point3D(-1.5d,4.5d,0d)});
        g.add(intersectable3);//We added to the collection A polygon with 4 vertices that has one intersection point with the ray at one point
        //Now all the geometries (triangle, plane, polygon) are cut with the ray except the sphere
        // Some (but not all) shapes are cut (EP)2
        assertEquals(3,g.findIntersections(ray).size(),"ERROR:There should be 3 points of intersection, one with the plane(that also the same point with the polygon)" +
                " and another two with the triangle");
        //same geometries with out the sphere, now all the geometris has intersection with the ray
        Geometries g1=new Geometries();
        g1.add(intersectable1);
        g1.add(intersectable2);
        g1.add(intersectable2);
        // All shapes are cut (BVA)
        assertEquals(3,g.findIntersections(ray).size(),"ERROR: aAll shapes are cut with the ray, 3 points in total ");
    }
}