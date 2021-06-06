package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
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
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        Vector vAxis = axisRay.getDirection();
        Vector v = ray.getDirection();
        Point3D p0 = ray.getpOrigin();

        // At^2+Bt+C=0
        double a = 0;
        double b = 0;
        double c = 0;

        double vVa = alignZero(v.dotProduct(vAxis));
        Vector vVaVa;
        Vector vMinusVVaVa;
        if (isZero(vVa)) // the ray is orthogonal to the axis
            vMinusVVaVa = v;
        else {
            vVaVa = vAxis.scale(vVa);
            try {
                vMinusVVaVa = v.substract(vVaVa);
            } catch (IllegalArgumentException e1) { // the rays is parallel to axis
                return null;
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();

        Vector deltaP = null;
        try {
            deltaP = p0.subtract(axisRay.getpOrigin());
        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
            if (isZero(vVa)) { // the ray is orthogonal to Axis
                return (alignZero(radius - maxDistance) <= 0) ? List.of(new GeoPoint(this, ray.getTargetPoint(radius)))
                        : null;// for unshaded
            }
            double rSlasVminusV = Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared());
            return (alignZero(rSlasVminusV - maxDistance) <= 0)
                    ? List.of(new GeoPoint(this, ray.getTargetPoint(rSlasVminusV)))
                    : null;// for unshaded
        }

        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        double rrSlasA;
        if (isZero(dPVAxis))
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = vAxis.scale(dPVAxis);
            try {
                dPMinusdPVaVa = deltaP.substract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                rrSlasA = Math.sqrt(radius * radius / a);
                return (alignZero(rrSlasA - maxDistance) <= 0) ? List.of(new GeoPoint(this, ray.getTargetPoint(rrSlasA)))
                        : null;
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        c = dPMinusdPVaVa.lengthSquared() - radius * radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        double discr = alignZero(b * b - 4 * a * c);
        if (discr <= 0)
            return null; // the ray is outside or tangent to the tube

        double doubleA = 2 * a;
        double tm = alignZero(-b / doubleA);
        double th = Math.sqrt(discr) / doubleA;
        if (isZero(th))
            return null; // the ray is tangent to the tube

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th); // always: t2 > t1
        if (t2 <= 0)
            return null;
        if (alignZero(t2 - maxDistance) <= 0)
            return t1 > 0 ? List.of(new GeoPoint(this, ray.getTargetPoint(t1)), new GeoPoint(this, ray.getTargetPoint(t2)))
                    : List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
        return alignZero(t1 - maxDistance) < 0 && t1 > 0 ? List.of(new GeoPoint(this, ray.getTargetPoint(t1))) : null;
    }

    @Override
    public void findMinMaxForBounding() {

    }

}
