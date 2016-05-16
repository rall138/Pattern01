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
	private List<Element> collected_elements = new ArrayList<>();
	
	public NodeTypeEnumGenerator(List<Element> collected_elements){
		this.collected_elements = collected_elements;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		generateClasses();
	}
	
	private void generateClasses(){
		LoggerThread log = new LoggerThread();
		CustomStringBuilder builder;
		log.writeSingleMessage("Generating NodeType");

		builder = new CustomStringBuilder();
		builder.appendLn(classHeaderComment);
		builder.appendLn("package pattern01.plugin.components.navigator;");
		builder.appendLn("");
		builder.appendLn("public enum NodeType {");
		builder.append(tabGen(1)+"UNDEFINED,");
		builder.append("CLASS,");
		for(int index = 0; index < collected_elements.size(); index++){
			if(index < collected_elements.size() -1){
				builder.append(collected_elements.get(index).getName().toUpperCase()+",");
			}else{
				builder.append(collected_elements.get(index).getName().toUpperCase()+";");
			}
		}
		
		builder.appendLn("");
		builder.appendLn("");
		builder.appendLn(tabGen(1)+"public static String toString(NodeType nodetype){");
		builder.appendLn(tabGen(2)+"String nodetypestr = "+quotscape+quotscape+";");
		builder.appendLn(tabGen(3)+"switch(nodetype){");
		builder.appendLn(tabGen(3)+"case UNDEFINED:");
		builder.appendLn(tabGen(4)+"nodetypestr = "+quotscape+"UNDEFINED"+quotscape+";");
		builder.appendLn(tabGen(4)+"break;");
		builder.appendLn(tabGen(3)+"case CLASS:");
		builder.appendLn(tabGen(4)+"nodetypestr = "+quotscape+"CLASS"+quotscape+";");
		builder.appendLn(tabGen(4)+"break;");
		for(int index = 0; index < collected_elements.size(); index++){
			builder.appendLn(tabGen(3)+"case "+collected_elements.get(index).getName().toUpperCase()+":");
			builder.appendLn(tabGen(4)+"nodetypestr = "+
					quotscape+collected_elements.get(index).getName().toUpperCase()+quotscape+";");
			builder.appendLn(tabGen(4)+"break;");
		}
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(2)+"return nodetypestr;");
		builder.appendLn(tabGen(1)+"}");
		
		builder.appendLn("");
		builder.appendLn(tabGen(1)+"public static NodeType nodeTypeFromString(String nodetypestr){");
		builder.appendLn(tabGen(2)+"NodeType nodetype = NodeType.UNDEFINED;");
		builder.appendLn(tabGen(3)+"switch(nodetypestr.toUpperCase()){");
		builder.appendLn(tabGen(3)+"case "+quotscape+"UNDEFINED"+quotscape+":");
		builder.appendLn(tabGen(4)+"nodetype = NodeType.UNDEFINED;");
		builder.appendLn(tabGen(4)+"break;");
		builder.appendLn(tabGen(3)+"case "+quotscape+"CLASS"+quotscape+":");
		builder.appendLn(tabGen(4)+"nodetype = NodeType.CLASS;");
		builder.appendLn(tabGen(4)+"break;");
		for(int index = 0; index < collected_elements.size(); index++){
			builder.appendLn(tabGen(3)+"case "+quotscape+collected_elements.get(index).getName().toUpperCase()+quotscape+":");
			builder.appendLn(tabGen(4)+"nodetype = NodeType."+
					collected_elements.get(index).getName().toUpperCase()+";");
			builder.appendLn(tabGen(4)+"break;");
		}
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(2)+"return nodetype;");
		builder.appendLn(tabGen(1)+"}");
		builder.appendLn("");
		builder.appendLn("}");
		generateClasses("NodeType", builder.toString());
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