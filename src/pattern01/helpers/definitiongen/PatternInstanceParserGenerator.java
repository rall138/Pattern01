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
		CustomStringBuilder attributeBuilder;
		CustomStringBuilder getterSetterBuilder;
		
		PropertyHelper propertyHelper = new PropertyHelper();

		builder = new CustomStringBuilder();
		builder.appendLn("package pattern01.helpers.generated;");
		builder.appendLn("");
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class PatternInstanceParser {");
		builder.appendLn("");
		builder.appendLn(tabGen(1)+"public void parseInstance(String instance_name, String patternfolder_path){");
		builder.appendLn(tabGen(2)+"javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance().newXPath();");
		builder.appendLn(tabGen(2)+"String expression = "+quotscape+"/PatternInstance"+quotscape+";");
		builder.appendLn(tabGen(2)+"try{");
		builder.appendLn(tabGen(3)+"java.io.File instance_file = new java.io.File(patternfolder_path/+instance_name.xml);");
		builder.appendLn(tabGen(3)+"if(instance_file.exists(){");
		builder.appendLn(tabGen(4)+"Node instance_node = (Node) xpath.evaluate(expression, is, XPath.Node);");
		builder.appendLn(tabGen(4)+"recursiveParseing(instance_file);");		
		builder.appendLn(tabGen(3)+"}");		
		builder.appendLn(tabGen(2)+"}catch(javax.xml.xpath.XPathExpressionException | java.lang.IllegalStateException e){");		
		builder.appendLn(tabGen(3)+"e.printStackTrace(System.err);");
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(1)+"}");
		for(int index = 0; index < collected_elements.size(); index++){
			
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