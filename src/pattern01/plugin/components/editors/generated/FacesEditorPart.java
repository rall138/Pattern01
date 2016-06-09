package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Jun 09 06:48:16 UYT 2016
	* Creator: rlomez
	**/
public class FacesEditorPart extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.FacesEditorPart";

	private boolean dirty = false;


	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {

		org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout();
		layout.numColumns = 2;
		layout.horizontalSpacing = 8;
		parent.setLayout(layout);
		addListeners();
	}

	private void addListeners() {
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

}
