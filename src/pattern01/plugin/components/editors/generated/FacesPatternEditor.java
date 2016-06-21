
package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Tue Jun 21 17:43:32 GFT 2016
	* Creator: rlomez
	**/
public class FacesPatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.FacesPatternEditor";
	private boolean dirty = false;

	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {

		org.eclipse.swt.layout.GridLayout layout = new org.eclipse.swt.layout.GridLayout(2, false);
		org.eclipse.swt.layout.GridData controlLayout = new org.eclipse.swt.layout.GridData();
		controlLayout.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL_HORIZONTAL;
		layout.horizontalSpacing = 8;
		layout.verticalSpacing = 5;
		parent.setLayout(layout);
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
	}

	private void initializeCombos(){
	}
}