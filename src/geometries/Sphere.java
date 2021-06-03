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


//    @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
//        //calculating varieables for the final formula
//        Point3D P0 = ray.getpOrigin();
//        Vector V = ray.getDirection();
//        Point3D O = cenetr;
//        if (P0.equals(cenetr)) {
//            return List.of(new GeoPoint(this, cenetr.add(V.scale(radius))));
//        }
//        Vector U = O.subtract(P0);
//        double tm = V.dotProduct(U);
//        double d = alignZero(Math.sqrt(alignZero(U.lengthSquared() - tm * tm)));
//        //there is no intersaction
//        //
//        if (d >= radius) {
//            return null;
//        }
//        double th = alignZero(Math.sqrt(alignZero(radius * radius - d * d)));
//        double t1 = alignZero(tm - th);
//        double t2 = alignZero(tm + th);
//        // if P is on the surface---
//        if (isZero(th)) {
//            return null;
//        }
//
//        //in case of 2 intersaction points
//        if ((t1 > 0 && t2 > 0) && (alignZero(t1 - maxDistance) <= 0) && (alignZero(t2 - maxDistance) <= 0)) {
////            //the first point and the second point
//            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)), new GeoPoint(this, ray.getTargetPoint(t2)));
//        }
//        //in case of 1 intersaction points
//        if (t1 > 0 && (alignZero(t1 - maxDistance) <= 0)) {
//
//            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)));
//        }
//        //in case of 1 intersaction points
//        if (t2 > 0 && (alignZero(t2 - maxDistance) <= 0)) {
//
//            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
//        }
//        return null;
//
//    }
//


    /**
     * find Geometry Intersections
     * @param ray
     * @return Geometry Intersections points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        if (ray.getpOrigin().equals(center)) {
            return List.of(new GeoPoint(this,ray.getTargetPoint(radius)));////
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
    public void findMinMaxForBounding() {
        Geometries boundingGeo=new Geometries();
        boundingGeo.add(new Polygon(
                new Point3D(center.getX()+radius,center.getY(),center.getZ()+radius),
                new Point3D(center.getX()+radius,center.getY(),center.getZ()-radius),
                new Point3D(center.getX(), center.getY()+radius,center.getZ()+radius),
                new Point3D(center.getX(), center.getY()+radius,center.getZ()-radius)));
}

    /**
     * A function that finds whether there are intersections with the boundary that warmed the bodies
     * @param r
     * @return
     */
    public boolean intersect(Ray r){
        double rayPoX=r.getpOrigin().getX();
        double rayPoY=r.getpOrigin().getY();
        double rayPoZ=r.getpOrigin().getZ();
        double tmin = (minX - rayPoX) / r.getDirection().getHead().getX();
        double tmax = (maxX - rayPoX) / r.getDirection().getHead().getX();
        if (tmin > tmax){
            //swap(tmin, tmax);
            double temp=tmin;
            tmin=tmax;
            tmax=temp;
        }

        double tymin = (minY - rayPoY) / r.getDirection().getHead().getY();
        double tymax = (maxY -rayPoY) /  r.getDirection().getHead().getY();

        if (tymin > tymax){
            //swap(tymin, tymax);
            double temp=tymin;
            tymin=tymax;
            tymax=temp;
        }

        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;

        if (tymax < tmax)
            tmax = tymax;

        double tzmin = (minZ -rayPoZ) / r.getDirection().getHead().getZ();
        double tzmax = (maxZ - rayPoZ) /  r.getDirection().getHead().getZ();

        if (tzmin > tzmax){
            //swap(tzmin, tzmax);
            double temp=tzmin;
            tzmin=tzmax;
            tzmax=temp;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;

        if (tzmin > tmin)
            tmin = tzmin;

        if (tzmax < tmax)
            tmax = tzmax;

        return true;
    }

}
