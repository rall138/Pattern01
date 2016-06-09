package pattern01.helpers.definitiongen.parsers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;

public class PatternDefinitionParser2 {

	private String groupName = "Default";
	private LoggerThread log = new LoggerThread();
	private List<Element> collected_elements = new ArrayList<>();
	private XPath xpath = XPathFactory.newInstance().newXPath();
	private String expression = "";
	
	public PatternDefinitionParser2(){}
	
	public List<Element> parseDefinition(){
		expression = "/PatternDefinition/Element[@name='patterninstance'][1]"; //First element
		try {
			URI classInstanceXml_uri = CommonPathFix.getHardCodedPath(PATH_NAME.PATTERNDEFINITION_XML);
			InputSource is = new InputSource(classInstanceXml_uri.getPath());
			
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node firstNodeElement = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (firstNodeElement != null){
				CommonElement parentElement = new CommonElement();
				recursiveParseing(parentElement, firstNodeElement, is);
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
 
		return this.collected_elements;
	}
	
//	private void recursiveParseing(NodeList childNodes, int index, CommonElement parentElement, InputSource is) throws XPathExpressionException{
	private void recursiveParseing(CommonElement parentElement, Node actualNode, InputSource is) throws XPathExpressionException{
		NodeList childNodes = actualNode.getChildNodes();
		log.writeSingleMessage("Entrando en recursive: "+childNodes.getLength());
		if (actualNode.hasChildNodes()){
			for (int index = 0; index < childNodes.getLength(); index++){
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("attributeelement")){
					attributeAttacher(parentElement, childNodes.item(index).getAttributes());
				}else if (childNodes.item(index).getNodeName().equalsIgnoreCase("childelement")){
					expression = "/PatternInstance/Element[@name='"+
							childNodes.item(index).getAttributes().getNamedItem("ref").getNodeValue()+"']";
					recursiveParseing(parentElement, (Node)xpath.evaluate(expression, is, XPathConstants.NODE), is);
				}
			}
		}

	}
	
	/*
	private void recursiveParseing(CommonElement parentElement, Node actualNode, InputSource is) throws XPathExpressionException{
		log.writeSingleMessage("Analizing element: "+actualNode.getAttributes().getNamedItem("name").getNodeValue());
		element = new CommonElement();
		if ()
		
		
		if (actualNode.hasChildNodes()){
			for (int index = 0; index < actualNode.getChildNodes().getLength(); index++){
				Node childNode = actualNode.getChildNodes().item(index);
				if (childNode.getNodeName().equalsIgnoreCase("childelement")){
					log.writeSingleMessage("Searching for child element: "+childNode.getAttributes().getNamedItem("ref").getNodeValue());
					expression = "/PatternDefinition/Element[@name='"+childNode.getAttributes().getNamedItem("ref").getNodeValue()+"']";
					//Recursion over childs
					recursiveParseing(element, (Node) xpath.evaluate(expression, is, XPathConstants.NODE), is);
				}else if (childNode.getNodeName().equalsIgnoreCase("attributeelement")){
					log.writeSingleMessage("Analizing attribute: "+childNode.getAttributes().getNamedItem("name").getNodeValue());
					attributeAttacher(element, actualNode.getAttributes());
				}
			}
		}else{
			parentElement.getChildElements_collection().add(element);
		}
		collected_elements.add(element);
	}	*/

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
		co.getAttribute_collection().add(attr);
	}
	
}
