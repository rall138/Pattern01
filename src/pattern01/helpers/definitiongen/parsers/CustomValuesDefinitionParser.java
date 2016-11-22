package pattern01.helpers.definitiongen.parsers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.Element;
import pattern01.helpers.temporal_containers.EnumElement;

public class CustomValuesDefinitionParser {
	
	private XPath xpath = XPathFactory.newInstance().newXPath();
	private String expression = "";
	private List<Element> collected_elements = new ArrayList<>();

	public CustomValuesDefinitionParser(){}
	
	public List<Element> parseDefinition(){
		expression = "/CustomValueDefinition/CustomValue";
		
		try {
			URI customvalues_uri = CommonPathFix.getHardCodedPath(PATH_NAME.CUSTOMVALUESDEFINITION);
			InputSource is = new InputSource(customvalues_uri.getPath());
			
			EnumElement element = null;
			
			NodeList nodes = (NodeList) xpath.evaluate(expression, is, XPathConstants.NODESET);
			for(int jindex =0; jindex < nodes.getLength(); jindex++){
				Node node = nodes.item(jindex);
				element = new EnumElement();
				element.setName(node.getAttributes().getNamedItem("name").getNodeValue());
				for (int index =0; index < node.getChildNodes().getLength(); index++){
					Node childNode = node.getChildNodes().item(index);
					if (childNode.hasAttributes() && childNode.getAttributes().getNamedItem("name") != null){
						element.getValue_list().put(childNode.getAttributes().getNamedItem("name").getNodeValue(), 
								childNode.getAttributes().getNamedItem("name").getNodeValue());
					}
				}
				collected_elements.add(element);
			}
			
		}catch(XPathException ex){
			ex.printStackTrace();
		}
		return collected_elements;
	}
	
	
	
}
