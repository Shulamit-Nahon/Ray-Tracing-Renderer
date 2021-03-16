package geometries;

import primitives.*;

import java.util.List;

/**
 * class plane
 * Defined by a point in space and a vector perpendicular to the plane
 */
public class Plane implements Geometry{
    /**
     * point in the space of the plain
     */
    final Point3D q0;
    /**
     * Vector vertical to the plane
     */
    final Vector normal;

    /**
     * @returnA point in the space of the plain
     */
    public Point3D getQ0() {

        return q0;
    }

    /**
     * @return Vector vertical to the plane
     */
    public Vector getNormal() {
        return normal;
    }


    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    public Vector getNormal(Point3D point){ return null; }

    /**
     * plane constructor receiving 3 point3D
     * Creates from three points3D two vectors and a vertical vector for both
     * @param p1 Point3D
     * @param p2 Point3D
     * @param p3 Point3D
     */
    public Plane(Point3D p1,Point3D p2, Point3D p3){
        q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector V2 = p3.subtract(p1);
        Vector v3 = v1.crossProduct(V2);
        v3.normalize();
        normal = v3;
    }

    /**
     * constructor for Plane
     * @param q0 for point in the space of the plain
     * @param normal for Vector vertical to the plane
     */
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalized();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}