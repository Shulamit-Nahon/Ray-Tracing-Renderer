package xml.factories;

import elements.AmbientLight;
import geometries.Geometries;
import org.w3c.dom.Element;
import primitives.Color;
import scene.Scene;

public class XmlSceneCreator implements XmlObjectCreator <Scene> {

    @Override
    public Scene create(Element element) {

        Element ambienLightElement = (Element)element.getElementsByTagName("ambient-light").item(0);
        AmbientLight ambientLight = new XmlAmbientLightCreator().create(ambienLightElement);

        Color background = new XmlColorCreator().create(element.getAttribute("background-color"));

        Element geometriesElement = (Element)element.getElementsByTagName("geometries").item(0);
        Geometries geometries = new XmlGeometriesCreator().create(geometriesElement);

        Scene scene = new Scene("Xml Generated Scene").setAmbientLight(ambientLight).setBackground(background).setGeometries(geometries);
        return scene;
    }
}
