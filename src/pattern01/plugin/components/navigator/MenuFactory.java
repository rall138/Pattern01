package pattern01.plugin.components.navigator;

import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import pattern01.helpers.ImageHelper;
import pattern01.helpers.generated.*;
import pattern01.helpers.instancegen.PatternInstanceParser;
import pattern01.plugin.components.editors.generated.*;

@SuppressWarnings("unused")
public class MenuFactory {
	
	private Tree parent = null;

	public MenuFactory(Tree tree_parent){
		this.parent = tree_parent;
	}
	
	public void generateListenerAttachement(){
		this.attachSelectionListener();
		this.attachDeleteListener();
	}
	
	private void attachSelectionListener(){
		parent.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				TreeItem aux_item = (TreeItem)event.item;
				if (aux_item.getData("type") != null){
					generateDisplayableOptions((NodeType.valueOf(aux_item.getData("type").toString().toUpperCase())));
				}
			}
		});
	}
	
	
	
	
	
	
	
	/* [Begin] Auto-generated code for menu factory do not remove */
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Mar 30 17:08:03 GFT 2017
	* Creator: rlomez
	**/

	public void generateDisplayableOptions(NodeType nodeType){
		Menu menu = new Menu(parent);
		parent.setMenu(menu);
		MenuItem properties_item = null;
		MenuItem add_item = null;
		Menu add_itemMenu = null;
		switch (nodeType) {
			case CLASS:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance.setText("PatternInstance");
				item_patternInstance.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance.setData("type",NodeType.PATTERNINSTANCE);
				item_patternInstance.setData("reference",java.util.UUID.randomUUID());
				item_patternInstance.addSelectionListener(listenerFactory());
				break;
			case PATTERNINSTANCE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form.setText("Form");
				item_patternInstance_form.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form.setData("type",NodeType.FORM);
				item_patternInstance_form.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE);
				break;
			case FORM:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_form_panelGrid = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_panelGrid.setText("PanelGrid");
				item_form_panelGrid.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_panelGrid.setData("type",NodeType.PANELGRID);
				item_form_panelGrid.addSelectionListener(listenerFactory());

				MenuItem item_form_panel = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_panel.setText("Panel");
				item_form_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_panel.setData("type",NodeType.PANEL);
				item_form_panel.addSelectionListener(listenerFactory());

				MenuItem item_form_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_property.setText("Property");
				item_form_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_property.setData("type",NodeType.PROPERTY);
				item_form_property.addSelectionListener(listenerFactory());

				MenuItem item_form_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_text.setText("Text");
				item_form_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_text.setData("type",NodeType.TEXT);
				item_form_text.addSelectionListener(listenerFactory());

				MenuItem item_form_messages = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_messages.setText("Messages");
				item_form_messages.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_messages.setData("type",NodeType.MESSAGES);
				item_form_messages.addSelectionListener(listenerFactory());

				MenuItem item_form_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_dataTable.setText("DataTable");
				item_form_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_dataTable.setData("type",NodeType.DATATABLE);
				item_form_dataTable.addSelectionListener(listenerFactory());

				MenuItem item_form_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_form_commandButton.setText("CommandButton");
				item_form_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form_commandButton.setData("type",NodeType.COMMANDBUTTON);
				item_form_commandButton.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.FORM);
				break;
			case PANELGRID:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_panelGrid_panel = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panelGrid_panel.setText("Panel");
				item_panelGrid_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panelGrid_panel.setData("type",NodeType.PANEL);
				item_panelGrid_panel.addSelectionListener(listenerFactory());

				MenuItem item_panelGrid_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panelGrid_property.setText("Property");
				item_panelGrid_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panelGrid_property.setData("type",NodeType.PROPERTY);
				item_panelGrid_property.addSelectionListener(listenerFactory());

				MenuItem item_panelGrid_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panelGrid_text.setText("Text");
				item_panelGrid_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panelGrid_text.setData("type",NodeType.TEXT);
				item_panelGrid_text.addSelectionListener(listenerFactory());

				MenuItem item_panelGrid_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panelGrid_commandButton.setText("CommandButton");
				item_panelGrid_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panelGrid_commandButton.setData("type",NodeType.COMMANDBUTTON);
				item_panelGrid_commandButton.addSelectionListener(listenerFactory());

				MenuItem item_panelGrid_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panelGrid_dataTable.setText("DataTable");
				item_panelGrid_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panelGrid_dataTable.setData("type",NodeType.DATATABLE);
				item_panelGrid_dataTable.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PANELGRID);
				break;
			case PANEL:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_panel_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panel_property.setText("Property");
				item_panel_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panel_property.setData("type",NodeType.PROPERTY);
				item_panel_property.addSelectionListener(listenerFactory());

				MenuItem item_panel_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panel_text.setText("Text");
				item_panel_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panel_text.setData("type",NodeType.TEXT);
				item_panel_text.addSelectionListener(listenerFactory());

				MenuItem item_panel_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panel_commandButton.setText("CommandButton");
				item_panel_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panel_commandButton.setData("type",NodeType.COMMANDBUTTON);
				item_panel_commandButton.addSelectionListener(listenerFactory());

				MenuItem item_panel_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_panel_dataTable.setText("DataTable");
				item_panel_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panel_dataTable.setData("type",NodeType.DATATABLE);
				item_panel_dataTable.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PANEL);
				break;
			case PROPERTY:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PROPERTY);
				break;
			case TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.TEXT);
				break;
			case COMMANDBUTTON:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.COMMANDBUTTON);
				break;
			case DATATABLE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_dataTable_column = new MenuItem(add_itemMenu, SWT.PUSH);
				item_dataTable_column.setText("Column");
				item_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_dataTable_column.setData("type",NodeType.COLUMN);
				item_dataTable_column.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.DATATABLE);
				break;
			case COLUMN:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_column_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_column_text.setText("Text");
				item_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_column_text.setData("type",NodeType.TEXT);
				item_column_text.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.COLUMN);
				break;
			case MESSAGES:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.MESSAGES);
				break;
			default:
				break;
		}
	}

	private void addElement(MenuItem selectedItem){
		switch(((NodeType)selectedItem.getData("type"))){
			case UNDEFINED:
				break;
			case CLASS:
				break;
			case PATTERNINSTANCE:
				TreeItem item_patternInstance = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance.setText(selectedItem.getText());
				item_patternInstance.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance.setData("type",NodeType.PATTERNINSTANCE);
				PatternInstance patternInstance = new PatternInstance();

				item_patternInstance.setData("class_instance", patternInstance);
				item_patternInstance.setData("reference",java.util.UUID.randomUUID());
				item_patternInstance.setData("parent_reference",patternInstance);

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement patternInstance_parentInstance = (IPatternElement)item_patternInstance.getParentItem().getData("class_instance");
				patternInstance_parentInstance.setGenericElement(patternInstance);
				break;
			case FORM:
				TreeItem item_form = new TreeItem(this.parent.getSelection()[0], 0);
				item_form.setText(selectedItem.getText());
				item_form.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_form.setData("type",NodeType.FORM);
				Form form = new Form();

				item_form.setData("class_instance", form);
				item_form.setData("parent_reference", item_form.getParentItem().getData("parent_reference"));
				item_form.setData("reference", item_form.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement form_parentInstance = (IPatternElement)item_form.getParentItem().getData("class_instance");
				form_parentInstance.setGenericElement(form);
				break;
			case PANELGRID:
				TreeItem item_panelGrid = new TreeItem(this.parent.getSelection()[0], 0);
				item_panelGrid.setText(selectedItem.getText());
				item_panelGrid.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panelGrid.setData("type",NodeType.PANELGRID);
				PanelGrid panelGrid = new PanelGrid();

				item_panelGrid.setData("class_instance", panelGrid);
				item_panelGrid.setData("parent_reference", item_panelGrid.getParentItem().getData("parent_reference"));
				item_panelGrid.setData("reference", item_panelGrid.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement panelGrid_parentInstance = (IPatternElement)item_panelGrid.getParentItem().getData("class_instance");
				panelGrid_parentInstance.setGenericElement(panelGrid);
				break;
			case PANEL:
				TreeItem item_panel = new TreeItem(this.parent.getSelection()[0], 0);
				item_panel.setText(selectedItem.getText());
				item_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panel.setData("type",NodeType.PANEL);
				Panel panel = new Panel();

				item_panel.setData("class_instance", panel);
				item_panel.setData("parent_reference", item_panel.getParentItem().getData("parent_reference"));
				item_panel.setData("reference", item_panel.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement panel_parentInstance = (IPatternElement)item_panel.getParentItem().getData("class_instance");
				panel_parentInstance.setGenericElement(panel);
				break;
			case PROPERTY:
				TreeItem item_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_property.setText(selectedItem.getText());
				item_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_property.setData("type",NodeType.PROPERTY);
				Property property = new Property();

				item_property.setData("class_instance", property);
				item_property.setData("parent_reference", item_property.getParentItem().getData("parent_reference"));
				item_property.setData("reference", item_property.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement property_parentInstance = (IPatternElement)item_property.getParentItem().getData("class_instance");
				property_parentInstance.setGenericElement(property);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case COMMANDBUTTON:
				TreeItem item_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_commandButton.setText(selectedItem.getText());
				item_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_commandButton.setData("type",NodeType.COMMANDBUTTON);
				CommandButton commandButton = new CommandButton();

				item_commandButton.setData("class_instance", commandButton);
				item_commandButton.setData("parent_reference", item_commandButton.getParentItem().getData("parent_reference"));
				item_commandButton.setData("reference", item_commandButton.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement commandButton_parentInstance = (IPatternElement)item_commandButton.getParentItem().getData("class_instance");
				commandButton_parentInstance.setGenericElement(commandButton);
				break;
			case DATATABLE:
				TreeItem item_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_dataTable.setText(selectedItem.getText());
				item_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_dataTable.setData("type",NodeType.DATATABLE);
				DataTable dataTable = new DataTable();

				item_dataTable.setData("class_instance", dataTable);
				item_dataTable.setData("parent_reference", item_dataTable.getParentItem().getData("parent_reference"));
				item_dataTable.setData("reference", item_dataTable.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement dataTable_parentInstance = (IPatternElement)item_dataTable.getParentItem().getData("class_instance");
				dataTable_parentInstance.setGenericElement(dataTable);
				break;
			case COLUMN:
				TreeItem item_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_column.setText(selectedItem.getText());
				item_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_column.setData("type",NodeType.COLUMN);
				Column column = new Column();

				item_column.setData("class_instance", column);
				item_column.setData("parent_reference", item_column.getParentItem().getData("parent_reference"));
				item_column.setData("reference", item_column.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement column_parentInstance = (IPatternElement)item_column.getParentItem().getData("class_instance");
				column_parentInstance.setGenericElement(column);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case PROPERTY:
				TreeItem item_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_property.setText(selectedItem.getText());
				item_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_property.setData("type",NodeType.PROPERTY);
				Property property = new Property();

				item_property.setData("class_instance", property);
				item_property.setData("parent_reference", item_property.getParentItem().getData("parent_reference"));
				item_property.setData("reference", item_property.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement property_parentInstance = (IPatternElement)item_property.getParentItem().getData("class_instance");
				property_parentInstance.setGenericElement(property);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case COMMANDBUTTON:
				TreeItem item_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_commandButton.setText(selectedItem.getText());
				item_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_commandButton.setData("type",NodeType.COMMANDBUTTON);
				CommandButton commandButton = new CommandButton();

				item_commandButton.setData("class_instance", commandButton);
				item_commandButton.setData("parent_reference", item_commandButton.getParentItem().getData("parent_reference"));
				item_commandButton.setData("reference", item_commandButton.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement commandButton_parentInstance = (IPatternElement)item_commandButton.getParentItem().getData("class_instance");
				commandButton_parentInstance.setGenericElement(commandButton);
				break;
			case DATATABLE:
				TreeItem item_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_dataTable.setText(selectedItem.getText());
				item_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_dataTable.setData("type",NodeType.DATATABLE);
				DataTable dataTable = new DataTable();

				item_dataTable.setData("class_instance", dataTable);
				item_dataTable.setData("parent_reference", item_dataTable.getParentItem().getData("parent_reference"));
				item_dataTable.setData("reference", item_dataTable.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement dataTable_parentInstance = (IPatternElement)item_dataTable.getParentItem().getData("class_instance");
				dataTable_parentInstance.setGenericElement(dataTable);
				break;
			case COLUMN:
				TreeItem item_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_column.setText(selectedItem.getText());
				item_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_column.setData("type",NodeType.COLUMN);
				Column column = new Column();

				item_column.setData("class_instance", column);
				item_column.setData("parent_reference", item_column.getParentItem().getData("parent_reference"));
				item_column.setData("reference", item_column.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement column_parentInstance = (IPatternElement)item_column.getParentItem().getData("class_instance");
				column_parentInstance.setGenericElement(column);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case PANEL:
				TreeItem item_panel = new TreeItem(this.parent.getSelection()[0], 0);
				item_panel.setText(selectedItem.getText());
				item_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_panel.setData("type",NodeType.PANEL);
				Panel panel = new Panel();

				item_panel.setData("class_instance", panel);
				item_panel.setData("parent_reference", item_panel.getParentItem().getData("parent_reference"));
				item_panel.setData("reference", item_panel.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement panel_parentInstance = (IPatternElement)item_panel.getParentItem().getData("class_instance");
				panel_parentInstance.setGenericElement(panel);
				break;
			case PROPERTY:
				TreeItem item_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_property.setText(selectedItem.getText());
				item_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_property.setData("type",NodeType.PROPERTY);
				Property property = new Property();

				item_property.setData("class_instance", property);
				item_property.setData("parent_reference", item_property.getParentItem().getData("parent_reference"));
				item_property.setData("reference", item_property.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement property_parentInstance = (IPatternElement)item_property.getParentItem().getData("class_instance");
				property_parentInstance.setGenericElement(property);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case COMMANDBUTTON:
				TreeItem item_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_commandButton.setText(selectedItem.getText());
				item_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_commandButton.setData("type",NodeType.COMMANDBUTTON);
				CommandButton commandButton = new CommandButton();

				item_commandButton.setData("class_instance", commandButton);
				item_commandButton.setData("parent_reference", item_commandButton.getParentItem().getData("parent_reference"));
				item_commandButton.setData("reference", item_commandButton.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement commandButton_parentInstance = (IPatternElement)item_commandButton.getParentItem().getData("class_instance");
				commandButton_parentInstance.setGenericElement(commandButton);
				break;
			case DATATABLE:
				TreeItem item_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_dataTable.setText(selectedItem.getText());
				item_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_dataTable.setData("type",NodeType.DATATABLE);
				DataTable dataTable = new DataTable();

				item_dataTable.setData("class_instance", dataTable);
				item_dataTable.setData("parent_reference", item_dataTable.getParentItem().getData("parent_reference"));
				item_dataTable.setData("reference", item_dataTable.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement dataTable_parentInstance = (IPatternElement)item_dataTable.getParentItem().getData("class_instance");
				dataTable_parentInstance.setGenericElement(dataTable);
				break;
			case COLUMN:
				TreeItem item_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_column.setText(selectedItem.getText());
				item_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_column.setData("type",NodeType.COLUMN);
				Column column = new Column();

				item_column.setData("class_instance", column);
				item_column.setData("parent_reference", item_column.getParentItem().getData("parent_reference"));
				item_column.setData("reference", item_column.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement column_parentInstance = (IPatternElement)item_column.getParentItem().getData("class_instance");
				column_parentInstance.setGenericElement(column);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case PROPERTY:
				TreeItem item_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_property.setText(selectedItem.getText());
				item_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_property.setData("type",NodeType.PROPERTY);
				Property property = new Property();

				item_property.setData("class_instance", property);
				item_property.setData("parent_reference", item_property.getParentItem().getData("parent_reference"));
				item_property.setData("reference", item_property.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement property_parentInstance = (IPatternElement)item_property.getParentItem().getData("class_instance");
				property_parentInstance.setGenericElement(property);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case MESSAGES:
				TreeItem item_messages = new TreeItem(this.parent.getSelection()[0], 0);
				item_messages.setText(selectedItem.getText());
				item_messages.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_messages.setData("type",NodeType.MESSAGES);
				Messages messages = new Messages();

				item_messages.setData("class_instance", messages);
				item_messages.setData("parent_reference", item_messages.getParentItem().getData("parent_reference"));
				item_messages.setData("reference", item_messages.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement messages_parentInstance = (IPatternElement)item_messages.getParentItem().getData("class_instance");
				messages_parentInstance.setGenericElement(messages);
				break;
			case DATATABLE:
				TreeItem item_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_dataTable.setText(selectedItem.getText());
				item_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_dataTable.setData("type",NodeType.DATATABLE);
				DataTable dataTable = new DataTable();

				item_dataTable.setData("class_instance", dataTable);
				item_dataTable.setData("parent_reference", item_dataTable.getParentItem().getData("parent_reference"));
				item_dataTable.setData("reference", item_dataTable.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement dataTable_parentInstance = (IPatternElement)item_dataTable.getParentItem().getData("class_instance");
				dataTable_parentInstance.setGenericElement(dataTable);
				break;
			case COLUMN:
				TreeItem item_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_column.setText(selectedItem.getText());
				item_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_column.setData("type",NodeType.COLUMN);
				Column column = new Column();

				item_column.setData("class_instance", column);
				item_column.setData("parent_reference", item_column.getParentItem().getData("parent_reference"));
				item_column.setData("reference", item_column.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement column_parentInstance = (IPatternElement)item_column.getParentItem().getData("class_instance");
				column_parentInstance.setGenericElement(column);
				break;
			case TEXT:
				TreeItem item_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_text.setText(selectedItem.getText());
				item_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_text.setData("type",NodeType.TEXT);
				Text text = new Text();

				item_text.setData("class_instance", text);
				item_text.setData("parent_reference", item_text.getParentItem().getData("parent_reference"));
				item_text.setData("reference", item_text.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement text_parentInstance = (IPatternElement)item_text.getParentItem().getData("class_instance");
				text_parentInstance.setGenericElement(text);
				break;
			case COMMANDBUTTON:
				TreeItem item_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_commandButton.setText(selectedItem.getText());
				item_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_commandButton.setData("type",NodeType.COMMANDBUTTON);
				CommandButton commandButton = new CommandButton();

				item_commandButton.setData("class_instance", commandButton);
				item_commandButton.setData("parent_reference", item_commandButton.getParentItem().getData("parent_reference"));
				item_commandButton.setData("reference", item_commandButton.getParentItem().getData("reference"));

				/*
				Vinculando las instancias nuevas con su respectivo padre
				*/
				IPatternElement commandButton_parentInstance = (IPatternElement)item_commandButton.getParentItem().getData("class_instance");
				commandButton_parentInstance.setGenericElement(commandButton);
				break;
			default:
				break;
		}
	}

	private void propertiesElement(MenuItem selectedItem){
		switch(((NodeType)selectedItem.getData("type"))){
			case PATTERNINSTANCE:
				JFaceDialogPatternInstance jfacedialogpatternInstance = new JFaceDialogPatternInstance(this.parent.getShell());
				jfacedialogpatternInstance.setParent(this.parent);
				jfacedialogpatternInstance.create();
				jfacedialogpatternInstance.open();
				break;
			case FORM:
				JFaceDialogForm jfacedialogform = new JFaceDialogForm(this.parent.getShell());
				jfacedialogform.setParent(this.parent);
				jfacedialogform.create();
				jfacedialogform.open();
				break;
			case PANELGRID:
				JFaceDialogPanelGrid jfacedialogpanelGrid = new JFaceDialogPanelGrid(this.parent.getShell());
				jfacedialogpanelGrid.setParent(this.parent);
				jfacedialogpanelGrid.create();
				jfacedialogpanelGrid.open();
				break;
			case PANEL:
				JFaceDialogPanel jfacedialogpanel = new JFaceDialogPanel(this.parent.getShell());
				jfacedialogpanel.setParent(this.parent);
				jfacedialogpanel.create();
				jfacedialogpanel.open();
				break;
			case PROPERTY:
				JFaceDialogProperty jfacedialogproperty = new JFaceDialogProperty(this.parent.getShell());
				jfacedialogproperty.setParent(this.parent);
				jfacedialogproperty.create();
				jfacedialogproperty.open();
				break;
			case TEXT:
				JFaceDialogText jfacedialogtext = new JFaceDialogText(this.parent.getShell());
				jfacedialogtext.setParent(this.parent);
				jfacedialogtext.create();
				jfacedialogtext.open();
				break;
			case COMMANDBUTTON:
				JFaceDialogCommandButton jfacedialogcommandButton = new JFaceDialogCommandButton(this.parent.getShell());
				jfacedialogcommandButton.setParent(this.parent);
				jfacedialogcommandButton.create();
				jfacedialogcommandButton.open();
				break;
			case DATATABLE:
				JFaceDialogDataTable jfacedialogdataTable = new JFaceDialogDataTable(this.parent.getShell());
				jfacedialogdataTable.setParent(this.parent);
				jfacedialogdataTable.create();
				jfacedialogdataTable.open();
				break;
			case COLUMN:
				JFaceDialogColumn jfacedialogcolumn = new JFaceDialogColumn(this.parent.getShell());
				jfacedialogcolumn.setParent(this.parent);
				jfacedialogcolumn.create();
				jfacedialogcolumn.open();
				break;
			case MESSAGES:
				JFaceDialogMessages jfacedialogmessages = new JFaceDialogMessages(this.parent.getShell());
				jfacedialogmessages.setParent(this.parent);
				jfacedialogmessages.create();
				jfacedialogmessages.open();
				break;
			default:
				break;
		}
	}

	/* [End] Auto-generated code for menu factory do not remove */




























	private SelectionListener listenerFactory(){
		SelectionListener listener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				addElement((MenuItem)e.getSource());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return listener;
	}
	
	private SelectionListener propertiesListener(){
		SelectionListener listener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				propertiesElement((MenuItem)e.getSource());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return listener;
	}

	
	private void attachDeleteListener(){
		this.parent.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.DEL){
					parent.getSelection()[0].dispose();
				}
			}
		});
		
	}
	
	private void applyDefaultPatternListener(MenuItem menu_item){
		menu_item.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				PatternInstanceParser parser = new PatternInstanceParser(parent.getSelection()[0]);
				parent.getSelection()[0].removeAll();
				parser.generateTreeFromDefaultDefinition();
				parent.getSelection()[0] = parser.getInstance();
			}
		});
	}
}
