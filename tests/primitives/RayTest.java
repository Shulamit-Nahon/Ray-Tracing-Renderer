package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {

        Ray ray = new Ray(Point3D.ZERO, new Vector(0,0,1));
        List<Point3D> point3DList = new LinkedList<>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: The point in the middle of the list is the closest to the beginning of the ray
        point3DList.add(new Point3D(-1000,90,100));
        point3DList.add(new Point3D(50,48,1000));
        point3DList.add(new Point3D(0,.5,1));
        point3DList.add(new Point3D(-20,60,50));
        point3DList.add(new Point3D(0,0,-70));
        assertEquals(point3DList.get(2),ray.findClosestPoint(point3DList));


        // =============== Boundary Values Tests ====================

        // TC10: Empty list (method should return null value)
        assertNull(ray.findClosestPoint(null), "supposed to be null");

        // TC11: The first point is closest to the beginning of the foundation
        point3DList.add(new Point3D(0,.5,1));
        point3DList.add(new Point3D(-1000,90,100));
        point3DList.add(new Point3D(50,48,1000));
        assertEquals(point3DList.get(2),ray.findClosestPoint(point3DList));

        // TC12: The last point is closest to the beginning of the foundation

    }
}