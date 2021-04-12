package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
    }

    @Test
    void findIntersections() {

        Geometries g=new Geometries();
        Ray ray=new Ray(new Point3D(1d,1d,1d),new Vector(1d,1d,2d));
        //Check if the body collection is empty (BVA), then need to return null
        assertEquals(null,g.findIntersections(ray),"ERROR: empty Geometries do not have Intersections ");
        Intersectable intersectable= new Sphere(1d,new Point3D(2d,3d,-4d));
        g.add(intersectable);//We added sphere to the collection of geometry   פה הפסקתי, קצת הסתבתכתי....

    }
}