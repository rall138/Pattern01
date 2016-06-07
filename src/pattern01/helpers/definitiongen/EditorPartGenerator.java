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

public class EditorPartGenerator extends Task{

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
	
	public EditorPartGenerator(List<Element> collected_elements){
		this.collected_elements = collected_elements;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		generateClasses();
	}
	
	private void generateClasses(){
		LoggerThread log = new LoggerThread();
		
		log.writeSingleMessage("*** File generation ***");
	
		CustomStringBuilder builder;
		CustomStringBuilder attributeBuilder;
		CustomStringBuilder comboInitializerBuilder;
		CustomStringBuilder getterSetterBuilder;
		boolean hasCustomValueDefined = false;
		
//		PropertyHelper propertyHelper = new PropertyHelper();

		String className = "";
		for(int index = 0; index < collected_elements.size(); index++){
			hasCustomValueDefined = false;
			className = collected_elements.get(index).getPrettyName()+"EditorPart";
			
			log.writeSingleMessage("Generating EditorPart: "+className);
			builder = new CustomStringBuilder();
			builder.appendLn("package pattern01.plugin.components.editors.generated;");
			builder.appendLn("");
			builder.appendLn(classHeaderComment);
			builder.appendLn("public class "+collected_elements.get(index).getPrettyName()+
					"EditorPart extends org.eclipse.ui.part.EditorPart{");
			
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"public static final String ID = "+
					quotscape+"pattern01.plugin.components.editors.generated."+
						collected_elements.get(index).getPrettyName()+"EditorPart"+quotscape+";");
			
			attributeBuilder = new CustomStringBuilder();
			getterSetterBuilder = new CustomStringBuilder();
			comboInitializerBuilder = new CustomStringBuilder();

			attributeBuilder.appendLn("");
			attributeBuilder.appendLn(tabGen(1)+"private boolean dirty = false;");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				attributeBuilder.appendLn(tabGen(1)+
						"public org.eclipse.swt.widgets.Label label_"+attr.getName()+" = null;");
				if (attr.getType().contains("#{")){
					attributeBuilder.appendLn(tabGen(1)+
							"private org.eclipse.swt.widgets.Combo "+attr.getName()+" = null;");
					hasCustomValueDefined = true;
				}else{
					attributeBuilder.appendLn(tabGen(1)+
							"private org.eclipse.swt.widgets.Text "+attr.getName()+" = null;");
					//Generate getters and setters
					generateGetterAndSettersOfAttributes(getterSetterBuilder, attr);
				}
				
//				TODO Agrupadores en los atributos
//				propertyHelper.putProperty(collected_elements.get(index).getPrettyName()+"."+
//				attr.getPrettyName()+".Group", attr.getGroup());				
			}
			builder.appendLn(attributeBuilder.toString());
			
			//Constructor del control
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public void createPartControl(org.eclipse.swt.widgets.Composite parent) {");
			builder.appendLn("");
			builder.appendLn(tabGen(2)+"org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout();");
			builder.appendLn(tabGen(2)+"layout.numColumns = 2;");
			builder.appendLn(tabGen(2)+"layout.horizontalSpacing = 8;");
			builder.appendLn(tabGen(2)+"parent.setLayout(layout);");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+
						" = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);");
				builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+
						".setText("+quotscape+attr.getPrettyName()+quotscape+");");
				
				if (attr.getType().contains("#{")){
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							" = new org.eclipse.swt.widgets.Combo(parent, org.eclipse.swt.SWT.NONE);");
					try {
						String processedType = attr.getType().replace("#{", "").replace("}", "");
						
						String processedDefaultValue = attr.getDefault_value()
								.substring(attr.getDefault_value().lastIndexOf(".")+1, 
										attr.getDefault_value().indexOf("}"));
						
						comboInitializerBuilder.appendLn(tabGen(2)+
									"for(int index = 0; index < pattern01.helpers.generated."+
										processedType+".getOptionCollection().size(); index++){");
						comboInitializerBuilder.appendLn(tabGen(3)+"this."+attr.getName()+".add("+
								"pattern01.helpers.generated."+processedType+".getOptionCollection().get(index));");
						comboInitializerBuilder.appendLn(tabGen(2)+"}");
						if (attr.getDefault_value()!= null && !attr.getDefault_value().equals("")){
							comboInitializerBuilder.appendLn(tabGen(2)+"this."+attr.getName()+".select("+
									"this."+attr.getName()+".indexOf("+quotscape+processedDefaultValue+quotscape+"));");
						}

					} catch (SecurityException | IllegalArgumentException  e) {
						e.printStackTrace();
					}
					
				}else{
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							" = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);");
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							".setText("+quotscape+attr.getDefault_value()+quotscape+");");
				}
			}
			if (hasCustomValueDefined){
				builder.appendLn(tabGen(2)+"initializeCombos();");
			}
			builder.appendLn(tabGen(2)+"addListeners();");
			builder.appendLn(tabGen(1)+"}");
			
			//Solo si existen valores custom definidos
			if(hasCustomValueDefined){
				builder.appendLn("");
				builder.appendLn(tabGen(1)+"private void initializeCombos(){");
				builder.append(comboInitializerBuilder.toString());
				builder.appendLn(tabGen(1)+"}");
			}
			
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"private void addListeners() {");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				String eventType ="";
				if(attr.getType().contains("#{")){
					eventType = "org.eclipse.swt.SWT.Selection";
				}else{
					eventType = "org.eclipse.swt.SWT.KeyDown";
				}
				builder.appendLn(tabGen(2)+"");
				builder.appendLn(tabGen(2)+"this."+attr.getName()+".addListener("+eventType+", "+
						"new org.eclipse.swt.widgets.Listener() {");
				builder.appendLn(tabGen(3)+"@Override");
				builder.appendLn(tabGen(3)+"public void handleEvent(org.eclipse.swt.widgets.Event event) {");
				builder.appendLn(tabGen(4)+"dirty = true;");
				builder.appendLn(tabGen(4)+"firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);");
				builder.appendLn(tabGen(3)+"}");
				builder.appendLn(tabGen(2)+"});");
			}
			builder.appendLn(tabGen(1)+"}");
			
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public void init(org.eclipse.ui.IEditorSite site, org.eclipse.ui.IEditorInput input) {");
			builder.appendLn(tabGen(2)+"setSite(site);");
			builder.appendLn(tabGen(2)+"setInput(input);");
			builder.appendLn(tabGen(1)+"}");
			
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public void doSave(org.eclipse.core.runtime.IProgressMonitor monitor) {");
			builder.appendLn(tabGen(2)+"this.dirty = false;");
			builder.appendLn(tabGen(1)+"}");

			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public void doSaveAs() {");
			builder.appendLn(tabGen(2)+"this.dirty = false;");
			builder.appendLn(tabGen(1)+"}");

			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public boolean isDirty() {");
			builder.appendLn(tabGen(2)+"return this.dirty;");
			builder.appendLn(tabGen(1)+"}");
			
			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public boolean isSaveAsAllowed() {");
			builder.appendLn(tabGen(2)+"return true;");
			builder.appendLn(tabGen(1)+"}");

			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public void setFocus() {");
			builder.appendLn(tabGen(1)+"}");

			if (getterSetterBuilder != null && getterSetterBuilder.toString() != null){
				builder.appendLn(getterSetterBuilder.toString());
			}
			
			builder.appendLn("}");
			generateClasses(className, builder.toString());
		}
	}
	
	private void generateGetterAndSettersOfAttributes(CustomStringBuilder getterAndSetterBuilder, Attribute attr){
		getterAndSetterBuilder.appendLn("");
		getterAndSetterBuilder.appendLn(tabGen(1)+"public "+attr.getType()+
				" getLabel_"+attr.getPrettyName()+"Text(){");
		if (attr.getType().equalsIgnoreCase("java.lang.Boolean")){
			getterAndSetterBuilder.appendLn(tabGen(2)+
					"return java.lang.Boolean.valueOf(this."+attr.getName()+".getText());");
		}else{
			getterAndSetterBuilder.appendLn(tabGen(2)+"return this."+attr.getName()+".getText();");
		}
		getterAndSetterBuilder.appendLn(tabGen(1)+"}");
	}
	
	private String tabGen(int quantity){
		String tabappender = "";
		for(int total = 0; total < quantity; total++){
			tabappender += tabspace;
		}
		return tabappender;
	}
	
	private void generateClasses(String className, String classBody){
		bfr.getProject().setProperty("filename", "../../plugin/components/editors/generated/"+className+".java");
		bfr.getProject().setProperty("message", classBody);
		bfr.executeTarget("fileRelative");
	}
	
}