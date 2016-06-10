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
	private Element patterninstanceElement = null;
	private CustomStringBuilder builder = new CustomStringBuilder();
	private CustomStringBuilder attributeBuilder = new CustomStringBuilder();
	private CustomStringBuilder getterAndSetterBuilder = new CustomStringBuilder();
	
	public void execute(){
		parsePatternDefinition();
		generateClasses(patterninstanceElement);
		/*generatePatterEditorClasses();
		generateNodeTypeClass();
		generatePatternInstanceClass();*/
	}
	
	private void parsePatternDefinition(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		PatternDefinitionParser2 parser = new PatternDefinitionParser2();
		patterninstanceElement = parser.parseDefinition();
	}
	
	
	private void generateClasses(Element element){
		if (element != null){
			CommonElement commonElement = (CommonElement)element;
			generateClassHeader(commonElement);
			generateAttributes(commonElement);
			generateGettersAndSettersOfReferences(commonElement);
			generatePropertyGetter();
			builder.append(attributeBuilder.toString());
			builder.append(getterAndSetterBuilder.toString());
			builder.appendLn("}");
			generateClasses(element.getPrettyName(), builder.toString());
		}
		
		/*
		//Generamos el file de propiedades
		propertyHelper.impactPropertiesOnFile(CommonPathFix
				.getHardCodedPath(PATH_NAME.CUSTOMPROPERTIES_PROPERTIES).getPath());*/
	}
	
	private void generateClassHeader(Element element){
		log.writeSingleMessage("Generating class: "+element.getPrettyName());
		builder = new CustomStringBuilder();
		builder.appendLn("package pattern01.helpers.generated;");
		builder.clrlf();
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class "+element.getPrettyName()+"{");
	}
	
	private void generateAttributes(Element element){
		for(Attribute attr : element.getAttribute_collection()){
			if (attr.getType().contains("#{")){
//				String processedValue = DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value());
//				String processedType = DataTypeConversion.getProcessedType(attr.getType());
//				generateCustomValueBasedProperties(processedType, processedValue, attr.getName());
			}else{
				attributeBuilder.appendLn(tabGen(1)+"private "+attr.getType()+" "+attr.getName()+
						(attr.getDefault_value() == null || attr.getDefault_value().equalsIgnoreCase("")?"":"="+
								DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value()))+";");
				
				generateGetterAndSettersOfAttributes(attr);
			}
		}
	}
	
	private void generateGetterAndSettersOfAttributes(Attribute attr){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(tabGen(1)+"public "+attr.getType()+" get"+attr.getPrettyName()+"(){");
		getterAndSetterBuilder.appendLn(tabGen(2)+"return this."+attr.getName()+";");
		getterAndSetterBuilder.appendLn(tabGen(1)+"}");
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(tabGen(1)+"public void set"+attr.getPrettyName()+"("+attr.getType()+" "+attr.getName()+"){");
		getterAndSetterBuilder.appendLn(tabGen(2)+"this."+attr.getName()+" = "+attr.getName()+";");
		getterAndSetterBuilder.appendLn(tabGen(1)+"}");
	}
	
	private void generateGettersAndSettersOfReferences(Element element){
		for (Element childElement : element.getChildElements_collection()){
			if(!childElement.isUnique()){
				attributeBuilder.appendLn(tabGen(1)+"private "+javaListNamespace+"<"+childElement.getPrettyName()+">"+
							" collection_"+childElement.getPrettyName()+" = new "+javaArrayListNamespace+"<>();");
				//Setter for collection
				getterAndSetterBuilder.appendLn(tabGen(1)+"public void setCollection_"+childElement.getPrettyName()+
						"("+javaListNamespace+"<"+childElement.getPrettyName()+"> "+
						collectionPrefix+childElement.getPrettyName()+"){");
				getterAndSetterBuilder.appendLn(tabGen(2)+"this."+collectionPrefix+childElement.getPrettyName()+
						" = "+collectionPrefix+childElement.getPrettyName()+";");
				getterAndSetterBuilder.appendLn(tabGen(1)+"}");
				getterAndSetterBuilder.clrlf();
	
				//Getter for collection
				getterAndSetterBuilder.appendLn(tabGen(1)+"public "+javaListNamespace+"<"+childElement.getPrettyName()+
						"> getCollection_"+childElement.getPrettyName()+"(){");
				getterAndSetterBuilder.appendLn(tabGen(2)+"return this."+
						collectionPrefix+childElement.getPrettyName()+";");
				getterAndSetterBuilder.appendLn(tabGen(1)+"}");
			}else{
				attributeBuilder.appendLn(tabGen(1)+"private "+childElement.getPrettyName()+" "+childElement.getName()+ ";");					
				getterAndSetterBuilder.clrlf();
				getterAndSetterBuilder.appendLn(tabGen(1)+"public "+childElement.getPrettyName()+
						" get"+childElement.getName()+"(){");
				getterAndSetterBuilder.appendLn(tabGen(2)+"return this."+childElement.getName()+";");
				getterAndSetterBuilder.appendLn(tabGen(1)+"}");
				getterAndSetterBuilder.clrlf();
				getterAndSetterBuilder.appendLn(tabGen(1)+"public void set"+childElement.getPrettyName()+
						"("+childElement.getPrettyName()+" "
				+childElement.getName()+"){");
				getterAndSetterBuilder.appendLn(tabGen(2)+"this."+childElement.getPrettyName()+
						" = "+childElement.getName()+";");
				getterAndSetterBuilder.appendLn(tabGen(1)+"}");
			}
		}
	}
	
	//Properties from .property file
	private void generatePropertyGetter(){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(tabGen(1)+"public java.lang.String getProperty(java.lang.String propertyName){");
		getterAndSetterBuilder.appendLn(tabGen(2)+"java.util.Properties properties = new java.util.Properties();");
		getterAndSetterBuilder.appendLn(tabGen(2)+"try{");
		getterAndSetterBuilder.appendLn(tabGen(3)+"java.io.FileInputStream input = new java.io.FileInputStream(\"Custom.properties\");");
		getterAndSetterBuilder.appendLn(tabGen(3)+"properties.load(input);");
		getterAndSetterBuilder.appendLn(tabGen(2)+"} catch (java.io.IOException e) {");
		getterAndSetterBuilder.appendLn(tabGen(3)+"e.printStackTrace(System.err);");
		getterAndSetterBuilder.appendLn(tabGen(2)+"}");
		getterAndSetterBuilder.appendLn(tabGen(2)+"return properties.getProperty(propertyName);");
		getterAndSetterBuilder.appendLn(tabGen(1)+"}");
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
	/*
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
	}*/
}