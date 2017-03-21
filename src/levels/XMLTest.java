package levels;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTest {

	public static void main(String[] args) {
		 boolean debug = true;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File("Resources/LevelFiles/custom.xml");

			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			
			NodeList tileNodes = doc.getElementsByTagName("tileset");
			
			for (int i = 0; i < tileNodes.getLength(); i++) {
				Node node = tileNodes.item(i);
				System.out.println("\nCurrent Element :" + node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE)
				
				if (debug)
					System.out.println("Exploring: " + node.getNodeName());

				switch (node.getNodeName()) {
				case "map":
					NodeList mapChildren = node.getChildNodes();

					break;
				case "monsters":
					break;
				}

			}

		} catch (ParserConfigurationException e) {
			// Error creating builder
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// Error getting input
			e.printStackTrace();
		} catch (SAXException e) {
			// Error parsing
			e.printStackTrace();
		} catch (IOException e) {
			// Error parsing
			e.printStackTrace();
		}

	}

}
