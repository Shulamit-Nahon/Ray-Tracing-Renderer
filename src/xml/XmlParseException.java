package xml;

/**
 * This exception is thrown when something goes wrong during the parsing of the XML file.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlParseException extends RuntimeException {
    /**
     * Construct this exception with an inner exception.
     *
     * @param cause The inner exception.
     */
    public XmlParseException(Throwable cause) {

        super(cause);
    }
    /**
     * Construct this exception with a message and an inner exception.
     *
     * @param message The error message.
     * @param cause  The inner exception.
     */
    public XmlParseException(String message, Throwable cause) {

        super(message, cause);
    }
}
