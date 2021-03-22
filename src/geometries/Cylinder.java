package geometries;

import primitives.*;
import java.util.List;

/**
 * A cylinder class inherits from TUBE
 * defined by its height
 */
public class Cylinder extends Tube {
/**
 * cylinder height value, radius and ray
 */
    final double height;

    /**
     * @return  cylinder height value
     */
    public double getHeight() {
        return height;
    }

    /**
     * construct for cylinder
     * @param axisRay Ray for The tube ray
     * @param radius double for Tube base radius value
     * @param height double cylinder height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(radius,axisRay);
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     *implementation of get normal from geometry interface
     * @param point Point3D, reference point
     * @return normal of the Cylinder(Heir from Tube)
     */
    @Override
    public Vector getNormal(Point3D point) {

        return getNormal(point);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
