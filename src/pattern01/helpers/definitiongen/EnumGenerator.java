package pattern01.helpers.definitiongen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.definitiongen.parsers.CustomValuesDefinitionParser;
import pattern01.helpers.temporal_containers.Element;
import pattern01.helpers.temporal_containers.EnumElement;

public class EnumGenerator extends Task{
	
	/* Package reference, if you want to change generation location modify this */
	public static final String classPackage = "pattern01.helpers.generated";
	private static final String tabspace = "\t";
	private static final String quotscape = "\"";
	private static final String classHeaderComment = 
			tabspace+"/**\n"
			+tabspace+"* Generated enum class via ClassGenerator.xml\n"
			+tabspace+"* Creation date: "+Calendar.getInstance().getTime()+"\n"
			+tabspace+"* Creator: rlomez\n"
			+tabspace+"**/";
	
	private BuildFileRule bfr = new BuildFileRule();
	private CustomStringBuilder builder = null;
	private List<Element> collected_elements = new ArrayList<>();
	
	public void execute(){
		parseCustomValuesDefinition();
	}
	
	private void parseCustomValuesDefinition(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		//Parseing del archivo de definicion de custom values.
		CustomValuesDefinitionParser parser = new CustomValuesDefinitionParser();
		collected_elements = parser.parseDefinition();

		builder = new CustomStringBuilder();
		EnumElement enumElement = null;
		int hindex = 0;
		for(int index = 0; index < collected_elements.size(); index++){
			enumElement = (EnumElement)collected_elements.get(index);
			builder.appendLn("package "+Element.classPackage+";");
			builder.clrlf();
			builder.appendLn(classHeaderComment);
			builder.appendLn("public enum "+enumElement.getPrettyName()+" {" );
			hindex = 1;
			builder.append("UNDEFINED,");
			for(Map.Entry<String, String> entry : enumElement.getValue_list().entrySet()){
				if(hindex <  enumElement.getValue_list().size()){
					builder.append(tabGen(1)+entry.getKey().toUpperCase()+",");
				}else{
					builder.append(tabGen(1)+entry.getKey().toUpperCase()+";");					
				}
				hindex++;
			}
			builder.clrlf();
			builder.appendLn(tabGen(1)+"public static java.lang.String getValueDescription("+
					enumElement.getPrettyName()+" description){");
			builder.appendLn(tabGen(2)+"switch(description){");
			for(Map.Entry<String, String> entry : enumElement.getValue_list().entrySet()){
				builder.appendLn(tabGen(3)+"case "+entry.getKey().toUpperCase()+":");
				builder.appendLn(tabGen(4)+"return "+quotscape+entry.getValue().toUpperCase()+quotscape+";");
			}
			builder.appendLn(tabGen(3)+"default:");
			builder.appendLn(tabGen(4)+"return "+quotscape+quotscape+";");
			builder.appendLn(tabGen(2)+"}");
			builder.appendLn(tabGen(1)+"}");
			
			generateFullyQualifiedValueComparator(enumElement);
			
			builder.clrlf();
			builder.appendLn(tabGen(1)+"public static java.util.List<java.lang.String> getOptionCollection(){");
			builder.appendLn(tabGen(2)+"java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();");
			for(Map.Entry<String, String> entry : enumElement.getValue_list().entrySet()){
				builder.appendLn(tabGen(2)+"optionCollection.add("+quotscape+entry.getValue().toUpperCase()+quotscape+");");
			}
			builder.appendLn(tabGen(2)+"return optionCollection;");
			builder.appendLn(tabGen(1)+"}");
			
			builder.appendLn("}");
			
			//Generamos las clases
			generateClasses(enumElement.getPrettyName(), builder.toString());
		}
	}
	
	private void generateFullyQualifiedValueComparator(EnumElement element){
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public static boolean fullyQualifiedComparer(String fullyQualifiedValue, String simpleValue){");
		builder.appendLn(tabGen(2)+"return fullyQualifiedValue.replace("+quotscape+Element.classPackage+quotscape+","+quotscape+quotscape+")");
		builder.append(".replace("+quotscape+element.getPrettyName()+quotscape+","+quotscape+quotscape+")");
		builder.append(".replace("+quotscape+"."+quotscape+","+quotscape+quotscape+")");
		builder.append(".equalsIgnoreCase(simpleValue);");
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
	
	
}
