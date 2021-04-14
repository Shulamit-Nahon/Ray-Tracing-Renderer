package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class plane
 * Defined by a point in space and a vector perpendicular to the plane
 */
public class Plane implements Geometry {

    final Point3D q0; //point in the space of the plain

    final Vector normal; //Vector vertical to the plane

    /**
     * plane constructor receiving 3 point3D
     * Creates from three points3D two vectors and a vertical vector for both
     *
     * @param p1 Point3D
     * @param p2 Point3D
     * @param p3 Point3D
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3))
            throw new IllegalArgumentException("two points can not be similiar");
        q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector V2 = p3.subtract(p1);
        Vector v3 = v1.crossProduct(V2);
        v3.normalize();
        normal = v3;
    }

    /**
     * constructor for Plane
     *
     * @param q0     for point in the space of the plain
     * @param normal for Vector vertical to the plane
     */
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalized();
    }

    /**
     * @return point in the space of the plain
     */
    public Point3D getQ0() {

        return q0;
    }

    /**
     * getter of the normal vector filed
     *
     * @return normal Vector  to the plane
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

    /**
     * implementation of get normal from geometry interface
     *
     * @param point Point3D, reference point
     * @return normal of the tube
     */
    @Override
    public Vector getNormal(Point3D point) {
        return normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getpOrigin();
        Vector v = ray.getDirection();

        Vector n = normal;

        if(q0.equals(P0)){
            return  null;
        }
       // Ray is orthogonal to the plane
        if(v.normalized().equals(n.normalized())){
            return null;
        }
        Vector P0_Q0 = q0.subtract(P0);

        double mechane = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(mechane)){
            return null;
        }

        //mone
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(mechane / nv);

        if (t <=0){
            return  null;
        }
        Point3D P = ray.getTargetPoint(t);

        return List.of(P);
    }

}