
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Jul 05 16:37:51 GFT 2016
	* Creator: rlomez
	**/
public class RegisterPatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.RegisterPatternEditor";
	private boolean dirty = false;
	private org.eclipse.swt.widgets.Group group_Default = null;
	private org.eclipse.swt.widgets.Label label_name = null;
	private org.eclipse.swt.widgets.Text name = null;
	private org.eclipse.swt.widgets.Label label_description = null;
	private org.eclipse.swt.widgets.Text description = null;
	private org.eclipse.swt.widgets.Label label_reference = null;
	private org.eclipse.swt.widgets.Text reference = null;
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
		this.label_reference = new org.eclipse.swt.widgets.Label(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.label_reference.setText("Reference");
		this.reference = new org.eclipse.swt.widgets.Text(this.group_Default, org.eclipse.swt.SWT.NONE);
		this.reference.setText("");
		this.reference.setLayoutData(controlLayout);
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
		((pattern01.helpers.generated.Register)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setName(this.getNameValue());
		((pattern01.helpers.generated.Register)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setDescription(this.getDescriptionValue());
		((pattern01.helpers.generated.Register)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setReference(this.getReferenceValue());
		((pattern01.helpers.generated.Register)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setImage(this.getImageValue());
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
		
		this.reference.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
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
	}

	public java.lang.String getNameValue(){
		return this.name.getText();
	}

	public java.lang.String getDescriptionValue(){
		return this.description.getText();
	}

	public java.lang.String getReferenceValue(){
		return this.reference.getText();
	}

	public java.lang.String getImageValue(){
		return this.image.getText();
	}
}