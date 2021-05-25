package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

/**
 * The RAY class represents a fundamental object in geometry,
 * a ray defined by a point and a direction
 */
public class Ray {

    private static final double DELTA = 0.1;

    final Point3D _pOrigin; //The starting point of the foundation on the axis

    final Vector _direction; //Vector direction that defines the direction of the ray

    /**
     * Ray constructor receiving a Point3D and vector To create the ray
     *
     * @param pOrigin The point where the ray begins
     * @param direction The direction of the ray
     */
    public Ray(Point3D pOrigin, Vector direction) {
        _pOrigin = pOrigin;
        _direction = direction.normalized();
    }

    public Ray(GeoPoint point, Vector lightDirection, Vector n) {
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
       _pOrigin= point.add(delta);
        _direction = lightDirection.normalized();
    }



    /**
     * Function for returning the starting point of the ray on the axis
     *
     * @return point of the ray on the axis
     */
    public Point3D getpOrigin() {
        return _pOrigin;
    }

    /**
     * Function returns the vector direction of the ray on the axis
     *
     * @return the vector direction of the ray on the axis
     */
    public Vector getDirection() {
        return new Vector(_direction.head);
    }

    /**
     * Function for returning the target point of the ray on the axis
     *
     * @param t The distance from the source of the ray
     * @return The point on the ray which is distance t away from the source
     */
    public Point3D getTargetPoint(double t) {
        return _pOrigin.add(_direction.scale(t));
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointlist){
        GeoPoint minPoint = null;

        if(pointlist == null){
            return null;
        }

        double distance = Double.POSITIVE_INFINITY;

        for (GeoPoint geo:pointlist) {
            double temp = geo.point.distanceSquared(_pOrigin);
            if(temp<distance){
                distance=temp;
                minPoint=geo;
            }
        }
        return minPoint;
    }

    public Point3D findClosestPoint(List<Point3D> point3DList){

        Point3D minPoint = null;

        if(point3DList == null){
            return null;
        }

        double distance = Double.POSITIVE_INFINITY;

        for (Point3D point:point3DList) {
            double temp = point.distance(_pOrigin);
            if(temp<distance){
                distance=temp;
                minPoint=point;
            }
        }
        return minPoint;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_pOrigin=" + _pOrigin +
                ", _direction=" + _direction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(_pOrigin, ray._pOrigin) && Objects.equals(_direction, ray._direction);
    }
}
