package pattern01.helpers.definitiongen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.PropertyHelper;
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
	
	private BuildFileRule bfr = new BuildFileRule();
	private List<Element> collected_elements = new ArrayList<>();

	public void execute(){
		parsePatternDefinition();
		//Se anaden los datos faltantes a los childselements creados
		childElementsFix();
		generateClasses();
	}
	
	private void parsePatternDefinition(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		//Se establece la propiedad de workspace para el build file 
		bfr.getProject().setProperty("binfolder", CommonPathFix
				.getHardCodedPath(PATH_NAME.BINFOLDER).getPath());
		
		PatternDefinitionParser parser = 
				new PatternDefinitionParser();
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
		
		log.writeSingleMessage("*** File generation ***");
	
		CustomStringBuilder builder;
		CustomStringBuilder attributeBuilder;
		CustomStringBuilder getterSetterBuilder;
		
		PropertyHelper propertyHelper = new PropertyHelper();

		for(int index = 0; index < collected_elements.size(); index++){
			log.writeSingleMessage("Generating class: "+collected_elements.get(index).getPrettyName());
			builder = new CustomStringBuilder();
			builder.appendLn("package pattern01.helpers.generated;");
			builder.appendLn("");
			builder.appendLn(classHeaderComment);
			builder.appendLn("public class "+collected_elements.get(index).getPrettyName()+"{");
			attributeBuilder = new CustomStringBuilder();
			
			getterSetterBuilder = new CustomStringBuilder();
			attributeBuilder.appendLn("");
			//Getters and setters y propiedades para el .properties de atributos generales.
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				generateGetterAndSettersOfAttributes(attributeBuilder, getterSetterBuilder, 
						attr.getName(), attr.getPrettyName(), attr.getType(), attr.getDefault_value());
				
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
			builder.appendLn("}");
			generateClasses(collected_elements.get(index).getPrettyName(), builder.toString());
		}
		
		//Generamos el file de propiedades
		propertyHelper.impactPropertiesOnFile(CommonPathFix
				.getHardCodedPath(PATH_NAME.CUSTOMPROPERTIES_PROPERTIES).getPath());
	}
	
	private void generateGetterAndSettersOfAttributes(CustomStringBuilder attributeBuilder, 
			CustomStringBuilder builder, String attrName, String attrPrettyName, String type,
			String default_value){
		attributeBuilder.appendLn(tabspace+"private "+type+" "+attrPrettyName+
				(default_value.equalsIgnoreCase("")?"":"="+quotscape+default_value+quotscape)+";");
		builder.appendLn("");
		builder.appendLn(tabspace+"public "+type+" get"+attrPrettyName+"(){");
		builder.appendLn(tabspace+tabspace+"return this."+attrPrettyName+";");
		builder.appendLn(tabspace+"}");
		builder.appendLn("");
		builder.appendLn(tabspace+"public void set"+attrPrettyName+"("+type+" "+attrPrettyName+"){");
		builder.appendLn(tabspace+tabspace+"this."+attrPrettyName+" = "+attrPrettyName+";");
		builder.appendLn(tabspace+"}");
	}
	
	private void generateGettersAndSettersOfReferences(CustomStringBuilder attributeBuilder, 
			CustomStringBuilder builder, String attrName, String attrPrettyName, boolean isUnique){
		if(!isUnique){
			attributeBuilder.appendLn(tabspace+"private "+javaListNamespace+"<"+attrPrettyName+">"+
						" collection_"+attrPrettyName+" = new "+javaArrayListNamespace+"<>();");
			//Setter for collection
			builder.appendLn(tabspace+"public void setCollection_"+attrPrettyName+
					"("+javaListNamespace+"<"+attrPrettyName+"> "+collectionPrefix+attrPrettyName+"){");
			builder.appendLn(tabspace+tabspace+"this."+collectionPrefix+attrPrettyName+
					" = "+collectionPrefix+attrPrettyName+";");
			builder.appendLn(tabspace+"}");
			builder.appendLn("");

			//Getter for collection
			builder.appendLn(tabspace+"public "+javaListNamespace+"<"+attrPrettyName+"> getCollection_"+
					attrPrettyName+"(){");
			builder.appendLn(tabspace+tabspace+"return this."+collectionPrefix+attrPrettyName+";");
			builder.appendLn(tabspace+"}");
		}else{
			attributeBuilder.appendLn(tabspace+"private "+attrPrettyName+" "+attrName + ";");					
			builder.appendLn("");
			builder.appendLn(tabspace+"public "+attrPrettyName+" get"+attrName+"(){");
			builder.appendLn(tabspace+tabspace+"return this."+attrName+";");
			builder.appendLn(tabspace+"}");
			builder.appendLn("");
			builder.appendLn(tabspace+"public void set"+attrPrettyName+"("+attrPrettyName+" "
			+attrName+"){");
			builder.appendLn(tabspace+tabspace+"this."+attrName+" = "+attrName+";");
			builder.appendLn(tabspace+"}");
		}
	}
	
	//Properties from .property file
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
		bfr.getProject().setProperty("filename", "../generated/"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
}