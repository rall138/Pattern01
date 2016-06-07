package pattern01.helpers.definitiongen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.DataTypeConversion;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.CommonElement;
import pattern01.helpers.temporal_containers.Element;

public class PatternInstanceParserGenerator extends Task{

	private static final String tabspace = "\t";
	private static final String quotscape = "\"";
	private static final String beginTag = "/* [Begin] Auto-generated code for pattern instance parser do not remove */";
	private static final String endTag = "/* [End] Auto-generated code for pattern instance parser do not remove */";
	
	//Regula expression for template code replacement.
	private static final String regex = "(\\/\\* \\[Begin\\] Auto-generated code for pattern instance parser do not remove \\*/)[\\s+\\S+]+"
			+ "(\\/\\* \\[End\\] Auto-generated code for pattern instance parser do not remove \\*/)";	
	
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
		
		builder.appendLn(beginTag);
		builder.appendLn(classHeaderComment);
		builder.appendLn(tabGen(1)+"private void recursiveParseing(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent){");
		builder.appendLn(tabGen(2)+"org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);");
		builder.appendLn(tabGen(2)+"item.setText(actualNode.getNodeName());");
		builder.appendLn(tabGen(2)+"item.setData("+quotscape+"type"+quotscape+", NodeType.nodeTypeFromString(actualNode.getNodeName()));");
		builder.clrlf();
		builder.appendLn(tabGen(2)+"// Attribute traspasseing (from xpath node --> treeItem node)");
		builder.appendLn(tabGen(2)+"for(int index = 0; index < actualNode.getAttributes().getLength(); index++){");
		builder.appendLn(tabGen(3)+"item.setData(actualNode.getAttributes().item(index).getNodeName(),"); 
		builder.appendLn(tabGen(4)+"actualNode.getAttributes().item(index).getNodeValue().toString());");
		builder.appendLn(tabGen(2)+"}");

		builder.appendLn(tabGen(2)+"classInstanceStrategy(actualNode, item);");
		
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
		builder.appendLn(tabGen(1)+"}");

		//Generamos el codigo para las instancias 
		builder.clrlf();
		elementStrategy(builder);
		
		builder.appendLn(endTag);
		generateClasses("PatternInstanceParser", builder.toString());
	}
	
	private void elementStrategy(CustomStringBuilder builder){
		builder.appendLn(tabGen(1)+"private void classInstanceStrategy(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem item){");
		for(int index = 0; index < collected_elements.size(); index++){
			CommonElement co = (CommonElement)collected_elements.get(index);
			if(index == 0){
				builder.appendLn(tabGen(2)+"if(actualNode.getNodeName().equalsIgnoreCase("+quotscape+co.getName()+quotscape+")){");
			}else{
				builder.appendLn(tabGen(2)+"}else if(actualNode.getNodeName().equalsIgnoreCase("+quotscape+co.getName()+quotscape+")){");				
			}

			//New de la variable 
			builder.appendLn(tabGen(3)+"pattern01.helpers.generated."+co.getPrettyName()+
					" "+co.getName()+" = new pattern01.helpers.generated."+co.getPrettyName()+"();");

			for(Attribute attr : co.getAttribute_collection()){
				builder.appendLn(tabGen(3)+"if("+quotscape+attr.getName()+quotscape+" == actualNode.getNodeName()){");
				builder.appendLn(tabGen(4)+co.getName()+".set"+attr.getPrettyName()+"(actualNode.getNodeValue() != null "
						+"? "+DataTypeConversion.getDataTypeWrapper(attr.getType(),"actualNode.getNodeValue().toString()")+":"+
						DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value())+");");
				builder.appendLn(tabGen(3)+"}");
			}
			builder.appendLn(tabGen(3)+"item.setData("+quotscape+"class_instance"+quotscape+","+co.getName()+");");
			if (index > 0){
				dependenciesAssignment(builder, co, index);
			}
		}
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(1)+"}");
	}
	
	private void dependenciesAssignment(CustomStringBuilder builder, CommonElement co,  int element_idex){
		if (!co.isUnique() && co.getParentElement() != null){
			builder.appendLn(tabGen(3)+
				"((pattern01.helpers.generated."+co.getParentElement().getPrettyName()+")"+
				"item.getParentItem().getData("+quotscape+"class_instance"+quotscape+")).getCollection_"+
				collected_elements.get(element_idex).getPrettyName()+".add("+co.getName()+");");
		}else if (co.isUnique() && co.getParentElement() != null){
			builder.appendLn(tabGen(3)+
				"((pattern01.helpers.generated."+co.getParentElement().getPrettyName()+")"+
				"item.getParentItem().getData("+quotscape+"class_instance"+quotscape+")).set"+
				collected_elements.get(element_idex).getPrettyName()+"("+co.getName()+");");
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
		bfr.getProject().setProperty("filename", "../instancegen/"+className+".java");
		bfr.getProject().setProperty("token", regex);
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("replacer");
	}
	
}