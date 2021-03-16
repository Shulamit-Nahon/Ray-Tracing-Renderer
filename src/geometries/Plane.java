package geometries;

import primitives.*;

import java.util.List;


public class Plane implements Geometry{
    Point3D q0;
    Vector normal;

    public Point3D getQ0() {
        return q0;
    }

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


    public Plane(Point3D p1,Point3D p2, Point3D p3){
//        Vector V = new Vector(p1, p2, p3); ////// קצת הסתבכתי
    }

    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}