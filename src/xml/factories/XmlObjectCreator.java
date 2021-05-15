package xml.factories;

import org.w3c.dom.Element;

public interface XmlObjectCreator <T> {

    public T create(Element element);
}
