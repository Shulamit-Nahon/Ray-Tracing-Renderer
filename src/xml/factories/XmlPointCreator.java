package xml.factories;

import geometries.Triangle;
import org.w3c.dom.Element;
import primitives.Point3D;
import java.util.Scanner;

/**
 * Constructs a {@link Point3D} from an XML {@link Element}.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlPointCreator implements XmlObjectCreatorFromAttribute <Point3D>{
    /**
     * Constructs a new {@link Point3D} from an XML {@link Element}.
     * @param attribute The XML attribute string from which to construct the T.
     * @return new Point3D
     */
    @Override
    public Point3D create(String attribute) {
        Scanner scan = new Scanner(attribute);
        return new Point3D(scan.nextDouble(),scan.nextDouble(),scan.nextDouble());
    }
}
