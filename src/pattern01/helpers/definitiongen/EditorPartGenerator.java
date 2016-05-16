package pattern01.helpers.definitiongen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.HeaderCommentHelper;
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
		CustomStringBuilder getterSetterBuilder;
		
//		PropertyHelper propertyHelper = new PropertyHelper();

		String className = "";
		for(int index = 0; index < collected_elements.size(); index++){
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

			attributeBuilder.appendLn("");
			attributeBuilder.appendLn(tabGen(1)+"private boolean dirty;");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				
				if (attr.getType().contains("#{")){
//					String processedType = attr.getType()
//							.substring(attr.getType().indexOf("#{")+2, attr.getType().indexOf("}"));
//					
//					String defaultValue = !attr.getDefault_value().equalsIgnoreCase("")?
//							attr.getDefault_value().substring(attr.getDefault_value().indexOf("#{")+2, 
//									attr.getDefault_value().indexOf("}")):"";
//					
//					generateCustomValueBasedProperties(attributeBuilder, getterSetterBuilder, 
//							processedType, attr.getName(), 
//							defaultValue);
				}else{
					attributeBuilder.appendLn(tabGen(1)+
							"public org.eclipse.swt.widgets.Label label_"+attr.getName()+" = null;");
					
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
			builder.appendLn(tabGen(2)+"org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout();");
			builder.appendLn(tabGen(2)+"layout.numColumns = 2;");
			builder.appendLn(tabGen(2)+"layout.makeColumnsEqualWidth = true;");
			builder.appendLn(tabGen(2)+"layout.horizontalSpacing = 8;");
			builder.appendLn(tabGen(2)+"parent.setLayout(layout);");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				if (attr.getType().contains("#{")){
					
				}else{
					builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+
							" = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);");
					builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+
							".setText("+quotscape+attr.getPrettyName()+quotscape+");");
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							" = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);");
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							".setText("+quotscape+attr.getDefault_value()+quotscape+");");
				}
			}
			builder.appendLn(tabGen(2)+"addListeners();");
			builder.appendLn(tabGen(1)+"}");
			
			// TODO Tener en cuenta los customValues como "Generator"
			// TODO Hay que ampliar para cuando es combo o radio button por ejemplo.
			builder.appendLn("");
			builder.appendLn(tabGen(1)+HeaderCommentHelper.headerComment("Listeners for all node properties"));
			builder.appendLn(tabGen(1)+"private void addListeners(){");
			for(Attribute attr : collected_elements.get(index).getAttribute_collection()){
				if (!attr.getType().contains("#{")){
					builder.appendLn("");
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							".addKeyListener(new org.eclipse.swt.events.KeyListener() {");
					builder.appendLn(tabGen(2)+"@Override");
					builder.appendLn(tabGen(3)+"public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}");
					builder.appendLn(tabGen(2)+"@Override");
					builder.appendLn(tabGen(3)+"public void keyPressed(org.eclipse.swt.events.KeyEvent e) {");
					builder.appendLn(tabGen(4)+"dirty = true;");
					builder.appendLn(tabGen(4)+"firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);");
					builder.appendLn(tabGen(3)+"}");
					builder.appendLn(tabGen(2)+"});");
				}
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
			builder.appendLn(tabGen(2)+"dirty = false;");
			builder.appendLn(tabGen(1)+"}");

			builder.appendLn("");
			builder.appendLn(tabGen(1)+"@Override");
			builder.appendLn(tabGen(1)+"public void doSaveAs() {");
			builder.appendLn(tabGen(2)+"dirty = false;");
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

//	private void generateCustomValueBasedProperties(CustomStringBuilder attrBuilder, 
//			CustomStringBuilder builder, String processedType, String attrName, String processedDefaultValue){
//		List<Element> collected_custom_values = (new CustomValuesDefinitionParser()).parseDefinition();
//		if (collected_custom_values.size() > 0){
//			int index = 0;
//			boolean itemFound = false;
//			while(index < collected_custom_values.size() && !itemFound){
//				if (collected_custom_values.get(index).getName().equalsIgnoreCase(processedType)){
//					attrBuilder.appendLn(tabGen(1)+"private "+collected_custom_values.get(index).getPrettyName()
//							+" "+attrName+(processedDefaultValue.isEmpty()?";" : " = "+processedDefaultValue+";"));
//					itemFound = true;
//				}else{
//					index++;
//				}
//			}
//			builder.appendLn(tabGen(1)+"public "+collected_custom_values.get(index).getPrettyName()
//					+" get"+collected_custom_values.get(index).getPrettyName()+"(){");
//			builder.appendLn(tabGen(2)+"return this."+attrName+";");
//			builder.appendLn(tabGen(1)+"}");
//			builder.appendLn("");
//			builder.appendLn(tabGen(1)+"public void set"+collected_custom_values.get(index).getPrettyName()
//					+"("+collected_custom_values.get(index).getPrettyName()+" "+collected_custom_values.get(index).getName()+"){");
//			builder.appendLn(tabGen(2)+"this."+attrName+" = "+collected_custom_values.get(index).getName()+";");
//			builder.appendLn(tabGen(1)+"}");
//			
//		}
//	}
	

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