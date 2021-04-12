package geometries;

import primitives.*;

import java.util.List;

/**
 * Sphere class
 * defined by the main center point and radius of the sphere
 */
public class Sphere extends RadialGeometry implements Geometry {

    Point3D cenetr; //center point of Sphere

    /**
     * @return center point of Sphere
     */
    public Point3D getCenetr() {
        return cenetr;
    }

    /**
     * constructor for sphere
     * @param cenetr  Point3D for center point of Sphere
     * @param radius value for radius of  Sphere
     */
    public Sphere(double radius,Point3D cenetr) {
        super(radius);
        this.cenetr = cenetr;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "cenetr=" + cenetr +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        
        if (point.equals(cenetr))
            throw new IllegalArgumentException("ERROR: point equals center");
        Vector v= point.subtract(cenetr);
        return v.normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
