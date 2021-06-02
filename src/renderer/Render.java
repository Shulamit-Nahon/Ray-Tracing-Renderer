
package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;

import java.util.MissingResourceException;

public class Render {

    ImageWriter _imageWriter = null;
    Camera _camera = null;
    RayTracerBase _rayTracerBase = null;

    //chaining methods
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /* public Render setScene(Scene scene) {
         _scene = scene;
         return this;
     }
 */
    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracerBase = rayTracer;
        return this;
    }

    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            // if (_scene == null) {
            //    throw new MissingResourceException("missing resource", Scene.class.getName(), "");
            // }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = _imageWriter.getNx()*2;
            int nY = _imageWriter.getNy()*2;
            for (int i = 0; i < nY; i+=2) {
                for (int j = 0; j < nX; j+=2) {
                    Ray rays[] = {_camera.constructRayThroughPixel(nX, nY, j+1, i+1),
                                 _camera.constructRayThroughPixel(nX, nY, j, i+1),
                                 _camera.constructRayThroughPixel(nX, nY, j+1, i),
                                 _camera.constructRayThroughPixel(nX, nY, j, i)};

                    Color pixelColor=Color.BLACK;
                    for (Ray ray : rays) {
                         pixelColor = pixelColor.add( _rayTracerBase.traceRay(ray));
                    }

                    _imageWriter.writePixel(j/2, i/2, pixelColor.reduce(4));
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }


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

    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}