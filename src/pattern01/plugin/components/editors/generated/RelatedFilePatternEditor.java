
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Jun 21 17:43:32 GFT 2016
	* Creator: rlomez
	**/
public class RelatedFilePatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.RelatedFilePatternEditor";
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

		org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout(2, false);
		org.eclipse.swt.layout.GridData controlLayout = new org.eclipse.swt.layout.GridData();
		controlLayout.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
		layout.horizontalSpacing = 8;
		layout.verticalSpacing = 5;
		parent.setLayout(layout);
		this.group_Default = new org.eclipse.swt.widgets.Group(parent, org.eclipse.swt.SWT.NONE);
		this.group_Default.setText("Default");
		this.label_name = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_name.setText("Name");
		this.name = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.name.setText("");
		this.name.setLayoutData(controlLayout);
		this.label_description = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_description.setText("Description");
		this.description = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.description.setText("");
		this.description.setLayoutData(controlLayout);
		this.label_reference = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_reference.setText("Reference");
		this.reference = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.reference.setText("");
		this.reference.setLayoutData(controlLayout);
		this.label_image = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_image.setText("Image");
		this.image = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.image.setText("ww.png");
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
}