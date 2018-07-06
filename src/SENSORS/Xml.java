package SENSORS;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Xml {
    private static String domain;
    private static String ip;
    private static String url;
    private static String user;
    private static String password;
    private static String ssl;
    private static String rxtxPort;

    // READ FROM Xml FILE...............................................................................................
    public Xml() {
        try {
            File fXmlFile = new File("key.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("settings");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    domain = eElement.getAttribute("domain");
                    ip = eElement.getElementsByTagName("ip").item(0).getTextContent();
                    url =  eElement.getElementsByTagName("url").item(0).getTextContent();
                    user = eElement.getElementsByTagName("user").item(0).getTextContent();
                    password = eElement.getElementsByTagName("password").item(0).getTextContent();
                    ssl = eElement.getElementsByTagName("ssl").item(0).getTextContent();
                    rxtxPort = eElement.getElementsByTagName("rxtxPort").item(0).getTextContent();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //..................................................................................................................
    public String getDomain(){
        return domain;
    }

    public static String getIP(){
        return ip;
    }

    public static String getURL(){
        return url;
    }

    public static String getUser(){
        return user;
    }

    public static String getPassword(){
        return password;
    }

    public static String getSSL(){
        return ssl;
    }

    public static String getRxTxPort(){return rxtxPort; }

}