package elements;

import primitives.*;

/**
 *  interface for all lights which have a source.
 */
public interface LightSource {

    /**
     *get Intensity of light
     * @param p the point that the function gets
     * @return the intensity of light source in point p
     */
     Color getIntensity(Point3D p);

    /**
     *
     * @param p the point that the function gets
     * @return vector direction of light
     */
     Vector getL(Point3D p);

    /**
     *get the Distance between light source to point
     * @param point the point that the function gets
     * @return Distance between light source to point
     */
    double getDistance(Point3D point);
}
