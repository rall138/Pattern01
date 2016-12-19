package pattern01.helpers.definitiongen;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.temporal_containers.Element;

public class NodeTypeEnumGenerator extends Task{

	private static final String tabspace = "\t";
	private static final String quotscape = "\"";
	private static final String classHeaderComment = 
			tabspace+"/**\n"
			+tabspace+"* Generated class via ClassGenerator.xml\n"
			+tabspace+"* Creation date: "+Calendar.getInstance().getTime()+"\n"
			+tabspace+"* Creator: rlomez\n"
			+tabspace+"**/";
	
	private BuildFileRule bfr = new BuildFileRule();
	private Element patternInstanceElement = null;
	private CustomStringBuilder builder = null;
	private CustomStringBuilder elementListBuilder = null;
	private Map<String, String> map = new HashMap<>();
	
	
	public NodeTypeEnumGenerator(Element patternInstanceElement){
		this.patternInstanceElement = patternInstanceElement;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		generateClasses(this.patternInstanceElement);
	}
	
	private void generateClasses(Element element){
		LoggerThread log = new LoggerThread();
		log.writeSingleMessage("<<< Generating Enum class NodeType >>>");
		if (element != null){
			builder = new CustomStringBuilder();
			elementListBuilder = new CustomStringBuilder();
			generateClassHeader(element);
			generateMapFromIteration(element);
			generateEnumListFromMap();
			builder.append(replaceLastCharacterFromEnumList(elementListBuilder));
			generateHardCodedToStringMethod();
			generateCasesFromMap();
			generateToStringMethodFooter();
			builder.appendLn("}");
			generateClasses("NodeType", builder.toString());
		}
	}
	
	private void generateClassHeader(Element element){
		builder = new CustomStringBuilder();
		builder.appendLn(classHeaderComment);
		builder.appendLn("package pattern01.plugin.components.navigator;");
		builder.clrlf();
		builder.appendLn("public enum NodeType {");
		builder.appendLn(1,"UNDEFINED,");
		builder.append("PACKAGE,");
		builder.append("CLASS,");
	}
	
	private void generateMapFromIteration(Element element){
		map.put(element.getName().toUpperCase(), element.getName().toUpperCase());
		for (Element childElement : element.getChildElements_collection()){
			generateMapFromIteration(childElement);
		}
	}
	
	private void generateCasesFromMap(){
		for (Entry<String, String> entry : map.entrySet()){
			generateCaseForSwitch(entry.getValue());
		}
	}
	
	private void generateEnumListFromMap(){
		for (Entry<String, String> entry : map.entrySet()){
			elementListBuilder.append(entry.getValue()+",");		
		}
	}
	
	private String replaceLastCharacterFromEnumList(CustomStringBuilder elementBuilder){
		char[] charArray = elementListBuilder.toString().toCharArray();
		charArray[charArray.length -1] = ';';
		return String.valueOf(charArray);
	}
	
	private void generateHardCodedToStringMethod(){
		builder.clrlf();
		builder.appendLn(1,"public static String toString(NodeType nodetype){");
		builder.appendLn(2,"String nodetypestr = "+quotscape+quotscape+";");
		builder.appendLn(2,"switch(nodetype){");
		builder.appendLn(3,"case UNDEFINED:");
		builder.appendLn(4,"nodetypestr = "+quotscape+"UNDEFINED"+quotscape+";");
		builder.appendLn(4,"break;");
		builder.appendLn(3,"case PACKAGE:");
		builder.appendLn(4,"nodetypestr = "+quotscape+"PACKAGE"+quotscape+";");
		builder.appendLn(4,"break;");
		builder.appendLn(3,"case CLASS:");
		builder.appendLn(4,"nodetypestr = "+quotscape+"CLASS"+quotscape+";");
		builder.appendLn(4,"break;");
	}
	
	private void generateCaseForSwitch(String entryValue){
		builder.appendLn(3,"case "+entryValue+":");
		builder.appendLn(4,"nodetypestr = "+quotscape+entryValue+quotscape+";");
		builder.appendLn(4,"break;");
	}
	
	private void generateToStringMethodFooter(){
		builder.appendLn(2,"}");
		builder.appendLn(2,"return nodetypestr;");
		builder.appendLn(1,"}");
	}
	
	private void generateClasses(String className, String classBody){
		bfr.getProject().setProperty("filename", "../../plugin/components/navigator/"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
}