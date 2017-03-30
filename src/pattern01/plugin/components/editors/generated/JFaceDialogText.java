

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

public class JFaceDialogText extends TitleAreaDialog {
	
	private Tree parent = null;
	
	private org.eclipse.swt.widgets.Label name_label = null;
	private org.eclipse.swt.widgets.Text name_text = null;
	private org.eclipse.swt.widgets.Label value_label = null;
	private org.eclipse.swt.widgets.Text value_text = null;
	private org.eclipse.swt.widgets.Label type_label = null;
	private org.eclipse.swt.widgets.Combo type_cmb = null;
	private org.eclipse.swt.widgets.Label description_label = null;
	private org.eclipse.swt.widgets.Text description_text = null;
	
	public JFaceDialogText(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("Text properties");
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

		value_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		value_label.setText("Value");
		value_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData value_layout = new org.eclipse.swt.layout.GridData();
		value_layout.grabExcessHorizontalSpace = true;
		value_layout.horizontalAlignment = GridData.FILL;
		value_text.setLayoutData(value_layout);

		type_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		type_label.setText("Type");
		type_cmb = new org.eclipse.swt.widgets.Combo(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData type_layout = new org.eclipse.swt.layout.GridData();
		type_layout.grabExcessHorizontalSpace = true;
		type_layout.horizontalAlignment = GridData.FILL;
		type_cmb.setLayoutData(type_layout);

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
	
	
	private pattern01.helpers.generated.Text getSelectedInstance(){
		return ((pattern01.helpers.generated.Text)this.parent.getSelection()[0].getData("class_instance"));
	}
	
	
	private void getPropertiesFromInstance(){
		pattern01.helpers.generated.Text text = getSelectedInstance();
		this.name_text.setText(text.getName());
		this.value_text.setText(text.getValue());
		for(String option : pattern01.helpers.generated.TextType.getOptionCollection()){
			this.type_cmb.add(option);
		}

		this.type_cmb.select(this.type_cmb.indexOf(text.getType().toString()));
		this.description_text.setText(text.getDescription());
	}
	
	
	private void savePropertiesOnInstance(){
		pattern01.helpers.generated.Text text = getSelectedInstance();
		text.setName(this.name_text.getText());
		text.setValue(this.value_text.getText());
		text.setType(pattern01.helpers.generated.TextType.valueOf(this.type_cmb.getText()));
		text.setDescription(this.description_text.getText());
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