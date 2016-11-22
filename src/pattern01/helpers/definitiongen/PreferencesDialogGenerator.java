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
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.DataTypeConversion;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.temporal_containers.Attribute;
import pattern01.helpers.temporal_containers.Element;

public class PreferencesDialogGenerator extends Task{

	private static final String quotscape = "\"";
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
				if (element != null){
					text = this.getDialogCode(element).toString();
					this.generateClasses(element.getPrettyName(), text);
					for(Element child : element.getChildElements_collection()){
						this.generateDialogs(child);
					}
				}
			}catch(IOException ex){
				System.err.println("Error on preference dialog generation");
				ex.printStackTrace();
			}
		}
	}
	
	private CustomStringBuilder getDialogCode(Element element) throws FileNotFoundException, IOException{
		
		CustomStringBuilder builder = new CustomStringBuilder();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				CommonPathFix.getHardCodedPath(PATH_NAME.JFACEDIALOG_TEMPLATE))));
		
		String line = null;
		while ((line = reader.readLine())!= null){
			line = line.replace("<<class_name>>", element.getPrettyName());
			line = line.replace("<<properties>>", getPropertiesDefinitionCode(element).toString());
			line = line.replace("<<property_method_body>>", getMethodBody(element).toString());
			line = line.replace("<<property_save>>", "savePropertiesOnInstance();");
			line = line.replace("<<element_size>>", String.valueOf(element.getAttribute_collection().size()));
			line = line.replace("<<getSelectedInstance>>", getSelectedInstanceCode(element).toString());
			line = line.replace("<<getProperties>>", getPropertyCode(element).toString());
			line = line.replace("<<setProperties>>", getPropertySetterCode(element).toString());
			builder.appendLn(line);
		}
		reader.close();
		return builder;
	}
	
	private CustomStringBuilder getPropertiesDefinitionCode(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		for (Attribute attr : element.getAttribute_collection()){
			builder.appendLn(1,"private org.eclipse.swt.widgets.Label "+attr.getName()+"_label = null;");
			if (attr.isCustomAttribute())
				builder.appendLn(1,"private org.eclipse.swt.widgets.Combo "+attr.getName()+"_cmb = null;");
			else
				builder.appendLn(1,"private org.eclipse.swt.widgets.Text "+attr.getName()+"_text = null;");
		}
		return builder;
	}
	
	private CustomStringBuilder getMethodBody(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		for (Attribute attr : element.getAttribute_collection()){
			builder.appendLn(2, attr.getName()+"_label");
			builder.append(" = new org.eclipse.swt.widgets.Label(container,SWT.NONE);");
			builder.appendLn(2,attr.getName()+"_label.setText("+quotscape+attr.getPrettyName()+quotscape+");");
			
			//Custom attributes producen combos
			if (attr.isCustomAttribute()){
				builder.appendLn(2, attr.getName()+"_cmb");
				builder.append(" = new org.eclipse.swt.widgets.Combo(container, SWT.SINGLE);");				
			}else{
				builder.appendLn(2, attr.getName()+"_text");
				builder.append(" = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);");				
			}

			builder.appendLn(2, "org.eclipse.swt.layout.GridData "+attr.getName()+"_layout");
			builder.append(" = new org.eclipse.swt.layout.GridData();");
			builder.appendLn(2, attr.getName()+"_layout.grabExcessHorizontalSpace = true;");
			builder.appendLn(2, attr.getName()+"_layout.horizontalAlignment = GridData.FILL;");
			if (attr.isCustomAttribute()){
				builder.appendLn(2, attr.getName()+"_cmb.setLayoutData("+attr.getName()+"_layout);");
			}else{
				builder.appendLn(2, attr.getName()+"_text.setLayoutData("+attr.getName()+"_layout);");
			}
			if (element.getAttribute_collection().indexOf(attr) < element.getAttribute_collection().size()){
				builder.clrlf();
			}
		}
		return builder;
	}

	private CustomStringBuilder getSelectedInstanceCode(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		builder.appendLn(1,"private "+Element.classPackage+"."+element.getPrettyName()+" getSelectedInstance(){");
		builder.appendLn(2,"return (("+Element.classPackage+"."+element.getPrettyName()+")"
				+ "this.parent.getSelection()[0].getData("+quotscape+"class_instance"+quotscape+"));");
		builder.appendLn(1, "}");
		return builder;
	}
	
	private CustomStringBuilder getPropertyCode(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		builder.appendLn(1,"private void getPropertiesFromInstance(){");

		//Obtencion de instancia
		builder.appendLn(2, Element.classPackage+"."+element.getPrettyName());
		builder.append(" "+element.getName()+" = getSelectedInstance();");
		
		//Asignacion de valores en propiedades de pantalla modal
		for (Attribute attr : element.getAttribute_collection()){
			if (attr.isCustomAttribute()){
				builder.appendLn(2,"for(String option : "+Element.classPackage+"."+DataTypeConversion.getProcessedType(attr.getType())
				+".getOptionCollection()){");
				builder.appendLn(3,"this."+attr.getName()+"_cmb.add(option);");
				builder.appendLn(2,"}");
				builder.clrlf();
				builder.appendLn(2,"this."+attr.getName()+"_cmb.select(this."+attr.getName()+"_cmb.indexOf(");
				builder.append(element.getName()+".get"+attr.getPrettyName()+"().toString()));");
				
			}else{
				builder.appendLn(2,"this."+attr.getName()+"_text.setText(");
				builder.append(element.getName()+".get"+attr.getPrettyName()+"());");
			}
		}
		builder.appendLn(1, "}");
		return builder;
	}
	
	private CustomStringBuilder getPropertySetterCode(Element element){
		CustomStringBuilder builder = new CustomStringBuilder();
		builder.appendLn(1, "private void savePropertiesOnInstance(){");

		//Obtencion de instancia
		builder.appendLn(2, Element.classPackage+"."+element.getPrettyName());
		builder.append(" "+element.getName()+" = getSelectedInstance();");

		//Asignacion de valores en instancia
		for (Attribute attr : element.getAttribute_collection()){
			if (attr.isCustomAttribute()){ 
				builder.appendLn(2, element.getName()+".set"+attr.getPrettyName()+"(");
				builder.append(Element.classPackage+"."+DataTypeConversion.getProcessedType(attr.getType())
				+".valueOf(this."+attr.getName()+"_cmb.getText()));");
			}else{
				builder.appendLn(2, element.getName()+".set"+attr.getPrettyName()+"(");
				builder.append("this."+attr.getName()+"_text.getText());");
			}
		}
		builder.appendLn(2, "pattern01.helpers.XMLPropertyHelper.saveProperties(this.parent.getSelection()[0]);");
		builder.appendLn(1, "}");
		return builder;
	}

	private void generateClasses(String className, String classBody){
		bfr.getProject().setProperty("message", classBody);
		bfr.getProject().setProperty("filename", "../../plugin/components/editors/generated/JFaceDialog"+className+".java");
		bfr.executeTarget("fileRelative");
	}
}