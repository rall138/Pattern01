package pattern01.helpers.definitiongen.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class PatternDefinitionParser {

	private static final String tabspace = "\t";
	private String groupName = "Default";
	private List<Element> collected_elements = new ArrayList<>();
	private Map<CommonElement, Boolean> classes_marked_as_child = new HashMap<>();
	
	public PatternDefinitionParser(){}
	
	public List<Element> parseDefinition(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler(){
				//Lo mantenemos en este scope para poder asignar los hijos mas inmediatos.
				private CommonElement element = null;
				private LoggerThread log = new LoggerThread();
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					
					//Generacion de elementos padres
					if(qName.equalsIgnoreCase("element")){
						element = new CommonElement();
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("name")){
								element.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("prettyname")){
								element.setPrettyName(attributes.getValue(index));
							}
						}
						// Parent elements are always uniques
						element.setUnique(true);
						log.writeSingleMessage("Parsed element: "+element.getPrettyName());
					}else if(qName.equalsIgnoreCase("childelement")){
						//Asignamos solamente el nombre porque tenemos nada mas que la referencia.
						CommonElement childElement = new CommonElement(element);
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("ref")){
								childElement.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("unique")){
								childElement.setUnique(Boolean
										.parseBoolean(attributes.getValue(index)));
							}
						}
						log.writeSingleMessage(tabspace+"-Parsed child: "+childElement.getName());
						classes_marked_as_child.put(childElement, Boolean.TRUE);
						element.getChildElements_collection().add(childElement);
					}else if(qName.equalsIgnoreCase("attributeelement")){
						Attribute attr = new Attribute();
						//Dejamos por defecto el default value vacio.
						attr.setDefault_value("");
						for (int index = 0; index < attributes.getLength(); index++){
							if(attributes.getQName(index).equalsIgnoreCase("name")){
								attr.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("prettyname")){
								attr.setPrettyName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("requiered")){
								attr.setRequiered(Boolean.parseBoolean(attributes.getValue(index)));
							}else if(attributes.getQName(index).equalsIgnoreCase("type")){
								attr.setType(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("default")){
								attr.setDefault_value(attributes.getValue(index));
							}
							//El nombre del grupo del ultimo groupElement.
							attr.setGroup(groupName);
						}
						log.writeSingleMessage(tabspace+"-Parsed Attribute: "+attr.getPrettyName());
						element.getAttribute_collection().add(attr);
					}else if(qName.equalsIgnoreCase("attributegroup")){
						for(int index = 0; index < attributes.getLength(); index++){
							if(attributes.getQName(index).equalsIgnoreCase("prettyName")){
								groupName = attributes.getValue(index);
							}
						}
					}
						
					//Adherimos el elemento solo si no existe.
					addElementIfnotExists(element);
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					super.characters(ch, start, length);
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if(qName.equalsIgnoreCase("attributegroup")){
						groupName = "Default";
					}
				}
				
			};
			parser.parse(CommonPathFix
					.getHardCodedPath(PATH_NAME.PATTERNDEFINITION_XML)
					.getPath(), handler);				
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		return this.collected_elements;
	}
	
	private void addElementIfnotExists(CommonElement element){
		if(element != null && classes_marked_as_child.get(element) == null){
			collected_elements.add(element);
		}
	}

}
