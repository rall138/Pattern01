package pattern01.helpers.definitiongen;

import java.util.Calendar;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.Element;

public class MenuFactoryGenerator extends Task {

	private static final String tabspace = "\t";
	private static final String quotscape = "\"";
	private static final String beginTag = "/* [Begin] Auto-generated code for menu factory do not remove */";
	private static final String endTag = "/* [End] Auto-generated code for menu factory do not remove */";
	//Regula expression for template code replacement.
	private static final String regex = "(\\/\\* \\[Begin\\] Auto-generated code for menu factory do not remove \\*/)[\\s+\\S+]+"
			+ "(\\/\\* \\[End\\] Auto-generated code for menu factory do not remove \\*/)";	
	
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
	
	public MenuFactoryGenerator(Element patternInstanceElement){
		this.patternInstanceElement = patternInstanceElement;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());

		//Se anaden los datos faltantes a los childselements creados
		generateClasses(this.patternInstanceElement);
	}
	
	private void generateClasses(Element element){
		log.writeSingleMessage("<<< Generating MenuFactory's code-replacement >>>");
		builder = new CustomStringBuilder();
		builder.appendLn(1,beginTag);
		builder.appendLn(classHeaderComment);
		builder.clrlf();
		builder.appendLn(1,"public void generateDisplayableOptions(NodeType nodeType){");
		builder.appendLn(2,"Menu menu = new Menu(parent);");
		builder.appendLn(2,"parent.setMenu(menu);");
		builder.appendLn(2,"MenuItem add_item = null;");
		builder.appendLn(2,"Menu add_itemMenu = null;");
		builder.appendLn(2,"switch (nodeType) {");
		builder = generateSwitchOptions(element, builder);
		builder.appendLn(3,"default:");
		builder.appendLn(4,"break;");
		builder.appendLn(2,"}");
		builder.appendLn(1,"}");
		builder.clrlf();
		builder = generateAddElementMethod(this.patternInstanceElement, builder);
		builder.appendLn(1,endTag);
		builder.clrlf();
		generateClasses(builder.toString());
	}
	
	private CustomStringBuilder generateSwitchOptions(Element element, CustomStringBuilder builder){
		builder.appendLn(3,"case "+element.getName().toUpperCase()+":");
		if (element.getChildElements_collection().size() > 0){
			builder.appendLn(4,"add_item = new MenuItem(menu, SWT.CASCADE);");
			builder.appendLn(4,"add_item.setText("+quotscape+"Add"+quotscape+");");
			builder.appendLn(4,"add_itemMenu = new Menu(add_item);");
			builder.appendLn(4,"add_item.setMenu(add_itemMenu);");
		}
		for(Element childElement : element.getChildElements_collection()){
			builder.appendLn(4,"MenuItem item_"+childElement.getPrettyName()+" = new MenuItem(add_itemMenu, SWT.PUSH);");
			builder.appendLn(4,"item_"+childElement.getPrettyName()+".setText("+quotscape+childElement.getPrettyName()+quotscape+");");
			builder.appendLn(4,"item_"+childElement.getPrettyName()+".setImage(ImageHelper.getImage("+quotscape+childElement.getImage()+quotscape+"));");
			builder.appendLn(4,"item_"+childElement.getPrettyName()+".setData("+quotscape+"type"+quotscape+",");
			builder.append("NodeType."+childElement.getName().toUpperCase()+");");
			builder.appendLn(4,"item_"+childElement.getPrettyName()+".addSelectionListener(listenerFactory());");
			if (element.getChildElements_collection().indexOf(childElement)<element.getChildElements_collection().size()-1){
				builder.clrlf();
			}
		}
		builder.appendLn(4,"break;");
		
		for(Element childElement : element.getChildElements_collection()){
			builder = (generateSwitchOptions(childElement, builder));
		}
		return builder;
	}
	
	private CustomStringBuilder generateAddElementMethod(Element element, CustomStringBuilder builder){
		builder.appendLn(1,"private void addElement(MenuItem selectedItem){");
		builder.appendLn(2,"switch(((NodeType)selectedItem.getData("+quotscape+"type"+quotscape+"))){");
		builder = generateAddElementMethodSwitchOptions(element, builder);
		builder.appendLn(3,"default:");
		builder.appendLn(4,"break;");
		builder.appendLn(2,"}");
		builder.appendLn(1,"}");
		return builder;
	}
	
	private CustomStringBuilder generateAddElementMethodSwitchOptions(Element element, CustomStringBuilder builder){
		builder.appendLn(3,"case "+element.getName().toUpperCase()+":");
		builder.appendLn(4,"TreeItem item_"+element.getPrettyName()+" = new TreeItem(this.parent.getSelection()[0], 0);");
		builder.appendLn(4,"item_"+element.getPrettyName()+".setText(selectedItem.getText());");
		builder.appendLn(4,"item_"+element.getPrettyName()+".setImage(ImageHelper.getImage("+quotscape+"primefaces.jpg"+quotscape+"));");
		builder.appendLn(4,"item_"+element.getPrettyName()+".setData("+quotscape+"type"+quotscape+",NodeType."+element.getName().toUpperCase()+");");
		builder.appendLn(4,"break;");
		for(Element childElement : element.getChildElements_collection()){
			builder = (generateAddElementMethodSwitchOptions(childElement, builder));
		}
		return builder;
	}
	
	private void generateClasses(String classBody){
		bfr.getProject().setProperty("filename", "../../plugin/components/navigator/MenuFactory.java");
		bfr.getProject().setProperty("token", regex);
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("replacer");
	}
}