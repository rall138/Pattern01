
package pattern01.plugin.components.editors.generated;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.PropertyHelper;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;

import pattern01.helpers.generated.PatternInstance;
import pattern01.helpers.temporal_containers.Attribute;

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
		final TableViewer tviewer = new TableViewer(parent);
		tviewer.setContentProvider(ArrayContentProvider.getInstance());
		this.createColumns(tviewer);
		this.createEditors(tviewer);
		tviewer.setCellModifier(new ICellModifier() {
			
			@Override
			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object getValue(Object element, String property) {
				if (property.equalsIgnoreCase("property")){
					return ((Attribute)element).getName();
				}else if (property.equalsIgnoreCase("value")){
					return ((Attribute)element).getDefault_value();
				}
				return "";
			}
			
			@Override
			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				return true;
			}
			
			
		});

		//Visualización de líneas
		tviewer.getTable().setLinesVisible(true);
		
		//Visualización de cabezal
		tviewer.getTable().setHeaderVisible(true);
		
		this.setModelProvider(tviewer);
	}
	
	private void createColumns(TableViewer tviewer){
		final TableViewerColumn col = new TableViewerColumn(tviewer, 0);
		col.getColumn().setText("Property");
		col.getColumn().setWidth(200);
		col.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				return ((Attribute)element).getName();
			}
			
		});
		
		final TableViewerColumn col2 = new TableViewerColumn(tviewer, 0);
		col2.getColumn().setText("Value");
		col2.getColumn().setWidth(200);		
		col2.setLabelProvider(new ColumnLabelProvider(){

			@Override
			public String getText(Object element) {
				return ((Attribute)element).getDefault_value();
			}
			
		});
	}
	
	private void createEditors(TableViewer tviewer){
		CellEditor[] editors = new CellEditor[2];
		TextCellEditor ed1 = new TextCellEditor(tviewer.getTable());
		editors[0] = ed1;
		TextCellEditor ed2 = new TextCellEditor(tviewer.getTable());
		editors[1] = ed2;
		tviewer.setCellEditors(editors);
	}
	
	private void setModelProvider(TableViewer tviewer){
		List<Attribute> attrList = new ArrayList<>();
		Attribute attr = new Attribute();
		attr.setName("Name");
		attr.setDefault_value("Nombre de instancia por defecto");
		attrList.add(attr);
		attr = new Attribute();
		attr.setName("Generator");
		attr.setDefault_value("Valor de generador");
		attrList.add(attr);
		attr = new Attribute();
		attr.setName("Description");
		attr.setDefault_value("Valor de descripcion");
		attrList.add(attr);
		tviewer.setInput(attrList);
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