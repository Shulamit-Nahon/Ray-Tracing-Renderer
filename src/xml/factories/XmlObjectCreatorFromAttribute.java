package xml.factories;

public interface XmlObjectCreatorFromAttribute <T> {

    public T create(String attribute);
}
