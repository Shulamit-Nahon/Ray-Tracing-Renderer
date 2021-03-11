package primitives;

public class Ray {
    Point3D _pOrigin;
    Vector _direction;

    public Ray(Point3D pOrigin, Vector direction) {
        _pOrigin = pOrigin;
        _direction = direction.normalized();
    }

    public Point3D getpOrigin() {
        return _pOrigin;
    }

    public Vector getDirection() {
        return new Vector(_direction.head);
    }
}
