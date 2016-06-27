
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sat Jun 25 21:23:02 UYT 2016
	* Creator: rlomez
	**/
public class PatternInstancePatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.PatternInstancePatternEditor";
	private boolean dirty = false;
	private org.eclipse.swt.widgets.Group group_Default = null;
	private org.eclipse.swt.widgets.Label label_generator = null;
	private org.eclipse.swt.widgets.Combo generator = null;
	private org.eclipse.swt.widgets.Label label_name = null;
	private org.eclipse.swt.widgets.Text name = null;
	private org.eclipse.swt.widgets.Label label_description = null;
	private org.eclipse.swt.widgets.Text description = null;
	private org.eclipse.swt.widgets.Label label_image = null;
	private org.eclipse.swt.widgets.Text image = null;

	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {

		org.eclipse.swt.layout.FillLayout layout = new org.eclipse.swt.layout.FillLayout();
		org.eclipse.swt.layout.GridData controlLayout = new org.eclipse.swt.layout.GridData();
		controlLayout.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
		layout.type = org.eclipse.swt.SWT.VERTICAL;
		parent.setLayout(layout);
		this.group_Default = new org.eclipse.swt.widgets.Group(parent, org.eclipse.swt.SWT.NONE);
		this.group_Default.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		this.group_Default.setText("Default");
		this.label_generator = new org.eclipse.swt.widgets.Label(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.label_generator.setText("Generator");
		this.generator = new org.eclipse.swt.widgets.Combo(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.generator.setLayoutData(controlLayout);
		this.label_name = new org.eclipse.swt.widgets.Label(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.label_name.setText("Name");
		this.name = new org.eclipse.swt.widgets.Text(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.name.setText("");
		this.name.setLayoutData(controlLayout);
		this.label_description = new org.eclipse.swt.widgets.Label(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.label_description.setText("Description");
		this.description = new org.eclipse.swt.widgets.Text(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.description.setText("");
		this.description.setLayoutData(controlLayout);
		this.label_image = new org.eclipse.swt.widgets.Label(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.label_image.setText("Image");
		this.image = new org.eclipse.swt.widgets.Text(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.image.setText("generic.jpg");
		this.image.setLayoutData(controlLayout);
		addListeners();
		initializeCombos();
	}

	@Override
	public void init(org.eclipse.ui.IEditorSite site, org.eclipse.ui.IEditorInput input) {
		setSite(site);
		setInput(input);
	}

	@Override
	public void doSave(org.eclipse.core.runtime.IProgressMonitor monitor) {
		String xml_definition ="";
		pattern01.plugin.components.navigator.DefaultEditorInput defaultEditorInput = (pattern01.plugin.components.navigator.DefaultEditorInput)this.getEditorInput();
		org.eclipse.swt.widgets.TreeItem parentItem = defaultEditorInput.getInstanceTree().getItem(0);
		java.util.List<pattern01.helpers.generated.PatternInstance> patternInstanceCollection = getPatternInstanceCollection(parentItem, 0, new java.util.ArrayList<pattern01.helpers.generated.PatternInstance>());
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setGenerator(pattern01.helpers.generated.Generator.valueOf(this.getGeneratorValue()));
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setName(this.getNameValue());
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setDescription(this.getDescriptionValue());
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setImage(this.getImageValue());
		this.dirty = false;
	}

	@Override
	public void doSaveAs() {
		this.dirty = false;
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void setFocus() {
	}

	private java.util.List<pattern01.helpers.generated.PatternInstance> getPatternInstanceCollection(org.eclipse.swt.widgets.TreeItem parentItem, int level, java.util.List<pattern01.helpers.generated.PatternInstance> patternInstanceCollection){
		if (level != 1){ //PatternInstance Nodes are located at secondLevel {1}
			level++;
			patternInstanceCollection.addAll(getPatternInstanceCollection(parentItem.getItems()[0],level, patternInstanceCollection));
		}else{
			for (org.eclipse.swt.widgets.TreeItem item : parentItem.getItems()){
				if (item.getText().equalsIgnoreCase("patterninstance")){
					patternInstanceCollection.add((pattern01.helpers.generated.PatternInstance)item.getData("class_instance"));
				}
			}
		}
		return patternInstanceCollection;
	}

	private void addListeners() {
		
		this.generator.addListener(org.eclipse.swt.SWT.Selection, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.name.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.description.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.image.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
	}

	private void initializeCombos(){
		java.lang.String selectedValue ="";
		for(int index = 0; index < pattern01.helpers.generated.Generator.getOptionCollection().size(); index++){
			this.generator.add(pattern01.helpers.generated.Generator.getOptionCollection().get(index));
			if (pattern01.helpers.generated.Generator.fullyQualifiedComparer("pattern01.helpers.generated.Generator.WEB",pattern01.helpers.generated.Generator.getOptionCollection().get(index))){
				selectedValue = pattern01.helpers.generated.Generator.getOptionCollection().get(index);
			}
		}
		this.generator.select(this.generator.indexOf(selectedValue));
	}

	public java.lang.String getGeneratorValue(){
		return this.generator.getText();
	}

	public java.lang.String getNameValue(){
		return this.name.getText();
	}

	public java.lang.String getDescriptionValue(){
		return this.description.getText();
	}

	public java.lang.String getImageValue(){
		return this.image.getText();
	}
}