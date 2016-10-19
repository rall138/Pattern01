

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

public class JFaceDialogRelatedFiles extends TitleAreaDialog {
	
	private Tree parent = null;
	
	private final static String RelatedFiles;
	private org.eclipse.swt.widgets.Label image_label = null;
	private org.eclipse.swt.widgets.Text image_text = null;
	
	public JFaceDialogRelatedFiles(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("RelatedFiles properties");
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
		
		image_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		image_label.setText("Image");
		image_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData image_layout = new org.eclipse.swt.layout.GridData();
		image_layout.grabExcessHorizontalSpace = true;
		image_layout.horizontalAlignment = GridData.FILL;
		image_text.setLayoutData(image_layout);

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
	
	
	private void getProperties(){
		pattern01.helpers.generated.RelatedFiles relatedFiles=(pattern01.helpers.generated.RelatedFiles) this.parent.getSelection()[0].getData("class_instance");
		this.image_text.setText(relatedFiles.getImage());
	}
	
	
	private void setProperties(){
		pattern01.helpers.generated.RelatedFiles relatedFiles=(pattern01.helpers.generated.RelatedFiles) this.parent.getSelection()[0].getData("class_instance");
		relatedFiles.setImage(relatedFiles.getimage());
		pattern01.helpers.XMLPropertyHelper.saveProperties(this.parent);
	
	@Override
	protected void okPressed(){
		/* TODO - Generar cuerpo del save */
		super.okPressed();
	}

	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}
	
}