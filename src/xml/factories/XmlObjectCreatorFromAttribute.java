package xml.factories;
/**
 * This interface represents a class which constructs an object of type T from an XML attribute string.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public interface XmlObjectCreatorFromAttribute <T> {
    /**
     * Create an instance of type T from the given attribute string.
     *
     * @param attribute The XML attribute string from which to construct the T.
     * @return An instance of type T whose value is the data in the given string.
     */
    public T create(String attribute);
}
