package pattern01.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tools.ant.Task;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.Element;

public class ClassGenerator extends Task{

	private static final String linux_generalConfigPath = 
			System.getProperty("user.dir").replace("src/", "")
			.replace("pattern01/", "").replace("/helpers", "")
			+ "/GeneralConfig/PatternDefinition.xml";


	private static final String windows_generalConfigPath = 
			System.getProperty("user.dir").replace("src\\", "")
			.replace("pattern01\\", "").replace("\\helpers", "")
			+ "\\GeneralConfig\\PatternDefinition.xml";
	
	
	private List<Element> colected_elements = new ArrayList<>();

	public void execute(){
		parsePatternDefinition();
	}
	
	private StringBuilder parsePatternDefinition(){
		StringBuilder builder = new StringBuilder();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			

			DefaultHandler handler = new DefaultHandler(){
				//Lo mantenemos en este scope para poder asignar los hijos mas inmediatos.
				private Element element;
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					
					//Generación de elementos padres
			
					if(qName.equalsIgnoreCase("element")){
						element = new Element();
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("name")){
								element.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("prettyname")){
								element.setPrettyName(attributes.getValue(index));
							}
						}						
					}else if(qName.equalsIgnoreCase("childelement")){
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("ref")){
								//Asignamos solamente el nombre porque tenemos nada mas que la referencia.
								if (element != null){
									Element childElement = new Element();
									childElement.setName(attributes.getValue(index));
								}
							}
						}
					}else if(qName.equalsIgnoreCase("attribute")){
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
						}
					}
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					// TODO Auto-generated method stub
					super.characters(ch, start, length);
				}
				
			};
			//Cambiar cuando se necesita para otro OS.
			parser.parse(windows_generalConfigPath, handler);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void generateAll(){
		StringBuilder builder = new StringBuilder();
		for(int index = 0; index < colected_elements.size(); index++){
			
		}
	}
	
}