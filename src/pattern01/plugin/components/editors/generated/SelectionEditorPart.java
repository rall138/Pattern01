package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Mon May 16 12:35:08 GFT 2016
	* Creator: rlomez
	**/
public class SelectionEditorPart extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.SelectionEditorPart";

	private boolean dirty;
	public org.eclipse.swt.widgets.Label label_name = null;
	private org.eclipse.swt.widgets.Text name = null;
	public org.eclipse.swt.widgets.Label label_description = null;
	private org.eclipse.swt.widgets.Text description = null;
	public org.eclipse.swt.widgets.Label label_reference = null;
	private org.eclipse.swt.widgets.Text reference = null;
	public org.eclipse.swt.widgets.Label label_image = null;
	private org.eclipse.swt.widgets.Text image = null;
	public org.eclipse.swt.widgets.Label label_insert = null;
	private org.eclipse.swt.widgets.Text insert = null;
	public org.eclipse.swt.widgets.Label label_update = null;
	private org.eclipse.swt.widgets.Text update = null;
	public org.eclipse.swt.widgets.Label label_delete = null;
	private org.eclipse.swt.widgets.Text delete = null;
	public org.eclipse.swt.widgets.Label label_display = null;
	private org.eclipse.swt.widgets.Text display = null;


	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = true;
		layout.horizontalSpacing = 8;
		parent.setLayout(layout);
		this.label_name = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_name.setText("Name");
		this.name = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.name.setText("");
		this.label_description = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_description.setText("Description");
		this.description = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.description.setText("");
		this.label_reference = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_reference.setText("Reference");
		this.reference = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.reference.setText("");
		this.label_image = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_image.setText("Image");
		this.image = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.image.setText("ww.png");
		this.label_insert = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_insert.setText("Insert");
		this.insert = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.insert.setText("");
		this.label_update = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_update.setText("Update");
		this.update = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.update.setText("");
		this.label_delete = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_delete.setText("Delete");
		this.delete = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.delete.setText("");
		this.label_display = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		this.label_display.setText("Display");
		this.display = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.FLAT);
		this.display.setText("");
		addListeners();
	}

	/* Listeners for all node properties */
	private void addListeners(){

		this.name.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.description.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.reference.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.image.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.insert.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.update.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.delete.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});

		this.display.addKeyListener(new org.eclipse.swt.events.KeyListener() {
		@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {}
		@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
	}

	@Override
	public void init(org.eclipse.ui.IEditorSite site, org.eclipse.ui.IEditorInput input) {
		setSite(site);
		setInput(input);
	}

	@Override
	public void doSave(org.eclipse.core.runtime.IProgressMonitor monitor) {
		dirty = false;
	}

	@Override
	public void doSaveAs() {
		dirty = false;
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
