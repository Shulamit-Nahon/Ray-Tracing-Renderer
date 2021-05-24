package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Tube class represented by radius and ray
 */
public class Tube extends RadialGeometry  {

    final Ray axisRay; //Tube ray


    /**
     * @return The tube ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * constructor for Tube
     *
     * @param axisRay for The tube ray
     * @param radius  for Tube base radius
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * returns the tube normal
     *
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point3D p) {
        Point3D p0 = axisRay.getpOrigin();
        Vector v = axisRay.getDirection();
        Vector p0_p = p.subtract(p0);
        double t = v.dotProduct(p0_p);
        if (isZero(t))
            return p0_p.normalize();
        Point3D O = p0.add(v.scale(t));
        Vector n = p.subtract(O);
        return n.normalize();
    }

   // @Override
  //  public List<Point3D> findIntersections(Ray ray) {
  //      return null;
 //   }
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        return null;
    }
}
