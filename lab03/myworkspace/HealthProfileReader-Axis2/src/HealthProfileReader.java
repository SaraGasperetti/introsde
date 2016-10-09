import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class HealthProfileReader {

	private static Document doc;
        private static XPath xpath;

        static{                
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            doc = builder.parse("/home/sara/Documenti/università/IntroSDE/introsde/lab03/myworkspace/HealthProfileReader-Axis2/people.xml");

            //creating xpath object
            XPathFactory factory = XPathFactory.newInstance();
            xpath = factory.newXPath();
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(HealthProfileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        


	public String displayHealthProfile(String firstname, String lastname) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
            return firstname + " " + lastname + "'s health profile: weight = " + 
                    getWeight(firstname, lastname) +
                    ", height = " + getHeight(firstname, lastname);
	}
        
        public String getWeight(String firstname, String lastname) throws XPathExpressionException {
            XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']");
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            Node healthProfile = (node.getChildNodes()).item(5);
            return healthProfile.getChildNodes().item(1).getTextContent();
        }
        
        public String getHeight(String firstname, String lastname) throws XPathExpressionException {
            XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']");
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            Node healthProfile = (node.getChildNodes()).item(5);
            return healthProfile.getChildNodes().item(3).getTextContent();
        }
        
        public String compareWeight(double weight, String op) throws XPathExpressionException {
            XPathExpression expr = xpath.compile("/people/person[healthprofile[weight " + op + " " + weight + "]]");
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            String result = "People with weight " + op + " " + weight + ": ";
            
            for (int i=0; i<nodeList.getLength(); i++) {
                result = result.concat(nodeList.item(i).getChildNodes().item(1).getTextContent());
                result = result.concat(" " + nodeList.item(i).getChildNodes().item(3).getTextContent() + " -- ");
            }
            return result;
        }



}