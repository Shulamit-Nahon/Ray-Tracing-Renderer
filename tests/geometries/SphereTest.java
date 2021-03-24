package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
      Sphere sphere=new Sphere(12.6,new Point3D(1d,2d,-6d));
        assertEquals(new Vector(0,0d,1d), sphere.getNormal(new Point3D(1d,2d,5d)),"ERROR: the actual normal is incorrect");
    }
}