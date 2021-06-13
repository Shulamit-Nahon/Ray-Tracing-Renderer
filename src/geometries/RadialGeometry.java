package geometries;

/**
 * class for geometry who have radius
 */
public abstract class RadialGeometry extends Geometry{

    final protected double radius; //radius of  Sphere

    /**
     * Radial Geometry constructor
     * @param radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * @return radius of  Sphere
     */
    public double getRadius() {
        return radius;
    }
}
