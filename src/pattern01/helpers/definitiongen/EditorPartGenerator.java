package pattern01.helpers.definitiongen;

import java.util.Calendar;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.DataTypeConversion;
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
	private Element patternInstanceElement = null;
	private CustomStringBuilder builder = null;
	private CustomStringBuilder attributeBuilder = null;
	private CustomStringBuilder getterAndSetterBuilder = null;
	private CustomStringBuilder comboInitializerBuilder = null;
	private CustomStringBuilder comboInitializerBodyBuilder = null;
	private boolean hasCustomValueDefined = false;
	
	public EditorPartGenerator(Element patternInstanceElement){
		this.patternInstanceElement = patternInstanceElement;
	}

	public void execute(){
		bfr.configureProject(CommonPathFix
				.getHardCodedPath(PATH_NAME.CLASSGENERATOR_XML).getPath());
		
		generateClasses(patternInstanceElement);
	}
	
	private void generateClasses(Element element){
		if(element != null){
			initializeCustomStringBuilders();
			generateClassHeader(element);
			generateAttributes(element);
			generateConstructor(element);
			generateEditorOverrides();
			generateListeners(element);
			if(hasCustomValueDefined){
				generateComboInitializerMethod(element);
			}
			builder.append(getterAndSetterBuilder.toString());
			builder.appendLn("}");
			
			generateClasses(element.getPrettyName()+"PatternEditor", builder.toString());
			declarePatternEditorOnPlugin(element);
			for(Element childElement : element.getChildElements_collection()){
				generateClasses(childElement);
			}
		}
	}
	
	private void declarePatternEditorOnPlugin(Element element){
		bfr.getProject().setProperty("filename", CommonPathFix.getHardCodedPath(PATH_NAME.MANIFEST_XML).getPath());
		bfr.getProject().setProperty("message", EditorPartRegister.generatePluginCode(element));
		bfr.executeTarget("fileRelative");
	}
	
	private void initializeCustomStringBuilders(){
		builder = new CustomStringBuilder();
		attributeBuilder = new CustomStringBuilder();
		getterAndSetterBuilder = new CustomStringBuilder();
		comboInitializerBuilder = new CustomStringBuilder();
		comboInitializerBodyBuilder = new CustomStringBuilder();
	}
	
	private void generateClassHeader(Element element){
		builder.appendLn("package pattern01.plugin.components.editors.generated;");
		builder.appendLn("");
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class "+element.getPrettyName()+"PatternEditor"+
				" extends org.eclipse.ui.part.EditorPart{");
		
		builder.appendLn("");
		builder.appendLn(tabGen(1)+"public static final String ID = "+
				quotscape+"pattern01.plugin.components.editors.generated."+
					element.getPrettyName()+"EditorPart"+quotscape+";");
	}
	
	private void generateAttributes(Element element){
		attributeBuilder.appendLn(tabGen(1)+"private boolean dirty = false;");
		for(Attribute attr : element.getAttribute_collection()){
			attributeBuilder.appendLn(tabGen(1)+"public org.eclipse.swt.widgets.Label label_"+attr.getName()+" = null;");
			if (attr.getType().contains("#{")){
				attributeBuilder.appendLn(tabGen(1)+
						"private org.eclipse.swt.widgets.Combo "+attr.getName()+" = null;");
				hasCustomValueDefined = true;
			}else{
				attributeBuilder.appendLn(tabGen(1)+
						"private org.eclipse.swt.widgets.Text "+attr.getName()+" = null;");
				//Generate getters and setters
				generateGetterAndSettersOfAttributes(attr);
			}
			
//			TODO Agrupadores en los atributos
//			propertyHelper.putProperty(collected_elements.get(index).getPrettyName()+"."+
//			attr.getPrettyName()+".Group", attr.getGroup());				
		}
		builder.append(attributeBuilder.toString());
	}

	private void generateGetterAndSettersOfAttributes(Attribute attr){
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
	
	private void generateConstructor(Element element){
		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public void createPartControl(org.eclipse.swt.widgets.Composite parent) {");
		builder.clrlf();
		builder.appendLn(tabGen(2)+"org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout();");
		builder.appendLn(tabGen(2)+"layout.numColumns = 2;");
		builder.appendLn(tabGen(2)+"layout.horizontalSpacing = 8;");
		builder.appendLn(tabGen(2)+"parent.setLayout(layout);");
		generateLabelInitializer(element);
		generateNoneCustomTypeInitializer(element);
		generateCustomTypeInitializer(element);
		generateCustomBodyMethodInitializer(element);
		builder.appendLn(tabGen(2)+"addListeners();");
		if (hasCustomValueDefined){
			builder.appendLn(tabGen(2)+"initializeCombos();");
		}
		builder.appendLn(tabGen(1)+"}");
	}

	private void generateComboInitializerMethod(Element element){
		comboInitializerBuilder.appendLn("");
		comboInitializerBuilder.appendLn(tabGen(1)+"private void initializeCombos(){");
		comboInitializerBuilder.append(comboInitializerBodyBuilder.toString());
		comboInitializerBuilder.appendLn(tabGen(1)+"}");
		builder.append(comboInitializerBuilder.toString());
	}
	
	private void generateLabelInitializer(Element element){
		for(Attribute attr : element.getAttribute_collection()){
			builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+" = new org.eclipse.swt.widgets.Label(parent, "+
					"org.eclipse.swt.SWT.NONE);");
			builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+".setText("+quotscape+attr.getPrettyName()+quotscape+");");
		}
	}

	private void generateNoneCustomTypeInitializer(Element element){
		for(Attribute attr : element.getAttribute_collection()){
			if (!attr.getType().contains("#{")){
				builder.appendLn(tabGen(2)+"this."+attr.getName()+
						" = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);");
				builder.appendLn(tabGen(2)+"this."+attr.getName()+
						".setText("+quotscape+attr.getDefault_value()+quotscape+");");
			}
		}
	}
	
	private void generateCustomTypeInitializer(Element element){
		for (Attribute attr : element.getAttribute_collection()){
			if (attr.getType().contains("#{")){
				builder.appendLn(tabGen(2)+"this."+attr.getName()+
						" = new org.eclipse.swt.widgets.Combo(parent, org.eclipse.swt.SWT.NONE);");
			}
		}
	}
	
	private void generateCustomBodyMethodInitializer(Element element){
		for (Attribute attr : element.getAttribute_collection()){
			if (attr.getType().contains("#{")){
				hasCustomValueDefined = true;
				try {
					String processedType = DataTypeConversion.getProcessedType(attr.getType());
					String processedDefaultValue = DataTypeConversion.getProcessedValue(attr.getType(), 
							attr.getDefault_value());
					comboInitializerBodyBuilder.appendLn(tabGen(2)+
								"for(int index = 0; index < pattern01.helpers.generated."+
									processedType+".getOptionCollection().size(); index++){");
					comboInitializerBodyBuilder.appendLn(tabGen(3)+"this."+attr.getName()+".add("+
							"pattern01.helpers.generated."+processedType+".getOptionCollection().get(index));");
					comboInitializerBodyBuilder.appendLn(tabGen(2)+"}");
					if (attr.getDefault_value()!= null && !attr.getDefault_value().equals("")){
						comboInitializerBodyBuilder.appendLn(tabGen(2)+"this."+attr.getName()+".select("+
								"this."+attr.getName()+".indexOf("+quotscape+processedDefaultValue+quotscape+"));");
					}

				} catch (SecurityException | IllegalArgumentException  e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void generateListeners(Element element){
		builder.clrlf();
		builder.appendLn(tabGen(1)+"private void addListeners() {");
		for(Attribute attr : element.getAttribute_collection()){
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
	}
	
	private void generateEditorOverrides(){
		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public void init(org.eclipse.ui.IEditorSite site, org.eclipse.ui.IEditorInput input) {");
		builder.appendLn(tabGen(2)+"setSite(site);");
		builder.appendLn(tabGen(2)+"setInput(input);");
		builder.appendLn(tabGen(1)+"}");
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public void doSave(org.eclipse.core.runtime.IProgressMonitor monitor) {");
		builder.appendLn(tabGen(2)+"this.dirty = false;");
		builder.appendLn(tabGen(1)+"}");

		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public void doSaveAs() {");
		builder.appendLn(tabGen(2)+"this.dirty = false;");
		builder.appendLn(tabGen(1)+"}");

		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public boolean isDirty() {");
		builder.appendLn(tabGen(2)+"return this.dirty;");
		builder.appendLn(tabGen(1)+"}");
		
		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public boolean isSaveAsAllowed() {");
		builder.appendLn(tabGen(2)+"return true;");
		builder.appendLn(tabGen(1)+"}");

		builder.clrlf();
		builder.appendLn(tabGen(1)+"@Override");
		builder.appendLn(tabGen(1)+"public void setFocus() {");
		builder.appendLn(tabGen(1)+"}");
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