
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Sep 05 21:47:00 UYT 2016
	* Creator: rlomez
	**/
public class SelectionPatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.SelectionPatternEditor";
	private boolean dirty = false;
	private org.eclipse.swt.widgets.Group group_Default = null;
	private org.eclipse.swt.widgets.Group group_Mode = null;
	private org.eclipse.swt.widgets.Label label_name = null;
	private org.eclipse.swt.widgets.Text name = null;
	private org.eclipse.swt.widgets.Label label_description = null;
	private org.eclipse.swt.widgets.Text description = null;
	private org.eclipse.swt.widgets.Label label_reference = null;
	private org.eclipse.swt.widgets.Text reference = null;
	private org.eclipse.swt.widgets.Label label_insert = null;
	private org.eclipse.swt.widgets.Button insert = null;
	private org.eclipse.swt.widgets.Label label_update = null;
	private org.eclipse.swt.widgets.Button update = null;
	private org.eclipse.swt.widgets.Label label_delete = null;
	private org.eclipse.swt.widgets.Button delete = null;
	private org.eclipse.swt.widgets.Label label_display = null;
	private org.eclipse.swt.widgets.Button display = null;

	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		org.eclipse.jface.viewers.TableViewer tviewer = new org.eclipse.jface.viewers.TableViewer(parent);

		// Grid lines visible
		tviewer.getTable().setLinesVisible(true);
		// Column titles visible
		tviewer.getTable().setHeaderVisible(true);

		tviewer.setContentProvider(org.eclipse.jface.viewers.ArrayContentProvider.getInstance());
		tviewer.setColumnProperties(new String[]{"Property","Value"});
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
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setName(this.getNameValue());
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setDescription(this.getDescriptionValue());
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setReference(this.getReferenceValue());
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setInsert(this.getInsertValue());
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setUpdate(this.getUpdateValue());
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setDelete(this.getDeleteValue());
		((pattern01.helpers.generated.Selection)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setDisplay(this.getDisplayValue());
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
		
		this.insert.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.update.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.delete.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.display.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
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

	public java.lang.Boolean getInsertValue(){
		return java.lang.Boolean.valueOf(this.insert.getText());
	}

	public java.lang.Boolean getUpdateValue(){
		return java.lang.Boolean.valueOf(this.update.getText());
	}

	public java.lang.Boolean getDeleteValue(){
		return java.lang.Boolean.valueOf(this.delete.getText());
	}

	public java.lang.Boolean getDisplayValue(){
		return java.lang.Boolean.valueOf(this.display.getText());
	}
}