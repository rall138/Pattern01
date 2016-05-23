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
		builder.appendLn("package pattern01.helpers.generated;");
		builder.clrlf();
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class PatternInstanceParser {");
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"private org.eclipse.swt.widgets.TreeItem parentItem = null;");
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public PatternInstanceParser(org.eclipse.swt.widgets.TreeItem parentItem){");
		builder.appendLn(tabGen(2)+"this.parentItem = parentItem;");
		builder.appendLn(tabGen(1)+"}");
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"public void parseInstance(String instance_name, String patternfolder_path){");
		builder.appendLn(tabGen(2)+"javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance().newXPath();");
		builder.appendLn(tabGen(2)+"String expression = "+quotscape+"/PatternInstance"+quotscape+";");
		builder.appendLn(tabGen(2)+"try{");
		builder.appendLn(tabGen(3)+"java.io.File instance_file = new java.io.File(patternfolder_path/+instance_name.xml);");
		builder.appendLn(tabGen(3)+"if(instance_file.exists(){");
		builder.appendLn(tabGen(4)+"org.xml.sax.InputSource is = new org.xml.sax.InputSource(instance_file.getPath());");
		builder.appendLn(tabGen(4)+"org.w3c.dom.Node instance_node = (org.w3c.dom.Node) xpath.evaluate(expression, is, XPathConstants.NODE);");
		builder.appendLn(tabGen(4)+"recursiveParseing(instance_file, this.parentItem);");		
		builder.appendLn(tabGen(3)+"}");		
		builder.appendLn(tabGen(2)+"}catch(javax.xml.xpath.XPathExpressionException | java.lang.IllegalStateException e){");		
		builder.appendLn(tabGen(3)+"e.printStackTrace(System.err);");
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(1)+"}");
		
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
			builder.clrlf();
			builder.appendLn(tabGen(2)+"pattern01.plugin.components.editors.generated."+collected_elements.get(index).getPrettyName()+
					" "+collected_elements.get(index).getName()+" = new pattern01.plugin.components.editors.generated."+
							collected_elements.get(index).getPrettyName()+"();");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				builder.appendLn(tabGen(2)+collected_elements.get(index).getName()+".set"+attr.getPrettyName()+
						"("+quotscape+attr.getDefault_value()+quotscape+");");
			}
			builder.appendLn(tabGen(2)+"item.setData(class_instance,"+collected_elements.get(index).getName()+");");
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