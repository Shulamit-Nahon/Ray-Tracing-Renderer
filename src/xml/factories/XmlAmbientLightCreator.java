package xml.factories;

import elements.AmbientLight;
import org.w3c.dom.Element;
import primitives.Color;

public class XmlAmbientLightCreator implements XmlObjectCreator<AmbientLight> {
    @Override
    public AmbientLight create(Element element) {
        Color color = new XmlColorCreator().create(element.getAttribute("color"));
        return new AmbientLight(color,1);
    }
}
