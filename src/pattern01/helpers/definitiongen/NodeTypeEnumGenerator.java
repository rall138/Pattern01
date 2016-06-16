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
		log.writeSingleMessage("Generating NodeType");
		if (element != null){
			builder = new CustomStringBuilder();
			elementListBuilder = new CustomStringBuilder();
			generateClassHeader(element);
			generateElementList(element);
			builder.append(elementListBuilder.toString().substring(0, elementListBuilder.toString().length()-1)+";");
			//TODO reemplazar coma por punto y coma en el ultimo elemento de la lista
			generateHardCodedToStringMethod();
			generateToStringMethod(element);
			generateToStringMethodFooter();
			builder.appendLn("}");
		}
		generateClasses("NodeType", builder.toString());
	}
	
	private void generateClassHeader(Element element){
		builder = new CustomStringBuilder();
		builder.appendLn(classHeaderComment);
		builder.appendLn("package pattern01.plugin.components.navigator;");
		builder.appendLn("");
		builder.appendLn("public enum NodeType {");
		builder.append(tabGen(1)+"UNDEFINED,");
		builder.append("CLASS,");
	}
	
	private void generateElementList(Element element){
		if (element != null){
			elementListBuilder.append(element.getName().toUpperCase()+",");
			for (Element childElement : element.getChildElements_collection()){
				generateElementList(childElement);
			}
		}
	}
	
	private void generateHardCodedToStringMethod(){
		builder.clrlf();
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public static String toString(NodeType nodetype){");
		builder.appendLn(tabGen(2)+"String nodetypestr = "+quotscape+quotscape+";");
		builder.appendLn(tabGen(3)+"switch(nodetype){");
		builder.appendLn(tabGen(3)+"case UNDEFINED:");
		builder.appendLn(tabGen(4)+"nodetypestr = "+quotscape+"UNDEFINED"+quotscape+";");
		builder.appendLn(tabGen(4)+"break;");
		builder.appendLn(tabGen(3)+"case CLASS:");
		builder.appendLn(tabGen(4)+"nodetypestr = "+quotscape+"CLASS"+quotscape+";");
		builder.appendLn(tabGen(4)+"break;");
	}
	
	private void generateToStringMethod(Element element){
		if (element != null){
			builder.appendLn(tabGen(3)+"case "+element.getName().toUpperCase()+":");
			builder.appendLn(tabGen(4)+"nodetypestr ="+quotscape+element.getName().toUpperCase()+quotscape+";");
			builder.appendLn(tabGen(4)+"break;");
			for (Element childElement : element.getChildElements_collection()){
				generateToStringMethod(childElement);
			}
		}
	}
	
	private void generateToStringMethodFooter(){
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(2)+"return nodetypestr;");
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
		bfr.getProject().setProperty("filename", "../../plugin/components/navigator/"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
}