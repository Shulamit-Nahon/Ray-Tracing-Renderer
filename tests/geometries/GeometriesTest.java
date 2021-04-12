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
        assertEquals(null,g.findIntersections(ray),"ERROR: empty Geometries do not have Intersections ");

    }
}