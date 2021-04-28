package renderer;

import elements.Camera;
import scene.Scene;

public class Render {

    private ImageWriter imageWriter;
    private Scene scene;
    private Camera camera;

    public Render setImageWriter(ImageWriter _imageWriter) {
        imageWriter = _imageWriter;
        return this;
    }

    public Render setScene(Scene _scene) {
        scene = _scene;
        return this;
    }

    public Render setCamera(Camera _camera) {
        camera =_camera;
        return this;
    }
}
