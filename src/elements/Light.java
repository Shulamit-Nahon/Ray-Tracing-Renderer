package elements;

import primitives.Color;

/**
 * abstract class for light
 */
abstract public class Light {

    protected Color _intensity; // intensity color

    /**
     * constructor for light
     * @param intensity of the light
     */
    protected Light(Color intensity){
        this._intensity=intensity;
    }

    /**
     * get Intensity of light
     * @return Intensity of light
     */
    public Color getIntensity() {
        return _intensity;
    }
}
