package elements;

import primitives.*;

public interface LightSource {
    /**
     * interface for light of source
     */

    /**
     *
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

}
