package pattern01.helpers.definitiongen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		this.generateDialogs(this.patternInstanceElement);
		
	}
	
	private void generateDialogs(Element element){
		String text = null;
		if (element != null){
			try{
				text = replaceMetaTags(element);
				this.generateClasses(element.getPrettyName(), text);
			}catch(IOException ex){
				System.err.println("Error on preference dialog generation");
				ex.printStackTrace();
			}
		}
	}
	
	private String replaceMetaTags(Element element) throws IOException{
		String text = 
				getDialogCode().toString()
				.replace("<<class_name>>", element.getPrettyName())
				.replace("<<property_method_body>>", getMethodBody(element).toString())
				.replace("<<property_save>>", "/* TODO - Generar cuerpo del save */");
		return text;
	}

	private CustomStringBuilder getDialogCode() throws FileNotFoundException, IOException{
		
		CustomStringBuilder builder = new CustomStringBuilder();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				CommonPathFix.getHardCodedPath(PATH_NAME.JFACEDIALOG_TEMPLATE))));
		
		String line = null;
		while ((line = reader.readLine())!= null){
			builder.appendLn(line);
		}
		reader.close();
		return builder;
	}
	
	private CustomStringBuilder getMethodBody(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		for (Attribute attr : element.getAttribute_collection()){
			builder.appendLn(2,"org.eclipse.swt.widgets.Label "+attr.getPrettyName()+"_text");
			builder.append(" = new org.eclipse.swt.widgets.Label(SWT.NONE);");
			builder.appendLn(2,attr.getPrettyName()+"_text.setText("+attr.getPrettyName());
			builder.appendLn(2,"org.eclipse.swt.widgets.Text "+attr.getPrettyName()+"_text");
			builder.append(" = new org.eclipse.swt.widgets.Text(SWT.SINGLE);");
			if (element.getAttribute_collection().indexOf(attr) < 
					element.getAttribute_collection().size()){
				builder.clrlf();
			}
		}
		return builder;
	}
	
	private void generateClasses(String className, String classBody){
		bfr.getProject().setProperty("filename", "../../../../generalconfig/templates/JFaceDialog.txt");
		bfr.getProject().setProperty("dirname", "../../plugin/components/editors/generated/");
		bfr.getProject().setProperty("newfilename", "JFaceDialog"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileCloner");
		bfr.executeTarget("replacer");
	}
	
}
