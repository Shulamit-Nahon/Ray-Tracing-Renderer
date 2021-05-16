package xml.factories;

import elements.AmbientLight;
import org.w3c.dom.Element;
import primitives.Color;
/**
 * Constructs an {@link AmbientLight} from an XML {@link Element}.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlAmbientLightCreator implements XmlObjectCreator<AmbientLight> {
    /**
     * Constructs a new {@link AmbientLight} from an XML {@link Element}.
     *
     * @param element The XML element, containing an attribute "color" whose value can be parsed into a {@link Color }.
     * @return A new AmbientLight with the given values.
     * @throws xml.XmlParseException  if the XML element did not have a valid colour attribute.
     */
    @Override
    public AmbientLight create(Element element) {
        Color color = new XmlColorCreator().create(element.getAttribute("color"));
        return new AmbientLight(color,1);
    }
}
