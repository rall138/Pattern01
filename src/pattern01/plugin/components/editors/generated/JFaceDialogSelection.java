

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

public class JFaceDialogSelection extends TitleAreaDialog {
	
	private Tree parent = null;
	
	private org.eclipse.swt.widgets.Label name_label = null;
	private org.eclipse.swt.widgets.Text name_text = null;
	private org.eclipse.swt.widgets.Label description_label = null;
	private org.eclipse.swt.widgets.Text description_text = null;
	private org.eclipse.swt.widgets.Label reference_label = null;
	private org.eclipse.swt.widgets.Text reference_text = null;
	private org.eclipse.swt.widgets.Label insert_label = null;
	private org.eclipse.swt.widgets.Text insert_text = null;
	private org.eclipse.swt.widgets.Label update_label = null;
	private org.eclipse.swt.widgets.Text update_text = null;
	private org.eclipse.swt.widgets.Label delete_label = null;
	private org.eclipse.swt.widgets.Text delete_text = null;
	private org.eclipse.swt.widgets.Label display_label = null;
	private org.eclipse.swt.widgets.Text display_text = null;
	
	public JFaceDialogSelection(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("Selection properties");
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

		description_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		description_label.setText("Description");
		description_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData description_layout = new org.eclipse.swt.layout.GridData();
		description_layout.grabExcessHorizontalSpace = true;
		description_layout.horizontalAlignment = GridData.FILL;
		description_text.setLayoutData(description_layout);

		reference_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		reference_label.setText("Reference");
		reference_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData reference_layout = new org.eclipse.swt.layout.GridData();
		reference_layout.grabExcessHorizontalSpace = true;
		reference_layout.horizontalAlignment = GridData.FILL;
		reference_text.setLayoutData(reference_layout);

		insert_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		insert_label.setText("Insert");
		insert_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData insert_layout = new org.eclipse.swt.layout.GridData();
		insert_layout.grabExcessHorizontalSpace = true;
		insert_layout.horizontalAlignment = GridData.FILL;
		insert_text.setLayoutData(insert_layout);

		update_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		update_label.setText("Update");
		update_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData update_layout = new org.eclipse.swt.layout.GridData();
		update_layout.grabExcessHorizontalSpace = true;
		update_layout.horizontalAlignment = GridData.FILL;
		update_text.setLayoutData(update_layout);

		delete_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		delete_label.setText("Delete");
		delete_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData delete_layout = new org.eclipse.swt.layout.GridData();
		delete_layout.grabExcessHorizontalSpace = true;
		delete_layout.horizontalAlignment = GridData.FILL;
		delete_text.setLayoutData(delete_layout);

		display_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		display_label.setText("Display");
		display_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData display_layout = new org.eclipse.swt.layout.GridData();
		display_layout.grabExcessHorizontalSpace = true;
		display_layout.horizontalAlignment = GridData.FILL;
		display_text.setLayoutData(display_layout);

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
	
	
	private pattern01.helpers.generated.Selection getSelectedInstance(){
		return ((pattern01.helpers.generated.Selection)this.parent.getSelection()[0].getData("class_instance"));
	}
	
	
	private void getPropertiesFromInstance(){
		pattern01.helpers.generated.Selection selection = getSelectedInstance();
		this.name_text.setText(selection.getName());
		this.description_text.setText(selection.getDescription());
		this.reference_text.setText(selection.getReference());
//		this.insert_text.setText(selection.getInsert());
//		this.update_text.setText(selection.getUpdate());
//		this.delete_text.setText(selection.getDelete());
//		this.display_text.setText(selection.getDisplay());
	}
	
	
	private void savePropertiesOnInstance(){
		pattern01.helpers.generated.Selection selection = getSelectedInstance();
		selection.setName(this.name_text.getText());
		selection.setDescription(this.description_text.getText());
		selection.setReference(this.reference_text.getText());
//		selection.setInsert(this.insert_text.getText());
//		selection.setUpdate(this.update_text.getText());
//		selection.setDelete(this.delete_text.getText());
//		selection.setDisplay(this.display_text.getText());
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