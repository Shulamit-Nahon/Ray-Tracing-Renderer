package xml.factories;

import elements.AmbientLight;
import geometries.Geometries;
import org.w3c.dom.Element;
import primitives.Color;
import scene.Scene;
/**
 * Constructs a {@link Scene} from XML {@link Element}s.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlSceneCreator implements XmlObjectCreator <Scene> {
    /**
     * Constructs a new {@link Scene} from an XML {@link Element}.
     *
     * @param element The XML {@link Element} representing the scene.
     * @return A scene with the data specified by the XML.
     * @throws xml.XmlParseException if the XML was malformed.
     */
    @Override
    public Scene create(Element element) {

        Element ambientLightElement = (Element)element.getElementsByTagName("ambient-light").item(0);
        AmbientLight ambientLight = new XmlAmbientLightCreator().create(ambientLightElement);

        Color background = new XmlColorCreator().create(element.getAttribute("background-color"));

        Element geometriesElement = (Element)element.getElementsByTagName("geometries").item(0);
        Geometries geometries = new XmlGeometriesCreator().create(geometriesElement);

        Scene scene = new Scene("Xml Generated Scene").setAmbientLight(ambientLight).setBackground(background).setGeometries(geometries);
        return scene;
    }
}
