package pattern01.helpers.definitiongen;

import java.util.Calendar;

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
			System.out.println("Hasta aca");
			generateClassHeader(element);
			generateElementList(element);
			builder.append(elementListBuilder.toString().substring(0, elementListBuilder.toString().length()-1)+";");
			//TODO reemplazar coma por punto y coma en el ultimo elemento de la lista
			generateHardCodedToStringMethod();
			generateToStringMethod(element);
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
	
	private void generateElementList(Element element){
		if (element != null){
			System.out.println("Element: "+element.getName());
			elementListBuilder.append(element.getName().toUpperCase()+",");
			for (Element childElement : element.getChildElements_collection()){
				System.out.println("ChildElement: "+childElement.getName());
				generateElementList(childElement);
			}
		}
	}
	
	private void generateHardCodedToStringMethod(){
		builder.clrlf();
		builder.appendLn(1,"public static String toString(NodeType nodetype){");
		builder.appendLn(2,"String nodetypestr = "+quotscape+quotscape+";");
		builder.appendLn(3,"switch(nodetype){");
		builder.appendLn(4,"case UNDEFINED:");
		builder.appendLn(3,"nodetypestr = "+quotscape+"UNDEFINED"+quotscape+";");
		builder.appendLn(3,"break;");
		builder.appendLn(4,"case PACKAGE:");
		builder.appendLn(3,"nodetypestr = "+quotscape+"PACKAGE"+quotscape+";");
		builder.appendLn(3,"break;");
		builder.appendLn(4,"case CLASS:");
		builder.appendLn(3,"nodetypestr = "+quotscape+"CLASS"+quotscape+";");
		builder.appendLn(3,"break;");
	}
	
	private void generateToStringMethod(Element element){
		if (element != null){
			builder.appendLn(3,"case "+element.getName().toUpperCase()+":");
			builder.appendLn(4,"nodetypestr ="+quotscape+element.getName().toUpperCase()+quotscape+";");
			builder.appendLn(4,"break;");
			for (Element childElement : element.getChildElements_collection()){
				generateToStringMethod(childElement);
			}
		}
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