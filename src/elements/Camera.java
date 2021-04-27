package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * A camera class
 * defined by his p0, vT0, vUp, vRight
 */
public class Camera {
    final Point3D _p0;
    final Vector _vTo;
    final Vector _vUp;
    final Vector _vRight;

    /**
     * @return the p0 point
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * @return vT0
     */
    public Vector getvTo() {
        return _vTo;
    }

    /**
     * @return vUp
     */
    public Vector getvUp() {
        return _vUp;
    }

    private double _distance;
    private double _width;
    private double _height;

    /**
     * constructor
     * @param p0
     * @param vTo
     * @param vUp
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        _p0 = p0;
        _vTo = vTo.normalized();
        _vUp = vUp.normalized();
        if (!isZero(_vTo.dotProduct(_vUp))) {
            throw new IllegalArgumentException("vUp are orthogonal to vTo");
        }
        _vRight = _vTo.crossProduct(_vUp);
    }

    /**
     * borrowing from builder pattern
     *
     * @param width
     * @param height
     * @return
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * @param distance
     * @return the camera with the current distance
     */
    public Camera setDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * constructing a ray passing through pixel(i,j) of the view plane
     *
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc = _p0.add(_vTo.scale(_distance));

        double Rx = _width / nX;
        double Ry = _height / nY;

        Point3D Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(_p0, Pij.subtract(_p0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(_vUp.scale(Yi));
            return new Ray(_p0, Pij.subtract(_p0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(_vRight.scale(Xj));
            return new Ray(_p0, Pij.subtract(_p0));
        }

        Pij = Pij.add(_vRight.scale(Xj).add(_vUp.scale(Yi)));
        return new Ray(_p0, Pij.subtract(_p0));
    }
}
