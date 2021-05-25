package primitives;

import static primitives.Point3D.ZERO;

/**
 * Vector in 3D for RayTracing
 */
public class Vector extends java.util.Vector {

    protected Point3D head;

    /**
     * get accessor for head Point
     *
     * @return a new Point3D corresponding to the head
     */
    public Point3D getHead() {
        return head;
    }

    /**
     * primary  constructor for Vector class
     *
     * @param head_
     */
    public Vector(Point3D head_) {
        if (head_.equals(ZERO)) {
            throw new IllegalArgumentException("Vector head cannot be Point(0,0,0)");
        }
        head = head_;
    }

    /**
     * constructor for Vector class
     *
     * @param x_ X Coordinate of the head Point
     * @param y_ Y Coordinate of the head Point
     * @param z_ Z Coordinate of the head Point
     */
    public Vector(Coordinate x_, Coordinate y_, Coordinate z_) {

        this(x_.coord, y_.coord, z_.coord);
    }

    /**
     * constructor for Vector class
     *
     * @param x_ value for X Coordinate of the head Point
     * @param y_ value for Y Coordinate of the head Point
     * @param z_ value for Z Coordinate of the head Point
     */
    public Vector(double x_, double y_, double z_) {
        this(new Point3D(x_, y_, z_));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }


    @Override
    public String toString() {

        return "{" + head + '}';
    }

    /**
     * creates new vector by the 3 new values from adding the 2 values of each vector
     *
     * @param vector Vector
     * @return new Vector (u+v)
     */
    public Vector add(Vector vector) {
        double x = head.x.coord + vector.head.x.coord;
        double y = head.y.coord + vector.head.y.coord;
        double z = head.z.coord + vector.head.z.coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * creates new vector by the 3 new values from subtraction of the 2 values of each vector
     *
     * @param vector
     * @return new Vector(u-v)
     */
    public Vector substract(Vector vector) {
        double x = head.x.coord - vector.head.x.coord;
        double y = head.y.coord - vector.head.y.coord;
        double z = head.z.coord - vector.head.z.coord;

        return new Vector(new Point3D(x, y, z));
    }

    /**
     * creating a new Vector corresponding to the actual one
     * scaled by scaling factor
     *
     * @param scalar scaling factot
     */
    public Vector scale(double scalar) {
        if (Double.compare(scalar, 0d) == 0) {
            throw new IllegalArgumentException("You can not multiplie by zero");
        }
        double x = head.x.coord * scalar;
        double y = head.y.coord * scalar;
        double z = head.z.coord * scalar;

        Point3D point3D = new Point3D(x, y, z);
        return new Vector(point3D);
    }

    /**
     * Cross product (vectorial product)
     *
     * @param v
     * @return new Vector resulting from cross product
     * @link https://en.wikipedia.org/wiki/Cross_product
     */
    public Vector crossProduct(Vector v) {
        double u1 = head.x.coord;
        double u2 = head.y.coord;
        double u3 = head.z.coord;

        double v1 = v.head.x.coord;
        double v2 = v.head.y.coord;
        double v3 = v.head.z.coord;

        return new Vector(new Point3D(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        ));
    }

    /**
     * dot product between two vectors (scalar product)
     *
     * @param v the right vector of U.V
     * @return scalre value of dot product
     */
    public double dotProduct(Vector v) {
        double u1 = head.x.coord;
        double u2 = head.y.coord;
        double u3 = head.z.coord;

        double v1 = v.head.x.coord;
        double v2 = v.head.y.coord;
        double v3 = v.head.z.coord;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }

    /**
     * @return length Squared
     */
    public double lengthSquared() {
        double u1 = head.x.coord;
        double u2 = head.y.coord;
        double u3 = head.z.coord;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    /**
     * @return length using Pythagoras
     */
    public double length() {

        return Math.sqrt(lengthSquared());
    }

    /**
     * normalizing the current Vector
     *
     * @return this Vector normalized
     */
    public Vector normalize() {
        double length = this.length();

        //cannot divide by 0
        if (length == 0)
            throw new ArithmeticException("divide by Zero");

        double x = this.head.x.coord;
        double y = this.head.y.coord;
        double z = this.head.z.coord;

        this.head = new Point3D(x / length, y / length, z / length);

        return this;
    }

    /**
     * creating a new Vector corresponding to the current values normalized
     *
     * @return new Vector normalized
     */
    public Vector normalized() {
        Vector result = new Vector(head);
        result.normalize();
        return result;
    }



}
