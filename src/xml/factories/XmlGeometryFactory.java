package xml.factories;

import geometries.Geometry;

/**
 * This interface represents a class which is able to create {@link Geometry} instances from XML {@link Element}s.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public interface XmlGeometryFactory extends XmlObjectCreator<Geometry> { }
