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
        Point3D p1 = new Point3D(0,0,2);
        Point3D p2 = new Point3D(0,0,3);
        Point3D p3 = new Point3D(0,0,4);

        // ============ Equivalence Partitions Tests ==============

        // TC01: The point in the middle of the list is the closest to the beginning of the ray
        point3DList.add(p2);
        point3DList.add(p1);
        point3DList.add(p3);
        assertEquals(point3DList.get(1),ray.findClosestPoint(point3DList));


        // =============== Boundary Values Tests ====================

        // TC10: Empty list (method should return null value)
        assertNull(ray.findClosestPoint(null), "supposed to be null");

        // TC11: The first point is closest to the beginning of the foundation
        point3DList = new LinkedList<>();
        point3DList.add(p1);
        point3DList.add(p2);
        point3DList.add(p3);
        assertEquals(point3DList.get(0),ray.findClosestPoint(point3DList));

        // TC12: The last point is closest to the beginning of the foundation
        point3DList = new LinkedList<>();
        point3DList.add(p3);
        point3DList.add(p2);
        point3DList.add(p1);
        assertEquals(point3DList.get(2),ray.findClosestPoint(point3DList));

    }
}