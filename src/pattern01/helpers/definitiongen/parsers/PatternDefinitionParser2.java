package pattern01.helpers.definitiongen.parsers;

import java.net.URI;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;

public class PatternDefinitionParser2 {

	private XPath xpath = XPathFactory.newInstance().newXPath();
	private String expression = "";
	
	public PatternDefinitionParser2(){}
	
	/***
	 * Convert from definition file to auxiliar structures [PatternDefinition.xml --> Element.class]
	 * @return Element
	 */
	public Element parseDefinition(){
		CommonElement parentElement = null;
		expression = "/PatternDefinition/Element[@name='patterninstance'][1]"; //First element
		try {
			URI classInstanceXml_uri = CommonPathFix.getHardCodedPath(PATH_NAME.PATTERNDEFINITION_XML);
			InputSource is = new InputSource(classInstanceXml_uri.getPath());
			
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node firstNodeElement = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (firstNodeElement != null){
				parentElement = new CommonElement();
				initializeCommonElement(parentElement, firstNodeElement);
				recursiveParseing(parentElement, firstNodeElement, is);
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
		return parentElement;
	}
	
	/***
	 * This is how i get all the defined elements at PatterDefinition file. 
	 * @param parentElement
	 * @param actualNode
	 * @param is
	 * @throws XPathExpressionException
	 */
	private void recursiveParseing(CommonElement parentElement, Node actualNode, InputSource is) throws XPathExpressionException{
		NodeList childNodes = actualNode.getChildNodes();
		if (actualNode.hasChildNodes()){
			for (int index = 0; index < childNodes.getLength(); index++){
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("attributeelement")){
					attributeAttacher(parentElement, childNodes.item(index).getAttributes());
				}else if (childNodes.item(index).getNodeName().equalsIgnoreCase("childelement")){
					expression = "/PatternDefinition/Element[@name='"+
							childNodes.item(index).getAttributes().getNamedItem("ref").getNodeValue()+"']";
					Node auxililarNode = (Node)xpath.evaluate(expression, is, XPathConstants.NODE);
					if (auxililarNode != null){
						CommonElement childElement = new CommonElement(parentElement);
						childElement.setXpathURI(auxililarNode.getNamespaceURI());
						initializeCommonElement(childElement, auxililarNode);
						parentElement.getChildElements_collection().add(childElement);
						recursiveParseing(childElement, auxililarNode, is);
					}
				}
			}
		}
	}
	
	//TODO Crear xmlns documento de declaración de espacio de nombres
	private void initializeCommonElement(CommonElement commonElement, Node nodeElement){
		commonElement.setXpathURI(nodeElement.getNamespaceURI());
		for (int hindex = 0; hindex < nodeElement.getAttributes().getLength(); hindex++){
			Node attributeNode = nodeElement.getAttributes().item(hindex);
			if (attributeNode.getNodeName().equalsIgnoreCase("name")){
				commonElement.setName(attributeNode.getNodeValue());
			}else if (attributeNode.getNodeName().equalsIgnoreCase("prettyname")){
				commonElement.setPrettyName(attributeNode.getNodeValue());
			}else if (attributeNode.getNodeName().equalsIgnoreCase("unique")){
				commonElement.setUnique(Boolean.valueOf(attributeNode.getNodeValue()));
			}
		}
	}
	
	private void attributeAttacher(CommonElement commonElement, NamedNodeMap attribute_node_collection){
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
			}else if (attributeNode.getNodeName().equalsIgnoreCase("group")){
				attr.setGroup(attributeNode.getNodeValue());
			}
		}
		commonElement.getAttribute_collection().add(attr);
	}
}
