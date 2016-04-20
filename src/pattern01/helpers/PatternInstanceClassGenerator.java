package pattern01.helpers;

import java.util.Calendar;
import java.util.List;

import pattern01.helpers.temporal_containers.Element;

public class PatternInstanceClassGenerator {

	private static final String tabspace = "\t";
	private static final String treeNameSpace = "org.eclipse.swt.widgets.Tree";
	private static final String quotscape = "\"";
	private static final String classHeaderComment = 
			tabspace+"/**\n"
			+tabspace+"* Generated class via Generator.xml\n"
			+tabspace+"* Creation date: "+Calendar.getInstance().getTime()+"\n"
			+tabspace+"* Creator: rlomez\n"
			+tabspace+"**/";
	
	public void generate(List<Element> collected_elements){
		CustomStringBuilder builder = new CustomStringBuilder();
		builder.appendLn("package pattern01.helpers.generated;");
		builder.appendLn("");
		builder.appendLn("public class PatternInterface{");
		builder.appendLn(tabspace+"private "+treeNameSpace+" instance = null;");
		builder.appendLn(tabspace+"public void load("+treeNameSpace+" parentClass){");
		//Generating tree's father
		if(collected_elements != null && collected_elements.size() > 0){
			builder.appendLn(tabgen(2)+"instance = "+
					"new "+treeNameSpace+"(parentClass,0);");
			builder.appendLn(tabgen(2)+"instance.setText("+quotscape+
					collected_elements.get(0).getPrettyName()+quotscape+");");
			builder.appendLn(tabgen(2)+"instance.setData(Type,"+quotscape+
					collected_elements.get(0).getPrettyName()+quotscape+");");			
	}
		builder.appendLn(tabspace+"}");		
		builder.appendLn("}");
	}
	
	private String tabgen(int tabnumber){
		String tab = "";
		for(int index = 0; index < tabnumber; index++){
			tab += tabspace;
		}
		return tab;
	}
}