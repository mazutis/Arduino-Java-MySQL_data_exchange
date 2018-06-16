package SENSORS;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Xml {
    static String domain;
    static String ip;
    static String url;
    static String user;
    static String password;
    static String ssl;

    // READ FROM Xml FILE...............................................................................................
    Xml() {
        try {
            File fXmlFile = new File("key.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //String rootElement = doc.getDocumentElement().getNodeName();
            //System.out.println(rootElement);                                                                          // test tracking

            NodeList nList = doc.getElementsByTagName("mySQL");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());                                      // test tracking
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    domain = eElement.getAttribute("domain");
                    ip = eElement.getElementsByTagName("ip").item(0).getTextContent();
                    url =  eElement.getElementsByTagName("url").item(0).getTextContent();
                    user = eElement.getElementsByTagName("user").item(0).getTextContent();
                    password = eElement.getElementsByTagName("password").item(0).getTextContent();
                    ssl = eElement.getElementsByTagName("ssl").item(0).getTextContent();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //..................................................................................................................

}