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
	
	private static final String tabspace = "\t";
	private static final String quotscape = "\"";
	private static final String classHeaderComment = 
			tabspace+"/**\n"
			+tabspace+"* Generated enum class via ClassGenerator.xml\n"
			+tabspace+"* Creation date: "+Calendar.getInstance().getTime()+"\n"
			+tabspace+"* Creator: rlomez\n"
			+tabspace+"**/";
	
	private BuildFileRule bfr = new BuildFileRule();
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

		CustomStringBuilder builder = new CustomStringBuilder();
		EnumElement el = null;
		int hindex = 0;
		for(int index = 0; index < collected_elements.size(); index++){
			el = (EnumElement)collected_elements.get(index);
			builder.appendLn("package pattern01.helpers.generated;");
			builder.appendLn("");
			builder.appendLn(classHeaderComment);
			builder.appendLn("public enum "+el.getPrettyName()+" {" );
			hindex = 1;
			builder.append("UNDEFINED,");
			for(Map.Entry<String, String> entry : el.getValue_list().entrySet()){
				if(hindex <  el.getValue_list().size()){
					builder.append(tabGen(1)+entry.getKey().toUpperCase()+",");
				}else{
					builder.append(tabGen(1)+entry.getKey().toUpperCase()+";");					
				}
				hindex++;
			}
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"public static java.lang.String getValueDescription("+
					el.getPrettyName()+" description){");
			builder.appendLn(tabGen(2)+"switch(description){");
			for(Map.Entry<String, String> entry : el.getValue_list().entrySet()){
				builder.appendLn(tabGen(3)+"case "+entry.getKey().toUpperCase()+":");
				builder.appendLn(tabGen(4)+"return "+quotscape+entry.getValue().toUpperCase()+quotscape+";");
			}
			builder.appendLn(tabGen(3)+"default:");
			builder.appendLn(tabGen(4)+"return "+quotscape+quotscape+";");
			builder.appendLn(tabGen(2)+"}");
			builder.appendLn(tabGen(1)+"}");
			
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"public static java.util.List<java.lang.String> getOptionCollection(){");
			builder.appendLn(tabGen(2)+"java.util.List<java.lang.String> optionCollection = new java.util.ArrayList<>();");
			for(Map.Entry<String, String> entry : el.getValue_list().entrySet()){
				builder.appendLn(tabGen(2)+"optionCollection.add("+quotscape+entry.getValue().toUpperCase()+quotscape+");");
			}
			builder.appendLn(tabGen(2)+"return optionCollection;");
			builder.appendLn(tabGen(1)+"}");
			
			builder.appendLn("}");
			
			//Generamos las clases
			generateClasses(el.getPrettyName(), builder.toString());
		}
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
