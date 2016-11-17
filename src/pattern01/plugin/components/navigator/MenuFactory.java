package pattern01.plugin.components.navigator;

import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
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
import pattern01.plugin.components.editors.generated.JFaceDialogPatternInstance;
import pattern01.plugin.components.editors.generated.JFaceDialogRelatedFile;

@SuppressWarnings("unused")
public class MenuFactory {
	
	private Tree parent = null;

	public MenuFactory(Tree tree_parent){
		this.parent = tree_parent;
		this.attachSelectionListener();
	}
	
	private void attachSelectionListener(){
		parent.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				parent.setMenu(null);
				TreeItem aux_item = (TreeItem)event.item;
				generateDisplayableOptions((NodeType)aux_item.getData("type"));
			}
		});
	}
	
	
	
	/* [Begin] Auto-generated code for menu factory do not remove */
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Thu Nov 17 16:32:08 GFT 2016
	* Creator: rlomez
	**/

	public void generateDisplayableOptions(NodeType nodeType){
		Menu menu = new Menu(parent);
		parent.setMenu(menu);
		MenuItem add_item = new MenuItem(menu, SWT.CASCADE);
		add_item.setText("Add");
		Menu add_itemMenu = new Menu(add_item);
		add_item.setMenu(add_itemMenu);
		switch (nodeType) {
			case PATTERNINSTANCE:
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
				break;
			case RELATEDFILES:
				MenuItem item_RelatedFile = new MenuItem(add_itemMenu, SWT.PUSH);
				item_RelatedFile.setText("RelatedFile");
				item_RelatedFile.setImage(ImageHelper.getImage("null"));
				item_RelatedFile.setData("type",NodeType.RELATEDFILE);
				item_RelatedFile.addSelectionListener(listenerFactory());
				break;
			case RELATEDFILE:
				break;
			case FACES:
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
				break;
			case SELECTION:
				break;
			case REGISTER:
				break;
			case DIALOG:
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
	
	private void propertiesListener(MenuItem menu_item){
		menu_item.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				//TODO Flexibilizar esta logica				
				if(parent.getSelection()[0].getData("type") != null){
						DefaultEditorInput dei = new DefaultEditorInput();
						dei.setInstanceTree(parent);
						NodeType nodetype_pointer = (NodeType)parent.getSelection()[0].getData("type");
						if (nodetype_pointer == NodeType.PATTERNINSTANCE){
							JFaceDialogPatternInstance jfd = new JFaceDialogPatternInstance(parent.getShell());
							jfd.setParent(parent);
							jfd.create();
							jfd.open();
						}else if (nodetype_pointer == NodeType.RELATEDFILE){
							(new JFaceDialogRelatedFile(parent.getShell())).open();
						}else if (nodetype_pointer == NodeType.SELECTION){
							
						}else if (nodetype_pointer == NodeType.DIALOG){
							
						}else if (nodetype_pointer == NodeType.REGISTER){
							
						}
					}
				}
		} );
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
