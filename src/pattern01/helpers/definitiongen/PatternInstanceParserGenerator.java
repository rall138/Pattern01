package pattern01.helpers.definitiongen;

import java.util.Calendar;

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
	private Element patternInstanceElement = null;
	private CustomStringBuilder builder = null;
	LoggerThread log = new LoggerThread();
	
	public PatternInstanceParserGenerator(Element patternInstanceElement){
		this.patternInstanceElement = patternInstanceElement;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());

		//Se anaden los datos faltantes a los childselements creados
		generateClasses(this.patternInstanceElement);
	}
	
	
	private void generateClasses(Element element){
		log.writeSingleMessage("<<< Generating PatternInstanceSaver's code-replacement >>>");
		builder = new CustomStringBuilder();
		builder.appendLn(beginTag);
		builder.appendLn(classHeaderComment);
		builder.appendLn(tabGen(1)+"private void recursiveParseing(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent){");
		builder.appendLn(tabGen(2)+"org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);");
		builder.appendLn(tabGen(2)+"item.setText(actualNode.getNodeName());");
		builder.appendLn(tabGen(2)+"item.setData("+quotscape+"type"+quotscape+", NodeType.valueOf(actualNode.getNodeName().toUpperCase()));");
		builder.clrlf();
		builder.appendLn(tabGen(2)+"// Attribute traspasseing (from xpath node --> treeItem node)");
		builder.appendLn(tabGen(2)+"for(int index = 0; index < actualNode.getAttributes().getLength(); index++){");
		builder.appendLn(tabGen(3)+"item.setData(actualNode.getAttributes().item(index).getNodeName(),"); 
		builder.appendLn(tabGen(4)+"actualNode.getAttributes().item(index).getNodeValue().toString());");
		builder.appendLn(tabGen(2)+"}");

		builder.appendLn(tabGen(2)+"classInstanceStrategy(actualNode, item);");
		builder.appendLn(tabGen(2)+"extraInfoForPatternInstance(parent, item);");
		
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
		generateElementStrategyHeader();
		generateElementStrategy(element, true);
		generateElementStrategyFooter();
		generatePatternInstanceExtraInfo();
		builder.appendLn(endTag);
		generateClasses("PatternInstanceParser", builder.toString());
	}
	
	private void generateElementStrategyHeader(){
		builder.appendLn(1,"private void classInstanceStrategy(org.w3c.dom.Node actualNode");
		builder.append(", org.eclipse.swt.widgets.TreeItem item){");
	}
	
	private void generateElementStrategy(Element element, boolean isParentElement){
		if (element != null){
			if (isParentElement){
				builder.appendLn(tabGen(2)+"if(actualNode.getNodeName()"
						+ ".equalsIgnoreCase("+quotscape+element.getName()+quotscape+")){");
			}else{
				builder.appendLn(tabGen(2)+"}else if(actualNode.getNodeName()"
						+ ".equalsIgnoreCase("+quotscape+element.getName()+quotscape+")){");				
			}
		}
		
		//New de la variable 
		builder.appendLn(tabGen(3)+"pattern01.helpers.generated."+element.getPrettyName()+
				" "+element.getName()+" = new pattern01.helpers.generated."+element.getPrettyName()+"();");
		
		generatePropertiesAssignment(element);

		//Image assignment
		builder.appendLn(tabGen(3)+"item.setImage(pattern01.helpers.ImageHelper.getImage("+quotscape+getDefaultImageValue(element)+quotscape+"));");
		
		//Element instance assignment in treeviewItem Data
		builder.appendLn(tabGen(3)+"item.setData("+quotscape+"class_instance"+quotscape+","+element.getName()+");");

		generateDependenciesInjection(element);
		
		for (Element childElement : element.getChildElements_collection()){
			generateElementStrategy(childElement, false);
		}
	}

	private void generateElementStrategyFooter(){
		builder.appendLn(tabGen(2)+"}");
		builder.appendLn(tabGen(1)+"}");
	}
	
	private String getDefaultImageValue(Element element){
		return element.getImage() == null? "": element.getImage();
	}
	
	private void generatePropertiesAssignment(Element element){
		for(Attribute attr : element.getAttribute_collection()){
			builder.appendLn(tabGen(3)+element.getName()+".set"+attr.getPrettyName());
			builder.append("(actualNode.getAttributes().getNamedItem("+quotscape);
			builder.append(attr.getName()+quotscape+") != null "+"? ");
			builder.append(DataTypeConversion.getDataTypeWrapper(attr.getType(),"actualNode.getAttributes()"
					+ ".getNamedItem("+quotscape+attr.getName()+quotscape+").getNodeValue()")+":");
			builder.append(DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value())+");");
		}
	}
	
	private void generateDependenciesInjection(Element element){
		if (element != null && ((CommonElement)element).getParentElement() != null){
			if (!element.isUnique()){
				builder.appendLn(tabGen(3)+
					"((pattern01.helpers.generated."+((CommonElement)element).getParentElement().getPrettyName()+")"+
					"item.getParentItem().getData("+quotscape+"class_instance"+quotscape+")).getCollection_"+
					element.getPrettyName()+"().add("+element.getName()+");");
			}else {
				builder.appendLn(tabGen(3)+
					"((pattern01.helpers.generated."+((CommonElement)element).getParentElement().getPrettyName()+")"+
					"item.getParentItem().getData("+quotscape+"class_instance"+quotscape+")).set"+
					element.getPrettyName()+"("+element.getName()+");");
			}
		}
	}
	
	private void generatePatternInstanceExtraInfo(){
		builder.clrlf();
		builder.appendLn(1, "private void extraInfoForPatternInstance( org.eclipse.swt.widgets.TreeItem parentItem,");
		builder.append(" org.eclipse.swt.widgets.TreeItem item){");
		builder.appendLn(2, "if (item.getData("+quotscape+"type"+quotscape+").toString().compareToIgnoreCase(");
		builder.append(quotscape+"patterninstance"+quotscape+")==0){");
		builder.appendLn(3, "(("+Element.classPackage+".PatternInstance)");
		builder.append("item.getData("+quotscape+"class_instance"+quotscape+")).setParentClass(");
		builder.append("parentItem.getData("+quotscape+"name"+quotscape+").toString());");
		builder.appendLn(2, "}");
		builder.appendLn(1, "}");
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