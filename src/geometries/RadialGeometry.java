package geometries;

public abstract class RadialGeometry {
    final protected double radius; //radius of  Sphere

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
