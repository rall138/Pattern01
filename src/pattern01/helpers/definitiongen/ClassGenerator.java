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
import pattern01.helpers.definitiongen.parsers.PatternDefinitionParser2;
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
		generatePatternEditorClasses();
		log.writeSingleMessage(" ============================================================== ");
		generatePatternInstanceClass();
		log.writeSingleMessage(" ============================================================== ");
	}
	
	private void parsePatternDefinition(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		PatternDefinitionParser2 parser = new PatternDefinitionParser2();
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
			unmarshallerHeader(element);
			unmarshallerBuilder(element);
			unmarshallerFooter(element);
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
			if (attr.getType().contains("#{")){
				String processedValue = DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value());
				String processedType = DataTypeConversion.getProcessedType(attr.getType());
				generateCustomValueBasedProperties(processedType, processedValue, attr.getName());
			}else{
				attributeBuilder.appendLn(tabGen(1)+"private "+attr.getType()+" "+attr.getName()+
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
				getterAndSetterBuilder.appendLn(tabGen(2)+"this."+childElement.getName()+
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
			if (itemFound){
				getterAndSetterBuilder.appendLn(tabGen(1)+"public "+collected_custom_values.get(index).getPrettyName()
						+" get"+collected_custom_values.get(index).getPrettyName()+"(){");
				getterAndSetterBuilder.appendLn(tabGen(2)+"return this."+attrName+";");
				getterAndSetterBuilder.appendLn(tabGen(1)+"}");
				getterAndSetterBuilder.clrlf();
				getterAndSetterBuilder.appendLn(tabGen(1)+"public void set"+collected_custom_values.get(index).getPrettyName()
						+"("+collected_custom_values.get(index).getPrettyName()+" "+collected_custom_values.get(index).getName()+"){");
				getterAndSetterBuilder.appendLn(tabGen(2)+"this."+attrName+" = "+collected_custom_values.get(index).getName()+";");
				getterAndSetterBuilder.appendLn(tabGen(1)+"}");
			}else{
				log.writeSingleMessage("[Exception]:No custom type {"+processedType+"} definition declared!");
			}
		}
	}
	
	private void marshallerHeader(Element element){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(tabGen(1)+"@Override");
		getterAndSetterBuilder.appendLn(tabGen(1)+"public java.lang.String toXml(){");
		getterAndSetterBuilder.appendLn(tabGen(2)+"java.lang.String xml="+quotscape+"<"+element.getPrettyName()+" "+quotscape+";");
		for(Attribute attr : element.getAttribute_collection()){
			getterAndSetterBuilder.appendLn(tabGen(2)+"xml+="+quotscape+attr.getName()+"=\'"+quotscape+"+this."+attr.getName());
			getterAndSetterBuilder.append("+"+quotscape+"\'"+quotscape+";");
		}
	}
	
	private void marshallerBuilder(Element element){
		for(Element childElement : element.getChildElements_collection()){
			if(childElement.isUnique()){
				getterAndSetterBuilder.appendLn(tabGen(2)+"xml+=this."+childElement.getName()+".toXml();");
			}else{
				getterAndSetterBuilder.appendLn(tabGen(2)+"for(int index = 0; index < "+collectionPrefix+
						childElement.getPrettyName()+".size(); index++){");
				getterAndSetterBuilder.appendLn(tabGen(3)+"xml+="+collectionPrefix+
						childElement.getPrettyName()+".get(index).toXml();");
				getterAndSetterBuilder.appendLn(tabGen(2)+"}");
			}
		}
		getterAndSetterBuilder.appendLn(tabGen(2)+"xml+="+quotscape+">"+quotscape);
		getterAndSetterBuilder.append(";");
	}
	
	private void marshallerFooter(Element element){
		getterAndSetterBuilder.appendLn(tabGen(2)+"xml+="+quotscape+"</"+element.getPrettyName()+">"+quotscape);
		getterAndSetterBuilder.append(";");
		getterAndSetterBuilder.appendLn(tabGen(2)+"return xml;");		
		getterAndSetterBuilder.appendLn(tabGen(1)+"}");
	}
	
	private void unmarshallerHeader(Element element){
		getterAndSetterBuilder.clrlf();
		getterAndSetterBuilder.appendLn(tabGen(1)+"@Override");
		getterAndSetterBuilder.appendLn(tabGen(1)+"public IPatternElement fromXml(java.lang.String xmlDocument){");
	}
	
	private void unmarshallerBuilder(Element element){
		getterAndSetterBuilder.appendLn(tabGen(2)+"java.lang.String xpathuri = ");
		getterAndSetterBuilder.append(quotscape+element.getXPathURI()+quotscape+";");
	}
	
	private void unmarshallerFooter(Element element){
		getterAndSetterBuilder.appendLn(tabGen(2)+"return null;");
		getterAndSetterBuilder.appendLn(tabGen(1)+"}");
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
	
	private void generatePatternEditorClasses(){
		EditorPartGenerator epgen = new EditorPartGenerator(this.patterninstanceElement);
		epgen.execute();
	}
	
	private void generateNodeTypeClass(){
		NodeTypeEnumGenerator ntgen = new NodeTypeEnumGenerator(this.patterninstanceElement);
		ntgen.execute();
	}
	
	private void generatePatternInstanceClass(){
		PatternInstanceParserGenerator pipgen = new PatternInstanceParserGenerator(this.patterninstanceElement);
		pipgen.execute();
	}
	
	private void generatePropertyFile(){
		//Generamos el file de propiedades
		PropertyHelper propertyHelper = new PropertyHelper(); 
		
		String propertieFilePath = CommonPathFix
				.getHardCodedPath(PATH_NAME.CUSTOMPROPERTIES_PROPERTIES).getPath();
		
		propertyHelper.impactPropertiesOnFile(propertieFilePath, attributePropertiesBuilder.toString());
	}
}