package elements;

import primitives.*;
/**
 *  interface for all lights which have a source.
 *
 * @author lea haimovich
 * @author shulamit nahon
 */
public interface LightSource {

    /**
     *get Intensity of light
     * @param p
     * @return the intensity of light source in point p
     */
    public Color getIntensity(Point3D p);

    /**
     *
     * @param p
     * @return vector direction of light
     */
    public Vector getL(Point3D p);

    /**
     *get the Distance between light source to point
     * @param point
     * @return Distance between light source to point
     */
   public double getDistance(Point3D point);
}
