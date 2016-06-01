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
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;

public class PatternInstanceParserGenerator extends Task{

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
	
	public PatternInstanceParserGenerator(List<Element> collected_elements){
		this.collected_elements = collected_elements;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		//Se anaden los datos faltantes a los childselements creados
		generateClasses();
	}
	
	
	private void generateClasses(){
		LoggerThread log = new LoggerThread();
		
		log.writeSingleMessage("*** File generation ***");
	
		CustomStringBuilder builder;

		builder = new CustomStringBuilder();
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"private void recursive(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent){");
		builder.appendLn(tabGen(2)+"org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);");
		builder.appendLn(tabGen(2)+"item.setText(actualNode.getNodeName());");
		builder.appendLn(tabGen(2)+"item.setData("+quotscape+"type"+quotscape+", NodeType.nodeTypeFromString(actualNode.getNodeName()));");
		builder.clrlf();
		builder.appendLn(tabGen(2)+"// Attribute traspasseing (from xpath node --> treeItem node)");
		builder.appendLn(tabGen(2)+"for(int index = 0; index < actualNode.getAttributes().getLength(); index++){");
		builder.appendLn(tabGen(3)+"item.setData(actualNode.getAttributes().item(index).getNodeName(),"); 
		builder.appendLn(tabGen(4)+"actualNode.getAttributes().item(index).getNodeValue().toString());");
		builder.appendLn(tabGen(2)+"}");
		
		for(int index = 0; index < collected_elements.size(); index++){
			
			// Variable auxiliar.
			CommonElement co = (CommonElement)collected_elements.get(index);
			
			builder.clrlf();
			
			//New de la variable 
			builder.appendLn(tabGen(2)+"pattern01.plugin.components.editors.generated."+co.getPrettyName()+
					" "+co.getName()+" = new pattern01.plugin.components.editors.generated."+co.getPrettyName()+"();");
			
			for(Attribute attr : co.getAttribute_collection()){
				builder.appendLn(tabGen(2)+co.getName()+".set"+attr.getPrettyName()+"("+quotscape+attr.getDefault_value()+quotscape+");");
			}
			builder.appendLn(tabGen(2)+"if(actualNode.getNodeName().equalsIgnoreCase("+quotscape+co.getPrettyName()+quotscape+")){");
			builder.appendLn(tabGen(3)+"item.setData(class_instance,"+co.getName()+");");
			builder.appendLn(tabGen(2)+"}");
		}
		
		builder.clrlf();
		builder.appendLn(tabGen(2)+"// Recursion over child nodes");
		builder.appendLn(tabGen(2)+"if (actualNode.getChildNodes().getLength() > 0){");
		builder.appendLn(tabGen(3)+"for(int index = 0; index < actualNode.getChildNodes().getLength(); index++){");
		builder.appendLn(tabGen(4)+"if (actualNode.getChildNodes().item(index).getNodeType() == Node.ELEMENT_NODE){");
		builder.appendLn(tabGen(5)+"recursiveParseing(actualNode.getChildNodes().item(index), item);");
		builder.appendLn(tabGen(4)+"}");
		builder.appendLn(tabGen(3)+"}");
		builder.appendLn(tabGen(2)+"}");
		
		builder.clrlf();
		builder.appendLn("}");
		
		generateClasses("PatternInstanceParser", builder.toString());
	}
	
	private void elementStrategy(CustomStringBuilder builder){
		builder.appendLn(tabGen(2)+"private void classInstanceStrategy(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem item){");
		builder.appendLn(tabGen(3)+"");
		for(int index = 0; index < collected_elements.size(); index++){

			// Variable auxiliar.
			CommonElement co = (CommonElement)collected_elements.get(index);
			
			builder.clrlf();
			
			if(index == 0){
				builder.appendLn(tabGen(3)+"if(actualNode.getNodeName().equalsIgnoreCase("+quotscape+co.getName()+quotscape+")){");
			}else{
				builder.appendLn(tabGen(3)+"}else if(actualNode.getNodeName().equalsIgnoreCase("+quotscape+co.getName()+quotscape+")){");				
			}
		
			//New de la variable 
			builder.appendLn(tabGen(2)+"pattern01.plugin.components.editors.generated."+co.getPrettyName()+
					" "+co.getName()+" = new pattern01.plugin.components.editors.generated."+co.getPrettyName()+"();");

			for(Attribute attr : co.getAttribute_collection()){
				builder.appendLn(tabGen(2)+co.getName()+".set"+attr.getPrettyName()+"("+quotscape+attr.getDefault_value()+quotscape+");");
			}
			
			builder.appendLn(tabGen(2)+"if(actualNode.getNodeName().equalsIgnoreCase("+quotscape+co.getPrettyName()+quotscape+")){");
			builder.appendLn(tabGen(3)+"item.setData(class_instance,"+co.getName()+");");
			builder.appendLn(tabGen(2)+"}");
		}
		builder.appendLn(tabGen(2)+"}");
	}
	
	private String tabGen(int quantity){
		String tabappender = "";
		for(int total = 0; total < quantity; total++){
			tabappender += tabspace;
		}
		return tabappender;
	}
	
	private void generateClasses(String className, String classBody){
		bfr.getProject().setProperty("filename", "../definitiongen/parsers/"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
}