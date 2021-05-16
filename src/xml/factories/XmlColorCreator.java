package xml.factories;

import primitives.Color;

import java.util.Scanner;
/**
 * Constructs {@link Color}s from XML attributes.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlColorCreator implements XmlObjectCreatorFromAttribute <Color>{
    /**
     * Constructs a new {@link Color} from an XML attribute string.
     *
     * @param attribute The XML attribute, in the form "r g b" where r, g, and b are integers between 0 and 255
     *                  representing the red, green, and blue values.
     * @return A new colour with the given values.
     * @throws xml.XmlParseException if the input string was malformed.
     */
    @Override
    public Color create(String attribute) {
        Scanner scan = new Scanner(attribute);
        double red = scan.nextDouble();
        double green = scan.nextDouble();
        double blue = scan.nextDouble();
        return new Color(red, green, blue);
    }
}
