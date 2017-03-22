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
	* Creation date: Mon Mar 20 17:47:47 GFT 2017
	* Creator: rlomez
	**/

	public void generateDisplayableOptions(NodeType nodeType){
		Menu menu = new Menu(parent);
		parent.setMenu(menu);
		MenuItem properties_item = null;
		MenuItem add_item = null;
		Menu add_itemMenu = null;
		switch (nodeType) {
			case PATTERNINSTANCE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form.setText("Form");
				item_patternInstance_form.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form.setData("type",NodeType.PATTERNINSTANCE_FORM);
				item_patternInstance_form.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE);
				break;
			case PATTERNINSTANCE_FORM:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid.setText("PanelGrid");
				item_patternInstance_form_panelGrid.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID);
				item_patternInstance_form_panelGrid.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panel = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel.setText("Panel");
				item_patternInstance_form_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL);
				item_patternInstance_form_panel.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_property.setText("Property");
				item_patternInstance_form_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PROPERTY);
				item_patternInstance_form_property.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_text.setText("Text");
				item_patternInstance_form_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_text.setData("type",NodeType.PATTERNINSTANCE_FORM_TEXT);
				item_patternInstance_form_text.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_messages = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_messages.setText("Messages");
				item_patternInstance_form_messages.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_messages.setData("type",NodeType.PATTERNINSTANCE_FORM_MESSAGES);
				item_patternInstance_form_messages.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_dataTable.setText("DataTable");
				item_patternInstance_form_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE);
				item_patternInstance_form_dataTable.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_commandButton.setText("CommandButton");
				item_patternInstance_form_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_COMMANDBUTTON);
				item_patternInstance_form_commandButton.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid_panel = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel.setText("Panel");
				item_patternInstance_form_panelGrid_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL);
				item_patternInstance_form_panelGrid_panel.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_property.setText("Property");
				item_patternInstance_form_panelGrid_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PROPERTY);
				item_patternInstance_form_panelGrid_property.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_text.setText("Text");
				item_patternInstance_form_panelGrid_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_TEXT);
				item_patternInstance_form_panelGrid_text.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_commandButton.setText("CommandButton");
				item_patternInstance_form_panelGrid_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON);
				item_patternInstance_form_panelGrid_commandButton.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_dataTable.setText("DataTable");
				item_patternInstance_form_panelGrid_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE);
				item_patternInstance_form_panelGrid_dataTable.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid_panel_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel_property.setText("Property");
				item_patternInstance_form_panelGrid_panel_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY);
				item_patternInstance_form_panelGrid_panel_property.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_panel_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel_text.setText("Text");
				item_patternInstance_form_panelGrid_panel_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT);
				item_patternInstance_form_panelGrid_panel_text.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_panel_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel_commandButton.setText("CommandButton");
				item_patternInstance_form_panelGrid_panel_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON);
				item_patternInstance_form_panelGrid_panel_commandButton.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panelGrid_panel_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel_dataTable.setText("DataTable");
				item_patternInstance_form_panelGrid_panel_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE);
				item_patternInstance_form_panelGrid_panel_dataTable.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid_panel_dataTable_column = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel_dataTable_column.setText("Column");
				item_patternInstance_form_panelGrid_panel_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN);
				item_patternInstance_form_panelGrid_panel_dataTable_column.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid_panel_dataTable_column_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.setText("Text");
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT);
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PROPERTY:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid_dataTable_column = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_dataTable_column.setText("Column");
				item_patternInstance_form_panelGrid_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN);
				item_patternInstance_form_panelGrid_dataTable_column.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panelGrid_dataTable_column_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panelGrid_dataTable_column_text.setText("Text");
				item_patternInstance_form_panelGrid_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT);
				item_patternInstance_form_panelGrid_dataTable_column_text.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANEL:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panel_property = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel_property.setText("Property");
				item_patternInstance_form_panel_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_PROPERTY);
				item_patternInstance_form_panel_property.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panel_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel_text.setText("Text");
				item_patternInstance_form_panel_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_TEXT);
				item_patternInstance_form_panel_text.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panel_commandButton = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel_commandButton.setText("CommandButton");
				item_patternInstance_form_panel_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON);
				item_patternInstance_form_panel_commandButton.addSelectionListener(listenerFactory());

				MenuItem item_patternInstance_form_panel_dataTable = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel_dataTable.setText("DataTable");
				item_patternInstance_form_panel_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE);
				item_patternInstance_form_panel_dataTable.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL);
				break;
			case PATTERNINSTANCE_FORM_PANEL_PROPERTY:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_PANEL_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON);
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panel_dataTable_column = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel_dataTable_column.setText("Column");
				item_patternInstance_form_panel_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN);
				item_patternInstance_form_panel_dataTable_column.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_panel_dataTable_column_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_panel_dataTable_column_text.setText("Text");
				item_patternInstance_form_panel_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT);
				item_patternInstance_form_panel_dataTable_column_text.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PROPERTY:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_TEXT);
				break;
			case PATTERNINSTANCE_FORM_MESSAGES:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_MESSAGES);
				break;
			case PATTERNINSTANCE_FORM_DATATABLE:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_dataTable_column = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_dataTable_column.setText("Column");
				item_patternInstance_form_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE_COLUMN);
				item_patternInstance_form_dataTable_column.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_DATATABLE_COLUMN:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_patternInstance_form_dataTable_column_text = new MenuItem(add_itemMenu, SWT.PUSH);
				item_patternInstance_form_dataTable_column_text.setText("Text");
				item_patternInstance_form_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT);
				item_patternInstance_form_dataTable_column_text.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_COMMANDBUTTON:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE_FORM_COMMANDBUTTON);
				break;
			default:
				break;
		}
	}

	private void addElement(MenuItem selectedItem){
		switch(((NodeType)selectedItem.getData("type"))){
			case PATTERNINSTANCE:
				TreeItem item_patternInstance = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance.setText(selectedItem.getText());
				item_patternInstance.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance.setData("type",NodeType.PATTERNINSTANCE);
				break;
			case PATTERNINSTANCE_FORM:
				TreeItem item_patternInstance_form = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form.setText(selectedItem.getText());
				item_patternInstance_form.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form.setData("type",NodeType.PATTERNINSTANCE_FORM);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID:
				TreeItem item_patternInstance_form_panelGrid = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL:
				TreeItem item_patternInstance_form_panelGrid_panel = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY:
				TreeItem item_patternInstance_form_panelGrid_panel_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel_property.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT:
				TreeItem item_patternInstance_form_panelGrid_panel_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel_text.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON:
				TreeItem item_patternInstance_form_panelGrid_panel_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel_commandButton.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE:
				TreeItem item_patternInstance_form_panelGrid_panel_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel_dataTable.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN:
				TreeItem item_patternInstance_form_panelGrid_panel_dataTable_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel_dataTable_column.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT:
				TreeItem item_patternInstance_form_panelGrid_panel_dataTable_column_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_panel_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PROPERTY:
				TreeItem item_patternInstance_form_panelGrid_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_property.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_TEXT:
				TreeItem item_patternInstance_form_panelGrid_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_text.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON:
				TreeItem item_patternInstance_form_panelGrid_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_commandButton.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE:
				TreeItem item_patternInstance_form_panelGrid_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_dataTable.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN:
				TreeItem item_patternInstance_form_panelGrid_dataTable_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_dataTable_column.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT:
				TreeItem item_patternInstance_form_panelGrid_dataTable_column_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panelGrid_dataTable_column_text.setText(selectedItem.getText());
				item_patternInstance_form_panelGrid_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panelGrid_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANEL:
				TreeItem item_patternInstance_form_panel = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel.setText(selectedItem.getText());
				item_patternInstance_form_panel.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL);
				break;
			case PATTERNINSTANCE_FORM_PANEL_PROPERTY:
				TreeItem item_patternInstance_form_panel_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel_property.setText(selectedItem.getText());
				item_patternInstance_form_panel_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_PANEL_TEXT:
				TreeItem item_patternInstance_form_panel_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel_text.setText(selectedItem.getText());
				item_patternInstance_form_panel_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON:
				TreeItem item_patternInstance_form_panel_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel_commandButton.setText(selectedItem.getText());
				item_patternInstance_form_panel_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON);
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE:
				TreeItem item_patternInstance_form_panel_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel_dataTable.setText(selectedItem.getText());
				item_patternInstance_form_panel_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN:
				TreeItem item_patternInstance_form_panel_dataTable_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel_dataTable_column.setText(selectedItem.getText());
				item_patternInstance_form_panel_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT:
				TreeItem item_patternInstance_form_panel_dataTable_column_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_panel_dataTable_column_text.setText(selectedItem.getText());
				item_patternInstance_form_panel_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_panel_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_PROPERTY:
				TreeItem item_patternInstance_form_property = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_property.setText(selectedItem.getText());
				item_patternInstance_form_property.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_property.setData("type",NodeType.PATTERNINSTANCE_FORM_PROPERTY);
				break;
			case PATTERNINSTANCE_FORM_TEXT:
				TreeItem item_patternInstance_form_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_text.setText(selectedItem.getText());
				item_patternInstance_form_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_text.setData("type",NodeType.PATTERNINSTANCE_FORM_TEXT);
				break;
			case PATTERNINSTANCE_FORM_MESSAGES:
				TreeItem item_patternInstance_form_messages = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_messages.setText(selectedItem.getText());
				item_patternInstance_form_messages.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_messages.setData("type",NodeType.PATTERNINSTANCE_FORM_MESSAGES);
				break;
			case PATTERNINSTANCE_FORM_DATATABLE:
				TreeItem item_patternInstance_form_dataTable = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_dataTable.setText(selectedItem.getText());
				item_patternInstance_form_dataTable.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_dataTable.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE);
				break;
			case PATTERNINSTANCE_FORM_DATATABLE_COLUMN:
				TreeItem item_patternInstance_form_dataTable_column = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_dataTable_column.setText(selectedItem.getText());
				item_patternInstance_form_dataTable_column.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_dataTable_column.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE_COLUMN);
				break;
			case PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT:
				TreeItem item_patternInstance_form_dataTable_column_text = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_dataTable_column_text.setText(selectedItem.getText());
				item_patternInstance_form_dataTable_column_text.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_dataTable_column_text.setData("type",NodeType.PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT);
				break;
			case PATTERNINSTANCE_FORM_COMMANDBUTTON:
				TreeItem item_patternInstance_form_commandButton = new TreeItem(this.parent.getSelection()[0], 0);
				item_patternInstance_form_commandButton.setText(selectedItem.getText());
				item_patternInstance_form_commandButton.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_patternInstance_form_commandButton.setData("type",NodeType.PATTERNINSTANCE_FORM_COMMANDBUTTON);
				break;
			default:
				break;
		}
	}

	private void propertiesElement(MenuItem selectedItem){
		System.err.println("Tipo de elemento seleccionado: "+selectedItem.getData("type"));
		switch(((NodeType)selectedItem.getData("type"))){
			case PATTERNINSTANCE:
				JFaceDialogPatternInstance jfacedialogpatternInstance = new JFaceDialogPatternInstance(this.parent.getShell());
				jfacedialogpatternInstance.setParent(this.parent);
				jfacedialogpatternInstance.create();
				jfacedialogpatternInstance.open();
				break;
			case PATTERNINSTANCE_FORM:
				JFaceDialogForm jfacedialogpatternInstance_form = new JFaceDialogForm(this.parent.getShell());
				jfacedialogpatternInstance_form.setParent(this.parent);
				jfacedialogpatternInstance_form.create();
				jfacedialogpatternInstance_form.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID:
				JFaceDialogPanelGrid jfacedialogpatternInstance_form_panelGrid = new JFaceDialogPanelGrid(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid.create();
				jfacedialogpatternInstance_form_panelGrid.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL:
				JFaceDialogPanel jfacedialogpatternInstance_form_panelGrid_panel = new JFaceDialogPanel(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel.create();
				jfacedialogpatternInstance_form_panelGrid_panel.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY:
				JFaceDialogProperty jfacedialogpatternInstance_form_panelGrid_panel_property = new JFaceDialogProperty(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel_property.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel_property.create();
				jfacedialogpatternInstance_form_panelGrid_panel_property.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_panelGrid_panel_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel_text.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel_text.create();
				jfacedialogpatternInstance_form_panelGrid_panel_text.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON:
				JFaceDialogCommandButton jfacedialogpatternInstance_form_panelGrid_panel_commandButton = new JFaceDialogCommandButton(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel_commandButton.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel_commandButton.create();
				jfacedialogpatternInstance_form_panelGrid_panel_commandButton.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE:
				JFaceDialogDataTable jfacedialogpatternInstance_form_panelGrid_panel_dataTable = new JFaceDialogDataTable(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable.create();
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN:
				JFaceDialogColumn jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column = new JFaceDialogColumn(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column.create();
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column_text.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column_text.create();
				jfacedialogpatternInstance_form_panelGrid_panel_dataTable_column_text.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_PROPERTY:
				JFaceDialogProperty jfacedialogpatternInstance_form_panelGrid_property = new JFaceDialogProperty(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_property.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_property.create();
				jfacedialogpatternInstance_form_panelGrid_property.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_panelGrid_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_text.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_text.create();
				jfacedialogpatternInstance_form_panelGrid_text.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON:
				JFaceDialogCommandButton jfacedialogpatternInstance_form_panelGrid_commandButton = new JFaceDialogCommandButton(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_commandButton.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_commandButton.create();
				jfacedialogpatternInstance_form_panelGrid_commandButton.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE:
				JFaceDialogDataTable jfacedialogpatternInstance_form_panelGrid_dataTable = new JFaceDialogDataTable(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_dataTable.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_dataTable.create();
				jfacedialogpatternInstance_form_panelGrid_dataTable.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN:
				JFaceDialogColumn jfacedialogpatternInstance_form_panelGrid_dataTable_column = new JFaceDialogColumn(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_dataTable_column.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_dataTable_column.create();
				jfacedialogpatternInstance_form_panelGrid_dataTable_column.open();
				break;
			case PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_panelGrid_dataTable_column_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_panelGrid_dataTable_column_text.setParent(this.parent);
				jfacedialogpatternInstance_form_panelGrid_dataTable_column_text.create();
				jfacedialogpatternInstance_form_panelGrid_dataTable_column_text.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL:
				JFaceDialogPanel jfacedialogpatternInstance_form_panel = new JFaceDialogPanel(this.parent.getShell());
				jfacedialogpatternInstance_form_panel.setParent(this.parent);
				jfacedialogpatternInstance_form_panel.create();
				jfacedialogpatternInstance_form_panel.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL_PROPERTY:
				JFaceDialogProperty jfacedialogpatternInstance_form_panel_property = new JFaceDialogProperty(this.parent.getShell());
				jfacedialogpatternInstance_form_panel_property.setParent(this.parent);
				jfacedialogpatternInstance_form_panel_property.create();
				jfacedialogpatternInstance_form_panel_property.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_panel_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_panel_text.setParent(this.parent);
				jfacedialogpatternInstance_form_panel_text.create();
				jfacedialogpatternInstance_form_panel_text.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON:
				JFaceDialogCommandButton jfacedialogpatternInstance_form_panel_commandButton = new JFaceDialogCommandButton(this.parent.getShell());
				jfacedialogpatternInstance_form_panel_commandButton.setParent(this.parent);
				jfacedialogpatternInstance_form_panel_commandButton.create();
				jfacedialogpatternInstance_form_panel_commandButton.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE:
				JFaceDialogDataTable jfacedialogpatternInstance_form_panel_dataTable = new JFaceDialogDataTable(this.parent.getShell());
				jfacedialogpatternInstance_form_panel_dataTable.setParent(this.parent);
				jfacedialogpatternInstance_form_panel_dataTable.create();
				jfacedialogpatternInstance_form_panel_dataTable.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN:
				JFaceDialogColumn jfacedialogpatternInstance_form_panel_dataTable_column = new JFaceDialogColumn(this.parent.getShell());
				jfacedialogpatternInstance_form_panel_dataTable_column.setParent(this.parent);
				jfacedialogpatternInstance_form_panel_dataTable_column.create();
				jfacedialogpatternInstance_form_panel_dataTable_column.open();
				break;
			case PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_panel_dataTable_column_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_panel_dataTable_column_text.setParent(this.parent);
				jfacedialogpatternInstance_form_panel_dataTable_column_text.create();
				jfacedialogpatternInstance_form_panel_dataTable_column_text.open();
				break;
			case PATTERNINSTANCE_FORM_PROPERTY:
				JFaceDialogProperty jfacedialogpatternInstance_form_property = new JFaceDialogProperty(this.parent.getShell());
				jfacedialogpatternInstance_form_property.setParent(this.parent);
				jfacedialogpatternInstance_form_property.create();
				jfacedialogpatternInstance_form_property.open();
				break;
			case PATTERNINSTANCE_FORM_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_text.setParent(this.parent);
				jfacedialogpatternInstance_form_text.create();
				jfacedialogpatternInstance_form_text.open();
				break;
			case PATTERNINSTANCE_FORM_MESSAGES:
				JFaceDialogMessages jfacedialogpatternInstance_form_messages = new JFaceDialogMessages(this.parent.getShell());
				jfacedialogpatternInstance_form_messages.setParent(this.parent);
				jfacedialogpatternInstance_form_messages.create();
				jfacedialogpatternInstance_form_messages.open();
				break;
			case PATTERNINSTANCE_FORM_DATATABLE:
				JFaceDialogDataTable jfacedialogpatternInstance_form_dataTable = new JFaceDialogDataTable(this.parent.getShell());
				jfacedialogpatternInstance_form_dataTable.setParent(this.parent);
				jfacedialogpatternInstance_form_dataTable.create();
				jfacedialogpatternInstance_form_dataTable.open();
				break;
			case PATTERNINSTANCE_FORM_DATATABLE_COLUMN:
				JFaceDialogColumn jfacedialogpatternInstance_form_dataTable_column = new JFaceDialogColumn(this.parent.getShell());
				jfacedialogpatternInstance_form_dataTable_column.setParent(this.parent);
				jfacedialogpatternInstance_form_dataTable_column.create();
				jfacedialogpatternInstance_form_dataTable_column.open();
				break;
			case PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT:
				JFaceDialogText jfacedialogpatternInstance_form_dataTable_column_text = new JFaceDialogText(this.parent.getShell());
				jfacedialogpatternInstance_form_dataTable_column_text.setParent(this.parent);
				jfacedialogpatternInstance_form_dataTable_column_text.create();
				jfacedialogpatternInstance_form_dataTable_column_text.open();
				break;
			case PATTERNINSTANCE_FORM_COMMANDBUTTON:
				JFaceDialogCommandButton jfacedialogpatternInstance_form_commandButton = new JFaceDialogCommandButton(this.parent.getShell());
				jfacedialogpatternInstance_form_commandButton.setParent(this.parent);
				jfacedialogpatternInstance_form_commandButton.create();
				jfacedialogpatternInstance_form_commandButton.open();
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
