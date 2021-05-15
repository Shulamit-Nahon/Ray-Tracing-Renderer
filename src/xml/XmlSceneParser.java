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

public class XmlSceneParser {

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private final DocumentBuilder builder;

    public XmlSceneParser( ){
        try{
            builder = factory.newDocumentBuilder();
        }
        catch(ParserConfigurationException exception){
            throw new XmlParseException(exception);
        }

    }

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
