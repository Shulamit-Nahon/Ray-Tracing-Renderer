package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.function.Predicate;

public class Border {

    //coordinates of the maximum point
    private double maxX;
    private double maxY;
    private double maxZ;

    //coordinates of the minimum points     
    private double minX;
    private double minY;
    private double minZ;

    private Point3D center;
    private Predicate<Point3D> side;
    public static final Border EMPTY = new Border(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    public static final Border INFINITE = new Border(
            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    public Border(double maxX, double maxY, double maxZ, double minX, double minY, double minZ) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;

        center = volumeCenter();
        side = cutPredicate();
    }

    public Border union(Border border) {
        return new Border(
                Math.max(border.maxX, maxX),
                Math.max(border.maxY, maxY),
                Math.max(border.maxZ, maxZ),
                Math.min(border.minX, minX),
                Math.min(border.minY, minY),
                Math.min(border.minZ, minZ)
        );
    }

    public Point3D getCenter() {
        return center;
    }

    public boolean chooseSide(Point3D point) {
        return side.test(point);
    }

    /**
     * A function that finds whether there are intersections with the boundary that warmed the bodies
     *
     * @param r
     * @return TRUE if there is intersection point with the boundary min and max value
     */
    public boolean intersect(Ray r) {

        double rayPoX = r.getpOrigin().getX();
        double rayPoY = r.getpOrigin().getY();
        double rayPoZ = r.getpOrigin().getZ();
        double tmin = (minX - rayPoX) / r.getDirection().getHead().getX();
        double tmax = (maxX - rayPoX) / r.getDirection().getHead().getX();
        if (tmin > tmax) {
            double temp = tmin;
            tmin = tmax;
            tmax = temp;
        }

        double tymin = (minY - rayPoY) / r.getDirection().getHead().getY();
        double tymax = (maxY - rayPoY) / r.getDirection().getHead().getY();

        if (tymin > tymax) {
            double temp = tymin;
            tymin = tymax;
            tymax = temp;
        }

        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;

        if (tymax < tmax)
            tmax = tymax;

        double tzmin = (minZ - rayPoZ) / r.getDirection().getHead().getZ();
        double tzmax = (maxZ - rayPoZ) / r.getDirection().getHead().getZ();

        if (tzmin > tzmax) {
            double temp = tzmin;
            tzmin = tzmax;
            tzmax = temp;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;

        if (tzmin > tmin)
            tmin = tzmin;

        if (tzmax < tmax)
            tmax = tzmax;

        return true;
    }

    /**
     * divides the bounding volume into two halves
     *
     * @return a predicate that decides if the point is in one side or the other
     */
    private Predicate<Point3D> cutPredicate() {

        double lengthX = maxX - minX;
        double lengthY = maxY - minY;
        double lengthZ = maxZ - minZ;

        Point3D center = volumeCenter();

        Predicate<Point3D> zPredicate = point -> point.getZ() > center.getZ();

        //checks which length is the largest
        if (lengthX < lengthY) {
            if (lengthY < lengthZ) {
                return zPredicate;
            }
            else {
                return point -> point.getY() > center.getY();
            }
        }
        else {
            if (lengthX > lengthZ) {
                return point -> point.getX() > center.getX();
            }
            else {
                return zPredicate;
            }
        }
    }

    /**
     * calculates the point of the volume center
     *
     * @return new point of the volume center
     */
    private Point3D volumeCenter() {

        if (isInfinite()) {
            return Point3D.ZERO;
        }
        return new Point3D((maxX + minX) / 2, (maxY + minY) / 2, (maxZ + minZ) / 2);
    }

    /**
     * checks if one of the values is infinity
     *
     * @return true if one of the values is infinity
     */
    private boolean isInfinite() {
        double sum = maxX + maxY + maxZ + minX + minY + minZ;
        return sum == Double.NEGATIVE_INFINITY || sum == Double.POSITIVE_INFINITY || Double.isNaN(sum);
    }
}
