package primitives;
/**
 * The RAY class represents a fundamental object in geometry,
 * a ray defined by a point and a direction
 */
public class Ray {
    /**
     * The starting point of the foundation on the axis
     */
     final Point3D _pOrigin;
    /**
     * Vector direction that defines the direction of the ray
     */
    final Vector _direction;

    /**
     * Ray constructor receiving a Point3D and vector To create the ray
     * @param pOrigin
     * @param direction
     */
    public Ray(Point3D pOrigin, Vector direction) {
        _pOrigin = pOrigin;
        _direction = direction.normalized();
    }

    /**
     * Function for returning the starting point of the ray on the axis
     * @return point of the ray on the axis
     */
    public Point3D getpOrigin() {
        return _pOrigin;
    }

    /**
     * Function returns the vector direction of the ray on the axis
     * @return the vector direction of the ray on the axis
     */
    public Vector getDirection() {
        return new Vector(_direction.head);
    }
}
