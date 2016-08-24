package pattern01.helpers.definitiongen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.Element;

public class PreferencesDialogGenerator extends Task{

	private LoggerThread log = new LoggerThread();
	private BuildFileRule bfr = new BuildFileRule();
	private Element patternInstanceElement = null;
	
	public PreferencesDialogGenerator(Element patternInstanceElement) {
		this.patternInstanceElement = patternInstanceElement;
	}
	
	@Override
	public void execute() throws BuildException {
		bfr.configureProject(CommonPathFix.
				getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		log.writeSingleMessage("Generating preferences dialog");
		
	}
	
	private void generateDialogs(Element element){
		if (element != null){
			
		}
	}
	
	private CustomStringBuilder getDialogCode() throws FileNotFoundException, IOException{
		
		CustomStringBuilder builder = new CustomStringBuilder();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				CommonPathFix.getHardCodedPath(PATH_NAME.JFACEDIALOG_TEMPLATE))));
		
		String line = null;
		while ((line = reader.readLine())!= null){
			builder.appendLn(line);
		}
		
		return builder;
	}
	
	private String replaceMetaTags(Element element){
		String text = 
				getDialogCode().toString()
				.replace("<<class_name>>", element.getPrettyName())
				.replace("<<property_method_body>>", element.getPrettyName());
	}
	
	private CustomStringBuilder getMethodBody(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		for (Attribute attr : element.getAttribute_collection()){
			builder.appendLn("org.eclipse.swt.widgets.Label "+attr.getPrettyName()+"_text");
			builder.append(" = new org.eclipse.swt.widgets.Label(SWT.NONE);");
			builder.appendLn("org.eclipse.swt.widgets.Text "+attr.getPrettyName()+"_text");
			builder.append(" = new org.eclipse.swt.widgets.Text(SWT.SINGLE);");
		}
	}

	
	
}
