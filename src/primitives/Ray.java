package primitives;

import java.util.List;
import java.util.Objects;

/**
 * The RAY class represents a fundamental object in geometry,
 * a ray defined by a point and a direction
 */
public class Ray {

    final Point3D _pOrigin; //The starting point of the foundation on the axis

    final Vector _direction; //Vector direction that defines the direction of the ray

    /**
     * Ray constructor receiving a Point3D and vector To create the ray
     *
     * @param pOrigin
     * @param direction
     */
    public Ray(Point3D pOrigin, Vector direction) {
        _pOrigin = pOrigin;
        _direction = direction.normalized();
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
     * @param t
     * @return
     */
    public Point3D getTargetPoint(double t) {
        return _pOrigin.add(_direction.scale(t));
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
