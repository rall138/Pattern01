

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

public class JFaceDialogPatternInstance extends TitleAreaDialog {
	
	private Tree parent = null;
	
	private final static String PatternInstance;
	private org.eclipse.swt.widgets.Label generator_label = null;
	private org.eclipse.swt.widgets.Text generator_text = null;
	private org.eclipse.swt.widgets.Label name_label = null;
	private org.eclipse.swt.widgets.Text name_text = null;
	private org.eclipse.swt.widgets.Label description_label = null;
	private org.eclipse.swt.widgets.Text description_text = null;
	
	public JFaceDialogPatternInstance(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("PatternInstance properties");
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
		
		generator_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		generator_label.setText("Generator");
		generator_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData generator_layout = new org.eclipse.swt.layout.GridData();
		generator_layout.grabExcessHorizontalSpace = true;
		generator_layout.horizontalAlignment = GridData.FILL;
		generator_text.setLayoutData(generator_layout);

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
		pattern01.helpers.generated.PatternInstance patterninstance=(pattern01.helpers.generated.PatternInstance) this.parent.getSelection()[0].getData("class_instance");
		this.generator_text.setText(patterninstance.getGenerator());
		this.name_text.setText(patterninstance.getName());
		this.description_text.setText(patterninstance.getDescription());
	}
	
	
	private void setProperties(){
		pattern01.helpers.generated.PatternInstance patterninstance=(pattern01.helpers.generated.PatternInstance) this.parent.getSelection()[0].getData("class_instance");
		patterninstance.setGenerator(patterninstance.getgenerator());
		patterninstance.setName(patterninstance.getname());
		patterninstance.setDescription(patterninstance.getdescription());
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