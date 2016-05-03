package pattern01.helpers.definitiongen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;
import pattern01.helpers.temporal_containers.EnumElement;

public class CustomValuesDefinitionParser {
	
	public CustomValuesDefinitionParser(){}
	
	public List<Element> parseDefinition(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		List<Element> collected_elements = new ArrayList<>();
		try {
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler(){

				Element customValueElement = new EnumElement();
				boolean isElementTag = false;
				String valueName = "";
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					if(qName.equalsIgnoreCase("customvalue")){
						customValueElement.setName(getValue(attributes, "name"));
						isElementTag = true;
					}else if(qName.equalsIgnoreCase("value")){
						isElementTag = false;
						valueName = getValue(attributes, "name");
					}

				}
				
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					String valueDescription = new String(ch, start, length);
					((EnumElement)customValueElement).getValue_list()
						.put(valueName, valueDescription);
				}

				private String getValue(Attributes attributes, String qName){
					String value = "";
					if (attributes.getValue(qName) != null){
						value = attributes.getValue(qName);
					}
					return value;
				}
				
			};

		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return collected_elements;		
	}
}
