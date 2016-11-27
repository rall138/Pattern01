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
	* Creation date: Thu Nov 24 22:20:21 UYST 2016
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

				MenuItem item_RelatedFiles = new MenuItem(add_itemMenu, SWT.PUSH);
				item_RelatedFiles.setText("RelatedFiles");
				item_RelatedFiles.setImage(ImageHelper.getImage("null"));
				item_RelatedFiles.setData("type",NodeType.RELATEDFILES);
				item_RelatedFiles.addSelectionListener(listenerFactory());

				MenuItem item_Faces = new MenuItem(add_itemMenu, SWT.PUSH);
				item_Faces.setText("Faces");
				item_Faces.setImage(ImageHelper.getImage("null"));
				item_Faces.setData("type",NodeType.FACES);
				item_Faces.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.PATTERNINSTANCE);
				break;
			case RELATEDFILES:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_RelatedFile = new MenuItem(add_itemMenu, SWT.PUSH);
				item_RelatedFile.setText("RelatedFile");
				item_RelatedFile.setImage(ImageHelper.getImage("null"));
				item_RelatedFile.setData("type",NodeType.RELATEDFILE);
				item_RelatedFile.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.RELATEDFILES);
				break;
			case RELATEDFILE:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.RELATEDFILE);
				break;
			case FACES:
				add_item = new MenuItem(menu, SWT.CASCADE);
				add_item.setText("Add");
				add_itemMenu = new Menu(add_item);
				add_item.setMenu(add_itemMenu);

				MenuItem item_Selection = new MenuItem(add_itemMenu, SWT.PUSH);
				item_Selection.setText("Selection");
				item_Selection.setImage(ImageHelper.getImage("null"));
				item_Selection.setData("type",NodeType.SELECTION);
				item_Selection.addSelectionListener(listenerFactory());

				MenuItem item_Register = new MenuItem(add_itemMenu, SWT.PUSH);
				item_Register.setText("Register");
				item_Register.setImage(ImageHelper.getImage("null"));
				item_Register.setData("type",NodeType.REGISTER);
				item_Register.addSelectionListener(listenerFactory());

				MenuItem item_Dialog = new MenuItem(add_itemMenu, SWT.PUSH);
				item_Dialog.setText("Dialog");
				item_Dialog.setImage(ImageHelper.getImage("null"));
				item_Dialog.setData("type",NodeType.DIALOG);
				item_Dialog.addSelectionListener(listenerFactory());

				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.FACES);
				break;
			case SELECTION:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.SELECTION);
				break;
			case REGISTER:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.REGISTER);
				break;
			case DIALOG:
				properties_item = new MenuItem(menu, SWT.PUSH);
				properties_item.setText("Properties");
				properties_item.addSelectionListener(propertiesListener());
				properties_item.setData("type",NodeType.DIALOG);
				break;
			default:
				break;
		}
	}

	private void addElement(MenuItem selectedItem){
		switch(((NodeType)selectedItem.getData("type"))){
			case PATTERNINSTANCE:
				TreeItem item_PatternInstance = new TreeItem(this.parent.getSelection()[0], 0);
				item_PatternInstance.setText(selectedItem.getText());
				item_PatternInstance.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_PatternInstance.setData("type",NodeType.PATTERNINSTANCE);
				break;
			case RELATEDFILES:
				TreeItem item_RelatedFiles = new TreeItem(this.parent.getSelection()[0], 0);
				item_RelatedFiles.setText(selectedItem.getText());
				item_RelatedFiles.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_RelatedFiles.setData("type",NodeType.RELATEDFILES);
				break;
			case RELATEDFILE:
				TreeItem item_RelatedFile = new TreeItem(this.parent.getSelection()[0], 0);
				item_RelatedFile.setText(selectedItem.getText());
				item_RelatedFile.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_RelatedFile.setData("type",NodeType.RELATEDFILE);
				break;
			case FACES:
				TreeItem item_Faces = new TreeItem(this.parent.getSelection()[0], 0);
				item_Faces.setText(selectedItem.getText());
				item_Faces.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_Faces.setData("type",NodeType.FACES);
				break;
			case SELECTION:
				TreeItem item_Selection = new TreeItem(this.parent.getSelection()[0], 0);
				item_Selection.setText(selectedItem.getText());
				item_Selection.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_Selection.setData("type",NodeType.SELECTION);
				break;
			case REGISTER:
				TreeItem item_Register = new TreeItem(this.parent.getSelection()[0], 0);
				item_Register.setText(selectedItem.getText());
				item_Register.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_Register.setData("type",NodeType.REGISTER);
				break;
			case DIALOG:
				TreeItem item_Dialog = new TreeItem(this.parent.getSelection()[0], 0);
				item_Dialog.setText(selectedItem.getText());
				item_Dialog.setImage(ImageHelper.getImage("primefaces.jpg"));
				item_Dialog.setData("type",NodeType.DIALOG);
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
			case RELATEDFILES:
				JFaceDialogRelatedFiles jfacedialogrelatedFiles = new JFaceDialogRelatedFiles(this.parent.getShell());
				jfacedialogrelatedFiles.setParent(this.parent);
				jfacedialogrelatedFiles.create();
				jfacedialogrelatedFiles.open();
				break;
			case RELATEDFILE:
				JFaceDialogRelatedFile jfacedialogrelatedFile = new JFaceDialogRelatedFile(this.parent.getShell());
				jfacedialogrelatedFile.setParent(this.parent);
				jfacedialogrelatedFile.create();
				jfacedialogrelatedFile.open();
				break;
			case FACES:
				JFaceDialogFaces jfacedialogfaces = new JFaceDialogFaces(this.parent.getShell());
				jfacedialogfaces.setParent(this.parent);
				jfacedialogfaces.create();
				jfacedialogfaces.open();
				break;
			case SELECTION:
				JFaceDialogSelection jfacedialogselection = new JFaceDialogSelection(this.parent.getShell());
				jfacedialogselection.setParent(this.parent);
				jfacedialogselection.create();
				jfacedialogselection.open();
				break;
			case REGISTER:
				JFaceDialogRegister jfacedialogregister = new JFaceDialogRegister(this.parent.getShell());
				jfacedialogregister.setParent(this.parent);
				jfacedialogregister.create();
				jfacedialogregister.open();
				break;
			case DIALOG:
				JFaceDialogDialog jfacedialogdialog = new JFaceDialogDialog(this.parent.getShell());
				jfacedialogdialog.setParent(this.parent);
				jfacedialogdialog.create();
				jfacedialogdialog.open();
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
