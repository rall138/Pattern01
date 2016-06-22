package pattern01.helpers.definitiongen;

import java.util.Calendar;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.CustomStringBuilder;
import pattern01.helpers.DataTypeConversion;
import pattern01.helpers.definitiongen.groups.GroupGenerator;
import pattern01.helpers.definitiongen.groups.Pair;
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
	private CustomStringBuilder groupInitializarBuilder = null;
	private boolean hasCustomValueDefined = false;
	private GroupGenerator gGenerator = null;
	
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
		groupInitializarBuilder = new CustomStringBuilder();
	}
	
	private void generateClassHeader(Element element){
		builder.appendLn("package pattern01.plugin.components.editors.generated;");
		builder.appendLn("");
		builder.appendLn(classHeaderComment);
		builder.appendLn("public class "+element.getPrettyName()+"PatternEditor"+
				" extends org.eclipse.ui.part.EditorPart{");
		
		builder.appendLn("");
		builder.appendLn(tabGen(1)+"public static final String ID = "+
				quotscape+Element.editorPackage+"."+element.getPrettyName()+Element.postFix+quotscape+";");
	}
	
	
	//TODO terminar de agrupar los elementos
	private void generateGroupControls(Element element, boolean attributeGeneration){
		gGenerator = new GroupGenerator(element, element.getAttribute_collection());
		String group = "";
		for (Pair pair : gGenerator.getPairCollection()){
			if (!group.equalsIgnoreCase(pair.getValue().toString())){
				group = pair.getValue().toString();
				if (attributeGeneration){
					attributeBuilder.appendLn(tabGen(1)+"private org.eclipse.swt.widgets.Group group_"+group);
					attributeBuilder.append(" = null;");
				}else{
					groupInitializarBuilder.appendLn(tabGen(2)+"this.group_"+group+" ");
					groupInitializarBuilder.append("= new org.eclipse.swt.widgets.Group(parent, org.eclipse.swt.SWT.NONE);");
					groupInitializarBuilder.appendLn(tabGen(2)+"this.group_"+group);
					groupInitializarBuilder.append(".setLayout(new org.eclipse.swt.layout.GridLayout(2, false));");
					groupInitializarBuilder.appendLn(tabGen(2)+"this.group_"+group+".setText(");
					groupInitializarBuilder.append(quotscape+pair.getValue().toString()+quotscape+");");
				}
			}
		}
	}
	
	private String getElementGroup(Element element, Attribute attr){
		String groupName = "";
		int index = 0;
		boolean isItemFound = false;
		while (index < gGenerator.getPairCollection().size() && !isItemFound){
			if (gGenerator.getPairCollection().get(index).getName().toString()
					.equalsIgnoreCase(attr.getPrettyName())){
				groupName = gGenerator.getPairCollection().get(index).getValue().toString();
				isItemFound = true;
			}else{
				index++;
			}
		}
		return groupName;
	}
	
	private void generateAttributes(Element element){
		attributeBuilder.appendLn(tabGen(1)+"private boolean dirty = false;");
		
		//Group control generator
		this.generateGroupControls(element, true);		
		
		for(Attribute attr : element.getAttribute_collection()){
			
			attributeBuilder.appendLn(tabGen(1)+"private org.eclipse.swt.widgets.Label label_"+attr.getName()+" = null;");
			if (attr.getType().contains("#{")){
				attributeBuilder.appendLn(tabGen(1)+
						"private org.eclipse.swt.widgets.Combo "+attr.getName()+" = null;");
				hasCustomValueDefined = true;
			}else{
				if (attr.getType().equalsIgnoreCase("java.lang.Boolean")){
					attributeBuilder.appendLn(tabGen(1)+
							"private org.eclipse.swt.widgets.Button "+attr.getName()+" = null;");
					
				}else{
					attributeBuilder.appendLn(tabGen(1)+
							"private org.eclipse.swt.widgets.Text "+attr.getName()+" = null;");
				}
				//Generate getters and setters
				generateGetterAndSettersOfAttributes(attr);
			}
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
		builder.appendLn(tabGen(2)+"org.eclipse.swt.layout.FillLayout layout = new org.eclipse.swt.layout.FillLayout();");
		builder.appendLn(tabGen(2)+"org.eclipse.swt.layout.GridData controlLayout = new org.eclipse.swt.layout.GridData();");
		builder.appendLn(tabGen(2)+"controlLayout.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;");
		builder.appendLn(tabGen(2)+"layout.type = org.eclipse.swt.SWT.VERTICAL;");
		builder.appendLn(tabGen(2)+"parent.setLayout(layout);");
		generateGroupControls(element, false);
		generateControlInitilizerOnConstruct(element);
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
	
	private void generateControlInitilizerOnConstruct(Element element){
		//Group control initializer
		builder.append(groupInitializarBuilder.toString());
		for(Attribute attr : element.getAttribute_collection()){
			
			// We get the group name for the current attribute, so we attach the new swt control to it.
			String groupName = this.getElementGroup(element, attr);
			groupName = "this.group_"+groupName;
			
			builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+" = new org.eclipse.swt.widgets.Label("+groupName+", "+
					"org.eclipse.swt.SWT.NONE);");
			builder.appendLn(tabGen(2)+"this.label_"+attr.getName()+".setText("+quotscape+attr.getPrettyName()+quotscape+");");
			if (!attr.getType().contains("#{")){
				if (attr.getType().equalsIgnoreCase("java.lang.Boolean")){
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							" = new org.eclipse.swt.widgets.Button("+groupName+", org.eclipse.swt.SWT.CHECK);");
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							".setSelection("+DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value())+");");
				}else{
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							" = new org.eclipse.swt.widgets.Text("+groupName+", org.eclipse.swt.SWT.NONE);");
					builder.appendLn(tabGen(2)+"this."+attr.getName()+
							".setText("+DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value())+");");
				}
			}else{
				builder.appendLn(tabGen(2)+"this."+attr.getName()+
						" = new org.eclipse.swt.widgets.Combo("+groupName+", org.eclipse.swt.SWT.NONE);");
			}
			builder.appendLn(tabGen(2)+"this."+attr.getName()+".setLayoutData(controlLayout);");
		}
	}

	private void generateCustomBodyMethodInitializer(Element element){
		for (Attribute attr : element.getAttribute_collection()){
			if (attr.getType().contains("#{")){
				hasCustomValueDefined = true;
				try {
					String processedType = DataTypeConversion.getProcessedType(attr.getType());
					String processedDefaultValue = DataTypeConversion.getProcessedValue(attr.getType(), attr.getDefault_value());
					comboInitializerBodyBuilder.appendLn(tabGen(2)+"java.lang.String selectedValue ="+quotscape+quotscape+";");
					comboInitializerBodyBuilder.appendLn(tabGen(2)+"for(int index = 0; index < pattern01.helpers.generated.");
					comboInitializerBodyBuilder.append(processedType+".getOptionCollection().size(); index++){");
					comboInitializerBodyBuilder.appendLn(tabGen(3)+"this."+attr.getName()+".add(");
					comboInitializerBodyBuilder.append(Element.classPackage+"."+processedType+".getOptionCollection().get(index));");
					comboInitializerBodyBuilder.appendLn(tabGen(3)+"if ("+Element.classPackage+"."+processedType);
					comboInitializerBodyBuilder.append(".fullyQualifiedComparer("+quotscape+processedDefaultValue+quotscape+",");
					comboInitializerBodyBuilder.append(Element.classPackage+"."+processedType+".getOptionCollection().get(index))){");
					comboInitializerBodyBuilder.appendLn(tabGen(4)+"selectedValue = ");
					comboInitializerBodyBuilder.append(Element.classPackage+"."+processedType+".getOptionCollection().get(index);");
					comboInitializerBodyBuilder.appendLn(tabGen(3)+"}"); // end-if
					comboInitializerBodyBuilder.appendLn(tabGen(2)+"}"); // end-for
					comboInitializerBodyBuilder.appendLn(tabGen(2)+"this."+attr.getName()+".select(");
					comboInitializerBodyBuilder.append("this."+attr.getName()+".indexOf(selectedValue));");
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