package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for direction light
 */
public class DirectionalLight extends Light implements LightSource{

    private Vector _direction;
    /**
     * constructor for light
     * @param direction
     * @param intensity
     */
    protected DirectionalLight(Color intensity,Vector direction) {
        super(intensity);
        this._direction=direction.normalized();
    }
    /**
     * get Intensity of Directional Light
     * @param p
     * @return color Intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }
    /**
     * get L
     * @param p
     * @return Lighting direction value
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
