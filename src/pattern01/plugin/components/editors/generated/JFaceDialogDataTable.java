

package pattern01.plugin.components.editors.generated;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;

public class JFaceDialogDataTable extends TitleAreaDialog {
	
	private Tree parent = null;
	
	private org.eclipse.swt.widgets.Label name_label = null;
	private org.eclipse.swt.widgets.Text name_text = null;
	private org.eclipse.swt.widgets.Label managedBean_label = null;
	private org.eclipse.swt.widgets.Text managedBean_text = null;
	private org.eclipse.swt.widgets.Label description_label = null;
	private org.eclipse.swt.widgets.Text description_text = null;
	
	public JFaceDialogDataTable(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("DataTable properties");
	    getPropertiesFromInstance();
	  }

  	@Override
	protected Control createDialogArea(Composite parent){
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);
        this.createProperties(container);
        return area;
	}
	
	private void createProperties(Composite container){
		
		name_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		name_label.setText("Name");
		name_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData name_layout = new org.eclipse.swt.layout.GridData();
		name_layout.grabExcessHorizontalSpace = true;
		name_layout.horizontalAlignment = GridData.FILL;
		name_text.setLayoutData(name_layout);

		managedBean_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		managedBean_label.setText("ManagedBean");
		managedBean_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData managedBean_layout = new org.eclipse.swt.layout.GridData();
		managedBean_layout.grabExcessHorizontalSpace = true;
		managedBean_layout.horizontalAlignment = GridData.FILL;
		managedBean_text.setLayoutData(managedBean_layout);

		description_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		description_label.setText("Description");
		description_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData description_layout = new org.eclipse.swt.layout.GridData();
		description_layout.grabExcessHorizontalSpace = true;
		description_layout.horizontalAlignment = GridData.FILL;
		description_text.setLayoutData(description_layout);

	}
	
	protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID,
            IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID,
            IDialogConstants.CANCEL_LABEL, false);
    }	
	
	protected Point getInitialSize(){
	 	return new Point(500, 375);
	}
	
	
	private pattern01.helpers.generated.DataTable getSelectedInstance(){
		return ((pattern01.helpers.generated.DataTable)this.parent.getSelection()[0].getData("class_instance"));
	}
	
	
	private void getPropertiesFromInstance(){
		pattern01.helpers.generated.DataTable patternInstance_form_dataTable = getSelectedInstance();
		this.name_text.setText(patternInstance_form_dataTable.getName());
		this.managedBean_text.setText(patternInstance_form_dataTable.getManagedBean());
		this.description_text.setText(patternInstance_form_dataTable.getDescription());
	}
	
	
	private void savePropertiesOnInstance(){
		pattern01.helpers.generated.DataTable patternInstance_form_dataTable = getSelectedInstance();
		patternInstance_form_dataTable.setName(this.name_text.getText());
		patternInstance_form_dataTable.setManagedBean(this.managedBean_text.getText());
		patternInstance_form_dataTable.setDescription(this.description_text.getText());
		pattern01.helpers.XMLPropertyHelper.saveProperties(this.parent.getSelection()[0]);
	}
	
	@Override
	protected void okPressed(){
		savePropertiesOnInstance();
		super.okPressed();
	}

	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}
	
}