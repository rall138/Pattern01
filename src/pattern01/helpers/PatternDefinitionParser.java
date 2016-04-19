package pattern01.helpers;

import java.io.IOException;
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

	public PatternDefinitionParser(){
		
	}
	
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
						
						log.addMessage("Parsed element: "+element.getPrettyName());
						log.run();
						log.clearMessages();
						
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
						
						log.addMessage(tabspace+"-Parsed child: "+childElement.getName());
						log.run();
						log.clearMessages();
						
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
						
						log.addMessage(tabspace+"-Parsed Attribute: "+attr.getPrettyName());
						log.run();
						log.clearMessages();
						
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
			parser.parse(windows_generalConfigPath, handler);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
