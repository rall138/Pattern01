

package pattern01.plugin.components.editors.generated;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import pattern01.helpers.XMLPropertyHelper;

import org.eclipse.swt.layout.GridLayout;

public class JFaceDialogPatternInstance extends TitleAreaDialog {
	
	private final static String xpath = "/PatternInstance"; 
	private org.eclipse.swt.widgets.Text generator_text;
	private org.eclipse.swt.widgets.Text name_text;
	private org.eclipse.swt.widgets.Text description_text;
	private Tree parent; 
	
	public JFaceDialogPatternInstance(Shell parentShell){
		super(parentShell);
	}
	
	  @Override
	  public void create() {
	    super.create();
	    setTitle("PatternInstance properties");
	  }	

  	@Override
	public int open() {
		return super.open();
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
		
		org.eclipse.swt.widgets.Label Generator_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		Generator_label.setText("Generator");
		generator_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData generator_layout = new org.eclipse.swt.layout.GridData();
		generator_layout.grabExcessHorizontalSpace = true;
		generator_layout.horizontalAlignment = GridData.FILL;
		generator_text.setLayoutData(generator_layout);

		org.eclipse.swt.widgets.Label Name_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		Name_label.setText("Name");
		name_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData name_layout = new org.eclipse.swt.layout.GridData();
		name_layout.grabExcessHorizontalSpace = true;
		name_layout.horizontalAlignment = GridData.FILL;
		name_text.setLayoutData(name_layout);

		org.eclipse.swt.widgets.Label Description_label = new org.eclipse.swt.widgets.Label(container,SWT.NONE);
		Description_label.setText("Description");
		description_text = new org.eclipse.swt.widgets.Text(container, SWT.SINGLE);
		org.eclipse.swt.layout.GridData description_layout = new org.eclipse.swt.layout.GridData();
		description_layout.grabExcessHorizontalSpace = true;
		description_layout.horizontalAlignment = GridData.FILL;
		description_text.setLayoutData(description_layout);
		
		getProperties();
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
		setProperties();
		super.okPressed();
	}
	
	private void getProperties(){
		
		System.out.println("Parent "+this.parent);
		System.out.println("Seleccion "+this.parent.getSelection()[0]);
		
		pattern01.helpers.generated.PatternInstance instance = 
				(pattern01.helpers.generated.PatternInstance)
				this.parent.getSelection()[0].getData("class_instance");
		
		System.out.println("Print nombre:"+instance.getName());
		
		this.name_text.setText(instance.getName());
		this.description_text.setText(instance.getDescription());
		this.generator_text.setText(instance.getGenerator().name());
	}
	
	private void setProperties(){
		
		System.out.println("Parent "+this.parent);
		System.out.println("Seleccion "+this.parent.getSelection()[0]);
		
		pattern01.helpers.generated.PatternInstance instance = 
				(pattern01.helpers.generated.PatternInstance)
				this.parent.getSelection()[0].getData("class_instance");
		
		//instance.setGenerator(generator_text.getText());
		instance.setName(name_text.getText());
		instance.setDescription(description_text.getText());
		this.parent.getSelection()[0].setData("class_instance", instance);
		
		XMLPropertyHelper.saveProperties(this.parent);
		
	}

	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}

}