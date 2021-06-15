package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for direction light
 */
public class DirectionalLight extends Light implements LightSource{

    private final Vector _direction; //the direction light

    /**
     * constructor for light
     * @param direction the direction light
     * @param intensity the light intensity
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this._direction=direction.normalized();
    }

    /**
     * get Intensity of Directional Light
     * @param p gets point
     * @return color Intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    /**
     * get L
     * @param p gets point
     * @return Lighting direction value
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    /**
     * get the Distance between light source to point
     * @param point the point we want the distance from it
     * @return distance between light source to point
     */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
