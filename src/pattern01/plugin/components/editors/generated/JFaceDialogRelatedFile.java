

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

public class JFaceDialogRelatedFile extends TitleAreaDialog {
	
	public JFaceDialogRelatedFile(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("RelatedFile properties");
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
		
		org.eclipse.swt.widgets.Label Name_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		Name_label.setText("Name");
		org.eclipse.swt.widgets.Text name_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData name_layout = new org.eclipse.swt.layout.GridData();
		name_layout.grabExcessHorizontalSpace = true;
		name_layout.horizontalAlignment = GridData.FILL;
		name_text.setLayoutData(name_layout);

		org.eclipse.swt.widgets.Label Description_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		Description_label.setText("Description");
		org.eclipse.swt.widgets.Text description_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData description_layout = new org.eclipse.swt.layout.GridData();
		description_layout.grabExcessHorizontalSpace = true;
		description_layout.horizontalAlignment = GridData.FILL;
		description_text.setLayoutData(description_layout);

		org.eclipse.swt.widgets.Label Reference_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		Reference_label.setText("Reference");
		org.eclipse.swt.widgets.Text reference_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData reference_layout = new org.eclipse.swt.layout.GridData();
		reference_layout.grabExcessHorizontalSpace = true;
		reference_layout.horizontalAlignment = GridData.FILL;
		reference_text.setLayoutData(reference_layout);

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
	
	@Override
	protected void okPressed(){
		/* TODO - Generar cuerpo del save */
		super.okPressed();
	}

}