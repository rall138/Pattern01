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
	
	private static final String tabspace = "\t";
	private static final String javaListNamespace = "java.util.List";
	private static final String elementNamespace = "pattern01.helpers.temporal_containers.Element";
	
	private List<Element> collected_elements = new ArrayList<>();
	

	public void execute(){
		parsePatternDefinition();
		generateAll();
	}
	
	private void parsePatternDefinition(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			

			DefaultHandler handler = new DefaultHandler(){
				//Lo mantenemos en este scope para poder asignar los hijos mas inmediatos.
				private Element element;
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					
					//Generaci锟絥 de elementos padres
			
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
						//Asignamos solamente el nombre porque tenemos nada mas que la referencia.
						for (int index = 0; index < attributes.getLength(); index++){
							Element childElement = new Element();							
							if (attributes.getQName(index).equalsIgnoreCase("ref")){
								childElement.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("unique")){
								childElement.setUnique(Boolean
										.parseBoolean(attributes.getValue(index)));								
							}
							element.getChildElements_collection().add(childElement);
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
						element.getAttribute_collection().add(attr);
					}
					if(element != null){
						collected_elements.add(element);
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
	}
	
	private void generateAll(){
		StringBuilder builder = new StringBuilder();
		StringBuilder attrBuilder;
		StringBuilder getterSetterBuilder;
		for(int index = 0; index < collected_elements.size(); index++){
			builder.append("public class " + collected_elements.get(index).getName() + "{");
			attrBuilder = new StringBuilder();
			getterSetterBuilder = new StringBuilder();
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				attrBuilder.append(tabspace+"private "+attr.getType()+" "+attr.getPrettyName() + ";");
				getterSetterBuilder.append(tabspace+"public "+attr.getType()+" get"+attr.getPrettyName()+"(){");
				getterSetterBuilder.append(tabspace+tabspace+"return this." + attr.getPrettyName() + ";");
				getterSetterBuilder.append(tabspace+"}");
				getterSetterBuilder.append("");
				getterSetterBuilder.append(tabspace+"public void set"+attr.getPrettyName()+"("+attr.getType()+" "+attr.getPrettyName()+"){");
				getterSetterBuilder.append(tabspace+tabspace+"this."+attr.getPrettyName()+" = "+attr.getPrettyName()+";");
				getterSetterBuilder.append(tabspace+"}");
			}
			for(Element child : collected_elements.get(index).getChildElements_collection()){
				//Si es colecci髇
				if(!child.isUnique()){
					attrBuilder.append(tabspace+javaListNamespace+"<"+elementNamespace+">"+
								" collection_"+child.getName()+" = new "+javaListNamespace+"ArrayList();");
					//Setter for collection
					getterSetterBuilder.append(tabspace+"public void setCollection_"+child.getPrettyName()+
							"("+javaListNamespace+"<"+elementNamespace+"> collection_" +child.getPrettyName()+"){");
					getterSetterBuilder.append(tabspace+tabspace+"this."+child.getPrettyName()+
							" = "+child.getPrettyName()+";");
					getterSetterBuilder.append(tabspace+"}");
					getterSetterBuilder.append("");					
					//Getter for collection
					getterSetterBuilder.append(tabspace+"public "+javaListNamespace+"<"+elementNamespace+"> getCollection_"+
							child.getPrettyName()+"(){");
					getterSetterBuilder.append(tabspace+tabspace+"return this."+child.getPrettyName()+";");
					getterSetterBuilder.append(tabspace+"}");

				}else if(child.isUnique()){
					attrBuilder.append(tabspace+"private "+elementNamespace+" "+child.getPrettyName() + ";");					
					getterSetterBuilder.append(tabspace+"public "+elementNamespace+" get"+child.getPrettyName()+"(){");
					getterSetterBuilder.append(tabspace+tabspace+"return this." + child.getPrettyName() + ";");
					getterSetterBuilder.append(tabspace+"}");
					getterSetterBuilder.append("");
					getterSetterBuilder.append(tabspace+"public void set"+child.getPrettyName()+"("+elementNamespace+" "+child.getPrettyName()+"){");
					getterSetterBuilder.append(tabspace+tabspace+"this."+child.getPrettyName()+" = "+child.getPrettyName()+";");
					getterSetterBuilder.append(tabspace+"}");
				}
			}
			builder.append(attrBuilder);
			builder.append("");
			builder.append(getterSetterBuilder);
			builder.append("}");
		}
		System.out.println(builder.toString());		
	}
	
	
	/* Hay que hacer la l贸gica para completar la informaci贸n faltante de los childelements dentro de un element,
	 * luego ver si la relaci贸n es simple o compleja para generar dependencia com煤n o colecci贸n.
	 * */
	
}