package elements;

import primitives.Color;

/**
 * class for AmbientLight
 */
public class AmbientLight extends Light {


    /**
     * default constructor for AmbientLight
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * constructor for AmbientLight
     * @param iA-intensity
     * @param kA coefficient of the ambient light
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA)) ;
    }
}