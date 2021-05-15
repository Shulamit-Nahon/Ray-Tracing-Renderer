package xml;

public class XmlParseException extends RuntimeException {

    public XmlParseException(Throwable cause) {
        super(cause);
    }

    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
