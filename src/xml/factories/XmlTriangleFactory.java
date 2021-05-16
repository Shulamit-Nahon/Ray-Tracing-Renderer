package xml.factories;

import geometries.Geometry;
import geometries.Triangle;
import org.w3c.dom.Element;
import primitives.Point3D;
import scene.Scene;

/**
 * Constructs a {@link Triangle} from an XML {@link Element}.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlTriangleFactory implements XmlGeometryFactory{
    /**
     *  Constructs a new {@link Triangle} from an XML {@link Element}.
     * @param element The XML {@link Element} representing the object to be created.
     * @return new Triangle
     */
    @Override
    public Geometry create(Element element) {

        XmlObjectCreatorFromAttribute<Point3D> factory = new XmlPointCreator();

        Point3D point0 = factory.create(element.getAttribute("p0"));
        Point3D point1 = factory.create(element.getAttribute("p1"));
        Point3D point2 = factory.create(element.getAttribute("p2"));

        return new Triangle(point0,point1,point2);
    }
}
