package elements;

import primitives.Color;

import java.util.List;

/**
 * abstract class for light
 */
abstract public class Light {

    // intensity color
    protected Color _intensity;

    /**
     * constructor for light
     * @param intensity
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
