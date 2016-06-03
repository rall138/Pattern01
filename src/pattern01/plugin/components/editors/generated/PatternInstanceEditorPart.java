package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 02 23:33:24 UYT 2016
	* Creator: rlomez
	**/
public class PatternInstanceEditorPart extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.PatternInstanceEditorPart";

	private boolean dirty;
	public org.eclipse.swt.widgets.Label label_generator = null;
	private org.eclipse.swt.widgets.Combo generator = null;
	public org.eclipse.swt.widgets.Label label_name = null;
	private org.eclipse.swt.widgets.Text name = null;
	public org.eclipse.swt.widgets.Label label_description = null;
	private org.eclipse.swt.widgets.Text description = null;
	public org.eclipse.swt.widgets.Label label_image = null;
	private org.eclipse.swt.widgets.Text image = null;


	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {

		org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout();
		layout.numColumns = 2;
		layout.horizontalSpacing = 8;
		parent.setLayout(layout);
		this.label_generator = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_generator.setText("Generator");
		this.generator = new org.eclipse.swt.widgets.Combo(parent, org.eclipse.swt.SWT.NONE);
		this.label_name = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_name.setText("Name");
		this.name = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.name.setText("");
		this.label_description = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_description.setText("Description");
		this.description = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.description.setText("");
		this.label_image = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.NONE);
		this.label_image.setText("Image");
		this.image = new org.eclipse.swt.widgets.Text(parent, org.eclipse.swt.SWT.NONE);
		this.image.setText("ww.png");
		initializeCombos();
		addListeners();
	}

	private void initializeCombos(){
		for(int index = 0; index < pattern01.helpers.generated.Generator.getOptionCollection().size(); index++){
			this.generator.add(pattern01.helpers.generated.Generator.getOptionCollection().get(index));
		}
		this.generator.select(this.generator.indexOf("WEB"));
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

	public java.lang.String getLabel_NameText(){
		return this.name.getText();
	}

	public java.lang.String getLabel_DescriptionText(){
		return this.description.getText();
	}

	public java.lang.String getLabel_ImageText(){
		return this.image.getText();
	}

}
