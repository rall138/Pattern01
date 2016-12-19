

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

public class JFaceDialogCommandButton extends TitleAreaDialog {
	
	private Tree parent = null;
	
	private org.eclipse.swt.widgets.Label name_label = null;
	private org.eclipse.swt.widgets.Text name_text = null;
	private org.eclipse.swt.widgets.Label action_label = null;
	private org.eclipse.swt.widgets.Text action_text = null;
	private org.eclipse.swt.widgets.Label description_label = null;
	private org.eclipse.swt.widgets.Text description_text = null;
	
	public JFaceDialogCommandButton(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("CommandButton properties");
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

		action_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		action_label.setText("Action");
		action_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData action_layout = new org.eclipse.swt.layout.GridData();
		action_layout.grabExcessHorizontalSpace = true;
		action_layout.horizontalAlignment = GridData.FILL;
		action_text.setLayoutData(action_layout);

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
	
	
	private pattern01.helpers.generated.CommandButton getSelectedInstance(){
		return ((pattern01.helpers.generated.CommandButton)this.parent.getSelection()[0].getData("class_instance"));
	}
	
	
	private void getPropertiesFromInstance(){
		pattern01.helpers.generated.CommandButton patternInstance_form_commandButton = getSelectedInstance();
		this.name_text.setText(patternInstance_form_commandButton.getName());
		this.action_text.setText(patternInstance_form_commandButton.getAction());
		this.description_text.setText(patternInstance_form_commandButton.getDescription());
	}
	
	
	private void savePropertiesOnInstance(){
		pattern01.helpers.generated.CommandButton patternInstance_form_commandButton = getSelectedInstance();
		patternInstance_form_commandButton.setName(this.name_text.getText());
		patternInstance_form_commandButton.setAction(this.action_text.getText());
		patternInstance_form_commandButton.setDescription(this.description_text.getText());
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