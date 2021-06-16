package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.function.Predicate;

public class Border {

    //coordinates of the maximum point
    private final double maxX;
    private final double maxY;
    private final double maxZ;

    //coordinates of the minimum points     
    private final double minX;
    private final double minY;
    private final double minZ;

    private final Point3D center;
    private final Predicate<Point3D> side;

    public static final Border EMPTY = new Border(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

    public static final Border INFINITE = new Border(
            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    /**
     * border constructor
     * @param maxX value
     * @param maxY value
     * @param maxZ value
     * @param minX value
     * @param minY value
     * @param minZ value
     */
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

    /**
     * @return the center
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * boolean function that choose the side of the point
     * @param point the point we want to check to which side
     * @return the side
     */
    public boolean chooseSide(Point3D point) {
        return side.test(point);
    }

    /**
     * A function that finds whether there are intersections with the boundary that warmed the bodies
     *
     * @param r the ray
     * @return TRUE if there is intersection point with the boundary min and max value
     */
    public boolean intersect(Ray r) {

        double rayPoX = r.getpOrigin().getX();
        double rayPoY = r.getpOrigin().getY();
        double rayPoZ = r.getpOrigin().getZ();
        double tMin = (minX - rayPoX) / r.getDirection().getHead().getX();
        double tMax = (maxX - rayPoX) / r.getDirection().getHead().getX();
        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }

        double tY_Min = (minY - rayPoY) / r.getDirection().getHead().getY();
        double tY_Max = (maxY - rayPoY) / r.getDirection().getHead().getY();

        if (tY_Min > tY_Max) {
            double temp = tY_Min;
            tY_Min = tY_Max;
            tY_Max = temp;
        }

        if ((tMin > tY_Max) || (tY_Min > tMax))
            return false;

        if (tY_Min > tMin)
            tMin = tY_Min;

        if (tY_Max < tMax)
            tMax = tY_Max;

        double tZ_Min = (minZ - rayPoZ) / r.getDirection().getHead().getZ();
        double tZ_Max = (maxZ - rayPoZ) / r.getDirection().getHead().getZ();

        if (tZ_Min > tZ_Max) {
            double temp = tZ_Min;
            tZ_Min = tZ_Max;
            tZ_Max = temp;
        }

        if ((tMin > tZ_Max) || (tZ_Min > tMax))
            return false;

        if (tZ_Min > tMin)
            tMin = tZ_Min;

        if (tZ_Max < tMax)
            tMax = tZ_Max;

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
