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
	private static final String collectionPrefix = "collection_";
	private static final String javaListNamespace = "java.util.List";
	private static final String javaArrayListNamespace = "java.util.ArrayList";	
	private static final String elementNamespace = "pattern01.helpers.temporal_containers.Element";
	
	private BuildFileRule bfr = new BuildFileRule();
	private List<Element> collected_elements = new ArrayList<>();
	

	public void execute(){
		parsePatternDefinition();
		//Se anaden los datos faltantes a los childselements creados
		childElementsFix();
		generateAll();
	}
	
	private void parsePatternDefinition(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			

			DefaultHandler handler = new DefaultHandler(){
				//Lo mantenemos en este scope para poder asignar los hijos mas inmediatos.
				private Element element = null;
				
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
						System.out.println("Created element: " + element.getName());
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
						System.out.println("child element to add: " + childElement.getName());
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
						System.out.println("Attributes to add: " +attr.getPrettyName());
						element.getAttribute_collection().add(attr);
					}
					
					
					Element auxiliarElement = get(element.getName());
					if(auxiliarElement == null){
						//Verificamos si se encuentra dentro de la colección, porque estamos corroborando comienzo de tags
						// se generan más elementos de los debidos.
						addElementIfnotExists(element);
					}else{
						//Reemplazamos el elemento.
						auxiliarElement = element;
					}
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					// TODO Auto-generated method stub
					super.characters(ch, start, length);
				}
				
			};
			//Cambiar cuando se necesita para otro OS.
			parser.parse(linux_generalConfigPath, handler);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
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
	
	private Element get(String name){
		boolean itemFound = false;
		int index = 0;
		while(!itemFound && index < collected_elements.size()){
			if(collected_elements.get(index).getName() == name){
				itemFound = true;
			}else{
				index++;
			}
		}
		if(itemFound)
			return collected_elements.get(index);
		else
			return null;
	}
	
	private void childElementsFix(){
		boolean itemFound = false;
		int jindex = 0;
		ArrayList<Element> coleccion_auxiliar_parent = new ArrayList<>(collected_elements);
		//Parent elements
		for(int index = 0; index < collected_elements.size(); index++){
			ArrayList<Element> collected_childs = 
					new ArrayList<Element>(collected_elements.get(index).getChildElements_collection());
			//Child of parents
			for(int hindex = 0; hindex < collected_childs.size(); hindex++){
				Element childElement = collected_childs.get(hindex);
				Element parentElement = null;

				//Busqueda dentro de los parents
				itemFound = false;
				jindex = 0;
				while(!itemFound && jindex < coleccion_auxiliar_parent.size()){
					parentElement = coleccion_auxiliar_parent.get(jindex);					
					if (parentElement.getName().equalsIgnoreCase(childElement.getName())){
						itemFound = true;
					}else{
						jindex ++;
					}
				}
				if(itemFound && parentElement != null){
					childElement.setPrettyName(parentElement.getPrettyName());
					childElement.setAttribute_collection(parentElement.getAttribute_collection());
				}
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
			builder.appendLn("public class "+collected_elements.get(index).getPrettyName()+"{");
			attrBuilder = new CustomStringBuilder();
			System.out.println("ClassName: "+collected_elements.get(index).getPrettyName());
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
				System.out.println("Child name: "+child.getPrettyName());
				//Si es colecci�n
				if(!child.isUnique()){
					attrBuilder.appendLn(tabspace+"private "+javaListNamespace+"<"+elementNamespace+">"+
								" collection_"+child.getPrettyName()+" = new "+javaArrayListNamespace+"<>();");
					//Setter for collection
					getterSetterBuilder.appendLn(tabspace+"public void setCollection_"+child.getPrettyName()+
							"("+javaListNamespace+"<"+elementNamespace+"> "+collectionPrefix+child.getPrettyName()+"){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"this."+collectionPrefix+child.getPrettyName()+
							" = "+collectionPrefix+child.getPrettyName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
					getterSetterBuilder.appendLn("");					

					//Getter for collection
					getterSetterBuilder.appendLn(tabspace+"public "+javaListNamespace+"<"+elementNamespace+"> getCollection_"+
							child.getPrettyName()+"(){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"return this."+collectionPrefix+child.getPrettyName()+";");
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
			generateClasses(collected_elements.get(index).getPrettyName(), builder.toString());		
		}
	}
	

	private void generateClasses(String className, String classBody){
		bfr.configureProject("ClassGenerator.xml");
		bfr.getProject().setNewProperty("filename", "generated/"+className+".java");
		bfr.getProject().setNewProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
}