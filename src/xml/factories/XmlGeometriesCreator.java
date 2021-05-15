package xml.factories;

import geometries.Geometries;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static java.util.Map.entry;
import java.util.Map;

public class XmlGeometriesCreator implements XmlObjectCreator <Geometries> {


        private static final Map<String, XmlGeometryFactory> FACTORIES = Map.ofEntries(
                entry("sphere", new XmlSphereFactory()),
                entry("triangle", new XmlTriangleFactory())
        );

    @Override
    public Geometries create(Element element) {

        NodeList children = element.getChildNodes();
        Geometries geometries = new Geometries();

        for(int i = 0; i< children.getLength(); ++i){
            Node child = children.item(i);
            if(child.getNodeType()==Node.ELEMENT_NODE){
                Element elementGeometry = (Element)child;
                geometries.add(FACTORIES.get(elementGeometry.getTagName()).create(elementGeometry));
            }
        }
        return geometries;
    }
}
