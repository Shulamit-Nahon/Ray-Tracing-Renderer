package geometries;

import primitives.*;
import java.util.List;

/**
 * Tube class represented by radius and ray
 */
public class Tube implements Geometry {
    /**
     * The tube ray
     */
    final Ray axisRay;
    /**
     *  Tube base radius
     */
    final double radius;

    /**
     * @return The tube ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * @return Tube base radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * constructor for Tube
     * @param axisRay for The tube ray
     * @param radius for Tube base radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
