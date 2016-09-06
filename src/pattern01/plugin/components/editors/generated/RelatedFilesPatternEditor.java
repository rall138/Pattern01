
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon Sep 05 21:47:00 UYT 2016
	* Creator: rlomez
	**/
public class RelatedFilesPatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.RelatedFilesPatternEditor";
	private boolean dirty = false;
	private org.eclipse.swt.widgets.Group group_Default = null;
	private org.eclipse.swt.widgets.Label label_image = null;
	private org.eclipse.swt.widgets.Text image = null;

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
		((pattern01.helpers.generated.RelatedFiles)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setImage(this.getImageValue());
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

	public java.lang.String getImageValue(){
		return this.image.getText();
	}
}