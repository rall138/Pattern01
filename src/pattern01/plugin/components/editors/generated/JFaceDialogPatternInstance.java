
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

package pattern01.plugins.components.editors.generated

public class <<class_name>>_dialog extends TitleAreaDialog {
	
	public <<class_name>>_dialog(Shell parentShell){
		super(parentShell);
	}

	protected Control createAreaDialog(Composite parent){
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        this.createProperties(container);
        return area;		
	}
	
	private void createProperties(Composite container){
		<<property_method_body>>
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID,
            IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID,
            IDialogConstants.CANCEL_LABEL, false);
    }	
	
	protected Point getInitialSize(){
	 	retrun new Point(500, 375);
	}
	
	@Override
	protected void okPressed(){
		<<property_save>>
		super.okPressed();
	}

}