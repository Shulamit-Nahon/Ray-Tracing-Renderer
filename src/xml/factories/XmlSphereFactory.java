package xml.factories;

import geometries.Geometry;
import geometries.Sphere;
import org.w3c.dom.Element;
import primitives.Point3D;

public class XmlSphereFactory implements XmlGeometryFactory {

    @Override
    public Geometry create(Element element) {
        Point3D point = new XmlPointCreator().create(element.getAttribute("center"));
        return new Sphere(Double.parseDouble(element.getAttribute("radius")),point);
    }
}
