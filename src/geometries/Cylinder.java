package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * A cylinder class inherits from TUBE
 * defined by its height
 */
public class Cylinder extends Tube {

    final double height; //cylinder height value, radius and ray
    Plane base1;
    Plane base2;
    double rad;//The width(rad*2) of the Cylinder

    /**
     * @return cylinder height value
     */
    public double getHeight() {
        return height;
    }

    /**
     * construct for cylinder
     *
     * @param axisRay Ray for The tube ray
     * @param radius  double for Tube base radius value
     * @param height  double cylinder height value
     */
//    public Cylinder(Ray axisRay, double radius, double height) {
//        super(radius, axisRay);
//        this.height = height;
//    }

    /**
     * ctor for: Tube with limited height
     *
     * @param ray    the start of the Cylinder
     * @param rad    The width(rad*2) of the Cylinder
     * @param height the height of the Cylinder
     */
    public Cylinder(Ray ray, double rad, double height) {
        super(rad, ray);
        this.rad = rad;
        this.height = height;
        Vector v = ray.getDirection();
        Point3D o1 = ray.getpOrigin();
        Point3D o2 = ray.getTargetPoint(height);
        base1 = new Plane(o1, v);
        base2 = new Plane(o2, v);

        border=findMinMaxForBounding();
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
     * implementation of get normal from geometry interface
     *
     * @param point3D Point3D, reference point
     * @return normal of the Cylinder(Heir from Tube)
     */
    @Override
    public Vector getNormal(Point3D point3D) {

        Point3D o = axisRay.getpOrigin();//at this point o = p0
        Vector v = axisRay.getDirection();//v == dir

        //The vector from the point of the cylinder to the given point
        Vector vector1 = point3D.subtract(o);

        /* Based on the plane equation (Ax + By + Cz = d) Calculate the sliding vector value.
        According to this sliding vector we will check if the given point is on the planes that block the cylinder from its 2 ends (d or d+height).
        then the normal vector will be a dir vector (and so also in boundary cases).
        If the given point is not on planes then it will necessarily be across the round cylinder shell,
        and the normal will be an orthogonal vector to dir */
        double d = alignZero(-1d * (v.getHead().getX() * o.getX() + v.getHead().getY() * o.getY() + v.getHead().getZ() * o.getZ()));

        //we need the projection to multiply the direction until unit vector
        double projection = alignZero(vector1.dotProduct(v));
        // Check that the point is not outside the cylinder
        if (!(projection <= 0) && (projection <= height)) {
            //projection of p0 on the ray:
            o = o.add(v.scale(projection));
        }

        // sliding vector of the point given
        double DGiven = alignZero(-1d * (v.getHead().getX() * point3D.getX() +
                v.getHead().getY() * point3D.getY() + v.getHead().getZ() * point3D.getZ()));

        // ============ Equivalence Partitions Tests ==============
        if (DGiven == d || DGiven == d + height) {
            return v.normalized();
        }
        //this vector is orthogonal to the dir vector
        Vector check = point3D.subtract(o);
        return check.normalize();
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        GeoPoint p1 = null;
        GeoPoint p2 = null;
        Point3D o1 = base1.getQ0();
        var ints = base1.findGeoIntersections(ray, maxDistance);
        if (ints != null) {
            GeoPoint p = ints.get(0);
            if (alignZero(o1.distance(p.point) - radius) <= 0)
                p1 = p;
        }
        Point3D o2 = base2.getQ0();
        ints = base2.findGeoIntersections(ray, maxDistance);
        if (ints != null) {
            GeoPoint p = ints.get(0);
            if (alignZero(o2.distance(p.point) - radius) <= 0)
                p2 = p;
        }
        if (p1 != null && p2 != null) {
            p1.geometry = this;
            p2.geometry = this;
            return List.of(p1, p2);
        }

        List<GeoPoint> pointIntersectTube = super.findGeoIntersections(ray, maxDistance);
        if (pointIntersectTube == null) {
            if (p1 != null) {
                p1.geometry = this;
                return List.of(p1);
            }
            if (p2 != null) {
                p2.geometry = this;
                return List.of(p2);
            }
            return null;
        }

        Vector v = axisRay.getDirection();

        if (p1 == null && p2 == null) {
            // bases are not intersected
            // therefore all tube intersections - either all inside or all outside
            for (GeoPoint p : pointIntersectTube) {
                double distanceFromLowBase = alignZero(p.point.subtract(o1).dotProduct(v));
                if (distanceFromLowBase <= 0 || alignZero(distanceFromLowBase - height) >= 0)
                    return null;
            }
            for (GeoPoint geoPoint : pointIntersectTube) {
                geoPoint.geometry = this;
            }
            return pointIntersectTube;
        }

        // if we are here - one base is intersected
        List<GeoPoint> resultList = new LinkedList<>(List.of(p1 == null ? p2 : p1));
        for (GeoPoint p : pointIntersectTube) {
            double distanceFromLowBase = alignZero(p.point.subtract(o1).dotProduct(v));
            if (distanceFromLowBase > 0 && alignZero(distanceFromLowBase - height) < 0)
                resultList.add(p);
        }
        resultList.get(0).geometry = this;
        return resultList;
    }

    /**
     * find 6 minimum and maximum value of the cylinder
     */
    public Border findMinMaxForBounding() {

        Point3D bottomCapCenter = axisRay.getpOrigin();
        Point3D upperCapCenter = axisRay.getTargetPoint(height);

        //get minimum & maximum x value for the containing box
        double minX = Math.min(bottomCapCenter.getX(), upperCapCenter.getX()) - radius;
        double maxX = Math.max(bottomCapCenter.getX(), upperCapCenter.getX()) + radius;

        //get minimum & maximum y value for the containing box
        double minY = Math.min(bottomCapCenter.getY(), upperCapCenter.getY()) - radius;
        double maxY = Math.max(bottomCapCenter.getY(), upperCapCenter.getY()) + radius;

        //get minimum & maximum z value for the containing box
        double minZ = Math.min(bottomCapCenter.getZ(), upperCapCenter.getZ()) - radius;
        double maxZ = Math.max(bottomCapCenter.getZ(), upperCapCenter.getZ()) + radius;

        return new Border(maxX, maxY, maxZ, minX, minY, minZ);
    }
}
