package geometries;

import primitives.*;

import java.util.List;

/**
 * Sphere class
 * defined by the main center point and radius of the sphere
 */
public class Sphere implements Geometry {
    /**
     * center point of Sphere
     */
    Point3D cenetr;
    /**
     * radius of  Sphere
     */
    double radius;

    /**
     * @return center point of Sphere
     */
    public Point3D getCenetr() {
        return cenetr;
    }
    /**
     * @return radius of  Sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * constructor for sphere
     * @param cenetr  Point3D for center point of Sphere
     * @param radius value for radius of  Sphere
     */
    public Sphere(double radius,Point3D cenetr) {
        this.cenetr = cenetr;
        this.radius = radius;
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
