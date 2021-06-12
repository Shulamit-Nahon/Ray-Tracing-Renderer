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

    Point3D center; //center point of Sphere

    /**
     * @return center point of Sphere
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * constructor for sphere
     *
     * @param cenetr Point3D for center point of Sphere
     * @param radius value for radius of  Sphere
     */
    public Sphere(double radius, Point3D cenetr) {
        super(radius);
        this.center = cenetr;

        border = findMinMaxForBounding();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "cenetr=" + center +
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

        if (point.equals(center))
            throw new IllegalArgumentException("ERROR: point equals center");
        Vector v = point.subtract(center);
        return v.normalize();
    }

    /**
     * find Geometry Intersections
     *
     * @param ray
     * @return Geometry Intersections points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        if (ray.getpOrigin().equals(center)) {
            return List.of(new GeoPoint(this, ray.getTargetPoint(radius)));////
        }

        Vector U = center.subtract(ray.getpOrigin());
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

        //in case of 2 intersaction points
        if ((t1 > 0 && t2 > 0) && (alignZero(t1 - maxDistance) <= 0) && (alignZero(t2 - maxDistance) <= 0)) {
//            //the first point and the second point
            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)), new GeoPoint(this, ray.getTargetPoint(t2)));
        }
        //in case of 1 intersaction points
        if (t1 > 0 && (alignZero(t1 - maxDistance) <= 0)) {

            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)));
        }
        //in case of 1 intersaction points
        if (t2 > 0 && (alignZero(t2 - maxDistance) <= 0)) {

            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
        }
        return null;
    }


    /**
     * find 6 minimum and maximum value of the shape
     */
    protected Border findMinMaxForBounding() {
        double maxX = center.getX() + radius;
        double maxY = center.getY() + radius;
        double maxZ = center.getZ() + radius;

        double minX = center.getX() - radius;
        double minY = center.getY() - radius;
        double minZ = center.getZ() - radius;

        return new Border(maxX, maxY, maxZ, minX, minY, minZ);
    }
}
