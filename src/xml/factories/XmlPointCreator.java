package xml.factories;

import primitives.Point3D;
import java.util.Scanner;

public class XmlPointCreator implements XmlObjectCreatorFromAttribute <Point3D>{

    @Override
    public Point3D create(String attribute) {
        Scanner scan = new Scanner(attribute);
        return new Point3D(scan.nextDouble(),scan.nextDouble(),scan.nextDouble());
    }
}
