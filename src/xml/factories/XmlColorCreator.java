package xml.factories;

import primitives.Color;

import java.util.Scanner;

public class XmlColorCreator implements XmlObjectCreatorFromAttribute <Color>{

    @Override
    public Color create(String attribute) {
        Scanner scan = new Scanner(attribute);
        double red = scan.nextDouble();
        double green = scan.nextDouble();
        double blue = scan.nextDouble();
        return new Color(red, green, blue);
    }
}
