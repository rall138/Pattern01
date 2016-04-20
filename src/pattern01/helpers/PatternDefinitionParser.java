package pattern01.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.Element;

public class PatternDefinitionParser {

	private static final String linux_generalConfigPath = 
			System.getProperty("user.dir").replace("src/", "")
			.replace("pattern01/", "").replace("/helpers", "")
			+ "/GeneralConfig/PatternDefinition.xml";


	private static final String windows_generalConfigPath = 
			System.getProperty("user.dir").replace("src\\", "")
			.replace("pattern01\\", "").replace("\\helpers", "")
			+ "\\GeneralConfig\\PatternDefinition.xml";

	private static final String tabspace = "\t";
	private String groupName = "Default";	
	private List<Element> collected_elements = new ArrayList<>();
	
	public PatternDefinitionParser(){}
	
	public List<Element> parseDefinition(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler(){
				//Lo mantenemos en este scope para poder asignar los hijos mas inmediatos.
				private Element element = null;
				private LoggerThread log = new LoggerThread();
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					
					//Generacion de elementos padres
			
					if(qName.equalsIgnoreCase("element")){
						element = new Element();
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("name")){
								element.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("prettyname")){
								element.setPrettyName(attributes.getValue(index));
							}
						}
						log.writeSingleMessage("Parsed element: "+element.getPrettyName());
					}else if(qName.equalsIgnoreCase("childelement")){
						//Asignamos solamente el nombre porque tenemos nada mas que la referencia.
						Element childElement = new Element();
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("ref")){
								childElement.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("unique")){
								childElement.setUnique(Boolean
										.parseBoolean(attributes.getValue(index)));
							}
						}
						log.writeSingleMessage(tabspace+"-Parsed child: "+childElement.getName());
						element.getChildElements_collection().add(childElement);						
					}else if(qName.equalsIgnoreCase("attributeelement")){
						Attribute attr = new Attribute();
						for (int index = 0; index < attributes.getLength(); index++){
							if(attributes.getQName(index).equalsIgnoreCase("name")){
								attr.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("prettyname")){
								attr.setPrettyName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("requiered")){
								attr.setRequiered(Boolean.parseBoolean(attributes.getValue(index)));
							}else if(attributes.getQName(index).equalsIgnoreCase("type")){
								attr.setType(attributes.getValue(index));
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
					//Adherimos el elemento si no existe.
					addElementIfnotExists(element);
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					// TODO Auto-generated method stub
					super.characters(ch, start, length);
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if(qName.equalsIgnoreCase("attributegroup")){
						groupName = "Default";
					}
				}
				
			};
			//Cambiar cuando se necesita para otro OS.
			parser.parse(linux_generalConfigPath, handler);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		return this.collected_elements;
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
