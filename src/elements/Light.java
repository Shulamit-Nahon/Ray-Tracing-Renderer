package elements;

import primitives.Color;

import java.util.List;

/**
 * abstract class for light
 */
abstract public class Light {

    protected Color _intensity;

    /**
     * constructor for light
     * @param intensity
     */
    protected Light(Color intensity){
        this._intensity=intensity;
    }

    public Color getIntensity() {
        return _intensity;
    }
}
