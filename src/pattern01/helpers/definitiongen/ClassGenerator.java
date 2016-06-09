package pattern01.helpers.definitiongen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.DataTypeConversion;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.PropertyHelper;
import pattern01.helpers.definitiongen.parsers.CustomValuesDefinitionParser;
import pattern01.helpers.definitiongen.parsers.PatternDefinitionParser;
import pattern01.helpers.definitiongen.parsers.PatternDefinitionParser2;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;

public class ClassGenerator extends Task{

	private static final String tabspace = "\t";
	private static final String quotscape = "\"";
	private static final String collectionPrefix = "collection_";
	private static final String javaListNamespace = "java.util.List";
	private static final String javaArrayListNamespace = "java.util.ArrayList";
	private static final String classHeaderComment = 
			tabspace+"/**\n"
			+tabspace+"* Generated class via ClassGenerator.xml\n"
			+tabspace+"* Creation date: "+Calendar.getInstance().getTime()+"\n"
			+tabspace+"* Creator: rlomez\n"
			+tabspace+"**/";

	private LoggerThread log = new LoggerThread();
	private BuildFileRule bfr = new BuildFileRule();
	private List<Element> collected_elements = new ArrayList<>();
	private CustomStringBuilder builder = new CustomStringBuilder();
	private CustomStringBuilder attributeBuilder = new CustomStringBuilder();
	private CustomStringBuilder getterAndSetterBuilder = new CustomStringBuilder();
	
	public void execute(){
		parsePatternDefinition();
		//Se anaden los datos faltantes a los childselements creados
		childElementsFix();
		generateClasses();
		generatePatterEditorClasses();
		generateNodeTypeClass();
		generatePatternInstanceClass();
	}
	
	private void parsePatternDefinition(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		PatternDefinitionParser2 parser = 
				new PatternDefinitionParser2();
		collected_elements = parser.parseDefinition();
	}
	
	private void childElementsFix(){
		boolean itemFound = false;
		int jindex = 0;
		ArrayList<Element> coleccion_auxiliar_parent = new ArrayList<>(collected_elements);
		//Parent elements
		for(int index = 0; index < collected_elements.size(); index++){
			ArrayList<CommonElement> collected_childs = 
					new ArrayList<CommonElement>(collected_elements.get(index).getChildElements_collection());
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
	
	private void generateClasses(){
		LoggerThread log = new LoggerThread();
		
		/*
		log.writeSingleMessage("*** File generation ***");
	
		CustomStringBuilder builder;
		CustomStringBuilder attributeBuilder;
		CustomStringBuilder getterSetterBuilder;
		*/
		PropertyHelper propertyHelper = new PropertyHelper();
		/*
		for(int index = 0; index < collected_elements.size(); index++){
			log.writeSingleMessage("Generating class: "+collected_elements.get(index).getPrettyName());
			builder = new CustomStringBuilder();
			builder.appendLn("package pattern01.helpers.generated;");
			builder.clrlf();
			builder.appendLn(classHeaderComment);
			builder.appendLn("public class "+collected_elements.get(index).getPrettyName()+"{");
			attributeBuilder = new CustomStringBuilder();
			getterSetterBuilder = new CustomStringBuilder();
			attributeBuilder.clrlf();

			//Getters and setters y propiedades para el .properties de atributos generales.
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				
				if (attr.getType().contains("#{")){
					String processedType = attr.getType()
							.substring(attr.getType().indexOf("#{")+2, attr.getType().indexOf("}"));
					
					String defaultValue = processedType+"."+attr.getDefault_value()
							.substring(attr.getDefault_value().lastIndexOf(".")+1, 
									attr.getDefault_value().indexOf("}"));
					
					generateCustomValueBasedProperties(attributeBuilder, getterSetterBuilder, 
							processedType, attr.getName(), defaultValue);
				}else{
					generateGetterAndSettersOfAttributes(attributeBuilder, getterSetterBuilder, 
							attr.getName(), attr.getPrettyName(), attr.getType(), attr.getDefault_value());
				}
				
				//Generamos las propiedades de los agrupadores
				propertyHelper.putProperty(collected_elements.get(index).getPrettyName()+"."+
				attr.getPrettyName()+".Group", attr.getGroup());				
			}

			//Getters and setters generales 
			for(CommonElement child : collected_elements.get(index).getChildElements_collection()){
				generateGettersAndSettersOfReferences(attributeBuilder, getterSetterBuilder, child.getName(),
						child.getPrettyName(),child.isUnique());
			}
			
			//Getter [Grupo]
			generatePropertyGetter(getterSetterBuilder);
			
			builder.appendLn(attributeBuilder.toString());
			builder.appendLn(getterSetterBuilder.toString());
			
			//Xml marshaller
			marshallerBuilder(builder, collected_elements.get(index),
					collected_elements.get(index).getChildElements_collection());
			
			builder.appendLn("}");
			generateClasses(collected_elements.get(index).getPrettyName(), builder.toString());
		}
		*/
		
		if (collected_elements.size() > 0){
			recursiveClassIteration((CommonElement)collected_elements.get(0), 0);
		
			//Generamos el file de propiedades
			propertyHelper.impactPropertiesOnFile(CommonPathFix
					.getHardCodedPath(PATH_NAME.CUSTOMPROPERTIES_PROPERTIES).getPath());
		}
	}
	
	private void recursiveClassIteration(CommonElement co, int index){
		generateClassHeader(co);
		generateAttributes(co.getPrettyName(), co.getAttribute_collection());
		generatePropertyGetter();
		log.writeSingleMessage("Generating class: "+co.getPrettyName());
		
		if (co.getChildElements_collection() != null &&
				co.getChildElements_collection().size() > 0){
			
			for (CommonElement child: co.getChildElements_collection()){
				generateGettersAndSettersOfReferences(child);
			}

			builder.append(attributeBuilder.toString());
			builder.append(getterAndSetterBuilder.toString());
//			generateClasses(co.getPrettyName(), builder.toString());
		
			for (CommonElement child: co.getChildElements_collection()){
				recursiveClassIteration(child, index);				
			}
			
		}else if ((index + 1) < collected_elements.size()){
			builder.append(attributeBuilder.toString());
			builder.append(getterAndSetterBuilder.toString());
			generateClasses(co.getPrettyName(), builder.toString());			
			recursiveClassIteration((CommonElement)collected_elements.get(++index), index);
		}
	}
	
	private void generateClassHeader(CommonElement co){
		log.writeSingleMessage("Generating class: "+co.getPrettyName());
		builder = new CustomStringBuilder();
		builder.appendLn("package pattern01.helpers.generated;");
		builder.clrlf();
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class "+co.getPrettyName()+"{");
	}
	
	private void generateAttributes(String prettyName, List<Attribute> attribute_collection){
		PropertyHelper propertyHelper = new PropertyHelper();
		for(Attribute attr : attribute_collection){
			if (attr.getType().contains("#{")){
//				String processedValue = DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value());
//				String processedType = DataTypeConversion.getProcessedType(attr.getType());
//				generateCustomValueBasedProperties(processedType, processedValue, attr.getName());
			}else{
				generateGetterAndSettersOfAttributes(attr);
			}
			//Generamos las propiedades de los agrupadores
			propertyHelper.putProperty(prettyName+"."+attr.getPrettyName()+".Group", attr.getGroup());				
		}
	}
	
	private void generateGetterAndSettersOfAttributes(Attribute attr){
		attributeBuilder.appendLn(tabGen(1)+"private "+attr.getType()+" "+attr.getName()+
				(attr.getDefault_value().equalsIgnoreCase("")?"":"="+
						DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value()))+";");
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public "+attr.getType()+" get"+attr.getPrettyName()+"(){");
		builder.appendLn(tabGen(2)+"return this."+attr.getName()+";");
		builder.appendLn(tabGen(1)+"}");
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public void set"+attr.getPrettyName()+"("+attr.getType()+" "+attr.getName()+"){");
		builder.appendLn(tabGen(2)+"this."+attr.getName()+" = "+attr.getName()+";");
		builder.appendLn(tabGen(1)+"}");
	}
	
	private void generateGettersAndSettersOfReferences(CommonElement co){
		if(!co.isUnique()){
			attributeBuilder.appendLn(tabGen(1)+"private "+javaListNamespace+"<"+co.getPrettyName()+">"+
						" collection_"+co.getPrettyName()+" = new "+javaArrayListNamespace+"<>();");
			//Setter for collection
			builder.appendLn(tabGen(1)+"public void setCollection_"+co.getPrettyName()+
					"("+javaListNamespace+"<"+co.getPrettyName()+"> "+collectionPrefix+co.getPrettyName()+"){");
			builder.appendLn(tabGen(2)+"this."+collectionPrefix+co.getPrettyName()+
					" = "+collectionPrefix+co.getPrettyName()+";");
			builder.appendLn(tabGen(1)+"}");
			builder.clrlf();

			//Getter for collection
			builder.appendLn(tabGen(1)+"public "+javaListNamespace+"<"+co.getPrettyName()+"> getCollection_"+
					co.getPrettyName()+"(){");
			builder.appendLn(tabGen(2)+"return this."+collectionPrefix+co.getPrettyName()+";");
			builder.appendLn(tabGen(1)+"}");
		}else{
			attributeBuilder.appendLn(tabGen(1)+"private "+co.getPrettyName()+" "+co.getName()+ ";");					
			builder.clrlf();
			builder.appendLn(tabGen(1)+"public "+co.getPrettyName()+" get"+co.getName()+"(){");
			builder.appendLn(tabGen(2)+"return this."+co.getName()+";");
			builder.appendLn(tabGen(1)+"}");
			builder.clrlf();
			builder.appendLn(tabGen(1)+"public void set"+co.getPrettyName()+"("+co.getPrettyName()+" "
			+co.getName()+"){");
			builder.appendLn(tabGen(2)+"this."+co.getPrettyName()+" = "+co.getName()+";");
			builder.appendLn(tabGen(1)+"}");
		}
	}
	
	//Properties from .property file
	private void generatePropertyGetter(){
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public java.lang.String getProperty(java.lang.String propertyName){");
		builder.appendLn(tabGen(2)+"java.util.Properties properties = new java.util.Properties();");
		builder.appendLn(tabGen(2)+"try{");
		builder.appendLn(tabGen(3)+"java.io.FileInputStream input = new java.io.FileInputStream(\"Custom.properties\");");
		builder.appendLn(tabGen(3)+"properties.load(input);");
		builder.appendLn(tabGen(2)+"} catch (java.io.IOException e) {");
		builder.appendLn(tabGen(3)+"e.printStackTrace(System.err);");
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(2)+"return properties.getProperty(propertyName);");
		builder.appendLn(tabGen(1)+"}");
	}
	
//	private void generateCustomValueBasedProperties(CustomStringBuilder attrBuilder, 
//			CustomStringBuilder builder, String processedType, String attrName, String processedDefaultValue){
//		List<Element> collected_custom_values = (new CustomValuesDefinitionParser()).parseDefinition();
//		if (collected_custom_values.size() > 0){
//			int index = 0;
//			boolean itemFound = false;
//			while(index < collected_custom_values.size() && !itemFound){
//				if (collected_custom_values.get(index).getName().equalsIgnoreCase(processedType)){
//					attrBuilder.appendLn(tabGen(1)+"private "+collected_custom_values.get(index).getPrettyName()
//							+" "+attrName+(processedDefaultValue.isEmpty()?";" : " = "+processedDefaultValue+";"));
//					itemFound = true;
//				}else{
//					index++;
//				}
//			}
//			builder.appendLn(tabGen(1)+"public "+collected_custom_values.get(index).getPrettyName()
//					+" get"+collected_custom_values.get(index).getPrettyName()+"(){");
//			builder.appendLn(tabGen(2)+"return this."+attrName+";");
//			builder.appendLn(tabGen(1)+"}");
//			builder.clrlf();
//			builder.appendLn(tabGen(1)+"public void set"+collected_custom_values.get(index).getPrettyName()
//					+"("+collected_custom_values.get(index).getPrettyName()+" "+collected_custom_values.get(index).getName()+"){");
//			builder.appendLn(tabGen(2)+"this."+attrName+" = "+collected_custom_values.get(index).getName()+";");
//			builder.appendLn(tabGen(1)+"}");
//			
//		}
//	}

	private void generateCustomValueBasedProperties(String processedType, String processedValue, String attrName){
		List<Element> collected_custom_values = (new CustomValuesDefinitionParser()).parseDefinition();
		if (collected_custom_values.size() > 0){
			int index = 0;
			boolean itemFound = false;
			while(index < collected_custom_values.size() && !itemFound){
				if (collected_custom_values.get(index).getName().equalsIgnoreCase(processedType)){
					attributeBuilder.appendLn(tabGen(1)+"private "+collected_custom_values.get(index).getPrettyName()
							+" "+attrName+"="+processedValue+";");
					itemFound = true;
				}else{
					index++;
				}
			}
			builder.appendLn(tabGen(1)+"public "+collected_custom_values.get(index).getPrettyName()
					+" get"+collected_custom_values.get(index).getPrettyName()+"(){");
			builder.appendLn(tabGen(2)+"return this."+attrName+";");
			builder.appendLn(tabGen(1)+"}");
			builder.clrlf();
			builder.appendLn(tabGen(1)+"public void set"+collected_custom_values.get(index).getPrettyName()
					+"("+collected_custom_values.get(index).getPrettyName()+" "+collected_custom_values.get(index).getName()+"){");
			builder.appendLn(tabGen(2)+"this."+attrName+" = "+collected_custom_values.get(index).getName()+";");
			builder.appendLn(tabGen(1)+"}");
			
		}
	}
	
	private void marshallerBuilder(CustomStringBuilder builder, Element element,
			List<CommonElement> childElementCollection){
		builder.appendLn(tabGen(1)+"public java.lang.String toXml(){");
		builder.appendLn(tabGen(2)+"java.lang.String xml ="+quotscape+"<"+element.getPrettyName()+" "+quotscape);
		for(int index = 0; index < element.getAttribute_collection().size(); index++){
			Attribute attr = element.getAttribute_collection().get(index);
			builder.appendLn(tabGen(2)+"+ "+quotscape+attr.getName()+"=\'"+quotscape+"+this."+attr.getName()+"+"
					+quotscape+"\'"+quotscape+"");
		}
		builder.appendLn(tabGen(2)+"+ "+quotscape+">"+quotscape+";");
		for(int index = 0; index < childElementCollection.size(); index++){
			if(childElementCollection.get(index).isUnique()){
				builder.appendLn(tabGen(3)+"xml+=this."+childElementCollection.get(index).getName()+".toXml();");
			}else{
				builder.appendLn(tabGen(3)+"for(int index = 0; index < "+collectionPrefix+
						childElementCollection.get(index).getPrettyName()+".size(); index++){");
				builder.appendLn(tabGen(4)+"xml+="+collectionPrefix
						+childElementCollection.get(index).getPrettyName()+".get(index).toXml();");
				builder.appendLn(tabGen(3)+"}");
			}
		}
		builder.appendLn(tabGen(2)+"xml+="+quotscape+"</"+element.getPrettyName()+">"+quotscape+";");
		builder.appendLn(tabGen(2)+"return xml;");		
		builder.appendLn(tabGen(1)+"}");
	}
	
	private String tabGen(int quantity){
		String tabappender = "";
		for(int total = 0; total < quantity; total++){
			tabappender += tabspace;
		}
		return tabappender;
	}
	
	private void generateClasses(String className, String classBody){
		bfr.getProject().setProperty("filename", "../generated/"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
	private void generatePatterEditorClasses(){
		EditorPartGenerator epgen = new EditorPartGenerator(collected_elements);
		epgen.execute();
	}
	
	private void generateNodeTypeClass(){
		NodeTypeEnumGenerator ntgen = new NodeTypeEnumGenerator(collected_elements);
		ntgen.execute();
	}
	
	private void generatePatternInstanceClass(){
		PatternInstanceParserGenerator pipgen = new PatternInstanceParserGenerator(collected_elements);
		pipgen.execute();
	}
}