package pattern01.helpers.definitiongen.parsers;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.DataTypeConversion;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;

public class PatternDefinitionParser2 {

	private static final String tabspace = "\t";
	private String groupName = "Default";
	private List<Element> collected_elements = new ArrayList<>();
	private XPath xpath = XPathFactory.newInstance().newXPath();
	private CommonElement element = null;
	private String expression = "";
	
	public PatternDefinitionParser2(){}
	
	public List<Element> parseDefinition(){
		expression = "/Element[1]"; //First element
		try {
			URI classInstanceXml_uri = CommonPathFix.getHardCodedPath(PATH_NAME.PATTERNDEFINITION_XML);
			InputSource is = new InputSource(classInstanceXml_uri.getPath());
			
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node firstNodeElement = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (firstNodeElement != null){
				recursiveParseing(firstNodeElement, is);
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
 
		return this.collected_elements;
	}
	
	private void recursiveParseing(Node actualNode, InputSource is) throws XPathExpressionException{
		element = new CommonElement();
		if (actualNode.hasChildNodes()){
			for (int index = 0; index < actualNode.getChildNodes().getLength(); index++){
				Node childNode = actualNode.getChildNodes().item(index);
				if (childNode.getNodeName().equalsIgnoreCase("ChildElement")){
					expression = "Element[@name='"+childNode.getAttributes().getNamedItem("name")+"']";
					//Recursion over childs
					recursiveParseing((Node) xpath.evaluate(expression, is, XPathConstants.NODE), is);
				}else if (childNode.getNodeName().equalsIgnoreCase("AttributeElement")){
					attributeAttacher(element, actualNode.getAttributes());
				}
			}
		}
	}

	private void attributeAttacher(CommonElement co, NamedNodeMap attribute_node_collection){
		Attribute attr = new Attribute();
		for (int hindex = 0; hindex < attribute_node_collection.getLength(); hindex++){
			Node attributeNode = attribute_node_collection.item(hindex);
			if (attributeNode.getNodeName().equalsIgnoreCase("name")){
				attr.setName(attributeNode.getNodeValue());
			}else if (attributeNode.getNodeName().equalsIgnoreCase("prettyname")){
				attr.setPrettyName(attributeNode.getNodeValue());
			}else if (attributeNode.getNodeName().equalsIgnoreCase("type")){
				attr.setType(attributeNode.getNodeValue()); //TODO - Ver como convertir con DataTypeConversion
			}else if (attributeNode.getNodeName().equalsIgnoreCase("requiered")){
				attr.setRequiered(Boolean.valueOf(attributeNode.getNodeValue()));
			}else if (attributeNode.getNodeName().equalsIgnoreCase("default")){
				attr.setDefault_value(attributeNode.getNodeValue());
			}
		}		
	}
	
}
