package pattern01.plugin.components.editors.generated;

	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu May 12 17:45:00 GFT 2016
	* Creator: rlomez
	**/
public class RegisterEditorPart extends org.eclipse.ui.part.EditorPart{

	private org.eclipse.swt.widgets.Label label_Name = null;
	private org.eclipse.swt.widgets.Label label_Description = null;
	private org.eclipse.swt.widgets.Label label_Reference = null;
	private org.eclipse.swt.widgets.Label label_Image = null;


	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		label_Name = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		label_Description = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		label_Reference = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
		label_Image = new org.eclipse.swt.widgets.Label(parent, org.eclipse.swt.SWT.FLAT);
	}

	@Override
	public void init(org.eclipse.ui.IEditorSite site, org.eclipse.ui.IEditorInput input) {
		setSite(site);
		setInput(input);
	}

	@Override
	public void doSave(org.eclipse.core.runtime.IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void setFocus() {
	}

	public java.lang.String getLabel_NameText(){
		return this.label_Name.getText();
	}

	public java.lang.String getLabel_DescriptionText(){
		return this.label_Description.getText();
	}

	public java.lang.String getLabel_ReferenceText(){
		return this.label_Reference.getText();
	}

	public java.lang.String getLabel_ImageText(){
		return this.label_Image.getText();
	}

}
