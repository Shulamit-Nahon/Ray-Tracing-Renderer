package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere class
 * defined by the main center point and radius of the sphere
 */
public class Sphere extends RadialGeometry {

    Point3D cenetr; //center point of Sphere

    /**
     * @return center point of Sphere
     */
    public Point3D getCenetr() {
        return cenetr;
    }

    /**
     * constructor for sphere
     *
     * @param cenetr Point3D for center point of Sphere
     * @param radius value for radius of  Sphere
     */
    public Sphere(double radius, Point3D cenetr) {
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

    /**
     * receives point and calculates the normal
     *
     * @param point Point3D
     * @return the normalize vector
     */
    @Override
    public Vector getNormal(Point3D point) {

        if (point.equals(cenetr))
            throw new IllegalArgumentException("ERROR: point equals center");
        Vector v = point.subtract(cenetr);
        return v.normalize();
    }


    /**
     * find Geometry Intersections
     * @param ray
     * @return Geometry Intersections points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if (ray.getpOrigin().equals(cenetr)) {
            return List.of(new GeoPoint(this,ray.getTargetPoint(radius)));
        }

        Vector U = cenetr.subtract(ray.getpOrigin());
        Vector V = ray.getDirection();
        double tm = U.dotProduct(V);
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));//Math.sqrt(radius*radius-)
        if (d > radius) {
            return null;
        }
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        //if P is on the surface of the sphere
        if (isZero(th)) {
            return null;
        }
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);
        if (t1 > 0 && t2 > 0) {
            return List.of(new GeoPoint(this,ray.getTargetPoint(t1)), new GeoPoint(this,ray.getTargetPoint(t2)));
        }
        if (t1 > 0) {
            return List.of(new GeoPoint(this,ray.getTargetPoint(t1)));
        }
        if (t2 > 0) {
            return List.of(new GeoPoint(this,ray.getTargetPoint(t2)));
        }
        return null;
    }
}
