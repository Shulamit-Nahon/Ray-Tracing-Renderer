package xml.factories;

import geometries.Geometry;
import geometries.Sphere;
import org.w3c.dom.Element;
import primitives.Point3D;
import scene.Scene;

/**
 * Constructs a {@link Sphere} from an XML {@link Element}.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlSphereFactory implements XmlGeometryFactory {
    /**
     *  Constructs a new {@link Sphere} from an XML {@link Element}.
     * @param element The XML {@link Element} representing the object to be created.
     * @return new Sphere
     */
    @Override
    public Geometry create(Element element) {
        Point3D point = new XmlPointCreator().create(element.getAttribute("center"));
        return new Sphere(Double.parseDouble(element.getAttribute("radius")),point);
    }
}
