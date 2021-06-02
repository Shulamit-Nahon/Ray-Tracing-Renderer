package xml;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import scene.Scene;
import xml.factories.XmlSceneCreator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * A class which parses an XML file and constructs the {@link Scene}.
 *
 * @author shulamit nahon
 * @author lea haimovich
 */
public class XmlSceneParser {

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private final DocumentBuilder builder;

    /**
     * Default constructor for the XML scene parser.
     */
    public XmlSceneParser( ){
        try{
            builder = factory.newDocumentBuilder();
        }
        catch(ParserConfigurationException exception){
            throw new XmlParseException(exception);
        }
    }

    /**
     * Create a {@link Scene} from the given XML file.
     *
     * @param xmlFileName The filename of the XML file containing the {@link Scene} information.
     * @return A new {@link Scene} reflecting the information in the given XML file.
     * @throws IOException        If the file could not be opened.
     * @throws xml.XmlParseException if the XML file is unparsable.
     */
    public Scene parse(String xmlFileName) {

        File xmlFile = new File(xmlFileName);
        Element root;

        try {
            root = builder.parse(xmlFile).getDocumentElement() ;
        }
        catch (SAXException | IOException e) {
            throw new XmlParseException(e);
        }
        return new XmlSceneCreator().create(root);
    }
}
