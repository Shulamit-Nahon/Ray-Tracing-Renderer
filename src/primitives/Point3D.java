package primitives;

import java.util.Objects;

/**
 * basic Point for RayTracing project in 3D
 */
public class Point3D {
    final Coordinate x;
    final Coordinate y;
    final Coordinate z;
    //static Point3D for origin point (0,0,0)
    public final static Point3D ZERO = new Point3D(0d, 0d, 0d);

    /**
     * primary constructor for Point3D
     * @param x_ coordinate value for X axis
     * @param y_ coordinate value for Y axis
     * @param z_ coordinate value for Z axis
     */
    public Point3D(double x_, double y_, double z_) {
        this.x = new Coordinate(x_);
        this.y = new Coordinate(y_);
        this.z = new Coordinate(z_);
    }
    /**
     * constructor for Point3D
     *
     * @param x_ coordinate for X axis
     * @param y_ coordinate for Y axis
     * @param z_ coordinate for Z axis
     */
    public Point3D(Coordinate x_, Coordinate y_, Coordinate z_) {

        this(x_.coord, y_.coord, z_.coord);
    }


    /**
     * @return Returns the X-axis values of the point
     */
    public double getX() {
        return x.coord;
    }
    /**
     * @return Returns the Y-axis values of the point
     */
    public double getY() {
        return y.coord;
    }
    /**
     * @return Returns the Z-axis values of the point
     */
    public double getZ() {
        return z.coord;
    }

    /**
     *
     * @param o Object (basicaly another Point3d) to compare
     * @return true or false accordingly
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }

    @Override
    public String toString() {

        return "(" + x + "," + y + "," + z + ')';
    }

    /**
     * @param point
     * @return (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
     */
    public double distanceSquared(Point3D point) {
        final double x2 = point.x.coord;
        final double y2 = point.y.coord;
        final double z2 = point.z.coord;
        final double x1 = this.x.coord;
        final double y1 = this.y.coord;
        final double z1 = this.z.coord;

        return ((x2 - x1)*(x2 - x1))+((y2 - y1)*(y2 - y1))+((z2 - z1) * (z2 - z1));
    }

    /**
     * @param point
     * @return euclidean distance between 2  3D points
     */
    public double distance(Point3D point) {

        return Math.sqrt(distanceSquared(point));
    }

    /**
     *
     * @param v
     * @return (x+vx,y+vy,z+vz)
     * Connecting the vector coordinate values to the point coordinate values
     */
    public Point3D add(Vector v) {
        return new Point3D(
                this.x.coord + v.head.x.coord,
                this.y.coord + v.head.y.coord,
                this.z.coord + v.head.z.coord);
    }

    /**
     *
     * @param pt2 Point3d from whom we create the Vector
     *            pointing toward the actual Point3d instance
     * @return
     */
    public Vector subtract(Point3D pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        }
        return  new Vector(new Point3D(
                x.coord - pt2.x.coord,
                y.coord - pt2.y.coord,
                z.coord - pt2.z.coord
        ));
    }
}