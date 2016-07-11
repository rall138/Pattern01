
package pattern01.plugin.components.editors.generated;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;

import pattern01.helpers.generated.PatternInstance;

/**
	* Generated class via ClassGenerator.xml
	* Creation date: Fri Jul 08 17:16:02 GFT 2016
	* Creator: rlomez
	**/
public class PatternInstancePatternEditor extends org.eclipse.ui.part.EditorPart{

	public static final String ID = "pattern01.plugin.components.editors.generated.PatternInstancePatternEditor";
	private boolean dirty = false;
	private org.eclipse.swt.widgets.Group group_Default = null;
	private org.eclipse.swt.widgets.Label label_generator = null;
	private org.eclipse.swt.widgets.Combo generator = null;
	private org.eclipse.swt.widgets.Label label_name = null;
	private org.eclipse.swt.widgets.Text name = null;
	private org.eclipse.swt.widgets.Label label_description = null;
	private org.eclipse.swt.widgets.Text description = null;

	@Override
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {

		TableViewer tviewer = new TableViewer(parent);
		
		//Visualización de líneas
		tviewer.getTable().setLinesVisible(true);
		
		//Visualización de cabezal
		tviewer.getTable().setHeaderVisible(true);
		
		tviewer.setContentProvider(ArrayContentProvider.getInstance());

		TableViewerColumn tvColumn = new TableViewerColumn(tviewer, 0);
		tvColumn.getColumn().setWidth(200);
		tvColumn.getColumn().setText("Columna 1");
		tvColumn.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				PatternInstance patterninstance = (PatternInstance) element;
				return patterninstance.getName();
				
			}
		
			
			
		});
		
		
		
		List<PatternInstance> array = new ArrayList<>();
		pattern01.plugin.components.navigator.DefaultEditorInput defaultEditorInput = (pattern01.plugin.components.navigator.DefaultEditorInput)this.getEditorInput();
		org.eclipse.swt.widgets.TreeItem parentItem = defaultEditorInput.getInstanceTree().getItem(0);
		
		PatternInstance patternInstance = (pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance");
		patternInstance.setName("Instancia de prueba");
		array.add(patternInstance);
		
		tviewer.setInput(array);

		
		CellEditor[] cellEditor = new CellEditor[1];
		cellEditor[0] = new TextCellEditor(tviewer.getTable());
		tviewer.setCellEditors(cellEditor);
		
	}

	@Override
	public void init(org.eclipse.ui.IEditorSite site, org.eclipse.ui.IEditorInput input) {
		setSite(site);
		setInput(input);
	}

	@Override
	public void doSave(org.eclipse.core.runtime.IProgressMonitor monitor) {
		String xml_definition ="";
		pattern01.plugin.components.navigator.DefaultEditorInput defaultEditorInput = (pattern01.plugin.components.navigator.DefaultEditorInput)this.getEditorInput();
		org.eclipse.swt.widgets.TreeItem parentItem = defaultEditorInput.getInstanceTree().getItem(0);
		java.util.List<pattern01.helpers.generated.PatternInstance> patternInstanceCollection = getPatternInstanceCollection(parentItem, 0, new java.util.ArrayList<pattern01.helpers.generated.PatternInstance>());
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setGenerator(pattern01.helpers.generated.Generator.valueOf(this.getGeneratorValue()));
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setName(this.getNameValue());
		((pattern01.helpers.generated.PatternInstance)defaultEditorInput.getInstanceTree().getSelection()[0].getData("class_instance")).setDescription(this.getDescriptionValue());
		this.dirty = false;
	}

	@Override
	public void doSaveAs() {
		this.dirty = false;
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void setFocus() {
	}

	private java.util.List<pattern01.helpers.generated.PatternInstance> getPatternInstanceCollection(org.eclipse.swt.widgets.TreeItem parentItem, int level, java.util.List<pattern01.helpers.generated.PatternInstance> patternInstanceCollection){
		if (level != 1){ //PatternInstance Nodes are located at secondLevel {1}
			level++;
			patternInstanceCollection.addAll(getPatternInstanceCollection(parentItem.getItems()[0],level, patternInstanceCollection));
		}else{
			for (org.eclipse.swt.widgets.TreeItem item : parentItem.getItems()){
				if (item.getText().equalsIgnoreCase("patterninstance")){
					patternInstanceCollection.add((pattern01.helpers.generated.PatternInstance)item.getData("class_instance"));
				}
			}
		}
		return patternInstanceCollection;
	}

	private void addListeners() {
		
		this.generator.addListener(org.eclipse.swt.SWT.Selection, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.name.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
		
		this.description.addListener(org.eclipse.swt.SWT.KeyDown, new org.eclipse.swt.widgets.Listener() {
			@Override
			public void handleEvent(org.eclipse.swt.widgets.Event event) {
				dirty = true;
				firePropertyChange(org.eclipse.ui.IEditorPart.PROP_DIRTY);
			}
		});
	}

	private void initializeCombos(){
		java.lang.String selectedValue ="";
		for(int index = 0; index < pattern01.helpers.generated.Generator.getOptionCollection().size(); index++){
			this.generator.add(pattern01.helpers.generated.Generator.getOptionCollection().get(index));
			if (pattern01.helpers.generated.Generator.fullyQualifiedComparer("pattern01.helpers.generated.Generator.WEB",pattern01.helpers.generated.Generator.getOptionCollection().get(index))){
				selectedValue = pattern01.helpers.generated.Generator.getOptionCollection().get(index);
			}
		}
		this.generator.select(this.generator.indexOf(selectedValue));
	}

	public java.lang.String getGeneratorValue(){
		return this.generator.getText();
	}

	public java.lang.String getNameValue(){
		return this.name.getText();
	}

	public java.lang.String getDescriptionValue(){
		return this.description.getText();
	}
}