package pattern01.helpers.definitiongen;

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
import pattern01.helpers.temporal_containers.Attribute;
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
	private CustomStringBuilder builder = null;
	private CustomStringBuilder attributeBuilder = null;
	private CustomStringBuilder getterAndSetterBuilder = null;
	private CustomStringBuilder attributePropertiesBuilder = new CustomStringBuilder();
	
	public void execute(){
		parsePatternDefinition();
		generateClasses(this.patterninstanceElement);
		log.writeSingleMessage(" ============================================================== ");
		generateNodeTypeClass();
		log.writeSingleMessage(" ============================================================== ");
		generatePropertyFile();
		log.writeSingleMessage(" ============================================================== ");
		generatePreferencesDialogs();
		log.writeSingleMessage(" ============================================================== ");		
		generatePatternInstanceClass();
		log.writeSingleMessage(" ============================================================== ");
		generateMenuFactoryClass();
		log.writeSingleMessage(" ============================================================== ");
	}
	
	private void parsePatternDefinition(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		PatternDefinitionParser parser = new PatternDefinitionParser();
		patterninstanceElement = parser.parseDefinition();
	}
	
	private void generateClasses(Element element){
		if (element != null){
			initializeCustomStringBuilders();
			generateClassHeader(element);
			generateAttributes(element);
			generateGettersAndSettersOfReferences(element);
			generatePropertyGetter();
			marshallerHeader(element);
			marshallerBuilder(element);
			marshallerFooter(element);
//			unmarshallerHeader(element);
//			unmarshallerBuilder(element);
//			unmarshallerFooter(element);
			generateGenericSetter(element);
			builder.append(attributeBuilder.toString());
			builder.append(getterAndSetterBuilder.toString());
			builder.appendLn("}");
			generateClasses(element.getPrettyName(), builder.toString());
			
			for (Element childElement : element.getChildElements_collection()){
				generateClasses(childElement);
			}
		}
	}
	
	private void initializeCustomStringBuilders(){
		this.builder = new CustomStringBuilder();
		this.attributeBuilder = new CustomStringBuilder();
		this.getterAndSetterBuilder = new CustomStringBuilder();
	}
	
	private void generateClassHeader(Element element){
		log.writeSingleMessage("<<< Generating class "+element.getPrettyName()+" >>>");
		builder = new CustomStringBuilder();
		builder.appendLn("package pattern01.helpers.generated;");
		builder.clrlf();
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class "+element.getPrettyName()+" implements IPatternElement{");
	}
	
	private void generateAttributes(Element element){
		for(Attribute attr : element.getAttribute_collection()){
			if (attr.isCustomAttribute()){
				String processedValue = DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value());
				String processedType = DataTypeConversion.getProcessedType(attr.getType());
				generateCustomValueBasedProperties(processedType, processedValue, attr);
			}else{
				attributeBuilder.appendLn(1, "private "+attr.getType()+" "+attr.getName()+
						(attr.getDefault_value() == null || attr.getDefault_value().equalsIgnoreCase("")?"":" = "+
								DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value()))+";");
				
				generateGetterAndSettersOfAttributes(attr);
			}
			generateAttributeProperties(element, attr);
		}
	}
	
	private void generateAttributeProperties(Element element, Attribute attr){
		if (attr.getGroup() == null || attr.getGroup().equals("")){
			attributePropertiesBuilder.appendLn(element.getPrettyName()+"."+attr.getPrettyName()+".Group=Default");
		}else if (attr.getGroup() != null){
			attributePropertiesBuilder.appendLn(element.getPrettyName()+"."+attr.getPrettyName()+".Group="+attr.getGroup());
		}
	}
	
	private void generateGetterAndSettersOfAttributes(Attribute attr){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(1, "public "+attr.getType()+" get"+attr.getPrettyName()+"(){");
		getterAndSetterBuilder.appendLn(2, "return this."+attr.getName()+";");
		getterAndSetterBuilder.appendLn(1, "}");
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(1, "public void set"+attr.getPrettyName()+"("+attr.getType()+" "+attr.getName()+"){");
		getterAndSetterBuilder.appendLn(2, "this."+attr.getName()+" = "+attr.getName()+";");
		getterAndSetterBuilder.appendLn(1, "}");
	}
	
	private void generateGettersAndSettersOfReferences(Element element){
		for (Element childElement : element.getChildElements_collection()){
			if(!childElement.isUnique()){
				attributeBuilder.appendLn(1, "private "+javaListNamespace+"<"+childElement.getPrettyName()+">"+
							" collection_"+childElement.getPrettyName()+" = new "+javaArrayListNamespace+"<>();");
				//Setter for collection
				getterAndSetterBuilder.appendLn(1, "public void setCollection_"+childElement.getPrettyName()+
						"("+javaListNamespace+"<"+childElement.getPrettyName()+"> "+
						collectionPrefix+childElement.getPrettyName()+"){");
				getterAndSetterBuilder.appendLn(2, "this."+collectionPrefix+childElement.getPrettyName()+
						" = "+collectionPrefix+childElement.getPrettyName()+";");
				getterAndSetterBuilder.appendLn(1, "}");
				getterAndSetterBuilder.clrlf();
	
				//Getter for collection
				getterAndSetterBuilder.appendLn(1, "public "+javaListNamespace+"<"+childElement.getPrettyName()+
						"> getCollection_"+childElement.getPrettyName()+"(){");
				getterAndSetterBuilder.appendLn(2, "return this."+
						collectionPrefix+childElement.getPrettyName()+";");
				getterAndSetterBuilder.appendLn(1, "}");
			}else{
				attributeBuilder.appendLn(1, "private "+childElement.getPrettyName()+" "+childElement.getName()+ ";");					
				getterAndSetterBuilder.clrlf();
				getterAndSetterBuilder.appendLn(1, "public "+childElement.getPrettyName()+
						" get"+childElement.getName()+"(){");
				getterAndSetterBuilder.appendLn(2, "return this."+childElement.getName()+";");
				getterAndSetterBuilder.appendLn(1, "}");
				getterAndSetterBuilder.clrlf();
				getterAndSetterBuilder.appendLn(1, "public void set"+childElement.getPrettyName()+
						"("+childElement.getPrettyName()+" "
				+childElement.getName()+"){");
				getterAndSetterBuilder.appendLn(2, "this."+childElement.getName()+
						" = "+childElement.getName()+";");
				getterAndSetterBuilder.appendLn(1, "}");
			}
		}
	}
	
	//Properties from .property file
	private void generatePropertyGetter(){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(1, "public java.lang.String getProperty(java.lang.String propertyName){");
		getterAndSetterBuilder.appendLn(2, "java.util.Properties properties = new java.util.Properties();");
		getterAndSetterBuilder.appendLn(2, "try{");
		getterAndSetterBuilder.appendLn(tabGen(3)+"java.io.FileInputStream input = new java.io.FileInputStream(\"Custom.properties\");");
		getterAndSetterBuilder.appendLn(tabGen(3)+"properties.load(input);");
		getterAndSetterBuilder.appendLn(2, "} catch (java.io.IOException e) {");
		getterAndSetterBuilder.appendLn(tabGen(3)+"e.printStackTrace(System.err);");
		getterAndSetterBuilder.appendLn(2, "}");
		getterAndSetterBuilder.appendLn(2, "return properties.getProperty(propertyName);");
		getterAndSetterBuilder.appendLn(1, "}");
	}
	
	private void generateCustomValueBasedProperties(String processedType, String processedValue, Attribute attr){
		List<Element> collected_custom_values = (new CustomValuesDefinitionParser()).parseDefinition();
		if (collected_custom_values.size() > 0){
			int index = 0;
			boolean itemFound = false;
			while(index < collected_custom_values.size() && !itemFound){
				if (collected_custom_values.get(index).getName().equalsIgnoreCase(processedType)){
					attributeBuilder.appendLn(1, "private "+collected_custom_values.get(index).getPrettyName()
							+" "+attr.getName()+" = "+processedValue+";");
					itemFound = true;
				}else{
					index++;
				}
			}
			if (itemFound){
				getterAndSetterBuilder.clrlf();
				getterAndSetterBuilder.appendLn(1, "public "+collected_custom_values.get(index).getPrettyName()
						+" get"+attr.getPrettyName()+"(){");
				getterAndSetterBuilder.appendLn(2, "return this."+attr.getName()+";");
				getterAndSetterBuilder.appendLn(1, "}");
				getterAndSetterBuilder.clrlf();
				
				getterAndSetterBuilder.appendLn(1, "public void set"+attr.getPrettyName()
						+"("+collected_custom_values.get(index).getPrettyName()+" "+attr.getName()+"){");
				getterAndSetterBuilder.appendLn(2, "this."+attr.getName()+" = "+attr.getName()+";");
				getterAndSetterBuilder.appendLn(1, "}");
			}else{
				log.writeSingleMessage("[Exception]:No custom type {"+processedType+"} definition declared!");
			}
		}
	}
	
	private void marshallerHeader(Element element){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(1, "@Override");
		getterAndSetterBuilder.appendLn(1, "public java.lang.String toXml(){");
		getterAndSetterBuilder.appendLn(2, "java.lang.String xml="+quotscape+"<"+element.getPrettyName()+" "+quotscape+";");
		for(Attribute attr : element.getAttribute_collection()){
			getterAndSetterBuilder.appendLn(2, "xml+="+quotscape+attr.getName()+"=\'"+quotscape+"+this."+attr.getName());
			getterAndSetterBuilder.append("+"+quotscape+"\' "+quotscape+";");
		}
		getterAndSetterBuilder.appendLn(2,"xml+="+quotscape+">"+quotscape+";");
	}
	
	private void marshallerBuilder(Element element){
		for(Element childElement : element.getChildElements_collection()){
			if(childElement.isUnique()){
				getterAndSetterBuilder.appendLn(2, "xml+=this."+childElement.getName()+".toXml();");
			}else{
				getterAndSetterBuilder.appendLn(2, "for(int index = 0; index < "+collectionPrefix+
						childElement.getPrettyName()+".size(); index++){");
				getterAndSetterBuilder.appendLn(tabGen(3)+"xml+="+collectionPrefix+
						childElement.getPrettyName()+".get(index).toXml();");
				getterAndSetterBuilder.appendLn(2, "}");
			}
		}
	}
	
	private void marshallerFooter(Element element){
		getterAndSetterBuilder.appendLn(2, "xml+="+quotscape+"</"+element.getPrettyName()+">"+quotscape);
		getterAndSetterBuilder.append(";");
		getterAndSetterBuilder.appendLn(2, "return xml;");		
		getterAndSetterBuilder.appendLn(1, "}");
	}
	
//	private void unmarshallerHeader(Element element){
//		getterAndSetterBuilder.clrlf();
//		getterAndSetterBuilder.appendLn(1, "@Override");
//		getterAndSetterBuilder.appendLn(1, "public IPatternElement fromXml(java.lang.String xmlDocument){");
//	}
//	
//	private void unmarshallerBuilder(Element element){
//		getterAndSetterBuilder.appendLn(2, "java.lang.String xpathuri = ");
//		getterAndSetterBuilder.append(quotscape+element.getXPathURI()+quotscape+";");
//	}
//	
//	private void unmarshallerFooter(Element element){
//		getterAndSetterBuilder.appendLn(2, "return null;");
//		getterAndSetterBuilder.appendLn(1, "}");
//	}
	
	private void generateGenericSetter(Element element){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(1, "@Override");
		getterAndSetterBuilder.appendLn(1, "public void setGenericElement(Object o){");
		if (element.getChildElements_collection().size() > 0){
			Element aux = element.getChildElements_collection().get(0);
			getterAndSetterBuilder.appendLn(2, "if (o instanceof "+element.getPrettyName()+"){");
			if (aux.isUnique())
				getterAndSetterBuilder.appendLn(3, "this.set"+aux.getPrettyName()+"(("+aux.getPrettyName()+")o);");
			else
				getterAndSetterBuilder.appendLn(3, "this.getCollection_"+aux.getPrettyName()+"().add(("+aux.getPrettyName()+")o);");
			
			for(int index=1; index < element.getChildElements_collection().size(); index++){
				aux = element.getChildElements_collection().get(index);
				getterAndSetterBuilder.appendLn(2, "}else if (o instanceof "+aux.getPrettyName()+"){");
				if (aux.isUnique())
					getterAndSetterBuilder.appendLn(3, "this.set"+aux.getPrettyName()+"(("+aux.getPrettyName()+")o);");
				else
					getterAndSetterBuilder.appendLn(3, "this.getCollection_"+aux.getPrettyName()+"().add(("+aux.getPrettyName()+")o);");
			}
			getterAndSetterBuilder.appendLn(2, "}");
		}
		getterAndSetterBuilder.appendLn(1, "}");			
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
	
	private void generatePreferencesDialogs(){
		PreferencesDialogGenerator pdgen = 
				new PreferencesDialogGenerator(this.patterninstanceElement);
		pdgen.execute();
	}
	
	private void generateNodeTypeClass(){
		NodeTypeEnumGenerator ntgen = new NodeTypeEnumGenerator(this.patterninstanceElement);
		ntgen.execute();
	}
	
	private void generatePatternInstanceClass(){
		PatternInstanceParserGenerator pipgen = new PatternInstanceParserGenerator(this.patterninstanceElement);
		pipgen.execute();
	}
	
	private void generateMenuFactoryClass(){
		MenuFactoryGenerator mnfgen = new MenuFactoryGenerator(this.patterninstanceElement);
		mnfgen.execute();
	}
	
	private void generatePropertyFile(){
		//Generamos el file de propiedades
		PropertyHelper propertyHelper = new PropertyHelper(); 
		
		String propertieFilePath = CommonPathFix
				.getHardCodedPath(PATH_NAME.CUSTOMPROPERTIES_PROPERTIES).getPath();
		
		propertyHelper.impactPropertiesOnFile(propertieFilePath, attributePropertiesBuilder.toString());
	}
}