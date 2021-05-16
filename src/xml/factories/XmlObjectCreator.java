package xml.factories;

import org.w3c.dom.Element;
/**
 * This interface represents a class which is able to create instances of type T from an XML {@link Element}.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public interface XmlObjectCreator <T> {
    /**
     * Construct a new instance of type T from an XML {@link Element}.
     *
     * @param element The XML {@link Element} representing the object to be created.
     * @return A new instance of type T created from the XML {@link Element}.
     */
    public T create(Element element);
}
