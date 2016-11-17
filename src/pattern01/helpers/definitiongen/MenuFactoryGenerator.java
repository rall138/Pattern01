package pattern01.helpers.definitiongen;

import java.util.Calendar;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;
import org.eclipse.swt.widgets.Tree;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.temporal_containers.Element;
import pattern01.plugin.components.navigator.NodeType;

public class MenuFactoryGenerator extends Task {

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
		private Element parentElement = null;
		
		
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
			builder.appendLn(beginTag);
			builder.appendLn(classHeaderComment);
			builder.clrlf();
			builder.appendLn(1,"public void generateDisplayableOptions(NodeType nodeType, Tree parent){");
			builder.appendLn(2,"this.parent = parent;");
			
		}
	
}
