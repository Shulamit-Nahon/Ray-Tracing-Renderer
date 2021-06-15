
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Classes which extend {@link RayTracerBase} are responsible for calculating the {@link Color} at the intersections of a
 * {@link Ray} in the {@link Scene}.
 *
 * @author lea haimovich
 * @author shulamit nahon
 */
public abstract class RayTracerBase {
    /** The scene to trace each ray in. */
    protected Scene _scene;

    /**
     * Construct a new {@link RayTracerBase} for the given {@link Scene}.
     *
     * @param scene The {@link Scene} to trace.
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * Trace the given ray to calculate the {@link Color} that ray results in.
     *
     * @param ray The ray to trace in the scene.
     * @return The {@link Color} resulting from the trace of the ray.
     */
    public abstract Color traceRay(Ray ray);

    public Color traceRays(Ray[] rays) {
        Color pixelColor = Color.BLACK;
        for (Ray ray : rays) {
            pixelColor = pixelColor.add(traceRay(ray));
        }
        return pixelColor;
    }
}