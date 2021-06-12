
package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;

import java.util.MissingResourceException;

/**
 * This class is responsible for communicating between a {@link RayTracerBasic} and an {@link ImageWriter}.
 *
 * @author lea haimovich
 * @author shulamit nahon
 */
public class Render {

    ImageWriter _imageWriter = null;
    Camera _camera = null;
    RayTracerBase _rayTracerBase = null;

    private int subPixels = 3; //the num to divide each pixel

    /**
     *set Sub Pixels
     * @param subPixels
     */
    public void setSubPixels(int subPixels) {
        this.subPixels = subPixels;
    }

    /**
     * get Sub Pixels
     * @return number of Sub Pixels
     */
    public int getSubPixels() {
        return subPixels;
    }

    //chaining methods
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     * set Camera
     * @param camera
     * @return this Render
     */
    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    /**
     * set Ray Tracer
     * @param rayTracer
     * @return this Render
     */
    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracerBase = rayTracer;
        return this;
    }

    /**
     *
     */
    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            _rayTracerBase._scene.geometries.constructHierarchy();

            //rendering the image
            int nX = _imageWriter.getNx() * subPixels;
            int nY = _imageWriter.getNy() * subPixels;
            for (int i = 0; i < nY; i += subPixels) {
                for (int j = 0; j < nX; j += subPixels) {

                    Ray[] rays = raysThroughPixel(subPixels, nX, nY, i, j);

                    Color pixelColor = Color.BLACK;
                    for (Ray ray : rays) {
                        pixelColor = pixelColor.add(_rayTracerBase.traceRay(ray));
                    }

                    _imageWriter.writePixel(j / subPixels, i / subPixels, pixelColor.reduce(subPixels*subPixels));
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    //creates array of rays to each pixel
    private Ray[] raysThroughPixel(int subPixels, int nX, int nY, int x, int y) {

        Ray rays[] = new Ray[subPixels * subPixels];
        int index = 0;
        for (int i = 0; i < subPixels; i++) {
            for (int j = 0; j < subPixels; j++) {
                rays[index++] = _camera.constructRayThroughPixel(nX, nY, y + i, x + j);
            }
        }
        return rays;
    }

    /**
     * print the final grid
     * @param interval
     * @param color
     */
    public void printGrid(int interval, Color color) {
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i += interval) {
            for (int j = 0; j < nX; j++) {
                _imageWriter.writePixel(j, i, color);
            }
        }

        for (int i = 0; i < nX; i += interval) {
            for (int j = 0; j < nY; j++) {
                _imageWriter.writePixel(i, j, color);
            }
        }
    }

    /**
     *
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}