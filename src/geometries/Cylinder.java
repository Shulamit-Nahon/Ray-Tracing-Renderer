package geometries;

import primitives.*;

import java.util.List;

public class Cylinder extends Tube {

    double height;

    public double getHeight() {
        return height;
    }

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
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

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
