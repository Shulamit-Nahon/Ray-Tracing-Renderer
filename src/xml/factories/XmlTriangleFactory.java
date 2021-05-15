package xml.factories;

import geometries.Geometry;
import geometries.Triangle;
import org.w3c.dom.Element;
import primitives.Point3D;

public class XmlTriangleFactory implements XmlGeometryFactory{

    @Override
    public Geometry create(Element element) {

        XmlObjectCreatorFromAttribute<Point3D> factory = new XmlPointCreator();

        Point3D point0 = factory.create(element.getAttribute("p0"));
        Point3D point1 = factory.create(element.getAttribute("p1"));
        Point3D point2 = factory.create(element.getAttribute("p2"));

        return new Triangle(point0,point1,point2);
    }
}
