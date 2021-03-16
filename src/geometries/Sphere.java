package geometries;

import primitives.*;

import java.util.List;

public class Sphere implements Geometry {
    Point3D cenetr;
    double radius;


    public Point3D getCenetr() {
        return cenetr;
    }

    public double getRadius() {
        return radius;
    }

    public Sphere(Point3D cenetr, double radius) {
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
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
