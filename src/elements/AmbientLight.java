package elements;

import primitives.Color;

public class AmbientLight {

    private final Color _intensity;

    public AmbientLight(Color iA, double kA) {
        _intensity = iA.scale(kA);
    }

    public Color get_intensity() {
        return _intensity;
    }

}
