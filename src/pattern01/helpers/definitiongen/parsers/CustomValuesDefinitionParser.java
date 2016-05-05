package pattern01.helpers.definitiongen.parsers;

import java.io.File;
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
import pattern01.helpers.LoggerThread;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;
import pattern01.helpers.temporal_containers.EnumElement;

public class CustomValuesDefinitionParser {
	
	private List<Element> collected_elements = null;
	public CustomValuesDefinitionParser(){}
	
	public List<Element> parseDefinition(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		collected_elements = new ArrayList<>();
		try {
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler(){

				private LoggerThread log = new LoggerThread();
				Element customValueElement = new EnumElement();
				String valueName = "";
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					if(qName.equalsIgnoreCase("customvalue")){
						log.writeSingleMessage("Parsed element: "+getValue(attributes, "prettyName"));
						customValueElement.setName(getValue(attributes, "name"));
						customValueElement.setPrettyName(getValue(attributes, "prettyName"));
						addElementIfnotExists(customValueElement);
					}else if(qName.equalsIgnoreCase("value")){ //Valores dentro de la defincion de opciones
						valueName = getValue(attributes, "name");
						log.writeSingleMessage("Parsed value: "+valueName);
					}
				}
				
				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					String valueDescription = new String(ch, start, length);
					if (valueDescription.trim().compareTo("")!=0){
						((EnumElement)customValueElement).getValue_list()
							.put(valueName, valueDescription);
						log.writeSingleMessage("Value description: "+valueName+" "+valueDescription);
					}
				}

				private String getValue(Attributes attributes, String qName){
					String value = "";
					if (attributes.getValue(qName) != null){
						value = attributes.getValue(qName);
					}
					return value;
				}
				
			};
			
			parser.parse(new File(CommonPathFix
					.getHardCodedPath(PATH_NAME.CUSTOMVALUESDEFINITION)), 
					handler);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return collected_elements;		
	}
	
	private void addElementIfnotExists(Element element){
		if(element != null){
			boolean itemFound = false;
			int index = 0;
			while(!itemFound && index < collected_elements.size()){
				if(collected_elements.get(index).getName() == element.getName()){
					itemFound = true;
				}else{
					index++;
				}
			}
			if(!itemFound){
				collected_elements.add(element);
			}
		}
	}
	
	
	
}
