package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * class for spot light
 */
public class SpotLight extends PointLight  {

    private final Vector _direction;  //direction of Spot Light

    /**
     * constructor for Spot Light
     *
     * @param intensity The color of the light
     * @param position The position of the light
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this._direction=direction.normalized();
    }

    /**
     * get Intensity of Spot Light
     *
     * @param p the point at which to get the intensity
     * @return color Intensity
     */
    @Override
    public Color getIntensity(Point3D p){
        double factor =alignZero(Math.max(0,_direction.dotProduct(getL(p))));
        return super.getIntensity(p).scale(factor);
    }
}
