
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Wed Jun 22 00:39:46 UYT 2016
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
	private org.eclipse.swt.widgets.Label label_image = null;
	private org.eclipse.swt.widgets.Text image = null;
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

		org.eclipse.swt.layout.FillLayout layout = new org.eclipse.swt.layout.FillLayout();
		org.eclipse.swt.layout.GridData controlLayout = new org.eclipse.swt.layout.GridData();
		controlLayout.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
		layout.type = org.eclipse.swt.SWT.VERTICAL;
		parent.setLayout(layout);
		this.group_Default = new org.eclipse.swt.widgets.Group(parent, org.eclipse.swt.SWT.NONE);
		this.group_Default.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		this.group_Default.setText("Default");
		this.group_Mode = new org.eclipse.swt.widgets.Group(parent, org.eclipse.swt.SWT.NONE);
		this.group_Mode.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		this.group_Mode.setText("Mode");
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
		this.image.setText("ww.png");
		this.image.setLayoutData(controlLayout);
		this.label_insert = new org.eclipse.swt.widgets.Label(this.group_Mode, org.eclipse.swt.SWT.NONE);
		this.label_insert.setText("Insert");
		this.insert = new org.eclipse.swt.widgets.Button(this.group_Mode, org.eclipse.swt.SWT.CHECK);
		this.insert.setSelection(true);
		this.insert.setLayoutData(controlLayout);
		this.label_update = new org.eclipse.swt.widgets.Label(this.group_Mode, org.eclipse.swt.SWT.NONE);
		this.label_update.setText("Update");
		this.update = new org.eclipse.swt.widgets.Button(this.group_Mode, org.eclipse.swt.SWT.CHECK);
		this.update.setSelection(true);
		this.update.setLayoutData(controlLayout);
		this.label_delete = new org.eclipse.swt.widgets.Label(this.group_Mode, org.eclipse.swt.SWT.NONE);
		this.label_delete.setText("Delete");
		this.delete = new org.eclipse.swt.widgets.Button(this.group_Mode, org.eclipse.swt.SWT.CHECK);
		this.delete.setSelection(true);
		this.delete.setLayoutData(controlLayout);
		this.label_display = new org.eclipse.swt.widgets.Label(this.group_Mode, org.eclipse.swt.SWT.NONE);
		this.label_display.setText("Display");
		this.display = new org.eclipse.swt.widgets.Button(this.group_Mode, org.eclipse.swt.SWT.CHECK);
		this.display.setSelection(true);
		this.display.setLayoutData(controlLayout);
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

	public java.lang.String getLabel_NameText(){
		return this.name.getText();
	}

	public java.lang.String getLabel_DescriptionText(){
		return this.description.getText();
	}

	public java.lang.String getLabel_ReferenceText(){
		return this.reference.getText();
	}

	public java.lang.String getLabel_ImageText(){
		return this.image.getText();
	}

	public java.lang.Boolean getLabel_InsertText(){
		return java.lang.Boolean.valueOf(this.insert.getText());
	}

	public java.lang.Boolean getLabel_UpdateText(){
		return java.lang.Boolean.valueOf(this.update.getText());
	}

	public java.lang.Boolean getLabel_DeleteText(){
		return java.lang.Boolean.valueOf(this.delete.getText());
	}

	public java.lang.Boolean getLabel_DisplayText(){
		return java.lang.Boolean.valueOf(this.display.getText());
	}
}