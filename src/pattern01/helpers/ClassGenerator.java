package pattern01.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tools.ant.BuildFileRule;
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
	private static final String newline = System.getProperty("line.separator");
	private static final String javaListNamespace = "java.util.List";
	private static final String javaArrayListNamespace = "java.util.ArrayList";	
	private static final String elementNamespace = "pattern01.helpers.temporal_containers.Element";
	
	private BuildFileRule bfr = new BuildFileRule();
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
						Element childElement = new Element();
						for (int index = 0; index < attributes.getLength(); index++){
							if (attributes.getQName(index).equalsIgnoreCase("ref")){
								childElement.setName(attributes.getValue(index));
							}else if(attributes.getQName(index).equalsIgnoreCase("unique")){
								childElement.setUnique(Boolean
										.parseBoolean(attributes.getValue(index)));								
							}
						}
						element.getChildElements_collection().add(childElement);						
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
	
	private void childElementsFix(){
		for(int index = 0; index < collected_elements.size(); index++){
			for(Element child: collected_elements.get(index).getChildElements_collection()){
				
			}
		}
		
	}
	
	private void generateAll(){
		CustomStringBuilder builder;
		CustomStringBuilder attrBuilder;
		CustomStringBuilder getterSetterBuilder;
		for(int index = 0; index < collected_elements.size(); index++){
			builder = new CustomStringBuilder();
			builder.appendLn("package pattern01.helpers.generated;");
			builder.appendLn("");
			builder.appendLn("public class " + collected_elements.get(index).getPrettyName() + "{");
			attrBuilder = new CustomStringBuilder();
			getterSetterBuilder = new CustomStringBuilder();
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				attrBuilder.appendLn(tabspace+"private "+attr.getType()+" "+attr.getPrettyName() + ";");
				getterSetterBuilder.appendLn(tabspace+"public "+attr.getType()+" get"+attr.getPrettyName()+"(){");
				getterSetterBuilder.appendLn(tabspace+tabspace+"return this." + attr.getPrettyName() + ";");
				getterSetterBuilder.appendLn(tabspace+"}");
				getterSetterBuilder.appendLn("");
				getterSetterBuilder.appendLn(tabspace+"public void set"+attr.getPrettyName()+"("+attr.getType()+" "+attr.getPrettyName()+"){");
				getterSetterBuilder.appendLn(tabspace+tabspace+"this."+attr.getPrettyName()+" = "+attr.getPrettyName()+";");
				getterSetterBuilder.appendLn(tabspace+"}");
			}
			for(Element child : collected_elements.get(index).getChildElements_collection()){
				//Si es colecci髇
				if(!child.isUnique()){
					attrBuilder.appendLn(tabspace+"private "+javaListNamespace+"<"+elementNamespace+">"+
								" collection_"+child.getName()+" = new "+javaArrayListNamespace+"();");
					//Setter for collection
					getterSetterBuilder.appendLn(tabspace+"public void setCollection_"+child.getPrettyName()+
							"("+javaListNamespace+"<"+elementNamespace+"> collection_" +child.getPrettyName()+"){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"this."+child.getPrettyName()+
							" = "+child.getPrettyName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
					getterSetterBuilder.appendLn("");					

					//Getter for collection
					getterSetterBuilder.appendLn(tabspace+"public "+javaListNamespace+"<"+elementNamespace+"> getCollection_"+
							child.getPrettyName()+"(){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"return this."+child.getPrettyName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
				}else if(child.isUnique()){
					attrBuilder.appendLn(tabspace+"private "+elementNamespace+" "+child.getPrettyName() + ";");					
					getterSetterBuilder.appendLn(tabspace+"public "+elementNamespace+" get"+child.getPrettyName()+"(){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"return this." + child.getPrettyName() + ";");
					getterSetterBuilder.appendLn(tabspace+"}");
					getterSetterBuilder.appendLn("");
					getterSetterBuilder.appendLn(tabspace+"public void set"+child.getPrettyName()+"("+elementNamespace+" "+child.getPrettyName()+"){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"this."+child.getPrettyName()+" = "+child.getPrettyName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
				}
			}
			builder.appendLn(attrBuilder.toString());
			builder.appendLn("");
			builder.appendLn(getterSetterBuilder.toString());
			builder.appendLn("}");
			generateClasses(collected_elements.get(index).getName(), builder.toString());		
		}
	}
	

	private void generateClasses(String className, String classBody){
		bfr.configureProject("ClassGenerator.xml");
		bfr.getProject().setNewProperty("filename", "generated/"+className+".java");
		bfr.getProject().setNewProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
	/* Hay que hacer la l贸gica para completar la informaci贸n faltante de los childelements dentro de un element,
	 * luego ver si la relaci贸n es simple o compleja para generar dependencia com煤n o colecci贸n.
	 * */
	
}