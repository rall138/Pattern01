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
	
	private String groupName = "Default";
	

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
						System.out.println("Attributes to add: " +attr.getPrettyName());
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
		CustomStringBuilder attributeBuilder;
		CustomStringBuilder getterSetterBuilder;
		
		PropertyHelper propertyHelper = new PropertyHelper();

		for(int index = 0; index < collected_elements.size(); index++){
			builder = new CustomStringBuilder();
			builder.appendLn("package pattern01.helpers.generated;");
			builder.appendLn("");
			builder.appendLn("public class "+collected_elements.get(index).getPrettyName()+"{");
			attributeBuilder = new CustomStringBuilder();
			System.out.println("ClassName: "+collected_elements.get(index).getPrettyName());
			getterSetterBuilder = new CustomStringBuilder();
			attributeBuilder.appendLn("");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				attributeBuilder.appendLn(tabspace+"private "+attr.getType()+" "+attr.getPrettyName() + ";");
				getterSetterBuilder.appendLn(tabspace+"public "+attr.getType()+" get"+attr.getPrettyName()+"(){");
				getterSetterBuilder.appendLn(tabspace+tabspace+"return this." + attr.getPrettyName() + ";");
				getterSetterBuilder.appendLn(tabspace+"}");
				getterSetterBuilder.appendLn("");
				getterSetterBuilder.appendLn(tabspace+"public void set"+attr.getPrettyName()+"("+attr.getType()+" "+attr.getPrettyName()+"){");
				getterSetterBuilder.appendLn(tabspace+tabspace+"this."+attr.getPrettyName()+" = "+attr.getPrettyName()+";");
				getterSetterBuilder.appendLn(tabspace+"}");
				propertyHelper.putProperty(collected_elements.get(index).getPrettyName()+"."+
				attr.getPrettyName()+".Group", attr.getGroup());				
			}

			//Getter del grupo al que pertenece
			generatePropertyGetter(getterSetterBuilder);
			
			for(Element child : collected_elements.get(index).getChildElements_collection()){
				System.out.println("Child name: "+child.getPrettyName());
				//Si es coleccion
				if(!child.isUnique()){
					attributeBuilder.appendLn(tabspace+"private "+javaListNamespace+"<"+child.getPrettyName()+">"+
								" collection_"+child.getPrettyName()+" = new "+javaArrayListNamespace+"<>();");
					//Setter for collection
					getterSetterBuilder.appendLn(tabspace+"public void setCollection_"+child.getPrettyName()+
							"("+javaListNamespace+"<"+child.getPrettyName()+"> "+collectionPrefix+child.getPrettyName()+"){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"this."+collectionPrefix+child.getPrettyName()+
							" = "+collectionPrefix+child.getPrettyName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
					getterSetterBuilder.appendLn("");

					//Getter for collection
					getterSetterBuilder.appendLn(tabspace+"public "+javaListNamespace+"<"+child.getPrettyName()+"> getCollection_"+
							child.getPrettyName()+"(){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"return this."+collectionPrefix+child.getPrettyName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
				}else if(child.isUnique()){
					attributeBuilder.appendLn(tabspace+"private "+child.getPrettyName()+" "+child.getName() + ";");					
					getterSetterBuilder.appendLn(tabspace+"public "+child.getPrettyName()+" get"+child.getName()+"(){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"return this." + child.getName() + ";");
					getterSetterBuilder.appendLn(tabspace+"}");
					getterSetterBuilder.appendLn("");
					getterSetterBuilder.appendLn(tabspace+"public void set"+child.getPrettyName()+"("+child.getPrettyName()+" "
					+child.getName()+"){");
					getterSetterBuilder.appendLn(tabspace+tabspace+"this."+child.getName()+" = "+child.getName()+";");
					getterSetterBuilder.appendLn(tabspace+"}");
				}
			}
			builder.appendLn(attributeBuilder.toString());
			builder.appendLn("");
			builder.appendLn(getterSetterBuilder.toString());
			builder.appendLn("}");
			generateClasses(collected_elements.get(index).getPrettyName(), builder.toString());
			//Generamos el file de propiedades
			propertyHelper.impactPropertiesOnFile();
		}
	}
	
	private void generatePropertyGetter(CustomStringBuilder builder){
		builder.appendLn("");
		builder.appendLn(tabspace+"public java.lang.String getProperty(java.lang.String propertyName){");
		builder.appendLn(tabspace+tabspace+"java.util.Properties properties = new java.util.Properties();");
		builder.appendLn(tabspace+tabspace+"try{");
		builder.appendLn(tabspace+tabspace+tabspace+"java.io.FileInputStream input = new java.io.FileInputStream(\"Custom.properties\");");
		builder.appendLn(tabspace+tabspace+tabspace+"properties.load(input);");
		builder.appendLn(tabspace+tabspace+"} catch (java.io.IOException e) {");
		builder.appendLn(tabspace+tabspace+tabspace+"e.printStackTrace(System.err);");
		builder.appendLn(tabspace+tabspace+"}");
		builder.appendLn(tabspace+tabspace+"return properties.getProperty(propertyName);");
		builder.appendLn(tabspace+"}");
	}
	
	private void generateClasses(String className, String classBody){
		bfr.configureProject("ClassGenerator.xml");
		bfr.getProject().setNewProperty("filename", "generated/"+className+".java");
		bfr.getProject().setNewProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
}